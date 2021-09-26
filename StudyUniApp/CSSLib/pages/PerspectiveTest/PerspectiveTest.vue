<template>
	<view class='main'>
		<view>
			<view>正常放置，无变化</view>
			<img src="/static/images/sample.jpg"></img>
		</view>
		<view>
			<view>rotateX: 以X轴为轴进行旋转</view>
			<img src="/static/images/sample.jpg" class="myRotateX"></img>
		</view>
		<view>
			<view>rotateY: 以Y轴为轴进行旋转</view>
			<img src="/static/images/sample.jpg" class="myRotateY"></img>
		</view>
		<view>
			<view>rotateZ: 以Z轴为轴进行旋转</view>
			<img src="/static/images/sample.jpg" class="myRotateZ"></img>
		</view>
		<view>
			<view>rotate: 只带一个参数的rotate, 相当于rotateZ, 即以Z轴为轴进行旋转</view>
			<img src="/static/images/sample.jpg" class="myRotate"></img>
		</view>
		
		<view class="myPerspective">
			<view>z轴朝着屏幕，图片推向你所在的方向</view>
			<img src="/static/images/sample.jpg" class="myTranslateZ"></img>
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

<style lang='scss'>
	.main {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		view {
			view {
				font-size: 20rpx;
				margin-bottom: 20rpx;
			}
			img {
				width: 200rpx;
				height: 200rpx;
			}
			.myRotateX {
				transform: rotateX(60deg);
			}
			.myRotateY {
				transform: rotateY(60deg);
			}
			.myRotateZ {
				transform: rotateZ(5deg);
			}
			.myRotate {
				transform: rotate(5deg);
			}
		}
	}
	.myPerspective {
		perspective:300px;
		perspective-origin: center center;
	}
	.myTranslateZ {
		transform:translateZ(40px);
	}
</style>
