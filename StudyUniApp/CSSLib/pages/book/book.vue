<template>
	<view class="book preserve-3d">
		<!-- 书的最后一页 -->
		<view class="book-page-box book-page-4 preserve-3d">
				<view>第三页</view>
		</view>
		<!-- 书的第三页 -->
		<view class="book-page-box book-page-3 preserve-3d flip-animation-3">
				<view>第二页</view>
		</view>
		<!-- 书的第二页 -->
		<view class="book-page-box book-page-2 preserve-3d flip-animation-2">
				<view>第一页</view>
		</view>
		<!-- 书的封面 -->
		<view class="book-page-box book-page-1 preserve-3d flip-animation-1">
			<view>翻页特效</view>
			<view>flip-book</view>
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
	body {
		/*perspective 属性指定了观察者与z=0平面的距离，使具有三维位置变换的元素产生透视效果。
			z>0的三维元素比正常大，而z<0时则比正常小，
			大小程度由该属性的值决定。默认情况下，消失点位于元素的中心，
			但是可以通过设置perspective-origin属性来改变其位置。*/
		perspective: 1000rpx;
		background-color: #212121;
		font-family: '微软雅黑';
	}

	.book {
		position: absolute;
		top: 30%;
		left: 50%;
		width: 300rpx;
		height: 300rpx;
		background-color: #fff;
		transform: rotateX(30deg);
		font-size: 30rpx;
	}

	.preserve-3d {
		transform-style: preserve-3d;
	}

	.book-page-box {
		transform-origin: 0 50%;
		transform: rotateY(0deg);
		position: absolute;
		top: 0;
		left: 0;
		width: 300rpx;
		height: 300rpx;
		border: 1rpx solid #1976D2;
		text-align: center;
	}

	@keyframes flipBook1 {
		0% {
			transform: rotateY(0deg);
		}

		50% {
			transform: rotateY(-160deg);
		}

		100% {
			transform: rotateY(0deg);
		}
	}

	@keyframes flipBook2 {
		0% {
			transform: rotateY(0deg);
		}

		50% {
			transform: rotateY(-150deg);
		}

		100% {
			transform: rotateY(0deg);
		}
	}

	@keyframes flipBook3 {
		0% {
			transform: rotateY(0deg);
		}

		50% {
			transform: rotateY(-140deg);
		}

		100% {
			transform: rotateY(0deg);
		}
	}

	/*书的封面*/
	.book-page-1 {
		background-color: #1976D2;
		display: flex;
		flex-direction: column;
		color: #fff;
		align-items: center;
		justify-content: center;
	}

	.flip-animation-1 {
		animation: flipBook1 17s;
	}

	/*书的第二页*/
	.book-page-2 {
		background-color: #fff;
		display: flex;
		flex-direction: column;
		color: #1976D2;
		align-items: center;
		justify-content: center;
	}

	.flip-animation-2 {
		animation: flipBook2 13s 2s;
	}

	/*书的第三页*/
	.book-page-3 {
		background-color: #fff;
		display: flex;
		flex-direction: column;
		color: #1976D2;
		align-items: center;
		justify-content: center;
	}

	.flip-animation-3 {
		animation: flipBook3 10s 3s;
	}

	/*书的第四页*/
	.book-page-4 {
		color: #1976D2;
		display: flex;
		flex-direction: column;
		color: #1976D2;
		align-items: center;
		justify-content: center;
	}
</style>
