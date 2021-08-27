'use strict';
const db = uniCloud.database();

const config = {
	url: "http://v.juhe.cn/calendar/day",
	appkey: "bd9e3e430d9277f8f46602798af45a3c",
	retriveTimesPerDay: 100
};

exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	let now = new Date();
	let startDate = new Date(now.getFullYear(), 0, 1);
	if(event.startDate) {
		startDate = new Date(event.startDate);
	}
	let startYear = startDate.getFullYear();
	let startMonth = startDate.getMonth();
	let startDay = startDate.getDate();
	
	const collection = await db.collection('t_dairy');
	let lastRecordRecord = await collection
		.orderBy("year", "desc")
		.orderBy("month", "desc")
		.orderBy("day", "desc")
		.limit(1)
		.get();
	
	if(lastRecordRecord.data.length) {
		console.log("lastRecordRecord", lastRecordRecord);
		let lastRecord = lastRecordRecord.data[0];
		startYear = lastRecord.year;
		startMonth = lastRecord.month;
		startDay = lastRecord.day + 1;
	}
	
	for(let i=0; i<config.retriveTimesPerDay; i++) {
		let date = new Date(startYear, startMonth, startDay+i);
		let dateDesc = await getDairyDetail(date);
		let dateStr = date.format("yyyy-MM-dd");
		let record = {
			date: dateStr,
			year: date.getFullYear(),
			month: date.getMonth(),
			day: date.getDate(),
			dateDesc: dateDesc,
			create_date: now.getTime(),
			modified_date: now.getTime()
		}
		collection.add(record);
	}
	
	//返回数据给客户端
	return event
};

Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
} 

async function getDairyDetail(date) {
	let sDate = date.format("yyyy-M-d");
	console.log(sDate);
	
	const res = await uniCloud.httpclient.request(config.url, {
		method: 'GET',
		data: {
			date: sDate,
			key: config.appkey
		}
	});
	let result = JSON.parse(res.data.toString('utf8'));
	console.log(result);
	if (result.reason = "Success") {
		return result.result.data;
	} else {
		return null;
	}
}
