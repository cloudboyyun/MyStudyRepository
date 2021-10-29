# EXPLAIN-QRCODE

explain扩展模块，主要用于生成二维码。

QQ群：[970799055](https://jq.qq.com/?_wv=1027&k=KFkDL5gp)。

支持云函数URL化，集成响应可直接响应为图片，无需做转换，使用更加简单方便。相比uQRCode，explain-qrcode书写代码量更少，且有网就有二维码，100%生成成功，不会因为平台差异，设备差异导致生成失败，无需在前端做适配和兼容。

云函数URL化预览：[https://75bb46ae-4018-4f21-94a2-c226eb480d30.bspapp.com/http/qrcode?text=explain-qrcode](https://75bb46ae-4018-4f21-94a2-c226eb480d30.bspapp.com/http/qrcode?text=explain-qrcode)（空间有限，仅做预览，请不要直接在项目中使用。应上传至云函数，开启URL化后将该地址替换成你的地址。）

若从插件市场直接部署后报错，在本地重新上传部署一次即可。

## 模块依赖
需要安装依赖：

|模块名称|最低版本|链接|
|--	|--	|--	|
|explain|1.0.83|[https://ext.dcloud.net.cn/plugin?id=3312](https://ext.dcloud.net.cn/plugin?id=3312)|

## 模块方法
### make(options)
生成二维码。

|参数|类型|必填|说明|
|--	|--	|--	|--	|
|text|String|是|二维码内容|
|type|String|否|类型，可选`buffer`、`base64`，默认`buffer`|
|imageType|String|否|返回图片类型，默认`png`|
|errorCorrectLevel|String|否|纠错等级，可选`L`、`M`、`Q`、`H`，默认`M`|
|margin|Number|否|边距，默认`1`|

#### 使用示例
```javascript
const explain = require("explain");
explain.qrcode = require("explain-qrcode");

let qrcodeImage = explain.qrcode.make({
	text: "explain-qrcode",
	type: "base64",
	imageType: "png",
	errorCorrectLevel: "M",
	margin: 2
});
```

### qr()
qrcode模块依赖于qr-image，调用该方法得到qr-image对象，可以使用qr-image方法。

## 插件使用方式
### 参数说明
|参数|类型|必填|说明|
|--	|--	|--	|--	|
|text|String|是|二维码内容|
|ec_level|String|否|纠错等级，可选`L`、`M`、`Q`、`H`，默认`M`|
|margin|Number|否|边距，默认`2`|

### 云函数URL化示例
#### html
```html
<template>
	<view>
		<!-- 空间有限，仅做预览，请不要直接在项目中使用。应上传至云函数，开启URL化后将该地址替换成你的地址。 -->
		<image src="https://75bb46ae-4018-4f21-94a2-c226eb480d30.bspapp.com/http/qrcode?text=explain-qrcode&ec_level=M&margin=2" mode="widthFix" />
	</view>
</template>
```
### 云函数调用示例
#### html
```html
<template>
	<view>
		<image :src="qrcodeSrc" mode="widthFix" />
	</view>
</template>
```
#### javascript
```javascript
export default {
	data() {
		return {
			qrcodeText: 'explain-qrcode',
			qrcodeErrorCorrectLevel: 'M',
			qrcodeMargin: 2,
			qrcodeSrc: ''
		}
	},
	onLoad() {
		uniCloud.callFunction({
			name: 'explain-qrcode',
			data: {
				service: 'qrcode',
				action: 'make',
				params: {
					text: this.qrcodeText,
					ec_level: this.qrcodeErrorCorrectLevel,
					margin: this.qrcodeMargin
				}
			}
		}).then(res => {
			this.qrcodeSrc = res.result // 得到图片base64
		})
	}
}
```