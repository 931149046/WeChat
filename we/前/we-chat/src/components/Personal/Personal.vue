<template>
  <div id="per">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">个人信息</span>
      </router-link>
    </mt-header>

    <div style="margin-top: 48px;" id="up">
      <ul>
        <li v-for="dee in detaileArr">
          <mt-cell title="头像" is-link="" to="/upload">
            <img :src="image" style="border-radius: 6px;" width="30" height="30">
          </mt-cell>
          <mt-cell title="昵称" is-link="" to="/namealter">
            <span>{{dee.dRemark}}</span>
          </mt-cell>
          <mt-cell title="微信号">
            <span>{{dee.dNumber}}</span>
          </mt-cell>
          <mt-cell title="二维码名片" is-link="" to="/twocode">
            <img src="../../../static/image/二维码.png" style="border-radius: 6px;" width="20" height="20">
          </mt-cell>
          <mt-cell title="性别" to="/selectsex" is-link="">
            <span>{{dee.dSex}}</span>
          </mt-cell>
          <mt-cell title="地区" is-link="" to="/area">
            <span>{{dee.dArea}}</span>
          </mt-cell>
          <mt-cell title="更多" is-link="">
          </mt-cell>
          <div style="margin-top: 6px;">
            <mt-cell title="收货地址" is-link="">
            </mt-cell>
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
        detaileArr: [],
        image: '',
      }
    },
    methods: {  //方法区

    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post(`/deta/findByTId/` + sessionStorage.getItem(`id`)).then(res => {
        this.detaileArr.push(res.data)
      })
      axios.post(`/user/getById/` + sessionStorage.getItem(`id`)).then(res => {
        console.log(`头像`,res.data)
        for (let r of res.data) {
          this.image = r.uImage
          console.log(`头像`,r.uImage)
        }
      })
    }
  }
</script>

<style scoped>
  #per {
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

  #per >>> .el-textarea__inner {
    border: none;
    resize: none;
    font-size: 12px;
    padding: 5px 28px;
  }


  #per >>> .el-upload--picture-card { /*上传界面的样式*/
    width: 50px;
    height: 50px;
    line-height: 50px;
  }
</style>
