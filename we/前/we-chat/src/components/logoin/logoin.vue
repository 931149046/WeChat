<template>
  <div id="Lid">
    <div style="height: 92%;">
      <el-row style="margin-top: 20px;">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <div style="margin-left: 20px;">
            <h2 style="color: #353535;">微信号/QQ/邮箱登录</h2>
          </div>
        </el-col>
      </el-row>
      <div id="inputId">
        <mt-field label="账号" v-model="number" style="margin-top: 30px;color: #353535;" type="text"
                  placeholder="请填写微信号/QQ号/邮箱" @input="log"></mt-field>
        <mt-field label="密码" v-model="password" style="color: #353535;" type="password" placeholder="请填写密码"></mt-field>
      </div>
      <hr/>
      <el-row style="margin-top: 22px;">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <span style="color: #586b93;margin-left: 20px;">用手机号登录</span>
          <router-link to="/register" style="margin-left: 12px;text-decoration: none;color: #586b93;">注册</router-link>
        </el-col>
      </el-row>
      <div style="padding: 0px 10px;">
        <el-row style="margin-top: 40px;">
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <mt-button type="primary" @click.passive="logoin()"
                       style="background-color: #07c160;width: 100%;height: 40px;opacity: 0.3" id="button"
                       @keyup.enter="logoin()">
              登录
            </mt-button>
          </el-col>
        </el-row>
      </div>
    </div>
    <div style="height: 8%;">
      <el-row>
        <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
          <div>
            <span style="color: #586b93;display: block;text-align: right;margin-right: 5px;">找回密码</span>
          </div>
        </el-col>
        <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
          <div style="border-left: 1px solid #bebebe; border-right: 1px solid #bebebe;">
            <span style="color: #586b93;display: block;text-align: center;">紧急冻结</span></div>
        </el-col>
        <el-col :xs="9" :sm="9" :md="9" :lg="9" :xl="9">
          <div>
            <span style="color: #586b93;display: block;text-align: left;margin-left: 5px;">微信安全中心</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import axios from 'axios' //引入axios
  import {MessageBox} from 'mint-ui'

  export default {
    data () {  //组件的数据区
      return {
        number: '',
        password: ''
      }
    },
    methods: {  //方法区
      log () {
        let bt = document.getElementById('button')
        if (this.number !== null && this.number !== '') {  //输入框不等于空,有数据就显示
          bt.style.opacity = '1'
        } else {  //否则没有数据的话就显示要变透明
          bt.style.opacity = '0.3'
        }
      },
      logoin () {
        axios.post('/user/getNumAndPassword/' + this.number + '/' + this.password).then(res => {
          let uid = res.data.uid
          this.$store.dispatch('svaeId', uid)
          if (res.data.msg === '登录成功') {
            this.setCookie('sessionid', res.data.sessionId) //保存sessionid到cookie中
            this.success()  //调用登录成功弹窗
            setTimeout(() => { //弹窗后进行跳转
              sessionStorage.setItem(`countT`, `1`)
              window.location.href = `#/tabbar/head/${uid}`  //这儿加#号是因为这个是原生跳转
              location.reload()  //跳转后刷新整个页面*!/
            }, 1000)
          } else {
            this.error() //调用错误弹窗
          }
        })
      },

      success () {  //登录成功弹窗提示
        this.$message({
          message: '登录成功',
          type: 'success',
          center: true,
          duration: '0',
        })
      },
      error () { //登录失败弹窗
        this.$message({
          message: '微信号或密码错误',
          type: 'error',
          center: true,
          duration: '2000',
        })
      },

      /**************************cookie操作方法*****************************/
      //设置cookie
      setCookie (name, value) {
        let Days = 30   //此cookie将被保存30天
        let exp = new Date()
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000)
        document.cookie = name + '=' + escape(value);
        +';expires=' + exp.toGMTString()
      },

      //获取cookie
      getCookie (name) {
        let arr, reg = new RegExp('(^|)' + name + '=([^;]*)(;|$)')
        if (arr = document.cookie.match(reg)) {
          return unescape(arr[2])
        } else {
          return null
        }
      },

      //删除cookie
      delCookie (name) {
        let exp = new Date()
        exp.setTime(exp.getTime() - 1)
        let cval = getCookie(name)
        if (cval != null) {
          document.cookie = name + '=' + cval
            + ';expires=' + exp.toGMTString()
        }
      },
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      //MessageBox('提示', '仅供个人学习使用,不做任何商业用途!!!')
    },
    computed: { //属性计算

    }
  }
</script>
<style>
  .el-message--success {
    background-color: #f0f9eb;
    border-color: #e1f3d8;
    margin-top: -22px;
  }

  .mint-msgbox-confirm {
    color: #07c160;
    width: 50%;
  }

  .mint-msgbox-title {
    color: #ff6666;
  }

  .mint-msgbox-message {
    color: black;
  }

  .el-message--error {
    background-color: #fef0f0;
    border-color: #fde2e2;
    margin-top: -22px;
  }


</style>
<style scoped>
  #Lid {
    position: fixed;
    width: 100%;
    top: 0px;
    background: #ededed;
    height: 100%;
  }

  #inputId >>> .mint-field-core {
    background-color: #ededed;
    color: #07c160;
  }

  #inputId >>> .mint-cell-wrapper {
    background-color: #ededed;
  }

  #inputId >>> .mint-cell-text {
    margin-left: 18px;
    font-size: 18px;
  }


  /*.mint-msgbox /deep/ .mint-msgbox-confirm {
    color: #07c160;
    width: 50%;
  }

  .mint-msgbox >>> .mint-msgbox-title {
    color: #ff6666;
  }

  .mint-msgbox >>> .mint-msgbox-message {
    color: black;
  }*/
</style>
