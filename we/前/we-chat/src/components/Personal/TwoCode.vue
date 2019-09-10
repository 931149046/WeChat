<template>
  <div id="code" style="display: flex;justify-content: center;align-items: center;">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">二维码名片</span>
      </router-link>
      <!--<router-link to="/personal" slot="right">
        <el-button type="success" size="small">确定
        </el-button>
      </router-link>-->
    </mt-header>
    <div style="width: 300px;height: 440px;background-color: #ffffff;">
      <ul>
        <li v-for="tw of TwoArr">
          <el-row style="margin-top: 10px;">
            <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
              <div style="display: flex;justify-content: flex-end;align-items: center;">
                <img src="http://172.16.11.230:8088/5a176d45ef774511a132af6bfbe0868e.jpg" width="46" height="46"
                     style="border-radius: 4px">
              </div>
            </el-col>
            <el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17">

              <el-row>
                <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14">
                  <div style="margin-top: 4px;">
                    <span style="display: block;margin-left: 12px;">{{tw.dRemark}}</span>
                  </div>
                </el-col>
                <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                  <div style="display: flex;justify-content: flex-start;margin-top: 6px;">
                    <img :src="tw.dSex === `男`?`../../../static/image/男.png`:`../../../static/image/女.png`" width="14"
                         height="14">
                  </div>
                </el-col>
              </el-row>
              <span style="display: block;margin-left: 12px;font-size: 12px;color: #999999;">{{tw.dArea}}</span>

            </el-col>
          </el-row>
          <div style="display: flex;justify-content: center;align-items: center;margin-top: 30px;">
            <div style="width: 240px;height: 240px;">
              <qrcode-vue :value="value" :size="size" level="H"></qrcode-vue> <!--二维码显示区-->
            </div>
          </div>
        </li>
      </ul>
      <div style="margin-top: 40px;display: flex;justify-content: center;">
        <span style="color: #999999;font-size: 12px;">扫描上面的二维码图案,加我微信</span>
      </div>
    </div>
  </div>
</template>

<script>
  import QrcodeVue from 'qrcode.vue' //引入qrcode生成二维码的组件
  import axios from 'axios'

  export default {
    data () {  //组件的数据区
      return {
        url: ``,
        value: 'http://172.16.11.230:8088/#/addInfo/', //通过下面给要进行扫码的地址赋值。二维码区域连接用的地址
        size: 240,
        TwoArr: []
      }
    },
    methods: {  //方法区

    },
    components: {  //子组件进行标签映射
      QrcodeVue  //映射生成的二维码组件成标签
    },
    mounted () {
      axios.post(`/deta/getById/` + sessionStorage.getItem(`id`)).then(res => {
        this.TwoArr = res.data
        console.log(`内容`, res.data)
        for (let i of res.data) {
          this.value += i.tId  //给要进行扫码的地址赋值
        }
      })
    }
  }
</script>

<style scoped>
  #code {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ededed;
  }

  .mint-header {
    background-color: #ededed;
    height: 48px;
  }

  a {
    color: black;
    text-decoration: none;
  }
</style>
