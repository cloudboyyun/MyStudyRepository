<template>
	<view class='page'>
		<view class='title-area'>
			<view class='year-month'>{{year}}年{{month}}月</view>
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
					@click='changeSelectedDate(item)'>
					<view class="solar-day" :class='solarDayClass(item)'>{{item.day}}</view>
					<view class="lunar-day" :class='lunarDayClass(item)'>{{getDateDesc(item)}}</view>
				</view>
			</view>
		</view>
		<view class='day-detail-section'>
			<view class='dds-main'>
				<view class='dds-day'>{{selectedItem.month}}月{{selectedItem.day}}日</view>
				<view class='dds-daydesc'>
					<view class='dds-weekday'>{{selectedItem.weekday}}</view>
					<view class='dds-lunar'>{{selectedItem.lunarYear}} {{selectedItem.lunar}}</view>
				</view>
				<image class='dds-animalsyear' src='/static/images/tu.png'></image>
			</view>
			<view class='dds-suit'>
				<view class='dds-suit-word'>宜</view>
				<view class='dds-suit-content'>{{selectedItem.suit}}</view>
			</view>
			<view class='dds-avoid'>
				<view class='dds-avoid-word'>忌:</view>
				<view class='dds-avoid-content'>{{selectedItem.avoid}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		loadMonthData
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
				lastY: 0
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
			}
		},
		onLoad() {
			let today = new Date();
			this.year = today.getFullYear();
			this.month = today.getMonth() + 1;
			this.initSelectedDate();
			this.loadPage();
		},
		methods: {
			async loadPage() {
				this.result = await loadMonthData(this.year, this.month);
				console.log("result", this.result);
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
					if (ty < 0) {
						text = '向上滑动';
						this.flag = 3;
						this.nextMonth();
					} else if (ty > 0) {
						text = '向下滑动';
						this.flag = 4;
						this.lastMonth();
					}
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
				console.log("lastMonth");
				let month = this.month - 1;
				if(month < 1) {
					this.month = 12;
					this.year = this.year - 1;
				} else {
					this.month = month;
				}
				this.initSelectedDate();
				this.loadPage();
			},
			
			nextMonth() {
				console.log("nextMonth");
				let month = this.month + 1;
				if(month > 12) {
					this.month = 1;
					this.year = this.year + 1;
				} else {
					this.month = month;
				}
				this.initSelectedDate();
				this.loadPage();
			},
			
			initSelectedDate() {
				let date = new Date(this.year, this.month-1, 1);
				let today = new Date();
				let year = today.getFullYear();
				let month = today.getMonth();
				if(year == date.getFullYear() && (month == date.getMonth())) {
					this.selectedDate = dateFormat(today, 'yyyy-MM-dd');
				} else {
					this.selectedDate = dateFormat(date, 'yyyy-MM-dd');
				}
			},
			
			changeSelectedDate(item) {
				this.selectedDate = item.date;
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
		padding-bottom: 50rpx;
		display:flex;
		flex-direction: row;
		justify-content:center;
	}
	
	.year-month {
		color: #ffffff;
		font-weight: bolder;
		font-size: 32rpx;
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
		/* background-color: #cfac7f; */
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
		font-size: 67rpx;
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
		line-height: 50rpx;
	}
	
	.dds-avoid-content {
		line-height: 50rpx;
	}
</style>
