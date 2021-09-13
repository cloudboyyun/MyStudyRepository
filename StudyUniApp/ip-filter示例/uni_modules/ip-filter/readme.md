# ip-filter

拦截黑名单ip、限制单ip访问频率

此模块是uni-cloud-router的中间件，如果不清楚如何使用请参考示例项目内的hello-uni-cloud-router云函数内的`config.js文件`

此模块依赖cloud-cache模块，插件市场导入本插件会自动导入cloud-cache

## 用法

```js
// hello-uni-cloud-router的config.js
const ipFilter = require('ip-filter')
module.exports = {
  debug: true, // 调试模式时，将返回 stack 错误堆栈
  baseDir: __dirname, // 必选，应用根目录
  middleware: [
    [
      ipFilter({
        whiteList: [], // ip白名单数组，支持正则
        blackList: [], // ip黑名单数组，支持正则
        frequencyLimit: 2, // 每秒最多能请求次数
        frequencyLimitBucket: 10 // 桶大小，以此配置为例，代表单个IP每10（frequencyLimitBucket）秒可以访问2 * 10（frequencyLimit*frequencyLimitBucket）次
      }),
      {
        name: 'ipFilter',
        enable: true,
        ignore: ''
      },
    ]
  ], // 自定义中间件
}
```

**额外注意事项**

- HBuilderX 3.1.7及以下版本批量上传云函数时由Bug，需要额外上传一次ip-filter才能正确建立ip-filter和cloud-cache之间的依赖关系
