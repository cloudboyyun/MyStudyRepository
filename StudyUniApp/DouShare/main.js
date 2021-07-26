import Vue from 'vue'
import App from './App'
import store from './store/index.js';
import openApp from './common/openApp.js';

Vue.config.productionTip = false
openApp();
App.mpType = 'app'
const app = new Vue({
    ...App,
	store
})
app.$mount()
