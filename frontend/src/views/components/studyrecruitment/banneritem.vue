<template>
  <div class="banner-wrapper">
    <img :src="imagesrc" class="banner-image" style="margin:5%" />
    <div class="banner-text">
      <div class="banner-title">
        {{ bannerItem[bannernumber].title }}
      </div>
      <div class="banner-content">
        {{ bannerItem[bannernumber].content }}
      </div>
      <div class="banner-writer">
        {{ bannerItem[bannernumber].writer }}
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/common/lib/axios";
export default {
  props: { bannernumber: 0 },
  setup(props) {
    console.log("???" + props.bannernumber);
    const imagesrc = require("@/assets/images/ssafy-logo.png");
    const bannerItem = [
      { title: "비대면 면접 강의", content: "20%off", writer: "사람인" },
      {
        title: "",
        content: "인성면접은 어떻게 준비해야 할까요?",
        writer: "OOO강사"
      }
    ];
    /* axios.get('/bannerinfo').then((data)=>{

    })*/

    return { imagesrc, bannerItem, props };
  }
};
</script>

<style>
.banner-writer {
  display: flex;
  font-size: 12px;
  color: #83859c;
}
.banner-content {
  display: flex;
  min-height: 30%;
  padding-bottom: 10%;
}

.banner-title {
  display: flex;
  font-size: 25px;
  min-height: 40%;
}
.banner-text {
  display: flex;
  flex-direction: column;
  margin: 5%;
}
.banner-wrapper {
  width: 48%;
  background-color: #f3f4ff;
  border-radius: 16px;
  display: flex;
}
.banner-image {
  width: 20%;
  height: 70%;
  margin: 5%;
}
</style>
