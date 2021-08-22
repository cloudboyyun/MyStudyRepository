<template>
	<view>
		
	</view>
</template>

<script>
	import {getMonthDays} from "../../utils/dairy.js"
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {
			this.loadPage();
		},
		methods: {
			loadPage() {
				let today = new Date();
				let result = getMonthDays(today.getFullYear(), 7);
				console.log(result);
			}
		}
	}
</script>

<style>

</style>
