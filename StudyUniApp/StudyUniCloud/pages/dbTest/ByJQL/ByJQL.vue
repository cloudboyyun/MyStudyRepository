<template>
	<view class='content'>
		<view class='menu-title'>通过ClientDB-JQL操作数据库</view>
		<view class='menu-item' v-for="(item,i) in newsList" :key="item._id">
			<view>{{item.title}} - {{item.channel}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				newsList: null
			}
		},
		onLoad(options) {
			const db = uniCloud.database();
			db.collection('t_news').get().then(res => {
				console.log(res.result.data);
				this.newsList = res.result.data;
			});
		},
		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
	}
	.menu-title {
		align-self: center;
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		padding-bottom: 10rpx;
		width: 100%;
		text-align: center;
		border-bottom: 2rpx solid #999999;
		font-size: 35rpx;
		font-weight: bolder;
	}
	
	.menu-item {
		margin-left: 20rpx;
		margin-right: 20rpx;
		margin-bottom: 10rpx;
		font-size: 20rpx;
	}
</style>
