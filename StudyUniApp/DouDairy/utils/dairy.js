
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