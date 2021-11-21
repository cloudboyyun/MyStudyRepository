<template>
	<view class="content">
		<canvas type="webgl" id="webgl" style="width: 100%; height: 450px;"></canvas>
	</view>
</template>

<script>
	import {
		createScopedThreejs
	} from '@/common/threejs'

	import {
		renderCube
	} from '@/common/test-cases/cube'
	import {
		renderCubes
	} from '@/common/test-cases/cubes'
	import {
		renderSphere
	} from '@/common/test-cases/sphere'
	import {
		renderModel
	} from '@/common/test-cases/model'

	export default {
		onLoad: function() {
			uni.createSelectorQuery()
				.select('#webgl')
				.node()
				.exec((res) => {
					const canvas = res[0].node
					const THREE = createScopedThreejs(canvas)

					// renderSphere(canvas, THREE)
					// renderCube(canvas, THREE)
					// renderCubes(canvas, THREE)
					renderModel(canvas, THREE)
				})
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
