const {
  globalCache
} = require('cloud-cache')

function getType(val) {
  return Object.prototype.toString.call(val).slice(8, -1).toLowerCase()
}

const CACHE_KEY_PREFIX = 'ip_freq_limit_'
class IPUtils {
  static checkIp(ip, pattern) {
    if (getType(pattern) === 'regexp') {
      return pattern.test(ip)
    }
    return pattern === ip
  }

  static isIpInPatterns(ip, patterns) {
    let isMatch = false
    for (let i = 0; i < patterns.length; i++) {
      const pattern = patterns[i]
      if (IPUtils.checkIp(ip, pattern)) {
        isMatch = true
        break
      }
    }
    return isMatch
  }

  static isIpExceedFrequencyLimit(ip, frequencyLimit, frequencyLimitBucket) {
    const key = CACHE_KEY_PREFIX + ip
    const bucketSize = frequencyLimitBucket
    const maxLimit = frequencyLimit * bucketSize
    const now = Date.now()
    if (!globalCache.has(key)) {
      globalCache.set(key, {
        time: now,
        limit: maxLimit
      }, {
        expired: now + bucketSize * 1000
      })
      return false
    }
    const ipInfo = globalCache.get(key)
    const limitAdd = Math.floor((now - ipInfo.time) / 1000 * frequencyLimit) - 1
    const finalLimit = limitAdd + ipInfo.limit
    if (limitAdd !== 0) {
      globalCache.set(key, {
        time: now,
        limit: finalLimit > maxLimit ? maxLimit : finalLimit
      }, {
        expired: now + bucketSize * 1000
      })
    }
    if (finalLimit < 0) {
      return true
    }
    return false
  }
}

module.exports = ({
  whiteList = [],
  blackList = [],
  frequencyLimit,
  frequencyLimitBucket = 10
} = {}) => {
  return async function(ctx, next) {
    const clientIp = ctx.context.CLIENTIP
    if (IPUtils.isIpInPatterns(clientIp, whiteList)) {
      await next()
      return
    } else if (IPUtils.isIpInPatterns(clientIp, blackList)) {
      ctx.throw('403', '禁止访问')
    }
    if (frequencyLimit) {
      if (IPUtils.isIpExceedFrequencyLimit(clientIp, frequencyLimit, frequencyLimitBucket)) {
        ctx.throw('403', '访问过于频繁')
      }
    }
    await next()
  }
}
