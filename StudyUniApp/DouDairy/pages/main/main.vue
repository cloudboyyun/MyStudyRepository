<template>
	<view class='page'>
		<view v-if="showLoading" class='loading'>
		  <image class='loading-image' src='/static/images/loading.gif'></image>
		</view>
		<view class='title-area'>
			<view class='today-icon'></view>
			<view class='year-month'>{{year}}年{{month}}月</view>
			<view class='today-icon' @click="onTodayIconClick()">
				<image class='today-icon-image' v-show="showTodayIcon" src='/static/images/today.png'></image>
			</view>
		</view>
		<swiper class='swiper' :current="selectedSwiperIndex" circular="true" @change="onSwiperItemChange">
			<swiper-item v-for="(result, i) in resultGroup" :key="i">
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
						<view v-for="(item,index) in result.dates" :key="index" class='day' :class="{selected: item.date == selectedDate}"
							@click='onDateClick(item)'>
							<view class="solar-day" :class='solarDayClass(item)'>{{item.day}}</view>
							<view class="lunar-day" :class='lunarDayClass(item)' 
								@longpress="onDateDescPress(item, $event)">{{getDateDesc(item)}}</view>
						</view>
					</view>
				</view>
			</swiper-item>
		</swiper>
		
		<view class='day-detail-section'>
			<view class='dds-main'>
				<view class='dds-day'>{{selectedItem.month}}月{{selectedItem.day}}日</view>
				<view class='dds-daydesc'>
					<view class='dds-weekday'>星期{{selectedItem.weekday}}</view>
					<view class='dds-lunar'>{{selectedItem.lunarYear}}年 {{selectedItem.lunar}}</view>
				</view>
				<image class='dds-animalsyear' :src='selectedItem.animalImage'></image>
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
	const ANIMALS = new Map([
		['狗', '/static/images/gou.png'],
		['猴', '/static/images/hou.png'],
		['虎', '/static/images/hu.png'],
		['鸡', '/static/images/ji.png'],
		['马', '/static/images/ma.png'],
		['牛', '/static/images/niu.png'],
		['蛇', '/static/images/she.png'],
		['鼠', '/static/images/shu.png'],
		['兔', '/static/images/tu.png'],
		['羊', '/static/images/yang.png'],
		['猪', '/static/images/zhu.png']
	]);
	import {
		loadMonthData,
		setDairyVersion
	} from "../../utils/dairy.js";
	import {
		dateFormat,
		monthIncrease,
		monthDecrease
	} from "../../utils/util.js";
	export default {
		data() {
			return {
				year: null,
				month: null,
				resultGroup: [{}, {}, {}],
				selectedSwiperIndex: 1,
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
					for(let index in this.resultGroup[this.selectedSwiperIndex].dates) {
						let item = this.resultGroup[this.selectedSwiperIndex].dates[index];
						if(this.selectedDate == item.date) {
							console.log("selectedItem", item);
							item.animalImage = ANIMALS.get(item.animalsYear);
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
				let currentSwiperIndex = this.selectedSwiperIndex;
				let leftSwiperIndex = 0;
				let rightSwiperIndex = 2;
				switch(currentSwiperIndex) {
					case 0:
						leftSwiperIndex = 2;
						rightSwiperIndex = 1;
						break;						
					case 2:
						leftSwiperIndex = 1;
						rightSwiperIndex = 0;
						break;
				}
				this.resultGroup[currentSwiperIndex] = await loadMonthData(year, month);
				this.year = year;
				this.month = month;
				this.selectedDate = dateFormat(date, 'yyyy-MM-dd');
				let leftMonth = monthDecrease(year, month);
				if(leftMonth.year < this.MIN_YEAR || 
					(leftMonth.year == this.MIN_YEAR && leftMonth.month<this.MIN_MONTH)) {
					leftMonth.year = this.MAX_YEAR;
					leftMonth.month = this.MAX_MONTH;
				}
				let rightMonth = monthIncrease(year, month);
				if(rightMonth.year > this.MAX_YEAR || 
					(rightMonth.year == this.MAX_YEAR && rightMonth.month>this.MAX_MONTH)) {
					rightMonth.year = this.MIN_YEAR;
					rightMonth.month = this.MIN_MONTH;
				}
				this.resultGroup[leftSwiperIndex] = await loadMonthData(leftMonth.year, leftMonth.month);
				this.resultGroup[rightSwiperIndex] = await loadMonthData(rightMonth.year, rightMonth.month);
				this.showLoading = false;
			},
			
			onSwiperItemChange(e) {
				this.selectedSwiperIndex = e.detail.current;
				let monthData = this.resultGroup[this.selectedSwiperIndex];
				let year = monthData.year;
				let month = monthData.month;
				let date = new Date(year, month-1, 1);
				let today = new Date();
				if(today.getFullYear() == date.getFullYear() 
					&& (today.getMonth() == date.getMonth())) {
					this.loadPage(today);
				} else {
					this.loadPage(date);
				}
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
				let inThisMonth = (item.month == this.month);
				return {
					'solar-day': true,
					'weekday': !isWeekEnd && inThisMonth,
					'weekend': isWeekEnd && inThisMonth,
					'weekday-other-month': !isWeekEnd && !inThisMonth,
					'weekend-other-month': isWeekEnd && !inThisMonth
				};
			},
			
			onDateClick(item) {
				this.selectedDate = item.date;
			},
			
			onTodayIconClick() {
				this.loadPage(new Date());
			},
			
			onDateDescPress(date, e) {
				console.log("onDateDescPress", e);
				let result = null;
				if (date.holiday) {
					result = date.holiday;
				} else {
					result = date.lunarDesc;
				}
				if(!result || result.length <= 3) {
					return;
				}
				uni.showToast({
					title:result,
					position: 'top'
				})
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
	
	.swiper {
		height: 720rpx;
	}

	.calendar {
		height: 720rpx;
		background-color: white;
		padding-top: 20rpx;
		padding-left: 20rpx;
		padding-right: 20rpx;
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
		margin-left: 30rpx;
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
		font-size: 55rpx;
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
