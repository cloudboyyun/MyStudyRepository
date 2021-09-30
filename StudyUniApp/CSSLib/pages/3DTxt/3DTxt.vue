<template>
	<view class="middle">
		<view class="scene">
			<view class="box" :style = "{transform: boxStyle}">
				<view class="ring">
					<view v-for="(item, index) in liArr" :key="index" class='li'
						:style="{transform: item.style.transform}">{{item.content}}</view>
				</view>
			</view>
			<view class="item">
				<p>{{content}}</p>
				<a href="javascript:;" class="close"></a>
			</view>
		</view>
		<view class="text">
			<p>在下面输入你自己的文字（建议50-350字）</p>
			<textarea cols="30" rows="10"></textarea>
			<div class="btn">
				<button>提交</button>
				<button>重置</button>
			</div>
			<a href="javascript:;" class="close"></a>
		</view>
		<view class="table">
			<view v-for="(item,index) in graphTypes" :key="index"
				class='graphtype' :class="{'active': (index == selectGraphType)}"
				@click="onGraphTypeClick(index)"
			>{{item}}</view>
		</view>
		<view class="line"></view>
		<view class="btn2">
			<view class='button'>展开</view>
			<view class='button'>自定义文字</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				content: '北国风光，千里冰封，万里雪飘。望长城内外，惟余莽莽；大河上下，顿失滔滔。山舞银蛇，原驰蜡象，欲与天公试比高。须晴日，看红装素裹，分外妖娆。江山如此多娇，引无数英雄竞折腰。惜秦皇汉武，略输文采；唐宗宋祖，稍逊风骚。一代天骄，成吉思汗，只识弯弓射大雕。俱往矣，数风流人物，还看今朝。',
				graphTypes: ['圆球', '圆锥', '柱状', '柱状扭曲'],
				selectGraphType: 0,
				r: 150,
				boxStyle: '',
				liArr: [],
				circleArr: [],
				coneArr: [],
				coneNum: 0,
				iTimer: 0,
				liNub: 0,
				iTimer2: 0,
				columnH: 0,
				columnNum: 0
			}
		},
		computed: {
		},
		onLoad (options) {
			this.star();
			let angleX = 0;
			let angleY = 0;
			let that = this;
			this.iTimer = setInterval(function() {
				angleY -= 3;
				that.boxStyle = 'rotateX(' + angleX + 'deg) rotateY(' + angleY + 'deg)';
			}, 60);
			clearInterval(this.iTimer);
		},
		methods: {
			star() {
				// layer是什么？
				let layer = 0, num = 0;
				for (let i = 4; i < 13; i++) {
					num = i * i + (i + 1) * (i + 1);
					if (num >= this.content.length) {
						layer = (i - 1) * 2 + 1;
						break;
					}
					layer = (i - 1) * 2 + 1;
				}
				
				// 圆球数据
				let wordNum = -1;
				this.circleArr = [];
				for (let i = 0; i < layer; i++) {
					if (i < (layer + 1) / 2) {
						wordNum += 2;
					} else {
						wordNum -= 2;
					}
					this.circleArr.push(wordNum);
				}
				num = 0;
				let theta = 0, phi = 0;
				for (let i = 0; i < this.circleArr.length; i++) {
					theta = Math.PI / this.circleArr.length;
					phi = 2 * Math.PI / this.circleArr[i];
					for (let j = 0; j < this.circleArr[i]; j++) {
						let li = {
							content: this.content[num],
							style:{}
						};
						num++;
						this.drawCircle(li, theta, phi, i, j);
						this.liArr.push(li);
					}
				}
				
				// 圆锥数据
				// 柱状和柱状扭曲数据
				
				for (var i = 0; i < this.liArr.length; i++) {
					let attrObj = {
						transform: 'translate3D(' + this.liArr[i].circleX + 'rpx,'
							+ this.liArr[i].circleY + 'rpx,' + this.liArr[i]
							.circleZ + 'rpx) rotateY(' + this.liArr[i].circlePhi + 'rad) rotateX(' 
							+ (this.liArr[i].circleTheta -
								Math.PI / 2) + 'rad)'
						};
					this.setCss3(this.liArr[i], attrObj);
				}
			},
			
			setCss3(obj, attrObj) {
				for (var i in attrObj) {
					var newi = i;
					if (newi.indexOf("-") > 0) {
						var num = newi.indexOf("-");
						newi = newi.replace(newi.substr(num, 2), newi.substr(num + 1, 1).toUpperCase());
					}
					obj.style[newi] = attrObj[i];
					// newi = newi.replace(newi.charAt(0), newi.charAt(0).toUpperCase());
					// obj.style["webkit" + newi] = attrObj[i];
					// obj.style["moz" + newi] = attrObj[i];
					// obj.style["o" + newi] = attrObj[i];
					// obj.style["ms" + newi] = attrObj[i];
				}
			},
			
			drawCircle(obj, theta, phi, i, j) {
				let r = this.r;
				obj.circleX = r * Math.sin(theta * i) * Math.sin(phi * j) + 200;
				obj.circleY = -r * Math.cos(theta * i) + 200;
				obj.circleZ = r * Math.sin(theta * i) * Math.cos(phi * j);
				obj.circleTheta = theta * (this.circleArr.length - i);
				obj.circlePhi = phi * j;
				obj.bigCircleX = (r + 2000) * Math.sin(theta * i) * Math.sin(phi * j) + 200;
				obj.bigCircleY = -(r + 2000) * Math.cos(theta * i) + 200;
				obj.bigCircleZ = (r + 2000) * Math.sin(theta * i) * Math.cos(phi * j);
				obj.maxX = obj.bigCircleX;
				obj.maxY = obj.bigCircleY;
				obj.maxZ = obj.bigCircleZ;
				obj.maxTheta = obj.circleTheta;
				obj.maxPhi = obj.circlePhi;
			
			},
			onGraphTypeClick(index) {
				this.selectGraphType = index;
			}
		}
	}
</script>

<style lang='scss'>
	$typeWidth: 50rpx;
	
	page {
		height: 100%;
		width: 100%;
		overflow: hidden;
		user-select: none;
		background-repeat: no-repeat;
		background-position: center;
		background-size: 100% 100%;
		font-family: '微软雅黑';
		background-color: #131313;
	}
	
	.middle {
		width: 100%;
		height: 100%;
		margin-top: 310rpx;
	}
	
	.scene {
		width: 400rpx;
		height: 400rpx;
		margin: 0 auto;
		perspective: 900rpx;
		position: relative;
		transform-style: preserve-3d;
		color: #16E5FF;
	}
	
	.box {
		width: 100%;
		height: 100%;
		position: relative;
		transform-style: preserve-3d;
		perspective-origin: center 250rpx;
	}
	
	.ring {
		width: 100%;
		height: 100%;
		transform-style: preserve-3d;
		position: absolute;
	}
	
	.ring .li {
		line-height: 30rpx;
		text-align: center;
		font-size: 16rpx;
		position: absolute;
		margin-top: -25rpx;
		margin-left: -15rpx;
		transition: all .6s;
	}
	
	.li.all {
		transition: all .6s cubic-bezier(.75,.25,1,1);
	}
	
	.li.one {
		transition: transform .6s cubic-bezier(0,0,.25,.75);
	}
	
	.text {
		position: absolute;
		top: 30px;
		left: 50%;
		margin-left: -329px;
		padding: 15px 18px;
		width: 602px;
		height: 318px;
		border: 1px solid #e5e5e5;
		border-radius: 8px;
		box-shadow: 0 5px 5px #d4dbe1;
		background-color: #fff;
		transform: scale(.5);
		opacity: 0;
		transition: opacity .3s cubic-bezier(.15,.84,.35,1), transform .3s cubic-bezier(.15,.84,.35,1);
		display: none;
	}
	
	.text p {
		margin-bottom: 12px;
		font-size: 12px;
	}
	
	.text textarea {
		resize: none;
		width: 580px;
		height: 220px;
		padding: 10px;
		border: 1px solid #cacaca;
		border-radius: 8px;
		background-color: #f3f7fa;
		margin-bottom: 15px;
		color: #00a0e9;
	}
	
	.text a {
		position: absolute;
		top: 10px;
		right: 10px;
		width: 19px;
		height: 19px;
		background: url("/static/icons/close.png") no-repeat center;
	}
	
	.btn {
		margin: 0 auto;
		width: 260px;
		overflow: hidden;
	}
	
	.btn button {
		width: 120rpx;
		height: 30rpx;
		border: 1rpx solid #cacaca;
		border-radius: 15rpx;
		float: left;
	}
	
	.btn button:nth-of-type(1) {
		margin-right: 18px;
	}
	
	.table {
		margin: 20rpx auto 10rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content:space-evenly;
	}
	
	.table .graphtype {
		width: 100rpx;
		height: 20rpx;
		font-size: 20rpx;
		line-height: 20rpx;
		padding-top: $typeWidth + 40rpx;
		text-align: center;
		color: #889199;
	}
	
	.table .graphtype:nth-of-type(1) {
		background: url("/static/icons/circle.png") no-repeat center 2rpx;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(2) {
		background: url("/static/icons/Cone.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}
	
	.table .graphtype:nth-of-type(3) {
		background: url("/static/icons/Column.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(4) {
		background: url("/static/icons/Column2.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(1):hover {
		background: url("/static/icons/circle-hover.png") no-repeat center 2rpx;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(2):hover {
		background: url("/static/icons/Cone-hover.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(3):hover {
		background: url("/static/icons/Column-hover.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(4):hover {
		background: url("/static/icons/Column2-hover.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(1).active {
		background: url("/static/icons/circle-active.png") no-repeat center 2px;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(2).active {
		background: url("/static/icons/Cone-active.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(3).active {
		background: url("/static/icons/Column-active.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.table .graphtype:nth-of-type(4).active {
		background: url("/static/icons/Column2-active.png") no-repeat center top;
		background-size: $typeWidth,$typeWidth;
	}
	
	.line {
		width: 734rpx;
		height: 2rpx;
		margin: 30rpx auto 14rpx;
		background: linear-gradient(left,#7ecef4,#94cc8e,#fff45c);
	}
	
	.btn2 {
		width: 280rpx;
		margin: 0 auto;
		display: flex;
		flex-direction: row;
		text-align: center;
	}
	
	.btn2 .button {
		width: 140rpx;
		height: 30rpx;
		border: 1rpx solid #cacaca;
		font-size: 24rpx;
	}
	
	.btn2 .button:nth-of-type(1) {
		border-radius: 15rpx 0 0 15rpx;
	}
	
	.btn2 .button:nth-of-type(2) {
		margin-left: -1rpx;
		border-radius: 0 15rpx 15rpx 0;
	}
	
	.button {
		background: linear-gradient(#fff,#e3ecf3);
		color: #889199;
	}
	
	.button:hover {
		background: linear-gradient(#fff,#bef5ff 10%,#6cb7df);
		border-color: #bef5ff;
		color: #00a0e9;
	}
	
	.button:active {
		background: linear-gradient(#fff,#6cb7df 10%,#bef5ff);
	}
	
	.button.active {
		background: linear-gradient(#fff,#6cb7df 10%,#bef5ff);
	}
	
	.item {
		width: 400rpx;
		padding: 30rpx;
		top: 100rpx;
		margin: -30rpx;
		position: fixed;
		transform: scale(2);
		opacity: 0;
		display: none;
		transform-origin: center center 200rpx;
		opacity: 0;
		transition: all .5s ease;
	}
	
	.item a {
		position: absolute;
		top: 10px;
		right: 10px;
		width: 19px;
		height: 19px;
		background: url("/static/icons/close.png") no-repeat center;
	}
	
	.item p {
		font-size: 14px;
		line-height: 24px;
		word-break: break-all;
	}
</style>
