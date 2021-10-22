<template>
	<view class="container">
		<view @click="onClick">点我</view>
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
			onClick() {
				uni.getUserProfile({
					desc: "用于快速生成名片",
					lang: 'zh_CN',
					success: function(userProfile) {
						console.log('userProfile', userProfile);
					},
					fail: function(err) {
						console.log("error", err);
					}
				});
			},
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
