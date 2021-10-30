<template>
	<view class='main'>
		<view class='result-area'>
			<image class='bar-code' :src="barCode"></image>
			<view class='r-a-button-area'>
				<view class='button' :style="{'background-color': '#fa5c65'}" @click="onGenClick">
					<view class='iconfont icon-baocun'></view>
					<view class='text'>保存</view>
				</view>
				<view class='button' :style="{'background-color': '#fa9a28'}" @click="onGenClick">
					<view class='iconfont icon-zhuanfa'></view>
					<view class='text'>转发</view>
				</view>
			</view>
		</view>
		
		<textarea class='content' placeholder="请输入二维码内容" 
			confirm-type="done" :value="content"
			@input="bindTextAreaInput"></textarea>
		<view class='action-area'>
			<view class='button' :style="{'background-color': '#268aff'}" @click="onGenClick">
				<view class='iconfont icon-iconfontyijiantuiguang'></view>
				<view class='text'>生成</view>
			</view>
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
				console.log('onGenClick', this.content);
				uniCloud.callFunction({
					name: 'xy-qrcode',
					data: {
						content: this.content
					}
				}).then(res => {
					console.log("res", res);
					this.barCode = res.result
				})
			},
			
			bindTextAreaInput(e) {
				this.content = e.detail.value;
			}
		}
	}
</script>

<style lang="scss">
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

	.button {
		margin-top: 10rpx;
		margin-bottom: 10rpx;
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
