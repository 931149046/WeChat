<template>
  <div id="sid">
    <div style="position: fixed;top: 0px;width: 100%;background: rgba(237, 237, 237,1)">
      <el-row>
        <div style="margin-top: 10px;">
          <el-col :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
            <div>
              <router-link to="">
                <img src="../../../static/image/左箭头.png" onclick="window.history.go(-1)"
                     style="width: 22px;height: 22px;margin-left: 10px;margin-top: 12px;">
              </router-link>
            </div>
          </el-col>
          <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
            <div class="inputClass">
              <mt-field type="textarea" label="" placeholder="搜索" v-model="value" @input="monitor"
                        style="background: #EDEDED;width: 100%;"></mt-field>
            </div>
          </el-col>
        </div>

      </el-row>
    </div>
    <hr/>

    <div id="toolbar2">
      <el-row style="margin-top: 80px;">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <span style="display: block;text-align: center;margin-top: 6px;color: #B3B3B3;">搜索指定内容</span>
        </el-col>
      </el-row>

      <el-row style="margin-top: 22px;">
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <div style="border-right: 1px solid #DADADA;">
            <span style="display: block;text-align: center;margin-top: 6px;color: #586b95;">朋友圈</span>
          </div>
        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <div style="border-right: 1px solid #DADADA;">
            <span style="display: block;text-align: center;margin-top: 6px;color: #586b95;">文章</span>
          </div>
        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <span style="display: block;text-align: center;margin-top: 6px;color: #586b95;">公众号</span>
        </el-col>
      </el-row>

      <el-row style="margin-top: 50px;">
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <div style="border-right: 1px solid #DADADA;">
            <span style="display: block;text-align: center;margin-top: 6px;color: #586b95;">小程序</span>
          </div>
        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <div style="border-right: 1px solid #DADADA;">
            <span style="display: block;text-align: center;margin-top: 6px;color: #586b95;">音乐</span>
          </div>
        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <span style="display: block;text-align: center;margin-top: 6px;color: #586b95;">表情</span>
        </el-col>
      </el-row>
    </div>
    <div id="div2" style="margin-top: 50px;">
      <mt-cell :title="chat.fname" :label="chat.content" to="/" :key="index" v-for="(chat,index) in ChattingArr">
        <img slot="icon" :src="chat.fimage" width="30" height="30" style="float: left;margin-top: 3px; padding-right: 8px;">
      </mt-cell>
    </div>

  </div>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    data () {  //组件的数据区
      return {
        value: '',
        id: ''
      }
    },
    methods: {  //方法区
      monitor () {  //监听input输入框变化的方法,通过@input进行触发这个方法的
        console.log("vue的this",this)
        let tool = document.getElementById('toolbar2') //获取需要进行监听的元素id
        let tool2 = document.getElementById('div2')
        if (this.value !== null && this.value !== '') {  //输入框不等于空,有数据就影藏
          let map = new Map()
          map.set('id', this.id)
          map.set('value', this.value)
          this.$store.dispatch('getChattingAllById', map)
          tool.style.display = 'none' //隐藏搜索前的元素
          tool2.style.display = 'block' //显示搜索后的元素
        } else {  //否则没有数据的话就显示要显示的元素
          tool.style.display = 'block'  //显示需要显示的元素
          tool2.style.display = 'none'
        }
      }
    },
    mounted () {  //页面初始化执行
      let tool = document.getElementById('toolbar2') //获取需要进行操作的元素id
      let tool2 = document.getElementById('div2')
      tool.style.display = 'block' //显示首次进来的元素
      tool2.style.display = 'none'
      this.id = this.$route.params.u_id
    },
    computed: {  //子组件进行标签映射
      ...mapState(['ChattingArr'])
    }
  }
</script>

<style scoped>
  .inputClass >>> textarea{
    background-color: #ededed !important;
    margin-top: 6px;
    color: #07c160;
  }

  .mint-cell-text {
    margin-left: 12px;
    font-size: 14px;
  }

  .mint-cell-label {
    margin-top: 4px;
    margin-left: 43px;
  }

  #sid{
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ededed;
  }
</style>
