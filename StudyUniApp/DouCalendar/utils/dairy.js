
let DAIRY_VERSION = 'v0.1';
export function setDairyVersion(dairyVersion) {
	DAIRY_VERSION = dairyVersion;
}

export async function loadMonthData(year, month) {
	if(month < 1) {
		month = 12;
		year = year - 1;
	} else if(month > 12) {
		month = 1;
		year = year + 1;
	}
	
	let key = DAIRY_VERSION + '-' + year + '-' + month;
	let result = uni.getStorageSync(key);
	if(result) {
		return result;
	}
	
	let res = await uniCloud.callFunction({
			name: 'query-month-dairy',
			data: {
				"year": year,
				"month": month
			}
		});
	uni.setStorage({
		key: key,
		data: res.result
	});
	
	return res.result;
}

export async function loadYearGanzhi(year) {
	let key = DAIRY_VERSION + '-' + year;
	console.log("xy2", key)
	let records = uni.getStorageSync(key);
	if(!records) {
		let res = await uniCloud.callFunction({
				name: 'query-year-ganzhi',
				data: {
					"year": year
				}
			});
		records = res.result;
		uni.setStorage({
			key: key,
			data: records
		});
	}
	
	let result = new Map();
	for(let index in records) {
		let record = records[index];
		result.set(record.term, record.date);
	}
	return result;
}

