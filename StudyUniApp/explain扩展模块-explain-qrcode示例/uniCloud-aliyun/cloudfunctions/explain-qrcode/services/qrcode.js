"use strict";

const explain = require("explain");
explain.qrcode = require("explain-qrcode");

module.exports = class qrcode extends explain.service {

	make({
		text,
		ec_level = "M",
		margin = 2
	}) {
		console.log({
			text,
			ec_level,
			margin
		});
		
		let qrcodeImage = explain.qrcode.make({
			text,
			type: "base64",
			errorCorrectLevel: ec_level,
			margin
		});

		if (this.context.isHttp) {
			// 如果是来自云函数URL化的请求，则返回集成响应，响应为图片
			return {
				mpserverlessComposedResponse: true, // 使用阿里云返回集成响应是需要此字段为true
				isBase64Encoded: true,
				statusCode: 200,
				headers: {
					"content-type": "image/png"
				},
				body: qrcodeImage
			}
		} else {
			// 否则返回图片base64资源
			return `data:image/png;base64,${qrcodeImage}`;
		}
	}

}
