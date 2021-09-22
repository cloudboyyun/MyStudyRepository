<template>
	<view>
		<xy-loading></xy-loading>
		<xy-loading v-if="showFullLoading" :opacity='1' brandText="...CloudBoy..." loadingText="...Loading..." 
			color="#FF9000" marginTop="40vh"></xy-loading>
		<xy-loading v-if="showLoading" :opacity='0.9' brandText="...CloudBoy..." loadingText="...Loading..."
			color="#FF9000" marginTop="40vh"></xy-loading>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				showFullLoading: false,
				showLoading: false
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	page {
		width: 100%;
		height: 100%;
		background-color: #007AFF;
	}
</style>
