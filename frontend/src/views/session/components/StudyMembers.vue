<template>
  <div>
    <div
      class="d-flex justify-content-between"
      style="margin-top:2.5vh; margin-bottom:2.5vh;"
    >
      <div class="memeber">
        <img
          class="profile-photo"
          :src="profileImg"
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
      </div>
    </div>
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
      },
      profileImg: ""
    };
  },
  mounted() {
    this.profileImg = this.member.user.userImg;
    if (this.profileImg == null) {
      this.profileImg = require(`@/assets/images/session/profile.png`);
    }
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
  width: 51.72px;
  height: 51.72px;
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
