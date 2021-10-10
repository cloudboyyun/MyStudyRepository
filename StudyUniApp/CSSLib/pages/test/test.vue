<template>
	<view class="main">
		<view class='box'>
			<view class='point'></view>
		</view>
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
	
	.box {
	  width: 300rpx;
	  height: 30rpx;
		background-color: white;
		position: relative;
	}
	
	.point {
		height: 100%;
		width: 0%;
		position: absolute;
		left: 0;
		background: gold;
		/*
		  start（不匀速，每帧都是直接跳到每帧结尾处等待每帧时间结束）：以左侧端点为起点，立即
				跳到第一个step的结尾处。它会立即跳到第一段的结束并且保持这样的状态直到第一步的持续
				时间结束。后面的每一帧都将按照此模式来完成动画。
			end（不匀速，保持开始状态，直接跳转到下次开始状态）:动画执行时，在每一帧里，动画保
		      持当前状态直到这一段的持续时间完成，才会跳到下一步的起点，后面的每一帧都按照这个模
		      式来进行。
		      在最后一帧的起点，等到这一帧的持续时间结束，整个动画的执行也已经结束，执行动画的元
		      素来不及跳到这一帧的终点，直接回到了整个动画起点，开始了第二次动画。;
		*/
		animation: 10s test steps(5, start) infinite;
	}
	
	@keyframes test {
		0% {
			width: 0%;
		}
		100% {
			width: 100%;
		}
	}
</style>
