<template>
	<view class='xy-loading' :style="styleVar" @touchmove.stop.prevent="preventTouchMove">
		<view class='cylinder'>
			<view class='circle' :style="circleStyle">
				<view class='sector' v-for="(character, i) in brandTextCharacters" :key="i"
					:style="sectorStyle(i)">{{character}}</view>
			</view>
			<view class='circle' :style="circleStyle">
				<view class='sector' v-for="(character, i) in loadingTextCharacters" :key="i"
					:style="sectorStyle(i)">{{character}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	const radius = 100;
	const divides = 30;
	const sectionWidth = 2 * radius * 3.1415926 / divides;
	export default {
		name:"xy-loading",
		props: {
			opacity: {
				type: Number,
				default: 1,
				required: false
			},
			loadingText: {
				type: String,
				default: 'Loading',
				required: false
			},
			brandText: {
				type: String,
				default: 'CloudBoy',
				required: false
			},
			color: {
				type: String,
				default: '#FF9000',
				required: false
			},
			marginTop: {
				type: String,
				default: '40vh',
				required: false
			}
		},
		async created() {
			this.loadingTextCharacters = this.loadingText.split("");
			let makeUpLength = divides - this.loadingTextCharacters.length;
			for(let i=0; i<makeUpLength; i++) {
				this.loadingTextCharacters.push('');
			}
			this.brandTextCharacters = this.brandText.split("");
			makeUpLength = divides - this.brandTextCharacters.length;
			for(let i=0; i<makeUpLength; i++) {
				this.brandTextCharacters.push('');
			}
		},
		data() {
			return {
				circleStyle: {
					"width": "" + sectionWidth + "rpx"
				},
				loadingTextCharacters: [],
				brandTextCharacters: [],
				styleVar: {
					"--opacity": this.opacity,
					"--color": this.color,
					"--marginTop": this.marginTop
 				}
			}
		},
		methods: {
			sectorStyle(i) {
				let partitionDegree = 360 / divides;
				let rotateDeg = partitionDegree * i;
				return {
					"transform": "rotateY(" + rotateDeg + "deg)" + " translateZ(" + radius + "rpx)"
				}
			},
			preventTouchMove() {
			}
		}
	}
</script>

<style scoped>
	.xy-loading {
		opacity: var(--opacity);
		position: fixed;
		width: 100%;
		z-index: 999;
		top: 0;
		bottom: 0;
		background-color:#ffffff;
		display: flex;
		flex-direction: column;
		align-items: center;
	  color: var(--color);
	}
	
	.cylinder {
	  animation: tiltSpin 8s linear infinite;
		transform: rotateX(30deg);
		display: flex;
	  flex-direction: column;
	  justify-content: center;
	  align-items: center;
	  margin-top: var(--marginTop);
		transform-style: preserve-3d;
	}

	.circle {
		transform-style: preserve-3d;
	  animation-name: spin;
	  animation-duration: 4s;
	  animation-timing-function: linear;
	  animation-iteration-count: infinite;
	  font-size: 20rpx;
	  position: relative;
	  height: 40rpx;
	}
	.circle:nth-child(even) {
	  animation-direction: reverse;
	}
	.sector {
	  font-weight: 600;
	  position: absolute;
	  top: 0;
	  left: 0;
	  text-align: center;
	  text-transform: uppercase;
	}
	.sector, .sector:empty:before {
	  display: inline-block;
	  width: 100%;
	  height: 100%;
	}
	.sector:empty:before {
	  background: linear-gradient(transparent 45%, currentColor 45% 55%, transparent 55%);
	  content: "";
	}
	
	/* Animations */
	@keyframes tiltSpin {
	  from {
	    transform: rotateY(0) rotateX(30deg);
	  }
	  to {
	    transform: rotateY(1turn) rotateX(30deg);
	  }
	}
	@keyframes spin {
	  from {
	    transform: rotateY(0);
	  }
	  to {
	    transform: rotateY(1turn);
	  }
	}
</style>
