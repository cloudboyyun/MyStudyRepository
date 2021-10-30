'use strict';
const _qr = require('qr-image');
exports.main = async (event, context) => {
	console.log("event", event);
	let content = event.content;
	if(!content) {
		throw "content不能为空！"
	}
	let c = _qr.imageSync(content, {
		type: "png",
		margin: 2,
		ec_level: 'M'
	});
	let  qrcodeImage = new Buffer(c).toString("base64");
	return `data:image/png;base64,${qrcodeImage}`;
};
