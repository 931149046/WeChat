// vuex最核心的配置js,也相当于创建vuex对象
import Vue from 'vue' //引入vue
import Vuex from 'vuex' //引入vuex
import state from './state' //引入数据区
import mutations from './mutations' //引入更新数据区的js
import actions from './actions' //引入分发的js
import getters from './getters'  //引入计算的js

//声明使用vuex
Vue.use(Vuex);

export default new Vuex.Store({  //创建vuex对象,向外暴露
  state,   //数据源的状态
  mutations,  //变更数据源中的状态的属性
  actions,  //Action 类似于 mutation不同在于:Action 提交的是 mutation,而不是直接变更状态Action 可以包含任意异步操作。
  getters   //用于计数的状态属性
})
