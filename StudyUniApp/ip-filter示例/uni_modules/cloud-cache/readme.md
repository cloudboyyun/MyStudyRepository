# cloud-cache

利用云函数实例复用（包括单实例多并发的场景），缓存部分数据到全局

## 用法

cloud-cache是作为公共模块来使用的，使用公共模块请参考[公共模块](https://uniapp.dcloud.net.cn/uniCloud/cf-common)

```js
const {
  CloudCache, // CloudCache类，用于创建一个全局缓存
  globalCache // CloudCache的实例，可以直接使用
} = require('cloud-cache')
```

### 自行创建CloudCache实例

```js
const {
  CloudCache, // CloudCache类，用于创建一个全局缓存
  globalCache // CloudCache的实例，可以直接使用
} = require('cloud-cache')
const cache = new CloudCache({ // 注意一定要在全局（函数外部）创建否则没有意义
  maxKey: 10000 // 最大存储键值数，超出将进行清理，默认10000
})
// 创建的cache和globalCache包含一样的方法和属性
exports.main = async function(event, context) {
  cache.set('message', 'hello cloud cache', {
    expired: Date.now() + 3000 // 过期时间，时间戳形式
  })
  cache.get('message') === 'hello cloud cache' // true
}
```

### 设置缓存

```js
globalCache.set(
  'message',  // 键
  'hello cloud cache', // 值
  {
    expired: Date.now() + 3000 // 过期时间，时间戳形式
  }
)
```

### 根据键值获取缓存

```js
globalCache.get('message')
```

### 删除指定键值

```js
globalCache.remove('message')
```

### 判断是否包含指定键值

```js
globalCache.has('message') // true | false
```

### 清空缓存

```js
globalCache.clear() // true | false
```

### 获取当前缓存的键值数

```js
globalCache.size // 注意size是属性并非方法
```
