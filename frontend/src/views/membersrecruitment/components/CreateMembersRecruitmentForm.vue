<template>
  
  <form @submit.prevent="createRecruitment(recruitmentInfo)">
    <div>
      <label for="recruitTitle">모집글 제목 : </label>
      <input v-model="recruitmentInfo.recruitTitle" type="text" id="recruitTitle" />
    </div>
    <div>
      카테고리 :
      <label for="stdType">기업 면접</label>
      <input v-model="recruitmentInfo.stdType" type="radio" id="stdType" value="COM" @click="isCompany('COM')"/>
      <label for="stdType">자율 면접</label>
      <input v-model="recruitmentInfo.stdType" type="radio" id="stdType" value="FREE" @click="isCompany('FREE')"/>
    </div>
    <div>
      <label for="comName">기업명 : </label>
      <input v-model="recruitmentInfo.comName" type="text" id="comName"/>
    </div>
    <div>
      <label for="startDate"> 시작일 : </label>
      <input v-model="recruitmentInfo.startDate" type="date" id="startDate" />
      <label for="endDate">종료일 : </label>
      <input v-model="recruitmentInfo.endDate" type="date" id="endDate">
    </div>
    <div>
      <label for="stdDay">진행 일시 : </label>
      <input v-model="recruitmentInfo.stdDay" type="text" id="stdDay" placeholder="예) 매주 월, 수, 금  오후 10시 ( 협의가능 )" />
    </div>
    <div>
      <label for="stdLimit"> 모집 인원 : </label>
      <input v-model="recruitmentInfo.stdLimit" type="number" id="stdLimit">
    </div>
    <div>
      <label for="stdName">스터디 이름 : </label>
      <input v-model="recruitmentInfo.stdName" type="text" id="stdName">
    </div>
    <div>
      <label for="stdDetail"> 스터디 설명 : </label>
      <input v-model="recruitmentInfo.stdDetail" type="text" id="stdDetail">
    </div>
    <div>
      <label for="stdImg">이미지 : </label>
      <input multiple @change="onInputimage" ref="studyImage" type="file">
      <input v-model="recruitmentInfo.stdImg" type="image" id="stdImg">
    </div>
    <button>button</button>
  </form>

  
</template>

<script>
  import { mapActions } from 'vuex'

  export default {
    name: 'CreateMembersRecruitmentForm',

    data(){
      return{
        company : true,
        recruitmentInfo:{
          comName: "",
          endDate: "",
          recruitTitle: "",
          startDate: "",
          stdDay: "",
          stdDetail: "",
          stdImg: "",
          stdLimit: 0,
          stdName: "",
          stdType: ""
        }
      }
    },
    computed:{

    },

    methods:{
      ...mapActions(['createRecruitment']),
      onInputimage(){
        this.recruitmentInfo.stdImg = this.$refs.studyImage.files[0]['name']
      },
      isCompany(type){
        if(type==="COM"){
          this.company = true
          // $("#comName").attr("disabled",true)
          console.log(this.company)
        }else{
          this.company = false
          // $("#comName").attr("disabled",false)
          console.log(this.company)
        }
      }

    }

  }
</script>

<style>

</style>