<template>
	<view class='cloud'>
	</view>
</template>

<script>
	export default {
		name:"xy-cloud",
		props: {
		},
		async created() {
		},
		data() {
			return {
				
			};
		}
	}
</script>

<style>
	.cloud {
		background: #fff;
		position: relative;
		width: 65rpx;
		height: 12rpx;
		border-radius: 20rpx;
		opacity: 1;
	}
	
	.cloud:after {
		content: "";
		position: absolute;
		background: #fff;
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
</style>
