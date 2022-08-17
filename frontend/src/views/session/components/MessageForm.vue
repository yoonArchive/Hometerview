<template>
  <div>
    <form v-on:keydown.enter.prevent="submitForm">
      <div class="chat-input">
        <input
          id="input_textarea"
          type="textarea"
          :rows="2"
          placeholder="채팅을 입력하세요"
          v-model="messageForm.message"
        />
        <img
          :src="require('@/assets/images/session/emailSend.png')"
          @click.prevent="submitForm"
          class="my-btn"
        />
      </div>
      <!-- <button
        class="my-btn"
        type="primary"
        @click.prevent="submitForm"
        style="border-radius:10px 10px 10px 10px; height:4vh; width:20%;"
      >
        입력
      </button> -->
    </form>
  </div>
</template>

<script>
export default {
  name: "MessageForm",
  data() {
    return {
      messageForm: {
        message: ""
      }
    };
  },
  props: {
    userName: String,
    userImg: String
  },
  methods: {
    submitForm() {
      const msg = this.messageForm.message.trim();
      if (msg != "") {
        const string = JSON.stringify({
          userName: this.userName,
          userImg: this.userImg,
          msg: msg
        });
        this.$emit("sendMsg", string);
      }
      this.messageForm.message = "";
    }
  }
};
</script>

<style scoped>
.chat-input {
  border-top: 2px solid rgba(60, 51, 176, 0.1);
}

.chat-input input {
  border: #fff;
  border-radius: 10px 10px 10px 10px;
  height: 5vh;
  /* overflow: hidden; */
  width: 90%;
  font-size: 2vh;
  padding: 2vh;
  background: rgba(0, 0, 0, 0.001);
  /* overflow: auto; */
  /* word-wrap: break-word; */
  /* word-break: break-word; */
  /* height: 4vh; */
  /* bottom: 0;
  left: 0; */
}
input:focus {
  outline: none;
}
.chat-input ::placeholder {
  color: #b3a2e3;
}
.my-btn {
  display: inline-block;
  margin-right: 10px;
  height: 4vh;
}
</style>
