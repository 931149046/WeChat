// 接收组件(actions.js)通知触发mutation调用间更新接状态的方法对象,更新的是state数据区的数据
export default {  //其实如果用到了vuex发异步请求的话不用对state
  findAllThe (state, {dataOb}) {  //查询所有通讯录
    state.TheArr = dataOb //给通讯录的数组赋值
  },

  findAllDetail (state, {dataOb}) {  //查询所有通讯录人的详细数据
    state.DetailArr = dataOb //通讯录人的详细数据数组赋值
  },

  findAllDetail1 (state, {dataOb}) {  //查询所有通讯录人的详细数据
    state.DetailChatArr = [] //通讯录人的详细数据数组赋值
    state.DetailChatArr = dataOb
  },

  ChatGetAll (state, {dataOb}) { //查询所有的聊天人数据存在ChatArr数组中
    state.ChatArr = dataOb
  },

  UserGetId (state, {dataOb}) {  //根据id查询用户信息并且更新在UserArr数组中
    state.UserArr = dataOb
    for (let user in dataOb) {
      sessionStorage.setItem('uname', dataOb[user].uName)
      sessionStorage.setItem('uimage', dataOb[user].uImage)
    }
  },

  /*CircleGetById (state, {dataOb}) {  //根据id查询用户信息并且更新在UserArr数组中
    state.CircleArr = dataOb
  },*/

  CircleGetByIdImg (state, {dataOb}) {  //根据id查询用户信息并且更新在BackImg数组中
    state.BackImg = dataOb
  },
  getChattingAllById (state, {dataOb}) {  //根据id查询聊天数据更新在ChattingArr数组中
    state.ChattingArr = dataOb
  },
  svaeId (state, {id}) { //单独保存用户id在数据状态区
    //sessionStorage.removeItem('id') //每次进来的时候就移除当前sessionStorage中的数据,根据键进行移除
    //如果需要当前组件保存的用户id一直有效的话就保存在浏览器中,用于临时保存同一窗口(或标签页)的数据，在关闭窗口或标签页之后将会删除这些数据
    sessionStorage.setItem('id', id) //setItem方法如果键名存在，则更新其对应的值
    //state.uid = []
    // state.uid.push(id)
  },
  savename (state, {name}) { //单独保存用户名在数据状态区
    state.uname = []
    state.uname.push(name)
  },

  saveAcceptId(state,id){ //单独保存接收方id
    sessionStorage.setItem('acceptId',id)
  }
}
