<template>
	<view class="main">
		<view class='ball'>
			<image class='ball-image' src='/static/images/niu.png'></image>
			<!-- <view class='ball-text'>牛</view> -->
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

<style lang='scss'>
	@font-face {
	    font-family: 'sunzhongshan';
	    src:url('/static/fonts/方圆孙中山行书.ttf');
	}
	
	page {
		width: 100%;
		height: 100%;
		.main {
			width: 100%;
			height: 100%;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			.ball {
				width: 300rpx;
				height: 300rpx;
				border-radius: 50%;
				position: relative;
				background: radial-gradient(circle at 50% 40%, #fcfcfc, #efeff1 66%, #9b5050 100%);
				animation: action 10s;
				animation-iteration-count:infinite;
				animation-timing-function:linear;
				.ball-text {
					position: relative;
					top: 50%;
					left: 50%;
					font: 100rpx sunzhongshan;
					line-height: 100rpx;
					width: 100rpx;
					transform: translateX(-50%) translateY(-50%)
				}
				
				.ball-image {
					position: relative;
					top: 50%;
					left: 50%;
					width: 100rpx;
					height: 100rpx;
					transform: translateX(-50%) translateY(-50%)
				}
			}
		}
	}
	
	/* @keyframes action{
	  0% { transform: rotateY(0deg) }
	  100% { transform: rotateY(360deg)}
	} */
	
	
	
	
</style>
