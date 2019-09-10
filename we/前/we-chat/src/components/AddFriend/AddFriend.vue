<template>
  <div class="div">
    <div id="seek">
      <mt-header class="muihead" title="" fixed style="height: 6%;">
        <router-link to="javascript:;" slot="left">
          <img src="../../../static/image/左箭头.png" width="18" height="18" onclick="window.history.go(-1)">
          <span style="color: #282828; display: block;margin-top: -21px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">添加朋友</span>
        </router-link>
      </mt-header>
      <el-row style="margin-top: 50px;">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <el-input
            placeholder="微信号"
            prefix-icon="el-icon-search" id="InputOne" @focus="searchOne()">
            <!--@focus="searchOne()"焦点事件当输入框获取了焦点就触发后面的方法-->
          </el-input>
        </el-col>
      </el-row>

      <div style="margin-top: 12px;color: #888888;" v-for="adduser in addArr">
        <el-row>
          <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8"
                  style="display: flex;justify-content: flex-end;padding-right: 6px;">
            我的微信号
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="display: flex;justify-content: center;">
            {{adduser.uNumber}}
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8"
                  style="display: flex;justify-content: flex-start;padding-left: 6px;">
            <img src="../../../static/image/二维码.png" width="18" height="18">
          </el-col>
        </el-row>
      </div>

      <div style="margin-top: 40px;">
        <mt-cell title="雷达加朋友" to="javascript:;" is-link="" style="padding: 8px 0px;">
          <span style="font-size: 12px;">添加身边朋友</span>
          <img slot="icon" src="../../../static/image/雷达.png" style="border-radius: 6px;" width="30"
               height="30">
        </mt-cell>
        <mt-cell title="面对面建群" to="javascript:;" is-link="" style="padding: 8px 0px;">
          <span style="font-size: 12px;">与身边的朋友进入同一个群聊</span>
          <img slot="icon" src="../../../static/image/群聊.png" style="border-radius: 6px;" width="30"
               height="30">
        </mt-cell>
        <mt-cell title="扫一扫" to="javascript:;" is-link="" style="padding: 8px 0px;">
          <span style="font-size: 12px;">扫描二维码名片</span>
          <img slot="icon" src="../../../static/image/扫一扫.png" style="border-radius: 6px;" width="30"
               height="30">
        </mt-cell>
        <mt-cell title="手机联系人" to="javascript:;" is-link="" style="padding: 8px 0px;">
          <span style="font-size: 12px;">添加或邀请通讯录中的朋友</span>
          <img slot="icon" src="../../../static/image/新的朋友.png" style="border-radius: 6px;" width="30"
               height="30">
        </mt-cell>
        <mt-cell title="公众号" to="javascript:;" is-link="" style="padding: 8px 0px;">
          <span style="font-size: 12px;">获取更多咨询和服务</span>
          <img slot="icon" src="../../../static/image/公众号图标.png" style="border-radius: 6px;" width="30"
               height="30">
        </mt-cell>
        <mt-cell title="企业微信联系人" to="javascript:;" is-link="" style="padding: 8px 0px;">
          <span style="font-size: 12px;">通过手机号搜索企业微信用户</span>
          <img slot="icon" src="../../../static/image/企业号.png" style="border-radius: 6px;" width="30"
               height="30">
        </mt-cell>
      </div>
    </div>


  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data () {  //组件的数据区
      return {
        addArr: [],
      }
    },
    methods: {  //方法区
      searchOne () { //输入框的焦点方法
        window.location.href = '#/addTwo'
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post('/user/getById/' + sessionStorage.getItem('id')).then(res => {
        this.addArr = res.data
      })
    }
  }
</script>

<style scoped>
  /*div里面的样式*/
  .div {
    position: fixed;
    width: 100%;
    top: 0px;
    background: #ededed;
    height: 100%;
  }

  /*头部的样式*/
  .div >>> .muihead {
    background: #EDEDED;
    height: 58px;
  }

  /*去除头部字的下划线*/
  .div >>> .mint-header-button > a {
    text-decoration: none;
  }

  /*搜索框的样式*/
  .div >>> .el-input__inner {
    border: none;
    border-radius: 0px;
    height: 46px;
    padding-left: 50px;
  }

  /*第一次搜索框里面的图标样式*/
  .div >>> .el-input__icon {
    width: 40px;
    font-size: 20px;
    color: #aaaaaa;
  }
</style>
