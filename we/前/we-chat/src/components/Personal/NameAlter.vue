<template>
  <div id="nameAlert">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">更改名字</span>
      </router-link>
      <router-link to="" slot="right">
        <el-button type="success" size="small" @click.passive="update()">保存
        </el-button>
      </router-link>
    </mt-header>

    <div style="margin-top: 48px;">
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <el-input placeholder="自定义微信号" v-model="name"></el-input>
          <el-divider></el-divider>
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
        name: ''
      }
    },
    methods: {  //方法区
      update () {
        console.log(`点击了保存`)
        axios.post(`/deta/updateName/` + this.name + `/` + sessionStorage.getItem(`id`)).then(res => {
          if (res.status === 200) {
            window.location.href = `#/personal`
            console.log(`更新成功`)
          }
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post(`/deta/findByTId/` + sessionStorage.getItem(`id`)).then(res => {
        this.name = res.data.dRemark
      })
    }
  }
</script>

<style scoped>
  #nameAlert {
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

  #nameAlert >>> .el-input__inner { /*昵称的输入框*/
    background: #ededed;
    border: none;
  }

  #nameAlert >>> .el-divider--horizontal {
    margin: 0px;
    background-color: #07c160;
  }
</style>
