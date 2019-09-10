<template>
  <!--头-->
  <mt-header title="" fixed>
    <span style="font-size: 18px;color: #181818;font-weight: bold;letter-spacing: 1px;" slot="left">微信</span>
    <router-link :to="`/search/${ids}`" slot="right">
      <img src="../../../static/image/搜索.png" class="img_right" width="22" height="22" alt="">
    </router-link>
    <router-link to="" slot="right" v-popover:popover2>
      <img src="../../../static/image/添加.png" class="img_right" width="22" height="22" alt="">
      <el-popover
        ref="popover2"
        placement="bottom"
        width="150"
        trigger="click"
        content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
        <mt-cell title="" to="/">
          <span style="font-size: 14px;">发起群聊</span>
          <img slot="icon" src="../../assets/发起群聊.png" width="20" height="20">
        </mt-cell>
        <mt-cell title="" to="/add">
          <span style="font-size: 14px;">添加朋友</span>
          <img slot="icon" src="../../assets/添加朋友.png" width="24" height="24">
        </mt-cell>
        <mt-cell title="" to="/">
          <span style="font-size: 14px;">扫一扫</span>
          <img slot="icon" src="../../assets/扫一扫.png" width="20" height="20">
        </mt-cell>
        <mt-cell title="" to="/">
          <span style="font-size: 14px;">收付款</span>
          <img slot="icon" src="../../assets/收付款.png" width="20" height="20">
        </mt-cell>
        <mt-cell title="" to="/">
          <span style="font-size: 14px;">帮助与反馈</span>
          <img slot="icon" src="../../assets/信封.png" width="20" height="20">
        </mt-cell>
      </el-popover>
    </router-link>
  </mt-header>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    data () {
      return {
        ids: '',
      }
    },
    mounted () {
      this.ids = this.uid
      console.log(this.ids);
    },
    computed: {
     ...mapState(['uid'])
    },
    components: {}
  }
</script>

<style scoped>
  .img_right {
    margin-left: 10px;
  }

  .mint-header {
    background-color: #ededed;
    height: 58px;
  }

  .mint-cell-value {
    color: #040404;
  }

  .el-popover--plain {
    padding: 0px;

  }

  .element.style {

    left: 222px;
  }

  .el-popper[x-placement^=bottom] .popper__arrow::after {
    top: 1px;
    margin-left: -2px;
    border-top-width: 0;
    border-bottom-color: #fff;
  }
</style>
