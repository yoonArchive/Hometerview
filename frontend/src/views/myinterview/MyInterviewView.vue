<template>
  <div class="mypage">
    <div class="mypage-banner">
      <router-link :to="{ name: 'diary' }" id="word">
        <button id="mypage-button">
          <font-awesome-icon icon="fa-solid fa-calendar" />&nbsp; &nbsp;
          <span>다이어리</span>
        </button>
      </router-link>
      <router-link :to="{ name: 'coverPage' }" id="word">
        <button id="mypage-button">
          <font-awesome-icon icon="fa-solid fa-file" />&nbsp; &nbsp;
          <span>자기소개서</span>
        </button>
      </router-link>
      <router-link :to="{ name: 'recordingPage' }" id="word">
        <button id="mypage-button">
          <font-awesome-icon icon="fa-solid fa-tape" />&nbsp; &nbsp;
          <span>녹화</span>
        </button>
      </router-link>
    </div>
    <div class="router-view">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import Modaldday from "./components/modal-dday";
import ModalEdit from "./components/modal-ddayEdit";
import ReviewForm from "./review/components/ReviewForm";
// import ReviewNew from './review/components/ReviewNewView'
import StudyCalendar from "./StudyCalendar";
import ReviewNewView from "./review/ReviewNewView.vue";
export default {
  components: {
    StudyCalendar,
    Modaldday,
    ModalEdit,
    ReviewForm,
    // ReviewNew,
    ReviewNewView
  },
  data() {
    return {
      ddays: {
        ddayTitle: "",
        ddayDate: ""
      },
      showModal: false,
      // showModalE: false,
      showReviewForm: false,
      roomName: "",
      headers: ["번호", "제목", "날짜", "유형"],
      openedModal: null
      // imgsource1: require("../../assets/images/myinterview1.png"),
      // imgsource2: require("../../assets/images/myinterview2.png"),
      // imgsource3: require("../../assets/images/myinterview3.jpg")

      // reviewContents:{},
      // review:{}
    };
  },
  computed: {
    ...mapGetters([
      "resumeContents",
      "numberOfResume",
      "reviewContents",
      "numberOfReview",
      "currentDdays",
      "restday",
      "ddaylen",
      "dday"
    ])
  },
  methods: {
    showModalE(id) {
      this.openedModal = id;
    },
    getLengthOfObject(obj) {
      let lengthOfObject = Object.keys(obj).length;
      console.log(lengthOfObject);
    },

    ...mapActions([
      "getResumeInfo",
      "getReviewInfo",
      "addNewResume",
      "getDdayInfo",
      "deleteReview",
      "deleteDDAY",
      "getDdayDetail"
    ]),
    findresumes() {
      this.getResumeInfo();
    },
    addResume() {
      this.addNewResume();
    },

    getReview() {
      this.getReviewInfo();
    },

    getDday() {
      this.getDdayInfo();
    },
    refreshAll() {
      // 새로고침
      this.$router.go();
    }
  },
  mounted() {
    this.findresumes();
    this.getReview();
    this.getDday();
    window.scrollTo(0, 0);
  },

  beforeMount() {
    // this.getReview();
    // this.getDday();
  }
};
</script>

<style scoped>
/*라우터 버튼디자인 */
.router-view {
  height: 100%;

  margin: 10px;
}
#title-word {
  margin-bottom: 50px;
}
.mypage {
  display: flex;
  flex-direction: row;
  /* align-items: center; */
  /* margin-right: 50px; */
}
.mypage-banner {
  display: flex;
  flex-direction: column;
  margin-top: 104px;
  margin-right: 30px;
  /* margin: 50px; */
  /* position: absolute; */
}
#mypage-button {
  /* border: 1px rgb(160, 160, 160); */
  border: none;
  width: 300px;
  border-radius: 10px;
  height: 50px;
  background-color: #f3f4ff;
  margin: 6px;
  text-align: left;
  display: flex;
  align-items: center;
}
/* 라우터 버튼 호버*/
#mypage-button:hover #mypageicon {
  background-color: #653fd3;
  border-radius: 10px;
  color: white;
  padding: 10px;
  border: #653fd3;
}
#mypage-button:hover {
  border-radius: 10px;
  border: solid #653fd3;
}
/* #mypage-button:hover, */
.route-active #mypage-button {
  background-color: #7a5fc9;
  border-radius: 10px;
  color: white;
  padding: 10px;
  border: #7a5fc9;
  /* cursor: pointer; */
}
/* #mypage-button:active {
  border: solid #653fd3;
} */

#mypageicon {
  width: 50px;
  height: 50px;
  padding: 10px;
  border-radius: 10%;
  margin-right: 30px;
}
#word {
  text-decoration-line: none;
}
span {
  font-size: 17px;
  letter-spacing: 0.04em;
}

/* @import './fullcalander/main.js'; */

.conatiner {
  display: flex;
  flex-direction: row;
}
.my-top {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.my-left {
  display: flex;
  flex-direction: column;
}
.calendar {
  width: 1000px;
  /* height: 800px; */
  /* display: flex; */
}

#cover-router {
  text-decoration-line: none;
}
.review-1 {
  /* margin-top: 100px; */
}
.review-2 {
  margin-top: 100px;
}

.review-3 {
  /* display: flex;
  flex-direction: row; */
  /* margin: 30px; */
}
.conatiner {
  display: flex;
  flex-direction: column;
}
#button-review {
  width: 100px;
  height: 50px;
  background-color: #653fd3;
  border-color: #653fd3;
  border-radius: 10%;
  margin: 5px;
}
#a {
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
  margin: 3px;
}
.buttonbundle {
  flex-direction: row-reverse;
  margin: 10px;
  width: 100px;
}
.table {
  text-align: center;
  padding: 10px;
}
th {
  text-align: left;
}
/* td {
    text-align: right;
  } */

/* 달력 스타일 */
h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b {
  /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}

.fc {
  /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}

.card {
  margin: 20px;
}
.card-text-2 {
  background-color: rgb(114, 209, 114);
  border-radius: 10%;
  color: white;
  padding: 5px;
}

.card-text-1 {
  background-color: rgb(236, 145, 145);
  border-radius: 10%;
  color: white;
  padding: 5px;
}

#inner-table {
  border: #653fd3;
}
</style>
