'use strict';
const db = uniCloud.database();

const config = {
	url: "https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php",
	retriveTimesPerDay: 1
};

// import iconv-lite from 'iconv-lite';

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event);
	
	const collection = await db.collection('t_wan_dairy');

	let startYear = 1900;
	let startMonth = 2;
	for(let year = startYear; year<=2050; year++) {
		for(let month = startMonth; month<=11; month=month+3) {
			console.log(year, month);
			let monthData = await getMonthData(year, month);
			if(monthData) {
				await collection.add(monthData);
				console.log("成功:", year, month);
			} else {
				console.error("失败", year, month);
			}
		}
	}
	// let monthData = await getMonthData(2035, 11);
	// if(monthData) {
	// 	await collection.add(monthData);
	// 	console.log("成功");
	// } else {
	// 	console.error("失败");
	// }
	return event;
};

async function getMonthData(year, month) {
	let queryMonth = '' + year + '年' + month + '月';
	queryMonth = encodeURI(queryMonth);
	
	let url = 'https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?'
		+ 'tn=wisetpl&format=json&resource_id=39043&query=' + queryMonth;
	const res = await uniCloud.httpclient.request(url,
		{
			method: 'GET'
		});
	var iconv = require('iconv-lite');
	console.log('res', res);
	let queryResult = JSON.parse(iconv.decode(res.data, 'GBK'));
	if(queryResult.status == 0) {
		let monthData = queryResult.data[0].almanac;
		return monthData;
		// collection.add(monthData);
	}
}