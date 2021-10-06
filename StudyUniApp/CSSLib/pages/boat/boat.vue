<template>
	<view class='main'>
		<view class='boat'></view>
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
	
	.boat {
		position: relative;
		width: 50rpx;
		height: 0rpx;
		border-top: 7rpx solid #bb7074;
		border-left: 10rpx solid transparent;
		border-right: 10rpx solid transparent;
		left: 50%;
		top: 50%;
		transform-origin: center bottom;
		animation: boat 5s infinite;
		animation-delay: 0.25s;
		transition: all 4s ease-in-out;
	}
	
	.boat:before,
	.boat:after {
		width: 0;
		height: 0;
		border: 0 solid transparent;
	}
	
	.boat:before {
		content: "";
		position: absolute;
		bottom: 8rpx;
		right: -6rpx;
		border-right-width: 15rpx;
		border-left-width: 0rpx;
		border-bottom: 30rpx solid #bb7074;
	}
	
	.boat:after {
		content: "";
		position: absolute;
		bottom: 8rpx;
		right: 11rpx;
		border-right-width: 0rpx;
		border-left-width: 20rpx;
		border-bottom: 48rpx solid #bb7074;
	}
	
	@keyframes boat {
		0% {
			transform: rotate(1deg)translate(2px, 0px);
		}
	
		25% {
			transform: rotate(-1deg) translate(-2px, -2px);
		}
	
		50% {
			transform: rotate(1deg) translate(1px, 0px);
		}
	
		75% {
			transform: rotate(-1deg) translate(-1px, -2px);
		}
	
		100% {
			transform: rotate(1deg) translate(2px, 0px);
		}
	}
</style>
