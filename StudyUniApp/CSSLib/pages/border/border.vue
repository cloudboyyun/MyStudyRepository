<template>
	<view class='main'>
		<view class='square'></view>
		<view class='square1'></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style lang='scss'>
	$square-size: 200rpx;
	
	page {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
	}
	
	.main {
		margin-top: 20rpx;
		width: 500rpx;
		height: 500rpx;
		position: relative;
		background: #0000FF;
	}
	
	.square {
		width: $square-size;
		height: $square-size;
		border: 100rpx solid;
		border-color: #893615 #E76B56 #A72310 #0C1F22;
		box-sizing: border-box;
	}
	
	.square1 {
		width: 0;
		height: 0;
		border: 0 solid transparent;
		border-right-width: 15rpx;
		border-left-width: 0rpx;
		border-bottom: 30rpx solid #bb7074;
	}
</style>
