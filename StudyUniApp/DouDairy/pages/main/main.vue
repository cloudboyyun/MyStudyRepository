<template>
	<view class='page'>
		<view v-if="showFullLoading" class='full-loading'>
			<image class='loading-image' src='/static/images/loading.gif'></image>
		</view>
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
						<view v-for="(item,index) in result.dates" :key="index" class='day' :class="{
								selected: (item.date == selectedDate),
								today: (item.date == todayStr),
								mask: (item.month != month),
								workingDay: (item.status == '2'),
								restingDay: (item.status == '1')
							}" @click='onDateClick(item)'>
							<view class="solar-day" :class='solarDayClass(item)'>
								{{item.day}}
							</view>
							<view class="lunar-day" :class="[item.holiday? 'lunar-day-holiday':'lunar-day-common']"
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
					<view class='dds-lunar'>
						<image src='/static/images/lunar.png' class='dds-lunar-png'></image>
						<view class='dds-lunar-value'>{{selectedItem.lunar}}</view>
					</view>
					<view class='dds-ganzhi'>{{selectedItem.gzYear}}年 {{selectedItem.gzMonth}}月 {{selectedItem.gzDate}}日
					</view>
				</view>
				<image class='dds-animalsyear' :src='selectedItem.animalImage' @longpress='onClearStorageClick'></image>
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
				todayStr: null,
				flag: 0,
				text: '',
				lastX: 0,
				lastY: 0,
				MIN_YEAR: 2020,
				MIN_MONTH: 12,
				MAX_YEAR: 2022,
				MAX_MONTH: 12,
				showLoading: false,
				showFullLoading: true
			}
		},
		computed: {
			selectedItem() {
				if (this.selectedDate) {
					for (let index in this.resultGroup[this.selectedSwiperIndex].dates) {
						let item = this.resultGroup[this.selectedSwiperIndex].dates[index];
						if (this.selectedDate == item.date) {
							console.log("selectedItem", item);
							item.animalImage = ANIMALS.get(item.animal);
							return item;
						}
					}
				}
				return {};
			},
			showTodayIcon() {
				return this.todayStr != this.selectedDate;
			}
		},
		onLoad() {
			let that = this;
			let today = new Date();
			this.todayStr = dateFormat(today, 'yyyy-MM-dd');
			this.showFullLoading = true;
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
					that.loadPage(today);
				}
			})
		},
		methods: {
			async loadPage(date) {
				if (!date) {
					return;
				}
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let currentSwiperIndex = this.selectedSwiperIndex;
				let leftSwiperIndex = 0;
				let rightSwiperIndex = 2;
				switch (currentSwiperIndex) {
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
				if (leftMonth.year < this.MIN_YEAR ||
					(leftMonth.year == this.MIN_YEAR && leftMonth.month < this.MIN_MONTH)) {
					leftMonth.year = this.MAX_YEAR;
					leftMonth.month = this.MAX_MONTH;
				}
				let rightMonth = monthIncrease(year, month);
				if (rightMonth.year > this.MAX_YEAR ||
					(rightMonth.year == this.MAX_YEAR && rightMonth.month > this.MAX_MONTH)) {
					rightMonth.year = this.MIN_YEAR;
					rightMonth.month = this.MIN_MONTH;
				}
				this.resultGroup[leftSwiperIndex] = await loadMonthData(leftMonth.year, leftMonth.month);
				this.resultGroup[rightSwiperIndex] = await loadMonthData(rightMonth.year, rightMonth.month);
				this.showLoading = false;
				this.showFullLoading = false;
			},

			onSwiperItemChange(e) {
				this.showLoading = true;
				this.selectedSwiperIndex = e.detail.current;
				let monthData = this.resultGroup[this.selectedSwiperIndex];
				let year = monthData.year;
				let month = monthData.month;
				let date = new Date(year, month - 1, 1);
				let today = new Date();
				if (today.getFullYear() == date.getFullYear() &&
					(today.getMonth() == date.getMonth())) {
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
				if (result && result.length > 3) {
					result = result.substring(0, 3);
					result += "...";
				}
				return result;
			},

			solarDayClass(item) {
				let isWeekEnd = item.dayOfWeek == 6 || item.dayOfWeek == 0;
				return {
					'weekday': !isWeekEnd,
					'weekend': isWeekEnd
				};
			},

			onDateClick(item) {
				this.selectedDate = item.date;
			},

			onTodayIconClick() {
				this.loadPage(new Date());
			},

			onDateDescPress(date, e) {
				let result = null;
				if (date.holiday) {
					result = date.holiday;
				} else {
					result = date.lunarDesc;
				}
				if (!result || result.length <= 3) {
					return;
				}
				uni.showToast({
					title: result,
					position: 'top'
				})
			},

			onClearStorageClick() {
				uni.clearStorageSync();
				uni.showToast({
					title: "缓存已清除",
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
		background-repeat: no-repeat;
		background-position: left top;
		background-size: 100% 100%;
	}

	.title-area {
		padding-top: calc(var(--status-bar-height) + 30rpx);
		width: 100%;
		padding-bottom: 30rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
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

	.lunar-day-common {
		color: #a1a1a1;
	}

	.lunar-day-holiday {
		color: #b74854;
	}

	.weekday {
		color: #111111;
	}

	.weekend {
		color: #c1252d;
	}

	.day {
		width: 13%;
		padding-bottom: 10rpx;
		margin-bottom: 10rpx;
		border: 5rpx solid #ffffff;
	}

	.selected {
		border: 3rpx solid #729e82;
		border-radius: 20rpx;
	}
	
	.today {
		border: 5rpx solid #bf5445;
		border-radius: 20rpx;
	}

	.mask {
		opacity: 0.3;
	}

	.workingDay {
		position: relative;
	}

	.workingDay:after {
		content: '班';
		font-size: 16rpx;
		line-height: 16rpx;
		width: 16rpx;
		height: 16rpx;
		padding: 5rpx;
		position: absolute;
		top: -5rpx;
		right: 0rpx;
		border-radius: 50%;
		background-color: #07C160;
		color: #ffffff;
	}

	.restingDay {
		position: relative;
	}
	
	.restingDay:after {
		content: '休';
		font-size: 16rpx;
		line-height: 16rpx;
		width: 16rpx;
		height: 16rpx;
		padding: 5rpx;
		position: absolute;
		top: -5rpx;
		right: 0rpx;
		border-radius: 50%;
		background-color: #c1252d;
		color: #ffffff;
	}

	.day-detail-section {
		border-radius: 20rpx;
		margin-top: 20rpx;
		margin-left: 5rpx;
		margin-right: 5rpx;
		padding-top: 40rpx;
		padding-left: 100rpx;
		padding-right: 20rpx;
		background-image: url(@/static/images/detail4.png);
		background-repeat: no-repeat;
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
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.dds-day {
		font-size: 55rpx;
		color: #d8272a;
	}

	.dds-weekday {
		display: flex;
		flex-direction: row;
		align-items: center;
		font-size: 25rpx;
		color: #181818;
		line-height: 25rpx;
	}

	.dds-lunar {
		margin-top: 2rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		text-align: center;
	}

	.dds-lunar-png {
		width: 20rpx;
		height: 20rpx;
	}

	.dds-lunar-value {
		margin-left: 10rpx;
		font-size: 32rpx;
		color: #181818;
		font-weight: bold;
	}

	.dds-ganzhi {
		/* margin-top: 5rpx; */
		font-size: 25rpx;
		color: #181818;
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
		margin-right: 20rpx;
		line-height: 50rpx;
		font-size: 25rpx;
	}

	.dds-avoid-content {
		margin-top: 10rpx;
		margin-right: 20rpx;
		line-height: 50rpx;
		font-size: 25rpx;
	}
</style>
