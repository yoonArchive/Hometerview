<template>


  <div class="card" style="width: 18rem;">
    <div class="card-body">
      <h5 class="card-title">
        {{ commonquestion.contents }}
      </h5>
      <p class="card-text">

        <div v-if="commonquestion.questionType=='JOB'">
         {{ commonquestion.questionNo }}. 기업 면접
        </div>
        <div v-else>
         {{ commonquestion.questionNo }}. 자율 면접
        </div>
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
      </p>
    </div>
  </div>
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
