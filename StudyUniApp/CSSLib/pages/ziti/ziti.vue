<template>
	<view class='main'>
		<view class='shi'>锄禾日当午，汗滴禾下土。</view>
		<view>字体从这里下载的，要选择免费字体，或者购买收费字体。对于Demo版本的字体，会字库不全。</view>
		<view>https://font.chinaz.com/</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.main {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		font-size: 20rpx;
	}
	
	@font-face {
	    font-family: 'sunzhongshan';
	    src:url('/static/fonts/方圆孙中山行书.ttf');
	}
	
	.shi {
		font: 100rpx sunzhongshan;
	}
</style>
