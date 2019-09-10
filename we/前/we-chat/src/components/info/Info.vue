<template>
  <div id="Iid">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="22" height="22" onclick="window.history.go(-1)">
      </router-link>

      <router-link to="" slot="right">
        <div @click.passive="del()">
          <img src="../../../static/image/三点.png" width="28" height="28">
        </div>
      </router-link>

    </mt-header>

    <el-row style="margin-top: 57px;height: 124px;background: white;" v-for="de in DetailArr">
      <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
        <div class="grid-content" style="margin-top: 4px;">
          <img :src="de.dImage" width="70" height="70" style="border-radius: 6px;margin-left: 28px;">
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <span style="font-size: 22px;font-weight: bold;letter-spacing: 1px;">{{de.dRemark}}</span>
            <img :src="de.dSex === `男` ? `../../../static/image/男.png` : `../../../static/image/女.png`" width="14"
                 height="14"> <br/>

            <span style="font-size: 12px;color: #999999;">微信号：{{de.dNumber}}</span><br/>
            <span style="font-size: 12px;color: #999999;">地区：{{de.dArea}}</span><br/>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <mt-cell title="设置备注和标签" is-link="" style="margin-top: 1px;"></mt-cell>

    <mt-cell title="朋友圈" is-link="" style="margin-top: 10px;">  <!--to=""路由跳转朋友圈暂时不进行跳转-->
      <img src="../../../static/image/T.jpg" width="40" height="40">
      <img src="../../../static/image/T.jpg" width="40" height="40" style="margin-left: 6px;">
      <img src="../../../static/image/T.jpg" width="40" height="40" style="margin-left: 6px;">
      <img src="../../../static/image/T.jpg" width="40" height="40" style="margin-left: 6px;margin-right: 8px;">
    </mt-cell>
    <mt-cell title="更多信息" is-link=""></mt-cell>

    <div style="margin-top: 10px;">
      <el-row style="background: white;min-height: 48px;">
        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" v-for="dd in DetailArr">
          <router-link :to="`/listcontent/${dd.dId}`">
            <div style="position: absolute; margin-left: 41%;line-height: 48px;color: #586b95;">
              <img src="../../../static/image/评论.png" width="24" height="24"
                   style="margin-top: 13px;float: left; margin-right: 6px;">
              <span @click.passive="send(dd.dRemark,dd.dImage,dd.dId)">发消息</span>
            </div>
          </router-link>
        </el-col>
      </el-row>
      <el-row style="background: white;min-height: 48px;margin-top: 1px;">
        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
          <router-link to="">
            <div style="position: absolute; margin-left: 39%;line-height: 48px;color: #586b95;">
              <img src="../../../static/image/视频.png" width="26" height="26"
                   style="margin-top: 13px;float: left; margin-right: 6px;">
              <span>音视频通话</span>
            </div>
          </router-link>
        </el-col>
      </el-row>
    </div>
    <mt-popup
      v-model="popupVisible"
      position="bottom">
      <div style="height: 300px;width: 100%;overflow-y: auto;" id="popid" v-for="dd in DetailArr">
        <mt-cell title="设置备注和标签" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/编辑.png" slot="icon" style="border-radius: 6px;" width="26" height="26">
        </mt-cell>
        <mt-cell title="把他(她)推荐给朋友" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/分享.png" slot="icon" style="border-radius: 6px;" width="22" height="22">
        </mt-cell>
        <mt-cell title="设为星标朋友" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/五角星.png" slot="icon" style="border-radius: 6px;" width="25" height="25">
        </mt-cell>
        <mt-cell title="设置朋友圈和视频动态" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/朋友圈灰色.png" slot="icon" style="border-radius: 6px;" width="25" height="25">
        </mt-cell>
        <mt-cell title="加入黑名单" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/禁止.png" slot="icon" style="border-radius: 6px;" width="20" height="20">
        </mt-cell>
        <mt-cell title="投诉" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/警告.png" slot="icon" style="border-radius: 6px;" width="20" height="20">
        </mt-cell>
        <mt-cell title="添加到桌面" is-link="" style="margin-top: 6px;color: #636363;">
          <img src="../../../static/image/复制.png" slot="icon" style="border-radius: 6px;" width="20" height="20">
        </mt-cell>
        <div @click.passive="deleteI(dd.dRemark,dd.tId)">
          <mt-cell title="删除" is-link="" style="margin-top: 6px;color: #636363;">
            <img src="../../../static/image/删除.png" slot="icon" style="border-radius: 6px;" width="20" height="20">
          </mt-cell>
        </div>
      </div>
    </mt-popup>
  </div>
</template>

<script>
  import {mapState} from 'vuex'   //引入mapState去读取vuex中todos中的数据,一般把获取数据都写在computed(计算属性)计算属性中
  import {MessageBox} from 'mint-ui'
  import axios from 'axios'

  export default {
    data () {
      return {
        ids: '',
        dArr: [],
        sh: false,
        popupVisible: false,
      }
    },
    computed: {
      ...mapState(['DetailArr']), //直接获取要拿的数组名字
    },
    mounted () {
      this.ids = this.$route.params.fid //获取其他组件传过来的参数赋值在本数据区的ids中
      this.$store.dispatch('findAllDetail', this.ids) //分发到vuex的actions.js(用于发异步提交到mutaion.js中更新数据状态)中
      console.log(`数据`, this.DetailArr)
    },
    methods: {
      send (name, image, id) {
        let map = new Map()
        map.set('name', name)
        map.set('porimage', image)
        map.set('id', id)
        map.set('u_id', sessionStorage.getItem('id'))
        this.$store.dispatch('ChatInstall', map)
      },

      del () { //删除好友的弹出页面
        // this.sh = !this.sh
        this.popupVisible = true
      },
      deleteI (name,tid) {
        MessageBox({
          title: '删除联系人',
          message: `确定将联系人"${name}"删除,将同时删除与改联系人的聊天记录?`,
          showCancelButton: true
        }).then(res => {
          axios.post(`/inform/deleteRelationTable/` + sessionStorage.getItem(`id`) + `/`+tid).then(res=>{
            if (res.status === 200){
              MessageBox('提示', '成功删除');
              window.location.href=`#/tabbar/head/`+sessionStorage.getItem(`id`)
            }
          })
        }).catch(err => {
          console.log(`点击了取消`)
        })
      }
    },
    components: {},
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

  #Iid >>> .mint-popup-bottom {
    width: 100%;
    border-top-right-radius: 6px;
    border-top-left-radius: 6px;
  }

  #Iid >>> .mint-cell-text {
    margin-left: 10px;
  }

  #popid >>> .mint-cell-allow-right {
    display: none;
  }
</style>
