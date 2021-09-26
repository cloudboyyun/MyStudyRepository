<template>
	<view class='main'>
		<view>
			<center>正方形</center>
			<view class="square1"></view>
		</view>
		<view>
			<center>使用border制作的正方形</center>
			<view class="square2"></view>
		</view>
		<view>
			<center>长方形</center>
			<view class="rectangle"></view>
		</view>
		<view>
			<center>圆形</center>
			<view class="circle"></view>
		</view>
		<view>
			<center>半圆形</center>
			<view class="semicircle"></view>
		</view>
		<view>
			<center>扇形</center>
			<view class="fanShaped"></view>
		</view>
		<view>
			<center>椭圆形</center>
			<view class="oval"></view>
		</view>
		<view>
			<center>向上的三角</center>
			<view class="triangle-up"></view>
		</view>
		<view>
			<center>向下的三角</center>
			<view class="triangle-down"></view>
		</view>
		<view>
			<center>向左的三角</center>
			<view class="triangle-left"></view>
		</view>
		<view>
			<center>向右的三角</center>
			<view class="triangle-right"></view>
		</view>
		<view>
			<center>向左上的三角</center>
			<view class="triangle-topleft"></view>
		</view>
		<view>
			<center>向右上的三角</center>
			<view class="triangle-topright"></view>
		</view>
		<view>
			<center>向左下的三角</center>
			<view class="triangle-bottomleft"></view>
		</view>
		<view>
			<center>向右下的三角</center>
			<view class="triangle-bottomright"></view>
		</view>
		<view>
			<center>平行四边形</center>
			<view class="parallelogram"></view>
		</view>
		<view>
			<center>六角星</center>
			<view class="star-six"></view>
		</view>
		<view>
			<center>五角星</center>
			<view class="star-five"></view>
		</view>
		<view>
			<center>心形</center>
			<view class="heart"></view>
		</view>
		<view>
			<center>pac man</center>
			<view class="pac-man"></view>
		</view>
		<view>
			<center>对话泡泡</center>
			<view class="talk-bubble"></view>
		</view>
		<view>
			<center>放射状背景</center>
			<view class="my-radial-gradient"></view>
		</view>
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
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: space-around;
		font-size: 20rpx;
		line-height: 60rpx;
	}
	
	.square1 {
		width: 100rpx;
		height: 100rpx;
		background: red;
	}
	
	.square2 {
		width: 0;
		height: 0;
		border: 50rpx solid red;/*边框大小等于正方形宽度（或高度）的一半*/
	}
	
	.rectangle {
		width: 200rpx;
		height: 100rpx;
		background: red;
	}
	
	.circle {
		width: 100rpx;
		height: 100rpx;
		background: red;
		border-radius: 50rpx;
	}
	
	.semicircle {
		width: 100rpx;
		height: 50rpx;
		background: red;
		border-radius: 100rpx 100rpx 0 0;
	}
	
	.fanShaped {
		width: 50rpx;
		height: 50rpx;
		background: red;
		border-radius: 50rpx 0 0 0;
	}
	
	.oval {
		width: 200rpx;
		height: 100rpx;
		background: red;
		border-top-left-radius: 100rpx 50rpx;
		border-top-right-radius: 100rpx 50rpx;
		border-bottom-left-radius: 100rpx 50rpx;
		border-bottom-right-radius: 100rpx 50rpx;
	}
	
	.triangle-up {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: transparent transparent red transparent;
	}
	
	.triangle-down {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: red transparent transparent transparent;
	}
	
	.triangle-left {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: transparent red transparent transparent;
	}
	
	.triangle-right {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: transparent transparent transparent red;
	}
	
	.triangle-topleft {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: red transparent transparent red;
	}
	
	.triangle-topright {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: red red transparent transparent;
	}
	
	.triangle-bottomleft {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: transparent transparent red red;
	}
	
	.triangle-bottomright {
		width: 0;
		height: 0;
		border: 50rpx solid red;
		border-color: transparent red red transparent;
	}
	
	.parallelogram {
		width: 150rpx;
		height: 100rpx;
		background: red;
		transform: skew(20deg, 0);
	}
	
	.star-six { 
		width: 0; 
		height: 0; 
		border-left: 50rpx solid transparent; 
		border-right: 50rpx solid transparent; 
		border-bottom: 100rpx solid red; 
		/* 这个设置为after元素的absolute做参考,absolute的定位是:生成绝对定位的元素，相对于 static 定位以外的第一个父元素进行定位。
		 所以此处定义了relative,却不定义left/top等指,位置不会变化,但可以给after中的absolute做定位参考. */
		position: relative;  
	} 
			
	.star-six:after { 
		width: 0; 
		height: 0; 
		border-left: 50rpx solid transparent; 
		border-right: 50rpx solid transparent; 
		border-top: 100rpx solid red; 
		position: absolute; 
		content: ""; 
		top: 30rpx; 
		left: -50rpx; 
	}
	
	.star-five { 
		margin: 50rpx 0;
		position: relative;
		display: block;
		color: red;
		width: 0rpx;
		height: 0rpx;
		border-right: 100rpx solid transparent; 
		border-bottom: 70rpx solid red; 
		border-left: 100rpx solid transparent; 
		transform: rotate(35deg);
	}
	
	.star-five:before { 
		border-bottom: 80rpx solid red; 
		border-left: 30rpx solid transparent; 
		border-right: 30rpx solid transparent; 
		position: absolute; 
		height: 0; 
		width: 0; 
		top: -45rpx; 
		left: -65rpx; 
		display: block; 
		content: ''; 
		transform: rotate(-35deg); 
	}
	
	.star-five:after { 
		position: absolute; 
		display: block; 
		color: red; 
		top: 3rpx; 
		left: -105rpx; 
		width: 0rpx; 
		height: 0rpx; 
		border-right: 100rpx solid transparent; 
		border-bottom: 70rpx solid red; 
		border-left: 100rpx solid transparent; 
		transform: rotate(-70deg); 
		content: ''; 
	}
	
	.heart { 
		position: relative; 
		width: 100rpx; 
		height: 90rpx; 
	}
	
	.heart:before { 
		position: absolute; 
		content: ""; 
		left: 50rpx; 
		top: 0; 
		width: 50rpx; 
		height: 80rpx; 
		background: red; 
		border-radius: 50rpx 50rpx 0 0;
		transform-origin: 0 100%;
		transform: rotate(-45deg);
	} 
	
	.heart:after {
		position: absolute;
		content: "";
		left: 0;
		top: 0; 
		width: 50rpx; 
		height: 80rpx; 
		background: blue; 
		border-radius: 50rpx 50rpx 0 0; 
		transform: rotate(45deg); 
		transform-origin: 100% 100%;
	}
	
	.pac-man {
		width: 0;
		height: 0;
		border: 60rpx solid red;
		border-color: red transparent red red;
		border-radius: 60rpx;
	}
	
	.talk-bubble {
		width: 120rpx;
		height: 80rpx;
		background: red;
		border-radius: 10rpx;
		position: relative;
	}
	
	.talk-bubble:after {
		content: "";
		width: 0;
		height: 0;
		border-top: 13rpx solid transparent;
		border-right: 26rpx solid blue;
		border-bottom: 13rpx solid transparent;
		right: 100%;
		top: 26rpx;
		position: absolute;
	}
	
	.my-radial-gradient {
		width: 100rpx;
		height: 100rpx;
		background: radial-gradient(circle at 50% 50%, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.1) 40%, rgba(0, 0, 0, 0) 50%);
	}
</style>
