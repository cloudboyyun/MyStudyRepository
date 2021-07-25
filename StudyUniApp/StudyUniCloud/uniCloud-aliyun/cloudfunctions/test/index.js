'use strict';
exports.main = async (event, context) => {
	let news = await getNews("wuhan");
	console.log(news);
	return "ok";
};

async function getNews(channel) {
	return channel;
}
