<template>
	<view class='wrapper'>
		<view class="score"></view>
		<view class="sun"></view>
		<view class="grid"></view>
		<view class="car">
			<view class="car__mirror car__mirror--left"></view>
			<view class="car__mirror car__mirror--right"></view>
			<view class="car__wheel car__wheel--left"></view>
			<view class="car__wheel car__wheel--right"></view>
			<view class="car__roof"></view>
			<view class="car__bumper"></view>
			<view class="car__back">
				<view class="car__light car__light--left"></view>
				<view class="car__light car__light--small car__light--left"></view>
				<view class="car__light car__light--small car__light--right"></view>
				<view class="car__light car__light--right"></view>
				<view class="car__grill">
					<view class="car__emblem"></view>
				</view>
			</view>
			<view class="car__plate">
				<view class="synth">SYNTH</view>
			</view>
			<view class="car__exhaust car__exhaust--left"></view>
			<view class="car__exhaust car__exhaust--right"></view>
		</view>
		<view class="diamond"></view>
		<view class="diamond"></view>
		<view class="diamond"></view>
		<view class="diamond"></view>
		<view class="circle"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				successcolor: '#2eff04',
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	$car-color: #ff2800;
	$window-color: #add8e6;
	$success-color: #2eff04;
	$error-color: #fd1c03;

	page {
		background-color: #000;
		background-image: radial-gradient(ellipse at center, rgba(127, 0, 173, 0.4) 0%, black 90%);
		height: 100%;
		width: 100%;
	}

	.wrapper {
		box-sizing: border-box;
		height: 500rpx;
		// width: 80%;
		width: 600rpx;
		margin-left: 20rpx;
		margin-right: 20rpx;
		border: 1rpx solid rgba(127, 0, 173, 0.6);
		background-color: #000;
		box-shadow: 0 19rpx 38rpx rgba(0, 0, 0, 0.3), 0 15rpx 12rpx rgba(0, 0, 0, 0.22);
		background-image: radial-gradient(ellipse at center, rgba(127, 0, 173, 0.6) 0%, rgba(0, 0, 0, 0.8) 60%, black 90%);
		background-size: cover;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		position: absolute;
		overflow: hidden;
		perspective: 50rpx;
		transform-style: preserve-3d;
	}

	.score {
		position: absolute;
		border-radius: 8rpx;
		top: 20rpx;
		right: 20rpx;
		width: 180rpx;
		height: 30rpx;
		background: #222;
		border: 1px solid white;
	}

	.score:after {
		animation: 8s health steps(4) infinite;
		animation-delay: 1s;
		content: "";
		border-radius: 0.1em;
		box-shadow: 0 0 10rpx currentColor;
		position: absolute;
		left: 0;
		top: 0;
		height: 100%;
		color: $success-color;
		background: currentColor;
		width: 20%;
	}

	@keyframes health {
		0% {
			width: 20%;
		}

		50% {
			width: 100%;
		}

		79% {
			color: $success-color;
			opacity: 1;
		}

		80% {
			color: $error-color;
			opacity: 0;
		}

		81% {
			opacity: 1;
		}

		82% {
			opacity: 0;
		}

		83% {
			opacity: 1;
		}

		84% {
			color: $error-color;
			width: 100%;
			opacity: 0;
		}

		90% {
			color: $success-color;
			width: 20%;
			opacity: 1;
		}

		100% {
			width: 20%;
		}
	}

	.sun {
		position: absolute;
		border-radius: 50%;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		width: 100rpx;
		height: 100rpx;
		background: linear-gradient(#f12711, yellow);
		box-shadow: 0 0 30rpx #f5af19;
	}

	.grid {
		background: #010101;
		height: 960rpx;
		transform: scale(1.4) rotateX(90deg);
		position: absolute;
		width: 600%;
		margin-left: -250%;
	}

	.grid::after {
		animation: 2.4s dash linear infinite;
		background-image:
			linear-gradient(180deg,
				rgba(54, 226, 248, 0.25) 0%,
				rgba(54, 226, 248, 0.25) 3px,
				rgba(0, 0, 0, 0) 0px),
			linear-gradient(90deg,
				rgba(54, 226, 248, 0.25) 0%,
				rgba(54, 226, 248, 0.25) 3px,
				rgba(0, 0, 0, 0) 0px);
		background-size: 60rpx 60rpx;
		background-color: rgba(2, 40, 45, 0.5);
		background-position: 0% 100%;
		content: "";
		width: 10%;
		height: 100%;
		position: absolute;
		bottom: 0;
		left: 50%;
		border: 2rpx solid rgba(54, 226, 248, 0.25);
		transform: translateX(-50%);
	}

	@keyframes dash {
		from {
			background-position: 0% 0%;
		}

		to {
			background-position: 0% 100%;
		}
	}

	.car {
		animation: 3s sway ease-in-out infinite;
		height: 140rpx;
		left: 50%;
		perspective: 200rpx;
		position: absolute;
		width: 200rpx;
		top: 55.5%;
		margin-left: -120rpx;
		z-index: 2;
	}

	.car::before {
		animation: 3s skew ease-in-out infinite;
		content: "";
		width: 110%;
		height: 100%;
		background: rgba(0, 0, 0, 0.6);
		position: absolute;
		top: 75%;
		left: 50%;
		transform: rotateX(60deg) translateX(-50%);
		z-index: 0;
	}

	.car__mirror {
		border-radius: 5rpx;
		position: absolute;
		top: 30%;
		background: $car-color;
		width: 24rpx;
		height: 16rpx;
	}

	.car__mirror::after {
		border-radius: 2rpx;
		content: "";
		position: absolute;
		left: 2rpx;
		right: 2rpx;
		top: 2rpx;
		bottom: 2rpx;
		background: $window-color;
	}

	.car__mirror::before {
		background-color: inherit;
		background-image: linear-gradient(rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.2));
		content: "";
		width: 32rpx;
		height: 8rpx;
		top: 90%;
		left: 80%;
		position: absolute;
		transform: rotate(30deg);
		z-index: 0;
	}

	.car__mirror--left {
		left: 5%;
	}

	.car__mirror--right {
		right: 5%;
		transform: scaleX(-1);
	}

	.car__wheel {
		animation: wheels 3s ease-in-out infinite;
		border-radius: 5rpx;
		position: absolute;
		height: 30%;
		bottom: 0;
		background: #010101;
		width: 22%;
	}

	.car__wheel::after,
	.car__wheel::before {
		animation: 1s dash linear infinite reverse;
		width: 10%;
		height: 100%;
		content: "";
		position: absolute;
		background-color: rgba(255, 255, 255, 0.025);
		background-image: linear-gradient(180deg, rgba(0, 0, 0, 0) 0px, rgba(255, 255, 255, 0.025) 0%, rgba(255, 255, 255, 0.025) 2px, rgba(0, 0, 0, 0) 0px);
		background-size: 4px 6px;
		top: 0;
	}

	.car__wheel::after {
		left: 10%;
	}

	.car__wheel::before {
		right: 10%;
	}

	.car__wheel--left {
		animation-delay: -1.5s;
		left: 2.5%;
	}

	.car__wheel--right {
		right: 2.5%;
	}

	.car__roof {
		border-radius: 16rpx 16rpx 0 0;
		background-color: $car-color;
		background-image: linear-gradient(transparent, rgba(0, 0, 0, 0.5) 70%);
		bottom: 45%;
		left: 50%;
		width: 60%;
		height: 45%;
		position: absolute;
		transform: rotateX(50deg) translateX(-50%);
	}

	.car__roof:after {
		background: $window-color;
		border-radius: 16rpx 16rpx 0 0;
		position: absolute;
		content: "";
		height: 70%;
		bottom: 5%;
		left: 50%;
		width: 80%;
		transform: translateX(-50%);
	}

	.car__roof:before {
		background: rgba(255, 255, 255, 0.4);
		border-radius: 16rpx;
		position: absolute;
		content: "";
		height: 10%;
		top: 5%;
		left: 50%;
		width: 80%;
		transform: translateX(-50%);
	}

	.car__bumper {
		border-radius: 160rpx 160rpx 108rpx 108rpx;
		background: #111;
		position: absolute;
		bottom: 10%;
		height: 35%;
		overflow: hidden;
		width: 90%;
		left: 5%;
	}

	.car__bumper::after {
		background: rgba(255, 255, 255, 0.05);
		height: 20%;
		content: "";
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
	}

	.car__back {
		border-radius: 108rpx 108rpx 64rpx 64rpx;
		background: $car-color;
		position: absolute;
		bottom: 25%;
		height: 35%;
		overflow: hidden;
		width: 95%;
		left: 2.5%;
	}

	.car__back::after {
		background: rgba(0, 0, 0, 0.2);
		height: 30%;
		content: "";
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
	}

	.car__back:before {
		background: rgba(255, 255, 255, 0.2);
		border-radius: 2rpx;
		position: absolute;
		content: "";
		height: 10%;
		top: 5%;
		left: 50%;
		width: 60%;
		transform: translateX(-50%);
	}

	.car__light {
		background-color: red;
		background-position: -2rpx -2rpx, -8rpx -8rpx;
		background-image: radial-gradient(white 10%, transparent 11%), radial-gradient(white 10%, transparent 11%);
		border-radius: 50%;
		border: 2rpx solid #8b0000;
		box-shadow: inset 0 0 0 5rpx rgba(255, 165, 0, 0.8), 0 0 5rpx rgba(255, 0, 0, 0.8), 0 0 6rpx 3rpx #fb492b inset, 0 0 15rpx 1rpx #fb492b;
		padding-top: 14%;
		width: 15%;
		position: absolute;
		transform: translateY(-50%);
		top: 50%;
		z-index: 2;
	}

	.car__light--small {
		background: white;
		padding-top: 9.5%;
		top: 65%;
		width: 11%;
		z-index: 3;
	}

	.car__light--left {
		left: 5%;
	}

	.car__light--small.car__light--left {
		left: 15%;
	}

	.car__light--right {
		right: 5%;
	}

	.car__light--small.car__light--right {
		right: 15%;
	}

	.car__grill {
		background-color: #222;
		background-image: linear-gradient(#111 2rpx, transparent 2rpx);
		background-size: 100% 3rpx;
		// background-repeat: no-repeat;
		border-radius: 2rpx;
		position: absolute;
		height: 50%;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		width: 80%;
		z-index: 1;
	}

	.car__emblem {
		border: 1px solid white;
		height: 13rpx;
		width: 13rpx;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%) rotate(45deg);
	}

	.car__emblem::after {
		content: "";
		position: absolute;
		width: 5rpx;
		height: 5rpx;
		background: white;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
	}

	.car__plate {
		border-radius: 3rpx;
		border: 2rpx solid #333;
		box-shadow: 0 4rpx 2rpx -2rpx rgba(0, 0, 0, 0.6);
		height: 10%;
		position: absolute;
		background: yellow;
		left: 50%;
		bottom: 15%;
		padding: 2% 0;
		transform: translateX(-50%);
		width: 28%;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.synth {
		font-weight: bold;
		letter-spacing: 0.1rpx;
		font-size: 12rpx;
		transform: scale(0.5);
		text-align: center;
	}

	.car__exhaust {
		background: black;
		border: 3.2rpx solid rgba(255, 255, 255, 0.2);
		bottom: 12.5%;
		position: absolute;
		border-radius: 50%;
		height: 20rpx;
		width: 20rpx;
	}

	.car__exhaust::after {
		background: inherit;
		border: inherit;
		position: absolute;
		content: "";
		height: inherit;
		width: inherit;
		top: -3.2rpx;
		border-radius: inherit;
	}

	.car__exhaust--left {
		left: 12.5%;
	}

	.car__exhaust--left::after {
		left: 100%;
	}

	.car__exhaust--right {
		right: 12.5%;
	}

	.car__exhaust--right::after {
		right: 100%;
	}

	@keyframes sway {

		0%,
		100% {
			transform: translateX(-40%) rotateZ(-4deg) rotateY(-0.5deg);
		}

		50% {
			transform: translateX(40%) rotateZ(4deg) rotateY(0.5deg);
		}
	}

	.diamond {
		animation: 8s fly-forward linear infinite;
		color: white;
		border: 1rpx solid currentColor;
		box-shadow: 0 0 20rpx currentColor;
		height: 13rpx;
		width: 13rpx;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%) rotate(45deg);
	}

	.diamond:nth-of-type(4) {
		animation-delay: -4s;
	}

	.diamond:nth-of-type(5) {
		animation-delay: -5s;
	}

	.diamond:nth-of-type(6) {
		animation-delay: -6s;
	}

	.diamond:nth-of-type(7) {
		animation-delay: -7s;
	}

	.diamond:nth-of-type(8) {
		animation-delay: -8s;
	}

	.circle {
		animation: 8s fly-forward-circle linear infinite;
		animation-delay: 5.5s;
		border-radius: 50%;
		color: $error-color;
		border: 1px solid currentColor;
		box-shadow: 0 0 20rpx currentColor;
		height: 13rpx;
		width: 13rpx;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: scale(0) translate(-50%, -50%) rotate(45deg);
	}

	@keyframes fly-forward {
		0% {
			transform: scale(0) translate(-50%, -50%) rotate(0deg);
		}

		22% {
			color: white;
		}

		23% {
			color: $success-color;
		}

		100% {
			transform: scale(4) translate3D(-50%, -50%, 200rpx) rotate(560deg);
		}
	}

	@keyframes fly-forward-circle {
		0% {
			transform: scale(0) translate(-50%, -50%) rotate(0deg);
		}

		100% {
			transform: scale(2) translate3D(-50%, -50%, 200rpx) rotate(560deg);
		}
	}
</style>
