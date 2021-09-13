<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
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
	.full-loading {
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
	}
	
	.loading {
		position: fixed;
		width: 100%;
		height: 120vh;
		z-index: 999;
		top: 0;
		bottom: 0;
		background-color:#ffffff;
		opacity: 0.7;
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
