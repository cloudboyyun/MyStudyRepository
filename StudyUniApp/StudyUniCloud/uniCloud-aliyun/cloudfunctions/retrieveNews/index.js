'use strict';
const config = {
	channels: ["财经", "头条"],
	url: "https://way.jd.com/jisuapi/get",
	appkey: "f1eca1bfa67398b7089044dddc34f677",
	num: 2,
	start: 0
};
const db = uniCloud.database()

exports.main = async (event, context) => {
	const collection = db.collection('t_news');
	var now = new Date().getTime();
	for(let channel of config.channels) {
		let newsList = await getNews(channel);
		if (newsList && newsList.length > 0) {
			newsList.forEach((news) => {
				news.channel = channel;
				news.create_date = now;
				news.modified_date = now;
			});
			collection.add(newsList);
		}
	}
	config.channels.forEach(async(channel) => {
		
	})
	return now;
};

async function getNews(channel) {
	console.log(channel);
	const res = await uniCloud.httpclient.request(config.url, {
		method: 'GET',
		data: {
			channel: channel,
			num: config.num,
			start: config.start,
			appkey: config.appkey,
			dataType: 'json'
		}
	});
	let result = JSON.parse(res.data.toString('utf8'));
	if (result.code = "10000") {
		return result.result.result.list;
	} else {
		return null;
	}
}
