<template>


  <div class="container">
    <div class="title">
       모집글 작성
    </div>
    <div class="row">
      <div class="create-form col">
        <create-members-recruitment-form :recruitDetail="recruitDetail" action='만들기'></create-members-recruitment-form>
      </div>
      <div class="generate-info col">
        <h3>General Information</h3>
        <div class="sub-title py-3">
          자율 면접과 기업 면접의 차이가 무엇인가요?
        </div>
        <div class="content pt-4">
          자율면접은 주제가 정해지지 않은 면접 스터디로 인성 면접이나 특정 산업의 공통된 질문 등을 스터디 하기 좋습니다.
          기업 면접은 지원하는 기업면접을 주제로 하여, 같은 기업을 준비하는 지원자분들이 모여 스터디를 하기 좋습니다.
        </div>
        <div class="sub-title py-3">
          활동 기간은 무엇인가요?
        </div>
        <div class="content pt-4">
          활동 기간은 시작일로부터 종료일까지 화상 스터디방이 유지되며, 면접 기간이 끝나면 자연스럽게 면접스터디 방이 사라질 수 있도록 하였습니다.
        </div>
        <div class="sub-title py-3">
          진행일시는 무엇인가요?
        </div>
        <div class="content pt-4">
          진행일시는 일주일에 스터디를 진행하는 요일 및 횟수를 기제할 수 있는 공간입니다. 메인페이지에서 확인이 가능하여 시간이 맞는 사람들과 스터디 할 수 있습니다.
        </div>
      </div>
    </div>
  </div>


</template>

<script>
  import CreateMembersRecruitmentForm from './components/CreateMembersRecruitmentForm.vue'
  import { mapGetters } from 'vuex'

  export default {
    name : 'CreateMembersRecruitmentView',
    components :{
      CreateMembersRecruitmentForm,
    },
    computed:{
      ...mapGetters(['recruitDetail'])
    }

  }
</script>

<style scoped>
.inner-con{
  display: flex;
  flex-direction: row;
}
.container{
  display: flex;
  flex-direction: column;
}
.title{
  background-color: #F3F4FF;
  margin-bottom: 100px;
  font-size: 80px;
  width: 100%;
  padding: 20px;
  font-weight: bold;

}
  .create-form{
    width: 550px;
    height: 1000px;
    margin: 20px;
    background: #F3F4FF;
  }
  .generate-info{
    width: 550px;
    height: 1000px;
    background: white;
  }
  .sub-title{

    width: 550px;
    height: 21px;

    font-family: 'Readex Pro';
    font-style: normal;
    font-weight: 900;
    font-size: 24px;
    line-height: 140%;
    /* identical to box height, or 21px */

    color: #653FD3;
  }
  .content{
    width: 550px;
    height: 130px;

    font-family: 'Readex Pro';
    font-style: normal;
    font-weight: 600;
    font-size: 18px;
    line-height: 140%;
    /* or 21px */
    color: #B4AEE8;

  }
</style>
