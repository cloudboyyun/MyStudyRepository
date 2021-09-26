<template>
	<view class='main' :style="{backgroundImage: backgroundImage}">
		<xy-loading v-if="showFullLoading" :opacity='1' brandText="...豆云日历..." loadingText="...Loading..."
			color="#FF9000" marginTop="50vh"></xy-loading>
		<xy-loading v-if="showLoading" :opacity='0.7' brandText="...豆云日历..." loadingText="...Loading..." color="#FF9000"
			marginTop="50vh"></xy-loading>
		<view class='title-area'>
			<view class='year-month'>
				{{year}}年{{month}}月
				<view class='today-icon' @click="onTodayIconClick()">
					<image class='today-icon-image' v-show="showTodayIcon" src='/static/images/today.png'></image>
				</view>
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
							<view class="solar-day"
								:class="[item.dayOfWeek == 6 || item.dayOfWeek == 0?'weekend':'weekday']">
								{{item.day}}
							</view>
							<view class="lunar-day" :class="[item.holiday? 'lunar-day-holiday':'lunar-day-common']"
								@longpress="onDateDescPress(item, $event)">{{getDateDesc(item)}}</view>
						</view>
					</view>
					<image src='/static/images/flyingBird.gif' class='flying-bird'></image>
				</view>
			</swiper-item>
		</swiper>

		<view class='day-detail-section' @touchmove="handletouchmove" @touchstart="handletouchstart"
			@touchend="handletouchend">
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
				<xy-bubble class='dds-animalsyear' radius="120rpx" :image="selectedItem.animalImage" fontSize="100rpx"
							fontFamily="sunzhongshan" fontColor="red"></xy-bubble>
				<!-- <image class='dds-animalsyear' :src='selectedItem.animalImage' @longpress='onClearStorageClick'></image> -->
			</view>
			<view v-if="selectedItem.holidays.length" class='h-holidays'>
				<view v-for="(item,index) in selectedItem.holidays" :key="index" class='h-holiday'>
					<image class='h-holiday-icon' src="/static/images/holiday.png"></image>
					<view class='h-holiday-txt'>{{item}}</view>
				</view>
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
	const BGIMAGES = new Map([
		['春',
			"url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/e0d66a81-e6d7-4e24-bbc7-6036c1145b26.jpeg')"
		],
		['夏',
			"url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/9403b9d2-9b48-43a7-a335-bb19fa108b29.jfif')"
		],
		['秋',
			"url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/376b4b20-61b5-48e0-bf7b-7898e6fe2144.jfif')"
		],
		['冬',
			"url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/30a542f2-e973-473f-b2de-fd60b4860e40.jfif')"
		]
	]);
	import {
		loadMonthData,
		setDairyVersion,
		loadYearGanzhi
	} from "../../utils/dairy.js";
	import {
		dateFormat,
		monthIncrease,
		monthDecrease
	} from "../../utils/util.js";
	let lastSelectedItem = null;
	export default {
		data() {
			return {
				year: null,
				month: null,
				day: null,
				resultGroup: [{}, {}, {}],
				selectedSwiperIndex: 1,
				selectedDate: null,
				todayStr: null,
				MIN_YEAR: 2020,
				MIN_MONTH: 12,
				MAX_YEAR: 2022,
				MAX_MONTH: 12,
				showLoading: false,
				showFullLoading: true,
				flag: 0,
				text: '',
				lastX: 0,
				lastY: 0,
				yearGanzhi: null,
			}
		},
		computed: {
			selectedItem() {
				if (this.selectedDate) {
					for (let index in this.resultGroup[this.selectedSwiperIndex].dates) {
						let item = this.resultGroup[this.selectedSwiperIndex].dates[index];
						if (this.selectedDate == item.date) {
							item.animalImage = ANIMALS.get(item.animal);
							lastSelectedItem = item;
							return item;
						}
					}
				}
				if (lastSelectedItem) {
					return lastSelectedItem;
				}
				return {
					'weekday': '',
					'lunar': '',
					'gzYear': '',
					'gzMonth': '',
					'gzDate': '',
					'animalImage': null,
					'suit': '',
					'avoid': '',
					'holidays': []
				};
			},
			showTodayIcon() {
				return this.todayStr != this.selectedDate;
			},
			backgroundImage() {
				let result = BGIMAGES.get("春");
				if (this.selectedDate && this.yearGanzhi) {
					if (this.selectedDate < this.yearGanzhi.get('立春')) {
						result = BGIMAGES.get("冬");
					} else if (this.selectedDate >= this.yearGanzhi.get('立春') &&
						this.selectedDate < this.yearGanzhi.get('立夏')) {
						result = BGIMAGES.get("春");
					} else if (this.selectedDate >= this.yearGanzhi.get('立夏') &&
						this.selectedDate < this.yearGanzhi.get('立秋')) {
						result = BGIMAGES.get("夏");
					} else if (this.selectedDate >= this.yearGanzhi.get('立秋') &&
						this.selectedDate < this.yearGanzhi.get('立冬')) {
						result = BGIMAGES.get("秋");
					} else if (this.selectedDate >= this.yearGanzhi.get('立冬')) {
						result = BGIMAGES.get("冬");
					}
				}
				return result;
			}
		},
		onLoad() {
			this._onLoad();
		},
		methods: {
			async _onLoad() {
				let that = this;
				let today = new Date();
				this.todayStr = dateFormat(today, 'yyyy-MM-dd');
				this.showFullLoading = true;
				let configDataResult = await uniCloud.callFunction({
					name: 'xy-calendar',
					data: {
						action: 'getDairyConfig',
						params: {}
					}					
				})
				const configData = configDataResult.result;
				that.MIN_YEAR = configData.dairy_data_min_year;
				that.MIN_MONTH = configData.dairy_data_min_month;
				that.MAX_YEAR = configData.dairy_data_max_year;
				that.MAX_MONTH = configData.dairy_data_max_month;
				setDairyVersion(configData.dairy_data_version);
				await that.loadPage(today);
				this.loginByWeixin();
			},

			async loginByWeixin() {
				// #ifdef MP-WEIXIN
				let code = await this.getWeixinCode();
				let loginByWeixinResult = await uniCloud.callFunction({
					name: 'uni-id-func',
					data: {
						action: 'loginByWeixin',
						params: {
							code: code,
							role: ['calendar-miniprogram-user']
						}
					}
				});
				uni.setStorageSync('uni_id_token', loginByWeixinResult.result.token)
				uni.setStorageSync('uni_id_token_expired', loginByWeixinResult.result.tokenExpired);
				// #endif
			},
			getWeixinCode() {
				return new Promise((resolve, reject) => {
					// #ifdef MP-WEIXIN
					uni.login({
						provider: 'weixin',
						success(res) {
							resolve(res.code)
						},
						fail(err) {
							reject(new Error('微信登录失败'))
						}
					})
					// #endif
				})
			},
			async loadPage(date) {
				if (!date) {
					return;
				}
				this.year = date.getFullYear();
				this.month = date.getMonth() + 1;
				this.day = date.getDate();
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
				this.resultGroup[currentSwiperIndex] = await loadMonthData(this.year, this.month);
				this.selectedDate = dateFormat(date, 'yyyy-MM-dd');
				let leftMonth = monthDecrease(this.year, this.month);
				if (leftMonth.year < this.MIN_YEAR ||
					(leftMonth.year == this.MIN_YEAR && leftMonth.month < this.MIN_MONTH)) {
					leftMonth.year = this.MAX_YEAR;
					leftMonth.month = this.MAX_MONTH;
				}
				let rightMonth = monthIncrease(this.year, this.month);
				if (rightMonth.year > this.MAX_YEAR ||
					(rightMonth.year == this.MAX_YEAR && rightMonth.month > this.MAX_MONTH)) {
					rightMonth.year = this.MIN_YEAR;
					rightMonth.month = this.MIN_MONTH;
				}
				this.resultGroup[leftSwiperIndex] = await loadMonthData(leftMonth.year, leftMonth.month);
				this.resultGroup[rightSwiperIndex] = await loadMonthData(rightMonth.year, rightMonth.month);
				this.yearGanzhi = await loadYearGanzhi(this.year);
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

			onDateClick(item) {
				this.selectedDate = item.date;
				let date = new Date(item.date);
				this.year = date.getFullYear();
				this.month = date.getMonth() + 1;
				this.day = date.getDate();
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
						this.nextDay();
					} else if (tx > 0) {
						text = '向右滑动';
						this.flag = 2;
						this.lastDay();
					}
				}
				//上下方向滑动
				else {
					if (ty < 0) {
						text = '向上滑动';
						this.flag = 3;
					} else if (ty > 0) {
						text = '向下滑动';
						this.flag = 4;
					}
				}

				//将当前坐标进行保存以进行下一次计算
				this.lastX = currentX;
				this.lastY = currentY;
				this.text = text;
			},
			handletouchstart: function(event) {
				this.lastX = event.touches[0].pageX;
				this.lastY = event.touches[0].pageY;
			},
			handletouchend: function(event) {
				this.flag = 0;
				this.text = '没有滑动';
			},

			nextDay() {
				let selectedDate = new Date(this.selectedDate);
				let newDate = new Date(selectedDate.getTime() + 1000 * 60 * 60 * 24);
				this.loadPage(newDate);
			},
			lastDay() {
				let selectedDate = new Date(this.selectedDate);
				let newDate = new Date(selectedDate.getTime() - 1000 * 60 * 60 * 24);
				this.loadPage(newDate);
			}
		}
	}
</script>

<style>
	page {
		width: 100%;
		height: 100%;
	}

	.main {
		width: 100%;
		height: 100%;
		background-repeat: no-repeat;
		background-position: left top;
		background-size: 100% 100%;
	}

	.title-area {
		padding-top: calc(var(--status-bar-height) + 60rpx);
		width: 100%;
		padding-bottom: 20rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}

	.year-month {
		color: #ffffff;
		font-weight: bolder;
		font-size: 32rpx;
		position: relative;
		display: flex;
		flex-direction: row;
		line-height: 50rpx;
	}

	.today-icon {
		position: absolute;
		width: 50rpx;
		height: 50rpx;
		left: -60rpx;
		top: 0rpx
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
		background-color: #d7f3f9;
		background-image: url("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/1cb2ad77-ac70-4974-9e9b-786bc7fe1f1c.png");
		animation: kf-cloud 50s linear infinite;
		transform: translate3d(0, 0, 0);
		padding-top: 20rpx;
		padding-left: 20rpx;
		padding-right: 20rpx;
		position: relative;
	}
	
	@keyframes kf-cloud {
		50% {
			background-position: 100% 0
		}
	}
	
	.flying-bird {
		width: 100%;
		height: 30%;
		position: absolute;
		bottom: 0;
		left: 0;
		transform: rotateY(180deg);
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
		/* border: 5rpx solid #ffffff; */
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
		background-image: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/9d3b1745-1914-44e8-bab2-d875757833d4.png');
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
		position: relative;
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
		font-size: 25rpx;
		color: #181818;
	}

	.dds-animalsyear {
		margin-right: 20rpx;
		margin-left: auto;
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

	.h-holidays {
		display: flex;
		flex-direction: row;
		border: 1rpx solid #F0AD4E;
		border-radius: 10rpx;
		margin-top: 20rpx;
		padding-top: 10rpx;
		padding-bottom: 10rpx;
		margin-right: 10rpx;
	}

	.h-holiday {
		margin-left: 10rpx;
		display: flex;
		flex-direction: row;
		background-color: #bf9548;
		border-radius: 15rpx;
		height: 40rpx;
		align-items: center;
	}

	.h-holiday-icon {
		margin-left: 10rpx;
		width: 25rpx;
		height: 25rpx;
	}

	.h-holiday-txt {
		margin-left: 10rpx;
		margin-right: 10rpx;
		font-size: 25rpx;
		line-height: 25rpx;
		color: #FFFFFF;
	}
</style>
