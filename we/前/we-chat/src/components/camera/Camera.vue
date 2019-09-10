<template>
    <div>
      <h4>调用摄像头</h4>
      <video id="v"></video>
    </div>
</template>

<script>
  export default {
    data () {  //组件的数据区
      return {}
    },
    methods: {  //方法区

    },
    components: {  //子组件进行标签映射

    },
    mounted () {
      !(function () {
        function userMedia() {
          return navigator.getUserMedia = navigator.getUserMedia ||
            navigator.webkitGetUserMedia ||
            navigator.mozGetUserMedia ||
            navigator.msGetUserMedia || null;
        }
        if (userMedia()) {
          let constraints = {
            video: true,
            audio: false
          };
          let media = navigator.getUserMedia(constraints, function (stream) {
            let v = document.getElementById('v');
            let url = window.URL || window.webkitURL;
            v.src = url ? url.createObjectURL(stream) : stream;
            v.play();
          }, function (error) {
            console.log("ERROR");
            console.log(error);
          });
        } else {
          console.log("不支持");
        }
      })();
    }
  }
</script>

<style>

</style>
