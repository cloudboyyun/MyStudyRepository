'use strict';
exports.main = async (event, context) => {
	let news = await getNews("wuhan");
	console.log(news);
	console.log(new Date());
	return "ok";
};

async function getNews(channel) {
	return channel;
}
