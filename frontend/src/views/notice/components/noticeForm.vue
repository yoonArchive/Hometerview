<template>
  <div class="container">
    <div class="title">
      <h1>공지사항 작성</h1>
    </div>
    <div class="inner-container">
      <form @submit.prevent="onSubmit">
        <div class="mb-3">
          <label for="title" class="form-label col-sm-2">제목: </label>
          <input
            type="text"
            class="form-control has-success col-sm-10"
            v-model="newnotice.noticeTitle"
            id="title"
            placeholder="공지사항 제목을 입력하세요."
          />
        </div>
        <div class="mb-3">
          <label for="exampleFormControlTextarea1" class="form-label"
            >내용:
          </label>
          <textarea
            class="form-control col-sm-12"
            v-model="newnotice.noticeContents"
            type="text"
            id="content"
            rows="6"
            placeholder="공지사항 내용을 입력하세요."
          ></textarea>
        </div>
        <div class="buttonbundle">
          <button id="button-review" v-if="action === 'create'">
            작성하기
          </button>
          <button id="button-review" v-else><p id="a">수정하기</p></button>
          <button id="button-review">
            <router-link class="routerlink" :to="{ name: 'notices' }">
              목록
            </router-link>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "noticeForm",
  props: {
    notice: Object,
    action: String
  },
  data() {
    return {
      newnotice: {
        noticeNo: this.$route.params.noticeNo,
        noticeContents: this.notice.noticeContents,
        noticeTitle: this.notice.noticeTitle,
        writeDate: ""
      }
    };
  },

  methods: {
    ...mapActions(["createNotice", "updateNotice"]),
    onSubmit() {
      if (this.action === "create") {
        this.createNotice(this.newnotice);
      } else if (this.action === "update") {
        const payload = {
          noticeNo: this.notice.noticeNo,
          ...this.newnotice
        };
        this.updateNotice(payload);
      }
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 50%;
}
.inner-container {
  padding-top: 100px;
  height: 50%;
}
.title {
  display: flex;
  justify-content: center;
  margin-top: 50px;
}
.buttonbundle {
  text-align: center;
}
a {
  text-decoration: none;
  color: black;
}
input[type="button"],
button,
.button {
  -moz-appearance: none;
  -webkit-appearance: none;
  -ms-appearance: none;
  appearance: none;
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  background-color: transparent;
  border-radius: 0.375em;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 700;
  height: 3.5em;
  letter-spacing: 0.075em;
  line-height: 3.5em;
  padding: 0 2.25em;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  white-space: nowrap;
  margin: 0 0.5em 0 0;
}
input[type="button"]:hover,
button:hover,
.button:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
input[type="button"]:active,
button:active,
.button:active {
  background-color: rgba(161, 104, 253, 0.15);
}
</style>
