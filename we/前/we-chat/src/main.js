import Vue from 'vue'
import App from './App'
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import store from './store/store' //引入vuex对象配置的js
import axios from 'axios' //引入axios
import AMap from 'vue-amap' //引入基于 Vue 2.x 与高德的地图组件


axios.defaults.withCredentials = true//让ajax携带cookie
axios.defaults.baseURL = 'http://172.16.11.230:7000' //配置axios的访问地址

Vue.prototype.$axios = axios
Vue.use(MintUI)
Vue.use(ElementUI)
Vue.use(AMap) //声明使用地图插件

// 初始化vue-amap
AMap.initAMapApiLoader({
  // 高德key
  key: '3385cdaebefdbbba99a453e2833cf94a', //你的key
  // 插件集合 （插件按需引入）
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale','AMap.CitySearch', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor','AMap.Geolocation'],
  uiVersion: '1.0.11' // 高德地图ui组件库版本号
});


new Vue({
  el: '#app',
  router, //使用路由
  store, //使用vuex创建的对象
  components: {App},
  template: '<App/>'
})
