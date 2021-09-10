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

export function dateFormat(date, format) {
	return date.format(format);
}

export function monthIncrease(year, month) {
	let year1 = year;
	let month1 = month + 1;
	if(month1 > 12) {
		month1 = 1;
		year1++;
	}
	let result = {
		year: year1,
		month: month1
	}
	return result;
}

export function monthDecrease(year, month) {
	let year1 = year;
	let month1 = month - 1;
	if(month1 < 1) {
		month1 = 12;
		year1--;
	}
	let result = {
		year: year1,
		month: month1
	}
	return result;
}