<template>
  <div class="container">
    <div class="my-top">
      <div class="my-left">
        <div class="review-1">
          <!-- <h3>자기소개서 개수 : {{ resumeContents.length }}</h3> -->
          <!-- <div class="row">
            <h3 id="icon" class="col" style="width:30vh;">
              <span @click="this.addNewResume()">
                <font-awesome-icon
                  icon="fa-solid fa-square-plus"
                  style="height:4.3vh; color:#7a5fc9;"
                />
                <span
                  class="col"
                  style="font-family: '티머니 둥근바람'; font-size: 2.1vh;font-weight: 500; margin-top: 0.01vh;"
                >
                  자기소개서를 추가해보세요.
                </span>
              </span>
            </h3>
          </div> -->
          <div class="dday-button row">
            <h1 id="icon col" style="width:1vh;">
              <span @click="this.addNewResume()">
                <font-awesome-icon
                  icon="fa-solid fa-square-plus"
                  style="height:4.3vh; color:#7a5fc9;"
                />
              </span>
            </h1>
            <div
              class="align-self-center col"
              style="font-family: '티머니 둥근바람'; font-size: 2.1vh;font-weight: 500; margin-left:2vh; margin-top: 0.01vh;"
            >
              자기소개서를 추가해보세요.
            </div>
          </div>
        </div>
        <main>
          <ol class="gradient-list">
            <li v-for="(item, index) in resumeContents.length" :key="index">
              <router-link
                :to="{
                  name: 'coverletter',
                  params: { resumeindex: item - 1 }
                }"
                id="cover-router"
              >
                <div>{{ resumeContents[item - 1].resumeTitle }}</div>
              </router-link>
            </li>
          </ol>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import Modaldday from "../components/modal-dday";
import ModalEdit from "../components/modal-ddayEdit";
import ReviewForm from "../review/components/ReviewForm";
// import ReviewNew from './review/components/ReviewNewView'
import StudyCalendar from "../StudyCalendar";
import ReviewNewView from "../review/ReviewNewView.vue";
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
      showReviewForm: false,
      roomName: "",
      openedModal: null
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

    ...mapActions(["getResumeInfo", "getReviewInfo", "addNewResume"]),
    findresumes() {
      this.getResumeInfo();
    },
    addResume() {
      this.addNewResume();
    }
  },
  mounted() {
    this.findresumes();
  }
};
</script>
<style lang="scss" scoped>
// @import "compass/css3";

// div {
//   width: 200px;
//   margin: 30px;
// }

// h3 {
//   font: 400 40px/1.5 Helvetica, Verdana, sans-serif;
//   margin: 0;
//   padding: 0;
// }

// ul {
//   list-style-type: none;
//   margin: 0;
//   padding: 0;
// }

// li {
//   font: 200 20px/1.5 Helvetica, Verdana, sans-serif;
//   border-bottom: 1px solid #ccc;
// }

// li:last-child {
//   border: none;
// }

li a {
  text-decoration: none;
  color: #000;

  -webkit-transition: font-size 0.3s ease, background-color 0.3s ease;
  -moz-transition: font-size 0.3s ease, background-color 0.3s ease;
  -o-transition: font-size 0.3s ease, background-color 0.3s ease;
  -ms-transition: font-size 0.3s ease, background-color 0.3s ease;
  transition: font-size 0.3s ease, background-color 0.3s ease;
  display: block;
  width: 900px;
}
li a:hover {
  font-size: 30px;
  background: #f6f6f6;
  width: 100%;
}
/*** FONTS ***/

/*** VARIABLES ***/
/* Colors */
$black: #1d1f20;
$blue: #653fd3;
$green: #7e61d4;
$yellow: #83e4e2;
$white: #fafafa;

/*** EXTEND ***/
/* box-shadow */
%boxshadow {
  box-shadow: 0.25rem 0.25rem 0.6rem rgba(0, 0, 0, 0.05),
    0 0.5rem 1.125rem rgba(75, 0, 0, 0.05);
}

/*** STYLE ***/
*,
*:before,
*:after {
  box-sizing: border-box;
}

html,
body {
  margin: 0;
  padding: 0;
}

body {
  background-color: $white;
  color: $black;
  font-family: "Raleway", sans-serif;
}

main {
  display: block;
  margin: 0 auto;
  max-width: 40rem;
  padding: 1rem;
}

ol.gradient-list {
  // font-size: 15px;

  counter-reset: gradient-counter;
  list-style: none;
  margin: 1.75rem 0;
  padding-left: 1rem;
  width: 900px;
  > li {
    background: white;
    border-radius: 0 0.5rem 0.5rem 0.5rem;
    @extend %boxshadow;
    counter-increment: gradient-counter;
    margin-top: 1rem;
    min-height: 3rem;
    padding: 1rem 1rem 1rem 3rem;
    position: relative;
    &::before,
    &::after {
      background: linear-gradient(135deg, $blue 0%, $green 100%);
      border-radius: 1rem 1rem 0 1rem;
      content: "";
      height: 3rem;
      left: -1rem;
      overflow: hidden;
      position: absolute;
      top: -1rem;
      width: 3rem;
    }
    &::before {
      align-items: flex-end;
      @extend %boxshadow;
      content: counter(gradient-counter);
      color: $black;
      display: flex;
      font: 900 1.5em/1 "Montserrat";
      justify-content: flex-end;
      padding: 0.125em 0.25em;
      z-index: 1;
    }

    @for $i from 1 through 5 {
      &:nth-child(10n + #{$i}):before {
        background: linear-gradient(
          135deg,
          rgba($green, $i * 0.2) 0%,
          rgba($yellow, $i * 0.2) 100%
        );
      }
    }
    @for $i from 6 through 10 {
      &:nth-child(10n + #{$i}):before {
        background: linear-gradient(
          135deg,
          rgba($green, 1 - (($i - 5) * 0.2)) 0%,
          rgba($yellow, 1 - (($i - 5) * 0.2)) 100%
        );
      }
    }
    + li {
      margin-top: 2rem;
    }
  }
}
</style>
<style scoped>
#icon {
  cursor: pointer;
}
#icon:hover {
  color: #653fd3;
}
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
  /* margin-left: 170px; */
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
  background-color: #653fd3;
  border-radius: 10px;
  color: white;
  padding: 10px;
  border: #653fd3;
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
/* span {
  font-size: 17px;
  letter-spacing: 0.04em;
} */

@import "../main.css";
/* @import './fullcalander/main.js'; */

.conatiner {
  display: flex;
  flex-direction: row;
  /* width: 1000px; */
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
  font-size: 20px;
}
.dday-button:hover {
  cursor: pointer;
  color: #653fd3;
}
</style>
