<template>
  <!-- 가능한 빨리 재생을 시작하는 video속성 -->
  <video v-if="mainStream" class="main col-12" autoplay />
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
  /* width: 98%; */
  /* height: 60vh;
  border-radius: 2% 2% 2% 2%;
  margin-top: 20px; */
  aspect-ratio: 4/3;
  max-height: calc(100vh - 6rem);
  max-width: 100%;
  width: fit-content;
  height: fit-content;
  display: block;

  /* filter: sepia(80%); */

  background-color: black;
  border-radius: 1.28rem;
  object-fit: cover;
  align-self: center;
  margin-left: auto;
  margin-right: auto;
  /* border-color: red; */
}
.sub {
  /* width: 320px; */
  /* height: 20vh; */
  /* border-radius: 4% 4% 4% 4%; */
  aspect-ratio: 4/3;
  max-height: calc(100vh - 6rem);
  max-width: 100%;
  width: fit-content;
  height: fit-content;
  display: block;

  /* filter: sepia(80%); */

  background-color: black;
  border-radius: 1.28rem;
  object-fit: cover;
  align-self: center;
}
/* .main {
  height: 70%;
}
.sub {
  height: 50%;
} */
</style>
