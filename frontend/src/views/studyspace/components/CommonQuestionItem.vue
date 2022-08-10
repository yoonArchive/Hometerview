<template>
  <li class="commonquestion-list-item">
    <div class="commonquestion-content">
      <div class="commonquestion-user">
        <!-- <router-link :to="{ name: 'profile', params: { username: commonquestion.user.username } }">
          {{ commonquestion.user.username }}
        </router-link> 님 : -->
        {{ commonquestion.questionNo }}
      </div>
      <div class="commonquestion-time">
        <!-- <div class="commonquestion-create">
          작성: {{timeForToday(commonquestion.created_at)}}
        </div>
        <div class="commonquestion-update">
          수정: {{timeForToday(commonquestion.updated_at)}}
        </div> -->
      </div>

      {{ commonquestion.contents }}
    </div>
    <div class="commonquestion-mid">
      <!-- <div v-if="!isEditing">{{ payload.content }}</div> -->
      {{ commonquestion.questionType }}
    </div>
    <div class="commonquestion-bottom">
      <div class="commonquestion-button">
        <span v-if="isEditing">
          <input type="text" v-model="payload.contents" />
          <button @click="onUpdate">Update</button> |
          <button @click="switchIsEditing">Cancel</button>
          <div>
            <input
              type="radio"
              name="reviewType"
              value="JOB"
              v-model="payload.questionType"
            />
            <label for="questionType">공통질문</label>
            <input
              type="radio"
              name="reviewType"
              value="FREE"
              v-model="payload.questionType"
            />
            <label for="questionType">자율질문</label>
          </div>
        </span>

        <span
          v-if="
            commonquestion.writerNo === commonquestion.writerNo && !isEditing
          "
        >
          <button @click="switchIsEditing">Edit</button> |
          <button
            @click="deletecommonQuestion([stdNo, commonquestion.questionNo])"
          >
            Delete
          </button>
        </span>
      </div>
    </div>
  </li>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "commonquestionListItem",
  props: {
    commonquestion: Object,
    stdNo: Number,
    questionNo: Number
  },
  data() {
    return {
      isEditing: false,
      // stdNo: this.$route.params.stdNo,
      payload: {
        // questionNo: this.commonquestion.questionNo,
        contents: this.commonquestion.contents,
        questionType: this.commonquestion.questionType
      }
      // questionNo: this.commonquestion.questionNo,
    };
  },
  computed: {
    ...mapGetters(["authHeader"])
  },
  methods: {
    ...mapActions(["updatecommonQuestion", "deletecommonQuestion"]),
    switchIsEditing() {
      this.isEditing = !this.isEditing;
    },
    onUpdate() {
      this.updatecommonQuestion([
        this.stdNo,
        this.payload,
        this.commonquestion.questionNo
      ]);
      this.isEditing = false;
    }
  }
};
</script>

<style scoped></style>
