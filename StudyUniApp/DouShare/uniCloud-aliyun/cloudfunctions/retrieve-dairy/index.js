'use strict';
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log('event : ', event)
	let result = yearApi('2021', '8');
	console.log("result", result);
	return event
};

/**
 * [yearApi 获取指定年月的节假日 （包括前后月）]
 * @param  string $year  [年]
 * @param  string $month [月]
 * @return [type]        [返回 放假时间 以及 补班时间]
 */
function yearApi($year='2018',$month='1'){
 
	// header('Content-Type:application/json;charset=utf-8');
	$url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query={$year}年{$month}月&resource_id=6018&format=json";
	$str = file_get_contents($url);
	$content = iconv("GBK//IGNORE","UTF-8",$str);
 
	$arr = json_decode($content,true); //获取到数组格式的数据。
	$holiday = $arr['data'][0]['holiday'];
	//筛选出放假的日期 和 补班的日期
	foreach ($holiday as $k => $v) {
		foreach ($v['list'] as $key => $value) {
			
			if($value['status'] == 1){ //获取假期
				$r_arr['holiday'][] = $value['date'];
			}elseif($value['status'] == 2){ //获取补班的日期
				$r_arr['work'][] = $value['date'];
			}
		}
	}
	//得到 结果
	return $r_arr;
}