<template>
	<view class='xy-loading' :style='{"--opacity": opacity,"--color": color,"--margintop": marginTop}'
		@touchmove.stop.prevent="preventTouchMove">
		<view class='cylinder'>
			<view class='circle' :style='{"width": "" + SECTION_WIDTH + "rpx"}'>
				<view class='sector' v-for="(character, i) in brandTextCharacters" :key="i"
					:style='{"transform": "rotateY(" + 360*i/DIVIDES + "deg)" + " translateZ(" + RADIUS + "rpx)"}'>
					{{character}}
				</view>
			</view>
			<view class='circle' :style='{"width": "" + SECTION_WIDTH + "rpx"}'>
				<view class='sector' v-for="(character, i) in loadingTextCharacters" :key="i"
					:style='{"transform": "rotateY(" + 360*i/DIVIDES + "deg)" + " translateZ(" + RADIUS + "rpx)"}'>
				{{character}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	const RADIUS = 100;
	const DIVIDES = 30;
	const SECTION_WIDTH = Math.ceil(2.0 * RADIUS * 3.15 / DIVIDES) + 1 ;
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
			console.log("SECTION_WIDTH", SECTION_WIDTH);
			this.loadingTextCharacters = this.loadingText.split("");
			let makeUpLength = DIVIDES - this.loadingTextCharacters.length;
			for(let i=0; i<makeUpLength; i++) {
				this.loadingTextCharacters.push('');
			}
			this.brandTextCharacters = this.brandText.split("");
			makeUpLength = DIVIDES - this.brandTextCharacters.length;
			for(let i=0; i<makeUpLength; i++) {
				this.brandTextCharacters.push('');
			}
		},
		data() {
			return {
				RADIUS: RADIUS,
				DIVIDES: DIVIDES,
				SECTION_WIDTH: SECTION_WIDTH,
				loadingTextCharacters: [],
				brandTextCharacters: []
			}
		},
		methods: {
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
		z-index: 990;
		top: 0;
		bottom: 0;
		left: 0;
		background-color:#ffffff;
		display: flex;
		flex-direction: column;
		align-items: center;
	  color: var(--color);
		perspective: 40em;
	}
	
	.cylinder {
	  animation: tiltSpin 8s linear infinite;
		transform: rotateX(30deg);
		display: flex;
	  flex-direction: column;
	  justify-content: center;
	  align-items: center;
	  margin-top: var(--margintop);
		transform-style: preserve-3d;
		z-index: 991;
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
		z-index: 992;
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
		z-index: 993;
	  /* text-transform: uppercase; */
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
