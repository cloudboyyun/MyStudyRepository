
const DAIRY_VERSION = 'v0'
export async function loadMonthData(year, month) {
	let thisMonthData = await prepareMonthData(year, month);
	prepareMonthData(year, month-1);
	prepareMonthData(year, month +1);
	return thisMonthData;
}

async function prepareMonthData(year, month) {
	if(month < 1) {
		month = 1;
		year = year - 1;
	} else if(month > 12) {
		month = 1;
		year = year + 1;
	}
	
	let key = DAIRY_VERSION + year + '-' + month;
	let result = uni.getStorageSync(key);
	if(result) {
		console.log('从缓存中获取', key);
		return result;
	}
	
	console.log('prepareMonthData', key);
	
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

