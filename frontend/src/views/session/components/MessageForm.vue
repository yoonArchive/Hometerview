<template>
  <form v-on:keydown.enter.prevent="submitForm">
    <input
      id="input_textarea"
      type="textarea"
      :rows="2"
      placeholder="채팅을 입력하세요."
      v-model="messageForm.message"
      style="width:80%"
    />

    <button
      class="my-btn"
      type="primary"
      @click.prevent="submitForm"
      style="border-radius:0px 0px 10px 0px; width:20%; height:3%;"
    >
      입력
    </button>
  </form>
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
    userName: String
  },
  methods: {
    submitForm() {
      const msg = this.messageForm.message.trim();
      if (msg != "") {
        // event.preventDefault();
        // this.$emit("sendMsg", "[" + this.userName + "] : " + msg);
        const string = JSON.stringify({
          userName: this.userName,
          msg: msg
        });
        this.$emit("sendMsg", string);
      }
      this.messageForm.message = "";
    }
  }
};
</script>

<style scoped></style>
