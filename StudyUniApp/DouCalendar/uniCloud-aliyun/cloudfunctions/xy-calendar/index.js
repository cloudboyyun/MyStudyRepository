'use strict';

const uniID = require('uni-id')
const db = uniCloud.database();
exports.main = async (event, context) => {
	let uniIDIns = uniID.createInstance({ // 创建uni-id实例，其上方法同uniID
		context: context // 传入context防止不同请求互相影响
	})
	/* 如果你通过云函数Url访问
	 * 使用GET时参数位于event.queryStringParameters
	 * 使用POST时参数位于event.body
	 * 请自行处理上述场景
	 */
	let params = event.params || {}
	let payload = {}
	let noCheckAction = ['register']
	if (noCheckAction.indexOf(event.action) === -1) {
		if (!event.uniIdToken) {
			console.error('缺少token', params)
			return {
				code: 403,
				msg: '缺少token'
			}
		}
		payload = await uniIDIns.checkToken(event.uniIdToken)
		if (payload.code && payload.code > 0) {
			return payload
		}
		params.uid = payload.uid
	}
	let res = {}

	switch (event.action) {
		case 'getDairyConfig':
			res = await getDairyConfig(params);
			break;
		case 'loadMonthData':
			res = await loadMonthData(params);
			break;
		case 'queryYearGanzhi':
			res = await queryYearGanzhi(params);
			break;
		default:
			res = {
				code: 403,
				msg: '非法访问'
			}
			break;
	}

	//返回数据给客户端
	return res
};

async function queryYearGanzhi(params) {
	console.log("queryYearGanzhi params", params);
	if(!params.year) {
		throw "year should be specified."
	}
	let year = params.year;
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
	let records = query.data;
	return records;
}

async function getDairyConfig(params) {
	console.log("getDairyConfig params", params);
	let query = await db.collection('t_dairy_config')
	.field({
		'_id': false
	})
	.get();
	let configData = query.data[0]; 
	//返回数据给客户端
	return configData;
}

async function loadMonthData(params) {
	console.log("loadMonthData params", params);
	if(!params.year || !params.month) {
		throw "year and month should be specified."
	}
	let year = params.year;
	let month = params.month;
	
	// 本月的天数
	let days = new Date(year, month, 0).getDate();
	// 本月第一天的日期
	let monthStartDate = new Date(year, month-1, 1);
	// 本月第一天是周几
	let week = monthStartDate.getDay();
	// 如果不是周日，则本页第一天应该是哪天
	let calendarStartDate = new Date(year, month-1, -week + 1);
	// 本月最后一天
	let monthEndDate = new Date(year, month, 0);
	week = monthEndDate.getDay();
	// 如果不是周六，那本页最后一天应该是哪天？
	let calendarEndDate = null;
	if(week == 6) {
		calendarEndDate = monthEndDate;
	} else {
		calendarEndDate = new Date(year, month-1, days + (6 - week));
	}
	
	const dbCmd = db.command;
	let query = await db.collection('t_wan_dairy')
	.field({
		'_id': false,
		'oDate': false
	})
	.where({
		date: dbCmd.gte(calendarStartDate.format('yyyy-MM-dd')).and(dbCmd.lte(calendarEndDate.format('yyyy-MM-dd')))
	})
	.orderBy('date', 'asc')
	.get();
	
	let records = query.data;
	let result = {
		year: year,
		month: month,
		dates: null
	}
	
	let dates = [];
	
	for(let index in records) {
		let record = records[index];
		let date = new Date(record.date);
		let lunarDesc = null;
		if (record.lunarDate == "1") {
			lunarDesc = record.lMonth + "月";
		} else {
			lunarDesc = record.lDate;
		}
		let item = {
			dayOfWeek: date.getDay(),
			year: record.year,
			month: record.month,
			day: record.day,
			date: record.date,
			lunar: record.lMonth + "月" + record.lDate,
			lunarDesc: lunarDesc,
			holiday: record.term,
			gzYear: record.gzYear,
			gzMonth: record.gzMonth,
			gzDate: record.gzDate,
			animal: record.animal,
			weekday: record.cnDay,
			suit: record.suit,
			avoid: record.avoid,
			status: record.status
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
