<template>
	<view class="content">
		<uni-list>
		    <uni-list-item clickable title="环装Loading"
					@click="toPage('/pages/huanzhuangLoading/huanzhuangLoading')"
					 ></uni-list-item>
				<uni-list-item clickable title="纯css3云彩动画效果" 
					@click="toPage('/pages/cloud/cloud')"></uni-list-item>
				<uni-list-item clickable title="纯css3翻书效果"
					@click="toPage('/pages/book/book')"></uni-list-item>
		</uni-list>
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
			toPage(url) {
				uni.navigateTo({
					url: url
				})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding-top: 20rpx;
	}
</style>
