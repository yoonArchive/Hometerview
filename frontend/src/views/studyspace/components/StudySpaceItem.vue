<template>
    <div class="card" :class="classArray[itemIndex]" @click="moveToStudyDetail">
      <img :src="imgsrc" class="card-img-top" alt="..." >
      <div class="card-body">
        <div>
          <div>

            <h3><span v-if="studySpace.comName!=null">[{{studySpace.comName}}]</span>{{ studySpace.stdName }}</h3>
            <ul class="study-item-text">
            <li>기간 : {{ studySpace.startDate }} ~ {{ studySpace.endDate }}</li>
            <li>진행 일자 :{{ studySpace.stdDay }}</li>
            <li>인원제한 : {{ studySpace.stdLimit }}</li>
            </ul>
          </div>

        </div>
      </div>
    </div>


</template>

<script>
import router from '@/common/lib/vue-router'

export default {
  name : "StudySpaceItem",
  props:{
    studySpace : Object,
    itemIndex : Number
  },

  data(){
    return{
        studyJoins : this.studySpace.studyJoins,
        classArray : [
          "col-auto box alizarin",
          "col-auto box wisteria",
          "col-auto box emerland",
          "col-auto box belizehole",
          "col-auto box sunflower"
        ],
        imgsrc: require("@/assets/images/ssafy-logo.png")
      }
    },
  computed: {
    // countStudyJoins(){
    //     const count = this.studyJoins.length
    //     return count
    // }

  },
  methods: {
    moveToStudyDetail(){
      router.push({
        name:"studydetail",
        params: {stdNo:this.studySpace.stdNo}
      })
    },

  },



}

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,800,900");
.study-item-text{
  color: rgb(138, 138, 138);
  font-size: 15px;
}

.card-body{
  width: auto;
}
.study-image{
  width: 100%;
  height: auto;
}


/* #Colors
================================================== */

.checkbox-all:checked + label:after,
.checkbox-all:not(:checked) + label:after {
  background-color: #000;
}

.checkbox-alizarin:checked + label:after,
.checkbox-alizarin:not(:checked) + label:after {
  background-color: #e74c3c;
}

.checkbox-wisteria:checked + label:after,
.checkbox-wisteria:not(:checked) + label:after {
  background-color: #8e44ad;
}

.checkbox-emerland:checked + label:after,
.checkbox-emerland:not(:checked) + label:after {
  background-color: #2ecc71;
}

.checkbox-belizehole:checked + label:after,
.checkbox-belizehole:not(:checked) + label:after {
  background-color: #2980b9;
}

.checkbox-sunflower:checked + label:after,
.checkbox-sunflower:not(:checked) + label:after {
  background-color: #f1c40f;
}

.box {
  border-radius: 4px;
  box-shadow: 6px 7px 28px 0 rgba(16, 16, 16, 0.7);
  -webkit-transition: all 0.2s linear;
  transition: all 0.3s linear;
}
.box.alizarin {
  border: 3px solid #e74c3c;
}
.box.wisteria {
  border: 3px solid #8e44ad;
}
.box.emerland {
  border: 3px solid #2ecc71;
}
.box.belizehole {
  border: 3px solid #2980b9;
}
.box.sunflower {
  border: 3px solid #f1c40f;
}


.study-space-item-wrapper{
  background: #F3F4FF;
  border-radius: 30px;
}
</style>
