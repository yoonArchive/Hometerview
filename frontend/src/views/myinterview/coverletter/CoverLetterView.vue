<template>
  <div class="member-cover-letter-title" v-if="!isloading">
    <div class="row" v-if="!isChange">
      <div class="col-6">
        <div class="member-cover-letter-stdname">
          {{ resumeContents[resumeindex].resumeTitle }} 자소서
        </div>
      </div>
      <div class="col">
        <div class="d-flex flex-row-reverse coverletter-buttons">
          <button
            type="button"
            class="btn"
            style="background-color:#653FD3; color: white;"
            @click="changeIsChange"
          >
            제목 수정
          </button>
          <button
            @click="removeResume(resumeindex)"
            type="button"
            class="btn"
            style="background-color:#653FD3; color: white;"
          >
            자소서 삭제
          </button>
          <router-link :to="{ name: 'myinterview' }">
            <button
              type="button"
              class="btn"
              style="background-color:#653FD3; color: white;"
            >
              목록으로
            </button>
          </router-link>
        </div>
      </div>
    </div>
    <div v-else>
      <div class="d-flex mb-3 coverletter-buttons">
        <input
          type="text"
          v-model="resumeContents[resumeindex].resumeTitle"
          class="me-auto p-2"
        />
        <button
          @click="changeResumeTitle(resumeindex)"
          type="button"
          class="btn p-2"
          style="background-color:#653FD3; color: white;"
        >
          수정
        </button>
        <button
          @click="cancelChange"
          type="button"
          class="btn p-2"
          style="background-color:#653FD3; color: white;"
        >
          취소
        </button>
      </div>
      <div></div>
    </div>
  </div>
  <cover-letter-detail :resumeindex="resumeindex"></cover-letter-detail>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import CoverLetterDetail from "./components/CoverLetterDetail.vue";
export default {
  data() {
    return {
      isloading: true,
      resumeindex: this.$route.params.resumeindex,
      isChange: false,
      originaltitle: ""
    };
  },

  async mounted() {
    await this.getResumeInfo();
    await this.getCurrentResume(this.resumeindex);
    await this.settingResumeIndex(this.resumeindex);
    this.originaltitle = this.resumeContents[this.resumeindex].resumeTitle;
    this.isloading = false;
  },
  methods: {
    ...mapActions([
      "settingResumeIndex",
      "getCurrentResume",
      "getResumeInfo",
      "changeNewResumeTitle",
      "removeResumeAction"
    ]),
    removeResume(resumeindex) {
      this.removeResumeAction(resumeindex);
    },
    changeIsChange() {
      this.originaltitle = this.resumeContents[this.resumeindex].resumeTitle;
      this.isChange = true;
    },
    cancelChange() {
      this.resumeContents[this.resumeindex].resumeTitle = this.originaltitle;
      this.isChange = false;
    },
    changeResumeTitle(resumeindex) {
      this.changeNewResumeTitle(resumeindex);
      this.isChange = false;
    }
  },
  components: {
    CoverLetterDetail
  },

  computed: {
    ...mapGetters(["resumeContents", "currentResume"])
  }
};
</script>

<style>
.coverletter-buttons {
  gap: 10px;
}
#button-bundels {
}
#b-button {
  background-color: blueviolet;
  color: white;
  border-radius: 10%;
  outline: 0;
  border: 0ch;
}
#b-button:hover {
  background-color: #9779f0;
}
#b-button:active {
  background-color: #7455d1;
}
</style>
