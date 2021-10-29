<template>
	<view class='main'>
		<view class='title-area'>
			<view class='app-name'>豆云工具箱</view>
		</view>
		<view class='content'>
			<view v-for="(item,index) in items" :key="index" class='item' @click="handleClick(item)">
				<view class='item-icon iconfont ' :class="item.icon" :style="{color: colColors[index%colColors.length]}"></view>
				<view class='item-name'>{{item.name}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				colColors: [
					'#268aff','#fa5c65','#fd9a28','#2cae75'
				],
				items: [
					{
						'name': '二维码生成',
						'icon': 'icon-erweima',
						'onClick': this.onQrCodeClick
					},
					{
						'name': '扫一扫',
						'icon': 'icon-saoyisao',
						'onClick': this.onScanClick
					}
				]
			}
		},
		computed: {},
		onLoad() {
			this._onLoad();
		},
		methods: {
			onShareAppMessage: function(e) {
				let title = '豆云日历'
				return {
					title: '豆云日历',
					path: 'pages/index/index'
				}
			},
			async _onLoad() {},
			
			handleClick(item) {
				item.onClick();
			},
			
			onQrCodeClick() {
				console.log("onQrCodeClick")
			},
			
			onScanClick() {
				console.log("onScanClick")
				uni.navigateTo({
					url: "../scanResult/scanResult"
				})
				// uni.scanCode({
				// 	success: function(res) {
				// 		console.log(res.result);
				// 		uni.navigateTo({
				// 			url: "../scanResult/scanResult?content=" + res.result
				// 		})
				// 	}
				// })
			}

		},
	}
</script>

<style lang="scss">
	@font-face {
		font-family: 'sunzhongshan';
		src: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/fa3b69e8-ffc9-46d3-9111-7cf472412576.ttf');
	}

	page {
		width: 100%;
		height: 100%;
	}

	.main {
		width: 100%;
		height: 100%;
		background-image: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-aa1f9ef9-8c87-45d9-bf88-9cc5b38a7983/2d281b43-80b9-4159-8ae3-b21ca0162b5a.jpeg');
		// background-image: url('/static/images/bg16.jpeg');
		background-repeat: no-repeat;
		background-position: left top;
		background-size: 100% 100%;
	}

	.title-area {
		padding-top: calc(var(--status-bar-height) + 60rpx);
		background-color: #007aff;
		width: 100%;
		padding-bottom: 20rpx;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: flex-start;
		position: relative;
		transition: background 5s ease;
		transition-delay: 2s;
	}
	
	.app-name {
		font: 38rpx sunzhongshan;
		align-self: center;
		color: #ffffff;
		font-weight: bolder;
	}
	
	.content {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		padding-top: 20rpx;
		padding-left: 20rpx;
		padding-right: 20rpx;
	}
	
	.item {
		display: flex;
		flex-direction: column;
		width: 177.5rpx;
		align-items: center;
		margin-top: 20rpx;
		// box-sizing:border-box;
		// border: 1rpx solid #007AFF;
		.item-icon {
			font-size: 100rpx;
		}
		.col-1 {
			color: #268aff;
		}
		
		.col-2 {
			color: #fa5c65;
		}
		
		.col-3 {
			color: #fd9a28;
		}
		
		.col-4 {
			color: #2cae75;
		}
		
		image {
			width: 100rpx;
			height: 100rpx;
		}
		
		.item-name {
			margin-top: 10rpx;
			font-size: 28rpx;
			color: white;
		}
	}
</style>
