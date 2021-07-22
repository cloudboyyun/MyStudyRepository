'use strict';

const db = uniCloud.database()
exports.main = async (event, context) => {
	// console.log('event : ', event);
	// console.log('context', context);
	const collection = db.collection('t_news');
	const res = await collection.limit(10).get();
	return res.data;
};
