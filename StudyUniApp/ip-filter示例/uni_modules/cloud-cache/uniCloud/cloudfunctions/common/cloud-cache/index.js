class CloudCache {
  constructor({
    maxKey = 10000
  } = {}) {
    this.store = new Map()
  }

  checkAndClean({
    spare = 1
  } = {}) {
    const store = this.store
    for (let [key, internalValue] in store) {
      if (internalValue.expired && internalValue.expired < Date.now()) {
        store.delete(key)
      }
    }
    if (store.size + spare > this.maxKey) {
      let keys = store.keys
      for (let i = 0; i < store.size + spare - this.maxKey; i++) {
        const key = keys.next().value
        store.delete(key)
      }
    }
  }

  set(key, value, {
    expired
  } = {}) {
    const store = this.store
    this.checkAndClean()
    store.set(key, {
      value,
      expired
    })
  }

  get(key) {
    const store = this.store
    const internalValue = store.get(key)
    if (!internalValue) {
      return
    }
    if (internalValue.expired && internalValue.expired < Date.now()) {
      store.delete(key)
      return
    }
    return internalValue.value
  }

  has(key) {
    const store = this.store
    const internalValue = store.get(key)
    if (!internalValue) {
      return false
    }
    if (internalValue.expired && internalValue.expired < Date.now()) {
      store.delete(key)
      return false
    }
    return true
  }

  remove(key) {
    this.store.delete(key)
  }

  get size() {
    this.checkAndClean({
      spare: 0
    })
    return this.store.size
  }

  clear() {
    this.store.clear()
  }
}

const globalCache = new CloudCache()

module.exports = {
  CloudCache,
  globalCache
}
