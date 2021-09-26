<template>
	<view class='main'>
		<view class="ball bubble"></view>
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
	}

	.ball {
		width: 200rpx;
		height: 200rpx;
		border-radius: 100%;
		position: relative;
		background: radial-gradient(circle at bottom, #81e8f6, #76deef 10%, #055194 80%, #062745 100%);
	}

	.ball:before {
		content: "";
		position: absolute;
		border-radius: 100%;
		z-index: 2;
		top: 0;
		left: 0;
		width: 90%;
		height: 90%;
		background: radial-gradient(circle at top, white, rgba(255, 255, 255, 0) 58%);
		filter: blur(5rpx);
	}

	.ball:after {
		content: "";
		position: absolute;
		border-radius: 100%;
		z-index: 2;
		display: none;
		top: 5%;
		left: 10%;
		width: 80%;
		height: 80%;
		filter: blur(1rpx);
		transform: rotateZ(-30deg);
	}

	.ball.bubble {
		background: radial-gradient(circle at 50% 55%,
				rgba(240, 245, 255, 0.9),
				rgba(240, 245, 255, 0.9) 40%,
				rgba(225, 238, 255, 0.8) 60%,
				rgba(43, 130, 255, 0.4));
	}

	.ball.bubble:after {
		display: block;
		background: radial-gradient(circle at 50% 80%, rgba(255, 255, 255, 0), rgba(255, 255, 255, 0) 74%, white 80%, white 84%, rgba(255, 255, 255, 0) 100%);
	}
</style>
