<template>
  <!-- 가능한 빨리 재생을 시작하는 video속성 -->
  <video autoplay />
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: {
      type: Object,
      default: () => {}
    }
  },

  mounted() {
    // 비디오 html에 화면 띄우기
    // console.log('check')
    // console.log(this.$el) // <video autoplay="" id="local-video-undefined"></video> 가 뜨게 된다.
    this.streamManager.addVideoElement(this.$el);
  }
};
</script>
