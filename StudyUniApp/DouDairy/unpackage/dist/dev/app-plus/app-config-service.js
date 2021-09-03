
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/main/main","pages/list/list","pages/grid/grid","pages/ucenter/login-page/index/index","pages/list/search/search","pages/list/detail","pages/ucenter/userinfo/bind-mobile/bind-mobile","pages/ucenter/ucenter","uni_modules/uni-feedback/pages/uni-feedback/uni-feedback","pages/ucenter/about/about","uni_modules/uni-upgrade-center-app/pages/upgrade-popup","pages/uni-agree/uni-agree","pages/ucenter/settings/settings","pages/ucenter/userinfo/userinfo","pages/ucenter/userinfo/cropImage","pages/ucenter/login-page/pwd-login/pwd-login","pages/ucenter/login-page/pwd-retrieve/pwd-retrieve","pages/ucenter/login-page/phone-code/phone-code","pages/common/webview/webview","pages/ucenter/login-page/register/register","pages/ucenter/read-news-log/read-news-log","pages/ucenter/invite/invite"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"uni-starter","navigationBarBackgroundColor":"#FFFFFF","backgroundColor":"#F8F8F8","enablePullDownRefresh":false},"nvueCompiler":"uni-app","nvueStyleCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":false,"autoclose":true},"appname":"DouDairy","compilerVersion":"3.2.3","entryPagePath":"pages/main/main","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/main/main","meta":{"isQuit":true},"window":{"navigationBarTitleText":"云日历","navigationStyle":"custom","enablePullDownRefresh":false,"disableScroll":true}},{"path":"/pages/list/list","meta":{},"window":{"navigationStyle":"custom","enablePullDownRefresh":true}},{"path":"/pages/grid/grid","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/ucenter/login-page/index/index","meta":{},"window":{"navigationBarTitleText":"","animationType":"none","popGesture":"none"}},{"path":"/pages/list/search/search","meta":{"isNVue":true},"window":{"navigationStyle":"custom"}},{"path":"/pages/list/detail","meta":{},"window":{"navigationBarTitleText":"文章详情","titleNView":{"buttons":[{"type":"share"}],"type":"transparent"}}},{"path":"/pages/ucenter/userinfo/bind-mobile/bind-mobile","meta":{},"window":{"navigationBarTitleText":"绑定手机号码"}},{"path":"/pages/ucenter/ucenter","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/uni_modules/uni-feedback/pages/uni-feedback/uni-feedback","meta":{},"window":{"navigationBarTitleText":"问题反馈"}},{"path":"/pages/ucenter/about/about","meta":{},"window":{"navigationBarTitleText":"关于","titleNView":{"buttons":[{"type":"share"}]}}},{"path":"/uni_modules/uni-upgrade-center-app/pages/upgrade-popup","meta":{},"window":{"disableScroll":true,"backgroundColorTop":"transparent","background":"transparent","titleNView":false,"scrollIndicator":false,"popGesture":"none","animationType":"fade-in","animationDuration":200}},{"path":"/pages/uni-agree/uni-agree","meta":{"isNVue":true},"window":{"navigationStyle":"custom","popGesture":"none"}},{"path":"/pages/ucenter/settings/settings","meta":{},"window":{"navigationBarTitleText":"设置"}},{"path":"/pages/ucenter/userinfo/userinfo","meta":{},"window":{"navigationBarTitleText":"个人资料"}},{"path":"/pages/ucenter/userinfo/cropImage","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/ucenter/login-page/pwd-login/pwd-login","meta":{},"window":{"navigationBarTitleText":""}},{"path":"/pages/ucenter/login-page/pwd-retrieve/pwd-retrieve","meta":{},"window":{"navigationBarTitleText":"重置密码"}},{"path":"/pages/ucenter/login-page/phone-code/phone-code","meta":{},"window":{"navigationBarTitleText":""}},{"path":"/pages/common/webview/webview","meta":{},"window":{"navigationBarTitleText":"","enablePullDownRefresh":false}},{"path":"/pages/ucenter/login-page/register/register","meta":{},"window":{"navigationBarTitleText":"注册","enablePullDownRefresh":false}},{"path":"/pages/ucenter/read-news-log/read-news-log","meta":{},"window":{"navigationBarTitleText":"阅读记录","enablePullDownRefresh":true}},{"path":"/pages/ucenter/invite/invite","meta":{},"window":{"navigationStyle":"custom","enablePullDownRefresh":false}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
