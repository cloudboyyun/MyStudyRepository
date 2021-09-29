<template>
	<view class='main'>
		<view class='helix'>
			<view v-for="(item,index) in 14" :key='index'>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {

		}
	}
</script>

<style lang='scss'>
	.main {
		width: 300rpx;
		height: 1000rpx;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		perspective: 5120rpx;
		/* background-color: #F0AD4E; */

		.helix {
			view {
				width: 200rpx;
				height: 60rpx;
				margin: auto;
				position: relative;
				animation: revolve 2s reverse linear infinite;
				transform-style: preserve-3d;
				transform-origin: center center 0;

				&:after,
				&:before {
					content: "";
					height: 40rpx;
					width: 40rpx;
					border-radius: 50%;
					background: red;
					position: absolute;
					animation: revolve 2s linear infinite;
				}

				&:before {
					left: 0;
					background: #68B0AB;
				}

				&:after {
					right: 0;
					background: #8FC0A9;
				}
				
				&:nth-child(1) {
					animation-delay: 0.15s;
				}
				
				@for $i from 1 to 14 {
					&:nth-child(#{$i}) {
						animation-delay: $i*0.15s;
						&:before,
						&:after {
							animation-delay: $i*0.15s;
						}
					}
				}
			}
		}

		@keyframes revolve {
			from {
				transform: rotateY(360deg);
			}

			to {
				transform: rotateY(0);
			}
		}
	}
</style>
