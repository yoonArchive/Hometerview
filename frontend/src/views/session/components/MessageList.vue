<template>
  <div>
    <div id="app_chat_list" class="container" style="overflow-x: hidden">
      <Message
        v-for="(msg, index) in msgs"
        :key="index"
        :msg="msg.fromMessage"
        :myId="myId"
        :fromId="msg.fromId"
      />
    </div>
  </div>
  <!-- <div>
    <div id="app_chat_list" class="container" @scroll.passive="scrolled()">
      <Message
        v-for="(msg, index) in msgs"
        :key="index"
        :msg="msg.fromMessage"
        :myId="myId"
        :fromId="msg.fromId"
      />
    </div>
  </div> -->
</template>

<script>
import Message from "./Message";
export default {
  name: "MessageList",
  components: {
    Message
  },
  props: {
    msgs: Array,
    myId: String
  },
  data() {
    return {
      isScrolled: false
    };
  },
  updated() {
    if (!this.isScrolled) {
      const objDiv = document.getElementById("app_chat_list");
      objDiv.scrollTop = objDiv.scrollHeight;
    }
  },
  methods: {
    // scrolled() {
    //   const objDiv = document.getElementById("app_chat_list");
    //   if (objDiv.scrollTop >= objDiv.scrollHeight - 500) {
    //     this.isScrolled = false;
    //   } else {
    //     this.isScrolled = true;
    //   }
    // },
    // scrolldown() {
    //   const objDiv = document.getElementById("app_chat_list");
    //   objDiv.scrollTop = objDiv.scrollHeight;
    //   this.isScrolled = false;
    // }
  }
};
</script>

<style scoped>
.container {
  background: #ffff;
  overflow: scroll;
  border-radius: 10px 10px 10px 10px;
  height: 85vh;
}
.container::-webkit-scrollbar {
  width: 10px;
}
.container::-webkit-scrollbar-track {
  background-color: grey;
}
.container::-webkit-scrollbar-thumb {
  background-color: #2f3542;
}
</style>
