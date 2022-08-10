<template>
  <div class="plus-container">
    <div class="plus-buttons">
      <button @click="changeIsremove" id="cover-Mbutton">-</button>
      <button @click="addCurrentResume" id="cover-Pbutton">+</button>
    </div>

    <div v-if="currentResume.length != 0">
      <input
        type="button"
        v-for="(item, index) in currentResume.length"
        :key="index"
        :value="item"
        @click="returnnumbermethod(item)"
      />

      <div class="cover-inner">
        <h3>자소서 질문</h3>
        <input
          type="text"
          v-model="currentResume[selectedNum].question"
          id="cover-write"
        />
        <!-- <input type="textarea" v-model="currentResume[selectedNum].answer"/> -->
        <h3>질문 내용</h3>
        <textarea
          name=""
          id="cover-write"
          cols="30"
          rows="10"
          v-model="currentResume[selectedNum].answer"
        ></textarea>
        <button @click="saveResumeChange" id="b-button">저장하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      selectedNum: 0,
      isremove: false
    };
  },
  mounted() {
    console.log(this.currentResume.length);
  },
  props: {
    resumeindex: Number
  },
  computed: {
    ...mapGetters(["currentResume", "numberOfQuestion", "isLodding"]),
    length() {
      return this.currentResume.length;
    }
  },

  methods: {
    ...mapActions([
      "saveResumeChange",
      "addItemCurrentResume",
      "removeItemCueentResume"
    ]),
    addCurrentResume() {
      this.addItemCurrentResume();
    },
    changeIsremove() {
      this.isremove = !this.isremove;
    },
    returnnumbermethod(item) {
      if (this.isremove) {
        this.removeResumeQuestion(item);
        console.log("지우기 모드");
      } else {
        this.changeSelectedNum(item);
        console.log("선택 모드");
      }
    },
    async removeResumeQuestion(item) {
      await this.saveResumeChange();
      console.log("지우기 시작");
      await this.removeItemCueentResume(item - 1);
    },
    changeSelectedNum(item) {
      this.selectedNum = item - 1;
      console.log(this.selectedNum);
    }
  }
};
</script>

<style>
/* input[type=text]:focus {
  border: 3px solid #653FD3;
} */
#cover-write:focus {
  border: solid 3px #856ccf;
  outline: solid 1px #9376e9;
}
.cover-inner {
  display: flex;
  flex-direction: column;
  margin-top: 40px;
  /* justify-content: space-between; */
}
.plus-buttons {
  display: flex;
  flex-direction: row;
  margin-bottom: 30px;
  /* align-items: right; */
}
.plus-container {
  margin-top: 50px;
  display: flex;
  flex-direction: column;
}
#cover-Pbutton {
  border-radius: 40%;
  width: 50px;
  height: 40px;
  background-color: #5cb85c;
  border: 0;
  outline: 0;
}
#cover-Pbutton:hover {
  background-color: #6ed36e;
}
#cover-Pbutton:active {
  background-color: #367c36;
}
#cover-Mbutton {
  border-radius: 40%;
  width: 50px;
  height: 40px;
  color: white;
  background-color: #e62626;
  border: 0;
  outline: 0;
}

#cover-Mbutton:hover {
  background-color: #ec6f6f;
}
#cover-Mbutton:active {
  background-color: #e42f2f;
}
</style>
