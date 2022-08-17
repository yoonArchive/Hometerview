<template>
  <div class="sesstion-member-list-wrapper">
    <div v-if="resumeQuestionList.length != 0">
      <div class="session-cover-letter-title">
        {{ studySpaceDetail.studyJoins[studentindex].user.userName }}님의
        자소서입니다.
      </div>

      <div class="wrapper" style="margin-top:20px">
        <div v-for="(item, index) in resumeQuestionList.length" :key="index">
          <div class="accordion {'active': index == 0}">
            <div
              class="accordion_tab {'active': index == 0}"
              @click="clicktabs(item, $event)"
            >
              {{ resumeQuestionList[index].question }}
              <div class="accordion_arrow">
                <img src="https://i.imgur.com/PJRz0Fc.png" alt="arrow" />
              </div>
            </div>
            <div class="accordion_content">
              <div class="accordion_item">
                <p style="font-size:15px">
                  {{ resumeQuestionList[index].answer }}
                </p>
              </div>
              <div class="accordion_item">
                <p class="item_title">예상 질문 리스트</p>
                <div
                  v-for="(item,
                  idnex) in expectedQuestionList.personalQuestions"
                  :key="idnex"
                >
                  <div class="row" style="font-size:20px">
                    {{ item.contents }}

                    <!-- <div class="col">
                      <img
                        :src="
                          require(`@/assets/images/session/exprectedQuestionTTSImg.png`)
                        "
                        @click="childttsrequest(item.contents)"
                      />
                    </div> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <div style="text-align: center;">
        <div class="no-cover-letter-wrapper">
          등록된 자소서가 없습니다.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
export default {
  name: "MemberCoverLetter",
  data() {
    return {
      selectedNum: 0
    };
  },
  props: {
    studentindex: Number
  },
  computed: {
    ...mapGetters([
      "resumeQuestionList",
      "studySpaceDetail",
      "expectedQuestionList"
    ])
  },

  methods: {
    ...mapMutations(["SET_SELECTED_QUESTION_NUM"]),
    ...mapActions(["getQuestionList", "changettsrequest"]),
    childttsrequest(data) {
      console.log("tts 데이터");
      console.log(data);
      console.log("tts 데이터");
      this.changettsrequest(data);
    },
    changeSelectedNum(item) {
      this.selectedNum = item - 1;
      console.log(this.selectedNum);
    },
    clicktabs(item, event) {
      this.selectedNum = item - 1;
      this.SET_SELECTED_QUESTION_NUM(this.selectedNum);
      console.log(this.resumeQuestionList);
      this.getQuestionList();
      var tabs = document.getElementsByClassName("accordion_tab");
      console.log(tabs);
      console.log(event.target);
      for (var i = 0; i < tabs.length; i++) {
        tabs[i].classList.remove("active");
        tabs[i].parentElement.classList.remove("active");
        // tabs[i].parent().classList.remove("active");
      }
      event.target.classList.add("active");
      event.target.parentElement.classList.add("active");
    }
  }
};
</script>

<style scoped>
.session-cover-letter-title {
  margin-top: 10px;
}
.no-cover-letter-wrapper {
  border: 1px solid #653fd3;
  background-color: #f3f4ff;
  border-radius: 10px;
  text-align: center;
  padding: 20px;
  margin-top: 20px;
  font-family: "티머니 둥근바람";
}
@import url("https://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Josefin Sans", sans-serif;
}

body {
  margin: 20px;
  background: #f4f4f6;
}
.sesstion-member-list-wrapper {
  margin-top: 30px;
}
.accordion {
  word-break: break-all;
  width: auto;
  height: 80px;
  margin: 0 auto;
  background: #fff;
  border-radius: 3px;
  box-shadow: 0 0 5px rgba(225, 225, 225, 1);
  overflow: hidden;
  transition: height 0.3s ease;
  margin-bottom: 20px;
}

.accordion .accordion_tab {
  padding: 20px;
  cursor: pointer;
  user-select: none;
  font-size: 20px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 2px;
  position: relative;
}

.accordion .accordion_tab .accordion_arrow {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 20px;
  width: 15px;
  height: 15px;
  transition: all 0.3s ease;
}

.accordion .accordion_tab .accordion_arrow img {
  width: 100%;
  height: 100%;
}

.accordion .accordion_tab.active .accordion_arrow {
  transform: translateY(-50%) rotate(180deg);
}

.accordion.active {
  height: auto;
}

.accordion .accordion_content {
  padding: 20px;
  border-top: 1px solid #e9e9e9;
}

.accordion .accordion_content .accordion_item {
  margin-bottom: 20px;
}

.accordion .accordion_content .accordion_item p.item_title {
  font-weight: 600;
  margin-bottom: 10px;
  font-size: 18px;
  color: #6adda2;
}

.accordion .accordion_content .accordion_item p:last-child {
  color: #9a9b9f;
  font-size: 14px;
  line-height: 20px;
}
</style>
