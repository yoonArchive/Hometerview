<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS } from './event-utils'
import ReviewForm from './review/components/ReviewForm.vue'
import { mapActions, mapGetters } from 'vuex'
// import review from '../main/store/modules/review'
const today = new Date().toLocaleString('ko-kr');

export default {
  props:{
    reviews: Object,
    dday : Object
  },
  components: {
    FullCalendar,
    ReviewForm

  },

  data: function() {
    return {
      getReviewOk: true,
      newreview: {
          // title: this.review.reviewTitle,
          // content: this.review.reviewContents,
          // reviewNo : this.$route.params.reviewNo,
          reviewContents: this.reviews.reviewContents,
          reviewTitle: this.reviews.reviewTitle,
          reviewType: this.reviews.reviewType,
          reviewDate: today
          },
      modal: false,
      showReviewForm: false,
      message: '',
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin, // needed for dateClick

        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
        locale: 'ko',
        buttonText: {
          today:    '오늘',
          month:    '월',
          week:     '주',
          day:      '일정',
          list:     '리스트'
          },

        events:[],

      }}},
   computed:{
    ...mapGetters(['currentReview',
    'reviewContents'

    ]),
  methods: {
    ...mapActions([
      "getResumeInfo",
      "getReviewInfo",
      "addNewResume",
      "getReviewDetail",
      "createReview1",
      "updateReview",
      "deleteReview1",
      "getDdayInfo"


      ]),
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    handleDateSelect(selectInfo) {
      let title = prompt('이벤트, 회고를 작성해보세요')
      let contents = prompt('내용을 입력하세요')
      let calendarApi = selectInfo.view.calendar
      this.showReviewForm = true
      calendarApi.unselect() // clear date selection
      let mmax = 0
      for (const aareview of this.reviews){
        if (aareview.reviewNo > mmax){
          mmax=aareview.reviewNo
        }else{
          continue
        }

      }
      console.log('mmax는'+mmax)
      if (title) {
        calendarApi.addEvent({
          id: mmax+1,
          title,
          contents,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay,
          color: 'purple'

        })
       }


      // const today = new Date().toLocaleString('ko-kr');
      const newreview= {
        // title: this.review.reviewTitle,
        // content: this.review.reviewContents,
        // reviewNo : this.$route.params.reviewNo,
        reviewContents: contents,
        reviewTitle: title,
        reviewType: 'REAL',
        reviewDate: selectInfo.startStr,
      }
      if (newreview.reviewTitle.length === 0){
        alert('제목을 입력하세요')


      }else if (newreview.reviewContents.length === 0){
        alert('내용을 입력하세요')
      }else{
        this.createReview1(newreview)
      }
      // this.openModal()


    },

    handleEventClick(clickInfo) {
      if (confirm(`삭제하시겠습니까? '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()

        this.deleteReview1(clickInfo.event.id)
        console.log('삭제완료')


      }},


    handleEvents(events) {
      this.currentEvents = events
    },

    doSend() {
      if (this.newreview.reviewTitle.length > 0) {
        alert(this.newreview.reviewTitle)
        this.newreview.reviewTitle= ''
        this.closeModal()
      } else {
        alert('제목을 입력해주세요.')
      }},
    checkReview(){
      for (const review of this.reviews){
        this.calendarOptions.events.push({
          id: review.reviewNo,
          title: review.reviewTitle,
          start: review.reviewDate,
          color: 'purple'

        })





        console.log('콘솔 로그 리뷰 '+review.reviewTitle)

      }
      for ( const dday of this.dday){
        this.calendarOptions.events.push({
          id: 999,
          title: dday.ddayTitle,
          start: dday.ddayDate,
          color: 'red'
        })
      console.log('콘솔 로그 리뷰 '+ dday.ddayTitle)
      }

    },
  },


  beforeMount() {
    // this.checkReview()
  },


}}


</script>

<template>

  <div class='demo-app'>
    <div class='demo-app-sidebar'>
      <div class='demo-app-sidebar-section'>
        <h2>소개</h2>

        <button v-if="getReviewOk" @click="checkReview(), getReviewOk=false">회고 가져오기</button>
        <ul>
          <li>날짜를 선택하고 회고를 작성하세요</li>
          <li>드래그해서 기간을 설정해보세요</li>
          <li>클릭해서 회고를 삭제해 보세요</li>
        </ul>
      </div>
      <div class='demo-app-sidebar-section'>
        <label>
          <input
            type='checkbox'
            :checked='calendarOptions.weekends'
            @change='handleWeekendsToggle'
          />
          주말 포함
        </label>
      </div>
      <div class='demo-app-sidebar-section'>
        <!-- <h2 v-if="(currentEvents.length) ">모든 이벤트({{ currentEvents.length }})</h2>
        <h2 v-else>이벤트 없음</h2> -->
        <h2>일정</h2>
        <ul>
          <li v-for='event in currentEvents' :key='event.id'>
            <b>{{ event.startStr }}</b>
            <b>{{ event.endStr}}</b>
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div>
    <div class='demo-app-main'>
      <FullCalendar
        class='demo-app-calendar'
        :options='calendarOptions'
      >
        <template v-slot:eventContent='arg'>
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </div>
  </div>


<!--모달-->
 <!-- <button id="button-review" @click="showReviewForm = true">작성하기</button> -->

       <ReviewForm v-if="showReviewForm" @close="showReviewForm = false" :review="reviewContents" action="create">
        <h3 slot="header">회고록작성 폼</h3>
        </ReviewForm>
</template>

<style lang='css'>

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

b { /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
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
  color: #653FD3;
  font-weight:600;

}

.fc { /* the calendar root */
  max-width: 1500px;
  margin: 0 auto;
}

</style>
