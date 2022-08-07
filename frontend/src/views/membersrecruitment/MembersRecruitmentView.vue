<template>
  <div class="container">
    <div class="row" style="margin-bottom:20px;">
      <div class="title col-md-9">
        <h5 id="title-name">스터디 모집글 목록</h5>
      </div>
      <button
        type="button"
        class="button small"
        @click="moveToCreate"
        style="margin-left:70px"
      >
        스터디 만들기
      </button>
    </div>
    <hr />
    <div>
      <div class="row">
        <div class="col-md-6"></div>
        <div class="col-md-4">
          <select
            name="recruitSearch"
            class="selectBar"
            v-model="recruitType"
            @change="getRecruitTypeList()"
          >
            <option value="1" class="option" selected>전체 스터디</option>
            <option value="2" class="option">기업 면접 스터디</option>
            <option value="3" class="option">자율 면접 스터디</option>
          </select>
        </div>
        <div class="form-check col-md-2">
          <input
            class="form-check-input"
            type="checkbox"
            name="recruiting"
            id="recruiting"
            v-model="recruiting"
            @change="isRecruiting()"
          />
          <label class="form-check-label" for="recruiting">모집 중</label>
        </div>
      </div>
      <div class="inner-container2">
        <div class="row row-cols-1 row-cols-md-3 g-5">
          <members-recruitment-item
            v-for="(recruitment, index) in recruitmentList"
            :key="recruitment.recruitNo"
            :recruitment="recruitment"
            :index="index"
            :recruitState="recruitState"
          >
          </members-recruitment-item>
        </div>
      </div>
    </div>
    <div id="searchBar" class="row aln-center">
      <div class="col-6 col-12-xsmall">
        <input
          type="text"
          id="recruitSearch"
          class="recruitSearch"
          v-model="recruitSearchKeyword"
          placeholder="스터디 모집글 제목 검색"
          @keyup.enter="bringRecruitSearchList(recruitSearchKeyword)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import MembersRecruitmentItem from "./components/MembersRecruitmentItem.vue";
import router from "@/common/lib/vue-router.js";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "MembersRecruitmentView",
  components: {
    MembersRecruitmentItem
  },
  data() {
    return {
      recruitState: true,
      recruitingState: false,
      recruitSearchKeyword: "",
      recruitType: "1",
      image: require("../../assets/images/samsung.jpg")
    };
  },
  created() {
    this.bringRecruitmentList();
  },
  computed: { ...mapGetters(["recruitmentList"]) },
  methods: {
    ...mapActions([
      "bringRecruitSearchList",
      "bringRecruitTypeList",
      "bringRecruitingList",
      "bringRecruitmentList"
    ]),

    moveToCreate() {
      router.push({ name: "createmembersrecruitment" });
    },
    // async moveToRecruitSearch(recruitSearchKeyword){
    //   await this.bringRecruitSearchList(recruitSearchKeyword)

    // },
    isRecruiting() {
      if (this.recruitState === true) {
        this.recruitState = false;
        this.recruitingState = true;
        this.bringRecruitingList(this.recruitType);
      } else {
        this.recruitState = true;
        this.recruitingState = false;
        if (this.recruitType != "1") this.getRecruitTypeList();
        else this.bringRecruitmentList(this.recruitType);
      }
    },
    getRecruitTypeList() {
      if (this.recruitingState === true) {
        this.bringRecruitingList(this.recruitType);
      } else {
        this.bringRecruitTypeList(this.recruitType);
      }
    }
  },
  mounted() {
    window.scrollTo(0, 0);
  }
};
</script>

<style scoped>
/* .bunddle-option { */
/* display: grid; */
/* flex-direction: row; */
/* justify-content: space-evenly; */
/* grid-column: 5; */
/* } */
/*
.new-create-button {
  height: 40px;
  background: #f3f0fb;
  border: 1px solid #653fd3;
  border-radius: 16px;
  font-family: "티머니 둥근바람";
}
.container {
  display: flex;
  flex-direction: column;
  font-family: "티머니 둥근바람";
}
.inner-container1 {
  /* justify-content: center; */
/* flex-direction: row;
  border-radius: 40%;
  font-family: "티머니 둥근바람";
} */
.selectBar {
  border-radius: 10px;
  font-size: 17px;
  float: right;
}
.btn::before {
  content: "전체 스터디";
  display: flex;
  align-items: center;
  width: 100%;
  height: 100%;
  background: white;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.062);
  border: 2px solid rgba(184, 184, 184, 0.25);
  padding-left: 1em;
  background: linear-gradient(
    to right,
    white 80%,
    rgba(184, 184, 184, 0.25) 20%
  );
}
.btn::after {
  content: "f062";
  position: absolute;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "Font Awesome 5 Free";
  font-weight: 700;
  width: 20%;
  height: 110%;
  color: white;
  transform: rotate(180deg);
  transition: all 0.4s;
}
.btn:checked::after {
  transform: rotate(0deg);
}
.list {
  position: absolute;
  margin-top: 0.5em;
  width: 14%;
  height: 140px;
  clip-path: polygon(0 0, 100% 0, 100% 0, 0 0);
  display: flex;
  flex-direction: column;
  transition: all 0.4s;
}
input:checked ~ .list {
  clip-path: none;
}
.list div {
  height: 25%;
  display: flex;
  align-items: center;
  padding: 0.3em 0 0.3em 1em;
  text-decoration: none;
  margin: 0.3em 0;
  background: white;
  color: rgb(36, 36, 36);
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.062);
  transition: background 0.4s;
}
.option:hover {
  background: #ff5f6d;
  color: white;
}
.card__image-holder {
  background: rgba(0, 0, 0, 0.1);
  height: 0;
  padding-bottom: 50%;
}
input[type="button"].small,
button.small,
.button.small {
  font-size: 0.7em;
}
input[type="button"],
button,
.button {
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  border-radius: 5px;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  background-color: #f3f0fa;
  color: #653fd3;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 600;
  height: 3em;
  width: 11em;
  letter-spacing: 0.15em;
  text-align: center;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
input[type="button"]:hover,
button:hover,
.button:hover {
  background-color: #653fd3;
  color: #ffffff;
}
.row.aln-center {
  justify-content: center;
}
input[type="text"],
/* select, */
textarea {
  -moz-appearance: none;
  -webkit-appearance: none;
  -ms-appearance: none;
  appearance: none;
  background-color: rgba(184, 184, 184, 0.25);
  border-radius: 3px;
  border: none;
  color: inherit;
  display: block;
  outline: 0;
  padding: 0 1em;
  margin-top: 3em;
  text-decoration: none;
  width: 100%;
  height: 2em;
}
.recruitSearch {
  font-size: 19px;
}
</style>
