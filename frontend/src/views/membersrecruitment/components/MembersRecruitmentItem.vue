<template>
  <div class="col" @click="moveToDetail()">
    <div class="card">
      <!-- <img src="{{ recruitment.stdImg }}" class="card-img-top" alt="..." /> -->
      <img class="card-img-top" :src="image" alt="beach" />
      <div class="card-body">
        <div style="height:3.2em;">
          <h6 class="card-text">
            {{ recruitment.recruitTitle }}
          </h6>
        </div>
        <div>
          <i class="input-icon uil uil-clock"></i>
          <span class="text-muted">{{ recruitment.stdDay }}</span>
          <span
            v-if="recruitment.stdType == `COM`"
            style="float:right; margin-right:6px;"
          >
            <i class="input-icon uil uil-location-arrow"></i>
            <span class="text-muted">{{ recruitment.comName }}</span>
          </span>
        </div>
        <div class="type">
          <div
            class="completed"
            v-if="recruitment.recruitStatus.status == `모집 완료`"
          >
            <i class="input-icon uil uil-moon"></i
            >{{ recruitment.recruitStatus.status }}
          </div>
          <div class="ing" v-else>
            <i class="input-icon uil uil-users-alt"></i
            ><b> {{ applyCounts[index] }}/{{ recruitment.stdLimit }}</b
            >&nbsp;
            {{ recruitment.recruitStatus.status }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/common/lib/vue-router";
import { mapGetters } from "vuex";
export default {
  name: "MembersRecruitmentItem",
  props: {
    recruitment: Object,
    index: Number,
    recruitState: Boolean
  },

  data() {
    return {
      image: require("../../../assets/images/fighting.jpeg")
    };
  },
  computed: { ...mapGetters(["applyCounts"]) },
  methods: {
    methods: {},

    moveToDetail() {
      router.push({
        name: "membersrecruitmentdetail",
        params: { recruitNo: this.recruitment.recruitNo }
      });
    }
  }
  // async created(){
  //   if(this.recruitCount===0){
  //     await this.deleteRecruitmentDetail(this.recruitNo)
  //     await this.bringRecruitmentDetail(this.recruitNo)
  //     }
  // }
};
</script>

<style scoped>
.card {
  width: 15rem;
  height: 16rem;
}
.card:hover {
  background-color: #e2e1e1;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
  transform: scale(1.03);
}
.card-img-top {
  height: 7rem;
  object-fit: cover;
}
.completed {
  background: #4b4b4b;
  color: white;
  border-radius: 15px;
  width: 7em;
  margin-top: 8px;
  padding: 6px;
  font-size: 16px;
}
.ing {
  background: #5fc971;
  color: black;
  border-radius: 15px;
  width: 8.1em;
  margin-top: 8px;
  padding: 6px;
  font-size: 16px;
}
.text-muted {
  font-size: 15px;
}
</style>
