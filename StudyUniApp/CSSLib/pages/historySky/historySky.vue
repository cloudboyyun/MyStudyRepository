<template>
	<view class='main'>
		<view class='content'>
			<xy-event-ball v-for="(data, i) in datas" :key="i" :data="data" 
				class='xy-event-ball'></xy-event-ball>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				datas: [
					{year: -221,event: '秦始皇统一六国'},
					{year: 305,event: '张三做了某事'},
					// {year: 2021, event: '土豆云开始编这个app'},
					// {year: -221,event: '秦始皇统一六国'},
					{year: 305,event: '张三做了某事'},
					{year: 2021, event: '土豆云开始编这个app'},
					{year: -221,event: '秦始皇统一六国'},
					{year: 305,event: '张三做了某事'},
					{year: 2021, event: '土豆云开始编这个app'}
				]
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
	}
	
	.main {
		width: 100%;
		height: 100%;
		background: #000000;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
	.content {
		width: 80%;
		height: 80%;
		position: relative;
	}
	
	.xy-event-ball {
		position: absolute;
		
	}
</style>
