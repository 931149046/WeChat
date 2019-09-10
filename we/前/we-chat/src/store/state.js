//vuex的状态管理数据区
export default {
  TheArr: [],  //通讯录表的数据状态数组
  DetailArr: [],  //通讯录表详细信息的所有数据的数组,根据tid进行查询的
  //ChatArr: [], //聊天人表的数据
  UserArr: [], //根据id查询用户信息的数组
  CircleArr: [], //朋友圈的所有数据的数组
  BackImg: [],  //背景图数组
  ChattingArr: [], //聊内容数组
  DetailChatArr: [],
  uid: [sessionStorage.getItem('id')], //保存当前用户的id,从浏览器中去取数据
  //uname: [], //保存当前用户的名字
  uname: sessionStorage.getItem('uname'), //发送方用户名
  uimage: sessionStorage.getItem('uimage'), //发送发头像
  acceptId: sessionStorage.getItem('acceptId'), //接收方id
}
