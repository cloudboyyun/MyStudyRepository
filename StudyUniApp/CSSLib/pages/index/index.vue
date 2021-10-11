<template>
	<view class="content">
		<!-- <scroll-view scroll-y="true" class='scroll-view'> -->
			<uni-card v-for="(item,index) in list" :key="index" mode="style" is-shadow="true"
				:title='item.title' @click="toPage(item.url)" :thumbnail="item.thumbnail"
				extra="" note=""></uni-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [
					{'title': '0. 测试',
						'url': '/pages/test/test',
						'thumbnail': '/static/thumbnail/0.png'
					},
					{'title': '1. 环装Loading',
						'url': '/pages/huanzhuangLoading/huanzhuangLoading',
						'thumbnail': '/static/thumbnail/1.png'
					},
					{'title': '2. 纯css3云彩动画效果',
						'url': '/pages/cloud/cloud',
						'thumbnail': '/static/thumbnail/2.png'
					},
					{'title': '3. 纯css3翻书效果',
						'url': '/pages/book/book',
						'thumbnail': '/static/thumbnail/3.png'
					},
					{'title': '4. 纯css制作的图形',
						'url': '/pages/CSSShapes/CSSShapes',
						'thumbnail': '/static/thumbnail/4.png'
					},
					{'title': '5. 球体',
						'url': '/pages/ball/ball',
						'thumbnail': '/static/thumbnail/5.png'
					},
					{'title': '6. perspective测试',
						'url': '/pages/PerspectiveTest/PerspectiveTest',
						'thumbnail': '/static/thumbnail/6.png'
					},
					{'title': '7. 泡泡',
						'url': '/pages/paopao/paopao',
						'thumbnail': '/static/thumbnail/7.png'
					},
					{'title': '8. 飘荡的泡泡',
						'url': '/pages/movingPaopao/movingPaopao',
						'thumbnail': '/static/thumbnail/8.png'
					},
					{'title': '9. 点赞效果',
						'url': '/pages/zan/zan',
						'thumbnail': '/static/thumbnail/9.png'
					},
					{'title': '10. 转圈圈',
						'url': '/pages/circling/circling',
						'thumbnail': '/static/thumbnail/10.png'
					},
					{'title': '11. 太阳地球和月亮',
						'url': '/pages/sun/sun',
						'thumbnail': '/static/thumbnail/11.png'
					},
					{'title': '12. 眼睛守望者',
						'url': '/pages/eyeHolder/eyeHolder',
						'thumbnail': '/static/thumbnail/12.png'
					},
					{'title': '13. border',
						'url': '/pages/border/border',
						'thumbnail': '/static/thumbnail/13.png'
					},
					{'title': '14. 落叶',
						'url': '/pages/leaf/leaf',
						'thumbnail': '/static/thumbnail/14.png'
					},
					{'title': '15. 历史的天空',
						'url': '/pages/historySky/historySky',
						'thumbnail': '/static/thumbnail/15.png'
					},
					{'title': '16. 螺旋',
						'url': '/pages/luoxuan/luoxuan',
						'thumbnail': '/static/thumbnail/16.png'
					},
					{'title': '17. 3DTxt',
						'url': '/pages/3DTxt/3DTxt',
						'thumbnail': '/static/thumbnail/17.png'
					},
					{'title': '18. 太阳月亮',
						'url': '/pages/sunMoon/sunMoon',
						'thumbnail': '/static/thumbnail/18.png'
					},
					{'title': '19. 纯CSS简易云彩',
						'url': '/pages/singleCloud/singleCloud',
						'thumbnail': '/static/thumbnail/19.png'
					},
					{'title': '20. 纯CSS简易小鸟',
						'url': '/pages/bird/bird',
						'thumbnail': '/static/thumbnail/20.png'
					},
					{'title': '21. 纯CSS简易小帆船',
						'url': '/pages/boat/boat',
						'thumbnail': '/static/thumbnail/21.png'
					},
					{'title': '22. css3汽车道路穿梭动画',
						'url': '/pages/car/car',
						'thumbnail': '/static/thumbnail/22.png'
					},
					{'title': '23. 基础',
						'url': '/pages/basicConcept/basicConcept',
						'thumbnail': '/static/thumbnail/23.png'
					},
					{'title': '24. grid',
						'url': '/pages/grid/grid',
						'thumbnail': '/static/thumbnail/24.png'
					},
				]
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
		/* height: 100%; */
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
	
	/deep/ uni-image>div {
		background-size: 50% 50% !important;
	}
	
</style>
