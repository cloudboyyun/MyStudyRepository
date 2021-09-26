<template>
	<view class="xy-bubble" :style="{width: radius, height: radius}">
		<view class="ball bubble">
			<view v-if="text" class='bubble-content' 
				:style="{'font-size': fontSize, 
					'font-family':fontFamily,
					'color': fontColor}">{{text}}</view>
			<image v-if="!text" class='bubble-content' :src='image'></image>
		</view>
	</view>
</template>

<script>
	export default {
		name: "xy-bubble",
		props: {
			image: {
				type: String,
				required: false
			},
			text: {
				type: String,
				default: null,
				required: false
			},
			textFont: {
				type: String,
				default: "50rpx",
				required: false
			},
			fontSize: {
				type: String,
				default: "50rpx",
				required: false
			},
			fontFamily: {
				type: String,
				default: "",
				required: false
			},
			fontColor: {
				type: String,
				default: "red",
				required: false
			},
			radius: {
				type: String,
				default: "100rpx",
				required: false
			}
		},
		async created() {
		},
		data() {
			return {
			};
		}
	}
</script>

<style>
	.xy-bubble {
		animation: move-bubble 15s linear infinite;
		transform-origin: 100% 100%;
		opacity: 1;
	}

	.ball {
		display: inline-block;
		width: 100%;
		height: 100%;
		border-radius: 50%;
		position: relative;
		background: radial-gradient(circle at bottom, #81e8f6, #76deef 10%, #055194 80%, #062745 100%);
	}

	.ball:before,
	.ball:after {
		content: "";
		position: absolute;
		border-radius: 50%;
		z-index: 2;
	}

	.ball:before {
		top: 0;
		left: 0;
		width: 90%;
		height: 90%;
		border-radius: 100%;
		background: radial-gradient(circle at top, #e2e3dd, rgba(226, 227, 221, 0) 58%);
		filter: blur(10rpx);
	}

	.ball:after {
		display: none;
		top: 5%;
		left: 10%;
		width: 80%;
		height: 80%;
		border-radius: 100%;
		filter: blur(2rpx);
		transform: rotateZ(-30deg);
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

	/* @keyframes move-bubble {
	  0% {
	    transform: rotateZ(-30deg);
	  }
	  10% {
	  }
	  80% {
	  }
	  100% {
	    transform: rotateZ(30deg);
	  }
	} */

	.bubble-content {
		width: 50%;
		height: 50%;
		position: relative;
		top: 25%;
		left: 25%;
	}
</style>
