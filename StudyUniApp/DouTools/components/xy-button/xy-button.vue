<template>
	<view class='xy-button' :style = "{
		width: width,
		height: height,
		backgroundColor: backgroundColor,
		color: fontColor,
		fontSize: fontSize,
		opacity: disabled ? 0.4 : 1.0,
		}"
		@tap.stop="onClick">
		<view v-if="iconFont" class='xy-button-icon' :class="[iconFont]"></view>
		<view class='xy-button-content'><slot></slot></view>
	</view>
</template>

<script>
	export default {
		name:"xy-button",
		data() {
			return {
				
			};
		},
		props: {
			width: {
				type: String,
				default: uni.getSystemInfoSync().screenWidth * 0.9 + 'px'
			},
			height: {
				type: String,
				default: '60rpx'
			},
			backgroundColor: {
				type: String,
				default: '#007aff'
			},
			fontColor: {
				type: String,
				default: '#ffffff'
			},
			fontSize: {
				type: [String, Number],
				default: '28rpx'
			},
			iconFont: {
				type: String,
				default: ""
			},
			disabled: {
				type: Boolean,
				default: false
			},
		},
		methods: {
			onClick(e) {
				if(this.disabled) {
					return;
				}
				this.$emit('click', e);
			}
		}
	}
</script>

<style scoped lang="scss">
	.xy-button {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		border-radius: 10rpx;
		.xy-button-icon {
			font-size: inherit;
		}
		.xy-button-content {
			margin-left: 10rpx;
		}
	}
	
</style>
