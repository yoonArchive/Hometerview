<template>
  <!-- 가능한 빨리 재생을 시작하는 video속성 -->
  <video v-if="mainStream" class="main" autoplay />
  <video v-else class="sub" autoplay />

  <!-- <div class="video-name">{{ clientData }}</div> -->
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: Object,
    mainStream: Boolean
  },
  computed: {
    clientData() {
      // 이름 띄우기
      const { clientData } = this.getConnectionData();
      return clientData;
    },
    clinentId() {
      const { clientId } = this.getConnectionData();
      return clientId;
    }
  },
  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
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
<style scoped>
.main {
  width: 98%;
  height: 100%;
  border-radius: 2% 2% 2% 2%;
  margin-top: 20px;
}
.sub {
  /* width: 320px; */
  height: 200px;
  border-radius: 4% 4% 4% 4%;
}
/* .main {
  height: 70%;
}
.sub {
  height: 50%;
} */
</style>
