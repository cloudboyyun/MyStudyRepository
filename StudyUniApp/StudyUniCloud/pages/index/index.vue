<template>
	<view class="content">
		<view class='menu-item' @click="toSchema2Code">自动生成的代码(schema2Code)</view>
		<view class='menu-item' @click="toByCloudFunction">通过云函数(CloudFunction)操作数据库</view>
		<view class='menu-item' @click="toByJQL">通过ClientDB-JQL操作数据库</view>
		<view class='menu-item' @click="toUniCloudDB">通过unicloud-db组件操作数据库</view>
		<view class='menu-item' @click="toVuex">测试Vuex</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {
			toSchema2Code:function() {
				uni.navigateTo({
					url:"../t_news/list"
				})
			},
			
			toByCloudFunction:function() {
				uni.navigateTo({
					url:"../dbTest/ByCloudFunction/ByCloudFunction"
				})
			},
			
			toByJQL:function() {
				uni.navigateTo({
					url:"../dbTest/ByJQL/ByJQL"
				})
			},
			
			toUniCloudDB:function() {
				uni.navigateTo({
					url:"../dbTest/ByUniCloudDB/ByUniCloudDB"
				})
			},
			
			toVuex: function() {
				uni.navigateTo({
					url:"../TestVuex/TestVuex"
				})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
	}

	.menu-item {
		margin-top: 20rpx;
		margin-left: 20rpx;
		color:#007AFF;
	}
</style>
