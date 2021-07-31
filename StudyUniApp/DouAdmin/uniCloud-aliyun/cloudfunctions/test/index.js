'use strict';
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event);

	let message1 = {
		body: null
	};
	let message2 = {
		body: {
			user: {
				firstName: "Martin"
			}
		}
	};
	printOut(message1);
	printOut(message2);

	return event
};

function printOut(message) {
	// 下面这句会报错：Cannot read property 'user' of null
	// let firstName = message.body.user.firstName;

	// 所以要进行如下判断，但这种写法太繁琐
	// let firstName = (message &&
	// 	message.body &&
	// 	message.body.user &&
	// 	message.body.user.firstName) || 'default';

	// ES2020引入新的运算符: ?.
	let firstName = message?.body?.user?.firstName || 'default';
	console.log(firstName)
}
