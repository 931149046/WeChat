<template>
  <div>
    <mt-header title="" fixed class="op" id="toolbar" style="background:rgba(248,248,255,0)">
      <a @click.passive="$router.back()" slot="left" style="color: black;">
        <img src="../../../static/image/左箭头.png" class="img_right" width="22" height="22" alt="">
      </a>
      <router-link to="" slot="right" style="color: black;">
        <img src="../../../static/image/相机.png" class="img_right" width="22" height="20" alt=""
             @touchstart="gotouchstart"
             @touchmove="gotouchmove" @touchend="gotouchend">
      </router-link>
    </mt-header>

    <div v-for="ba in BackImg.length ===0 ? defaultArr : BackImg">
      <img :src="ba.bbackimg" height="300" width="100%">  <!--背景图-->
    </div>


    <div style="position: absolute;top: 260px;right: 0;" v-for="us in UserArr2">
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <div class="grid-content">
            <span style="float: left;margin-right: 6px;margin-top: 8px;color: #ffffff;">{{us.uName}}</span>
            <img :src="us.uImage" width="60" height="60"
                 style="border-radius: 6px;margin-right: 12px;">   <!--当前用户头像-->
          </div>
        </el-col>
      </el-row>
    </div>

    <ul v-infinite-scroll="loadMore">
      <li v-for="co in contentArr">
        <el-row style="margin-top: 30px;">
          <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
            <div class="grid-content">
              <img :src="co.image" width="60" height="60"
                   style="border-radius: 6px;margin-top: 6px;margin-left: 14px;">
            </div>
          </el-col>
          <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18">
            <div class="grid-content">
              <span
                style="font-size: 20px;font-weight: bold;letter-spacing: 1px;color: #566b96;">{{co.name}}</span><br/>
              <span>{{co.content}}</span><br/>

              <img :src="co.cimage" v-show="co.cimage === null? false:true" width="100" height="100">

              <el-row>
                <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                  <span style="color: #818181;font-size: 12px;">{{co.time}}</span>
                  <span style="margin-left: 10px;color: #818181;font-size: 12px;">{{co.address}}</span>
                </el-col>
                <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                  <img src="../../../static/image/icon两点.png" width="40" height="30" style="float: right;">
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
        <div style="width: 100%;height: 1px;background: #d9d9d9;"></div>
      </li>
    </ul>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import axios from 'axios'

  export default {
    data () {
      return {
        list: [],
        id: 'd72b0006a61a46b3acde4a525e9bf4fb',
        timeOutEvent: 0,
        UserArr2: [],
        contentArr: [],
        defaultArr: [{bbackimg: 'http://172.16.11.230:8088/5a176d45ef774511a132af6bfbe0868e.jpg'}]
      }
    },
    mounted () {
      let toolbar = document.getElementById('toolbar') //获取需要进行监听的元素id
      window.addEventListener('scroll', () => {  //这个js带的监听事件
        this.offsetTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop  //监听向下滚动的时候获取的像素
        if (this.offsetTop >= 240) {  //像素大于等于240的时候就改变颜色
          toolbar.style.background = '#ededed'
        } else {
          toolbar.setAttribute('style', 'background:rgba(248,248,255,0.0)')
        }
      }, true)

      axios.post(`/user/getById/` + sessionStorage.getItem(`id`)).then(res => { //查询当前用户的头像
        this.UserArr2 = res.data
      })

      axios.post(`/circle/getByUid/` + sessionStorage.getItem(`id`)).then(res => {
        this.contentArr = res.data
      })

      this.$store.dispatch('CircleGetByIdImg', this.$route.params.uid)

    },
    methods: {
      //其实点击和长安之所以会分开执行是因为定时器的原因,定时一秒为长按,但是还没到1秒就离开了屏幕就执行离开屏幕时间,然后马上清除定时器不让它定时执行了
      gotouchstart () { //手指触摸屏幕时触发事件
        this.timeOutEvent = setTimeout(() => { //长按1秒,触摸1秒
          this.timeOutEvent = 0 //执行这个定时器的时候,定时器时间改为0次数
          console.log('长按了')
          window.location.href = `#/publish`  //长按后跳转
        }, 1000)

      },
      gotouchend () { //手指离开屏幕时触发,相当于是点击
        clearTimeout(this.timeOutEvent) //点击的时候移除定时器赋值的0个数,改为自己当前定时器执行到的个数
        if (this.timeOutEvent !== 0) { //移除了定时器后自然0个数也没有了,所以会进来点击
          console.log('点击了')
        }
      },
      gotouchmove () { //手指在屏幕上移动时触发
        clearTimeout(this.timeOutEvent) //移动的时候清除定时器个数
        this.timeOutEvent = 0 //然后赋值为0秒
      },

      loadMore () {
        setTimeout(() => {
          this.list.push({
              title: '哈哈', centent: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容' +
              '内容内容内容内容内容内容内容内容内容内容内容内容内容内容',
              time: '1小时前'
            },
            {
              title: '哈哈', centent: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容' +
                '内容内容内容内容内容内容内容内容内容内容内容内容内容内容',
              time: '1小时前'
            })
        }, 500)
      }
    },
    computed: {
      ...mapState(['CircleArr']),
      ...mapState(['BackImg']),
    },
    components: {},
    /*watch: {
      contentArr: function (val, olaval) {
        for (let aa of val) {
          if (aa.cimage === null) {
            this.isshow = false
          } else {

          }
        }
        //deep:true
      }
    }*/
  }
</script>

<style scoped>
  .mint-header {
    background-color: white;
    height: 58px;
  }

  .cssw {
    display: none;
  }

  .csss {
    display: block;
  }
</style>
