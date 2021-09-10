'use strict';

const uniID = require('uni-id')
exports.main = async (event, context) => {
	let uniIDIns = uniID.createInstance({ // 创建uni-id实例，其上方法同uniID
		context: context // 传入context防止不同请求互相影响
	})
	/* 如果你通过云函数Url访问
	 * 使用GET时参数位于event.queryStringParameters
	 * 使用POST时参数位于event.body
	 * 请自行处理上述场景
	 */
	let params = event.params || {}
	let payload = {}
	let noCheckAction = ['register', 'checkToken', 'encryptPwd', 'login', 'loginByWeixin', 'sendSmsCode',
		'setVerifyCode', 'loginBySms', 'loginByEmail', 'code2SessionWeixin', 'code2SessionAlipay'
	]
	if (noCheckAction.indexOf(event.action) === -1) {
		if (!event.uniIdToken) {
			return {
				code: 403,
				msg: '缺少token'
			}
		}
		payload = await uniIDIns.checkToken(event.uniIdToken)
		if (payload.code && payload.code > 0) {
			return payload
		}
		params.uid = payload.uid
	}
	let res = {}

	switch (event.action) {
		case 'register':
			res = await uniIDIns.register(params);
			break;
		case 'login':
			res = await uniIDIns.login({
				...params,
				// 不指定queryField的情况下只会查询username
				queryField: ['username', 'email', 'mobile']
			});
			break;
		case 'logout':
			res = await uniIDIns.logout(event.uniIdToken);
			break;
		case 'updatePwd':
			res = await uniIDIns.updatePwd(params);
			break;
		case 'setAvatar':
			res = await uniIDIns.setAvatar(params);
			break;
		case 'bindMobile':
			res = await uniIDIns.bindMobile(params);
			break;
		case 'unbindMobile':
			res = await uniIDIns.unbindMobile(params);
			break;
		case 'bindEmail':
			res = await uniIDIns.bindEmail(params);
			break;
		case 'unbindEmail':
			res = await uniIDIns.unbindEmail(params);
			break;
		case 'code2SessionWeixin':
			res = await uniIDIns.code2SessionWeixin(params);
			break;
		case 'loginByWeixin':
			res = await uniIDIns.loginByWeixin(params);
			break;
		case 'bindWeixin':
			res = await uniIDIns.bindWeixin(params);
			break;
		case 'unbindWeixin':
			res = await uniIDIns.unbindWeixin(params.uid);
			break;
		case 'code2SessionAlipay':
			res = await uniIDIns.code2SessionAlipay(params);
			break;
		case 'loginByAlipay':
			res = await uniIDIns.loginByAlipay(params);
			break;
		case 'bindAlipay':
			res = await uniIDIns.bindAlipay(params);
			break;
		case 'unbindAlipay':
			res = await uniIDIns.unbindAlipay(params.uid);
			break;
		case 'checkToken':
			// 注意1.1.0版本会返回userInfo（res.userInfo），如果没有必要请不要返回全部信息给客户端
			res = await uniIDIns.checkToken(event.uniIdToken)
			break;
		case 'resetPwd':
			res = await uniIDIns.resetPwd({
				uid: params.uid,
				password: '123456'
			});
			break;
		case 'encryptPwd':
			const password = await uniIDIns.encryptPwd('123456');
			res = {
				code: 0,
				msg: '密码加密完成',
				password
			}
			break;
		case 'sendSmsCode':
			res = await uniIDIns.sendSmsCode(params);
			break;
		case 'setVerifyCode':
			res = await uniIDIns.setVerifyCode(params);
			break;
		case 'loginBySms':
			res = await uniIDIns.loginBySms(params);
			break;
		case 'loginByEmail':
			res = await uniIDIns.loginByEmail(params);
			break;
		case 'updateUser':
			res = await uniIDIns.updateUser(params);
			break;
		case 'setUserInviteCode':
			res = await uniIDIns.setUserInviteCode(params);
			break;
		case 'acceptInvite':
			res = await uniIDIns.acceptInvite(params);
			break;
		case 'addRole':
			res = await uniIDIns.addRole(params);
			break;
		case 'getRoleList':
			res = await uniIDIns.getRoleList(params);
			break;
		case 'addPermission':
			res = await uniIDIns.addPermission(params);
			break;
		case 'getPermissionList':
			res = await uniIDIns.getPermissionList(params);
			break;
		case 'getRoleByUid':
			res = await uniIDIns.getRoleByUid(params);
			break;
		case 'bindRole':
			res = await uniIDIns.bindRole(params);
			break;
		case 'bindPermission':
			res = await uniIDIns.bindPermission(params);
			break;
		default:
			res = {
				code: 403,
				msg: '非法访问'
			}
			break;
	}

	//返回数据给客户端
	return res
};
