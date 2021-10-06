<template>
	<view class='main'>
		<view class='bird'></view>
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
		position: relative;
	}
	
	.bird:before,
	.bird:after {
		content: "";
		position: absolute;
		border: 2rpx solid #bb7074;
		border-bottom: 0;
		width: 18rpx;
		height: 8rpx;
	}
	
	.bird {
		position: relative;
		top: 100rpx;
		left: 250rpx;
		transform: scale(5);
		transform-origin: left center;
		/* animation: bird 1s infinite;
		transition: all 2s ease-in-out; */
	}
	
	.bird:after {
		content: "";
		position: absolute;
		border-top-left-radius: 18rpx;
		border-top-right-radius: 18rpx;
		border-right: 0;
		animation: wing-right 1s infinite;
		transform-origin: bottom left;
	}
	
	.bird:before {
		content: "";
		position: absolute;
		left: -19rpx;
		border-top-right-radius: 18rpx;
		border-top-left-radius: 18rpx;
		border-left: 0;
		animation: wing-left 1s infinite;
		transform-origin: bottom right;
	}
	
	@keyframes wing-right {
		0% {
			transform: rotate(0deg);
		}
	
		50% {
			transform: rotate(10deg);
		}
	
		100% {
			transform: rotate(0deg);
		}
	}
	
	@keyframes wing-left {
		0% {
			transform: rotate(0deg);
		}
	
		50% {
			transform: rotate(-10deg);
		}
	
		100% {
			transform: rotate(0deg);
		}
	}
	
	@keyframes bird {
		0% {
			transform: translate(0rpx, 0rpx) scale(1);
		}
		50% {
			transform: translate(2rpx, -3rpx) scale(5);
		}
		100% {
			transform: translate(0rpx, 0rpx) scale(10);
		}
	}
</style>
