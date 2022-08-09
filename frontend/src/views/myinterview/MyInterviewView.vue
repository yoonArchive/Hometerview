<template>
<div class="container">
  <div class="review-1">
    <div>자소서 개수 {{resumeContents.length}}</div>
    <button @click="addResume" id="cover-Pbutton">+</button>
    <button id="cover-Mbutton">-</button>
    <div v-for="(item, index) in resumeContents.length" :key="index">
      <router-link :to="{name : 'coverletter', params : {'resumeindex' : item - 1}}" id="cover-router">
        <div>{{resumeContents[item-1].resumeTitle}}</div>
      </router-link>
    </div>
  </div>
  <!-- <div>{{ reviewContents }}</div> -->
  <!-- <h1>{{numberOfReview}}</h1> -->

  <hr>
      <h1>DDAY 목록</h1>

    <div class="review-3 ">
        <div class="card " style="width: 15rem;" v-for="(ddays, index) in currentDdays" :key="index">
          <div class="card-body">
            <h5 class="card-title">{{ddays.ddayTitle}}</h5>
            <p>{{index}} </p>
            <p>{{ddays.ddayNo}}</p>
            <h6 class="card-subtitle mb-2 text-muted">날짜 {{ddays.ddayDate}}</h6>
            <p  v-if="(restday[index] < 0)" class="card-text-1">D-DAY {{restday[index]}} </p>
            <p  v-else class="card-text-2">D-DAY {{restday[index]}} </p>
            <button @click="showModalE(ddays.ddayNo)">수정</button>
                <!-- <ModalEdit v-if="showModalE" @close="showModalE = false" :dday="ddays" :key="ddays.ddayNo+1" >
                  <h3 slot="header">dday 수정</h3>
                </ModalEdit> -->
            <button @click="deleteDDAY(ddays.ddayNo)">삭제</button>
          </div>
        </div>
    </div>


    <ModalEdit :id="'ModalEdit '+ddays.ddayNo" v-if="openedModal !== null" @close="openedModal = null" :dday="ddays" :key="this.openedModal" >
      <h3 slot="header">dday 수정</h3>
    </ModalEdit>

  	<button id="button-review" @click="showModal = true"><p id="a">DDAY 작성하기</p></button>

     <Modaldday v-if="showModal" @close="showModal = false" :dday="currentDdays">

       <h3 slot="header">dday 생성</h3>
     </Modaldday>
  </div>
  <hr>
    <DemoCalander :reviews="reviewContents" :dday="currentDdays"></DemoCalander>

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

      <button id="button-review" @click="showReviewForm = true">작성하기</button>

       <ReviewForm v-if="showReviewForm" @close="showReviewForm = false" :review="reviewContents" action="create">
        <h3 slot="header">회고록작성 폼</h3>
        </ReviewForm>
    <!-- <ReviewNewView ></ReviewNewView> -->

  </div>
</div>
</template>

<script>
const today = new Date()

import { mapActions, mapGetters } from 'vuex'
import Modaldday from './components/modal-dday'
import ModalEdit from './components/modal-ddayEdit'
import ReviewForm from './review/components/ReviewForm'
// import ReviewNew from './review/components/ReviewNewView'
import DemoCalander from './DemoApp'
import ReviewNewView from './review/ReviewNewView.vue'
export default {
  components:{
    DemoCalander,
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
      Editsum: [false*this.ddaylen],
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
    'ddaylen'


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
      "deleteDDAY"

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
    console.log('김'+this.Editsum)

  },

  beforeMount() {
    // this.getReview();
    // this.getDday();
  },



}
</script>

<style scoped>

  @import './main.css';
  /* @import './fullcalander/main.js'; */

#cover-router{
  text-decoration-line: none;
}
.review-1{
  margin-top: 100px;
}
.review-2{
  margin-top: 100px;
}
.conatiner{

  display: flex;
  flex-direction: column;
}
#button-review{
   width: 100px;
   height: 50px;
   background-color: #653FD3;
   border-color : #653FD3;
   border-radius: 10%;
   margin: 5px;

}
#button-review:hover{

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

.review-3{
  display: flex;
  flex-direction: row;
  margin: 30px;
}
.card{
  margin: 20px;
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
</style>
