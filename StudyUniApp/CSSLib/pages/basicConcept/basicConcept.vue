<template>
	<view class='main'>
		<uni-list>
			<uni-list-item clickable title="steps函数" note="动画的steps函数" 
				@click="toPage('/pages/basicConcept/steps/steps')"></uni-list-item>
			<uni-list-item clickable title="perspective" note="perspective是做啥的"
				@click="toPage('/pages/basicConcept/perspective/perspective')"></uni-list-item>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			toPage(url) {
				console.log(url);
				uni.navigateTo({
					url: url
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		width: 100%;
	}
	
	.main {
		width: 100%;
	}
</style>
