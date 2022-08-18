<template>
<div class="container">
  <div class="my-top">
    <div class="my-left">
      <div class="d-flex justify-content-start">
        <div class="dday-button row">
          <h1 id="icon col" style="width:1vh;">
            <span @click="showModal = true">
              <font-awesome-icon icon="fa-solid fa-square-plus" style="height:4.3vh; color:#7a5fc9;"/>
            </span>
          </h1>
          <div class="align-self-center col"
              style="font-family: '티머니 둥근바람'; font-size: 2.1vh;font-weight: 500; margin-left:2vh; margin-top: 0.01vh;">
            디데이를 추가해 보세요
          </div>
        </div>
      </div>
      <div class="review-3 ">
          <div class="card-list">
          <div v-for="(ddays, index) in currentDdays" :key="index">
          <div v-if="restday[index]>5" class="card mb-2" style="max-width: 18rem;">
            <div class="card-header" >{{ddays.ddayTitle}} </div>
            <div  class="card-body">
              <h6 class="card-title">
              <span class="inner-dday1">D-{{restday[index]}}</span>
              <!-- <span v-else></span> -->
              <span class="inner-dday2">
              <span id="icon" @click="showModalE(ddays.ddayNo), getDdayDetail(ddays.ddayNo)">
                      <font-awesome-icon icon="fa-solid fa-pen-to-square" />
              </span>

              <span id="icon" @click="deleteDDAY(ddays.ddayNo)">
                      <font-awesome-icon icon="fa-solid fa-trash-can"  />
                  </span>
                </span>
              </h6>

            </div>

          </div>
           <div v-else-if="0<=restday[index]&& restday[index]<=5" class="card mb-2" style="max-width: 18rem;" id="dday-up">
            <div class="card-header" >{{ddays.ddayTitle}} </div>
            <div  class="card-body">
              <h6 class="card-title">
              <span class="inner-dday1">D-{{restday[index]}}</span>
              <!-- <span v-else></span> -->
              <span class="inner-dday2">
              <span id="icon" @click="showModalE(ddays.ddayNo), getDdayDetail(ddays.ddayNo)">
                      <font-awesome-icon icon="fa-solid fa-pen-to-square" />
              </span>

              <span id="icon" @click="deleteDDAY(ddays.ddayNo)">
                      <font-awesome-icon icon="fa-solid fa-trash-can"  />
                  </span>
                </span>
              </h6>

            </div>

          </div>
          </div>

          </div>

      </div>

      <hr/>
    </div>
     <div class="calendar">
    <StudyCalendar :reviews="reviewContents" :dday="currentDdays"></StudyCalendar>
  </div>

    <ModalEdit v-if="openedModal !== null" @close="openedModal = null" :dday="this.dday" :key="this.openedModal" >
      <!-- <h3 slot="header">dday 수정</h3> -->
    </ModalEdit>



     <Modaldday v-if="showModal" @close="showModal = false" :dday="currentDdays">

       <!-- <h3 slot="header">dday 생성</h3> -->
     </Modaldday>
  </div>
  <hr>
  <div class="review-2">
    <h1>회고록</h1>

      <table class="table">
          <thead class="table-head">
            <tr>
              <th scope="col" v-for="(header,idx) in headers" :key="idx"> {{ header }}</th>
            </tr>
          </thead>
          <tbody class="t-body">
            <tr v-for="(reviews, index) in reviewContents" :key="index" >
              <th scope="row" >
                <td>{{ reviews.reviewNo }}</td>
              </th>
              <th>
                <td>
                   <router-link :to="{name : 'review', params : {'reviewNo' : reviews.reviewNo}}">
                      {{reviews.reviewTitle}}
                  </router-link>
                </td>
              </th>
              <th>
                <td>{{ reviews.reviewDate}}</td>
              </th>
              <th>
                <td><p v-if="reviews.reviewType==='FAKE'">혼자</p>
                    <p v-else>단체</p>
                </td>
              </th>
            </tr>
          </tbody>
        </table>
        <div class="buttonbundle">

      <button class="btn btn-outline-primary" @click="showReviewForm = true">작성하기</button>

       <ReviewForm v-if="showReviewForm" @close="showReviewForm = false" :review="reviewContents" action="create">
        </ReviewForm>

    </div>
    <div class="layerPopup">
  <div class="spinner"></div>
</div>
  </div>
  </div>





</template>

<script>

import { mapActions, mapGetters } from 'vuex'
import Modaldday from '../components/modal-dday'
import ModalEdit from '../components/modal-ddayEdit'
import ReviewForm from '../review/components/ReviewForm'
// import ReviewNew from './review/components/ReviewNewView'
import StudyCalendar from '../StudyCalendar'
import ReviewNewView from '../review/ReviewNewView.vue'
export default {
  components:{
    StudyCalendar,
    Modaldday,
    ModalEdit,
    ReviewForm,
    // ReviewNew,
    ReviewNewView
},
  data(){
    return {
      ddays:{
        ddayTitle:'',
        ddayDate:''
      },
      showModal: false ,
      // showModalE: false,
      showReviewForm: false,
      roomName : '',
      headers: ['번호','제목', '날짜', '유형'],
      openedModal: null,


      // reviewContents:{},
      // review:{}
    }
  },
  computed:{
    ...mapGetters(['resumeContents',
    'numberOfResume',
    'reviewContents',
    'numberOfReview',
    'currentDdays',
    'restday',
    'ddaylen',
    'dday'


    ]),

  },
  methods:{
    showModalE(id) {
      this.openedModal = id
    },
    getLengthOfObject(obj){
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
    findresumes(){
      this.getResumeInfo();
    },
    addResume(){
      this.addNewResume();
    },

    getReview(){
      this.getReviewInfo()
    },

    getDday(){
      this.getDdayInfo()
    },
  },
  mounted(){
    this.findresumes();
    this.getReview();
    this.getDday();

  },

  beforeMount() {

  },



}

</script>
<style lang="scss" scoped>



</style>
<style scoped>

  @import '../main.css';

/* @import './fullcalander/main.js'; */

#dday-up{
  border : 0.3vh solid #9c7bfd;

}
.card{
  background-color: #f6f2ff;
  color: #383561;
  box-shadow: 0px 0px 7px 1px rgb(0 0 0 / 25%);
}

.card-list{
  display: flex;
  width: 900px;
  /* padding: 20px; */
  overflow: scroll;
  overflow: auto;
  white-space: nowrap;

}

.card-list::-webkit-scrollbar {
    width: 10px;
  }
.card-list::-webkit-scrollbar-thumb {
  background-color: #653FD3;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.card-list::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
#icon{
  cursor: pointer;
}
#icon:hover{
  color: #ffffff;
}
#icon:active{


}
.dday-button{
  text-align: center;
  margin: 10px;
}
.inner-dday1{
  float:left;


}
.inner-dday2{
  float:right;
  display:inline-block;

}
.card-body{
  padding: 10px;
}
.card{
  width: 190px;
  height: 100px;
  margin: 15px;


}
.conatiner{
  display: flex;
  flex-direction: row;
}
.my-top{
  display: flex;
  flex-direction: column;
  justify-content: space-between;

}
.my-left{
  display: flex;
  flex-direction: column;
}
.calendar{
  width: 1000px;
  /* height: 800px; */
  /* display: flex; */
}

#cover-router{
  text-decoration-line: none;
}
.review-1{
  /* margin-top: 100px; */
}
.review-2{
  margin-top: 100px;
}

.review-3{
  display: flex;
  flex-direction: row;
  /* margin: 30px; */
}
.conatiner{

  display: flex;
  flex-direction: column;
}
#button-review{
   width: 100px;
   height: 50px;
   background-color: #9c7bfd;
   border-color : #5b5a60;
   border-radius: 0.9vh;
   margin: 5px;
   color:#383561;

}
#button-review:hover{
   color: white;

}
#a {
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
  margin: 3px;
}
.buttonbundle{
  flex-direction: row-reverse;
  margin: 10px;
  width: 100px;

}
.table{
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

b { /* used for event dates/times */
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

.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}



.card-text-2{
  background-color: rgb(114, 209, 114);
  border-radius: 10%;
  color: white;
  padding: 5px;

}

.card-text-1{
  background-color: rgb(236, 145, 145);
  border-radius: 10%;
  color: white;
  padding: 5px;
}

#inner-table{
  border:#653FD3

}

.layerPopup {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,0.8);
    z-index: 1000;
    justify-content: center;
    align-items: center;
    margin: -30px 0 0 -30px;
}
.spinner {
    position: absolute;
    top: 50%;
    left: 50%;
    border: 8px solid #f3f3f3; /* Light grey */
    border-top: 8px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spinner 2s linear infinite;
}
@keyframes spinner {
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
}
.btn-outline-primary {
  --bs-btn-color: #9c7bfd;
  --bs-btn-border-color: #9c7bfd;
  --bs-btn-hover-color: #fff;
  --bs-btn-hover-bg: #9c7bfd;
  --bs-btn-hover-border-color: #9c7bfd;
  --bs-btn-focus-shadow-rgb: 13, 110, 253;
  --bs-btn-active-color: #fff;
  --bs-btn-active-bg: #9c7bfd;
  --bs-btn-active-border-color: #9c7bfd;
  --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
  --bs-btn-disabled-color: #9c7bfd;
  --bs-btn-disabled-bg: transparent;
  --bs-btn-disabled-border-color: #9c7bfd;
  --bs-gradient: none;
  border-width: 0.23vh;
  width: 120px;
}
</style>
