'use strict';
const _qr = require('qr-image');
exports.main = async (event, context) => {
	console.log("event", event);
	let queryStringParameters = event.queryStringParameters;
	let content = queryStringParameters.content;
	if(!content) {
		throw "content不能为空！"
	}
	let c = _qr.imageSync(content, {
		type: "png",
		margin: 2,
		ec_level: 'M'
	});
	let  qrcodeImage = new Buffer(c).toString("base64");
	switch (queryStringParameters.action) {
		case 'base64Data':
			return `data:image/png;base64,${qrcodeImage}`;
		case 'httpUrl':
			return {
				mpserverlessComposedResponse: true, // 使用阿里云返回集成响应是需要此字段为true
				isBase64Encoded: true,
				statusCode: 200,
				headers: {
					"content-type": "image/png"
				},
				body: qrcodeImage
			}
		default:
			throw "action不能为空！"
	}
	
};
