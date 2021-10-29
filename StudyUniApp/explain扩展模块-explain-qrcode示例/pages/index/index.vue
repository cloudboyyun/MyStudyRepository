<template>
	<view class="content">
		<view class="text">explain-qrcode二维码生成</view>
		<view class="image">
			<image :src="qrcodeSrc" mode="widthFix" />
			<text>调用云函数</text>
		</view>
		<view class="image">
			<image :src="`https://75bb46ae-4018-4f21-94a2-c226eb480d30.bspapp.com/http/qrcode?text=${qrcodeText}&ec_level=${qrcodeErrorCorrectLevel}&margin=${qrcodeMargin}`"
			 mode="widthFix" />
			<text>云函数URL化</text>
		</view>
		<input class="input" v-model="qrcodeText" placeholder="输入内容生成二维码" />
		<button class="button" type="primary" @tap="make">生成二维码</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				qrcodeText: 'explain-qrcode',
				qrcodeErrorCorrectLevel: 'M',
				qrcodeMargin: 2,
				qrcodeSrc: ''
			}
		},
		onLoad() {
			this.make()
		},
		methods: {
			make() {
				uniCloud.callFunction({
					name: 'explain-qrcode',
					data: {
						service: 'qrcode',
						action: 'make',
						params: {
							text: this.qrcodeText, // 二维码内容
							ec_level: this.qrcodeErrorCorrectLevel, // 纠错等级L、M、Q、H
							margin: this.qrcodeMargin // 边距
						}
					}
				}).then(res => {
					this.qrcodeSrc = res.result
				})
			}
		}
	}
</script>

<style>
	page {
		background-color: #f0f0f0;
	}

	.content {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		margin-top: var(--status-bar-height);
	}

	.text {
		display: flex;
		justify-content: center;
		margin-top: 50rpx;
		font-size: 36rpx;
		color: #666666;
	}

	.image {
		width: 354rpx;
		margin-top: 50rpx;
		text-align: center;
	}

	.image image {
		display: block;
		width: 354rpx;
		height: 354rpx;
	}

	.input {
		width: 600rpx;
		height: 40px;
		margin: 50rpx 0;
		padding: 0 20rpx;
		border: 1px solid #b0b0b0;
		border-radius: 5px;
		background-color: #ffffff;
		box-sizing: border-box;
	}

	.button {
		width: 690rpx;
		margin: 10rpx;
	}

	.button:last-child {
		margin-bottom: 50rpx;
	}
</style>
