<template>
  <div id="Cid">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="22" height="22" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -21px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">聊天信息</span>
      </router-link>
    </mt-header>
    <div style="background: white;height: 100px;" v-for="cc in ChatInfo">
      <el-row class="ma">
        <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
          <div class="grid-content">
            <!-- <img src="../../../static/image/T.jpg" width="50" height="50"
                  style="margin-left: 8px;border-radius: 6px;margin-top: 18px;"> -->
            <img :src="cc.cPorimage" width="50" height="50"
                 style="margin-left: 8px;border-radius: 6px;margin-top: 18px;">
            <el-row>
              <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                <span style="margin-left: 9px;font-size: 14px;color: #999999;">{{cc.dRemark}}</span>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18">
          <div class="grid-content">
            <img src="../../../static/image/上传图片.png" width="50" height="50"
                 style="border-radius: 6px;margin-top: 18px;">
          </div>
        </el-col>
      </el-row>
    </div>
    <mt-cell title="聊天小程序" is-link="" style="margin-top: 12px;font-size: 16px;color: #181818;"></mt-cell>
    <mt-cell title="查找聊天消息" is-link="" :to="`/record/${id}`"
             style="margin-top: 12px;font-size: 16px;color: #181818;"></mt-cell>

    <mt-cell title="置顶聊天" style="margin-top: 12px;font-size: 16px;color: #181818;">
      <mt-switch></mt-switch>
    </mt-cell>
    <mt-cell title="消息免打扰" style="font-size: 16px;color: #181818;">
      <mt-switch></mt-switch>
    </mt-cell>
    <mt-cell title="请提醒" style="font-size: 16px;color: #181818;">
      <mt-switch></mt-switch>
    </mt-cell>
    <mt-cell title="设置当前聊天背景" is-link="" style="margin-top: 12px;font-size: 16px;color: #181818;"></mt-cell>
    <mt-cell title="清空聊天记录" @click.native.passive="empty()" is-link=""
             style="margin-top: 12px;font-size: 16px;color: #181818;"></mt-cell>
    <mt-cell title="投诉" is-link="" style="margin-top: 12px;font-size: 16px;color: #181818;"></mt-cell>
  </div>
</template>

<script>
  import {MessageBox} from 'mint-ui'
  import axios from 'axios'

  export default {
    data () {
      return {
        id: sessionStorage.getItem('id'),
        ChatInfo: [], //保存聊天人信息的数组
      }
    },
    methods: {
      empty () {
        MessageBox.confirm('确定执行此操作?').then(action => {
          axios.post(`/chatt/deleteUid/` + sessionStorage.getItem('id')).then(res => {
            if (res.status === 200) {
              setTimeout(() => {
                MessageBox('提示', '清空成功')
              }, 500)
            }
          })
        })
      }
    },
    mounted () {
      axios.post(`/chat/getById/` + this.$route.params.id).then(res => { //根据chat表中的didpk
        this.ChatInfo = res.data
      })
    },
    components: {}
  }
</script>

<style scoped>
  .mint-header {
    background-color: #ededed;
    height: 58px;
  }

  body {
    background: #ededed;
  }

  .ma {
    margin-top: 58px;
  }

  #Cid >>> .mint-switch-input:checked + .mint-switch-core {
    border-color: #07c160;
    background-color: #07c160;
  }

  #Cid {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ededed;
  }
</style>
