<template>
  <div class="div">
    <mt-header class="muihead" title="" fixed style="height: 6%;">
      <router-link to="" slot="left">
        <img src="../../../static/image/叉叉.png" width="18" height="18" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -21px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;"></span>
      </router-link>
    </mt-header>
    <div style="margin-top: 50px;">
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <h3 style="display: block;margin-left: 20px;">微信注册</h3>
        </el-col>
      </el-row>
    </div>
    <div class="fileUpdate" style="margin-top: 30px; ">
      <el-row>
        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" :class="nickname_bor"
                style="display: flex;justify-content: center;">
          <span style="line-height: 40px;">昵称</span>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16" :class="nickname_bor">
          <el-input placeholder="例如:这是昵称昵称" style="color: #dddddd;" @input="backName()" v-model="nameValue"></el-input>
        </el-col>

        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="display: flex;justify-content: center">
          <div style="width: 42px;height: 96px;">
            <!--action="http://172.16.11.230:7000/user/FileImage"图片上传后台的访问地址
            这里面有个bug就是如果你注册两次的话,第一次上传了头像,第二次不上传头像后台把第一次头像的地址保存了的,就会把第一次的头像地址保存为当前用户的。
            所以现在网站都是先让你注册用户后让你完善资料(特别是上传文件这部分)-->
            <el-upload name="file" action="http://172.16.11.230:7000/user/FileImage" list-type="picture-card">
              <i class="el-icon-plus"></i>
            </el-upload>
          </div>
        </el-col>
      </el-row>
      <div>
        <el-row>
          <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" :class="nicknumber_bor"
                  style="display: flex;justify-content: center;">
            <span style="line-height: 40px;">微信号</span>
          </el-col>
          <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20" :class="nicknumber_bor">
            <el-input placeholder="自定义微信号" v-model="diyNumber" @input="backNumber()"></el-input>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top: 50px;">
        <el-row>
          <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" :class="nickname_borGrey"
                  style="display: flex;justify-content: center;">
            <span style="line-height: 40px;">密码</span>
          </el-col>
          <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20" :class="nickname_borGrey">
            <el-input placeholder="填写密码" v-model="passwordValue" @input="backPassword()" type="password"></el-input>
          </el-col>
        </el-row>
      </div>
    </div>
    <div style="padding: 0px 10px;margin-top: 50px;">
      <mt-button type="primary" disabled="disabled"
                 style="background-color: #07c160;width: 100%;height: 40px;opacity:0.3;" id="button"
                 @click.passive="register()">
        注册
      </mt-button>
    </div>
    <div style="font-size: 12px;margin-top: 20px;padding: 0 10px;">
      <span style="color: #999999;">点击上名的"注册"按钮,即表示你同意
        <router-link to="" style="color: #586b95;">《微信软件许可及服务协议》</router-link>
        和<router-link to="" style="color: #586b95;">《微信隐私保护指引》</router-link>
      </span>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data () {  //组件的数据区
      return {
        nameValue: '', //昵称输入框的
        diyNumber: '', //微信号输入框
        passwordValue: '', //密码输入框的
        nickname_bor: '', //昵称下方的线条样式绑定
        nicknumber_bor: '', //微信号下方的线条样式绑定
        nickname_borGrey: '', //密码下方的线条样式绑定
      }
    },
    methods: {  //方法区
      backName () { //监听昵称输入框的方法,改变输入框下线条的颜色
        if (this.nameValue !== null && this.nameValue !== '') {
          this.nickname_bor = 'nickname_bor'
        } else {
          this.nickname_bor = 'nickname_borGrey'
        }
      },
      backNumber () { //监听微信号输入框的方法,改变输入框下线条的颜色
        if (this.diyNumber !== null && this.diyNumber !== '') {
          this.nicknumber_bor = 'nickname_bor'
        } else {
          this.nicknumber_bor = 'nickname_borGrey'
        }
      },
      backPassword () { //监听昵称输入框的方法,改变输入框下线条的颜色
        if (this.passwordValue !== null && this.passwordValue !== '') {
          this.nickname_borGrey = 'nickname_bor'
        } else {
          this.nickname_borGrey = 'nickname_borGrey'
        }
      },
      register () { //注册的方法
        console.log('点击了注册')
        axios.post('/user/insert/' + this.nameValue + '/' + this.diyNumber + '/' + this.passwordValue).then((res) => {
          if (res.status === 200) {
            this.$message({
              message: '注册成功',
              type: 'success',
              center: true,
              duration: '1000',
            })
            setTimeout(() => {
              window.location.href = `#/logoin`
            }, 1500)
          }
        })
      }
    },
    mounted () {
      this.backName() //页面加载完成调用一次初始化判断输入框中是否有值,进行的改变样式
      this.backPassword() //页面加载完成调用一次初始化判断输入框中是否有值,进行的改变样式

    },
    watch: { //监听属性
      monitorInpt: (val) => { //涉及到了同时监听两个data所以在computed中把两个data进行封装成了对象,直接监听计算属性中的monitorInpt方法
        let bt = document.getElementById('button') //获取注册按钮dom
        if (val.nameValue !== null && val.nameValue !== '') { //判断昵称输入框有没有值,有值就进来
          if (val.diyNumber !== null && val.diyNumber !== '') { //判断密码输入框有没有值,有值就进来
            if (val.passwordValue !== null && val.passwordValue !== '') {
              bt.removeAttribute('disabled')
              bt.style.opacity = '1' //把按钮改成不透明
            } else { //两个中有一个没有值得话就透明
              bt.style.opacity = '0.3'
              bt.setAttribute('disabled', 'disabled')
            }
          } else { //两个中有一个没有值得话就透明
            bt.style.opacity = '0.3'
            bt.setAttribute('disabled', 'disabled')
          }
        } else { //两个中有一个没有值得话就透明
          bt.style.opacity = '0.3'
          bt.setAttribute('disabled', 'disabled')
        }
      }
    },
    computed: { //计算属性,里面可以写一些方法和其他的表达式
      monitorInpt () { //引入进来的data区的需要同时监听的两个变量
        const {nameValue, passwordValue, diyNumber} = this  //引入需要监听的data数据区的变量
        return { //把这两个变量当做对象返回,调用这个方法的时候就返回
          nameValue,
          passwordValue,
          diyNumber
        }
      }
    },
    components: {  //子组件进行标签映射

    }
  }
</script>

<style scoped>
  .div >>> .muihead {
    background: #EDEDED;
    height: 58px;
  }

  .div {
    position: fixed;
    width: 100%;
    top: 0px;
    background: #ededed;
    height: 100%;
  }

  /**************************elemetn图片上传样式************************************/
  .fileUpdate >>> .el-upload--picture-card { /*上传的样式*/
    width: 42px;
    height: 42px;
    line-height: 42px;
  }

  .fileUpdate >>> .el-upload-list--picture-card .el-upload-list__item { /*上传界面的样式*/
    width: 42px;
    height: 42px;
  }

  .fileUpdate >>> .el-input__inner { /*昵称的输入框*/
    background: #ededed;
    border: none;
  }

  /*********************************输入框下面的线***********************************************/
  .nickname_bor {
    border-bottom: 1px solid #07c160;
  }

  .nickname_borGrey {
    border-bottom: 1px solid #d8d8d8;
  }

</style>
