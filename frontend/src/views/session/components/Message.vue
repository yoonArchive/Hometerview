<template>
  <div class="chatbox" v-if="!ismy">
    <div>
      <img
        class="profile-photo"
        :src="require(`@/assets/images/session/profile.png`)"
        style="box-shadow: 0px 0px 7px 1px rgba(0, 0, 0, 0.6);"
      />&nbsp;&nbsp;{{ sendname }}
    </div>
    <div class="count-chat">
      <span id="msgs">{{ massage }}</span>
    </div>
  </div>
  <div class="my-chatbox">
    <div class="my-chat" v-if="ismy">
      <span id="msgs">{{ massage }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Message",
  props: {
    msg: String,
    myId: String,
    fromId: String
  },
  data() {
    return {
      ismy: this.equal(),
      sendname: "",
      massage: ""
    };
  },
  mounted() {
    // JSON확인
    this.sendname = JSON.parse(this.msg).userName;
    this.massage = JSON.parse(this.msg).msg;
  },
  methods: {
    equal() {
      if (this.myId == this.fromId) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>

<style scoped>
.profile-photo {
  height: 4.5vh;
  border-radius: 100%;
  background-color: #c4c1c9;
}
#msgs {
  font-size: 15px;
  margin: 2px;
  padding-left: 5px;
  padding-right: 5px;
}
.count-chat {
  background-color: #c4c1c9;
  margin: 5px;
  padding: 5px;
  border-radius: 0px 15px 15px 15px;
  width: fit-content;
  max-width: 66%;
  min-height: 2.25rem;
  text-align: left;
  display: inline-block;
  margin-left: 5vh;
  margin-top: 1.5vh;
  margin-bottom: 1vh;
  box-shadow: 0px 0px 7px 1px rgba(0, 0, 0, 0.6);
}
.my-chat {
  background-color: #b3a2e3;
  margin: 5px;
  padding: 5px;
  border-radius: 15px 15px 0px 15px;
  width: fit-content;
  text-align: left;
  display: inline-block;
  margin-left: 1vh;
  margin-top: 1.5vh;
  margin-bottom: 1vh;
  box-shadow: 0px 0px 7px 1px rgba(0, 0, 0, 0.6);
}
.my-chatbox {
  text-align: right;
  margin-top: 1.5vh;
}
.chatbox {
  text-align: left;
  margin-top: 1.5vh;
}
.conut {
  background-color: #c4c1c9;
  height: 5.5vh;
  width: 5.5vh;
  border-radius: 100% 100% 100% 100%;
}
</style>
