<template>
  <div class="listco">
    <mt-header class="muihead" title="" fixed v-for="chat in DetailChatArr" style="height: 6%;">
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="22" height="22" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -21px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;" v-text="chat.dRemark"></span>
      </router-link>
      <router-link :to="`/chat/${chat.dIdPk}`" slot="right">
        <img src="../../../static/image/三点.png" width="28" height="28">
      </router-link>
    </mt-header>

    <!--这是最开始的样式-->
    <!--<div style="margin-top: 60px; height:86%;overflow-y:auto;">
      <ul>
        <li v-for="val in messageArr">
          <div style="display: flex;justify-content: center;font-size: 10px;color: #a6a6a6;">{{val.date}}
          </div>
          <div class="message" style="margin-top: 16px;">
            <div style="background: #b2e281;border-radius: 6px;">
              <span style="padding: 10px; display: block;line-height: 8px;">{{val.content}}</span>
            </div>
            <div
              style=" width: 0;height: 0;border: 6px solid;border-color: transparent transparent transparent #b2e281;margin-top: 10px;"></div>
            <img :src="val.image" width="30" height="30" style="border-radius: 4px;margin-right: 6px;"/>
          </div>
        </li>
      </ul>
    </div>-->

    <div style="margin-top: 60px; height:86%;overflow-y:auto;">
      <ul>
        <li v-for="val in messageArr"> <!--发送人显示的-->
          <!--要想发送人和接收人显示的位置不一样的话,在后台加一个内容作为标识,用v-if和v-else进行判断控制显影(也就是发送方和接收方谁显示)-->
          <div v-if="val.category === '发送'">
            <div style="display: flex;justify-content: center;font-size: 10px;color: #a6a6a6;">{{val.date}}
            </div>
            <div class="message" style="margin-top: 12px;">
              <div style="background: #b2e281;border-radius: 6px;">
                <span style="padding: 10px; display: block;line-height: 8px;">{{val.content}}</span>
              </div>
              <div
                style=" width: 0;height: 0;border: 6px solid;border-color: transparent transparent transparent #b2e281;margin-top: 10px;"></div>
              <img :src="val.image" width="30" height="30" style="border-radius: 4px;margin-right: 6px;"/>
            </div>
          </div>

          <div v-else> <!--接收人显示的-->
            <div style="display: flex;justify-content: center;font-size: 10px;color: #a6a6a6;margin-top: 8px;">{{val.date}}
            </div>
            <div style="margin-top: 12px;display: flex; flex-direction: row;justify-content: flex-start;">
              <img :src="val.image" width="30" height="30" style="border-radius: 4px;margin-left: 6px;"/>
              <div
                style=" width: 0;height: 0;border: 6px solid;border-color: transparent white transparent transparent ;margin-top: 10px;"></div>
              <div style="background: white;border-radius: 6px;">
                <span style="padding: 10px; display: block;line-height: 8px;">{{val.content}}</span>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>


    <mt-tabbar fixed="" style="height: 40px;">
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <div class="grid-content">
          <img src="../../../static/image/语音.png" width="22" height="22" style="margin-top: 9px;">
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
        <div class="grid-content">
          <el-input @input="co" v-model="value" placeholder="请输入内容" size="medium"
                    style="line-height: 40px;"></el-input>
        </div>
      </el-col>
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <div class="grid-content">
          <img src="../../../static/image/表情灰.png" width="22" height="22" style="margin-top: 8px;">
        </div>
      </el-col>
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <div class="grid-content">
          <img :src="img" width="26" height="26" style="margin-top: 6px;" @click.passive="ScoketSend()">
        </div>
      </el-col>
    </mt-tabbar>
  </div>
</template>
<script>
  import {mapState} from 'vuex'
  import axios from 'axios' //引入axios
  import {Toast} from 'mint-ui'

  export default {
    data () {
      return {
        value: '',
        img: 'data:image/png;base64,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',
        id: '', //保存当前用户id
        name: '', //保存当前用户名
        count: 1,
        websocket: null,
        names: '',  //发送方用户名,要保存在数据共享区去
        image: '', //发送方头像,要保存在数据共享区去
        acceptIds: '', //接收方id
        messageArr: [], //消息内容的数组
        DetailChatArr: '',
      }
    },
    methods: {
      ScoketSend () {
        //console.log('接收方id:', this.acceptIds, '==发送方用户名:', this.names, '==发送方头像:', this.image, '==发送方id:', this.id[0])
        //单聊
        let obj = {
          Id: this.acceptIds, //接收方id(向数据库保存)
          Uname: this.names, //发送用户名,也就是当前用户名(用于显示在对方和自己这边的)
          Uimage: this.image,  //发送方头像地址(当前用户名的头像)
          FId: this.id[0],  //发送方(当前用户)id(向数据表保存)
          Content: this.value, //发送的内容(向数据库保存)
          //发送时间,后台去自动生成后保存在数据库中
          Type: 1 //如果有输入接收人的id就设为1,1为单聊
        }
        this.websocket.send(JSON.stringify(obj)) //发送请求
      },
      scoket () {
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
          //发送建立websocket的连接,建立连接的时候把用户名传过去
          this.websocket = new WebSocket('ws://172.16.11.230:7000/websocket/' + this.name + '/' + this.id)
        } else {
          console.log('不支持websocket')
        }
        //连接发生错误的回调方法
        this.websocket.onerror = function () {
          console.log('error')
        }
        //连接成功建立的回调方法
        this.websocket.onopen = function (event) {
          console.log('Loc MSG: 成功建立连接')
        }
        //接收到消息的回调方法
        this.websocket.onmessage = event => {  //注意这儿接收到的消息要往vue的data中赋值的话必须要用箭头函数;不然this的指向有问题,用了箭头函数this就指向了外部对象的了
          if (event.data.substr(0, 2) === '系统') { //这是发送消息后比如对方不在线和没有传id过去,后台判断返回来的数据就提示发送的用户
            //console.log(event.data)
            Toast(event.data)
          } else {
            this.messageArr.push(JSON.parse(event.data)) //只要不等于系统就赋值给内容数组
          }
        }
        //连接关闭的回调方法
        this.websocket.onclose = function () {
          console.log('Loc MSG:关闭连接')
        }
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
          this.websocket.close()
        }
      },
      co () {
        if (this.value !== null && this.value !== '') {
          //发送图片
          this.img = 'data:image/png;base64,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'
          if (this.count === 1) {  //每次进入当前组件只调用一次建立websocket的连接方法
            this.scoket() //scoket连接方法
            ++this.count
          }
        } else {
          this.img = 'data:image/png;base64,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'
        }
      }
    },
    components: {},

    computed: {  //计算属性
      //...mapState(['DetailChatArr']), //直接获取要拿的数组名字
      ...mapState(['uid']), //当前用户id
      ...mapState(['uname']),  //用户名
      //...mapState(['uimage']),  //用户头像
    },
    mounted () {
      //this.$store.dispatch('findAllDetail1', this.$route.params.id) //分发到vuex的actions.js(用于发异步提交到mutaion.js中更新数据状态)中
      //this.$store.dispatch('ChatGetById', this.$route.params.ids)  //1

      this.id = this.uid
      this.names = this.uname

      if (this.$route.params.ids !== undefined) {
        axios.post('/chat/getById/' + this.$route.params.ids).then(res => {
          this.DetailChatArr = res.data;
        })

        axios.post('/deta/getByDid/' + this.$route.params.ids).then(res => {
          console.log(this.$route.params.ids)
          this.acceptIds = res.data[0].tId
          this.image = res.data[0].dImage
        })
      } else {

        axios.post('/chat/getBydidpkId/' + this.$route.params.id).then(res => {
          this.DetailChatArr = res.data;
        })

        axios.post('/deta/getByDid/' + this.$route.params.id).then(res => {
          console.log(this.$route.params.id)
          this.acceptIds = res.data[0].tId
          this.image = res.data[0].dImage
        })
      }

      axios.post('/user/getById/' + this.id).then(res => {
        this.name = res.data[0].uName  //取出用户名
      })
    }
  }
</script>

<style scoped>

  .listco >>> .muihead {
    background: #EDEDED;
    height: 58px;
  }

  a {
    text-decoration: none;
  }

  .listco {
    position: fixed;
    width: 100%;
    top: 0px;
    background: #ededed;
    height: 100%;
  }

  .mint-toast {
    background-color: #66ff66;
  }

  .message {
    display: flex; /* 将对象作为弹性伸缩盒显示 */
    flex-direction: row; /* 横向排列,从左向右 */
    justify-content: flex-end; /* 右对齐 */
  }

</style>
