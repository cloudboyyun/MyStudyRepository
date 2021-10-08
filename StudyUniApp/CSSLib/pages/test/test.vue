<template>
	<view class="main">
		<view class='sun'></view>
		<!-- <xy-event-ball :data="data"
			class='xy-event-ball'></xy-event-ball> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				data: {year: -221,event: '秦始皇统一六国'},
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
		background-color: black;
	}
	
	.sun {
		position: relative;
		top: 0;
		left: 0;
	  width: 30rpx;
	  height: 30rpx;
	  border-radius: 50%;
		box-shadow: 0rpx 0rpx 35rpx 10rpx #FFFF00;
		background-color: gold;
	}
</style>
