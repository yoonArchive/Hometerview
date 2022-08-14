<template>
  <tr>
    <td style="width:30px"></td>
    <td style="font-weight:700">
      {{ member.user.userName
      }}<span v-if="member.joinType === 'LEADER'" style="font-size:17px"
        >&nbsp;👑</span
      >
    </td>
    <td>{{ member.user.userEmail }}</td>
    <td style="width:100px"></td>
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
        <i class="input-icon uil uil-ban"></i>
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
td {
  font-size: 23px;
}
tr:hover {
  background-color: rgba(161, 104, 253, 0.05) !important;
}
</style>
