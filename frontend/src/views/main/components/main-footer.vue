<template>
  <el-row
    class="main-footer"
    :gutter="10">
    <div class="contents">
      <div class="box">
        <div class="word">
          <a href="www.naver.com" id="a">공지사항</a>
          <a href="" id="b">이용약관</a>
          <a href="" id="c">고객센터</a>
        </div>
        <img :src="imgsource" alt="" id="logo">
      </div>

    </div>
  </el-row>
</template>
<style>

.word {
  float: left;
}
#logo {
  width: 150px;
  height: 110px;
  float: right;


}
#a {
  text-decoration-line: none;
  color: white;
  font-size: 20px;
  font-family: "티머니 둥근바람";
}

#b {
  text-decoration-line: none;
  color: white;
  font-size: 20px;
  margin-left: 30px;
  font-family: "티머니 둥근바람";
}

#c {
  text-decoration-line: none;
  color: white;
  font-size: 20px;
  margin-left: 30px;
  font-family: "티머니 둥근바람";
}

.box {
  /* display: flex;
  margin-left: 50px;
  flex-direction: row; */
  /* display: flex; */
  /* flex-direction: row; */
  /* align-items: center; */
  padding: 0px;
  gap: 40px;
  margin-left: 100px;
}

@media (max-width: 700px) {
  .main-footer {
    height: 190px;
    width: 100%;
    color: white;
    background-color: #653FD3;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08), 0px 4px 24px rgba(0, 0, 0, 0.08);

  }
  .main-footer .contents {
    line-height: 190px;
    text-align: center;
    margin: 0 auto;
  }
}

@media (min-width: 701px) {
  .main-footer {
    height: 110px;
    /* width: 102%; */
    background-color: #653FD3;
    color: white;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08), 0px 4px 24px rgba(0, 0, 0, 0.08);




    /* display: flex;
flex-direction: row;
justify-content: space-between;
align-items: center;
padding: 16px 32px;
gap: 40px;

position: relative;
width: auto;
height: 76px;

background: #653FD3; */
/* Shadow/4dp */

box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08), 0px 4px 24px rgba(0, 0, 0, 0.08);
  }
  .main-footer .contents {
    line-height: 110px;

    text-align: center;
    margin: 0 auto;



    /* display: flex;
flex-direction: row;
justify-content: space-between;
align-items: center;
padding: 16px 32px;
gap: 40px;

position: relative;
width: auto;
height: 76px;

background: #653FD3; */
/* Shadow/4dp */

box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08), 0px 4px 24px rgba(0, 0, 0, 0.08);

  }
}
</style>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'main-footer',
  data() {
    return { imgsource: require('../../../assets/images/logo.png')}
  },
  props: {
    height: {
      type: String,
      default: '110px'
    }
  },

  setup() {
    const state = reactive({})

    return { state }
  }
}
</script>
<style>
</style>
