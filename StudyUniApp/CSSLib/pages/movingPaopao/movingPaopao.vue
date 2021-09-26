<template>
	<view class="main">
		<view class="bubble-holder">
			<view class="bubble-rotate-offset">
				<view class="ball bubble"></view>
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
	.main {
		position: absolute;
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0;
		top: 0;
		left: 0;
		background-color: #eef;
	}
	
	.bubble-holder {
	  width: 100rpx;
	  height: 100rpx;
	  position: absolute;
	  margin: 20rpx;
	  top: 50%;
	  left: 0;
	  animation: move-bubble 15s linear infinite;
	  transform-origin: 100% 100%;
	  opacity: 1;
	}

	.bubble-rotate-offset {
		width: 100%;
		height: 100%;
		animation: rotate-bubble-offset 15s linear infinite;
	}

	.ball {
		display: inline-block;
		width: 100%;
		height: 100%;
		border-radius: 100%;
		position: relative;
		background: radial-gradient(circle at bottom, #81e8f6, #76deef 10%, #055194 80%, #062745 100%);
	}

	.ball:before,
	.ball:after {
		content: "";
		position: absolute;
		border-radius: 100%;
		z-index: 2;
	}

	.ball:before {
		top: 0;
		left: 0;
		width: 90%;
		height: 90%;
		border-radius: 100%;
		background: radial-gradient(circle at top, white, rgba(255, 255, 255, 0) 58%);
		filter: blur(5px);
	}

	.ball:after {
		display: none;
		top: 5%;
		left: 10%;
		width: 80%;
		height: 80%;
		border-radius: 100%;
		filter: blur(1px);
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
	  97%,100% {
	    transform: scale(1);
	  }
	}
	@keyframes move-bubble {
	  0% {
	    transform: translateX(0) rotateZ(-30deg);
	    opacity: 0;
	  }
	  10% {
	    opacity: 1;
	  }
	  80% {
	    opacity: 1;
	  }
	  100% {
	    opacity: 0;
	    transform: translateX(100%) rotateZ(30deg);
	  }
	}
</style>
