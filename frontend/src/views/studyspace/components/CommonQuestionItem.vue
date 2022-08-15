<template>
  <section class="comments">
    <article class="comment">
      <a class="comment-img" href="#non">
        <img :src="currentUser.userImg" :alt="profile" width="50" height="50" />
        <p class="attribution" v-if="name">by {{ name }}</p>
        <p class="attribution" v-else>
          by 나간 회원입니다.
        </p>
      </a>
      <!-- {{ currentUser.Img }} -->

      <div class="comment-body">
        <div class="text">
          <div v-if="commonquestion.questionType == 'JOB'">
            {{ commonquestion.questionNo }}. 직무 면접
            <span v-if="currentUser.userId === userId && !isEditing">
              <!-- <button @click="switchIsEditing">Edit</button> | -->
              <span id="icon" @click="switchIsEditing">
                <font-awesome-icon icon="fa-solid fa-pen-to-square" /> </span
              >|
              <!-- <button
            @click="deletecommonQuestion([stdNo, commonquestion.questionNo])"
          >
            Delete
          </button> -->

              <span
                id="icon"
                @click="
                  deletecommonQuestion([stdNo, commonquestion.questionNo])
                "
              >
                <font-awesome-icon icon="fa-solid fa-trash-can" />
              </span>
            </span>
            <!-- <p class="attribution" v-if="name">by {{ name }}</p>
            <p class="attribution" v-else>
              by 나간 회원입니다.
            </p>
          </div>
          <div v-else>
            {{ commonquestion.questionNo }}. 인성 면접
            <p class="attribution" v-if="name">by {{ name }}</p>
            <p class="attribution" v-else>
              by 나간 회원입니다.
            </p> -->
          </div>
          <p>{{ commonquestion.contents }}</p>
        </div>
        <span v-if="isEditing">
          <input
            id="aa"
            type="text"
            v-model="payload.contents"
            class="form-control"
          />
          <!-- <button @click="onUpdate">Update</button> -->
          <span id="icon" @click="onUpdate">
            <font-awesome-icon icon="fa-solid fa-pen-to-square" /> </span
          >|
          <!-- <button @click="switchIsEditing">Cancel</button> -->

          <span id="icon" @click="switchIsEditing">
            <font-awesome-icon icon="fa-solid fa-xmark" />
          </span>
          <div>
            <input
              type="radio"
              name="reviewType"
              value="JOB"
              v-model="payload.questionType"
            />
            <label for="questionType">직무 면접</label>
            <input
              type="radio"
              name="reviewType"
              value="FREE"
              v-model="payload.questionType"
            />
            <label for="questionType">인성 면접</label>
          </div>
        </span>
      </div>
    </article>
  </section>
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
      },
      // questionNo: this.commonquestion.questionNo,,
      name: "",
      userImag: "",
      userId: "",
      profile: require("../../../assets/images/profile.png")
    };
  },
  computed: {
    ...mapGetters([
      "authHeader",
      "studentIndex",
      "studySpaceDetail",
      "currentUser",
      "commonQuestions"
    ])
  },
  methods: {
    ...mapActions([
      "updatecommonQuestion",
      "deletecommonQuestion",
      "bringStudySpaceDetail"
    ]),
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
    },
    checkUser() {
      for (const study of this.studySpaceDetail.studyJoins) {
        if (study.user.userNo === this.commonquestion.writerNo) {
          this.name = study.user.userName;
          this.userImag = study.user.userImg;
          this.userId = study.user.userId;
          console.log(study);
        }
      }
    }
  },
  mounted() {
    this.checkUser();
  },

  created() {
    this.bringStudySpaceDetail(this.stdNo);
    this.checkUser();
  }
};
</script>

<style scoped>
#icon {
  cursor: pointer;
}
#icon:hover {
  color: #653fd3;
}
body {
  width: 500px;
  margin: 20px auto;
  font: 16px/1.4 Arial, sans-serif;
  background: #f3eee9;
}

p {
  margin: 0 0 1em;
}

.comment {
  overflow: hidden;
  padding: 0 0 1em;
  border-bottom: 1px solid #ddd;
  margin: 0 0 1em;
  *zoom: 1;
}

.comment-img {
  float: left;
  margin-right: 33px;
  border-radius: 5px;
  overflow: hidden;
}

.comment-img img {
  display: block;
}

.comment-body {
  overflow: hidden;
}

.comment .text {
  padding: 10px;
  border: 1px solid #e5e5e5;
  border-radius: 5px;
  background: #fff;
}

.comment .text p:last-child {
  margin: 0;
}

.comment .attribution {
  margin: 0.5em 0 0;
  font-size: 14px;
  color: #666;
  text-decoration-line: none;
}

/* Decoration */

.comments,
.comment {
  position: relative;
}

.comments:before,
.comment:before,
.comment .text:before {
  content: "";
  position: absolute;
  top: 0;
  left: 65px;
}

.comments:before {
  width: 3px;
  top: -20px;
  bottom: -20px;
  background: rgba(0, 0, 0, 0.1);
}

.comment:before {
  width: 9px;
  height: 9px;
  border: 3px solid #fff;
  border-radius: 100px;
  margin: 16px 0 0 -6px;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2), inset 0 1px 1px rgba(0, 0, 0, 0.1);
  background: #ccc;
}

.comment:hover:before {
  background: orange;
}
.aa {
  border-color: #653fd3;
  background-color: #653fd3;
  margin: 10px;
}
.form-control {
  width: 50%;
}
.text {
  overflow: auto;
  /* overflow-wrap: normal; */
  /* overflow: auto; */
  /* width: 500px; */
  white-space: pre-wrap;
  height: 100%;

  /* height: 150px; */
}
.text::-webkit-scrollbar {
  width: 10px;
}
.text::-webkit-scrollbar-thumb {
  background-color: #653fd3;
  border-radius: 10px;
  background-clip: padding-box;
  border: 1px solid transparent;
}
.text::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
</style>
