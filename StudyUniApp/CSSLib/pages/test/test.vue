<template>
	<view class="main">
		<view class='box'>
			<image src='/static/images/niu.png' class='dds-animal-image'></image>
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
	}
	
	.main {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-around;
		padding-top: 50rpx;
	}
	
	.dds-animal-image {
		width: 80rpx;
		height: 80rpx;
		animation: switcher2 2s infinite ease-in-out;
	}
	
	.box {
		width: 120rpx;
		height: 120rpx;
		border-radius: 50%;
		position: relative;
		display: flex;
		justify-content: center;
		align-items: center;
		border-top: 5rpx solid red;
		animation: switcher 2s infinite ease-in-out;
	}
	
	@keyframes switcher {
		50% {
			transform: rotate(-40deg);
		}
		100% {
			transform: rotate(40deg);
		}
	}
	
	@keyframes switcher2 {
		50% {
			transform: rotate(40deg);
		}
		100% {
			transform: rotate(-40deg);
		}
	}
</style>
