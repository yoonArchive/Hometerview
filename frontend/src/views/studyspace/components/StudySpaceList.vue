<template>
  <!-- <div class="row row-cols-1 row-cols-md-3 g-4">
    <div
      class="col"
      v-for="(studySpace, index) in studySpaceList"
      :key="studySpace.std_no"
    >
      <study-space-item
        :studySpace="studySpace"
        :itemIndex="index"
      ></study-space-item>
    </div>
  </div> -->
  <div class="carousel-item active">
    <img :src="image" class="d-block w-100" alt="..." />
    <div class="carousel-caption d-none d-md-block">
      <h5>First slide label</h5>
      <p>Some representative placeholder content for the first slide.</p>
      <div class="slider-btn">
        <button class="carouselBtn btn-1">Our Services</button>
        <button class="carouselBtn btn-2">Get q quote</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import StudySpaceItem from "./StudySpaceItem.vue";

export default {
  name: "StudySpaceList",
  components: {
    StudySpaceItem
  },

  data() {
    return {
      isloading: false
    };
  },
  computed: {
    ...mapGetters(["studySpaceList"])
  },
  methods: {
    ...mapActions(["bringStudySpace"])
  },
  mounted() {
    this.bringStudySpace();
  },
  beforeUpdate() {
    console.log(this.studySpaceList.length);
  }
};
</script>

<style></style>
