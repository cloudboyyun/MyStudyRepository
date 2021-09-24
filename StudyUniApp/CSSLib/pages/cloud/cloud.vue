<template>
	<view class='sky'>
		<!-- <view class='clouds_one'></view> -->
		<!-- <view class='clouds_two'></view> -->
		<view class='clouds_three'></view>
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
	.sky {
		height: 480px;
		background: #007fd5;
		position: relative;
		overflow: hidden;
		animation: sky_background 50s ease-out infinite;
		transform: translate3d(0, 0, 0);
	}

	@keyframes sky_background {
		0% {
			background: #007fd5;
			color: #007fd5
		}

		50% {
			background: #000;
			color: #a3d9ff
		}

		100% {
			background: #007fd5;
			color: #007fd5
		}
	}
	
	.sky .clouds_one {
	  background: url("/static/images/cloud_one.png");
	  position: absolute;
	  left: 0;
	  top: 0;
	  height: 100%;
	  width: 300%;
	  animation: cloud 50s linear infinite;
	  transform: translate3d(0, 0, 0);
	}
	
	@keyframes cloud {
	  0% {
		left: 0
	  }
	  100% {
		left: -200%
	  }
	}
	
	.sky .clouds_two {
	  background: url("/static/images/cloud_two.png");
	  position: absolute;
	  left: 0;
	  top: 0;
	  height: 100%;
	  width: 300%;
	  animation: cloud 75s linear infinite;
	  transform: translate3d(0, 0, 0);
	}
	
	.sky .clouds_three {
	  background: url("/static/images/cloud_three.png");
	  position: absolute;
	  left: 0;
	  top: 0;
	  height: 100%;
	  width: 300%;
	  animation: cloud 100s linear infinite;
	  transform: translate3d(0, 0, 0);
	}
</style>
