<script>
	import initApp from '@/common/appInit.js';
	import checkIsAgree from '@/pages/uni-agree/utils/uni-agree.js';
	export default {
		globalData: {
			searchText: '',
			appVersion: {},
			config: {}
		},
		onLaunch: function() {
			console.log('App Launch')
			initApp();
			// #ifdef APP-PLUS
			//checkIsAgree(); APP端暂时先用原生默认生成的。目前，自定义方式启动vue界面时，原生层已经请求了部分权限这并不符合国家的法规
			// #endif

			// #ifdef H5
			// checkIsAgree(); // 默认不开启。目前全球，仅欧盟国家有网页端同意隐私权限的需要。如果需要
			// #endif

			// #ifdef APP-PLUS
			plus.screen.lockOrientation("portrait-primary");  
			//idfa有需要的用户在应用首次启动时自己获取存储到storage中
			//https://ask.dcloud.net.cn/article/36107
			/*if(~plus.storage.getItem('idfa')){
				plus.device.getInfo({//需要勾选IDFA
					success:function(e){  
						console.log('idfa =  '+JSON.stringify(e.idfa));  
					},
					fail:function(e){
						console.log('getDeviceInfo failed: '+JSON.stringify(e));  
					}  
				});
			}*/
			// #endif
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	.loading {
		position: fixed;
		width: 100%;
		height: 120vh;
		z-index: 999;
		top: 0;
		bottom: 0;
		background-color:#ffffff;
		opacity: 1;
		display: flex;
		flex-direction: column;
		align-items: center;
		/* justify-content: first baseline; */
	}
	
	.loading-image{
	  margin-top: 26vh;
	  width: 500rpx;
	  height: 500rpx;
	}
</style>
