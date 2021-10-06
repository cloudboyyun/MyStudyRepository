<template>
	<view class='main'>
		<view class='cloud'></view>
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

<style>
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
		background: #0000FF;
	}
	
	.cloud {
		background: #fff;
		width: 65rpx;
		height: 12rpx;
		position: relative;
		left: 50%;
		top: 50%;
		border-radius: 20rpx;
		animation: cloud 4s infinite;
	}
	
	.cloud:after {
		content: "";
		background: #fff;
		position: absolute;
		border-radius: 50%;
		height: 30rpx;
		left: 27rpx;
		top: -18rpx;
		width: 30rpx;
	}
	
	.cloud:before {
		content: "";
		position: absolute;
		background: #fff;
		border-radius: 50%;
		height: 20rpx;
		left: 12rpx;
		top: -11rpx;
		width: 20rpx;
	}
	
	@keyframes cloud {
		0% {
			transform: translate(0px, 0px);
		}
		25% {
			transform: translate(5rpx, 5rpx);
		}
		50% {
			transform: translate(0rpx, 0rpx);
		}
		75% {
			transform: translate(-5rpx, -5rpx);
		}
		100% {
			transform: translate(0rpx, 0rpx);
		}
	}
</style>
