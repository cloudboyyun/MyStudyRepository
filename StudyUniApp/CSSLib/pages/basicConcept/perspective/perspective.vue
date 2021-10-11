<template>
	<view class='main'>
		<pre>
perspective定义了人眼和屏幕的距离。
这个属性要定义在父元素上面。
这个属性和translateZ的大小、正负都有关系,实际上和元素在屏幕上的投影。见图。
1. 如果z=0, 也就是元素在屏幕的平面上，那无论怎么调整perspective, 图像大小都没变化。
2. 如果z>0, 也就是元素在屏幕前面，那增加perspective，离图像远了，则图像变小。
3. 如果z<0, 也就是元素在屏幕后面，那增加perspective，离图像远了，投影图像反而变大。
		</pre>
		<image src="/static/images/2.png"></image>
		<image src="/static/images/1.png"></image>
		<view class='seperator'></view>
		<view class='title'>设置translateZ</view>
		<slider :value="z" @change="zChange" min="-100" max="100" step="1" show-value="true" class='slider'/>
		<view class='title'>设置perspective</view>
		<slider :value="perspective" @change="pChange" min="0" max="200" step="1" show-value="true" class='slider'/>
		<view class="box2" :style="{'perspective': perspective + 'rpx'}">
			<view :style="{'transform': 'translateZ(' + z + 'rpx)'}"></view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				z: 0,
				perspective: 0
			}
		},
		methods: {
			zChange(e) {
				this.z = e.detail.value;
			},
			pChange(e) {
				this.perspective = e.detail.value;
			}
		}
	}
</script>

<style lang="scss">
	page {
		width: 100%;
	}

	.main {
		margin-top: 20rpx;
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	pre {
		margin-left: 20rpx;
		margin-right: 20rpx;
		align-self: flex-start;
		white-space: pre-wrap;
		margin-bottom: 50rpx;;
	}
	
	.seperator {
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		width: 100%;
		height: 2rpx;
		background: blue;
	}
	
	.title {
		font-weight: bold;
		align-self: flex-start;
	}
	
	.slider {
		width: 80%;
	}

	.box2 {
		margin-top: 20rpx;
		width: 600rpx;
		height: 600rpx;
		background: #0000FF;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.box2>view {
		width: 200rpx;
		height: 200rpx;
		background: red;
		border-radius: 50%;
	}
	
	
</style>
