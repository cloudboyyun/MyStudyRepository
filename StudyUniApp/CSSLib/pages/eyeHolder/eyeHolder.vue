<template>
	<view class='main'>
		<view class="eye-holder">
			<view class="ball">
				<view class="shadow"></view>
				<view class="iris"></view>
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
		background-color: #eef;
		display: flex;
		align-items: flex-start;
		justify-content: center;
	}

	.eye-holder {
		margin-top: 40vh;
		width: 300rpx;
		height: 300rpx;
	}

	.eye-holder .ball {
		background: radial-gradient(circle at 50% 40%, #fcfcfc, #efeff1 66%, #9b5050 100%);
	}

	.ball {
		display: inline-block;
		width: 100%;
		height: 100%;
		border-radius: 50%;
		position: relative;
		background: radial-gradient(circle at bottom, #81e8f6, #76deef 10%, #055194 80%, #062745 100%);
	}

	.ball:before {
		top: 0;
		left: 0;
		width: 90%;
		height: 90%;
		border-radius: 50%;
		background: radial-gradient(circle at top, white, rgba(255, 255, 255, 0) 58%);
		filter: blur(10rpx);
	}

	.ball:after {
		display: none;
		top: 5%;
		left: 10%;
		width: 80%;
		height: 80%;
		border-radius: 50%;
		filter: blur(2rpx);
		transform: rotateZ(-30deg);
	}

	.ball:before,
	.ball:after {
		content: "";
		position: absolute;
		border-radius: 100%;
		z-index: 2;
	}

	.ball .shadow {
		position: absolute;
		width: 100%;
		height: 100%;
		background: radial-gradient(circle, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.1) 40%, transparent 50%);
		transform: rotateX(90deg) translateZ(-320rpx);
		z-index: 1;
	}

	.ball.bubble {
		background: radial-gradient(circle at 50% 55%, rgba(240, 245, 255, 0.9), rgba(240, 245, 255, 0.9) 40%, rgba(225, 238, 255, 0.8) 60%, rgba(43, 130, 255, 0.4));
		animation: bubble-anim 2s ease-out infinite;
	}

	.ball.bubble:before {
		filter: blur(0);
		height: 80%;
		width: 40%;
		background: radial-gradient(circle at 130% 130%, rgba(255, 255, 255, 0) 0, rgba(255, 255, 255, 0) 46%, rgba(255, 255, 255, 0.8) 50%, rgba(255, 255, 255, 0.8) 58%, rgba(255, 255, 255, 0) 60%, rgba(255, 255, 255, 0) 100%);
		transform: translateX(131%) translateY(58%) rotateZ(168deg) rotateX(10deg);
	}

	.ball.bubble:after {
		display: block;
		background: radial-gradient(circle at 50% 80%, rgba(255, 255, 255, 0), rgba(255, 255, 255, 0) 74%, white 80%, white 84%, rgba(255, 255, 255, 0) 100%);
	}

	.iris {
		width: 40%;
		height: 40%;
		margin: 30%;
		border-radius: 50%;
		background: radial-gradient(circle at 50% 50%, #208ab4 0%, #6fbfff 30%, #4381b2 100%);
		transform: translateX(68rpx) translateY(-60rpx) skewX(15deg) skewY(2deg);
		position: absolute;
		animation: move-eye-skew 5s ease-out infinite;
	}

	.iris:before,
	.iris:after {
		content: "";
		position: absolute;
		border-radius: 50%;
	}

	.iris:before {
		width: 37.5%;
		height: 37.5%;
		top: 31.25%;
		left: 31.25%;
		background: black;
	}

	.iris:after {
		width: 31.25%;
		height: 31.25%;
		top: 18.75%;
		left: 18.75%;
		background: rgba(255, 255, 255, 0.2);
	}

	@keyframes bubble-anim {
		0% {
			transform: scale(1);
		}

		20% {
			transform: scaleY(0.95) scaleX(1.05);
		}

		48% {
			transform: scaleY(1.1) scaleX(0.9);
		}

		68% {
			transform: scaleY(0.98) scaleX(1.02);
		}

		80% {
			transform: scaleY(1.02) scaleX(0.98);
		}

		97%,
		100% {
			transform: scale(1);
		}
	}

	@keyframes move-eye-skew {
		0% {
			transform: none;
		}
		20% {
			transform: translateX(-50rpx) translateY(30rpx) skewX(15deg) skewY(-10deg) scale(0.95);
		}
		25%,
		44% {
			transform: none;
		}

		50%,
		60% {
			transform: translateX(50rpx) translateY(-30rpx) skewX(5deg) skewY(2deg) scaleX(0.95);
		}

		66%,
		100% {
			transform: none;
		}
	}
</style>
