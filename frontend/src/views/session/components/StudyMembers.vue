<template>
  <div>
    <div
      class="d-flex justify-content-between"
      style="margin-top:2.5vh; margin-bottom:2.5vh;"
    >
      <div class="memeber">
        <img
          class="profile-photo"
          :src="require(`@/assets/images/session/profile.png`)"
          style="box-shadow: 0px 0px 7px 1px rgba(0, 0, 0, 0.6);"
        />&nbsp;&nbsp;&nbsp; {{ member.user.userName }}
      </div>
      <div>
        <img
          class="resume"
          :src="require(`@/assets/images/session/resumeOff.png`)"
          style="box-shadow: 0px 0px 7px 1px rgba(0, 0, 0, 0.4); "
          @click="changeToCoverLetter(['coverletter', studentindex])"
        />
        <!-- <button @click="changeToCoverLetter(['coverletter', studentindex])">
          자소서 보기
        </button> -->
      </div>
    </div>

    <!-- 여기에 leader일때만 인터뷰어 지정 체크 버튼 생성 -->
    <!-- 면접관이 지정 되었을 경우 메인 화면으로 그 사람이 메인 화면으로 바뀌게 만들어야 함 -->
    <!-- 메인 스트림으로 띄울수 있게 만들면 될 것 같음 -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import MemberCoverLetter from "./MemberCoverLetter.vue";
export default {
  name: "StudyMembers",
  components: {
    MemberCoverLetter
  },

  props: {
    member: Object,
    studentindex: Number,
    userId: String
  },
  data() {
    return {
      stdMemberInfo: {
        stdNo: this.$route.params.stdNo,
        userNo: this.member.user.userNo
      }
    };
  },
  computed: {
    ...mapGetters(["resumeQuestionList"])
  },
  methods: {
    ...mapActions(["getStudyResume", "changeToCoverLetter"])
  }
};
</script>

<style scoped>
.profile-photo {
  border-radius: 100%;
  background-color: #c4c1c9;
  margin-left: 4vh;
  height: 6vh;
}
.resume {
  margin-right: 4.5vh;
  margin-top: 3.5vh;
  border-radius: 1vh;
  height: 5.5vh;
}
.memeber {
  margin-top: 3vh;
  margin-bottom: 3vh;
  font-weight: 600;
  color: #39344a;
}
</style>
