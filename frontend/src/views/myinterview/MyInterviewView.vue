<template>
  <div>자소서 추가 삭제 버튼</div>
  <button @click="addResume">+</button>
  <button>-</button>
  <div v-for="(item, index) in numberOfResume" :key="index">
    <router-link :to="{name : 'coverletter', params : {'resumeindex' : item - 1}}">
      <div>{{resumeContents[item-1].resumeTitle}}</div>
    </router-link>
  </div>

  <!-- <div>{{ reviewContents }}</div> -->
  <!-- <h1>{{numberOfReview}}</h1> -->
  <div class="review">
    <h1>회고록</h1>
  </div>
      <table class="table table-hover">
          <thead class="table-head">
            <tr>
              <th scope="col" v-for="(header,idx) in headers" :key="idx"> {{ header }}</th>
            </tr>
          </thead>
          <tbody>
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
      <button id="button-review">
        <router-link class="routerlink" :to="{ name: 'reviewNew' }">
          <p id="a">새로 만들기</p>
        </router-link>
      </button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'


export default {
  data(){
    return {
      roomName : '',
      headers: ['번호','제목', '작성시간', '유형']
    }
  },
  computed:{
    ...mapGetters(['resumeContents','numberOfResume', 'reviewContents','numberOfReview']),
  },
  methods:{
    ...mapActions(["getResumeInfo","getReviewInfo"]),
    findresumes(){
      this.getResumeInfo();
    },
    addResume(){
    },

    findreveiw(){
      this.getReviewInfo();
    },
    changeName(){
    }
  },
  created(){
    this.findresumes();
    this.findreveiw();
  }
}
</script>

<style>
#button-review{
   width: 100px;
   height: 50px;
   background-color: #653FD3;
   border-color : #653FD3;
   border-radius: 10%;
   margin: 5px;

}
#a {
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
}
.buttonbundle{
  flex-direction: row-reverse;
  margin: 10px;
  width: 100px;

}
</style>
