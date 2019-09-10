<template>
  <div id="upload" style="display: flex;justify-content: center;align-items: center;">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">上传头像</span>
      </router-link>
      <router-link to="/personal" slot="right">
        <el-button type="success" size="small">确定
        </el-button>
      </router-link>
    </mt-header>

    <div
      style="width: 300px;height: 300px;background-color: white;display: flex;justify-content: center;align-items: center;border-radius: 8px;">
      <div>
        <el-upload action="http://172.16.11.230:7000/user/updateImage" :data="obj" list-type="picture-card"
                   :on-success="sec">
          <i class="el-icon-plus"></i>
        </el-upload>
      </div>
    </div>
  </div>
</template>

<script>
  import { MessageBox } from 'mint-ui';
  export default {
    data () {  //组件的数据区
      return {
        obj: {
          id: sessionStorage.getItem(`id`)
        },
        val: [],
      }
    },
    methods: {  //方法区
      sec: function (response, file, fileList) {
        if (file.status === `success`) {
          MessageBox('提示', '上传成功');
          /*setTimeout(() => {
            window.location.href = `#/personal`
          }, 5000)*/

        }
      }
    },
    components: {  //子组件进行标签映射
    }
  }
</script>

<style scoped>
  #upload {
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

  #upload >>> .el-upload--picture-card {
    width: 100px;
    height: 100px;
    line-height: 100px;
  }

  #upload >>> .el-upload-list--picture-card .el-upload-list__item {
    width: 100px;
    height: 100px;
  }

  body >>> .mint-msgbox-btn mint-msgbox-confirm {
    color: red;
  }
</style>
