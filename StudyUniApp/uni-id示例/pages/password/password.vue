<template>
	<view class="content">
		<input type="text" v-model="username" placeholder="用户名/邮箱/手机号" />
		<input type="text" v-model="password" password="true" placeholder="密码" />
		<view class="row">
			<checkbox-group @change="permissionChange">
				<label>
					<checkbox value="needPermission" /><text>启用Permission，一般在管理后台需要传递此参数</text>
				</label>
			</checkbox-group>
		</view>
		<button type="default" @tap="register">注册</button>
		<button type="default" @tap="login">登录</button>
		<button type="default" @tap="updatePwd">修改密码</button>
		<button type="default" @tap="resetPwd">重设密码</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username: '',
				password: '',
				needPermission: false
			}
		},
		methods: {
			permissionChange(e) {
				this.needPermission = e.detail.value.indexOf('needPermission') > -1
			},
			register() {
				uniCloud.callFunction({
					name: 'uni-id-test',
					data: {
						action: 'register',
						params: {
							username: this.username,
							password: this.password,
							needPermission: this.needPermission
						}
					},
					success(res) {
						uni.showModal({
							showCancel: false,
							content: JSON.stringify(res.result)
						})
						uni.setStorageSync('uni_id_token', res.result.token)
						uni.setStorageSync('uni_id_token_expired', res.result.tokenExpired)
					},
					fail(e) {
						console.error(e)
						uni.showModal({
							showCancel: false,
							content: '注册失败，请稍后再试'
						})
					}
				})
			},
			login() {
				uniCloud.callFunction({
					name: 'uni-id-test',
					data: {
						action: 'login',
						params: {
							username: this.username,
							password: this.password,
							needPermission: this.needPermission
						}
					},
					success(res) {
						uni.showModal({
							showCancel: false,
							content: JSON.stringify(res.result)
						})
						uni.setStorageSync('uni_id_token', res.result.token)
						uni.setStorageSync('uni_id_token_expired', res.result.tokenExpired)
					},
					fail(e) {
						console.error(e)
						uni.showModal({
							showCancel: false,
							content: '登录失败，请稍后再试'
						})
					}
				})
			},
			updatePwd() {
				uniCloud.callFunction({
					name: 'uni-id-test',
					data: {
						action: 'updatePwd',
						params: {
							oldPassword: '123456',
							newPassword: 'abcdef'
						}
					},
					success(res) {
						uni.showModal({
							showCancel: false,
							content: JSON.stringify(res.result)
						})
					},
					fail(e) {
						console.error(e)
						uni.showModal({
							showCancel: false,
							content: '修改失败，请稍后再试'
						})
					}
				})
			},
			resetPwd() {
				uniCloud.callFunction({
					name: 'uni-id-test',
					data: {
						action: 'resetPwd'
					},
					success(res) {
						if (res.result.code === 0) {
							uni.showModal({
								showCancel: false,
								content: '密码已重置为123456'
							})
						} else {
							uni.showModal({
								showCancel: false,
								content: JSON.stringify(res.result)
							})
						}
					},
					fail(e) {
						console.error(e)
						uni.showModal({
							showCancel: false,
							content: '重置失败，请稍后再试'
						})
					}
				})
			},
		}
	}
</script>

<style>

</style>
