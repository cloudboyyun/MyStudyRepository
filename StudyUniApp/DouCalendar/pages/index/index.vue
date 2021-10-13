<template>
	<view class='main' :style="{backgroundImage: backgroundImage}" :class="{daylight: !isNight, night: isNight}">
		<xy-loading v-if="showFullLoading" :opacity='1' brandText="...豆云日历..." loadingText="...Loading..."
			color="#FF9000" marginTop="50vh"></xy-loading>
		<xy-loading v-if="showLoading" :opacity='0.7' brandText="...豆云日历..." loadingText="...Loading..." color="#FF9000"
			marginTop="50vh"></xy-loading>
		<view class='title-area'>
			<xy-cloud class="cloud-1" :class="{'cloud-fade': isNight}"></xy-cloud>
			<xy-cloud class="cloud-2" :class="{'cloud-fade': isNight}"></xy-cloud>
			<view class='app-name'>豆云日历</view>
			<view class='year-month'>
				{{year}}年{{month}}月
				<view class='today-icon' @click="onTodayIconClick()">
					<image class='today-icon-image' v-show="showTodayIcon" src='/static/images/today.png'></image>
				</view>
			</view>
			<view class='sun-container' :style="{transform: 'rotate(' + sunDeg + 'deg)'}">
				<view class='sun' :class="{'sun-fade': isNight}"></view>
			</view>
			<image src='/static/images/flyingBird.gif' class='flying-bird' :style="{opacity: isNight?0:1}"></image>
			<view class='stars' :class="{'stars-fade': isNight}">
				<view class="shooting-star" :class="{'shooting': isNight}"></view>
				<view class="star star-1"></view>
				<view class="star star-2"></view>
				<view class="star star-3"></view>
				<view class="star star-4"></view>
				<!-- <view class="star star-5"></view> -->
				<view class="star star-6"></view>
				<view class="star star-7"></view>
				<view class="star star-8"></view>
				<view class="star star-9"></view>
				<view class="star star-10"></view>
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
				</view>
			</swiper-item>
		</swiper>

		<view class='day-detail-section' @touchmove="handletouchmove" @touchstart="handletouchstart"
			@touchend="handletouchend" :style="{'filter': 'invert(' + (isNight?'80%':'0') + ')'}">
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
				<!-- <xy-bubble class='dds-animalsyear' radius="120rpx"
					:image="selectedItem.animalImage?selectedItem.animalImage:''" fontSize="100rpx"
					fontFamily="sunzhongshan" fontColor="red" @click.native='onSwitcherClick()'></xy-bubble> -->
				<view class='dds-animal-switcher'>
					<xy-bubble class='dds-animalsyear' radius="80rpx"
						:image="selectedItem.animalImage?selectedItem.animalImage:''" fontSize="100rpx"
						fontFamily="sunzhongshan" fontColor="red" @click.native='onSwitcherClick()'
						:class="{'switcher-fade': isNight}"></xy-bubble>
				</view>
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
	const innerAudioContext = uni.createInnerAudioContext();
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
				isNight: false,
				sunDeg: 0
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
			innerAudioContext.autoplay = false;
			innerAudioContext.loop = false;
			innerAudioContext.src = '/static/sounds/2.mp3';
		},
		methods: {
			onShareAppMessage: function(e) {
				let title = '豆云日历'
				return {
					title: '豆云日历',
					path: 'pages/index/index'
				}
			},
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
				console.log("onTodayIconClick");
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
			},

			onSwitcherClick() {
				if (this.switcherDisabled) {
					return;
				}
				innerAudioContext.play();
				this.switcherDisabled = true;
				this.isNight = !this.isNight;
				this.sunDeg += 360;
				let that = this;
				setTimeout(() => {
					that.switcherDisabled = false;
				}, 5000);
			}
		}
	}
</script>

<style lang="scss">
	@font-face {
		font-family: 'sunzhongshan';
		src: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/fa3b69e8-ffc9-46d3-9111-7cf472412576.ttf');
	}

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
		// overflow-x:hidden;
	}

	.title-area {
		padding-top: calc(var(--status-bar-height) + 60rpx);
		// padding-left: 20rpx;
		width: 100%;
		padding-bottom: 20rpx;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: flex-start;
		position: relative;
		transition: background 5s ease;
		transition-delay: 2s;
	}

	.stars {
		opacity: 0;
		transition: opacity 2s ease;
		position: absolute;
		left: 0;
		top: 0;
	}

	.shooting-star {
		position: absolute;
		background: linear-gradient(to right,
				rgba(255, 255, 255, 1) 0%,
				rgba(255, 255, 255, 0) 100%);
		width: 35rpx;
		height: 3rpx;
		left: 510rpx;
		top: 10rpx;
		transform-origin: left;
		transform: rotate(-40deg);
	}

	.shooting {
		animation: shooting-star 10s infinite ease-in-out;
		animation-delay: 5s;
	}

	@keyframes shooting-star {
		20% {
			transform: rotate(-40deg)translateX(-450rpx);
		}

		100% {
			transform: rotate(-40deg)translateX(-450rpx);
		}
	}

	.star {
		border-radius: 50%;
		background-color: #fff;
		position: relative;
		animation: star 3s infinite ease;
	}

	@keyframes star {
		50% {
			transform: scale(0.8);
		}

		100% {
			transform: scale(1.2);
		}
	}

	.star-1 {
		width: 5rpx;
		height: 5rpx;
		left: 100rpx;
		top: 150rpx;
		animation-delay: 5s;
	}

	.star-2 {
		width: 5rpx;
		height: 5rpx;
		left: 120rpx;
		top: 100rpx;
		animation-delay: 3s;
	}

	.star-3 {
		width: 4rpx;
		height: 4rpx;
		left: 170rpx;
		top: 120rpx;
		animation-delay: 7s;
	}

	.star-4 {
		width: 5rpx;
		height: 5rpx;
		left: 260rpx;
		top: 160rpx;
		animation-delay: 10s;
	}

	.star-5 {
		width: 5rpx;
		height: 5rpx;
		left: 300rpx;
		top: 200rpx;
		animation-delay: 5s;
	}

	.star-6 {
		width: 5rpx;
		height: 5rpx;
		left: 300rpx;
		top: 150rpx;
		animation-delay: 7s;
	}

	.star-7 {
		width: 3rpx;
		height: 3rpx;
		left: 360rpx;
		top: 170rpx;
		animation-delay: 6s;
	}

	.star-8 {
		width: 4rpx;
		height: 4rpx;
		left: 450rpx;
		top: 210rpx;
		animation-delay: 9s;
	}

	.star-9 {
		width: 3rpx;
		height: 3rpx;
		left: 490rpx;
		top: 150rpx;
		animation-delay: 4s;
	}

	.star-10 {
		width: 4rpx;
		height: 4rpx;
		left: 600rpx;
		top: 150rpx;
		animation-delay: 3s;
	}

	.stars-fade {
		opacity: 1;
		transition-delay: 4s;
	}

	.sun-container {
		position: absolute;
		left: 200rpx;
		top: 100rpx;
		width: 350rpx;
		height: 350rpx;
		transform-origin: center center;
		transition: all 5s ease-in-out;
	}

	.sun {
		position: relative;
		top: 0;
		left: 0;
		width: 30rpx;
		height: 30rpx;
		border-radius: 50%;
		opacity: 1;
		box-shadow: 0rpx 0rpx 35rpx 8rpx #F0F0F0;
		transition: background-color 5s ease-in-out;
	}

	.cloud-fade {
		opacity: 0;
	}

	.cloud-1 {
		position: absolute;
		left: 100rpx;
		top: 160rpx;

		animation: cloud1 8s infinite;
		animation-delay: 0.5s;
		transition: opacity 8s ease;
	}

	.cloud-2 {
		position: absolute;
		left: 40rpx;
		top: 140rpx;
		animation: cloud2 5s infinite;
		transition: opacity 8s ease;
	}

	@keyframes cloud1 {
		0% {
			transform: translate(0px, 0px) scale(0.8);
		}

		25% {
			transform: translate(1px, 1px) scale(0.8);
		}

		50% {
			transform: translate(0px, 0px) scale(0.8);
		}

		75% {
			transform: translate(-1px, -1px) scale(0.8);
		}

		100% {
			transform: translate(0px, 0px) scale(0.8);
		}
	}

	@keyframes cloud2 {
		0% {
			transform: translate(0px, 0px) scale(0.6);
		}

		25% {
			transform: translate(1px, 1px) scale(0.6);
		}

		50% {
			transform: translate(0px, 0px) scale(0.6);
		}

		75% {
			transform: translate(-1px, -1px) scale(0.6);
		}

		100% {
			transform: translate(0px, 0px) scale(0.6);
		}
	}

	.app-name {
		font: 38rpx sunzhongshan;
		align-self: center;
		color: #ffffff;
		font-weight: bolder;
	}

	.year-month {
		margin-left: 20rpx;
		margin-top: 40rpx;
		color: #ffffff;
		font-weight: bold;
		font-size: 28rpx;
		position: relative;
		display: flex;
		flex-direction: row;
		line-height: 50rpx;
	}

	.today-icon {
		position: absolute;
		width: 50rpx;
		height: 50rpx;
		right: -60rpx;
		top: 0rpx;
		z-index: 10;
	}

	.today-icon-image {
		width: 100%;
		height: 100%;
	}

	.swiper {
		height: 700rpx;
	}

	.calendar {
		height: 700rpx;
		background-image: url("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/1cb2ad77-ac70-4974-9e9b-786bc7fe1f1c.png");
		animation: kf-cloud 50s linear infinite;
		transform: translate3d(0, 0, 0);
		padding-top: 20rpx;
		padding-left: 20rpx;
		padding-right: 20rpx;
		position: relative;
		transition: background 5s ease;
		transition-delay: 2s;
	}

	@keyframes kf-cloud {
		50% {
			background-position: 100% 0
		}
	}

	.flying-bird {
		// width: 100%;
		height: 200rpx;
		position: absolute;
		bottom: -50rpx;
		left: 220rpx;
		width: 530rpx;
		transform: rotateY(180deg);
		opacity: 1;
		transition: opacity 5s ease;
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
		transition: color 5s ease;
		transition-delay: 2s;
	}

	.lunar-day {
		font-size: 20rpx;
		transition: color 5s ease;
		transition-delay: 2s;
	}

	.day {
		width: 13%;
		padding-bottom: 10rpx;
		margin-bottom: 10rpx;
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
		transition: filter 5s ease;
		transition-delay: 2s;
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

	.dds-animal-switcher {
		margin-right: 0rpx;
		margin-left: auto;
		width: 140rpx;
		height: 40rpx;
		border-radius: 30rpx;
		position: relative;

		background-color: transparent;
		border-radius: .75em;
		box-shadow: -0.1em -0.1em .1em #ffffff inset, 0.1em .1em .1em #808080 inset;
	}

	.dds-animalsyear {
		position: absolute;
		left: 0;
		top: -50%;
		transform-origin: center center;
		transition: all 1s ease;
	}

	.switcher-fade {
		// transform:  translateX(60rpx);
		transform: translateX(60rpx) rotateZ(360deg);
		// left: 60rpx;
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

	.daylight {
		.title-area {
			// background: linear-gradient(45deg,blue 0%, white 100%);
			background-color: blue;
		}

		.calendar {
			background-color: #d7f3f9;
		}

		.weekday {
			color: #111111;
		}

		.weekend {
			color: #c1252d;
		}

		.lunar-day-common {
			color: #424040;
		}

		.lunar-day-holiday {
			color: #b74854;
		}

		.sun {
			// background: radial-gradient(circle at center, red, gold);
			background-color: gold;
		}
	}

	.night {
		.title-area {
			// background: linear-gradient(45deg, #000 0%, white 100%);
			background-color: #000;
		}

		.calendar {
			background-color: #aaa;
		}

		.weekday {
			color: #fff;
		}

		.weekend {
			color: #c1252d;
		}

		.lunar-day-common {
			color: #f2f0f0;
		}

		.lunar-day-holiday {
			color: #b74854;
		}

		.sun {
			// background: linear-gradient(#fefefe, #fffbe8);
			background-color: #fefefe;
		}
	}
</style>
