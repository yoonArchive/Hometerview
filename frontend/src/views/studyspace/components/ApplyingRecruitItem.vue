<template>
  <div class="carousel-caption d-none d-md-block">
    <div class="row">
      <h5>
        <span class="comName" v-if="applyingRecruit.comName != null"
          >[{{ applyingRecruit.comName }}] </span
        ><span class="stdName">{{ applyingRecruit.stdName }}</span>
      </h5>
      <p class="stdDetail">스터디 설명 : {{ applyingRecruit.stdDetail }}</p>
      <div class="slider-btn">
        <span class="moveBtn" @click="moveToRecruitDetail()"
          >📝 모집글 상세보기</span
        >
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/common/lib/vue-router";

export default {
  name: "ApplyingRecruitItem",
  props: {
    applyingRecruit: Object
  },

  data() {
    return {};
  },
  computed: {
    // countStudyJoins(){
    //     const count = this.studyJoins.length
    //     return count
    // }
  },
  methods: {
    moveToRecruitDetail() {
      router.push({
        name: "membersrecruitmentdetail",
        params: { recruitNo: this.applyingRecruit.recruitNo }
      });
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,800,900");
.comName {
  color: #d76d77;
  font-weight: 1000;
}

.stdName {
  font-weight: 100;
}

.stdDetail {
  font-size: 18px;
}

.moveBtn:hover {
  font-weight: 700;
  color: #76bef8;
}
</style>
