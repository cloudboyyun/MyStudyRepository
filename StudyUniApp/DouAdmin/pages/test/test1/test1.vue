<template>
	<view>
		<button @click="test2()">测试</button>
		<view v-for="(item,index) in messages" v-bind:key="item">
			{{item}}
		</view>
	</view>
</template>

<script>
	import {getMyState, setMyState} from '../../../utils/common.js';
	export default {
		data() {
			return {
				messages: ['输出在这里']
			}
		},
		methods: {
			test1() {
				let message1 = {
					body: null
				};
				let message2 = {
					body: {
						user: {
							firstName: "Martin"
						}
					}
				};
				let firstName1 = this.printOut(message1);
				let firstName2 = this.printOut(message2);
				this.messages.push("message1的输出：" + firstName1);
				this.messages.push("message2的输出：" + firstName2);
			},

			printOut(message) {
				// 下面这句会报错：Cannot read property 'user' of null
				// let firstName = message.body.user.firstName;

				// 所以要进行如下判断，但这种写法太繁琐
				// let firstName = (message &&
				// 	message.body &&
				// 	message.body.user &&
				// 	message.body.user.firstName) || 'default';

				// ES2020引入新的运算符: ?.
				let firstName = message?.body?.user?.firstName || 'default';
				console.log(firstName)
				return firstName;
			},
			
			test2() {
				setMyState(2000);
				this.messages.push(getMyState());
			}
		}
	}
</script>

<style>

</style>
