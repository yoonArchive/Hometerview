<script>
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ReviewForm from "./components/modal-review.vue";
import ReviewFormEdit from "./components/modal-reviewEdit.vue";
import { mapActions, mapGetters } from "vuex";
// import review from '../main/store/modules/review'
const today = new Date().toLocaleString("ko-kr");

export default {
  props: {
    reviews: Object,
    dday: Object
  },
  components: {
    FullCalendar,
    ReviewForm,
    ReviewFormEdit
  },

  data: function() {
    return {
      reviewdate: "",

      getReviewOk: true,
      currentday: null,
      // fetchReview:{
      //   reviewContents: this.currentReview.reviewContents,
      //   reviewTitle: this.currentReview.reviewTitle,
      //   reviewType: this.currentReview.reviewType,
      //   reviewDate: this.currentReview.reviewDate
      // },

      fetchReview: {},
      newreview: {
        // title: this.review.reviewTitle,
        // content: this.review.reviewContents,
        // reviewNo : this.$route.params.reviewNo,
        reviewContents: this.reviews.reviewContents,
        reviewTitle: this.reviews.reviewTitle,
        reviewType: this.reviews.reviewType,
        reviewDate: today
      },
      id: "",
      reviewmodalE: false,
      reviewmodal: false,
      message: "",
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: "prev,today",
          center: "title",
          // right: "dayGridMonth,timeGridWeek,timeGridDay",
          right: "next"
        },
        initialView: "dayGridMonth",
        // initialEvents , // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
        // locale: "ko",

        buttonText: {
          today: "??????",
          month: "???",
          week: "???",
          day: "??????",
          list: "?????????"
        },

        events: []
      }
    };
  },
  computed: {
    ...mapGetters(["currentReview", "reviewContents", "currentDdays"])
  },
  methods: {
    ...mapActions([
      "getResumeInfo",
      "getReviewInfo",
      "addNewResume",
      "getReviewDetail",
      "createReview1",
      "updateReview",
      "deleteReview1",
      "getDdayInfo",
      "deleteDDAY"
    ]),
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends; // update a property
    },

    handleDateSelect(selectInfo) {
      // let title = prompt('?????????, ????????? ??????????????????')
      // let contents = prompt('????????? ???????????????')
      let calendarApi = selectInfo.view.calendar;
      this.currentday = selectInfo.startStr;
      calendarApi.unselect(); // clear date selection
      let mmax = 0;

      for (const aareview of this.reviews) {
        if (aareview.reviewNo > mmax) {
          mmax = aareview.reviewNo;
        } else {
          continue;
        }
      }
      this.reviewmodal = true;

      // if (title) {
      //   calendarApi.addEvent({
      //     id: mmax+1,
      //     title,
      //     contents,
      //     start: selectInfo.startStr,
      //     end: selectInfo.endStr,
      //     allDay: selectInfo.allDay,
      //     color: 'purple'

      //   })
      //  }

      // const today = new Date().toLocaleString('ko-kr');
      // const newreview= {
      // title: this.review.reviewTitle,
      // content: this.review.reviewContents,
      // reviewNo : this.$route.params.reviewNo,
      //   reviewContents: contents,
      //   reviewTitle: title,
      //   reviewType: 'REAL',
      //   reviewDate: selectInfo.startStr,
      // }
      // if (!newreview.reviewTitle){
      //   alert('????????? ???????????????')

      // }else if (!newreview.reviewContents){
      //   alert('????????? ???????????????')
      // }else{
      //   this.createReview1(newreview)
      // }
      // this.openModal()
    },

    //?????? ???????????? ???
    async handleEventClick(clickInfo) {
      this.id = clickInfo.event.id;

      // this.fetchReview = null
      // if (confirm(`????????????????????????? '${clickInfo.event.title}'`)) {

      if (clickInfo.event.id === "999") {
        const ddayName = clickInfo.event.title;
        console.log("???????????????" + ddayName);
        if (confirm(`????????????????????????? '${clickInfo.event.title}'`)) {
          for (const ddaykim of this.currentDdays) {
            if (ddaykim.ddayTitle === ddayName) {
              const ddayNum = ddaykim.ddayNo;
              console.log("???????????????" + ddayNum);
              this.deleteDDAY(ddayNum);
              // this.getDdayInfo()
              clickInfo.event.remove();
            } else {
              console.log("??????");
              this.getDdayInfo();
            }
          }
        }
      } else {
        await this.getReviewDetail(clickInfo.event.id);
        this.fetchReview = {
          reviewNo: clickInfo.event.id,
          reviewContents: this.currentReview.reviewContents,
          reviewTitle: this.currentReview.reviewTitle,
          reviewType: this.currentReview.reviewType,
          reviewDate: this.currentReview.reviewDate
        };
        // setTimeout(function() {
        //   console.log("Works!");
        // }, 2000);
        this.reviewmodalE = true;
      }
      // else{
      //   this.deleteReview1(clickInfo.event.id);
      //   console.log("????????????");
      //   clickInfo.event.remove();
      //   }
      // }
    },

    handleEvents(events) {
      this.currentEvents = events;
    },

    checkReview() {
      for (const review of this.reviews) {
        this.calendarOptions.events.push({
          id: review.reviewNo,
          title: review.reviewTitle,
          start: review.reviewDate,
          color: "#99CCFF"
          // backgroundColor: "#99CCFF"
        });
      }
      for (const dday of this.dday) {
        this.calendarOptions.events.push({
          id: 999,
          title: dday.ddayTitle,
          start: dday.ddayDate,
          color: "#9c7bfd "
        });
      }
    }
  },

  // updated() {
  //   this.checkReview();
  // },
  created() {
    this.checkReview();
  }
};
</script>

<template>
  <div class="demo-app">
    <!-- <div class="demo-app-sidebar">
      <div class="demo-app-sidebar-section">
        <h2>??????</h2>
        <ul>
          <li>????????? ???????????? ????????? ???????????????</li>
          <li>???????????? DDAY, ???????????? ???????????????.</li>
          <li>???????????? ????????? ????????? ?????????</li>
        </ul>
      </div>
      <div class="demo-app-sidebar-section">
        <label>
          <input
            type="checkbox"
            :checked="calendarOptions.weekends"
            @change="handleWeekendsToggle"
          />
          ?????? ??????
        </label>
      </div>
      <div class="demo-app-sidebar-section">

        <h2>??????</h2>
        <ul>
          <li v-for="event in currentEvents" :key="event.id">
            <b>{{ event.startStr }}</b>
            <b>{{ event.endStr }}</b>
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div> -->
    <div class="demo-app-main">
      <FullCalendar class="demo-app-calendar" :options="calendarOptions">
        <template v-slot:eventContent="arg">
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </div>
  </div>

  <!--??????-->

  <ReviewForm
    v-if="reviewmodal"
    @close="reviewmodal = false"
    :currentday="this.currentday"
    action="create"
  >
    <!--??????????????? ??? -->
  </ReviewForm>

  <ReviewFormEdit
    v-if="reviewmodalE"
    @close="reviewmodalE = false"
    :fetchReview="this.fetchReview"
    :currentday="this.currentday"
    :id="this.id"
  >
    <!--??????????????? ??? -->
  </ReviewFormEdit>
</template>

<style scoped lang="css">
@import "./main.css";
h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b {
  /* used for event dates/times */
  margin-right: 3px;
}
a {
  text-decoration: none;
}
.demo-app {
  display: flex;
  min-height: 100%;
  /* font-family: Arial, Helvetica, Helvetica, sans-serif; */
  font-size: 12px;
  font-family: "????????? ????????????";
  width: 900px;
  color: black;
  text-decoration: none !important;
}
.fc-col-header-cell-cushion {
  color: #653fd3;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
  /* font-weight: bold; */
  /* color: #653fd3; */
  font-weight: 600;
  /* text-decoration: none;
  color: black; */
}

.fc {
  /* the calendar root */
  max-width: 1500px;
  margin: 0 auto;
}
</style>
