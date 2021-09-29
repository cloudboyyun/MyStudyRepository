<template>
	<view class='xy-event-ball'>
		<!-- {{data.event}} -->
	</view>
</template>

<script>
	export default {
		name:"xy-event-ball",
		props: {
			data: {
				type: Object,
				required: true
			}
		},
		data() {
			return {
				
			};
		}
	}
</script>

<style>
	.xy-event-ball {
		border:1rpx solid #F0F0F0;
		background:#F0F0F0;
		box-shadow:0rpx 0px 35rpx #F0F0F0;
		width: 20rpx;
		height: 20rpx;
		border-radius: 50%;
	}
</style>
