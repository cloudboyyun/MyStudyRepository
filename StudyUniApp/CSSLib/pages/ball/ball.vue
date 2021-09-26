<template>
	<view class='main'>
		<view class='ball'>
			<view class='shadow'></view>
		</view>
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
	.main {
		width: 300rpx;
		height: 300rpx;
		display: inline-block;
		margin: 20rpx;
		perspective: 1200rpx;
		perspective-origin: 50% 50%;
	}
	.ball {
		display: block;
		position: relative;
		width: 300rpx;
		height: 300rpx;
		/* background: black url(../../static/images/niu.png) repeat-x center center; */
		border-radius: 50%;
		background: radial-gradient(circle at 100rpx 100rpx, #5cabff, #000);
	}
	
	.ball .shadow {
		position: absolute;
		width: 100%;
		height: 100%;
		background: radial-gradient(circle at 50% 50%, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.1) 40%, rgba(0, 0, 0, 0) 50%);
		transform: rotateX(90deg) translateZ(-150rpx);
		z-index: -1;
	}
</style>
