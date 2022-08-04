<template>
<div class="container">
  <div class="review-1">
    <div>자소서 개수 {{resumeContents.length}}</div>
    <button @click="addResume" id="cover-Pbutton">+</button>
    <button id="cover-Mbutton">-</button>
    <div v-for="(item, index) in resumeContents.length" :key="index">
      <router-link :to="{name : 'coverletter', params : {'resumeindex' : item - 1}}" id="cover-router">
        <div>{{resumeContents[item-1].resumeTitle}}</div>
      </router-link>
    </div>
  </div>
  <!-- <div>{{ reviewContents }}</div> -->
  <!-- <h1>{{numberOfReview}}</h1> -->
  <div class="review-2">
    <h1>회고록</h1>

      <table class="table">
          <thead class="table-head">
            <tr>
              <th scope="col" v-for="(header,idx) in headers" :key="idx"> {{ header }}</th>
            </tr>
          </thead>
          <tbody class="t-body">
            <tr v-for="(reviews, index) in reviewContents" :key="index" >
              <th scope="row" >
                <td>{{ reviews.reviewNo }}</td>
              </th>
              <th>
                <td>
                   <router-link :to="{name : 'review', params : {'reviewNo' : reviews.reviewNo}}">
                      {{reviews.reviewTitle}}
                  </router-link>
                </td>
              </th>
              <th>
                <td>{{ reviews.reviewDate}}</td>
              </th>
              <th>
                <td><p v-if="reviews.reviewType==='FAKE'">혼자</p>
                    <p v-else>단체</p>
                </td>
              </th>
            </tr>
          </tbody>
        </table>



    <div class="buttonbundle">
      <button id="button-review">
        <router-link class="routerlink" :to="{ name: 'reviewNew' }">
          <p id="a">새로 만들기</p>
        </router-link>
      </button>
  </div>
  </div>

  <Fullcalendar v-bind:options="options"/>
  </div>

</template>
<script setup>
import {ref, reactive, watch} from 'vue'
import '@fullcalendar/core/vdom'
import Fullcalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import listPlugin from '@fullcalendar/list'
import interactionPlugin from '@fullcalendar/interaction'
import useEvents from './useEvent'
// import '@fullcalendar/core/main.css';
// import '@fullcalendar/daygrid/main.css';



const { getEvents, createEvent, updateEvent, deleteEvent } = useEvents()
mapActions(['getReviewInfo', 'getReviewDetail','createReview','updateReview','deleteReview'])

const id= ref(10)
const options = reactive({
  plugins: [dayGridPlugin,timeGridPlugin,listPlugin,interactionPlugin],
  initialView: 'dayGridMonth',
  headersToolbar: {
    left: 'prev, next, today',
    center: 'title',
    right: 'dayGridMonth, dayGridWeek, listDay'

  },
  editable: true,
  selectable: true,
  weekends: true,
  select: (arg) => {

    console.log('arg')
    id.value = id.value +1

    const cal = arg.view.calendar
    cal.unselect()
    cal.addEvent({
      id: `${id.value}`,
      title: `New event ${id.value}`,
      start: arg.start,
      end: arg.end,
      allDay:true
    })
  },
  eventClick: (arg) =>{
    console.log(arg.event.title)
  },
  events: [],
  eventAdd: (arg) => {
    createEvent({
      // id: arg.event.id,
      reviewTitle: arg.event.title,
      // start: arg.event.start,
      // end: arg.event.end,
      reviewDate: arg.event.allDay
    })
  },
  eventChange: (arg) => {
    updateEvent({
      id: arg.event.id,
      title: arg.event.title,
      start: arg.event.start,
      end: arg.event.end,
      allDay: arg.event.allDay
    }, arg.event.id)
  },
  eventRemove: (arg) => {
    deleteEvent(arg.event.id)
  },

})
options.events = getEvents.value
watch(getEvents, () => {
  options.events = getEvents.value
})
</script>



<script>

import { mapActions, mapGetters } from 'vuex'
// import { assertTSMethodSignature } from '@babel/types'

export default {
  components:{
    },
  data(){
    return {
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin ],
        initialView: 'dayGridMonth',
        dateClick: this.handleDateClick,
        events: [
          { title: 'event 1', date: '2019-04-01' },
          { title: 'event 2', date: '2019-04-02' }
        ]
      },



      roomName : '',
      headers: ['번호','제목', '작성시간', '유형']
    }
  },
  computed:{
    ...mapGetters(['resumeContents','numberOfResume', 'reviewContents','numberOfReview']),
  },
  methods:{
    handleDateClick: function(arg) {
      alert('date click! ' + arg.dateStr)
    },
    ...mapActions([
      "getResumeInfo",
      "getReviewInfo",
      "addNewResume"



      ]),
    findresumes(){
      this.getResumeInfo();
    },
    addResume(){
      this.addNewResume();
    },

    findreveiw(){
      this.getReviewInfo();
    },
    onDateClick (payload) {
      const title = prompt('Please enter a new title for your event')

      if (!title) {
        return
      }

      const id = (this.events.length + 1) * 10
      const { start, end, date, allDay } = payload

      return this.createEvent({
        id,
        title,
        date,
        start,
        end,
        allDay
      })
    },

    onDateSelect (payload) {
      return this.onDateClick(payload)
    },

    onEventClick ({ event }) {
      const confirmed = confirm(`Are you sure you want to delete the event '${event.title}'?`)

      if (!confirmed) {
        return
      }

      return this.deleteEvent(event.id)
    },

    onEventDrop ({ event }) {
      return this.updateEvent(event)
    }

  },
  mounted(){
    this.findresumes();
    this.findreveiw();
  }
}
</script>

<style scoped>

  @import './fullcalander/main.css';
  /* @import './fullcalander/main.js'; */

#cover-router{
  text-decoration-line: none;
}
.review-1{
  margin-top: 100px;
}
.review-2{
  margin-top: 100px;
}
.conatiner{

  display: flex;
  flex-direction: column;
}
#button-review{
   width: 100px;
   height: 50px;
   background-color: #653FD3;
   border-color : #653FD3;
   border-radius: 10%;
   margin: 5px;

}
#button-review:hover{

}
#a {
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
  margin: 3px;
}
.buttonbundle{
  flex-direction: row-reverse;
  margin: 10px;
  width: 100px;

}
.table{
  text-align: center;
  padding: 10px;
}
th {
    text-align: left;
  }
/* td {
    text-align: right;
  } */


  /* 달력 스타일 */
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
}

.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
</style>
