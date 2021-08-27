'use strict';

const db = uniCloud.database();

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	const collection = await db.collection('t_dairy');
	let count = await collection.count();
	console.log("count:", count)
	let records = await collection.limit(200).get();
	console.log("records:", records.data.length);
	for(let i in records.data) {
		let item = records.data[i];
		let date = '' + item.year + '-0' + (item.month + 1) + '-';
		if(item.day < 10) {
			date = date + "0";
		}
		date = date + item.day;
		let res = await collection.doc(item._id).update({
			date: date
		});
		console.log(res, item.year, item.month, item.day);
	};
	
	
	//返回数据给客户端
	return event
};
