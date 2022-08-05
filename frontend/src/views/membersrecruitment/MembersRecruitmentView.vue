<template>
  <div class="container">
    <div class="img-class"></div>

    <div class="container-middle">
      <div class="bunddle-option">
        <div class="title">
          <p id="title-name">스터디 리스트</p>
        </div>

        <div class="inner-container1">
          <div>
            <label for="recruitSearch">전체 : </label>
            <input
              type="radio"
              id="recruitSearch"
              name="recruitSearch"
              value="1"
              v-model="recruitType"
              @change="bringRecruitTypeList(recruitType)"
            />
            <label for="recruitSearch"> | 기업 면접 : </label>
            <input
              type="radio"
              id="recruitSearch"
              name="recruitSearch"
              value="2"
              v-model="recruitType"
              @change="bringRecruitTypeList(recruitType)"
            />
            <label for="recruitSearch"> | 자율 면접 : </label>
            <input
              type="radio"
              id="recruitSearch"
              name="recruitSearch"
              value="3"
              v-model="recruitType"
              @change="bringRecruitTypeList(recruitType)"
            />
          </div>

          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              name="recruiting"
              id="recruiting"
              v-model="recruiting"
              @change="isRecruiting(recruitState)"
            />
            <label class="form-check-label" for="recruiting">| 모집 중</label>
          </div>
        </div>

        <div class="inner-container2">
          <button class="new-create-button" @click="moveToCreate">
            스터디 만들기
          </button>
          <div>
            <label for="recruitSearch"></label>
            <input
              type="text"
              id="recruitSearch"
              v-model="recruitSearchKeyword"
              @keyup.enter="bringRecruitSearchList(recruitSearchKeyword)"
            />
          </div>
          <members-recruitment-list
            v-if="recruitState"
          ></members-recruitment-list>
          <members-recruiting-list
            v-if="recruitingState"
          ></members-recruiting-list>
        </div>
      </div>
    </div>
  </div>
  <div class="section">
    <div class="container">
      <div class="row justify-content-center">
        <input
          class="checkbox-all"
          type="radio"
          id="color"
          name="color"
          checked
        />
        <label for="color">all</label>
        <input
          class="checkbox-alizarin"
          type="radio"
          name="color"
          id="color-1"
        />
        <label for="color-1">alizarin</label>
        <input
          class="checkbox-wisteria"
          type="radio"
          name="color"
          id="color-2"
        />
        <label for="color-2">wisteria</label>
        <input
          class="checkbox-emerland"
          type="radio"
          name="color"
          id="color-3"
        />
        <label for="color-3">emerland</label>
        <input
          class="checkbox-belizehole"
          type="radio"
          name="color"
          id="color-4"
        />
        <label for="color-4">belizehole</label>
        <input
          class="checkbox-sunflower"
          type="radio"
          name="color"
          id="color-5"
        />
        <label for="color-5">sunflower</label>
        <div class="w-100 p-0 pt-2"></div>
        <div class="col-auto box alizarin"></div>
        <div class="col-auto box wisteria"></div>
        <div class="col-auto box emerland"></div>
        <div class="col-auto box belizehole"></div>
        <div class="col-auto box sunflower"></div>
        <div class="col-auto box alizarin"></div>
        <div class="col-auto box wisteria"></div>
        <div class="col-auto box emerland"></div>
        <div class="col-auto box belizehole"></div>
        <div class="col-auto box sunflower"></div>
        <div class="col-auto box alizarin"></div>
        <div class="col-auto box wisteria"></div>
        <div class="col-auto box emerland"></div>
        <div class="col-auto box belizehole"></div>
        <div class="col-auto box sunflower"></div>
        <div class="col-auto box alizarin"></div>
        <div class="col-auto box wisteria"></div>
        <div class="col-auto box emerland"></div>
        <div class="col-auto box belizehole"></div>
        <div class="col-auto box sunflower"></div>
      </div>
    </div>
  </div>
</template>

<script>
import MembersRecruitmentList from "./components/MembersRecruitmentList.vue";
import MembersRecruitingList from "./components/MembersRecruitingList.vue";
import router from "@/common/lib/vue-router.js";
import { mapActions } from "vuex";

export default {
  name: "MembersRecruitmentView",
  components: {
    MembersRecruitmentList,
    MembersRecruitingList
  },

  data() {
    return {
      recruitState: true,
      recruitingState: false,
      recruitSearchKeyword: "",
      recruitType: ""
    };
  },
  computed: {},
  methods: {
    ...mapActions(["bringRecruitSearchList", "bringRecruitTypeList"]),

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
      } else {
        this.recruitState = true;
        this.recruitingState = false;
      }
    }
  }
};
</script>

<style scoped>
.bunddle-option {
  /* display: grid; */
  /* flex-direction: row; */
  /* justify-content: space-evenly; */
  grid-column: 5;
}
.container-middle {
  /* display: grid; */
  /* justify-content: right; */
  /* justify-content: space-evenly; */
}
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
  flex-direction: row;
  border-radius: 40%;
  font-family: "티머니 둥근바람";
}
</style>
