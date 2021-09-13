// config.js
const ipFilter = require('ip-filter')
module.exports = {
  debug: true, // 调试模式时，将返回 stack 错误堆栈
  baseDir: __dirname, // 必选，应用根目录
  middleware: [
    [
      ipFilter({
        whiteList: [],
        blackList: [],
        frequencyLimit: 1
      }),
      {
        name: 'ipFilter',
        enable: true,
        ignore: ''
      },
    ]
  ], // 自定义中间件
}
