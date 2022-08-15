<template>
  <div class="container">
    <div class="row">
      <div class="title col-md-10">
        <h5 id="title-name">나의 스터디</h5>
      </div>
      <div class="select col-md-2">
        <input
          type="radio"
          id="completed"
          v-model="recruitStatus"
          value="completed"
          style="margin-left:25px"
        />
        <label for="completed">신청 완료</label>
        <input
          type="radio"
          id="ing"
          v-model="recruitStatus"
          value="ing"
          style="margin-left:25px"
        />
        <label for="ing">신청 중</label>
      </div>
    </div>
    <hr />
    <div
      id="carouselExampleCaptions"
      class="carousel slide"
      data-bs-ride="carousel"
    >
      <div v-if="recruitStatus === 'completed'">
        <div v-if="!isLoggedIn">
          <div class="carousel carousel-inner">
            <h6 style="color:white; text-align:center; margin-top:90px">
              지금 바로 로그인하고 나만의 스터디를 만들어 보세요!
            </h6>
          </div>
        </div>
        <div v-else>
          <div v-if="studySpaceList.length == 0">
            <div class="carousel carousel-inner">
              <h6 style="color:white; text-align:center; margin-top:70px">
                <b>{{ currentUser.userName }}</b
                >님이 속한 스터디가 등록됩니다.<br /><br />
                스터디 만들기에서 내 스터디를 만들거나 스터디에 가입하여
                홈터뷰를 이용해보세요.
              </h6>
            </div>
          </div>
          <div v-else>
            <div class="carousel carousel-inner">
              <div
                class="carousel-item"
                v-for="(studySpace, idx) in studySpaceList"
                :key="idx"
                :class="{ active: idx == 0 }"
              >
                <study-space-item :studySpace="studySpace"></study-space-item>
              </div>
            </div>
            <button
              class="carousel-control-prev"
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide="prev"
            >
              <span
                class="carousel-control-prev-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button
              class="carousel-control-next"
              type="button"
              data-bs-target="#carouselExampleCaptions"
              data-bs-slide="next"
            >
              <span
                class="carousel-control-next-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>
      </div>
      <div v-else>
        <div v-if="!isLoggedIn">
          <div class="carousel carousel-inner">
            <h6 style="color:white; text-align:center; margin-top:90px">
              지금 바로 로그인하고 나만의 스터디를 만들어 보세요!
            </h6>
          </div>
        </div>
        <div v-else-if="applyingList.length == 0">
          <div class="carousel carousel-inner">
            <div>
              <h6 style="color:white; text-align:center; margin-top:70px">
                스터디 승인이 완료되지 않은 스터디가 등록됩니다.<br /><br />
                스터디 만들기에서 내 스터디를 만들거나 스터디에 가입하여
                홈터뷰를 이용해보세요.
              </h6>
            </div>
          </div>
        </div>
        <div v-else>
          <div class="carousel carousel-inner">
            <div
              class="carousel-item"
              v-for="(applyingRecruit, idx) in applyingList"
              :key="idx"
              :class="{ active: idx == 0 }"
            >
              <applying-recruit-item
                :applyingRecruit="applyingRecruit"
              ></applying-recruit-item>
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </div>
    <div class="row" style="margin-bottom:20px;">
      <div class="title col-md-3">
        <h5 id="title-name">스터디 모집글 목록</h5>
      </div>
      <div class=" col-md-6">
        <button
          type="button"
          class="createBtn small"
          @click="moveToCreate"
          style="margin-left:-100px"
        >
          스터디 만들기
        </button>
      </div>
      <div class="col-md-2">
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
      <div class="form-check col-md-1">
        <input
          class="form-check-input"
          type="checkbox"
          name="recruitType"
          id="recruiting"
          v-model="recruitingState"
          @change="isRecruiting()"
        />
        <label class="form-check-label" for="recruiting">모집 중</label>
      </div>
    </div>
    <hr />
    <div>
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
          @keyup.enter="searchByTitle(recruitSearchKeyword)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { computed, reactive } from "@vue/runtime-core";
import MembersRecruitmentItem from "./components/MembersRecruitmentItem.vue";
import StudySpaceItem from "../studyspace/components/StudySpaceItem.vue";
import ApplyingRecruitItem from "../studyspace/components/ApplyingRecruitItem.vue";
import router from "@/common/lib/vue-router.js";
import { mapActions, mapGetters } from "vuex";
import { useStore } from "vuex";

export default {
  name: "MembersRecruitmentView",
  components: {
    MembersRecruitmentItem,
    StudySpaceItem,
    ApplyingRecruitItem
  },
  data() {
    return {
      recruitState: true,
      recruitingState: false,
      recruitSearchKeyword: "",
      recruitType: "1",
      isloading: false,
      recruitStatus: "completed",
      isLoggedIn: computed(() => store.getters.isLoggedIn)
    };
  },
  created() {
    this.bringRecruitmentList();
    this.bringStudySpace();
    this.bringApplyingRecruit();
    this.refreshsetting();
  },
  computed: {
    ...mapGetters([
      "recruitmentList",
      "studySpaceList",
      "applyingList",
      "isLoggedIn",
      "currentUser"
    ])
  },
  methods: {
    ...mapActions([
      "bringRecruitSearchList",
      "bringRecruitTypeList",
      "bringRecruitingList",
      "bringRecruitmentList",
      "bringStudySpace",
      "bringApplyingRecruit",
      "fetchCurrentUser"
    ]),
    async refreshsetting() {
      await this.fetchCurrentUser();
      await this.setting();
    },
    moveToCreate() {
      router.push({ name: "createmembersrecruitment" });
    },
    isRecruiting() {
      this.recruitSearchKeyword = "";
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
      this.recruitSearchKeyword = "";
      console.log(this.recruitType);
      if (this.recruitingState === true) {
        this.bringRecruitingList(this.recruitType);
      } else {
        this.bringRecruitTypeList(this.recruitType);
      }
    },
    searchByTitle(recruitSearchKeyword) {
      this.recruitType = "1";
      this.recruitState = true;
      this.recruitingState = false;
      this.bringRecruitSearchList(recruitSearchKeyword);
    }
  },
  mounted() {
    window.scrollTo(0, 0);
    this.refreshsetting();
  },
  setup() {
    const store = useStore();
    const state = reactive({
      headeritems: computed(() => {
        const headeritems = store.getters["root/getMenus"];
        console.log(headeritems + "?");
        let keys = Object.keys(headeritems);
        console.log(keys + "!");
        let headmenu = [];
        for (var i = 0; i < keys.length; i++) {
          let menuobject = {};
          menuobject.path = headeritems[keys[i]].path;
          menuobject.title = headeritems[keys[i]].name;
          headmenu.push(menuobject);
        }
        return headmenu;
      })
    });
    return {
      state,
      isLoggedIn: computed(() => store.getters.isLoggedIn)
    };
  }
};
</script>

<style scoped>
.title {
  font-family: "티머니 둥근바람 볼드";
}
.select {
  padding: 10px 8px;
}
.select input[type="radio"] {
  display: none;
}
.select input[type="radio"] + label {
  display: inline-block;
  cursor: pointer;
  height: 30px;
  width: 100px;
  border: 1px solid #333;
  line-height: 25px;
  text-align: center;
  font-weight: bold;
  font-size: 18px;
}
.select input[type="radio"] + label {
  background-color: #fff;
  color: #333;
}
.select input[type="radio"]:checked + label {
  background-color: #333;
  color: #fff;
}
.selectBar {
  background-color: #333;
  color: #fff;
  border-radius: 10px;
  font-size: 18px;
  float: right;
  height: 35px;
  width: 170px;
  font-weight: bold;
  text-align: center;
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
.createBtn {
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  border-radius: 5px;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  background-color: #ffffff;
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
.createBtn:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
.createBtn:active {
  background-color: rgba(161, 104, 253, 0.15);
}
.row.aln-center {
  justify-content: center;
}
input[type="text"] {
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
.carousel {
  margin-bottom: 2.5em;
}
.carousel-inner {
  border-radius: 30px;
  height: 200px;
  background-color: #282a30;
}
.carousel-item {
  /* margin-top: 30px; */
  height: 110%;
  /* min-height: 200px; */
  background: no-repeat scroll center scroll;
  background-size: cover;
}
.carousel-item::before {
  /* content: ""; */
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  /* background: #000; */
  /* opacity: 0.7; */
}
.carousel-caption h5 {
  font-size: 100px;
  font-weight: 700;
}
.carousel-caption p {
  font-size: 18px;
  top: 2rem;
}
.slider-btn {
  margin-top: 20px;
}
.slider-btn .carouselBtn {
  background-color: #ff5f6d;
  columns: white;
  border-radius: 0;
  padding: 1.5rem 2rem;
  font-size: 0.8rem;
  margin-right: 15px;
}
</style>
