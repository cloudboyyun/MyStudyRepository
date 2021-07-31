<template>
	<view>
		<button @click="test1()">测试</button>
		<view v-for="(item,index) in messages">
			{{item}}
		</view>
	</view>
</template>

<script>
	import {getMyState} from '../../../utils/common.js';
	export default {
		data() {
			return {
				messages: ['输出在这里']
			}
		},
		methods: {
			test1() {
				this.messages.push(getMyState());
			}
		}
	}
</script>

<style>

</style>
