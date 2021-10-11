<template>
	<view class='main'>
		<pre>steps方法，是animation-timing-function的一种。
语法：steps(integer, start | end)：
接受两个参数的步进函数。
第一个参数必须为正整数，指定整个动画被拆分为几步完成。
第二个参数取值可以是start或end，指定每一步的值发生变化的时间点。第二个参数是可选的，默认值为end.

*start（不匀速，每帧都是直接跳到每帧结尾处等待每帧时间结束）：以左侧端点为起点，立即跳到第一个step的结尾处。它会立即跳到第一段的结束并且保持这样的状态直到第一步的持续时间结束。后面的每一帧都将按照此模式来完成动画。

*end（不匀速，保持开始状态，直接跳转到下次开始状态）:动画执行时，在每一帧里，动画保持当前状态直到这一段的持续时间完成，才会跳到下一步的起点，后面的每一帧都按照这个模式来进行。
在最后一帧的起点，等到这一帧的持续时间结束，整个动画的执行也已经结束，执行动画的元素来不及跳到这一帧的终点，直接回到了整个动画起点，开始了第二次动画。

以下分别为：没有steps时，steps(5, start)，steps(5, end)时的动画：
			</pre>
		<view class="box1">
			<view class='box1-1'></view>
			<view class='box1-2'></view>
			<view class='box1-3'></view>
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

<style lang="scss">
	page {
		width: 100%;
	}

	.main {
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.section {
		margin-top: 20rpx;
		width: 90%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	pre {
		margin-left: 20rpx;
		margin-right: 20rpx;
		white-space: pre-wrap;
	}

	.box1 {
		margin-top: 20rpx;
		width: 300rpx;
		height: 200rpx;
		background-color: black;
		position: relative;

		.box1-1 {
			height: 20rpx;
			width: 0%;
			position: absolute;
			left: 0;
			top: 0;
			background: red;
			animation: 5s no1 infinite;
		}

		.box1-2 {
			height: 20rpx;
			width: 0%;
			position: absolute;
			left: 0;
			top: 30rpx;
			background: blue;
			animation: 5s no1 steps(5, start) infinite;
		}

		.box1-3 {
			height: 20rpx;
			width: 0%;
			position: absolute;
			left: 0;
			top: 60rpx;
			background: yellow;
			animation: 5s no1 steps(5, end) infinite;
		}
	}

	@keyframes no1 {
		0% {
			width: 0%;
		}

		100% {
			width: 100%;
		}
	}
</style>
