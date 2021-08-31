<template>
	<view class='page'>
		<view class='year-month-title'>
			<view class='year-month'>{{year}}年{{month}}月</view>
			<view class='lunar-year'>{{result.lunarYear}}</view>
			<view class='animals-year'>{{result.animalsYear}}</view>
		</view>
		<view class='calendar'>
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
				<view v-for="(item,index) in result.dates" :key="index" class='day'>
					<view class="solar-day" :class='solarDayClass(item)'>{{item.day}}</view>
					<view class="lunar-day" :class='lunarDayClass(item)'>{{getDateDesc(item)}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		getMonthDays
	} from "../../utils/dairy.js"
	export default {
		data() {
			return {
				year: null,
				month: null,
				result: {}
			}
		},
		computed: {},
		onLoad() {
			let today = new Date();
			this.year = today.getFullYear();
			this.month = today.getMonth() + 1;
			this.loadPage();
		},
		methods: {
			loadPage() {
				// uni.setNavigationBarTitle({
				//     title: '' + this.year + '年' + this.month + '月'
				// });
				let that = this;
				uniCloud.callFunction({
						name: 'query-month-dairy',
						data: {
							"year": this.year,
							"month": this.month
						}
					})
					.then(res => {
						that.result = res.result;
						console.log(that.result);
					});
			},

			// 农历字段的描述
			getDateDesc(date) {
				let result = null;
				if (date.holiday) {
					result = date.holiday;
				} else {
					result = date.lunarDesc;
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
			}
		}
	}
</script>

<style>
	.year-month-title {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		margin-left: 20rpx;
		margin-right: 20rpx;
		margin-bottom: 30rpx;
	}

	.year-month {
		font-size: 40rpx;
	}

	.lunar-year {
		font-size: 40rpx;
	}

	.animals-year {
		font-size: 40rpx;
	}

	.calendar {
		width: 100%;
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
		font-size: 20rpx;
	}

	.solar-day {
		font-size: 32rpx;
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
		width: 14%;
		margin-bottom: 20rpx;
	}
</style>
