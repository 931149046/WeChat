<template>
  <div id="upload">
    <mt-header title="" fixed>
      <router-link to="" slot="left">
        <img src="../../../static/image/左箭头.png" width="16" height="16" onclick="window.history.go(-1)">
        <span style="color: #282828; display: block;margin-top: -19px;
          margin-left: 32px;font-size: 17px;font-weight: 500;letter-spacing: 1px;">性别选择</span>
      </router-link>
    </mt-header>
    <div style="margin-top: 48px;">
      <mt-radio
        v-model="value"
        :options="['男', '女']">
      </mt-radio>
    </div>

    <div style="margin-top: 100px;padding: 0px 10px 0px 10px;">
      <mt-button type="primary" @click.passive="confirm()"
                 style="background-color: #07c160;width: 100%;height: 40px;" id="button">
        保存
      </mt-button>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data () {  //组件的数据区
      return {
        value: '',
      }
    },
    methods: {  //方法区
      confirm () {
        axios.post(`/deta/updateSex/` + this.value + `/` + sessionStorage.getItem(`id`)).then(res => {
          if (res.status === 200) {
            window.location.href = `#/personal`
            console.log(`更新成功`)
          }
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post(`/deta/findByTId/` + sessionStorage.getItem(`id`)).then(res => {
        this.value = res.data.dSex
      })
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

  #upload >>> .mint-radio-input:checked + .mint-radio-core {
    background-color: #07c160;
    border-color: #07c160;
  }
</style>
