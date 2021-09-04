'use strict';

const db = uniCloud.database();

exports.main = async (event, context) => {
	const collection = await db.collection('t_wan_dairy');
	const dbCmd = db.command;
	var count = 1;
	while (count > 0) {
		let records = await collection.where({
			date: dbCmd.eq(null)
		}).limit(500).get();
		console.log("records:", records.data.length);
		for (let i in records.data) {
			let item = records.data[i];
			// let date = '' + item.year + '-0' + (item.month) + '-';
			let date = '' + item.year + '-' + (item.month) + '-';
			if (item.day < 10) {
				date = date + "0";
			}
			date = date + item.day;
			let res = await collection.doc(item._id).update({
				date: date
			});
			console.log(res, item.year, item.month, item.day);
		};
		let ccount = await collection.
		where({
			date: dbCmd.eq(null)
		}).count();
		count = ccount.total;
		console.log("count:", count);
	}
	return event;
}
