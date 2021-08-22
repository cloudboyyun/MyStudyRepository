<template>
	<view class="page">
		<view class="head">
			<view class="date">
				<text style="font-size: 46rpx;">{{month}}</text>月{{date}}日
				<text class="day"> {{day}} </text>
			</view>
		</view>
		<view class="share-box">
			<view style="margin-left: 30rpx;">今日全部分享</view>
			<view class="share">
				<view class="share-1" @click="toVisitorRecord(0)">
					<view class="share-num-title">被浏览</view>
					<view class='share-num'>
						{{operateRecord.todayBrowsedCount}}
						<text class="unit">次</text>
					</view>
				</view>
				<view class="share-3" @click="toVisitorRecord(2)">
					<view class="share-num-title">我的分享</view>
					<view class='share-num'>
						{{operateRecord.todayShareCount}}
						<text class="unit">条</text>
					</view>
				</view>
			</view>
		</view>
		
		<view class="title">分享拓客</view>
		<view class="space-bewteen">
			<view class="dev-customer" @click="toShare(0)">
				每日头条
				<view class="customer-1">分享行业资讯</view>
				<image src="../../static/images/main/Subtracttoutiao.png" class="toutiao-img"></image>
			</view>
			<view class="dev-customer" @click="toShare(1)">
				励志海报
				<view class="customer-2">每日励志语录</view>
				<image src="../../static/images/main/lizhi.png" class="toutiao-img"></image>
			</view>
			<view class="dev-customer" @click="toShare(2)">
				自定海报
				<view class="customer-3">定制个性海报</view>
				<image src="../../static/images/main/Subtractdingzhi.png" class="toutiao-img"></image>
			</view>
		</view>
	</view>
</template>

<script>
	const day = {
		'0': '星期天',
		'1': '星期一',
		'2': '星期二',
		'3': '星期三',
		'4': '星期四',
		'5': '星期五',
		'6': '星期六'
	}

	export default {
		components: {
		},
		data() {
			return {
				href: 'https://uniapp.dcloud.io/component/README?id=uniui',
				eventList: [{'activityType': '销售拜访', 'customerName':'A名字', activityRemindList:'提醒',
				activityDate: '2021-04-21 10:00', visitType: '面谈', SpendTime: '1：30', 
				activityThemeList:"客户资料收集"},
				{'activityType': '销售拜访', 'customerName':'A名字', activityRemindList:'提醒',
				activityDate: '2021-04-21 10:00', visitType: '面谈', SpendTime: '1：30', 
				activityThemeList:"需求分析"}],
				picList:[],
				month: '',
				date: '',
				day: '',
				operateRecord: {},
				jobList: [{'name': '代理人', degree: '2', experience: '2', area: '000000', company: 'XX公司', tags:['优势标签','标签2']},
				{'name': '代理人', degree: '1', experience: '3', area: '000000', company: 'XX公司', tags:['优势标签','标签2']},
				{'name': '代理人', degree: '3', experience: '1', area: '000000', company: 'XX公司', tags:['优势标签','标签2']},]
			}
		},
		onLoad() {
		},
		onShow() {
			this.loadData();
		},
		methods: {
			loadData() {
				let date = new Date()
				this.month = date.getMonth() + 1
				this.date = date.getDate()
				this.day =  day[date.getDay()]
				// this.$api.mainPages.homePageData().then(res=> {
				// 	console.log('homePage', res)
				// 	this.jobList = res.jobList;
				// 	this.operateRecord = res.operateRecord;
				// 	this.picList = res.tCarousels
				// 	console.log(this.operateRecord);
				// })
			},
			
			toVisitorRecord(i) {
				uni.navigateTo({
					url: '../share/visitorRecord?tab=' + i
				})
			},
			toJobDetail(item) {
				uni.navigateTo({
					url: '../job/jobDetail?id=' + item.jobId
				})
			},
		
			confirm(e) {
				console.log(e)
				this.month = e.month
				this.date = e.date
				this.day = day[new Date(e.fulldate).getDay()]
			},
			toShare(i) {
				console.log('toshare', i)
				uni.navigateTo({
					url: '/pages/share/share?tab=' + i,
				})
			},
			toJob() {
				uni.navigateTo({
					url: '../job/job'
				})
			},
			toevent(){
				uni.switchTab({
					url: '/pages/event/event'
				})
			},
			toCustomerList() {
				uni.navigateTo({
					url: '/pages/customer/customerList',
				})
			}
		}
	}
</script>

<style>
	page {
		background-color: #efefef;
		color: #999999;   
	}
	.page {
		padding: 0 30rpx;
		font-size: 14px;
		line-height: 24px;
	}
	.head {
		background-image: linear-gradient(95.74deg, #F17A4F 5.74%, #F45858 59.53%, #F45858 93.27%);
		height: 280rpx;
		width: 100vw;
		margin-left: -30rpx;
		color: white;
	}
	.date {
		display: flex;
		flex-direction: row;
		padding: 70rpx 0 0 40rpx;
		font-size: 34rpx;
	}
	.day {
		font-size: 26rpx;
		padding-left: 20rpx;
	}
	
	.space-bewteen {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-between;
	}

	.share-box {
		color: #999999;
		background-color: white;
		border-radius: 20rpx;
		margin-bottom: 30rpx;
		height: 210rpx;
		padding: 20rpx 0;
		margin-top: -130rpx;
	}
	.share {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-between;
		margin-top: 20rpx;
	}
	.share-1 {
		color: #F4584D;
		font-size: 40rpx;
		display: flex;
		align-items: center;
		flex-direction: column;
		justify-content: center;
		font-weight: 700;
		width: 50%;
		border-right: 1rpx solid #E7E7E7;
	}
	.share-3 {
		color: #38B7CF;
		font-size: 40rpx;
		display: flex;
		align-items: center;
		flex-direction: column;
		justify-content: center;
		font-weight: 700;
		width: 50%;
	}
	.share-num-title {
		margin-bottom: 20rpx;
		letter-spacing: 10rpx;
	}
	.share-num {
		display: flex;
		flex-direction: row;
		align-items: flex-end;
	}
	.unit {
		font-size: 26rpx;
		color: #999999;
		font-weight: 500;
		margin-left: 10rpx;
		line-height: 40rpx;
	}

	.expand-box > view {
		font-size: 28rpx;
		padding-top: 20rpx;
	}
	.title {
		font-size: 40rpx;
		font-weight: 700;
		color: #3d3d3d;
		display: flex;
		align-items: flex-start;
		justify-content: space-between;
		height: 60rpx;
		margin: 40rpx 0 20rpx 0;
	}
	.see-more {
		font-size: 28rpx;
		color: #C4C4C4;
	}
	.scroll-x {
		width: 720rpx;
		white-space: nowrap;
	}
	.event {
		background-color: white;
		width: 230rpx;
		height: 150rpx;
		border-radius: 14rpx;
		margin-right: 20rpx;
		position: relative;
		display: inline-block; 
	}
	.done {
		position: absolute;
		background-color: #A9A9A9;
		right: 0;
		top: 0;
		color: #900c03;
		font-size: 24rpx;
	}
	.not {
		position: absolute;
		background-color: #A9A9A9;
		right: 0;
		top: 0;
		color: white;
		font-size: 24rpx;
	}
	
	.myClient {
		background-color: white;
		border-radius: 16rpx;
		display: flex;
		align-items: center;
		height: 148rpx;
		position: relative;
		font-size: 28rpx;
		font-weight: 600;
		color: #C4C4C4;
	}
	.myClient-1 {
		color: #F4584D;
		padding-right: 20rpx;
		padding-left: 190rpx;
	}
	.myClient-2 {
		color: #F99500;
		padding-right: 20rpx;
		padding-left: 20rpx;
	}
	.myClient-3 {
		color: #38B7CF;
		padding-right: 20rpx;
		padding-left: 20rpx;
	}
	.myClient > image {
		width: 176rpx;
		height: 140rpx;
		position: absolute;
		bottom: 0;
		left: 0;
	}

	.line {
		display: flex;
		align-items: center;
	}
	.job-box {
		background-color: white;
		margin-bottom: 10rpx;
		color: #999999;
		font-size: 24rpx;
		padding: 10rpx;
		position: relative;
		overflow: hidden;
	}
	.job-company {
		color: #F3B869;
	}
	.job-name {
		font-size: 30rpx;
		font-weight: 600;
	}
	.tag {
		color: white;
		background-color: #FFCD8A;
		border-radius: 10rpx;
		margin-left: 10rpx;
		padding: 0 6rpx;
		font-size: 20rpx;
		height: 40rpx;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	
	.dev-customer {
		background-color: white;
		width: 220rpx;
		height: 140rpx;
		border-radius: 8rpx;
		position: relative;
		overflow: hidden;
		box-sizing: border-box;
		padding-left: 20rpx;
		padding-top: 20rpx;
		color: #6C6C6C;
		font-weight: 600;
	}
	.toutiao-img {
		width: 84rpx;
		height: 104rpx;
		position: absolute;
		right: -10rpx;
		bottom: -10rpx;
	}
	.customer-1 {
		color: #5E8AFF;
		font-size: 22rpx;
		position: relative;
		z-index: 10;
	}
	.customer-2 {
		color: #FFC48D;
		font-size: 22rpx;
		position: relative;
		z-index: 10;
	}
	.customer-3 {
		color: #FF9EB0;
		font-size: 22rpx;
		position: relative;
		z-index: 10;
	}
	.event-box {
		background-color: white;
		border-radius: 16rpx;
	}
	.event-type-line {
		background: linear-gradient(270deg, #FFAC5F 0%, #FF8412 100%);
		width: 350rpx;
		height: 60rpx;
		display: inline-block;
		color: white;
		display: flex;
		justify-content: space-evenly;
		align-items: center;
	}
	.type {
		text-align: center;
		width: 340rpx;
	}
	.event-name {
		font-size: 32rpx;
	}
	.event-start {
		width: 345rpx;
		height: 120rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;
		font-size: 22rpx;
	}
	.start-time {
		color: #F26960;
		font-size: 36rpx;
		font-weight: 600;
	}
	.spend-time {
		color: #FFAB49;
		font-size: 36rpx;
		font-weight: 600;
		
	}
	.black {
		color: #000000;
		font-size: 24rpx;
	}
	.remind-line {
		border-top: 1px solid #F2F2F2;
		padding-left: 30rpx;
		padding-top: 10rpx;
	}
	.quarter-circle {
		width: 100rpx;
		height: 100rpx;
		background-color: #E3F5FF;
		border-radius: 50%;
		position: absolute;
		right: -50rpx;
		top: -50rpx;
		z-index: 2;
	}
	.quarter-circle-2{
		width: 100rpx;
		height: 100rpx;
		background-color: #FFF4E3;
		border-radius: 50%;
		position: absolute;
		right: -50rpx;
		top: -50rpx;
		z-index: 2;
	}
	.pic {
		width: 690rpx;
		height: 280rpx;
		border-radius: 16rpx;
	}
</style>
