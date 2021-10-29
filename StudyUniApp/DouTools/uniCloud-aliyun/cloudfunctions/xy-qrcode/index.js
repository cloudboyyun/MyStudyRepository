'use strict';
const _qr = require('qr-image');
exports.main = async (event, context) => {
	console.log("event", event);
	let content = event.content;
	if(!content) {
		throw "content不能为空！"
	}
	let c = _qr.imageSync(content, {
		type: "png"
	});
	return c;
	// switch (r) {
	// 	case "buffer":
	// 		return c;
	// 	case "base64":
	// 		return new Buffer(c).toString("base64")
	// }
	// return event
};
