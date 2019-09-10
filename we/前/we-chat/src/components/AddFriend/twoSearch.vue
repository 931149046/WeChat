<template>
  <div class="div">
    <el-row style="border-bottom: 1px solid #d9d9d9;height: 58px;display: flex;align-items: center;">
      <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
        <img src="../../../static/image/左箭头.png" style="margin-top:4px;margin-left: 14px;" width="18" height="18"
             onclick="window.history.go(-1)">
      </el-col>
      <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
        <el-input placeholder="请输入内容" class="noneInput" v-model="twoInputValue" @input="twoMonitor()"></el-input>
      </el-col>
    </el-row>

    <div id="none">
      <mt-cell :title="content" to="javascript:;" style="padding: 6px 0px;color: #07c160;" @click.native.passive="findUser()">
        <img slot="icon" src="../../../static/image/添加用户.png" style="border-radius: 6px;" width="26"
             height="26">
      </mt-cell>
    </div>

    <div style="height: 100%;width: 100%;background-color: white;" id="nonentity">
      <div
        style="height: 10%;background-color: white;display: flex;justify-content: center;color: #888888;font-size: 12px;">
        <span style="margin-top: 50px;">该用户不存在</span>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data () {  //组件的数据区
      return {
        twoInputValue: '',
        content: '',
      }
    },
    methods: {  //方法区
      twoMonitor () { //监听输入框的内容
        let none = document.getElementById('none')
        let nonentity = document.getElementById('nonentity')
        if (this.twoInputValue !== null && this.twoInputValue !== '') {
          this.content = '搜索:' + this.twoInputValue
          none.style.display = 'block'
          nonentity.style.display = 'none'
        } else {
          none.style.display = 'none'
          nonentity.style.display = 'none'
          this.content = ''
        }
      },
      findUser () {
        axios.post('/user/getNumber/' + this.twoInputValue).then(res => {
          if (res.data !== null && res.data !== '') {
            window.location.href = '#/addInfo/' + res.data.uId
          } else {
            let nonentity = document.getElementById('nonentity')
            let none = document.getElementById('none')
            nonentity.style.display = 'block'
            none.style.display = 'none'
          }
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      let none = document.getElementById('none')
      let nonentity = document.getElementById('nonentity')
      nonentity.style.display = 'none'
      none.style.display = 'none'
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

  /*第二次搜索框的样式*/
  .div >>> .noneInput .el-input__inner {
    border: none;
    background-color: #ededed;
    padding: 0px;
  }
</style>
