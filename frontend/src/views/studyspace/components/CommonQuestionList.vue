<template>
  <!-- <div class="study-detail-information-wrapper">
    <h4 class="stdNotice col-md-3">공통질문 &nbsp;</h4>
    <common-question-item
      v-for="commonquestion in this.commonquestions"
      :commonquestion="commonquestion"
      :questionNo="commonquestion.questionNo"
      :stdNo="stdNo"
      :key="commonquestion.questionNo"
    >
    </common-question-item>
    <div class="comments-from">
      <common-question-form></common-question-form>
    </div>
  </div> -->

  <div class="container">
    <div class="study-detail-information-wrapper">
      <div class="row">
        <h4 class="stdNotice col-md-3">
          스터디 공통질문 &nbsp;
          <span @click="showModal = true" id="icon">
            <font-awesome-icon icon="fa-solid fa-square-plus" />
          </span>
        </h4>
        <br />
        <common-question-item
          v-for="commonquestion in this.commonquestions"
          :commonquestion="commonquestion"
          :questionNo="commonquestion.questionNo"
          :stdNo="stdNo"
          :key="commonquestion.questionNo"
        >
        </common-question-item>
      </div>
      <div class="comments-from" v-show="showModal">
        <common-question-form></common-question-form>
      </div>
    </div>
  </div>
</template>
<script>
import CommonQuestionForm from "./CommonQuestionForm.vue";
import CommonQuestionItem from "./CommonQuestionItem.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "CommonQuestionList",
  props: {
    commonquestions: Array
  },
  components: { CommonQuestionForm, CommonQuestionItem },
  data() {
    return {
      stdNo: this.$route.params.stdNo,
      showModal: false
    };
  },
  setup() {},
  created() {
    this.commonQuestions1(this.stdNo);
    console.log(this.commonQuestions);
  },
  beforeUpdate() {
    // this.commonQuestions1(this.stdNo);
    // console.log("김" + this.stdNo);
  },
  methods: {
    ...mapActions(["getStd", "commonQuestions1"])
  },
  computed: {
    ...mapGetters(["commonQuestions"])
  }
};
</script>
<style scoped>
.comments-from {
  margin-top: 40px;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
}
.study-detail-information-wrapper {
  margin-left: 3.5em;
}
h3,
h5 {
  font-family: "티머니 둥근바람 볼드";
}
h3 {
  margin-bottom: 25px;
}
.stdNotice,
.stdMembers {
  font-family: "티머니 둥근바람";
  font-weight: bold;
  margin-bottom: 20px;
}
.status {
  font-size: 25px;
  font-weight: 600;
  font-family: "티머니 둥근바람";
  color: #ffffff;
  border-radius: 35px;
  padding: 10px;
}
.ing {
  background-color: #5fc971;
}
.completed {
  background-color: #4b4b4b;
}
.title {
  font-weight: 600;
  font-size: 25px;
}
.info {
  margin-bottom: 12px;
}
.comName {
  color: #f56a6a;
}
blockquote {
  quotes: none;
  border-left: solid 3px rgba(210, 215, 217, 0.75);
  margin: 0 0 1.7em 0;
  padding: 0.5em 0 0.5em 2em;
}
#banner {
  padding: 3em 0 2em 0;
  display: -moz-flex;
  display: -webkit-flex;
  display: -ms-flex;
}
#banner h1 {
  margin-top: -0.125em;
}
#banner .content {
  -moz-flex-grow: 1;
  -webkit-flex-grow: 1;
  -ms-flex-grow: 1;
  flex-grow: 1;
  -moz-flex-shrink: 1;
  -webkit-flex-shrink: 1;
  -ms-flex-shrink: 1;
  flex-shrink: 1;
  width: 50%;
}
#banner .image {
  -moz-flex-grow: 0;
  -webkit-flex-grow: 0;
  -ms-flex-grow: 0;
  flex-grow: 0;
  -moz-flex-shrink: 0;
  -webkit-flex-shrink: 0;
  -ms-flex-shrink: 0;
  flex-shrink: 0;
  display: block;
  margin: 0 0 2em 4em;
  width: 50%;
}
#banner .image img {
  height: 100%;
  -moz-object-fit: cover;
  -webkit-object-fit: cover;
  -ms-object-fit: cover;
  object-fit: cover;
  -moz-object-position: center;
  -webkit-object-position: center;
  -ms-object-position: center;
  object-position: center;
  width: 100%;
}
@media screen and (orientation: portrait) {
  #banner {
    -moz-flex-direction: column-reverse;
    -webkit-flex-direction: column-reverse;
    -ms-flex-direction: column-reverse;
    flex-direction: column-reverse;
  }
  #banner h1 br {
    display: none;
  }
  #banner .content {
    -moz-flex-grow: 0;
    -webkit-flex-grow: 0;
    -ms-flex-grow: 0;
    flex-grow: 0;
    -moz-flex-shrink: 0;
    -webkit-flex-shrink: 0;
    -ms-flex-shrink: 0;
    flex-shrink: 0;
    width: 100%;
  }
  #banner .image {
    -moz-flex-grow: 0;
    -webkit-flex-grow: 0;
    -ms-flex-grow: 0;
    flex-grow: 0;
    -moz-flex-shrink: 0;
    -webkit-flex-shrink: 0;
    -ms-flex-shrink: 0;
    flex-shrink: 0;
    margin: 0 0 0 4em;
    height: 25em;
    max-height: 50vh;
    min-height: 18em;
    width: 100%;
  }
}
@media screen and (orientation: portrait) and (max-width: 480px) {
  #banner .image {
    max-height: 35vh;
  }
}
section.special,
article.special {
  text-align: center;
}

header p {
  font-family: "Roboto Slab", serif;
  font-size: 1em;
  font-weight: 400;
  letter-spacing: 0.075em;
  margin-top: -0.5em;
  text-transform: uppercase;
}

header.major > :last-child {
  border-bottom: solid 3px #653fd3;
  display: inline-block;
  margin: 0 0 2em 0;
  padding: 0 0.75em 0.5em 0;
}

header.main > :last-child {
  margin: 0 0 1em 0;
}
.image {
  border-radius: 0.375em;
  border: 0;
  display: inline-block;
  position: relative;
}
.image img {
  border-radius: 0.375em;
  display: block;
}
.image.fit {
  display: block;
  margin: 0 0 2em 0;
  width: 100%;
}
.image.fit img {
  width: 100%;
}
.image.main {
  display: block;
  margin: 0 0 3em 0;
  width: 100%;
}
.image.main img {
  width: 100%;
}
.image :hover {
  transform: scale(1.03);
}
.box {
  border-radius: 0.375em;
  border: solid 2px rgba(210, 215, 217, 0.75);
  padding: 0.8em;
  margin: 0 0 1.7em 0;
  font-size: 20px;
}
.box > :last-child,
.box > :last-child > :last-child,
.box > :last-child > :last-child > :last-child {
  margin-bottom: 0;
}
input[type="button"],
button,
.button {
  -moz-appearance: none;
  -webkit-appearance: none;
  -ms-appearance: none;
  appearance: none;
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  background-color: transparent;
  border-radius: 0.375em;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  /* color: #653fd3 !important; */
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 700;
  height: 3.5em;
  letter-spacing: 0.075em;
  line-height: 3.5em;
  padding: 0 2.25em;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  white-space: nowrap;
  margin: 0 0.5em 0 0;
}
input[type="button"]:hover,
button:hover,
.button:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
input[type="button"]:active,
button:active,
.button:active {
  background-color: rgba(161, 104, 253, 0.15);
}
input[type="button"].icon:before,
button.icon:before,
.button.icon:before {
  margin-right: 0.5em;
}
input[type="button"].fit,
button.fit,
.button.fit {
  width: 100%;
}
input[type="button"].small,
button.small,
.button.small {
  font-size: 0.63em;
  width: 7em;
  text-align: center;
}
input[type="button"].large,
button.large,
.button.large {
  font-size: 1em;
  height: 3.65em;
  line-height: 3.65em;
}
input[type="button"].disabled,
input[type="button"]:disabled,
button.disabled,
button:disabled,
.button.disabled,
.button:disabled {
  pointer-events: none;
  opacity: 0.25;
}

.table-wrapper {
  -webkit-overflow-scrolling: touch;
  overflow-x: auto;
}
table {
  margin: 0 0 2em 0;
  width: 100%;
}
table tbody tr {
  border: solid 1px rgba(210, 215, 217, 0.75);
  border-left: 0;
  border-right: 0;
  height: 60px;
}
table tbody tr:nth-child(2n + 1) {
  background-color: rgba(230, 235, 237, 0.25);
}
table td {
  padding: 0.75em 0.75em;
}
table th {
  color: #3d4449;
  font-size: 0.9em;
  font-weight: 600;
  padding: 0 0.75em 0.75em 0.75em;
  text-align: left;
}
table thead {
  border-bottom: solid 2px rgba(210, 215, 217, 0.75);
}
table tfoot {
  border-top: solid 2px rgba(210, 215, 217, 0.75);
}
table.alt {
  border-collapse: separate;
}
table.alt tbody tr td {
  border: solid 1px rgba(210, 215, 217, 0.75);
  border-left-width: 0;
  border-top-width: 0;
}
table.alt tbody tr td:first-child {
  border-left-width: 1px;
}
table.alt tbody tr:first-child td {
  border-top-width: 1px;
}
table.alt thead {
  border-bottom: 0;
}
table.alt tfoot {
  border-top: 0;
}
i:hover {
  color: #653fd3;
}
#icon {
  cursor: pointer;
}
#icon:hover {
  color: #653fd3;
}
</style>
