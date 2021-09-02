'use strict';

const db = uniCloud.database();
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	let query = await db.collection('t_dairy_config')
	.field({
		'_id': false
	})
	.get();
	let configData = query.data[0]; 
	//返回数据给客户端
	return configData;
};
