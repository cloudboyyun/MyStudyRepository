<template>
	<view class="middle">
		<view class="scene" @touchstart='onTouchStart' @touchmove="onTouchMove" @touchend='onTouchEnd'>
			<view class="box" :style="{transform: boxStyle}">
				<view class="ring">
					<view v-for="(item, index) in liArr" :key="index" class='li' :class="item.className"
						:style="{transform: item.style.transform,
							opacity: item.style.opacity,
							transition: item.style.transition}">{{item.content}}</view>
				</view>
			</view>
			<view class="item" :style='{transform: oItem.transform,opacity: oItem.opacity}'>
				<view class='content'>{{content}}</view>
				<view class='a' @click="onExpandCloseButtonClick"></view>
			</view>
		</view>
		<view class="text" 
			:style='{opacity: oText.opacity, 
							display: oText.display,
							transform: oText.transform}'>
			<view class='p'>在下面输入你自己的文字（建议50-350字）</view>
			<textarea cols="30" rows="10" :value="oText.value" auto-height=""></textarea>
			<view class="btn">
				<view class='button'>提交</view>
				<view class='button'>重置</view>
			</view>
			<view class='a' @click="onCloseDefineTextClick"></view>
		</view>
		<view class="table">
			<view v-for="(item,index) in graphTypes" :key="index" class='graphtype'
				:class="{'active': (index == selectGraphType)}" @click="onGraphTypeClick(index)">{{item}}</view>
		</view>
		<view class="line"></view>
		<view class="btn2">
			<view class='button' @click="onExpandClick">展开</view>
			<view class='button' @click='onDefineTextClick'>自定义文字</view>
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
				radius: 200,
				boxStyle: '',
				liArr: [],
				circleArr: [],
				coneArr: [],
				coneNum: 0,
				iTimer: 0,
				angleX: 0,
				angleY: 0,
				startX: 0,
				startY: 0,
				disX: 0,
				disY: 0,
				isExpanded: false,
				oItem: {},
				oText: {
					display: 'none',
					opacity: 0,
					transform: 'scale(.5)',
					value: ""
				},
				columnNum: 0,
				iTimer2: 0,
			}
		},
		computed: {},
		onLoad(options) {
			this.oText.value = this.content;
			console.log(this.oText.value);
			this.star();
			let that = this;
			this.iTimer = setInterval(function() {
				that.angleY -= 3;
				that.boxStyle = 'rotateX(' + that.angleX + 'deg) rotateY(' + that.angleY + 'deg)';
			}, 60);
		},
		methods: {
			star() {
				let layer = 0,
					num = 0;
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
				let theta = 0,
					phi = 0;
				for (let i = 0; i < this.circleArr.length; i++) {
					theta = Math.PI / this.circleArr.length;
					phi = 2 * Math.PI / this.circleArr[i];
					for (let j = 0; j < this.circleArr[i]; j++) {
						let li = {
							content: this.content[num],
							style: {}
						};
						num++;
						this.drawCircle(li, theta, phi, i, j);
						this.liArr.push(li);
					}
				}

				// 圆锥数据
				for (let i = 0; i < this.liArr.length; i++) {
					this.coneNum += 2 * i + 1;
					if (this.coneNum > this.liArr.length) {
						this.coneNum -= 2 * i + 1;
						break;
					}
					this.coneArr.push(2 * i + 1);
				}

				let liNub = 0;
				for (let i = 0; i < this.coneArr.length; i++) {
					phi = 2 * Math.PI / this.coneArr[i];
					for (var j = 0; j < this.coneArr[i]; j++) {
						this.drawCone(this.liArr[liNub], phi, i, j);
						liNub++;
					}
				}

				// 柱状数据和柱状扭曲数据
				liNub = 0;
				let columnH = Math.floor(this.liArr.length / (this.circleArr.length - 2));
				this.columnNum = columnH * (this.circleArr.length - 2);
				for (var i = 0; i < this.circleArr.length - 1; i++) {
					phi = 2 * Math.PI / columnH;
					for (var j = 0; j < columnH; j++) {
						this.drawColumn(this.liArr[liNub], phi, i, j);
						this.drawColumn2(this.liArr[liNub], phi, i, j);
						liNub++;
					}
				}

				// 
				for (var i = 0; i < this.liArr.length; i++) {
					this.liArr[i].style.transform = 'translate3D(' + this.liArr[i].circleX + 'rpx,' +
						this.liArr[i].circleY + 'rpx,' + this.liArr[i]
						.circleZ + 'rpx) rotateY(' + this.liArr[i].circlePhi + 'rad) rotateX(' +
						(this.liArr[i].circleTheta - Math.PI / 2) + 'rad)';
				}
			},

			drawCircle(obj, theta, phi, i, j) {
				let r = this.radius;
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

			drawCone(obj, phi, i, j) {
				let r = this.radius;
				obj.coneX = (2 * r / this.coneArr.length) * i * Math.tan(30 * Math.PI / 180) * Math.sin(phi * j) + 200;
				obj.coneY = (2 * r / this.coneArr.length) * i + 50;
				obj.coneZ = (2 * r / this.coneArr.length) * i * Math.tan(30 * Math.PI / 180) * Math.cos(phi * j);
				obj.coneTheta = Math.PI / 6;
				obj.conePhi = phi * j;
				obj.bigConeX = (2 * (r + 2000) / this.coneArr.length) * i * Math.tan(30 * Math.PI / 180) * Math.sin(phi *
						j) +
					200;
				obj.bigConeY = (2 * (r + 2000) / this.coneArr.length) * i + 50 - 2000;
				obj.bigConeZ = (2 * (r + 2000) / this.coneArr.length) * i * Math.tan(30 * Math.PI / 180) * Math.cos(phi *
					j);
			},

			drawColumn(obj, phi, i, j) {
				let r = this.radius;
				obj.columnX = r / 1.5 * Math.sin(phi * j) + 200;
				obj.columnY = (2 * r / (this.circleArr.length - 2)) * i + 50;
				obj.columnZ = (r / 1.5 * Math.cos(phi * j)).toFixed(2);
				obj.columnPhi = phi * j;
				obj.bigColumnX = (r + 2000) / 1.5 * Math.sin(phi * j) + 200;
				obj.bigColumnY = (2 * (r + 2000) / (this.circleArr.length - 2)) * i + 50 - 2000;
				obj.bigColumnZ = ((r + 2000) / 1.5 * Math.cos(phi * j)).toFixed(2);
			},

			drawColumn2(obj, phi, i, j) {
				let r = this.radius;
				obj.column2X = r / 1.5 * Math.sin(phi * j + i * Math.PI / 180 * 8) + 200;
				obj.column2Y = (2 * r / (this.circleArr.length - 2)) * i + 50;
				obj.column2Z = (r / 1.5 * Math.cos(phi * j + i * Math.PI / 180 * 8)).toFixed(2);
				obj.column2Phi = phi * j + i * Math.PI / 180 * 8;
				obj.bigColumn2X = (r + 2000) / 1.5 * Math.sin(phi * j + i * Math.PI / 180 * 8) + 200;
				obj.bigColumn2Y = (2 * (r + 2000) / (this.circleArr.length - 2)) * i + 50 - 2000;
				obj.bigColumn2Z = ((r + 2000) / 1.5 * Math.cos(phi * j + i * Math.PI / 180 * 8)).toFixed(2);
			},

			startChange() {
				for (let i = 0; i < this.liArr.length; i++) {
					this.liArr[i].style.transition = 'all .6s cubic-bezier(.75,.25,1,1)';
					this.liArr[i].style.transform = 'translate3D(' + this.liArr[i].maxX + 'rpx,' +
						this.liArr[i].maxY + 'rpx,' + this.liArr[i].maxZ +
						'rpx) rotateY(' + this.liArr[i].maxPhi + 'rad) rotateX(' +
						(this.liArr[i].maxTheta - Math.PI / 2) + 'rad)';
					this.liArr[i].style.opacity = 0;
				}
			},

			fn() {
				this.oItem.isExpanded = false;
				this.oItem.transform = 'translateZ(-2000rpx) rotateX(-180deg)';
				this.oItem.opacity = 0;
				let that = this;
				setTimeout(function() {
					switch (that.selectGraphType) {
						case 0:
							that.changeCircle();
							break;
						case 1:
							that.changeCone();
							break;
						case 2:
							that.changeColumn();
							break;
						case 3:
							that.changeColumn2();
							break;
					}
					that.oItem.transform = 'translateZ(0px) scale(2)';
				}, 600);
			},

			changeCircle() {
				let that = this;
				for (let i = 0; i < that.columnNum; i++) {
					that.liArr[i].style.transition = '';
					that.liArr[i].maxX = that.liArr[i].bigCircleX;
					that.liArr[i].maxY = that.liArr[i].bigCircleY;
					that.liArr[i].maxZ = that.liArr[i].bigCircleZ;
					that.liArr[i].maxTheta = that.liArr[i].circleTheta;
					that.liArr[i].maxPhi = that.liArr[i].circlePhi;
					that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].maxX + 'rpx,' 
						+ that.liArr[i].maxY + 'rpx,' +
						that.liArr[i].maxZ + 'rpx) rotateY(' +
						that.liArr[i].maxPhi + 'rad) rotateX(' +
						(that.liArr[i].maxTheta - Math.PI / 2) + 'rad)';
				}
				setTimeout(function() {
					for (let i = 0; i < that.liArr.length; i++) {
						that.liArr[i].style.transition = 'transform .6s cubic-bezier(0,0,.25,.75)';
						that.liArr[i].style.opacity = 1;
						that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].circleX + 'rpx,' +
							that.liArr[i].circleY + 'rpx,' +
							that.liArr[i].circleZ + 'rpx) rotateY(' +
							that.liArr[i].circlePhi + 'rad) rotateX(' +
							(that.liArr[i].circleTheta - Math.PI / 2) + 'rad)'
					}
				}, 100);
			},

			changeCone() {
				let that = this;
				for (let i = 0; i < that.coneNum; i++) {
					that.liArr[i].style.transition = '';
					that.liArr[i].maxX = that.liArr[i].bigConeX;
					that.liArr[i].maxY = that.liArr[i].bigConeY;
					that.liArr[i].maxZ = that.liArr[i].bigConeZ;
					that.liArr[i].maxPhi = that.liArr[i].conePhi;
					that.liArr[i].maxTheta = that.liArr[i].coneTheta;
					that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].maxX + 'rpx,' +
						that.liArr[i].maxY + 'rpx,' + that.liArr[i].maxZ +
						'rpx) rotateY(' + that.liArr[i].maxPhi + 'rad) rotateX(' +
						that.liArr[i].maxTheta + 'rad)';
				}
				setTimeout(function() {
					for (var i = 0; i < that.coneNum; i++) {
						that.liArr[i].style.transition = 'transform .6s cubic-bezier(0,0,.25,.75)';
						that.liArr[i].style.opacity = 1;
						that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].coneX + 'rpx,' +
							that.liArr[i].coneY + 'rpx,' +
							that.liArr[i].coneZ + 'rpx) rotateY(' +
							that.liArr[i].conePhi + 'rad) rotateX(' +
							that.liArr[i].coneTheta + 'rad)';
					}
				}, 100)
			},

			changeColumn() {
				let that = this;
				for (var i = 0; i < that.columnNum; i++) {
					that.liArr[i].style.transition = '';
					that.liArr[i].maxX = that.liArr[i].bigColumnX;
					that.liArr[i].maxY = that.liArr[i].bigColumnY;
					that.liArr[i].maxZ = that.liArr[i].bigColumnZ;
					that.liArr[i].maxTheta = 0;
					that.liArr[i].maxPhi = that.liArr[i].columnPhi;
					that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].maxX + 'rpx,'
						+ that.liArr[i].maxY + 'rpx,' 
						+ that.liArr[i].maxZ 
						+ 'rpx) rotateY(' + that.liArr[i].maxPhi + 'rad) rotateX(' 
						+ that.liArr[i].maxTheta + 'rad)';

				}
				setTimeout(function() {
					for (var i = 0; i < that.columnNum; i++) {
						that.liArr[i].style.transition = 'transform .6s cubic-bezier(0,0,.25,.75)';
						that.liArr[i].style.opacity = 1;
						that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].columnX + 'rpx,'
						 + that.liArr[i].columnY + 'rpx,'
						 + that.liArr[i].columnZ + 'rpx) rotateY(' 
						 + that.liArr[i].columnPhi + 'rad)';
					}
				}, 100);
			},

			changeColumn2() {
				let that = this;
				for (var i = 0; i < that.columnNum; i++) {
					that.liArr[i].style.transition = '';
					that.liArr[i].maxX = that.liArr[i].bigColumn2X;
					that.liArr[i].maxY = that.liArr[i].bigColumn2Y;
					that.liArr[i].maxZ = that.liArr[i].bigColumn2Z;
					that.liArr[i].maxTheta = 0;
					that.liArr[i].maxPhi = that.liArr[i].column2Phi;
					that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].maxX + 'rpx,'
						+ that.liArr[i].maxY + 'rpx,'
						+ that.liArr[i].maxZ 
						+ 'rpx) rotateY(' + that.liArr[i].maxPhi + 'rad) rotateX(' 
						+ that.liArr[i].maxTheta + 'rad)'
				}
				setTimeout(function() {
					for (var i = 0; i < that.columnNum; i++) {
						that.liArr[i].style.transition = 'transform .6s cubic-bezier(0,0,.25,.75)';
						that.liArr[i].style.opacity = 1;
						that.liArr[i].style.transform = 'translate3D(' + that.liArr[i].column2X + 'rpx,'
						 + that.liArr[i].column2Y + 'rpx,' 
						 + that.liArr[i].column2Z + 'rpx) rotateY('
						 + that.liArr[i].column2Phi + 'rad)';
					}
				}, 100)
			},

			onGraphTypeClick(index) {
				this.selectGraphType = index;
				this.startChange();
				this.fn();
			},

			onTouchStart(e) {
				clearInterval(this.iTimer);
				this.startX = e.touches[0].clientX;
				this.startY = e.touches[0].clientY;
			},

			onTouchMove(e) {
				let x = e.touches[0].clientX;
				let y = e.touches[0].clientY;
				this.disX = x - this.startX;
				this.disY = y - this.startY;
				this.boxStyle = 'rotateX(' + (this.angleX - this.disY) + 'deg) rotateY(' +
					(this.angleY + this.disX) + 'deg)';
			},

			onTouchEnd(e) {
				this.angleX = this.angleX - this.disY;
				this.angleY = this.angleY + this.disX;
				let that = this;
				if (this.disY == 0 && this.disX == 0) {
					this.disX = -300;
				}
				this.iTimer = setInterval(function() {
					that.angleX -= that.disY / 100;
					that.angleY += that.disX / 100;
					that.boxStyle = 'rotateX(' + that.angleX + 'deg) rotateY(' + that.angleY + 'deg)';
				}, 60);
			},

			onExpandClick() {
				this.startChange();
				let that = this;
				that.oItem.isExpanded = true;
				setTimeout(function() {
					that.oItem.transform = 'scale(1)';
					that.oItem.opacity = 1;
				}, 1000);
			},

			onExpandCloseButtonClick() {
				this.fn();
			},
			
			onDefineTextClick() {
				let that = this;
				that.oText.display = 'block';
				setTimeout(function() {
					that.oText.transform = 'scale(1)';
					that.oText.opacity = 1;
				}, 12);
			},
			
			onCloseDefineTextClick() {
				let that = this;
				that.oText.transform = 'scale(0.5)';
				that.oText.opacity = 0;
				setTimeout(function() {
					that.oText.display = 'none';
				}, 60);
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
		margin-top: 200rpx;
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
		transition: all .6s cubic-bezier(.75, .25, 1, 1);
	}

	.li.one {
		transition: transform .6s cubic-bezier(0, 0, .25, .75);
	}

	.text {
		font-size: 20rpx;
		position: absolute;
		top: 200rpx;
		left: 50%;
		margin-left: -329rpx;
		padding: 15rpx 18rpx;
		width: 602rpx;
		border: 1rpx solid #e5e5e5;
		border-radius: 8rpx;
		box-shadow: 0 5rpx 5rpx #d4dbe1;
		background-color: #fff;
		transition: opacity .3s cubic-bezier(.15, .84, .35, 1), transform .3s cubic-bezier(.15, .84, .35, 1);
	}

	.text .p {
		margin-bottom: 12rpx;
		font-size: 12rpx;
	}

	.text textarea {
		resize: none;
		height: 220rpx;
		padding: 10rpx;
		border: 1rpx solid #cacaca;
		border-radius: 8rpx;
		background-color: #f3f7fa;
		margin-bottom: 15rpx;
		color: #00a0e9;
	}

	.text .a {
		position: absolute;
		top: 10rpx;
		right: 10rpx;
		width: 19rpx;
		height: 19rpx;
		background: url("/static/icons/close.png") no-repeat center;
	}

	.btn {
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items: flex-start;
		justify-content: center;
	}

	.btn .button {
		width: 120rpx;
		height: 30rpx;
		border: 1rpx solid #cacaca;
		border-radius: 15rpx;
		font-size: 10rpx;
		text-align: center;
	}

	.btn .button:nth-of-type(1) {
		margin-right: 18rpx;
	}

	.table {
		margin: 200rpx auto 10rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-evenly;
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
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(2) {
		background: url("/static/icons/Cone.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(3) {
		background: url("/static/icons/Column.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(4) {
		background: url("/static/icons/Column2.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(1):hover {
		background: url("/static/icons/circle-hover.png") no-repeat center 2rpx;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(2):hover {
		background: url("/static/icons/Cone-hover.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(3):hover {
		background: url("/static/icons/Column-hover.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(4):hover {
		background: url("/static/icons/Column2-hover.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(1).active {
		background: url("/static/icons/circle-active.png") no-repeat center 2px;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(2).active {
		background: url("/static/icons/Cone-active.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(3).active {
		background: url("/static/icons/Column-active.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.table .graphtype:nth-of-type(4).active {
		background: url("/static/icons/Column2-active.png") no-repeat center top;
		background-size: $typeWidth, $typeWidth;
	}

	.line {
		width: 734rpx;
		height: 2rpx;
		margin: 30rpx auto 14rpx;
		background: linear-gradient(left, #7ecef4, #94cc8e, #fff45c);
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
		background: linear-gradient(#fff, #e3ecf3);
		color: #889199;
	}

	.button:hover {
		background: linear-gradient(#fff, #bef5ff 10%, #6cb7df);
		border-color: #bef5ff;
		color: #00a0e9;
	}

	.button:active {
		background: linear-gradient(#fff, #6cb7df 10%, #bef5ff);
	}

	.button.active {
		background: linear-gradient(#fff, #6cb7df 10%, #bef5ff);
	}

	.item {
		width: 400rpx;
		padding: 30rpx;
		top: 100rpx;
		margin: -30rpx;
		position: fixed;
		opacity: 0;
		transform: scale(2);
		transform-origin: center center 200rpx;
		transition: all .5s ease;
	}

	.item .a {
		position: absolute;
		top: -10rpx;
		right: 0rpx;
		width: 38rpx;
		height: 38rpx;
		background: url("/static/icons/close.png") no-repeat center;
	}

	.item .content {
		font-size: 14rpx;
		line-height: 24rpx;
		word-break: break-all;
	}
</style>
