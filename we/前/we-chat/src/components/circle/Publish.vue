<template>
  <div id="div1">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">发表文字</span>
      </router-link>
      <router-link to="" slot="right">
        <el-button type="success" size="small" style="color: #cbf2dd;background-color: #b4edcd;border: #b4edcd;"
                   @click.passive="send()"
                   id="butt">发表
        </el-button>
      </router-link>
    </mt-header>
    <div style="margin-top: 48px;">
      <el-input type="textarea" placeholder="这一刻的想法" rows="4" v-model="valuetextarea" @input="textArea()"></el-input>
    </div>
    <div id="upload" style="padding: 10px 0px 10px 18px; width: 100%;height: 50px;">
      <el-upload name="file" action="http://172.16.11.230:7000/circle/FileImage"
                 list-type="picture-card">
        <i class="el-icon-plus"></i>
      </el-upload>
    </div>
    <div>
      <mt-cell title="所在位置" to="/amap" is-link="">
        <span style="font-size: 12px;">{{address}}</span>
        <img slot="icon" src="../../../static/image/定位.png" style="border-radius: 6px;" width="28" height="28">
      </mt-cell>
      <mt-cell title="谁可以看" is-link="">
        <span>公开</span>
        <img slot="icon" src="../../../static/image/头像.png" style="border-radius: 6px;" width="28" height="28">
      </mt-cell>
      <mt-cell title="提醒谁" is-link="">
        <img slot="icon" src="../../../static/image/艾特.png" style="border-radius: 6px;" width="20" height="20">
      </mt-cell>
    </div>
    <div style="margin-top: 10px;">
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="42">
          <img src="../../../static/image/空间.png" width="24" height="24" style="margin-left: 10px;">
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
        valuetextarea: '',
        address: sessionStorage.getItem(`address`),
      }
    },
    methods: {  //方法区
      textArea () {
        let bouttn = document.getElementById(`butt`)
        if (this.valuetextarea !== null && this.valuetextarea !== ``) {
          bouttn.style = `background-color:#07c160;color:#ffffff;border: #07c160;`

        } else {
          bouttn.style = 'color: #cbf2dd;background-color: #b4edcd;border: #b4edcd;'
          bouttn.setAttribute('dis')
        }
      },
      send () {
        axios.post(`/circle/insert/` + sessionStorage.getItem(`id`) + `/` + this.valuetextarea + `/` + this.address).then(res => {
          if (res.status === 200) {
            window.location.href = `#/circle/` + sessionStorage.getItem(`id`)
          }
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      console.log(`获取的当前用户id`,sessionStorage.getItem(`id`))
    }
  }
</script>

<style scoped>
  #div1 {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ffffff;
  }

  .mint-header {
    background-color: #ffffff;
    height: 48px;
  }

  a {
    color: black;
    text-decoration: none;
  }

  #div1 >>> .el-textarea__inner {
    border: none;
    resize: none;
    font-size: 12px;
    padding: 5px 28px;
  }


  /**************************elemetn图片上传样式************************************/
  #upload >>> .el-upload--picture-card { /*上传的样式*/
    width: 50px;
    height: 50px;
    line-height: 50px;
  }

  #upload >>> .el-upload-list--picture-card .el-upload-list__item { /*上传界面的样式*/
    width: 50px;
    height: 50px;
  }

  #upload >>> .el-upload--picture-card i {
    line-height: 50px;
  }

  #upload >>> .el-upload--picture-card {
    border: 1px solid #c0ccda;
  }
</style>
