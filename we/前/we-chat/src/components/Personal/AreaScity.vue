<template>
  <div id="ascity">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">选择地区</span>
      </router-link>
      <!--<router-link to="/personal" slot="right">
        <el-button type="success" size="small">确定
        </el-button>
      </router-link>-->
    </mt-header>
    <div style="margin-top: 48px;overflow-y:auto;height: 100%;">
      <mt-cell :title="st.cityname" is-link="" :to="`/areasscity/${st.id}`" style="margin-top: 6px;" v-for="st in ScityArr">
      </mt-cell>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data () {  //组件的数据区
      return {
        ScityArr: [],
      }
    },
    methods: {  //方法区

    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post(`/city/getScity/` + this.$route.params.pid).then(res => {
        this.ScityArr = res.data
      })
    }
  }
</script>

<style scoped>
  #ascity {
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
