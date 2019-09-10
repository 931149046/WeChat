<template>
  <div id="div1">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">新的朋友</span>
      </router-link>
    </mt-header>
    <div style="width: 100%;margin-top: 48px;">
      <span style="font-size: 12px;color: #888888;margin-left: 20px;">已发送添加好友的请求</span>
    </div>
    <div style="margin-top: 2px;">
      <el-row style="width: 100%; height: 40px;display: flex;align-items: center;background: white;margin-top: 1px;"
              v-for="(as,index) in AddStateArr" :key="index">
        <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="display: flex;justify-content: center;">
          <img :src="as.image" width="30" height="30">
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="margin-left:10px;">
              <span style="text-align: center;font-size: 14px;">{{as.name}}</span><br/>
              <el-row>
                <el-col>
                  <span style="text-align: center;font-size: 12px;color: #888888;">{{as.value}}</span><br/>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-col>
        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="display: flex;justify-content: center;">
          <span style="font-size: 12px;">{{as.state}}</span>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data () {  //组件的数据区
      return {
        AddStateArr: [],
      }
    },
    methods: {  //方法区

    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post(`/addfriend/findByFId/` + sessionStorage.getItem(`id`)).then(res => {
        this.AddStateArr = res.data
      })
    }
  }
</script>

<style scoped>
  #div1 {
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
