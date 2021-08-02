<template>
	<view>
		<view>用户名：{{username}}</view>
		<view>年龄：{{age}}</view>
		<view>{{customerGender}}</view>
		<view>{{customerGenderDesc}}</view>
		<button @click="changeGender">修改性别</button>
		<button @click="setUserName('李四')">修改用户名</button>
	</view>
</template>

<script>
	import {
		mapState, mapMutations
	} from 'vuex' //引入mapState
	export default {
		data() {
			return {

			}
		},
		computed: {
			// 方式1：使用store变量
			username() {
				return this.$store.state.username
			},
			// 方式2：使用mapState辅助函数帮助我们生成计算属性，让你少按几次键
			// mapState 函数返回的是一个对象。使用对象展开运算符将多个对象合并为一个，
			// 以使我们可以将最终对象传给 computed 属性。极大地简化写法
			...mapState({
				age: state => state.age,
				customerGender: 'gender',
				customerGenderDesc(state) {
					return state.gender == 'M'? '男' : '女';
				}
			})
		},
		methods: {
			changeGender() {
				if(this.customerGender == 'F') {
					this.$store.commit('changeGender', 'M');
				} else {
					this.$store.commit('changeGender', 'F');
				}
			},
			...mapMutations(['setUserName'])
		}
	}
</script>

<style>

</style>
