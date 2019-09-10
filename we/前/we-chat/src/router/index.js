import Vue from 'vue'
import Router from 'vue-router'
import logoin from '../components/logoin/logoin'
import Tabbar from '../components/tabbar/Tabbar'
import Head from '../components/head/Head'
import ListContent from '../components/listcontent/ListContent'
import Chat from '../components/chat/Chat'
import Info from '../components/info/Info'
import Circle from '../components/circle/Circle'
import Search from '../components/search/Search'
import Register from '@/components/register/Register'  //用这个@符号就不用去找相对路径了直接代表这个组件在src下
import AddFriend from '@/components/AddFriend/AddFriend'
import twoSearch from '@/components/AddFriend/twoSearch'
import addInfo from '../components/AddFriend/addInfo'
import Record from '../components/chat/Record'
import NewFriend from '../components/tabbar/NewFriend'
import ReplyInfo from '../components/tabbar/ReplyInfo'
import AddState from '../components/tabbar/AddState'
import Publish from '../components/circle/Publish'
import Amap from '../components/circle/Amap'
import Personal from '../components/Personal/Personal'
import Upload from '../components/Personal/Upload'
import SelectSex from '../components/Personal/SelectSex'
import NameAlter from '../components/Personal/NameAlter'
import TwoCode from '../components/Personal/TwoCode'
import Area from '../components/Personal/Area'
import AreaScity from '../components/Personal/AreaScity'
import AreaSscity from '../components/Personal/AreaSscity'

Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/',
      redirect: '/logoin'
    },
    {
      path: '/tabbar',
      component: Tabbar,
      children: [
        {
          path: '/tabbar/head/:uid',  //路由的路径,访问根路径的时候就指定显示那个路由组件
          component: Head //指定要把
        }
      ]
    },
    {
      path: '/listcontent/:id',
      component: ListContent,
    },
    {
      path: '/listcontent1/:ids',
      component: ListContent,
    },
    {
      path: '/info/:fid',  //:id作为占位符通过其他组件中向这个info这个组件中的url中传地址带参数就用这种绑定
      component: Info
    },
    {
      path: '/chat/:id',
      component: Chat
    },
    {
      path: '/circle/:uid',
      component: Circle
    },
    {
      path: '/search/:u_id',
      component: Search
    },
    {
      path: '/logoin',
      component: logoin
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/add',
      component: AddFriend
    },
    {
      path: '/addTwo',
      component: twoSearch
    },
    {
      path: '/addInfo/:id',
      component: addInfo
    },
    {
      path: '/record/:id',
      component: Record
    },
    {
      path: '/newfriend/:id',
      component: NewFriend
    },
    {
      path: '/replyinfo/:id',
      component: ReplyInfo
    },
    {
      path: '/addstate',
      component: AddState
    },
    {
      path: '/publish',
      component: Publish
    },
    {
      path: '/amap',
      component: Amap
    },
    {
      path: '/personal',
      component: Personal
    },
    {
      path: '/upload',
      component: Upload
    },
    {
      path: '/selectsex',
      component: SelectSex
    },
    {
      path: '/namealter',
      component: NameAlter
    },
    {
      path: '/twocode',
      component: TwoCode
    },
    {
      path: '/area',
      component: Area
    },
    {
      path: '/areascity/:pid',
      component: AreaScity
    },
    {
      path: '/areasscity/:pid',
      component: AreaSscity
    }
  ]
})
