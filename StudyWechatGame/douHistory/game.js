// import './js/libs/weapp-adapter'
// import './js/libs/symbol'

// import Main from './js/main'

// new Main()

const canvas = wx.createCanvas()
const context = canvas.getContext('2d')
const {windowWidth, windowHeight} = wx.getSystemInfoSync(); // 320 568
console.log(windowWidth, windowHeight)
context.fillStyle = '#1aad19' 
const rectX = windowWidth / 2 - 50
let rectY = 0

var image = wx.createImage()
var imgLoad = false

image.onload = function() {
  imgLoad = true
}
image.src = 'images/myplane.png'
let imgX = windowWidth/2 -50
let imgY = 500

function drawRect(x, y) {
  context.clearRect(0, 0, windowWidth, windowHeight)
  context.fillRect(x, y, 100, 100)
  if(imgLoad) {
    context.drawImage(image, imgX, imgY)
  }
}

wx.onTouchMove(function (res) {
  imgX = res.changedTouches[0].clientX // 将飞机x坐标置为当前触摸点x坐标
  imgY = res.changedTouches[0].clientY // 将飞机y坐标置为当前触摸点y坐标
})

setInterval(function() {
  drawRect(rectX, rectY++);
  if (imgX >= rectX - 100 && imgX <= rectX + 100 && imgY >= rectY - 100 && imgY <= rectY + 100) { 
    wx.showModal({
      title: '提示',
      content: '发生碰撞，游戏结束！'
    })
  }
}, 16)