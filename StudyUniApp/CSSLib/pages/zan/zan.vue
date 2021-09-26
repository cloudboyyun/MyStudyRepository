<template>
	<view class='container'>
		<view>
			<view class="heart" @click="onClick" 
				:style="{'background-position': isLike?'left' : 'right'}"
				:class="{'heartAnimation':isHeartAnimation}"></view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isHeartAnimation: false,
				isLike: true
			}
		},
		methods: {
			onClick() {
				console.log(this.isLike);
				if(this.isLike) {
					this.isHeartAnimation = true;
					this.isLike = false;
				} else {
					this.isHeartAnimation = false;
					this.isLike = true;
				}
			}
		}
	}
</script>

<style>
	.container {
		margin: 20rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: 100%;
	}

	.heart {
		background: url(/static/images/web_heart_animation.png);
		/* background-position: left; */
		background-repeat: no-repeat;
		height: 200rpx;
		width: 200rpx;
		cursor: pointer;
		background-size: 2900%;
	}

	.heart:hover,
	.heart:focus {
		background-position: right;
	}

	@keyframes heartBlast {
		0% {
			background-position: left;
		}
		100% {
			background-position: right;
		}
	}

	.heartAnimation {
		display: inline-block;
		animation-name: heartBlast;
		animation-duration: 0.8s;
		animation-iteration-count: 1;
		animation-timing-function: steps(28);
		background-position: right;
	}
</style>
