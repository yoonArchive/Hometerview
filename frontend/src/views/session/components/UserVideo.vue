<template>
  <div v-if="streamManager" style="position: relative;">
    <div class="large-video-name" v-if="mainStream">{{ clientData }}</div>
    <div class="small-video-name" v-else>{{ clientData }}</div>
    <ov-video
      :stream-manager="streamManager"
      :mainStream="mainStream"
      :interviewMode="interviewMode"
      :checkId="checkId"
    />
  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",
  components: {
    OvVideo
  },
  emits: [],
  props: {
    streamManager: Object,
    mainStream: Boolean,
    interviewMode: Boolean,
    checkId: String
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
  }
};
</script>
<style scoped>
/* .video-name {
  position: relative;
  background-color: #272930;
  color: whitesmoke;
} */
.large-video-name {
  /* display: inline-block; */
  /* padding-left: 5px;
  padding-right: 5px; */
  color: whitesmoke;
  font-weight: bold;
  position: absolute;
  z-index: 999;
  padding: 5px;
  font-weight: 700;
}
.small-video-name {
  /* display: inline-block; */
  /* padding-left: 5px;
  padding-right: 5px; */
  color: whitesmoke;
  font-weight: bold;
  position: absolute;
  z-index: 999;
  padding: 5px;
  font-weight: 700;
  padding-left: 2vh;
}
</style>
