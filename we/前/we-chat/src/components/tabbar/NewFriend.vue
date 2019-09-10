<template>
  <div class="nfid">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="18" height="18" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -21px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">新的朋友</span>
      </router-link>
    </mt-header>

    <div class="ma">
      <el-row style="width: 100%; height: 40px;display: flex;align-items: center;background: white;margin-top: 1px;"
              v-for="(fri,index) in newFriendArr" :key="index">
        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="display: flex;justify-content: center;">
          <img :src="fri.fuserImage" width="30" height="30">
        </el-col>
        <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="margin-left:10px;">
              <span style="text-align: center;font-size: 14px;">{{fri.fName}}</span><br/>
              <el-row>
                <el-col>
                  <span style="text-align: center;font-size: 12px;color: #888888;">{{fri.content}}</span><br/>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-col>
        <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="display: flex;justify-content: center;">
          <el-button type="success" round size="small" @click.passive="refuse(fri.jId,fri.fId)" :class="fri.fId">拒绝
          </el-button>
        </el-col>
        <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5" style="display: flex;justify-content: center;">
          <el-button type="success" round size="small" @click.passive="consent(fri.jId,fri.fId)" :class="fri.fId">同意
          </el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import {Toast} from 'mint-ui'

  export default {
    data () {  //组件的数据区
      return {
        newFriendArr: [], //接收等待当前用户同意的内容数组
      }
    },
    methods: {  //方法区
      refuse (jid, fid) { //拒绝
        let className = document.getElementsByClassName(fid)
        console.log(`拒绝`, className)
        axios.post(`/addfriend/updateStateWhereJId/拒绝/` + jid + `/` + fid).then(res => {
          if (res.status === 200) {
            for (let i of className) {
              i.setAttribute('disabled', 'true')
              Toast({
                message: '已拒绝',
              })
            }
          }
        })
      },
      consent (jid, fid) { //同意
        let className = document.getElementsByClassName(fid)
        console.log(`同意`, className)
        axios.post(`/addfriend/updateStateWhereJId/同意/` + jid + `/` + fid).then(res => {
          if (res.status === 200) {
            for (let i of className) {
              i.setAttribute('disabled', 'true')
              Toast({
                message: '已同意',
              })
            }
          }
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      console.log('获取id', this.$route.params.id)
      axios.post(`/addfriend/getjidAndStateAndImage/` + this.$route.params.id).then(res => {
        console.log(`内容:`, res.data)
        this.newFriendArr = res.data
      })
    }
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

  a {
    color: black;
    text-decoration: none;
  }

  .nfid {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ededed;
  }
</style>
