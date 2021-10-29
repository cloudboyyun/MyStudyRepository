<template>
	<view>
		<web-view :src='url'></web-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				url: null
			}
		},
		onLoad(options) {
			this.url = options.url;
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
