<template>
  <div>
    <div id="app_chat_list" class="container" v-on:scroll.passive="scrolled">
      <Message
        v-for="(msg, index) in msgs"
        :key="index"
        :msg="msg"
        :myId="myId"
        :fromId="fromId"
      />
    </div>
  </div>
</template>

<script>
import Message from "./Message";
export default {
  name: "MessageList",
  components: {
    Message
  },
  props: {
    msgs: {
      type: Array
    },
    myId: {
      type: String
    },
    fromId: {
      type: String
    }
  },
  data() {
    return {
      isScrolled: false,
    };
  },
  updated() {
    if (!this.isScrolled) {
      var objDiv = document.getElementById("app_chat_list");
      objDiv.scrollTop = objDiv.scrollHeight + 500;
    }
  },
  methods: {
    scrolled() {
      var objDiv = document.getElementById("app_chat_list");
      if (objDiv.scrollTop >= objDiv.scrollHeight - 500) {
        this.isScrolled = false;
        this.recentmsg = 0;
      } else {
        this.isScrolled = true;
      }
    },
    scrolldown() {
      var objDiv = document.getElementById("app_chat_list");
      objDiv.scrollTop = objDiv.scrollHeight;
      this.isScrolled = false;
      this.recentmsg = 0;
    }
  }
};
</script>

<style scoped>
.container {
  height: 500px;
  background: #FFFF;
  overflow: scroll;
  border-radius: 10px 10px 0px 0px;
}
#rctmsg {
  height: 0px;
  background: #ffffffbe;
  position: relative;
  top: -30px;
}

</style>