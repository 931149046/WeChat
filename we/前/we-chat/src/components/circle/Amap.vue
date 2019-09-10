<template>
  <div id="map">
    <div style="">
      <el-row style="display: flex;align-items: center;height: 5%;width: 100%;">
        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4"
                style="display: flex;justify-content: center;align-items: center;">
          <a @click.passive="$router.back()">
            <img src="../../../static/image/左箭头.png" width="20" height="20">
          </a>
        </el-col>

        <el-col :xs="15" :sm="15" :md="15" :lg="15" :xl="15"
                style="display: flex;justify-content: center;align-items: center;">
          <el-input placeholder="输入地名..." v-model="address" id="buttn"></el-input>
        </el-col>

        <!--<el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5"
                style="display: flex;justify-content: center;align-items: center;">
          <el-button type="primary" size="small " @click.passive="search">搜索</el-button>
        </el-col>-->
        <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5"
                style="display: flex;justify-content: center;align-items: center;">
          <el-button type="success" size="small" @click.passive="addAddress()">确定</el-button>
        </el-col>
      </el-row>
    </div>
    <div id="amap" style="height: 95%;width: 100%;">
      <!--地图管理区-->
      <el-amap vid="amap"
               :amapManager="amapManager"
               :events="events"> <!--:plugin="plugin表示使用下面data中的定位插件-->
      </el-amap>
    </div>
  </div>
</template>

<script>
  import {AMapManager} from 'vue-amap'
  export default {
    data () {
      let self = this //当前data的this
      return {
        map: null,
        address: '',
        amapManager:new AMapManager,
        events: { //地图初始化的对象,和地图显示区绑定在一起的
          init (o) { //然后把地图对象赋值给当前data的map
            self.map = o
          }
        }
      }
    },
    watch: {
      map: function () { //监听data对象
        if (this.map !== null) { //监听不为空就调用下面的定位方法
          this.Geolocation() //调用定位
          this.Poi() //调用poi
        }
      }
    },
    mounted () {

    },
    methods: {
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
        console.log(`定位成功`, data.formattedAddress)
        this.address = data.formattedAddress
      },

      //定位失败
      onError (data) {
        //console.log('定位失败', '失败原因:' + data.message)
        console.log('定位失败', '失败原因:' + data)
      },

      Poi () {
        let vm = this //当前定位的方法的this,this默认的是执行vue对象的
        let map = this.amapManager.getMap() //获取全局的amapManager,获取地图对象
        AMapUI.loadUI(['misc/PoiPicker'], function (PoiPicker) {

          let poiPicker = new PoiPicker({
            //city:'北京',
            input: 'buttn'
          })

          //初始化poiPicker
          poiPickerReady(poiPicker)
        })

        function poiPickerReady (poiPicker) {

          window.poiPicker = poiPicker

          let marker = new AMap.Marker()

          let infoWindow = new AMap.InfoWindow({
            offset: new AMap.Pixel(0, -20)
          })

          //选取了某个POI
          poiPicker.on('poiPicked', function (poiResult) {

            let source = poiResult.source,
              poi = poiResult.item,
              info = {
                source: source,
                id: poi.id,
                name: poi.name,
                location: poi.location.toString(),
                address: poi.address
              }

            marker.setMap(map)
            infoWindow.setMap(map)

            marker.setPosition(poi.location)
            infoWindow.setPosition(poi.location)
            console.log('这是this', vm)
            vm.address = info.name
            infoWindow.setContent('坐标: <pre>' + info.name + '</pre>')
            infoWindow.open(map, marker.getPosition())
          })
        }
      },
      addAddress () { //保存地址在sessionStorage中
        sessionStorage.setItem(`address`, this.address)
        window.location.href='#/publish'
       // console.log(`保存了`, sessionStorage.getItem(`address`))
      }
    }
  }
</script>

<style scoped>
  #map {
    position: fixed;
    top: 0px;
    width: 100%;
    height: 100%;
    background: #ffffff;
  }

  #map >>> .el-input__inner {
    border: none;
    padding: 0px 0px;
  }
</style>
