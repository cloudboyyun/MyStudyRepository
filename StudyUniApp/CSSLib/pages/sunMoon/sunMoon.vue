<template>
	<view class="container-wrapper" :class="{'container-night': isNight}">
		<view class="container">
			<view class="circle-container">
				<view class="sky">
					<view class="sky-night" :class="{'sky-night-fade': isNight}">
						<view class="stars" :class="{'stars-fade': isNight}">
							<view class="shooting-star" :class="{'shooting': isNight}"></view>
							<view class="star-group-1"></view>
							<view class="star-group-2"></view>
							<view class="star-group-3"></view>
							<view class="star-group-4"></view>
						</view>
					</view>
					<view class="cloud cloud-1" :class="{'cloud-fade': isNight}"></view>
					<view class="cloud cloud-2" :class="{'cloud-fade': isNight}"></view>
					<view class="cloud cloud-3" :class="{'cloud-fade': isNight}"></view>
					<view class="cloud cloud-4" :class="{'cloud-fade': isNight}"></view>
					<view class="cloud cloud-5" :class="{'cloud-fade': isNight}"></view>
					<view class="cloud cloud-6" :class="{'cloud-fade': isNight}"></view>
					<view class="bird bird-1" :class="{'birds-fly': isNight}"></view>
					<view class="bird bird-2" :class="{'birds-fly': isNight}"></view>
					<view class="bird bird-3" :class="{'birds-fly': isNight}"></view>
				</view>

				<view class="mountains">
					<view class="mountain-back">
						<view class="mountain-back-1"></view>
						<view class="mountain-back-2"></view>
					</view>
					<view class="mountain-middle">
						<view class="mountain-middle-1"></view>
						<view class="mountain-middle-2"></view>
					</view>
					<view class="mountain-top">
						<view class="mountain-top-1"></view>
					</view>
				</view>

				<view class="sun-container" :style="{transform: 'rotate(' + sunDeg + 'deg)'}" @click='onSunClick'>
					<view class="sun"></view>
				</view>

				<view class="ocean" :class="{'animation-stop': isNight}">
					<view class="boat" :class="{'boat-sail': isNight}"></view>
					<view class="ocean-night" :class="{'ocean-night-fade': isNight}">
						<view class="moon" :class="{'moon-fade': isNight}"></view>
						<view class="mountains mountains-bottom">
							<view class="mountain-back">
								<view class="mountain-back-1"></view>
								<view class="mountain-back-2"></view>
							</view>
							<view class="mountain-middle">
								<view class="mountain-middle-1"></view>
								<view class="mountain-middle-2"></view>
							</view>
							<view class="mountain-top">
								<view class="mountain-top-1"></view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isNight: false,
				sunDeg: 0
			}
		},
		methods: {
			onSunClick() {
				console.log("onSunClick");
				this.isNight = !this.isNight;
				this.sunDeg += 360;
			}
		}
	}
</script>

<style>
	/* @font-face {
	  font-family: 'Sanchez';
	  font-style: normal;
	  font-weight: 400;
	  src: url(/static/fonts/Ycm2sZJORluHnXbIfmdR_SwE.woff2) format('woff2');
	  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
	} */
	/* @font-face {
	  font-family: 'Sanchez';
	  font-style: normal;
	  font-weight: 400;
	  src: url(/static/fonts/Ycm2sZJORluHnXbIfmlR_Q.woff2) format('woff2');
	  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
	} */
	
	*:before,
	*:after {
	  content: "";
	  position: absolute;
	}
	
	page {
		width: 100%;
		height: 100%;
	}
	
	.container-wrapper {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		transition: background-color 3s ease;
		transition-delay: 2s;
		background-color: #fff7f4;
	}

	.container {
		width: 450rpx;
		height: 450rpx;
		position: relative;
	}

	.container-night {
		background-color: #e4ecff;
	}

	.circle-container {
		width: 450rpx;
		height: 450rpx;
		clip-path: circle(50% at 50% 50%);
		position: absolute;
		overflow: hidden;
		z-index: 100;
	}

	.sky {
		position: absolute;
		width: 100%;
		height: 55%;
		top: 0;
		background: linear-gradient(#feb8b0, #fef9db);
		z-index: 1;
		overflow: hidden;
	}

	.sky-night {
		position: absolute;
		width: 100%;
		height: 100%;
		background: linear-gradient(#4c3f6d, #6c62bb, #93b1ed);
		opacity: 0;
		transition: opacity 3s;
		transition-delay: 2s;
	}

	.sky-night-fade {
		opacity: 1;
	}

	@keyframes sky-night {
		0% {
			background: linear-gradient(#feb8b0, #fef9db);
		}
		100% {
			background: linear-gradient(#4c3f6d, #96b5ee);
		}
	}

	.sun-container {
		z-index: 10;
		position: absolute;
		left: 0;
		right: 0;
		margin: auto;
		top: 0;
		bottom: 0;
		width: 290rpx;
		height: 290rpx;
		transform-origin: center center;
		transition: all 5s ease-in-out;
	}
	
	.sun {
		position: absolute;
		width: 90rpx;
		height: 90rpx;
		border-radius: 50%;
		background: linear-gradient(#fefefe, #fffbe8);
		cursor: pointer;
		z-index: 10;
	}

	.boat {
		position: absolute;
		width: 50rpx;
		height: 0rpx;
		border-top: 7rpx solid #bb7074;
		border-left: 10rpx solid transparent;
		border-right: 10rpx solid transparent;
		left: 280rpx;
		top: -5rpx;
		transform-origin: center bottom;
		animation: boat 5s infinite;
		animation-delay: 0.25s;
		transition: all 4s ease-in-out;
	}

	.boat-sail {
		margin-left: -350rpx;
	}

	.boat,
	.boat:before,
	.boat:after {
		position: absolute;
		z-index: 200;
	}

	@keyframes boat {
		0% {
			transform: rotate(1deg)translate(2px, 0px);
		}

		25% {
			transform: rotate(-1deg) translate(-2px, -2px);
		}

		50% {
			transform: rotate(1deg) translate(1px, 0px);
		}

		75% {
			transform: rotate(-1deg) translate(-1px, -2px);
		}

		100% {
			transform: rotate(1deg) translate(2px, 0px);
		}
	}

	.boat:before,
	.boat:after {
		width: 0;
		height: 0;
		border: 0 solid transparent;
	}

	.boat:before {
		bottom: 8rpx;
		right: -6rpx;
		border-right-width: 15rpx;
		border-left-width: 0rpx;
		border-bottom: 30rpx solid #bb7074;
	}

	.boat:after {
		bottom: 8rpx;
		right: 11rpx;
		border-right-width: 0rpx;
		border-left-width: 20rpx;
		border-bottom: 48rpx solid #bb7074;
	}

	.ocean {
		position: absolute;
		width: 100%;
		height: 45%;
		bottom: 0;
		background: #ceefe8;
		z-index: 200;
		animation: ocean-top 3s infinite;
		opacity: 1;
		transition: all 3s ease;
		transition-delay: 2s;
		transform-origin: top;
	}

	.animation-stop {
		animation: ocean-top 3s forwards;
		height: 42%;
	}

	.ocean-night {
		position: relative;
		width: 100%;
		height: 100%;
		background: linear-gradient(#8da6e6, #788cc9);
		opacity: 0;
		transition: opacity 3s;
		transition-delay: 2s;
		z-index: 500;
	}

	.ocean-night-fade {
		opacity: 1;
	}

	.moon {
		position: absolute;
		width: 90rpx;
		height: 90rpx;
		border-radius: 50%;
		background: linear-gradient(#fefefe, #fffbe8);
		opacity: 0;
		left: 80rpx;
		margin-top: 90rpx;
		transition: opacity 1s ease;
	}

	.moon-fade {
		opacity: 0.3;
		transition-delay: 4s;
	}

	.ocean:before,
	.ocean:after {
		width: 100%;
		bottom: 0;
		position: absolute;
		z-index: 200;
	}

	.ocean:before {
		height: 75%;
		background: #baeced;
		animation: ocean-middle 3s infinite;
	}

	.ocean:after {
		height: 50%;
		background: #ace6f1;
		animation: ocean-bottom 2s infinite;
	}

	@keyframes ocean-top {
		0% {
			transform: rotate(0deg);
		}

		25% {
			transform: rotate(-1deg);
		}

		50% {
			transform: rotate(0deg);
		}

		75% {
			transform: rotate(1deg);
		}

		100% {
			transform: rotate(0deg);
		}
	}

	@keyframes ocean-middle {
		0% {
			height: 75%;
		}

		50% {
			height: 77%;
			transform: rotate(2deg);
		}

		100% {
			height: 75%;
		}
	}

	@keyframes ocean-bottom {
		0% {
			height: 50%;
		}

		50% {
			height: 52%;
			transform: rotate(-2deg);
		}

		100% {
			height: 50%;
		}
	}

	.bird:before,
	.bird:after {
		border: 2rpx solid #bb7074;
		border-bottom: 0;
		width: 18rpx;
		height: 8rpx;
		z-index: 200;
	}

	.bird {
		position: absolute;
		animation: bird 1s infinite;
		z-index: 200;
		transition: all 2s ease-in-out;
	}

	.birds-fly {
		margin-left: 150rpx;
		margin-top: -100rpx;
	}

	.bird:after {
		border-top-left-radius: 18rpx;
		border-top-right-radius: 18rpx;
		border-right: 0;
		animation: wing-right 1s infinite;
		transform-origin: bottom left;
	}

	.bird:before {
		left: -19rpx;
		border-top-right-radius: 18rpx;
		border-top-left-radius: 18rpx;
		border-left: 0;
		animation: wing-left 1s infinite;
		transform-origin: bottom right;
	}

	.bird-1 {
		left: 240rpx;
		top: 140rpx;
		transform: scale(0.9)rotate(10deg);
		animation-delay: 0.4s;
	}

	.bird-2 {
		left: 280rpx;
		top: 120rpx;
		transform: scale(0.8)rotate(-5deg);
		animation-delay: 0.2s;
	}

	.bird-3 {
		left: 250rpx;
		top: 100rpx;
		transform: scale(1)rotate(8deg);
	}

	@keyframes bird {
		0% {
			transform: translate(0px, 0px);
		}
		50% {
			transform: translate(2px, -3px);
		}
		100% {
			transform: translate(0px, 0px);
		}
	}

	@keyframes wing-right {
		0% {
			transform: rotate(0deg);
		}

		50% {
			transform: rotate(10deg);
		}

		100% {
			transform: rotate(0deg);
		}
	}

	@keyframes wing-left {
		0% {
			transform: rotate(0deg);
		}

		50% {
			transform: rotate(-10deg);
		}

		100% {
			transform: rotate(0deg);
		}
	}

	.mountains {
		position: absolute;
		width: 100%;
		height: 20%;
		bottom: 35%;
	}

	.mountains-bottom {
		opacity: 0.5;
		transform-origin: center;
		transform: scaleY(-1);
		top: 0;
		position: fixed;
		overflow: hidden;
		height: 31.5%;
	}

	.mountains view,
	.mountains view:after,
	.mountains view:before {
		border-radius: 50%;
		position: absolute;
		z-index: 200;
	}

	.mountains * {
		transition: background 3s ease;
		transition-delay: 2s;
	}

	.mountain-top,
	.mountain-top * {
		background: #fffcdd;
	}

	.mountain-top {
		width: 200rpx;
		height: 90rpx;
		top: 18rpx;
		right: 0;
		margin-right: -50rpx;
	}

	.mountain-top-1 {
		position: absolute;
		width: 250rpx;
		height: 80rpx;
		top: 15rpx;
		right: 0;
		margin-right: 150rpx;
	}

	.mountain-top-night {
		background: #614da2;
	}

	.mountain-middle,
	.mountain-middle * {
		background: #fee1cb;
	}

	.mountain-middle {
		width: 180rpx;
		height: 120rpx;
		top: 12rpx;
		margin-left: 180rpx;
	}

	.mountain-middle-1 {
		width: 200rpx;
		height: 100rpx;
		top: 3rpx;
		margin-left: -110rpx;
	}

	.mountain-middle-2 {
		width: 220rpx;
		height: 150rpx;
		margin-left: -250rpx;
	}

	.mountain-middle-night {
		background: #6154a4;
	}

	.mountain-back,
	.mountain-back * {
		background: #fec6b9;
	}

	.mountain-back {
		width: 180rpx;
		height: 120rpx;
		top: 0rpx;
	}

	.mountain-back-1 {
		width: 200rpx;
		height: 100rpx;
		top: 3rpx;
		margin-left: 80rpx;
	}

	.mountain-back-2 {
		width: 150rpx;
		height: 80rpx;
		margin-left: 250rpx;
	}

	.mountain-back-night {
		background: #7980c8;
	}

	.cloud {
		background: #fff;
		position: absolute;
		z-index: 500;
		width: 65rpx;
		height: 12rpx;
		border-radius: 20rpx;
		opacity: 1;
		animation: cloud 4s infinite;
		transition: opacity 3s ease;
	}
	
	.cloud-fade {
		opacity: 0;
	}
	
	.cloud:after {
		background: #fff;
		position: absolute;
		z-index: 500;
		border-radius: 50%;
		height: 30rpx;
		left: 27rpx;
		top: -18rpx;
		width: 30rpx;
	}
	
	.cloud:before {
		background: #fff;
		z-index: 500;
		border-radius: 50%;
		height: 20rpx;
		left: 12rpx;
		top: -11rpx;
		width: 20rpx;
	}

	@keyframes cloud {
		0% {
			transform: translate(0px, 0px);
		}

		25% {
			transform: translate(1px, 1px);
		}

		50% {
			transform: translate(0px, 0px);
		}

		75% {
			transform: translate(-1px, -1px);
		}

		100% {
			transform: translate(0px, 0px);
		}
	}

	.cloud-1 {
		left: 310rpx;
		top: 50rpx;
	}

	.cloud-2 {
		left: 280rpx;
		top: 150rpx;
		animation-delay: 0.2s;
	}

	.cloud-3 {
		left: 410rpx;
		top: 190rpx;
		animation-delay: 0.4s;
	}

	.cloud-4 {
		left: 160rpx;
		top: 120rpx;
		animation-delay: 0.6s;
	}

	.cloud-5 {
		left: 60px;
		top: 170px;
		animation-delay: 0.8s;
	}

	.cloud-6 {
		left: 0px;
		top: 100px;
		animation-delay: 1s;
	}

	.stars *,
	.stars *:before,
	.stars *:after {
		border-radius: 50%;
		background-color: #fff;
		position: absolute;
	}

	.stars {
		opacity: 0;
		transition: opacity 2s ease;
	}

	.stars-fade {
		opacity: 1;
		transition-delay: 4s;
	}

	.star-group-1 {
		width: 5rpx;
		height: 5rpx;
		left: 225rpx;
		top: 120rpx;
	}

	.star-group-1:before {
		width: 4rpx;
		height: 4rpx;
		left: 50rpx;
		top: 18rpx;
		opacity: 0.8;
	}

	.star-group-1:after {
		width: 3rpx;
		height: 3rpx;
		left: -35rpx;
		top: 35rpx;
		opacity: 1;
	}

	.star-group-2 {
		width: 5rpx;
		height: 5rpx;
		left: 25rpx;
		top: 175rpx;
	}

	.star-group-2:before {
		width: 4rpx;
		height: 4rpx;
		left: 40rpx;
		top: 14rpx;
		opacity: 0.8;
	}

	.star-group-2:after {
		width: 3rpx;
		height: 3rpx;
		left: 30rpx;
		top: -40rpx;
		opacity: 1;
	}

	.star-group-3 {
		width: 5rpx;
		height: 5rpx;
		left: 125rpx;
		top: 50rpx;
	}

	.star-group-3:before {
		width: 4rpx;
		height: 4rpx;
		left: 50rpx;
		top: 20rpx;
		opacity: 0.8;
	}

	.star-group-3:after {
		width: 3rpx;
		height: 3rpx;
		left: 90rpx;
		top: -20rpx;
		opacity: 1;
	}

	.star-group-4 {
		width: 5rpx;
		height: 5rpx;
		left: 300rpx;
		top: 100rpx;
	}

	.star-group-4:before {
		width: 3rpx;
		height: 3rpx;
		left: 80rpx;
		top: 60rpx;
		opacity: 0.8;
	}

	.star-group-4:after {
		width: 3rpx;
		height: 3rpx;
		left: 50rpx;
		top: -20rpx;
		opacity: 1;
	}

	.shooting-star {
		position: absolute;
		background: linear-gradient(to right,
				rgba(255, 255, 255, 1) 0%,
				rgba(255, 255, 255, 0) 100%);
		width: 35rpx;
		height: 3rpx;
		left: 310rpx;
		top: 10rpx;
		transform-origin: left;
		transform: rotate(-40deg);
	}

	.shooting {
		animation: shooting-star 2s 1 ease-in-out;
		animation-delay: 5s;
	}

	@keyframes shooting-star {
		100% {
			transform: rotate(-40deg)translateX(-450rpx);
		}
	}

</style>
