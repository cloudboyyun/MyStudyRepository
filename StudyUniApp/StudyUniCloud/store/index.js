// 页面路径：store/index.js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);//vue的插件机制

//Vuex.Store 构造器选项
const store = new Vuex.Store({
    state:{//存放状态
        "username":"张三",
        "age":18,
				"gender": 'F'
    },
		mutations: {
			changeGender(state, newGender) {
				state.gender = newGender;
			},
			setUserName(state, newUserName) {
				state.username = newUserName;
			}
		}
})
export default store