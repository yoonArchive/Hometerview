<template>
  <!-- 가능한 빨리 재생을 시작하는 video속성 -->
  <video v-if="mainStream && interviewMode" class="main col-12" autoplay />
  <video v-else-if="!mainStream && interviewMode" class="sub" autoplay />
  <video v-else-if="!interviewMode" class="not-interview-mode" autoplay />
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "OvVideo",
  props: {
    streamManager: Object,
    mainStream: Boolean,
    interviewMode: Boolean
  },
  emits: [],
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["posture"]),
    clientData() {
      // 이름 띄우기
      const { clientData } = this.getConnectionData();
      return clientData;
    }
  },
  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    }
  },
  updated() {
    this.streamManager.addVideoElement(this.$el);
  },
  mounted() {
    this.streamManager.addVideoElement(this.$el);
  }
};
</script>
<style scoped>
.main {
  aspect-ratio: 4/3;
  margin-top: -0.9vh;
  max-height: calc(100vh - 6rem);
  max-width: 100%;
  width: fit-content;
  height: 63vh;
  display: block;
  /* border-color: v-bind(color) */
  border: solid v-bind(posture) 3px;
  background-color: black;
  border-radius: 1.2rem;
  object-fit: cover;
  align-self: center;
  margin-left: auto;
  margin-right: auto;
}
.sub {
  /* width: 320px; */

  aspect-ratio: 4/3;
  max-height: calc(100vh - 6rem);
  max-width: 100%;
  width: fit-content;
  height: 23vh;
  display: block;
  /* border: solid #8c1d1d 3px; */
  background-color: black;
  border-radius: 1.2rem;
  object-fit: cover;
  align-self: center;
}
.not-interview-mode {
  aspect-ratio: 4/3;
  max-height: calc(100vh - 6rem);
  max-width: 100%;
  width: fit-content;
  height: 23vh;
  display: block;
  /* border: solid #8c1d1d 3px; */
  background-color: black;
  border-radius: 1.2rem;
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
