<template>
  <div id="Iid">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="22" height="22" onclick="window.history.go(-1)">

        <span style="color: #282828; display: block;margin-top: -21px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">聊天记录</span>
      </router-link>
    </mt-header>

    <!--这是最开始的样式-->
    <div style="margin-top: 60px; height:92%;overflow-y:auto;">
      <ul>
        <li v-for="val in messageArr"> <!--发送人显示的-->
          <!--要想发送人和接收人显示的位置不一样的话,在后台加一个内容作为标识,用v-if和v-else进行判断控制显影(也就是发送方和接收方谁显示)-->
          <div v-if="val.category === '发送'">
            <div style="display: flex;justify-content: center;font-size: 10px;color: #a6a6a6;">{{val.chTime}}
            </div>
            <div class="message" style="margin-top: 16px;">
              <div style="background: #b2e281;border-radius: 6px;">
                <span style="padding: 10px; display: block;line-height: 8px;">{{val.chContent}}</span>
              </div>
              <div
                style=" width: 0;height: 0;border: 6px solid;border-color: transparent transparent transparent #b2e281;margin-top: 10px;"></div>
              <img :src="val.chImage" width="30" height="30" style="border-radius: 4px;margin-right: 6px;"/>
            </div>
          </div>

          <div v-else> <!--接收人显示的-->
            <div style="display: flex;justify-content: center;font-size: 10px;color: #a6a6a6;">{{val.chTime}}
            </div>
            <div style="margin-top: 16px;display: flex; flex-direction: row;justify-content: flex-start;">
              <img :src="val.chImage" width="30" height="30" style="border-radius: 4px;margin-left: 6px;"/>
              <div
                style=" width: 0;height: 0;border: 6px solid;border-color: transparent white transparent transparent ;margin-top: 10px;"></div>
              <div style="background: white;border-radius: 6px;">
                <span style="padding: 10px; display: block;line-height: 8px;">{{val.chContent}}</span>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data () {  //组件的数据区
      return {
        messageArr: []
      }
    },
    methods: {  //方法区

    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      console.log('获取的地址', this.$route.params.id)
      axios.post(`/chatt/getChUidOrChFid/` + sessionStorage.getItem('id')).then(res => {
        console.log('获取的数据',res.data)
        this.messageArr = res.data
      })
    }
  }
</script>

<style scoped>
  #Iid {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ededed;
  }

  body {
    background: #ededed;
  }

  a {
    text-decoration: none;
    color: #333;
  }

  .mint-header {
    background-color: #ededed;
    height: 58px;
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
