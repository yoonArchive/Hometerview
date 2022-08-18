<template>
  <div style="margin: 1.5vh; font-size: 2vh; font-weight: 500;">
    아이콘을 눌러 팀원의 자기소개서를 확인해보세요
  </div>
  <!-- 자소서 보기 -->
  <div v-if="coverLetter" class="cover-letter-list">
    <div
      class="cover-letter-exit-button"
      @click="changeToCoverLetter(['memberlist', null])"
    ></div>
    <member-cover-letter :studentindex="studentIndex"></member-cover-letter>
  </div>
  <!-- this.studySpaceDetail.joinType.user.userId -->
  <!-- 리스트 보기 -->
  <div v-if="memberList" class="study-member-list">
    <study-members
      v-for="(member, index) in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
      :studentindex="index"
    ></study-members>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import StudyMembers from "./StudyMembers.vue";
import MemberCoverLetter from "./MemberCoverLetter.vue";

export default {
  name: "StudyMemberList",
  components: {
    StudyMembers,
    MemberCoverLetter
  },
  data() {
    return {
      sessionNo: this.$route.params.sessionNo
    };
  },
  computed: {
    ...mapGetters([
      "studySpaceDetail",
      "studentIndex",
      "coverLetter",
      "memberList"
    ])
  },
  methods: {
    ...mapActions(["changeToCoverLetter"])
  }
  // created() {
  //   this.bringStudySpaceDetail(this.sessionNo);
  // }
};
</script>

<style scoped>
.cover-letter-exit-button {
  position: relative;
  float: right;
  display: inline-block;
  *display: inline;
  width: 25px;
  height: 25px;
  text-align: center;
  margin-right: 20px;
}
.cover-letter-exit-button:after {
  content: "\00d7";
  font-size: 25pt;
  line-height: 45px;
}
.cover-letter-list {
  height: 80vh;
}
.study-member-list {
  height: 80vh;
}
</style>
