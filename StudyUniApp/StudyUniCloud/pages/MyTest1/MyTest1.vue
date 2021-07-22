<template>
	<view>
		<view v-for="(item,i) in newsList" :key="item._id">
			<view>{{item.title}} - {{item.channel}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				newsList: null
			}
		},
		onLoad(options) {
			console.log("hello");
			uniCloud.callFunction({
				name: 'newsList',
				data: {}
			})
			.then(res =>{
				console.log(res);
				this.newsList = res.result;
			})
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
