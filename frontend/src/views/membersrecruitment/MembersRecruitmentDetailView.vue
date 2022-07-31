<template>
 <h1>Detail Page</h1>

    <div class="container">
      <h2 class="py-3">
        {{ recruitDetail.recruitTitle }}
      </h2>
      <div class="d-flex justify-content-around">
        <div>
          대표 이미지 :
          <img src="{{ recruitDetail.stdImg }}" alt="">
        </div>
        <div>
          스터디 이름 :{{ recruitDetail.stdName }} <br>
          활동기간 : {{ recruitDetail.startDate }} ~  {{ recruitDetail.endDate }}<br>
          진행 일자 : {{ recruitDetail.stdDay }} <br>
          모집인원 :   {{ recruitDetail.stdLimit }}<br>
          스터디 타입 : {{ recruitDetail.stdType }} <br>
        </div>
      </div><hr>
      <div class="card border-secondary mb-3" style="max-width: 80rem; height: 20rem;">
        <div class="card-header">스터디 소개</div>
        <div class="card-body text-secondary">
          <h5 class="card-title">{{ recruitDetail.stdName }}</h5>
          <p class="card-text">{{ recruitDetail.stdDetail }}</p>
        </div>
      </div>
      기업명 : {{ recruitDetail.comName }}
      스터디 현황 : {{ recruitDetail.recruitStatus }}
      <button @click="moveToUpdate">수정</button>
      <button @click="deleteRecruitmentDetail([recruitNo])"> 삭제 </button>
      <button type="button" class="btn btn-bd-primary" style="color:indigo">Primary</button>

    {{ recruitDetail.stdDetail }} <br>
    <button @click="studyStart(recruitNo)"> 스터디 시작 </button>
    <button @click="studyApply(recruitNo)"> 스터디 신청하기</button>
    <button @click="studyApplyCancel(recruitNo)">스터디 신청 취소</button>
    <button >{{ applyState }}</button><br>
    </div>

</template>

<script>
  import router from '@/common/lib/vue-router'
  import { mapActions, mapGetters } from 'vuex'


  export default {
    name:'MembersRecruitmentDetailView',

    data(){
      return{
        recruitNo:this.$route.params.recruitNo,
        studyType : '',
        applyState : '스터디 신청하기',
      }
    },
    computed:{
      ...mapGetters(['recruitDetail','currentUser','isApplied'])

    },
    methods:{
      ...mapActions([
        'bringRecruitmentDetail',
        'updateRecruitmentDetail',
        'deleteRecruitmentDetail',
        'createStudySpace',
        'studyApply',
        'studyApplyCancel',

        ]),
      changeApplyState(){
        if(this.isApplied==true){
          this.applyState = '스터디 취소'
        }else{
          this.applyState = '스터디 신청'
        }
      },

      interviewType(){
        if(this.recruitDetail.stdType === 'COM'){
          this.studyType = '기업 면접'
        }else{
          this.studyType = '자율 면접'
        }
      },
      moveToUpdate(){
        router.push({
        name:'membersrecruitmentedit',
        params:{recruitNo:this.recruitNo}
        })
      },
      async studyStart(){
        await this.createStudySpace(this.recruitNo)
        router.push({ name:'study'})
      }
    },
    created(){
      this.interviewType()
      this.bringRecruitmentDetail(this.recruitNo)
    }

  }
</script>

<style>
  .btn-bd-primary {
    --bs-btn-font-weight: 600;
    --bs-btn-color: var(--bs-white);
    --bs-btn-bg: var(--bd-violet);
    --bs-btn-border-color: var(--bd-violet);
    --bs-btn-border-radius: .5rem;
    --bs-btn-hover-color: var(--bs-white);
    /* --bs-btn-hover-bg: #{shade-color($bd-violet, 10%)}; */
    /* --bs-btn-hover-border-color: #{shade-color($bd-violet, 10%)}; */
    --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
    --bs-btn-active-color: var(--bs-btn-hover-color);
    /* --bs-btn-active-bg: #{shade-color($bd-violet, 20%)}; */
    /* --bs-btn-active-border-color: #{shade-color($bd-violet, 20%)}; */
  }


  .recruitmentInfo{
    width: 440px;
    height: 300px;
    background: #FFFFFF;
    box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
  }
  .recruitment-intro{

    width: 841px;
    height: 287px;

    background: #FFFFFF;
    border: 1px solid #000000;
    border-radius: 30px;
  }

</style>
