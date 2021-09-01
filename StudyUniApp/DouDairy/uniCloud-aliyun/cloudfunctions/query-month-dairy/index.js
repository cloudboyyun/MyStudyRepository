'use strict';

const db = uniCloud.database();

// 获取月历的每页数据，每周以周日开始
// event中要求传入:
// year: 年
// month: 月（1~12）
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event);
	if(!event.year || !event.month) {
		throw "year and month should be specified."
	}
	let year = event.year;
	let month = event.month - 1;
	
	let thisMonthData = await loadMonthData(year, month);
	return thisMonthData;
};

async function loadMonthData(year, month) {
	// 本月的天数
	let days = new Date(year, month + 1, 0).getDate();
	// 本月第一天的日期
	let monthStartDate = new Date(year, month, 1);
	// 本月第一天是周几
	let week = monthStartDate.getDay();
	// 如果不是周日，则本页第一天应该是哪天
	let calendarStartDate = new Date(year, month, -week + 1);
	console.log("calendarStartDate", calendarStartDate.format('yyyy-MM-dd'));
	// 本月最后一天
	let monthEndDate = new Date(year, month + 1, 0);
	week = monthEndDate.getDay();
	// 如果不是周六，那本页最后一天应该是哪天？
	let calendarEndDate = new Date(year, month, days + (6 - week));
	console.log("calendarEndDate", calendarEndDate.format('yyyy-MM-dd'));
	
	const dbCmd = db.command;
	let query = await db.collection('t_dairy')
	.field({
		'date': true,
		'year': true,
		'month': true,
		'day': true,
		'dateDesc': true
	})
	.where({
		date: dbCmd.gte(calendarStartDate.format('yyyy-MM-dd')).and(dbCmd.lte(calendarEndDate.format('yyyy-MM-dd')))
	})
	.orderBy('date', 'asc')
	.get();
	
	let records = query.data;
	let result = {
		dates: null,
		lunarYear: null,
		animalsYear: null
	}
	
	for(let index in records) {
		let item = records[index];
		if(item.year == year && item.month == month) {
			result.lunarYear = item.dateDesc.lunarYear;
			result.animalsYear = item.dateDesc.animalsYear;
			break;
		}
	}
	
	let dates = [];
	
	for(let index in records) {
		let record = records[index];
		let date = new Date(record.date);
		let lunarDesc = record.dateDesc.lunar;
		let strArr = lunarDesc.split("月");
		if (strArr[1] == "初一") {
			lunarDesc = strArr[0] + "月";
		} else {
			lunarDesc = strArr[1];
		}
		let item = {
			dayOfWeek: date.getDay(),
			year: record.year,
			month: record.month,
			day: record.day,
			date: record.date,
			lunar: record.dateDesc.lunar,
			lunarDesc: lunarDesc,
			holiday: record.dateDesc.holiday
		}
		dates.push(item);
	}
	
	result.dates = dates;
	
	//返回数据给客户端
	return result;
}

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
