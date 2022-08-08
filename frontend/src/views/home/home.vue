<template>
  <header-vue></header-vue>
  <div :class="fullcontainer">
    <!-- <div class="section">
	</div>
	  <div class="section">
	</div> -->
    <div :class="maincontainer">
      <router-view></router-view>

    </div>
    <div class="progress-wrap">
      <svg class="progress-circle svg-content" width="100%" height="100%" viewBox="-1 -1 102 102">
        <path d="M50,1 a49,49 0 0,1 0,98 a49,49 0 0,1 0,-98"/>
      </svg>
    </div>
  </div>
  <footer-vue></footer-vue>


</template>



<script>

// window.addEventListener('scroll', () => {

//   const [red, green, blue] = [101, 63, 211]
//   const section1 = document.querySelector(".full-container");
//   let y = 1 + (window.scrollY || window.pageYOffset) / 3000
//   y = y < 1 ? 1 : y // ensure y is always >= 1 (due to Safari's elastic scroll)
//   const [r, g, b] = [red*y, green/y, blue*y]
//   section1.style.background = `rgb(${r}, ${g}, ${b})`

// })
(function($) { "use strict";

	//Switch dark/light

	$(".switch").on('click', function () {
		if ($("body").hasClass("light")) {
			$("body").removeClass("light");
			$(".switch").removeClass("switched");
		}
		else {
			$("body").addClass("light");
			$(".switch").addClass("switched");
		}
	});

	$(document).ready(function(){"use strict";

		//Scroll back to top

		var progressPath = document.querySelector('.progress-wrap path');
		var pathLength = progressPath.getTotalLength();
		progressPath.style.transition = progressPath.style.WebkitTransition = 'none';
		progressPath.style.strokeDasharray = pathLength + ' ' + pathLength;
		progressPath.style.strokeDashoffset = pathLength;
		progressPath.getBoundingClientRect();
		progressPath.style.transition = progressPath.style.WebkitTransition = 'stroke-dashoffset 10ms linear';
		var updateProgress = function () {
			var scroll = $(window).scrollTop();
			var height = $(document).height() - $(window).height();
			var progress = pathLength - (scroll * pathLength / height);
			progressPath.style.strokeDashoffset = progress;
		}
		updateProgress();
		$(window).scroll(updateProgress);
		var offset = 50;
		var duration = 550;
		jQuery(window).on('scroll', function() {
			if (jQuery(this).scrollTop() > offset) {
				jQuery('.progress-wrap').addClass('active-progress');
			} else {
				jQuery('.progress-wrap').removeClass('active-progress');
			}
		});
		jQuery('.progress-wrap').on('click', function(event) {
			event.preventDefault();
			jQuery('html, body').animate({scrollTop: 0}, duration);
			return false;
		})


	});

})(jQuery);


import Conference from "./components/conference";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import headerVue from "../components/header.vue";
import footerVue from "../components/footer.vue"
import { mapGetters, mapMutations } from "vuex";
export default {
  name: "Home",

  components: {
    headerVue,
    Conference,
    footerVue
  },
  computed:{
    ...mapGetters(['fullcontainer','maincontainer'])
  },
  methods:{
    ...mapMutations(['SET_MAIN_CONTAINER','SET_FULL_CONTAINER'])
  },
  setup() {
    const router = useRouter();

    const state = reactive({
      count: 12
    });

    const load = function() {
      state.count += 4;
    };

    const clickConference = function(id) {
      router.push({
        name: "conference-detail",
        params: {
          conferenceId: id
        }
      });
    };

    return { state, load, clickConference };
  }
};
</script>
<style>

@import url('https://fonts.googleapis.com/css?family=Muli:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i&subset=latin-ext,vietnamese');
@import url('https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&subset=latin-ext');



:root {
	font-size: 20px;
	--red: #da2c4d;
	--yellow: #f8ab37;
	--green: #2ecc71;
	--white: #ffffff;
	--grey-light: #f2f7f9;
	--grey: #653FD3;
	--black: #080808;
}

@-webkit-keyframes border-transform{
    0%,100% { border-radius: 63% 37% 54% 46% / 55% 48% 52% 45%; }
	14% { border-radius: 40% 60% 54% 46% / 49% 60% 40% 51%; }
	28% { border-radius: 54% 46% 38% 62% / 49% 70% 30% 51%; }
	42% { border-radius: 61% 39% 55% 45% / 61% 38% 62% 39%; }
	56% { border-radius: 61% 39% 67% 33% / 70% 50% 50% 30%; }
	70% { border-radius: 50% 50% 34% 66% / 56% 68% 32% 44%; }
	84% { border-radius: 46% 54% 50% 50% / 35% 61% 39% 65%; }
}
@keyframes border-transform{
    0%,100% { border-radius: 63% 37% 54% 46% / 55% 48% 52% 45%; }
	14% { border-radius: 40% 60% 54% 46% / 49% 60% 40% 51%; }
	28% { border-radius: 54% 46% 38% 62% / 49% 70% 30% 51%; }
	42% { border-radius: 61% 39% 55% 45% / 61% 38% 62% 39%; }
	56% { border-radius: 61% 39% 67% 33% / 70% 50% 50% 30%; }
	70% { border-radius: 50% 50% 34% 66% / 56% 68% 32% 44%; }
	84% { border-radius: 46% 54% 50% 50% / 35% 61% 39% 65%; }
}
/* body.light .navbar-brand::before{
	opacity: 1;
}
body.light .navbar-brand::after{
	opacity: 0;
} */




/* #Progress
================================================== */

.progress-wrap {
	position: fixed;
	right: 50px;
	bottom: 50px;
	height: 46px;
	width: 46px;
	cursor: pointer;
	display: block;
	border-radius: 50px;
	box-shadow: inset  0 0 0 2px rgba(255,255,255,0.2);
	z-index: 10000;
	opacity: 0;
	visibility: hidden;
	transform: translateY(15px);
	-webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.progress-wrap.active-progress {
	opacity: 1;
	visibility: visible;
	transform: translateY(0);
}
.progress-wrap::after {
	position: absolute;
	font-family: 'unicons';
	content: '↑';
	text-align: center;
	line-height: 46px;
	font-size: 24px;
	color: var(--grey);
	left: 0;
	top: 0;
	height: 46px;
	width: 46px;
	cursor: pointer;
	display: block;
	z-index: 1;
	-webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.progress-wrap:hover::after {
	opacity: 0;
}
.progress-wrap::before {
	position: absolute;
	font-family: 'unicons';
	content: '↑';
	text-align: center;
	line-height: 46px;
	font-size: 24px;
	opacity: 0;
	background-image: linear-gradient(298deg, var(--red), var(--yellow));
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
	left: 0;
	top: 0;
	height: 46px;
	width: 46px;
	cursor: pointer;
	display: block;
	z-index: 2;
	-webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.progress-wrap:hover::before {
	opacity: 1;
}
.progress-wrap svg path {
	fill: none;
}
.progress-wrap svg.progress-circle path {
	stroke: var(--grey);
	stroke-width: 4;
	box-sizing:border-box;
	-webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
body.light .progress-wrap {
	box-shadow: inset  0 0 0 2px rgba(0,0,0,0.2);
}
body.light .progress-wrap::after {
	color: var(--black-blue);
}
body.light .progress-wrap svg.progress-circle path {
	stroke: var(--black-blue);
}
.full-container-coverletter{
  min-height: 100%;
  background-color: #F3F4FF;
}
.main-container-coverletter{
  height: 100%;
  margin-right: 15%;
  margin-left: 15%;
  padding: 5%;
  padding-top: 10%;
  border: 1px;
  background-color: #F3F4FF;
}

.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}
.full-container{
  min-height: 100%;
  background-color: white;
}
/* 컨테이너 */
.main-container {

  /* height: 100%; */
  /* padding: 5%; */
  /* padding-top: 7%; */
  min-height: 100%;
  margin-right: 10%;
  margin-left: 10%;
  padding-top: 10%;
  border: 1px;
  background-color: white;
}
@media (min-width: 701px) and (max-width: 1269px) {
  .infinite-list {
    min-width: 700px;
  }
}

@media (min-width: 1270px) {
  .infinite-list {
    min-width: 1021px;
  }
}

.infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
}
</style>
