<template>
	<view class='main'>
		<view class="iconfont icon-shuye leaf"></view>
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
		width: 500rpx;
		height: 500rpx;
		border: 1rpx #000000 solid;
		margin-top: 50rpx;
		margin-left: auto;
		margin-right: auto;
		position: relative;
	}
	
	.leaf {
		position: absolute;
		left: 60%;
		top: -10rpx;
		font-size: 50rpx;
		transform: rotateZ(90deg);
		/* color: #f47983; */
		color: green;
		animation: left-left 20s linear infinite,leaf-down 20s cubic-bezier(.30,.1,1,.41) infinite;
	}
	
	@keyframes left-left {
		0% {
		  left: 60%;
		}
		50% {
			left: 60%;
		}
		100% {
		  left: 10%;
		}
	}
	
	@keyframes leaf-down {
	  0% {
			transform: rotateZ(90deg) scale(0);
	  }
		25% {
			transform: rotateZ(90deg) scale(1);
			color: green;
		}
		50% {
			transform: rotateZ(90deg) scale(1);
			top: 0%;
			color: #d07b04;
		}
		90% {
			opacity: 0;
			color: yellow;
		}
	  100% {
			opacity: 0;
	    top: 100%;
	  }
	}
</style>
