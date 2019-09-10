//接收组件通知触发mutation(更改vuex中store(存放整个项目中要用的数据,在state.js文件中)中的状态唯一的方法是提交mutation。也就是说需要修改vuex中)
//这个js文件也就是状态数据的配置

import axios from 'axios' //引入axios

export default {   //向外暴露接口
  /****************************通讯录表*************************************/
  findAllThe ({commit}, id) {  //查询所有通讯录
    axios.post('/the/getAllById/' + id).then(res => {
      let dataOb = res.data
      commit('findAllThe', {dataOb})
    })
  },

  findAllDetail ({commit}, id) {  //查询通讯录详细表的数据根据id
    axios.post('/deta/getById/' + id).then(res => {
      let dataOb = res.data
      commit('findAllDetail', {dataOb})
    })
  },
  /*findAllDetail1 ({commit}, id) {  //查询通讯录详细表的数据根据id
    axios.post('/deta/getById/' + id).then(res => {
      let dataOb = res.data
      commit('findAllDetail1', {dataOb})
    })
  }, */
  findAllDetail1 ({commit}, id) {  //查询通讯录详细表的数据根据id
    axios.post('/chat/getBydidpkId/' + id).then(res => {
      let dataOb = res.data
      commit('findAllDetail1', {dataOb})
    })
  },

  /*********************聊天人表*********************************/
  ChatInstall ({commit}, map) { //向聊天表中添加聊天人的信息
    let name = map.get('name')
    let porimage = map.get('porimage')
    let id = map.get('id')
    let uid = map.get('u_id')
    axios.post('/chat/install?name=' + name + '&porimage=' + porimage + '&did=' + id + '&uid=' + uid).then(res => {
      if (res.status === 200) {
        axios.post('/chat/getAll').then(res => {
          let dataOb = res.data
          commit('ChatGetAll', {dataOb})
        })
      }
    })
  },
  ChatGetAll ({commit}, uid) { //查询聊天表中所有的数据
    axios.post('/chat/getAllById/' + uid).then(res => {
      let dataOb = res.data
      commit('ChatGetAll', {dataOb})
    })
  },
  ChatGetById ({commit}, id) { //根据Id查询聊天表
    axios.post('/chat/getById/' + id).then(res => {
      let dataOb = res.data
      commit('findAllDetail1', {dataOb})
    })
  },

  ChatDeleteById ({commit}, map) { //根据c_id删除聊天表信息

  },

  /***********************用户信息表*******************************/
  UserGetId ({commit}, id) {  //根据id进行查询用户信息
    axios.post('/user/getById/' + id).then(res => {
      let dataOb = res.data
      commit('UserGetId', {dataOb})
    })
  },

  /*************************朋友圈的表*******************************************/
  CircleGetByIdImg ({commit}, id) { //根据用户id查询,只查询背景表获取它的背景图路径
    axios.post('/back/getById/' + id).then(res => {
      let dataOb = res.data
      commit('CircleGetByIdImg', {dataOb})
    })
  },

  /***********************聊天信息查询*******************************************/
  getChattingAllById ({commit}, map) { //根据用户id查询
    let id = map.get('id')
    let value = map.get('value')
    axios.post('/chatt/getAllById/' + id + '/' + value).then(res => {
      let dataOb = res.data
      commit('getChattingAllById', {dataOb})
    })
  },

  /***************************单独保存当前用户的id方法*******************************/
  svaeId ({commit}, id) {
    commit('svaeId', {id})
  },

  /***************************单独保存当前用户的名字方法*******************************/
  savename ({commit}, name) {
    commit('savename', {name})
  },

  /*******************************单独保存接收方id*******************************************************/
  saveAcceptId({commit},id){
    commit('saveAcceptId',id)
  }
}
