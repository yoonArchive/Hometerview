<template>
  
  <form @submit.prevent="submitType(action)">
    <div>
      <label for="recruitTitle">모집글 제목 : </label>
      <input v-model="newrecruitmentInfo.recruitTitle" type="text" id="recruitTitle" />
    </div>
    <div>
      카테고리 :
      <label for="stdType">기업 면접</label>
      <input v-model="newrecruitmentInfo.stdType" name="stdType" type="radio" id="stdType" value="COM" @click="isCompany('COM')" checked/>
      <label for="stdType">자율 면접</label>
      <input v-model="newrecruitmentInfo.stdType" name="stdType" type="radio" id="stdType" value="FREE" @click="isCompany('FREE')"/>
    </div>
    <div>
      <label for="comName">기업명 : </label>
      <input v-model="newrecruitmentInfo.comName" type="text" id="comName" :disabled="company"/>
    </div>
    <div>
      <label for="startDate"> 시작일 : </label>
      <input v-model="newrecruitmentInfo.startDate" type="date" id="startDate" />
      <label for="endDate">종료일 : </label>
      <input v-model="newrecruitmentInfo.endDate" type="date" id="endDate">
    </div>
    <div>
      <label for="stdDay">진행 일시 : </label>
      <input v-model="newrecruitmentInfo.stdDay" type="text" id="stdDay" placeholder="예) 매주 월, 수, 금  오후 10시 ( 협의가능 )" />
    </div>
    <div>
      <label for="stdLimit"> 모집 인원 : </label>
      <input v-model="newrecruitmentInfo.stdLimit" type="number" id="stdLimit">
    </div>
    <div>
      <label for="stdName">스터디 이름 : </label>
      <input v-model="newrecruitmentInfo.stdName" type="text" id="stdName">
    </div>
    <div>
      <label for="stdDetail"> 스터디 설명 : </label>
      <input v-model="newrecruitmentInfo.stdDetail" type="text" id="stdDetail">
    </div>
    <div>
      <label for="stdImg">이미지 : </label>
      <input multiple @change="onInputimage" ref="studyImage" type="file">
      <input v-model="newrecruitmentInfo.stdImg" type="image" id="stdImg">
    </div>
    <button>{{ action }}</button>
  </form>
    <!-- <button @click="deleteRecruitmentDetail(recruitNo)">삭제 </button> -->
  
</template>

<script>
  import { mapActions } from 'vuex'

  export default {
    name: 'CreateMembersRecruitmentForm',

    props:{
      recruitDetail:Object,
      action:String,
      recruitNo:String,

    },

    data(){
      return{

        company : false,

        newrecruitmentInfo:{
          comName: this.recruitDetail.comName,
          endDate: this.recruitDetail.endDate,
          recruitTitle: this.recruitDetail.recruitTitle,
          startDate: this.recruitDetail.startDate,
          stdDay: this.recruitDetail.stdDay,
          stdDetail: this.recruitDetail.stdDetail,
          stdImg: this.recruitDetail.stdImg,
          stdLimit: this.recruitDetail.stdLimit,
          stdName: this.recruitDetail.stdName,
          stdType: this.recruitDetail.stdType
        }
      }
    },
    computed:{

    },
    methods:{
      ...mapActions(['createRecruitment','updateRecruitmentDetail','deleteRecruitmentDetail' ]),
      onInputimage(){
        this.recruitmentInfo.stdImg = this.$refs.studyImage.files[0]['name']
      },
      isCompany(type){
        if(type==="COM"){
          this.company = false
        }else{
          this.company = true
        }
      },
      submitType(action){
        if(action==='create'){
          console.log(action)
          this.createRecruitment(this.recruitmentInfo)
        }
        else if (action==='update'){
          this.updateRecruitmentDetail([this.recruitNo,this.newrecruitmentInfo])
        }
      }

    }

  }
</script>

<style>

</style>