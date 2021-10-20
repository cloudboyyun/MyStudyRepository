<template>
	<view class="container">
		
		<view class="intro">本项目已包含uni ui组件，无需import和注册，可直接使用。在代码区键入字母u，即可通过代码助手列出所有可用组件。光标置于组件名称处按F1，即可查看组件文档。</view>
		<text class="intro">详见：</text>
		<uni-link :href="href" :text="href"></uni-link>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				href: 'https://uniapp.dcloud.io/component/README?id=uniui'
			}
		},
		onLoad() {
			this._onLoad();
		},
		methods: {
			async _onLoad() {
				this.loginByWeixin();
			},
			async loginByWeixin() {
				// #ifdef MP-WEIXIN
				let code = await this.getWeixinCode();
				console.log("code", code)
				let loginByWeixinResult = await uniCloud.callFunction({
					name: 'uni-id-func',
					data: {
						action: 'loginByWeixin',
						params: {
							code: code,
							role: ['card-miniprogram-user']
						}
					}
				});
				uni.setStorageSync('uni_id_token', loginByWeixinResult.result.token)
				uni.setStorageSync('uni_id_token_expired', loginByWeixinResult.result.tokenExpired);
				// #endif
			},
			
			getWeixinCode() {
				return new Promise((resolve, reject) => {
					// #ifdef MP-WEIXIN
					uni.login({
						provider: 'weixin',
						success(res) {
							resolve(res.code)
						},
						fail(err) {
							reject(new Error('微信登录失败'))
						}
					})
					// #endif
				})
			},
		}
	}
</script>

<style>
	.container {
		padding: 20px;
		font-size: 14px;
		line-height: 24px;
	}
</style>
