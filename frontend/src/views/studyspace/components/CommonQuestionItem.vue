<template>
  <li class="commonquestion-list-item">
    <div class="commonquestion-content">
      <div class="commonquestion-user">
        <!-- <router-link :to="{ name: 'profile', params: { username: commonquestion.user.username } }">
          {{ commonquestion.user.username }}
        </router-link> 님 : -->
        {{commonquestion.questionNo}}
      </div>
      <div class="commonquestion-time">
        <!-- <div class="commonquestion-create">
          작성: {{timeForToday(commonquestion.created_at)}}
        </div>
        <div class="commonquestion-update">
          수정: {{timeForToday(commonquestion.updated_at)}}
        </div> -->
      </div>
      {{commonquestion.contents}}
    </div>
    <div class="commonquestion-mid">
      <!-- <div v-if="!isEditing">{{ payload.content }}</div> -->
    </div>
    <div class="commonquestion-bottom">
      <div class="commonquestion-button">
        <!-- <span v-if="isEditing">
          <input type="text" v-model="payload.content">
          <button @click="onUpdate">Update</button> |
          <button @click="switchIsEditing">Cancel</button>
        </span> -->
<!--
        <span v-if="currentUser.username === commonquestion.user.username && !isEditing">
          <button @click="switchIsEditing">Edit</button> |
          <button @click="deletecommonquestion(payload)">Delete</button>
        </span> -->
      </div>
    </div>
  </li>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'commonquestionListItem',
  props: { commonquestion: Object },
  data() {
    return {
      isEditing: false,
      payload: {
        stdNo: this.$route.params.stdNo,
        questionNo: this.commonquestion.questionNo,
        contents: this.commonquestion.contents
      },
    }
  },
  computed: {
    ...mapGetters(['currentUser']),
  },
  methods: {
    ...mapActions(['updatecommonquestion', 'deletecommonquestion']),
    switchIsEditing() {
      this.isEditing = !this.isEditing
    },
    onUpdate() {
      this.updatecommonquestion(this.payload)
      this.isEditing = false
    },

    timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금 전';
        if (betweenTime < 60) {
            return `${betweenTime}분 전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간 전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일 전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년 전`;
 }
  },

}
</script>

<style scoped>


</style>
