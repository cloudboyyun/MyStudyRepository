<template>
	<view class="content">
		<uni-list>
		    <uni-list-item clickable title="环装Loading"
					@click="toPage('/pages/huanzhuangLoading/huanzhuangLoading')"
					 ></uni-list-item>
				<uni-list-item clickable title="纯css3云彩动画效果" 
					@click="toPage('/pages/cloud/cloud')"></uni-list-item>
				<uni-list-item clickable title="纯css3翻书效果"
					@click="toPage('/pages/book/book')"></uni-list-item>
				<uni-list-item clickable title="纯css制作的图形"
					@click="toPage('/pages/CSSShapes/CSSShapes')"></uni-list-item>
				<uni-list-item clickable title="球体"
					@click="toPage('/pages/ball/ball')"></uni-list-item>
				<uni-list-item clickable title="perspective测试"
					@click="toPage('/pages/PerspectiveTest/PerspectiveTest')"></uni-list-item>
				<uni-list-item clickable title="泡泡"
					@click="toPage('/pages/paopao/paopao')"></uni-list-item>
				<uni-list-item clickable title="飘荡的泡泡"
					@click="toPage('/pages/movingPaopao/movingPaopao')"></uni-list-item>
				<uni-list-item clickable title="点赞效果"
					@click="toPage('/pages/zan/zan')"></uni-list-item>
				<uni-list-item clickable title="转圈圈"
					@click="toPage('/pages/circling/circling')"></uni-list-item>
				<uni-list-item clickable title="太阳地球和月亮"
					@click="toPage('/pages/sun/sun')"></uni-list-item>
				<uni-list-item clickable title="眼睛守望者"
					@click="toPage('/pages/eyeHolder/eyeHolder')"></uni-list-item>
				<uni-list-item clickable title="测试"
					@click="toPage('/pages/test/test')"></uni-list-item>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {
			toPage(url) {
				uni.navigateTo({
					url: url
				})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding-top: 20rpx;
	}
</style>
