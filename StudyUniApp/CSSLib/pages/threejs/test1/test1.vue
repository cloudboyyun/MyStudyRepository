<template>
	<view class="main">
		<canvas type="webgl" id="webgl" style="width: 100%; height: 450px;"></canvas>
	</view>
</template>

<script>
	import {
		createScopedThreejs
	} from 'three'
	export default {
		data() {
			return {

			}
		},
		onLoad(options) {
			let a = uni.createSelectorQuery().in(this)
				.select('#webgl')
				.node();
			console.log(a);
				// .node()
				// .exec((res) => {
				// 	console.log(res);
				// 	// const canvas = res[0].node
				// 	// const THREE = createScopedThreejs(canvas)

				// 	// renderSphere(canvas, THREE)
				// 	// renderCube(canvas, THREE)
				// 	// renderCubes(canvas, THREE)
				// 	// renderModel(canvas, THREE)
				// });
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
		background: #000000;
	}
</style>
