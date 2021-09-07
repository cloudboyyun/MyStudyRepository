'use strict';
const db = uniCloud.database();
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	if(!event.year) {
		throw "year should be specified."
	}
	let year = event.year;
	let yearGanzhi = await loadYearGanzhi(year);
	return yearGanzhi;
};

async function loadYearGanzhi(year) {
	const dbCmd = db.command;
	let query = await db.collection('t_wan_dairy')
	.field({
		'_id': false,
		'date': true,
		'term': true
	})
	.where({
		term: dbCmd.in(['立春', '立夏','立秋','立冬']),
		year: "" + year
	})
	.get();
	console.log('query', query);
	let records = query.data;
	return records;
}
