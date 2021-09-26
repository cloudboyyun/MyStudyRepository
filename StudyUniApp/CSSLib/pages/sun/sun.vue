<template>
	<view class='main'>
		<view class="sun">
		  <view class="earth">       
		    <view class="moon"></view>
		  </view>
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
	page {
		width: 100%;
		height: 100%;
	}
	.main {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		/* margin-top: 200rpx; */
		background: #000000;
	}
	
	.sun{
	  width: 280rpx;
	  height: 280rpx;
	  border:1px solid #FFA500;
	  background:#FFA500;
	  border-radius: 50%;
	  box-shadow:0rpx 0rpx 35rpx #FFA500;
	  animation:action 10s;
	  animation-iteration-count:infinite;
	  animation-timing-function:linear;
	}
	
	.earth{
	  border:1rpx solid #0000CC;
	  background:#0000CC;
	  box-shadow:0px 0px 35rpx #0000CC;
	  width: 40rpx;
	  height: 40rpx;
	  border-radius: 50%;
	  margin-left: -90rpx;
	  animation:action 5s;
	  animation-iteration-count:infinite;
	  animation-timing-function:linear;
	}
	
	.moon{
	  border:1rpx solid #F0F0F0;
	  background:#F0F0F0;
	  box-shadow:0rpx 0px 35rpx #F0F0F0;
	  width: 20rpx;
	  height: 20rpx;
	  border-radius: 50%;
	  margin-left: -40rpx;                
	}
	
	@keyframes action{
	  0% { transform: rotate(0deg) }
	  100% { transform: rotate(360deg)}
	}
</style>
