<template>
    <!-- table -->
    <h2 id="title">저희의 신규 <p id="aa">업데이트</p> 소식을 알려드릴게요.</h2>
    <div class="table-cocover">
      <div class="table-cover">
        <table class="table table-hover">
          <thead class="table-head">
            <tr>
              <th scope="col" v-for="(header,idx) in headers" :key="idx"> {{ header }}</th>
            </tr>
          </thead>
          <tbody>
              <!-- {{notices}} -->
            <tr v-for="notice in notices.notices" :key="notice.noticeNo" >
              <th scope="row" >
                <td>{{ notice.noticeNo }}</td></th>
              <!-- {{ notice[noticeTitle] }} -->
              <th >
                <td>
                  <router-link style="text-decoration:none"
                    :to="{ name: 'notice', params: {noticeNo: notice.noticeNo} }">
                    {{ notice.noticeTitle }}
                    </router-link>
                </td></th>
              <th>
                <td>{{ timeForToday(notice.writeDate) }}</td>
              </th>
              <th>
                <td>관리자</td>
              </th>
            </tr>
          </tbody>
        </table>
        <div class="buttonbundle" v-if="isAdmin">
          <button  class="createBtn" >
            <router-link class="routerlink" :to="{ name: 'noticeNew' }" >
              작성하기
            </router-link>
          </button>
        </div>
      </div>
    </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'NoticeList',
    data(){
      return {
        headers: ['번호','제목', '작성시간', '작성자']
      }
    },
    computed: {
      ...mapGetters(['notices', 'isLoggedIn','isAdmin'])
    },
    methods: {
      ...mapActions(['fetchNotices']),

      timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금 전';
        if (betweenTime < 60) {
            return `${betweenTime}분 전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간 전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일 전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년 전`;
 }
    },
    created() {
      this.fetchNotices()
      console.log('김')
    },
  }
</script>

<style scoped>
.table-cocover{
  display : flex;
  justify-content: center;
  align-items: center;
}
.table-cover{
  width : 80%;
  padding-top : 50px;
}
#aa{
  display: inline;
  font-family: "티머니 둥근바람";
  color: #653FD3;
}
#title{
  font-family: "티머니 둥근바람";
  text-align: center;
  margin-top: 10px;
}
.createBtn {
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  border-radius: 5px;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  background-color: #ffffff;
  color: #653fd3;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 600;
  height: 3em;
  width: 7em;
  letter-spacing: 0.15em;
  text-align: center;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.createBtn:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
.createBtn:active {
  background-color: rgba(161, 104, 253, 0.15);
}
a{
  text-decoration: none;
  color: #653fd3;
}
</style>
