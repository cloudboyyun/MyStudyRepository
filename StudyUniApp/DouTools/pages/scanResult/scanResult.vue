<template>
	<view class='main'>
		<view class='title'>您扫描的二维码中的内容如下：</view>
		<view class='content' :style="{'font-family': isHyperLink?'站酷意大利体':'站酷文艺体'}">{{content}}</view>
		<view class='action-area'>
			<!-- <button size="mini" type='primary'>开始</button> -->
			<view class='button' :style="{'background-color': '#fa9a28'}" @click="onCopyButtonClick">
				<view class='iconfont icon-copy-template'></view>
				<view class='text'>复制</view>
			</view>
			<!-- <view class='button' v-if="isHyperLink" :style="{'background-color': '#fa5c65'}" @click="onHyperLinkButtonClick">
				<view class='iconfont icon-lianjie'></view>
				<view class='text'>跳转</view>
			</view> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				content: "豆云工具箱"
			}
		},
		computed: {
			isHyperLink() {
				if(this.content && this.content.startsWith('http')) {
					return true;
				}
				return false;
			}
		},
		onLoad(options) {
			console.log("options", options);
			if(options.content) {
				this.content = options.content;
			} 
		},
		methods: {
			onShareAppMessage: function(e) {
				let title = '豆云日历'
				return {
					title: '豆云日历',
					path: 'pages/index/index'
				}
			},
			onCopyButtonClick() {
				uni.setClipboardData({
					data: this.content,
					success : function() {
					}
				})
			},
			
			onHyperLinkButtonClick() {
				uni.navigateTo({
					url: '../webView/webView?url=' + this.content
				})
			}
		}
	}
</script>

<style lang="scss">
	@font-face {
		font-family: '站酷意大利体';
		src: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/b8661cf5-be94-49eb-a5e3-be9f68706878.ttf');
	}
	
	@font-face {
		font-family: '站酷文艺体';
		src: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/899d5c0c-95f2-40a7-8176-98214d6e83b7.ttf');
	}
	
	page {
		width: 100%;
		height: 100%;
	}
	.main {
		width: 100%;
		height: 100%;
		background-image: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/0f8c11f6-95d4-4d8a-bac8-007f01f662ed.jpeg');
		background-repeat: no-repeat;
		background-position: left top;
		background-size: 100% 100%;
		padding-top: 20rpx;
	}
	.title {
		/* margin-top: 20rpx; */
		margin-left: 20rpx;
		color: white;
	}
	.content {
		margin-top: 50rpx;
		margin-left: 20rpx;
		margin-right: 20rpx;
		box-sizing: border-box;
		padding: 20rpx;
		border-radius: 20rpx;
		height: 600rpx;
		background-image: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/9d3b1745-1914-44e8-bab2-d875757833d4.png');
		background-repeat: no-repeat;
		background-position: left top;
		background-size: 100% 100%;
		padding-left: 100rpx;
		padding-right: 20rpx;
		word-wrap: break-word;
		padding-top: 50rpx;
		line-height: 50rpx;
		font-size: 38rpx;
		letter-spacing: 10rpx;
	}
	
	.action-area {
		margin-top: 20rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
	}
	
	.button {
		width: 150rpx;
		height: 60rpx;
		background-color: white;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		border-radius: 10rpx;
		color: white;
		.iconfont {
			font-size: 28rpx;
		}
		.text {
			margin-left: 10rpx;
			font-size: 28rpx;
		}
	}
</style>
