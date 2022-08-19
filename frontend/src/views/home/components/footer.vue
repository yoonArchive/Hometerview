<template>
  <div class="main-footer" :gutter="10">
    <router-link class="routerlink" :to="{ name: 'notices' }" id="a">
      공지사항 &nbsp;&nbsp;
    </router-link>
    <a href="" id="b">이용약관</a> &nbsp;&nbsp;
    <a href="" id="c">고객센터</a> &nbsp;&nbsp;
    <div class="copyright">
      <h6>
        <!-- Copyright 2022. 홈터뷰 All pictures cannot be copied without permission. -->
        &copy; <strong><span>Hometerview</span></strong
        >. All pictures cannot be copied without permission.
      </h6>
    </div>
  </div>
  <!-- Copyright 2021. 저작권자 All pictures cannot be copied without permission.  -->
</template>

<style scoped>
.logo-image {
  display: flex;
  /* margin-left: 500px; */
  margin-left: auto;
}
#arrow {
  margin-left: 30px;
  height: auto;
  width: 50px;
}
.word {
}
#logo {
  width: 150px;
  height: 100px;
  /* float: right */
}
#a {
  text-decoration-line: none;
  color: white;
  font-size: 20px;
  margin-left: 30px;
  font-family: "티머니 둥근바람";
  text-align: center;
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
  display: flex;
  /* margin-left: 50px; */
  flex-direction: row;
  /* display: flex; */
  /* flex-direction: row; */
  /* align-items: center; */
  padding: 0px;
  /* gap: 40px; */
  margin-left: auto;
}

@media (max-width: 700px) {
  .main-footer {
    height: 100px;
    width: 100%;
    color: white;
    text-align: center;
    background-color: #8065d3;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08),
      0px 4px 24px rgba(0, 0, 0, 0.08);
  }
  .main-footer .contents {
    line-height: 190px;
    text-align: center;
    margin: 0 auto;
  }

  .word {
    /* display: none; */
  }
}
@media (max-width: 1218px) {
  .word {
    /* display: none; */
  }
}
@media (min-width: 701px) {
  .main-footer {
    height: 100px;
    width: 100%;

    background-color: #8065d3;
    color: white;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08),
      0px 4px 24px rgba(0, 0, 0, 0.08);
    text-align: left;
    padding: 20px;
    text-align: center;

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

    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08),
      0px 4px 24px rgba(0, 0, 0, 0.08);
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

    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.08),
      0px 4px 24px rgba(0, 0, 0, 0.08);
  }
}
.copyright {
  color: rgba(255, 255, 255, 0.534);
  margin: 10px;
}
</style>
<script>
import { reactive } from "vue";
import { useStore } from "vuex";

export default {
  name: "footer",
  data() {
    return {
      imgsource: require("../../../assets/images/logo.png")
    };
  },
  props: {
    height: {
      type: String,
      default: "110px"
    }
  },

  setup() {
    const state = reactive({});

    return { state };
  }
};
</script>
<style></style>
