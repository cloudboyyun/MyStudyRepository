<template>
	<view class="main">
		<view class='box'></view>
		<!-- <xy-bubble radius="200rpx" image='/static/images/niu.png' ></xy-bubble> -->
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
	
	.main {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		margin-top: 50rpx;
	}
	
	.box {
		width: 100rpx;
		height: 100rpx;
		background: blue;
		transition: all .6s cubic-bezier(.75,.25,1,1);
	}
	
	.box:hover {
		width: 300rpx;
		background: red;
	}
</style>
