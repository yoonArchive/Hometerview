<template>
  <div>
    <vue-scroll-snap :fullscreen="true">
      <body class="item">
        <!-- ======= Hero Section ======= -->
        <section
          id="hero"
          class="d-flex justify-cntent-center align-items-center"
        >
          <div
            id="heroCarousel"
            class="container carousel carousel-fade"
            data-bs-ride="carousel"
            data-bs-interval="5000"
          >
            <!-- Slide 1 -->
            <div class="carousel-item active">
              <div class="carousel-container">
                <h2
                  id="main-text"
                  class="animate__animated animate__fadeInDown"
                >
                  면접 스터디 <span>모집부터</span><br />
                  관리까지 편안하게
                </h2>
                <p id="sub-text" class="animate__animated animate__fadeInUp">
                  홈터뷰와 함께 편리하게 면접을 준비해보세요.
                </p>
                <button
                  id="enter-btn"
                  type="button"
                  class="btn btn-primary"
                  style="background-color: #6C63FF; border-color: #6C63FF"
                  @click="goRecruit()"
                >
                  스터디 참여하기
                </button>
                <!-- <a
                  href=""
                  class="btn-get-started animate__animated animate__fadeInUp"
                  @click="goRecruit()"
                  >스터디 참여하기</a
                > -->
              </div>
            </div>
          </div>
        </section>
      </body>
      <body class="item" style="background-color: #FDF7FF">
        <section class="features" style="background-color : #F4F4F4">
          <div class="container">
            <div class="row" data-aos="fade-up">
              <div class="col-md-5">
                <img :src="imagesrc1" class="img-fluid" alt="" />
              </div>
              <div class="col-md-7 pt-4 pl-2">
                <h3
                  class="animate__animated animate__fadeInLeft"
                  id="main-text"
                >
                  면접준비 더 이상 혼자하지 마세요.
                  <br />
                  홈터뷰에서 원하는 스터디에 참여하세요.
                </h3>
                <p id="sub-text">
                  원하는 기업의 스터디가 없으신가요? 모집글을 직접 작성해보세요!
                </p>
                <button
                  id="enter-btn"
                  type="button"
                  class="btn btn-primary"
                  style="background-color: #6C63FF; border-color: #6C63FF; "
                  @click="goRecruit()"
                >
                  스터디 참여하기
                </button>
              </div>
            </div>
          </div>
        </section>
      </body>
      <body class="item">
        <section class="features">
          <div class="container">
            <div class="row" data-aos="fade-up">
              <div class="col-md-7 pt-4">
                <h3 id="main-text">
                  실시간 화상회의 서비스를 통해
                  <br />
                  모의 면접을 진행하세요.
                </h3>
                <p id="sub-text">
                  홈터뷰는 자세교정, TTS, 녹화 등 다양한 편의 서비스를
                  제공합니다.
                </p>
                <button
                  id="enter-btn"
                  type="button"
                  class="btn btn-primary"
                  style="background-color: #6C63FF; border-color: #6C63FF"
                  @click="goRecruit()"
                >
                  스터디 참여하기
                </button>
              </div>
              <div class="col-md-5">
                <img :src="imagesrc2" class="img-fluid" alt="" />
              </div>
            </div>
          </div>
        </section>
      </body>
      <body class="item" style="background-color: #FDF7FF">
        <section class="features" style="background-color : #F4F4F4">
          <div class="container">
            <div class="row" data-aos="fade-up">
              <div class="col-md-5">
                <img :src="imagesrc3" class="img-fluid" alt="" />
              </div>
              <div class="col-md-7 pt-4">
                <h3 id="main-text">
                  스터디 스페이스에 자유롭게 공유하고
                  <br />
                  예상질문을 등록하세요.
                </h3>
                <p id="sub-text">
                  자기소개서를 공유하고 스터디원들에게 예상질문을 받아보세요!
                </p>
                <button
                  id="enter-btn"
                  type="button"
                  class="btn btn-primary"
                  style="background-color: #6C63FF; border-color: #6C63FF"
                  @click="goRecruit()"
                >
                  스터디 참여하기
                </button>
              </div>
            </div>
          </div>
        </section>
      </body>
      <body class="item">
        <section class="features">
          <div class="container">
            <div class="row" data-aos="fade-up">
              <div class="col-md-7 pt-4">
                <h3 id="main-text">
                  면접 스터디부터 면접 관리까지
                  <br />
                  홈터뷰에서 한번에!
                </h3>
                <p id="sub-text">
                  일정관리, 회고작성, 화상회의 다시보기 등 다양한 기능을
                  제공합니다.
                </p>
                <button
                  id="enter-btn"
                  type="button"
                  class="btn btn-primary"
                  style="background-color: #6C63FF; border-color: #6C63FF"
                  @click="goRecruit()"
                >
                  스터디 참여하기
                </button>
              </div>
              <div class="col-md-5">
                <img :src="imagesrc4" class="img-fluid" alt="" />
              </div>
            </div>
          </div>
        </section>
      </body>
    </vue-scroll-snap>
    <body>
      <footer
        id="footer"
        data-aos="fade-up"
        data-aos-easing="ease-in-out"
        data-aos-duration="500"
      >
        <div class="container">
          <div class="copyright">
            &copy; Copyright <strong><span>Hometerview</span></strong
            >. All Rights Reserved
          </div>
          <div class="credits">
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
          </div>
        </div>
      </footer>
    </body>
  </div>
</template>
<style scoped>
@import "./main.css";
@import "../../common/css/common.css";
</style>
<script>
import router from "@/common/lib/vue-router";
import VueScrollSnap from "vue-scroll-snap";

export default {
  name: "Main",
  components: { VueScrollSnap },

  data() {
    return {
      imagesrc1: require("../../assets/images/browser_stats.svg"),
      imagesrc2: require("../../assets/images/group_video.svg"),
      imagesrc3: require("../../assets/images/learning.svg"),
      imagesrc4: require("../../assets/images/online_organizer.svg")
    };
  },

  methods: {
    goRecruit() {
      router.push({ name: "membersrecruitment" });
    }
  }
};
</script>
<style scoped>
#enter-btn {
  font-family: "맑은 고딕 볼드";
  font-size: 20px;
}
#main-text {
  font-family: "맑은 고딕 볼드";
  font-size: 40px;
  font-weight: 600;
  line-height: 1.4;
  color: #111;
}

#sub-text {
  font-family: "맑은 고딕";
  font-size: 20px;
  font-weight: 600;
  line-height: 1.4;
}

body {
  color: #444;
}

a {
  color: #1998dc;
  text-decoration: none;
}

a:hover {
  color: #45beff;
  text-decoration: none;
}

#header {
  height: 80px;
  transition: all 0.5s;
  z-index: 997;
  transition: all 0.5s;
  background: #1e4356;
}

#header.header-transparent {
  background: none;
}

#header.header-scrolled {
  background: rgba(30, 67, 86, 0.8);
  height: 60px;
}

#header .logo h1 {
  font-size: 28px;
  margin: 0;
  padding: 4px 0;
  line-height: 1;
  font-weight: 400;
  letter-spacing: 3px;
  text-transform: uppercase;
}

#header .logo h1 a,
#header .logo h1 a:hover {
  color: #fff;
  text-decoration: none;
}

#header .logo img {
  padding: 0;
  margin: 0;
  max-height: 40px;
}

#main {
  margin-top: 80px;
}

#hero {
  width: 100%;
  height: 90vh;
  overflow: hidden;
  position: relative;
}

#hero::after {
  content: "";
  position: absolute;
  left: 50%;
  top: 0;
  width: 130%;
  height: 95%;
  background: linear-gradient(
      to right,
      rgba(10, 21, 27, 0.8),
      rgba(10, 21, 27, 0.6)
    ),
    url("../../assets/images/interview10.webp") center top no-repeat;
  z-index: 0;
  border-radius: 0 0 50% 50%;
  transform: translateX(-50%) rotate(0deg);
}

#hero::before {
  content: "";
  position: absolute;
  left: 50%;
  top: 0;
  width: 130%;
  height: 96%;
  background: #6c63ff;
  opacity: 0.9;
  z-index: 0;
  border-radius: 0 0 50% 50%;
  transform: translateX(-50%) translateY(18px) rotate(2deg);
}

#hero .carousel-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  bottom: 0;
  top: 0;
  left: 0;
  right: 0;
}

#hero h2 {
  color: #fff;
  margin-bottom: 30px;
  font-size: 48px;
  font-weight: 700;
}

#hero p {
  width: 80%;
  -webkit-animation-delay: 0.4s;
  animation-delay: 0.4s;
  margin: 0 auto 30px auto;
  color: #fff;
}

#hero .carousel-control-prev,
#hero .carousel-control-next {
  width: 10%;
}

#hero .carousel-control-next-icon,
#hero .carousel-control-prev-icon {
  background: none;
  font-size: 48px;
  line-height: 1;
  width: auto;
  height: auto;
}

#hero .btn-get-started {
  font-family: "Roboto", sans-serif;
  font-weight: 500;
  font-size: 14px;
  letter-spacing: 1px;
  display: inline-block;
  padding: 12px 32px;
  border-radius: 50px;
  transition: 0.5s;
  line-height: 1;
  margin: 10px;
  color: #fff;
  -webkit-animation-delay: 0.8s;
  animation-delay: 0.8s;
  border: 2px solid #68a4c4;
}

#hero .btn-get-started:hover {
  background: #68a4c4;
  color: #fff;
  text-decoration: none;
}

@media (min-width: 1024px) {
  #hero p {
    width: 60%;
  }

  #hero .carousel-control-prev,
  #hero .carousel-control-next {
    width: 5%;
  }
}

@media (max-width: 768px) {
  #hero::after {
    width: 180%;
    height: 95%;
    border-radius: 0 0 50% 50%;
    transform: translateX(-50%) rotate(0deg);
  }

  #hero::before {
    top: 0;
    width: 180%;
    height: 94%;
    border-radius: 0 0 50% 50%;
    transform: translateX(-50%) translateY(20px) rotate(4deg);
  }
}

@media (max-width: 575px) {
  #hero h2 {
    font-size: 30px;
  }

  #hero::after {
    left: 40%;
    top: 0;
    width: 200%;
    height: 95%;
    border-radius: 0 0 50% 50%;
    transform: translateX(-50%) rotate(0deg);
  }

  #hero::before {
    left: 50%;
    top: 0;
    width: 200%;
    height: 94%;
    border-radius: 0 0 50% 50%;
    transform: translateX(-50%) translateY(20px) rotate(4deg);
  }
}

section {
  padding: 325px 0;
}

.section-bg {
  background-color: #f3f8fa;
}

.section-title {
  text-align: center;
  padding-bottom: 30px;
}

.section-title h2 {
  font-size: 28px;
  font-weight: 400;
  margin-bottom: 20px;
  padding-bottom: 20px;
  position: relative;
}

.section-title h2::before {
  content: "";
  position: absolute;
  display: block;
  width: 120px;
  height: 1px;
  background: #ddd;
  bottom: 1px;
  left: calc(50% - 60px);
}

.section-title h2::after {
  content: "";
  position: absolute;
  display: block;
  width: 40px;
  height: 3px;
  background: #68a4c4;
  bottom: 0;
  left: calc(50% - 20px);
}

.section-title p {
  margin-bottom: 0;
}

.services {
  padding-bottom: 20px;
}

.services .icon-box {
  padding: 30px;
  position: relative;
  overflow: hidden;
  margin: 0 0 40px 0;
  background: #fff;
  box-shadow: 0 5px 26px 0 rgba(68, 88, 144, 0.14);
  transition: all 0.3s ease-in-out;
  text-align: center;
  border: 1px solid #fff;
}

.services .icon {
  margin: 0 auto 20px auto;
  padding-top: 17px;
  display: inline-block;
  text-align: center;
  border-radius: 50%;
  width: 72px;
  height: 72px;
}

.services .icon i {
  font-size: 36px;
  line-height: 1;
}

.services .title {
  font-weight: 700;
  margin-bottom: 15px;
  font-size: 18px;
}

.services .title a {
  color: #111;
}

.services .description {
  font-size: 15px;
  line-height: 28px;
  margin-bottom: 0;
}

.services .icon-box-pink .icon {
  background: #fceef3;
}

.services .icon-box-pink .icon i {
  color: #ff689b;
}

.services .icon-box-pink:hover {
  border-color: #ff689b;
}

.services .icon-box-cyan .icon {
  background: #e6fdfc;
}

.services .icon-box-cyan .icon i {
  color: #3fcdc7;
}

.services .icon-box-cyan:hover {
  border-color: #3fcdc7;
}

.services .icon-box-green .icon {
  background: #eafde7;
}

.services .icon-box-green .icon i {
  color: #41cf2e;
}

.services .icon-box-green:hover {
  border-color: #41cf2e;
}

.services .icon-box-blue .icon {
  background: #e1eeff;
}

.services .icon-box-blue .icon i {
  color: #2282ff;
}

.services .icon-box-blue:hover {
  border-color: #2282ff;
}

.why-us .container {
  box-shadow: 0 5px 25px 0 rgba(214, 215, 216, 0.6);
  background: #fff;
}

.why-us .icon-box + .icon-box {
  margin-top: 50px;
}

.why-us .icon-box .icon {
  float: left;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 72px;
  height: 72px;
  background: #f3f8fa;
  border-radius: 6px;
  transition: 0.5s;
}

.why-us .icon-box .icon i {
  color: #68a4c4;
  font-size: 32px;
}

.why-us .icon-box:hover .icon {
  background: #68a4c4;
}

.why-us .icon-box:hover .icon i {
  color: #fff;
}

.why-us .icon-box .title {
  margin-left: 95px;
  font-weight: 700;
  margin-bottom: 10px;
  font-size: 18px;
  text-transform: uppercase;
}

.why-us .icon-box .title a {
  color: #343a40;
  transition: 0.3s;
}

.why-us .icon-box .title a:hover {
  color: #68a4c4;
}

.why-us .icon-box .description {
  margin-left: 95px;
  line-height: 24px;
  font-size: 14px;
}

.why-us .video-box {
  position: relative;
}

.why-us .video-box img {
  padding-top: 15px;
  padding-bottom: 15px;
}

.why-us .play-btn {
  width: 94px;
  height: 94px;
  background: radial-gradient(#68a4c4 50%, rgba(104, 164, 196, 0.4) 52%);
  border-radius: 50%;
  display: block;
  position: absolute;
  left: calc(50% - 47px);
  top: calc(50% - 47px);
  overflow: hidden;
}

.why-us .play-btn::after {
  content: "";
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translateX(-40%) translateY(-50%);
  width: 0;
  height: 0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 15px solid #fff;
  z-index: 100;
  transition: all 400ms cubic-bezier(0.55, 0.055, 0.675, 0.19);
}

.why-us .play-btn::before {
  content: "";
  position: absolute;
  width: 120px;
  height: 120px;
  -webkit-animation-delay: 0s;
  animation-delay: 0s;
  -webkit-animation: pulsate-btn 2s;
  animation: pulsate-btn 2s;
  -webkit-animation-direction: forwards;
  animation-direction: forwards;
  -webkit-animation-iteration-count: infinite;
  animation-iteration-count: infinite;
  -webkit-animation-timing-function: steps;
  animation-timing-function: steps;
  opacity: 1;
  border-radius: 50%;
  border: 5px solid rgba(104, 164, 196, 0.7);
  top: -15%;
  left: -15%;
  background: rgba(198, 16, 0, 0);
}

.why-us .play-btn:hover::after {
  border-left: 15px solid #68a4c4;
  transform: scale(20);
}

.why-us .play-btn:hover::before {
  content: "";
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translateX(-40%) translateY(-50%);
  width: 0;
  height: 0;
  border: none;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 15px solid #fff;
  z-index: 200;
  -webkit-animation: none;
  animation: none;
  border-radius: 0;
}

@-webkit-keyframes pulsate-btn {
  0% {
    transform: scale(0.6, 0.6);
    opacity: 1;
  }

  100% {
    transform: scale(1, 1);
    opacity: 0;
  }
}

@keyframes pulsate-btn {
  0% {
    transform: scale(0.6, 0.6);
    opacity: 1;
  }

  100% {
    transform: scale(1, 1);
    opacity: 0;
  }
}

.features .row + .row {
  margin-top: 100px;
}

.features h3 {
  font-weight: 400;
  font-size: 24px;
}

.features ul {
  list-style: none;
  padding: 0;
}

.features ul li {
  padding-bottom: 10px;
}

.features ul i {
  font-size: 20px;
  padding-right: 4px;
  color: #68a4c4;
}

.features p:last-child {
  margin-bottom: 0;
}

@-webkit-keyframes animate-loading {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

@keyframes animate-loading {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}
#footer {
  background: #0b212d;
  padding: 0 0 30px 0;
  color: #fff;
  font-size: 14px;
}

#footer .footer-newsletter {
  padding: 50px 0;
  background: #0d2735;
}

#footer .footer-newsletter h4 {
  font-size: 24px;
  margin: 0 0 20px 0;
  padding: 0;
  line-height: 1;
  font-weight: 600;
  color: #a2cce3;
}

#footer .footer-newsletter form {
  margin-top: 30px;
  background: #fff;
  padding: 6px 10px;
  position: relative;
  border-radius: 50px;
}

#footer .footer-newsletter form input[type="email"] {
  border: 0;
  padding: 4px;
  width: calc(100% - 100px);
}

#footer .footer-newsletter form input[type="submit"] {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  border: 0;
  background: none;
  font-size: 16px;
  padding: 0 20px;
  margin: 3px;
  background: #68a4c4;
  color: #fff;
  transition: 0.3s;
  border-radius: 50px;
}

#footer .footer-newsletter form input[type="submit"]:hover {
  background: #468db3;
}

#footer .footer-top {
  background: #0d2735;
  border-top: 1px solid #17455e;
  border-bottom: 1px solid #123649;
  padding: 60px 0 30px 0;
}

#footer .footer-top .footer-info {
  margin-bottom: 30px;
}

#footer .footer-top .footer-info h3 {
  font-size: 18px;
  margin: 0 0 20px 0;
  padding: 2px 0 2px 0;
  line-height: 1;
  color: #a2cce3;
  font-weight: 600;
}

#footer .footer-top .footer-info p {
  font-size: 14px;
  line-height: 24px;
  margin-bottom: 0;
  font-family: "Roboto", sans-serif;
  color: #fff;
}

#footer .footer-top .social-links a {
  font-size: 18px;
  display: inline-block;
  background: #1e4356;
  color: #fff;
  line-height: 1;
  padding: 8px 0;
  margin-right: 4px;
  border-radius: 50%;
  text-align: center;
  width: 36px;
  height: 36px;
  transition: 0.3s;
}

#footer .footer-top .social-links a:hover {
  background: #68a4c4;
  color: #fff;
  text-decoration: none;
}

#footer .footer-top h4 {
  font-size: 18px;
  font-weight: 600;
  color: #a2cce3;
  position: relative;
  padding-bottom: 12px;
}

#footer .footer-top .footer-links {
  margin-bottom: 30px;
}

#footer .footer-top .footer-links ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

#footer .footer-top .footer-links ul i {
  padding-right: 2px;
  color: #a2cce3;
  font-size: 18px;
  line-height: 1;
}

#footer .footer-top .footer-links ul li {
  padding: 10px 0;
  display: flex;
  align-items: center;
}

#footer .footer-top .footer-links ul li:first-child {
  padding-top: 0;
}

#footer .footer-top .footer-links ul a {
  color: #fff;
  transition: 0.3s;
  display: inline-block;
  line-height: 1;
}

#footer .footer-top .footer-links ul a:hover {
  color: #a2cce3;
}

#footer .footer-top .footer-contact {
  margin-bottom: 30px;
}

#footer .footer-top .footer-contact p {
  line-height: 26px;
}

#footer .copyright {
  text-align: center;
  padding-top: 30px;
}

#footer .credits {
  padding-top: 10px;
  text-align: center;
  font-size: 13px;
  color: #fff;
}

#footer .credits a {
  color: #a2cce3;
}
</style>
