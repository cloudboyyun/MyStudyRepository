<template>
	<view class='main'>
		<view class="trajectory">
		    <view class="move">兔子</view>
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
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		margin-top: 50rpx;
		font-size: 20rpx;
	}
	
	.trajectory {
	    width: 600rpx;
	    height: 600rpx;
	    border: 4rpx solid #949494;
	    border-radius: 50%;
	    position: relative;
	}
	
	.move {
		text-overflow: ellipsis;
		width: 40rpx;
		height: 40rpx;
		font-size: 12rpx;
		background-color: #32c33a;
		border-radius: 50%;
		position: relative;
		left:-20rpx;
		top:50%;
		animation: moveX 4s cubic-bezier(0.36,0,0.64,1) -2s infinite alternate, moveY 4s cubic-bezier(0.36,0,0.64,1) 0s infinite alternate;
	}
	@keyframes moveX{
	    0% {left: -20rpx;}
	    100% {left: 580rpx;}
	}
	@keyframes moveY{
	    0% {top: -20rpx;}
	    100% {top: 580rpx;}
	}
</style>
