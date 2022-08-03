<template>
    <div class="container">
    <h4 class="py-2" style="color: #653FD3;">스터디 그룹을 만들어 보세요!</h4>
    <form @submit.prevent="submitType(action)">
      <div class="py-2">
        <label class="font-color" for="recruitTitle">모집글 제목 : </label>
        <input class="box1" v-model="newrecruitmentInfo.recruitTitle" type="text" id="recruitTitle" />
      </div>
      <div class="py">
        <span class="font-color"> 카테고리 : </span>
        <label for="stdType">기업 면접</label>
        <input v-model="newrecruitmentInfo.stdType" name="stdType" type="radio" id="stdType" value="COM" @click="isCompany('COM')" checked/>

        <label for="stdType">자율 면접</label>
        <input v-model="newrecruitmentInfo.stdType" name="stdType" type="radio" id="stdType" value="FREE" @click="isCompany('FREE')"/>
      </div>
      <div class="py-2">
        <label class="font-color" for="comName">기업명 : </label>
        <input class="box1" v-model="newrecruitmentInfo.comName" type="text" id="comName" :disabled="company"/>
      </div>
      <div class="py-2">
        <label class="font-color" for="startDate"> 시작일 :
          <input v-model="newrecruitmentInfo.startDate" type="date" id="Date" />
        </label>
        <label class="font-color" for="endDate">종료일 :
          <input v-model="newrecruitmentInfo.endDate" type="date" id="Date">
        </label>
      </div>

      <div class="py-2">
        <label class="font-color" for="stdDay">진행 일시 : </label>
        <input class="box1" v-model="newrecruitmentInfo.stdDay" type="text" id="stdDay" placeholder="예) 매주 월, 수, 금  오후 10시 ( 협의가능 )" />
      </div>

      <div class="py-2">
        <label class="font-color" for="stdLimit"> 모집 인원 : </label>
        <input v-model="newrecruitmentInfo.stdLimit" type="number" id="stdLimit">
      </div>

      <div class="py-2">
        <label class="font-color" for="stdName">스터디 이름 : </label>
        <input class="box1" v-model="newrecruitmentInfo.stdName" type="text" id="stdName">
      </div>

        <div class="py-2">
          <label class="font-color" for="stdDetail"> 스터디 설명 : </label>
          <input class="box2" v-model="newrecruitmentInfo.stdDetail" type="text" id="stdDetail">
      </div>

      <div>
        이미지 :
        <label for="stdImg">
           <button id="file-button">파일</button>
          <input multiple @change="onInputimage" ref="studyImage" type="file" id="stdImg" style="display:none">
          <input v-model="newrecruitmentInfo.stdImg" type="image" id="stdImg" >
        </label>

      </div>
      <div>
      <button id="b-button">{{ action }}</button>
      </div>
    </form>
    <!-- <button @click="deleteRecruitmentDetail(recruitNo)">삭제 </button> -->
  </div>
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
          stdType: this.recruitDetail.stdType || "COM"
        }
      }
    },
    computed:{

    },
    methods:{
      ...mapActions(['createRecruitment','updateRecruitmentDetail','deleteRecruitmentDetail' ]),
      onInputimage(){
        this.newrecruitmentInfo.stdImg = this.$refs.studyImage.files[0]['name']
      },
      isCompany(type){
        if(type==="COM"){
          this.company = false
        }else{
          this.company = true
        }
      },
      submitType(action){
        if(action==='만들기'){
          console.log(action)
          this.createRecruitment(this.newrecruitmentInfo)
        }
        else if (action==='수정하기'){
          this.updateRecruitmentDetail([this.recruitNo,this.newrecruitmentInfo])
        }
      }

    }

  }
</script>

<style scoped>
#kim{
  background-color: #653FD3;
}
#file-button{
  background-color: #5cb85c;
  border:0;
  outline: 0;
  border-radius: 10%;
  color: white;
  height: 30px;
  margin-bottom: 10px;
}

#file-button:hover {
  background-color: #6ed36e;
}

#file-button:active {
  background-color: #367c36;
}


#b-button{
  margin-top: 10px;
  border: #653FD3;
  border-radius: 10%;
  background-color: #653FD3;
  color: white;
}

#b-button:hover{

    background-color: #9779f0;

  }
#b-button:active{
    background-color: #7455d1;
  }
.container{
  border: #653FD3;
  border-radius: 10%;
  display: flex;
  flex-direction: column;
}
  .create-form{
    width: 550px;
    height: 1000px;

    background: #F3F4FF;
  }
  .font-color{
    width: 130px;
    height: 24px;
    font-family: 'ABeeZee';
    font-style: normal;
    font-weight: 1000;
    font-size: 20px;
    line-height: 150%;
    color: #653FD3;
  }
  .box1{
    width: 381px;
    height: 38px;

    /* Gray/White */

    background: #FFFFFF;
    /* Gray/400 */

    border: 1px solid #CED4DA;
    border-radius: 4px;
  }
  .box2{
    width: 381px;
    height: 300px;

    /* Gray/White */

    background: #FFFFFF;
    /* Gray/400 */

    border: 1px solid #CED4DA;
    border-radius: 4px;
  }
  #stdLimit{
    width: 50px;
    border: 1px solid #CED4DA;
    border-radius: 4px;
  }

  #Date{
    width: 100px;
    margin-right: 10px;
    background-color: #653FD3;
    color: #CED4DA;
    border-radius: 10%;
    font-size: 15px;

    border:0;
  outline: 0;
  }
#Date:hover{

    background-color: #9779f0;

  }
#Date:active{
    background-color: #7455d1;
  }
  #endDate{
    width: 100px;
    margin-right: 0px;
  }


</style>
