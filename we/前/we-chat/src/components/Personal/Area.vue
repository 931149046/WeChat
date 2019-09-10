<template>
  <div id="area">
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
    <div style="margin-top: 48px;">
      <div>
        <span style="color: #888888;font-size: 12px;display: block;margin-left: 12px;">当前位置</span>
      </div>
      <div @click.passive="updateAddress()">
        <mt-cell :title="address" is-link=""  style="margin-top: 6px;">
          <img src="../../../static/image/位置.png" slot="icon" style="border-radius: 6px;" width="26" height="26">
        </mt-cell>
      </div>


      <div style="margin-top: 20px;">
        <span style="color: #888888;font-size: 12px;display: block;margin-left: 12px;">全部地区</span>
      </div>
      <mt-cell :title="gcity" is-link="" :to="`/areascity/${ids}`" style="margin-top: 6px;">
        <!-- <span>已选地区</span>-->
      </mt-cell>
    </div>
    <div v-show="false">
      <!--地图管理区-->
      <el-amap vid="amap"
               :amapManager="amapManager"
               :events="events"> <!--:plugin="plugin表示使用下面data中的定位插件-->
      </el-amap>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import {AMapManager} from 'vue-amap'

  export default {
    data () {  //组件的数据区
      let self = this //当前data的this
      return {
        gcity: ``,
        ids: ``,
        map: null,
        address: ``,
        amapManager: new AMapManager,
        events: { //地图初始化的对象,和地图显示区绑定在一起的
          init (o) { //然后把地图对象赋值给当前data的map
            self.map = o
          }
        }
      }
    },
    watch: {
      map: function () { //监听data中的map对象
        if (this.map !== null) { //监听不为空就调用下面的定位方法
          this.Geolocation() //调用定位
        }
      }
    },
    methods: {  //方法区
      Geolocation () { //定位
        let vm = this //当前定位的方法的this
        let map = this.amapManager.getMap() //获取全局的amapManager,获取地图对象
        map.plugin('AMap.Geolocation', function () { //定位插件
          let geolocation = new AMap.Geolocation({
            enableHighAccuracy: true, //是否使用高精度定位，默认:true
            timeout: 10000, //超过10秒后停止定位，默认：5s
            buttonPosition: 'RB', //定位按钮的停靠位置
            buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true, //定位成功后是否自动调整地图视野到定位点
          })
          map.addControl(geolocation) //添加定位插件在map中
          geolocation.getCurrentPosition(function (status, result) { //获取定位
            if (status === 'complete') {  //complete代表成功
              vm.onComplete(result) //调用成功,用当前方法的this去调用外部的
            } else { //调用失败
              vm.onError(result)
            }
          })
        })
      },
      //定位成功
      onComplete (data) {
        let Pstr = `` //省份
        let toCity = `` //市区

        for (let i = 0; i < data.addressComponent.province.length; i++) {  //循环省份字符串取出不要省的字符串
          if (data.addressComponent.province.charAt(i) !== `省`) {
            Pstr += data.addressComponent.province.charAt(i)
          }
        }
        for (let j = 0; j < data.addressComponent.city.length; j++) { //循环省份字符串取出不要市的字符串
          if (data.addressComponent.city.charAt(j) !== `市`) {
            toCity += data.addressComponent.city.charAt(j)
          }
        }

        this.address = Pstr + ` ` + toCity
      },

      //定位失败
      onError (data) {
        console.log('定位失败', '失败原因:' + data.message)
      },

      //更新地址
      updateAddress(){
        axios.post(`/deta/updateAddress/` +this.address + `/` + sessionStorage.getItem(`id`)).then(res => {
          if (res.status === 200) {
            window.location.href = `#/personal`
          }
        })
      }
    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      axios.post(`/city/getCity`).then(res => {
        for (let ss of res.data) {
          this.gcity = ss.cityname
          this.ids = ss.id
        }
      })
      if (this.address === ``) {
        this.address = `定位中...`
      }
    }
  }
</script>

<style scoped>
  #area {
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
