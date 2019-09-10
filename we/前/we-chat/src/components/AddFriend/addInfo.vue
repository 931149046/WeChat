<template>
  <div id="Iid">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="22" height="22" onclick="window.history.go(-1)">
      </router-link>
    </mt-header>
    <div v-for="re in resArr">
      <el-row style="margin-top: 57px;height: 124px;background: white;">
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <div class="grid-content" style="margin-top: 4px;">
            <img :src="re.uImage" width="60" height="60" style="border-radius: 6px;margin-left: 28px;">
          </div>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <span style="font-size: 20px;font-weight: bold;letter-spacing: 1px;">{{re.uName}}</span> <br>
              <span style="font-size: 12px;color: #999999;">微信号：{{re.uNumber}}</span><br/>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <div style="margin-top: 10px;">
        <el-row style="background: white;min-height: 48px;margin-top: 1px;"
                @click.native.passive="addUser(re.uName,re.uImage,re.uId)">
          <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
            <router-link to="">
              <div style="position: absolute; margin-left: 39%;line-height: 48px;color: #586b95;">
                <span>添加到通讯录</span>
              </div>
            </router-link>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import {MessageBox} from 'mint-ui'
  import {Toast} from 'mint-ui'

  export default {
    data () {  //组件的数据区
      return {
        resArr: [], //信息数组
      }
    },
    methods: {  //方法区
      addUser (name, image, uid) { //添加好友
        console.log('接收id',uid)
        MessageBox.prompt('请输入验证消息',).then(({value, action}) => {
          axios.post(`/addfriend/insert?&uid=` + sessionStorage.getItem('id') + `&uname=` + sessionStorage.getItem('uname') + `&value=` + value + `&udid=` + uid).then(res => {
            if (res.status === 200) {
              Toast({
                message: '发送成功,等待对方接受',
                duration: 1000
              })
              setTimeout(() => {
                window.location.href = '#/tabbar/head/' + sessionStorage.getItem('id')
              }, 1200)
            }
          })
        }).catch(error => {
          console.log('取消')
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      let id = this.$route.params.id //获取url中的id;id是获取的占位符变量名
      axios.post('/user/getById/' + id).then(res => {
        console.log('内容', res.data)
        this.resArr = res.data
      })
    }
  }
</script>

<style scoped>
  .mint-header {
    background-color: #ffffff;
    height: 58px;
  }

  body {
    background: #ededed;
  }

  a {
    text-decoration: none;
    color: #333;
  }

  #Iid {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ededed;
  }
</style>
