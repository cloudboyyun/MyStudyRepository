<template>
	<view class="content">
		<scroll-view scroll-y="true" class='scroll-view'>
			<uni-list>
			    <uni-list-item clickable title="1. 环装Loading"
						@click="toPage('/pages/huanzhuangLoading/huanzhuangLoading')"></uni-list-item>
					<uni-list-item clickable title="2. 纯css3云彩动画效果" 
						@click="toPage('/pages/cloud/cloud')"></uni-list-item>
					<uni-list-item clickable title="3. 纯css3翻书效果"
						@click="toPage('/pages/book/book')"></uni-list-item>
					<uni-list-item clickable title="4. 纯css制作的图形"
						@click="toPage('/pages/CSSShapes/CSSShapes')"></uni-list-item>
					<uni-list-item clickable title="5. 球体"
						@click="toPage('/pages/ball/ball')"></uni-list-item>
					<uni-list-item clickable title="6. perspective测试"
						@click="toPage('/pages/PerspectiveTest/PerspectiveTest')"></uni-list-item>
					<uni-list-item clickable title="7. 泡泡"
						@click="toPage('/pages/paopao/paopao')"></uni-list-item>
					<uni-list-item clickable title="8. 飘荡的泡泡"
						@click="toPage('/pages/movingPaopao/movingPaopao')"></uni-list-item>
					<uni-list-item clickable title="9. 点赞效果"
						@click="toPage('/pages/zan/zan')"></uni-list-item>
					<uni-list-item clickable title="10. 转圈圈"
						@click="toPage('/pages/circling/circling')"></uni-list-item>
					<uni-list-item clickable title="11. 太阳地球和月亮"
						@click="toPage('/pages/sun/sun')"></uni-list-item>
					<uni-list-item clickable title="12. 眼睛守望者"
						@click="toPage('/pages/eyeHolder/eyeHolder')"></uni-list-item>
					<uni-list-item clickable title="13. 测试"
						@click="toPage('/pages/test/test')"></uni-list-item>
					<uni-list-item clickable title="14. 落叶"
						@click="toPage('/pages/leaf/leaf')"></uni-list-item>
					<uni-list-item clickable title="15. 历史的天空"
						@click="toPage('/pages/historySky/historySky')"></uni-list-item>
					<uni-list-item clickable title="16. 螺旋"
						@click="toPage('/pages/luoxuan/luoxuan')"></uni-list-item>
					<uni-list-item clickable title="17. 3DTxt"
						@click="toPage('/pages/3DTxt/3DTxt')"></uni-list-item>
					<uni-list-item clickable title="18. 太阳月亮"
						@click="toPage('/pages/sunMoon/sunMoon')"></uni-list-item>
					<uni-list-item clickable title="19. 纯CSS简易云彩"
						@click="toPage('/pages/singleCloud/singleCloud')"></uni-list-item>
					<uni-list-item clickable title="20. 纯CSS简易小鸟"
						@click="toPage('/pages/bird/bird')"></uni-list-item>
					<uni-list-item clickable title="21. 纯CSS简易小帆船"
						@click="toPage('/pages/boat/boat')"></uni-list-item>
					<uni-list-item clickable title="21. border"
						@click="toPage('/pages/border/border')"></uni-list-item>
			</uni-list>
		</scroll-view>
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

<style scoped>
	page {
		width: 100%;
		height: 100%;
	}
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: 100%;
		height: 70%;
	}
	
	.scroll-view {
		margin: 20rpx;
		height: 100%;
		border: 1rpx #0000CC solid;
	}
	
/deep/	.uni-list-item__content-title {
		font-size: 20rpx;
	}
	
</style>
