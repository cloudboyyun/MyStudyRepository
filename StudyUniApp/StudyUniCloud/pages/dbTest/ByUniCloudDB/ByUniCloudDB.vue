<template>
	<view class='content'>
		<view class='menu-title'>通过unicloud-db组件操作数据库</view>
		<unicloud-db v-slot:default="{data, loading, error, options}" field="title"  collection="t_news">
			<view v-if="error">{{error.message}}</view>
			<view v-else>
				<view class='menu-item' v-for="(item,index) in data" :key="item._id">
					{{ item.title }}
				</view>
				
			</view>
		</unicloud-db>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			}
		},
		onLoad(options) {
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
