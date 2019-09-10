<template xmlns:v-popover="http://www.w3.org/1999/xhtml">
  <!--底部导航组件-->
  <div id="backc">
    <mt-tab-container v-model="selected" swipeable>
      <mt-tab-container-item id="微信">
        <router-view></router-view>
        <mt-loadmore :top-method="loadTop" ref="loadmore" top-drop-text="松开更新" class="ma"
                     top-loading-text="加载中..." style="margin-bottom: 54px;">
          <div v-for="(li,index) in ChatArr" :key="index" @click.passive="add(li.cId)">
            <mt-cell-swipe :title="li.dRemark"
                           :to="`/listcontent1/${li.dIdPk}`"
                           :right="
                            [{
                              content: '删除',   //显示的名字
                              style: {background: 'red', color: '#fff'},  //背景色和字体颜色
                              handler: () => { //点击后的事件
                               del(li.cId);  //调用根据聊天表的c_id进行删除
                              }
                            }
                          ]">
              <span>{{li.cTime}}</span>
              <img slot="icon" :src="li.cPorimage" style="border-radius: 6px;" width="40" height="40">
            </mt-cell-swipe>
          </div>
        </mt-loadmore>
      </mt-tab-container-item>
      <mt-tab-container-item id="通讯录">
        <mt-header title="" fixed>
          <span style="font-size: 18px;color: #181818;font-weight: bold;letter-spacing: 1px;" slot="left">通讯录</span>
          <router-link :to="`/search/${id}`" slot="right">
            <img src="../../../static/image/搜索.png" class="img_right" style="border-radius: 6px;" width="22"
                 height="22" alt="">
          </router-link>
          <router-link to="" slot="right">
            <img src="../../../static/image/添加.png" class="img_right" style="border-radius: 6px;" width="22"
                 height="22" alt="" v-popover:popover3>
            <el-popover
              ref="popover3"
              placement="bottom"
              width="150"
              trigger="click"
              content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">发起群聊</span>
                <img slot="icon" src="../../assets/发起群聊.png" width="20" height="20">
              </mt-cell>
              <mt-cell title="" to="/add">
                <span style="font-size: 14px;">添加朋友</span>
                <img slot="icon" src="../../assets/添加朋友.png" width="24" height="24">
              </mt-cell>
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">扫一扫</span>
                <img slot="icon" src="../../assets/扫一扫.png" width="20" height="20">
              </mt-cell>
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">收付款</span>
                <img slot="icon" src="../../assets/收付款.png" width="20" height="20">
              </mt-cell>
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">帮助与反馈</span>
                <img slot="icon" src="../../assets/信封.png" width="20" height="20">
              </mt-cell>
            </el-popover>
          </router-link>


        </mt-header>
        <mt-index-list class="mt">
          <mt-cell title="新的朋友" class="ma" to="/addstate">
            <img slot="icon" src="../../../static/image/新的朋友.png" style="border-radius: 6px;" width="40" height="40">
          </mt-cell>
          <mt-cell title="群聊">
            <img slot="icon" src="../../../static/image/群聊.png" style="border-radius: 6px;" width="40" height="40">
          </mt-cell>
          <mt-cell title="标签">
            <img slot="icon" src="../../../static/image/标签.png" style="border-radius: 6px;" width="40" height="40">
          </mt-cell>
          <mt-cell title="公众号">
            <img slot="icon" src="../../../static/image/公众号图标.png" style="border-radius: 6px;" width="40" height="40">
          </mt-cell>

          <mt-index-section :index="the.tLetter" v-for="(the,index) in TheArr" :key="index">
            <mt-cell :title="the.tName" :to="`/info/${the.udId}`">
              <!--给这个info这个组件的路由上绑定一个参数传过去,${the.tId}获取参数-->
              <img slot="icon" :src="the.tPorimage" style="border-radius: 6px;" width="40" height="40">
            </mt-cell>
          </mt-index-section>
        </mt-index-list>

      </mt-tab-container-item>
      <mt-tab-container-item id="发现">
        <mt-header title="" fixed>
          <span style="font-size: 18px;color: #181818;font-weight: bold;letter-spacing: 1px;" slot="left">发现</span>
          <router-link :to="`/search/${id}`" slot="right">
            <img src="../../../static/image/搜索.png" style="border-radius: 6px;" class="img_right" width="22"
                 height="22" alt="">
          </router-link>
          <router-link to="" slot="right">
            <img src="../../../static/image/添加.png" style="border-radius: 6px;" class="img_right" width="22"
                 height="22" alt="" v-popover:popover2>
            <el-popover
              ref="popover2"
              placement="bottom"
              width="150"
              trigger="click"
              content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">发起群聊</span>
                <img slot="icon" src="../../assets/发起群聊.png" width="20" height="20">
              </mt-cell>
              <mt-cell title="" to="/add">
                <span style="font-size: 14px;">添加朋友</span>
                <img slot="icon" src="../../assets/添加朋友.png" width="24" height="24">
              </mt-cell>
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">扫一扫</span>
                <img slot="icon" src="../../assets/扫一扫.png" width="20" height="20">
              </mt-cell>
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">收付款</span>
                <img slot="icon" src="../../assets/收付款.png" width="20" height="20">
              </mt-cell>
              <mt-cell title="" to="/">
                <span style="font-size: 14px;">帮助与反馈</span>
                <img slot="icon" src="../../assets/信封.png" width="20" height="20">
              </mt-cell>
            </el-popover>
          </router-link>
        </mt-header>


        <mt-cell title="朋友圈" is-link class="ma" style="" :to="`/circle/${id}`">
          <img slot="icon" src="../../../static/image/朋友圈.png" width="22" height="22">
        </mt-cell>
        <mt-cell title="扫一扫" is-link style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/扫一扫.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="摇一摇" is-link>
          <img slot="icon" src="../../../static/image/摇一摇.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="看一看" is-link style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/看一看.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="搜一搜" is-link>
          <img slot="icon" src="../../../static/image/搜一搜.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="附近的人" is-link style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/附近的人.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="漂流瓶" is-link>
          <img slot="icon" src="../../../static/image/漂流瓶.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="购物" is-link style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/购物袋.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="游戏" is-link>
          <img slot="icon" src="../../../static/image/游戏.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="小程序" is-link style="margin-top: 14px;margin-bottom: 66px;">
          <img slot="icon" src="../../../static/image/小程序.png" width="24" height="24">
        </mt-cell>
      </mt-tab-container-item>


      <mt-tab-container-item id="我">
        <div style="background: #ffffff;height: 160px;color: #353535;">
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <div class="grid-content" style="float: right;margin-top: 14px;margin-right: 14px;">
                <img src="../../../static/image/相机.png" width="20" height="18">
              </div>
            </el-col>
          </el-row>

          <el-row v-for="user in UserArr">
            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
              <div class="grid-content" style="">
                <img :src="user.uImage" width="70" height="70"
                     style="border-radius: 6px;margin-top: 22px;margin-left: 24px;">
              </div>
            </el-col>
            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
              <div class="grid-content" style="margin-left: 19px;">
                <h3>{{user.uName}}</h3>
                <span
                  style="font-size: 14px;display:block;margin-bottom: 4px; color:#7f7f7f;">微信号：{{user.uNumber}}</span>
              </div>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
              <div class="grid-content" style="float: right;margin-top: 66px;;">
                <img src="../../../static/image/二维码.png" width="22" height="22" style="margin-right: 2px;">
                <router-link to="/personal">
                  <img src="../../../static/image/向右箭头.png" width="22" height="22" style="margin-right: 10px;">
                </router-link>
              </div>
            </el-col>
          </el-row>
        </div>
        <mt-cell title="支付" is-link class="ma" style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/支付图标_微信.png" width="22" height="22">
        </mt-cell>
        <mt-cell title="收藏" is-link style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/收藏.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="相册" is-link>
          <img slot="icon" src="../../../static/image/相册.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="卡包" is-link>
          <img slot="icon" src="../../../static/image/卡包.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="表情" is-link>
          <img slot="icon" src="../../../static/image/表情.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="设置" is-link style="margin-top: 14px;">
          <img slot="icon" src="../../../static/image/设置.png" width="24" height="24">
        </mt-cell>
      </mt-tab-container-item>
    </mt-tab-container>


    <mt-tabbar v-model="selected" fixed>
      <mt-tab-item id="微信">
        <img slot="icon" src="../../../static/image/评论.png" alt="">
        微信
      </mt-tab-item>
      <mt-tab-item id="通讯录">
        <img slot="icon" src="../../../static/image/通讯录.png" alt="">
        通讯录
      </mt-tab-item>
      <mt-tab-item id="发现">
        <img slot="icon" src="../../../static/image/发现.png" alt="">
        发现
      </mt-tab-item>
      <mt-tab-item id="我">
        <img slot="icon" src="../../../static/image/我.png" alt="">
        我
      </mt-tab-item>
    </mt-tabbar>
  </div>
</template>

<script>

  import {mapState} from 'vuex'   //引入mapState去读取vuex中todos中的数据,一般把获取数据都写在computed(计算属性)计算属性中
  import axios from 'axios'
  import {MessageBox} from 'mint-ui'

  export default {
    data () {
      return {
        selected: '微信',
        count: 1,
        id: '', //当前用户id
        ChatArr: [],
        addfriendJid: '',
        informjid: '',
      }
    },
    methods: {
      add (cid) {  //向vuex数据共享区保存数据
        this.$store.dispatch('saveAcceptId', cid)
      },
      loadTop () {  //下拉刷新
        setTimeout(() => {  //用这个可以延迟执行就能显示加载中...
          this.$refs.loadmore.onTopLoaded()  //调用刷新方法
          //再去后台获取数据区
        }, 1000)
      },
      del (cid) { //根据c_id进行删除聊天表的信息
        axios.post('/chat/deleteById/' + cid).then(res => { //根据c_id删除聊天用户表信息
          axios.post('/chat/getAllById/' + this.id).then(res => { //再根据用户id查询聊天信息
            this.ChatArr = res.data
          })
        })
      }
    },
    mounted () { //页面加载完成执行,一般用来发异步请求,这儿用了vuex就分发到actions.js中去发异步请求
      axios.post('/chat/getAllById/' + sessionStorage.getItem(`id`)).then(res => { //再根据用户id查询聊天信息
        console.log('临时内容', res.data)
        this.ChatArr = res.data
      })
      this.id = this.$route.params.uid
      this.$store.dispatch('findAllThe', this.$route.params.uid) //查询通讯录表,根据当前用户id查询
      this.$store.dispatch('UserGetId', this.$route.params.uid)  //这儿暂时给个用户名的id号进行查询,本来是用户登录后跳转到我的这个页面把对应的id号拿去查询用户信息的;因为暂时没有写登录的页面
      this.$store.dispatch('svaeId', this.$route.params.uid)

      axios.post(`/addfriend/getfidAndState/` + sessionStorage.getItem('id')).then(res => { //查询等待用户验证的表(看看有没有人给当前用户发送添加请求的消息)
        for (let index in res.data) {
          this.addfriendJid = res.data[index].jId
          console.log('id是', this.addfriendJid)
          if (res.data[index].state === '等待同意') {
            MessageBox.confirm('是否去查看等待验证的消息?').then(({value, action}) => { //点击确定后的函数
              console.log('确定')
              window.location.href = '#/newfriend/' + this.addfriendJid //去查看谁在添加你
            }).catch(err => {
              console.log('取消')
            })
          }
        }
      })
      axios.post(`/inform/findByFIdAndFindstate/` + sessionStorage.getItem('id')).then(res => { //查询验证后的通知表(验证用户进行了消息验证后保存在这张表里的)
        for (let index in res.data) {
          this.informjid = res.data[index].jid
          MessageBox.confirm('是否去查看对方回复的验证消息?').then(({value, action}) => { //点击确定后的函数
            //点击了查看后去数据库更新添加表的查看状态
            axios.post(`/inform/updateJidState/` + res.data[index].jid).then(res => {
              if (res.status === 200) {
                window.location.href = '#/replyinfo/' + this.informjid //去查看谁在拒绝或者同意了你
              }
            })
          }).catch(err => {
            console.log('取消')
          })
        }
      })

      axios.post(`/deta/findByTId/` + sessionStorage.getItem(`id`)).then(res => { //查询用户详细表城市d_area和d_sex是否为空,为空就提示用户是否去完善资料
        if (res.data.dArea === `` || res.data.dSex === ``) {
          if (sessionStorage.getItem(`countT`) === `1`) { //登录页面
            MessageBox.confirm('是否去完善资料信息?').then(({value, action}) => { //点击确定或者取消后的按钮都把在登录页面保存的countT键值覆盖
              sessionStorage.setItem(`countT`, `2`)
              window.location.href = `#/personal`
            }).catch(err => {
              sessionStorage.setItem(`countT`, `2`)
              console.log(`不去完善`, sessionStorage.getItem(`countT`))
            })
          }
        }
      })
    },
    computed: {  //获取通讯录数组中的数据
      ...mapState(['TheArr']),  //直接获取要拿的数组名字
      ...mapState(['UserArr']),  //用户信息
    }
  }
</script>

<style scoped>
  .mint-tabbar > .mint-tab-item.is-selected {
    color: #08c261;
    background: #f7f7f7;
  }

  .ma {
    margin-top: 58px;
  }

  .mt {
    margin-bottom: 50px;
  }

  .mint-tabbar.is-fixed {
    background: #f7f7f7;
  }

  .mint-cell-text {
    margin-left: 10px;
  }

  #backc {
    position: absolute;
    width: 100%;
    top: 0px;
    background: #ededed;
    height: 100%;
  }

  .mint-cell-value {
    color: #040404;
  }

  .el-popover--plain {
    padding: 0px;
  }

  .element.style {
    left: 222px;
  }

  .el-popper[x-placement^=bottom] .popper__arrow::after {
    top: 1px;
    margin-left: -2px;
    border-top-width: 0;
    border-bottom-color: #fff;
  }

  /****************************************************/

  .img_right {
    margin-left: 10px;
  }

  .mint-header {
    background-color: #ededed;
    height: 58px;
  }

  .mint-cell-value {
    color: #040404;
  }

  .el-popover--plain {
    padding: 0px;
  }

  .element.style {
    left: 222px;
  }

  .el-popper[x-placement^=bottom] .popper__arrow::after {
    top: 1px;
    margin-left: -2px;
    border-top-width: 0;
    border-bottom-color: #fff;
  }
</style>
