<template>
  <div v-if="streamManager">
    <ov-video :streamManager="streamManager" :mainStream="mainStream" />
    <div class="video-name">{{ clientData }}</div>
  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo
  },

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
  }
};
</script>
<style scoped>
/* .video-name {
  position: relative;
  background-color: #272930;
  color: whitesmoke;
} */
.video-name {
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
</style>
