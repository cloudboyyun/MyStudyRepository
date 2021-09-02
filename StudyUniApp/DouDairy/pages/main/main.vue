<template>
	<view class='page'>
		<view v-if="showLoading" class='loading'>
		  <image src='/static/images/loading.gif'></image>
		</view>
		<view class='title-area'>
			<view class='today-icon'></view>
			<view class='year-month'>{{year}}年{{month}}月</view>
			<view class='today-icon' @click="onTodayIconClick()">
				<image class='today-icon-image' v-show="showTodayIcon" src='/static/images/today.png'></image>
			</view>
		</view>
		<view class='calendar' @touchmove="handletouchmove" @touchstart="handletouchstart" @touchend="handletouchend">
			<view class='row day-of-week'>
				<view class='day weekend'>日</view>
				<view class='day'>一</view>
				<view class='day'>二</view>
				<view class='day'>三</view>
				<view class='day'>四</view>
				<view class='day'>五</view>
				<view class='day weekend'>六</view>
			</view>
			<view class='row'>
				<view v-for="(item,index) in result.dates" :key="index" class='day' :class="{selected: item.date == selectedDate}"
					@click='onDateClick(item)'>
					<view class="solar-day" :class='solarDayClass(item)'>{{item.day}}</view>
					<view class="lunar-day" :class='lunarDayClass(item)'>{{getDateDesc(item)}}</view>
				</view>
			</view>
		</view>
		<view class='day-detail-section'>
			<view class='dds-main'>
				<view class='dds-day'>{{selectedItem.month+1}}月{{selectedItem.day}}日</view>
				<view class='dds-daydesc'>
					<view class='dds-weekday'>{{selectedItem.weekday}}</view>
					<view class='dds-lunar'>{{selectedItem.lunarYear}} {{selectedItem.lunar}}</view>
				</view>
				<image class='dds-animalsyear' src='/static/images/tu.png'></image>
			</view>
			<view class='dds-suit'>
				<view class='dds-suit-word'>宜</view>
				<view class='dds-suit-content' v-if="selectedItem.suit">{{selectedItem.suit}}</view>
				<view class='dds-suit-content' v-if="!selectedItem.suit">百无禁忌</view>
			</view>
			<view class='dds-avoid'>
				<view class='dds-avoid-word'>忌</view>
				<view class='dds-avoid-content' v-if="selectedItem.avoid">{{selectedItem.avoid}}</view>
				<view class='dds-suit-content' v-if="!selectedItem.avoid">百无禁忌</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		loadMonthData,
		setDairyVersion
	} from "../../utils/dairy.js";
	import {
		dateFormat
	} from "../../utils/util.js";
	export default {
		data() {
			return {
				year: null,
				month: null,
				result: {},
				selectedDate: null,
				flag: 0,
				text: '',
				lastX: 0,
				lastY: 0,
				MIN_YEAR: 2020,
				MIN_MONTH: 12,
				MAX_YEAR: 2022,
				MAX_MONTH: 12,
				showLoading: true
			}
		},
		computed: {
			selectedItem() {
				if(this.selectedDate) {
					for(let index in this.result.dates) {
						let item = this.result.dates[index];
						if(this.selectedDate == item.date) {
							console.log("selectedItem", item);
							return item;
						}
					}
				}
				return {};
			},
			showTodayIcon() {
				let today = new Date();
				let todayStr = dateFormat(today, 'yyyy-MM-dd');
				return todayStr != this.selectedDate;
			}
		},
		onLoad() {
			let that = this;
			this.showLoading = true;
			uniCloud.callFunction({
				name: 'get-dairy-config',
				success(res) {
					const configData = res.result;
					console.log('get-dairy-config', configData.result);
					that.MIN_YEAR = configData.dairy_data_min_year;
					that.MIN_MONTH = configData.dairy_data_min_month;
					that.MAX_YEAR = configData.dairy_data_max_year;
					that.MAX_MONTH = configData.dairy_data_max_month;
					setDairyVersion(configData.dairy_data_version);
				},
				complete() {
					let today = new Date();
					that.loadPage(today);
				}
			})
		},
		methods: {
			async loadPage(date) {
				if(!date) {
					return;
				}
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				if(year > this.MAX_YEAR || (year == this.MAX_YEAR && month>this.MAX_MONTH)) {
					return;
				}
				if(year < this.MIN_YEAR || (year == this.MIN_YEAR && month<this.MIN_MONTH)) {
					return;
				}
				this.result = await loadMonthData(year, month);
				this.year = year;
				this.month = month;
				this.selectedDate = dateFormat(date, 'yyyy-MM-dd');
				this.showLoading = false;
			},
			
			// 农历字段的描述
			getDateDesc(date) {
				let result = null;
				if (date.holiday) {
					result = date.holiday;
				} else {
					result = date.lunarDesc;
				}
				if(result && result.length > 3) {
					result = result.substring(0, 3);
					result += "...";
				}
				return result;
			},

			// 农历字段的样式
			lunarDayClass(item) {
				let isWeekEnd = item.dayOfWeek == 6 || item.dayOfWeek == 0;
				let inThisMonth = (item.month + 1) == this.month;
				return {
					'luna-day-this-month': !item.holiday && inThisMonth,
					'lunar-day-other-month': !item.holiday && !inThisMonth,
					'luna-day-this-month-holiday': item.holiday && inThisMonth,
					'luna-day-other-month-holiday': item.holiday && !inThisMonth
				};
			},

			solarDayClass(item) {
				let isWeekEnd = item.dayOfWeek == 6 || item.dayOfWeek == 0;
				let inThisMonth = (item.month + 1) == this.month;
				return {
					'solar-day': true,
					'weekday': !isWeekEnd && inThisMonth,
					'weekend': isWeekEnd && inThisMonth,
					'weekday-other-month': !isWeekEnd && !inThisMonth,
					'weekend-other-month': isWeekEnd && !inThisMonth
				};
			},

			handletouchmove: function(event) {
				if (this.flag !== 0) {
					return;
				}
				let currentX = event.touches[0].pageX;
				let currentY = event.touches[0].pageY;
				let tx = currentX - this.lastX;
				let ty = currentY - this.lastY;
				let text = '';
				this.mindex = -1;
				//左右方向滑动
				if (Math.abs(tx) > Math.abs(ty)) {
					if (tx < 0) {
						text = '向左滑动';
						this.flag = 1;
						this.nextMonth();
					} else if (tx > 0) {
						text = '向右滑动';
						this.flag = 2;
						this.lastMonth();
					}
				}
				//上下方向滑动
				else {
					// if (ty < 0) {
					// 	text = '向上滑动';
					// 	this.flag = 3;
					// 	this.nextMonth();
					// } else if (ty > 0) {
					// 	text = '向下滑动';
					// 	this.flag = 4;
					// 	this.lastMonth();
					// }
				}

				//将当前坐标进行保存以进行下一次计算
				this.lastX = currentX;
				this.lastY = currentY;
				this.text = text;
			},
			handletouchstart: function(event) {
				// console.log(event)
				this.lastX = event.touches[0].pageX;
				this.lastY = event.touches[0].pageY;
			},
			handletouchend: function(event) {
				this.flag = 0;
				this.text = '没有滑动';
			},
			
			lastMonth() {
				let selectDate = this.initSelectedDate(this.year, this.month - 1);
				this.loadPage(selectDate);
			},
			
			nextMonth() {
				let selectDate = this.initSelectedDate(this.year, this.month + 1);
				this.loadPage(selectDate);
			},
			
			initSelectedDate(year, month) {
				if(month > 12) {
					month = 1;
					year++;
				} else if(month < 1) {
					month = 12;
					year--;
				}
				let date = new Date(year, month-1, 1);
				let today = new Date();
				if(today.getFullYear() == date.getFullYear() 
					&& (today.getMonth() == date.getMonth())) {
					return today;
				} else {
					return date;
				}
			},
			
			onDateClick(item) {
				this.selectedDate = item.date;
			},
			
			onTodayIconClick() {
				this.loadPage(new Date());
			}
		}
	}
</script>

<style>
	page {
		width: 100%;
		height: 100%;
	}
	
	.page {
		width: 100%;
		height: 100%;
		/* background: url(@/static/images/bg1.jpeg) no-repeat top center; */
		background-image: url(@/static/images/bg1.jpeg);
		background-repeat:no-repeat;
		background-position: left top;
		background-size: 100% 100%;
	}
	
	.title-area {
		padding-top:  calc(var(--status-bar-height) + 30rpx);
		width: 100%;
		padding-bottom: 30rpx;
		display:flex;
		flex-direction: row;
		justify-content:space-between;
		align-items: center;
	}
	
	.year-month {
		color: #ffffff;
		font-weight: bolder;
		font-size: 32rpx;
	}
	
	.today-icon {
		width: 50rpx;
		height: 50rpx;
		margin-right: 30rpx;
	}
	
	.today-icon-image {
		width: 100%;
		height: 100%;
	}

	.calendar {
		background-color: white;
		padding-top: 20rpx;
		padding-left: 20rpx;
		padding-right: 20rpx;
		/* border-radius: 20rpx; */
		/* margin-left: 20rpx;
		margin-right: 20rpx; */
	}
	
	.row {
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		flex-wrap: wrap;
		text-align: center;
	}

	.day-of-week {
		font-size: 21rpx;
	}

	.solar-day {
		font-size: 35rpx;
		font-weight: bold;
	}

	.lunar-day {
		font-size: 20rpx;
	}

	.luna-day-this-month {
		color: #a1a1a1;
	}

	.luna-day-this-month-holiday {
		color: #b74854;
	}

	.luna-day-other-month-holiday {
		color: #e9dde1;
	}

	.lunar-day-other-month {
		color: #e1e1e1;
	}

	.weekday {
		color: #111111;
	}

	.weekday-other-month {
		color: #dfdfdf;
	}

	.weekend {
		color: #c1252d;
	}

	.weekend-other-month {
		color: #f1dcde;
	}
	
	.day {
		width: 13%;
		padding-bottom: 10rpx;
		margin-bottom: 10rpx;
		border: 5rpx solid #ffffff;
	}
	
	.selected {
		border: 5rpx solid #bf5445;
		border-radius: 20rpx;
	}
	
	.day-detail-section {
		border-radius: 20rpx;
		margin-top: 20rpx;
		margin-left: 20rpx;
		margin-right: 20rpx;
		padding-top: 40rpx;
		padding-left: 100rpx;
		padding-right: 50rpx;
		/* padding-bottom: 20rpx; */
		background-image: url(@/static/images/detail4.png);
		background-repeat:no-repeat;
		background-position: left top;
		background-size: 100% 100%;
		height: 620rpx;
	}
	
	.dds-daydesc {
		margin-left: 20rpx;
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: flex-start;
	}
	
	.dds-main {
		display:flex;
		flex-direction: row;
		align-items: center;
	}
	
	.dds-day {
		font-size: 60rpx;
		color: #d8272a;
	}
	
	.dds-weekday {
		font-size: 25rpx;
		color: #181818;
		font-weight: bold;
	}
	
	.dds-lunar {
		font-size: 25rpx;
		color: #181818;
		font-weight: bold;
		margin-top: 5rpx;
	}
	
	.dds-animalsyear {
		margin-right: 20rpx;
		margin-left: auto;
		width: 100rpx;
		height: 70rpx;
	}
	
	.dds-suit {
		margin-top: 20rpx;
		display: flex;
		flex-direction: row;
		align-items: flex-start;
		justify-content: flex-start;
		align-content: flex-start;
		align-items: flex-start;
	}
	
	.dds-avoid {
		margin-top: 20rpx;
		display: flex;
		flex-direction: row;
	}
	
	.dds-suit-word {
		color: #80afcc;
		font-size: 40rpx;
		margin-right: 20rpx;
	}
	
	.dds-avoid-word {
		color: #db8399;
		font-size: 40rpx;
		margin-right: 20rpx;
	}
	
	.dds-suit-content {
		margin-top: 10rpx;
		line-height: 50rpx;
		font-size: 25rpx;
	}
	
	.dds-avoid-content {
		margin-top: 10rpx;
		line-height: 50rpx;
		font-size: 25rpx;
	}
</style>
