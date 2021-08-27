
export function getMonthDays(year, month) {
	// 本月的天数
	let days = new Date(year, month + 1, 0).getDate();
	// 本月第一天的日期
	let monthStartDate = new Date(year, month, 1);
	// 本月第一天是周几
	let week = monthStartDate.getDay();
	// 如果不是周日，则本页第一天应该是哪天
	let calendarStartDate = new Date(year, month, -week + 1);
	console.log("calendarStartDate", calendarStartDate);

	// 本月最后一天
	let monthEndDate = new Date(year, month + 1, 0);
	week = monthEndDate.getDay();
	// 如果不是周六，那本页最后一天应该是哪天？
	let calendarEndDate = new Date(year, month, days + (6 - week));
	console.log("calendarEndDate", calendarEndDate);
	
	let result = [];
	for (let i = calendarStartDate; i <= calendarEndDate;i.setDate(i.getDate() + 1)) {
		var item = {
			date: i.getDate(),
			month: i.getMonth(),
			inThisMonth: i.getMonth() == month
		};
		result.push(item);
	}
	return result;
}