<template>
	<view class='main'>
		<view class='wrapper'>
			<view class='grid'></view>
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
		/* background: #000000; */
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		perspective: 50rpx;
	}
	
	.wrapper {
		width: 500rpx;
		height: 500rpx;
		background: #000000;
		transform: rotateX(10deg);
		
	}
	
	.grid {
		animation: 2.4s dash linear infinite;
		width: 100%;
		height: 100%;
		/* background: #0000CC; */
		background-image:
			linear-gradient(180deg,
				red 0%,
				red 3px,
				transparent 0px),
			linear-gradient(90deg,
				blue 0%,
				blue 3px,
				transparent 0px);
		background-size: 60rpx 60rpx;
		background-color: rgba(2, 40, 45, 0.5);
		border: 2rpx solid rgba(54, 226, 248, 0.25);
	}
	
	@keyframes dash {
		from {
			background-position: 0% 0%;
		}
	
		to {
			background-position: 0% 100%;
		}
	}
</style>
