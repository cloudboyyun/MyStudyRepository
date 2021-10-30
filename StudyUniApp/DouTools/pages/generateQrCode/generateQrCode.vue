<template>
	<view class='main'>
		<view class='result-area'>
			<image class='bar-code' :src="barCode"></image>
			<view class='r-a-button-area'>
				<xy-button class='button' width="150rpx" height="60rpx" backgroundColor="#fa5c65"
					fontColor="#ffffff" iconFont="iconfont icon-baocun" 
					:disabled="!barCode" @click="onSaveClick">保存</xy-button>
				<xy-button class='button' width="150rpx" height="60rpx" backgroundColor="#fa9a28"
					fontColor="#ffffff" iconFont="iconfont icon-zhuanfa" 
					:disabled="!barCode" @click="onForwardClick">转发</xy-button>
			</view>
		</view>
		
		<textarea class='content' placeholder="请输入二维码内容" 
			confirm-type="done" :value="content"
			@input="bindTextAreaInput"></textarea>
		<view class='action-area'>
			<xy-button width="150rpx" height="60rpx" backgroundColor="#268aff"
				fontColor="#ffffff" iconFont="iconfont icon-iconfontyijiantuiguang" 
				 @click="onGenClick">生成</xy-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				barCode: null,
				content: null
			}
		},
		methods: {
			onGenClick() {
				let url = "https://aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983.bspapp.com/xyQrCode?content="
					+ this.content + "&action=httpUrl";
				this.barCode = url;
				// uniCloud.callFunction({
				// 	name: 'xy-qrcode',
				// 	data: {
				// 		action: 'httpUrl',
				// 		content: this.content
				// 	}
				// }).then(res => {
				// 	console.log("res", res);
				// 	this.barCode = res.result
				// })
			},
			
			bindTextAreaInput(e) {
				this.content = e.detail.value;
			},
			
			onSaveClick(e) {
				console.log("onSaveClick");
				let that = this;
				uni.downloadFile({
					url: that.barCode,
					success: (res) => {
						console.log("res", res);
						uni.saveImageToPhotosAlbum({
							filePath: res.tempFilePath,
							success: (res2) => {
								uni.showToast({
									title: '已保存在您的相册。'
								})
							}
						})
					}
				})
			},
			
			onForwardClick(e) {
				console.log("onForwardClick");
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		width: 100%;
		height: 100%;
	}

	.main {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
		
		.result-area {
			box-sizing: border-box;
			width: 100%;
			height: 380rpx;
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			position: relative;
			.bar-code {
				width: 300rpx;
				height: 300rpx;
				border: 1rpx solid #007AFF;
			}
			.r-a-button-area {
				align-self: flex-end;
				position: absolute;
				margin-right: 0rpx;
				margin-left: auto;
				right: 20rpx;
				top: 0;
				height: 100%;
				display: flex;
				flex-direction: column;
				justify-content: center;
				.button {
					margin-top: 10rpx;
					margin-bottom: 10rpx;
				}
			}
		}
	}
	
	.content {
		margin-left: 20rpx;
		width: 710rpx;
		height: 600rpx;
		box-sizing: border-box;
		padding-top: 100rpx;
		padding-left: 50rpx;
		background-image: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/c62db415-f279-4f7c-9678-3e195317e9db.png');
		background-repeat: no-repeat;
		background-position: left top;
		background-size: 710rpx 100%;
		font-size: 32rpx;
		line-height: 50rpx;
	}

	.action-area {
		display: flex;
		flex-direction: row;
		justify-content: space-around;
	}
</style>
