'use strict';
const config = {
	// channels: ["财经","头条"],
	channels: ["财经"],
	url: "https://way.jd.com/jisuapi/get",
	appkey: "f1eca1bfa67398b7089044dddc34f677",
	num: 2,
	start: 0
}

exports.main = async (event, context) => {
	config.channels.forEach((channel) => {
		getNews(channel).then((newsList) => {
			if(newsList) {
				newsList.forEach((news) => {
					console.log(news);
				}) 
			}
		});
	})
	return event
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
	// console.log("res", res);
	let result = JSON.parse(res.data.toString('utf8'));
	console.log(result.code);
	if(result.code = "10000") {
		return result.result.result.list;
	} else {
		return null;
	}
}
