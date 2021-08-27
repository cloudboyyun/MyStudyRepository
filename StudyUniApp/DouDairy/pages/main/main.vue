<template>
	<view class='page'>
		<view class='year-month-title'>
			<view>{{year}}年{{month}}月</view>
			<view>{{result.lunarYear}}</view>
			<view>{{result.animalsYear}}</view>
		</view>
		<view class='calendar'>
			<view class='row'>
				<view class='day weekend'>日</view>
				<view class='day'>一</view>
				<view class='day'>二</view>
				<view class='day'>三</view>
				<view class='day'>四</view>
				<view class='day'>五</view>
				<view class='day weekend'>六</view>
			</view>
			<view class='row'>
				<view v-for="(item,index) in result.dates" :key="index" class='day'>
					<view>{{item.day}}</view>
					<view>{{getDateDesc(item)}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {getMonthDays} from "../../utils/dairy.js"
	export default {
		data() {
			return {
				year: null,
				month: null,
				result: {}
			}
		},
		onLoad() {
			let today = new Date(2021, 6, 25);
			this.year = today.getFullYear();
			this.month = today.getMonth() + 1;
			this.loadPage();
		},
		methods: {
			loadPage() {
				// uni.setNavigationBarTitle({
				//     title: '' + this.year + '年' + this.month + '月'
				// });
				let that = this;
				uniCloud.callFunction({
				    name: 'query-month-dairy',
				    data: { 
							"year": this.year,
							"month": this.month
						}
				  })
				  .then(res => {
						that.result = res.result;
						console.log(that.result);
					});
			},
			
			getDateDesc(date) {
				let result = null;
				if(date.dateDesc) {
					let lunar = date.dateDesc.lunar;
					let strArr = lunar.split("月");
					if(strArr[1] == "初一") {
						result = strArr[0] + "月";
					} else {
						result = strArr[1];
					}
				}
				return result;
			}
		}
	}
</script>

<style>
	.year-month-title {
		display: flex;
		flex-direction: row;
	}
	
	.calendar {
		width: 100%;
	}
	
	.row {
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		flex-wrap: wrap;
		margin-left: 20rpx;
		margin-right: 20rpx;
		text-align: center;
	}
	
	.weekend {
		color: red;
	}
	
	.day {
		width: 14%;
	}
</style>
