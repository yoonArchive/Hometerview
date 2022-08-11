<template>
  <tr>
    <td>{{ member.user.userId }}</td>
    <td>{{ member.user.userName }}</td>
    <td>{{ member.user.userEmail }}</td>
    <td>
      <router-link
        :to="{
          name: 'membercoverletter',
          params: { studentindex: studentindex }
        }"
      >
        <i class="input-icon uil uil-file-alt"></i>
      </router-link>
    </td>
    <td>
      <button
        @click="kickMember(stdMemberInfo)"
        v-if="
          studySpaceDetail.joinType === 'LEADER' && member.joinType !== 'LEADER'
        "
      >
        추방하기
      </button>
    </td>
  </tr>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "StudyMembers",
  props: {
    member: Object,
    studentindex: Number
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
    ...mapGetters(["studySpaceDetail"])
  },
  methods: {
    ...mapActions(["kickMember"])
  }
};
</script>

<style scoped>
/* .study-member-wrapper {
  padding: 5%;
}
.member {
  box-sizing: border-box;
  width: 100%;
  background: #fcfcfc;
  border: 1px solid #653fd3;
  border-radius: 30px;
} */
</style>
