<template>
  <section class="comments">
    <article class="comment">
      <div class="comment-img" href="#non">
        <img
          class="profile-img"
          :src="userImg"
          alt="유저이미지"
          width="50"
          height="50"
        />
        <p class="attribution" v-if="name">by {{ name }}</p>
        <p class="attribution" v-else>
          탈퇴 회원
        </p>
      </div>
      <div class="comment-body">
        <div class="text">
          <div>
          <p v-if="commonquestion.questionType == 'JOB'">
            <p id="a1">[직무]</p> {{ commonquestion.contents }}
          </p>
          <p v-else><p id="a2">[인성]</p> {{ commonquestion.contents }}</p>
          </div>
          <div>
          <span v-if="currentUser.userId === userId && !isEditing" id="incons">
            <span id="icon" @click="switchIsEditing">
              <font-awesome-icon icon="fa-solid fa-pen-to-square" />
              </span
            >|
            <span
              id="icon"
              @click="deletecommonQuestion([stdNo, commonquestion.questionNo])"
            >
              <font-awesome-icon icon="fa-solid fa-trash-can" />
            </span>
          </span>
          </div>
        </div>
          <span v-if="isEditing" id="edit">
        <div class="edit">
          <div>
          <input
            id="aa"
            type="text"
            v-model="payload.contents"
            class="form-control"
          />
        </div>
          <!-- <div id="edit-bottom"> -->
            <div>
            <input
              type="radio"
              name="reviewType"
              value="JOB"
              v-model="payload.questionType"
            />
            <label for="questionType">직무 면접</label>
             &nbsp;
            <input
              type="radio"
              name="reviewType"
              value="FREE"
              v-model="payload.questionType"
            />
            <label for="questionType">인성 면접</label>
             &nbsp;
          <!-- <div id="edit-bottom"> -->
            <span id="icon" @click="onUpdate">
              <font-awesome-icon icon="fa-solid fa-pen-to-square" /> </span
            >|
            <span id="icon" @click="switchIsEditing">
              <font-awesome-icon icon="fa-solid fa-xmark" />
            </span>
            </div>
          <!-- </div> -->
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
      userImg: require("../../../assets/images/profile.png"),
      userId: ""
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
          this.userImg = study.user.userImg;
          this.userId = study.user.userId;
          console.log(study);
        }
      }
    }
  },
  mounted() {
    this.checkUser();
    if (this.userImg == null) this.userImg = require("../../../assets/images/profile.png");
  },

  created() {
    this.bringStudySpaceDetail(this.stdNo);
    this.checkUser();
  }
};
</script>

<style scoped>
#incons{

}
#a1{
  color: #653fd3;
  display: inline;
}
#a2{
  color: red;
  display: inline;
}
.profile-img {
  border-radius: 70%;
  overflow: hidden;
}
#edit-bottom {

  /* display: inline; */
}
.edit {
  /* display: flex;
  flex-direction: column;
  align-items: center; */
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
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
.text{
  display: flex;
  justify-content: space-between;

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
  left: 85px;
}

.comments:before {
  width: 3px;
  top: -20px;
  bottom: -20px;
  height: 150px;
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
  width: 700px;
}
.form-control {
  width: 700px
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
