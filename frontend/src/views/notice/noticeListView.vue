<template>
    <!-- table -->

    <h1 id="title">저희의 신규 업데이트 소식을 알려드릴께요!</h1>

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
        <div class="buttonbundle">
          <button id="button-review">
          <router-link class="routerlink" :to="{ name: 'noticeNew' }" >
            <p id="a">작성하기</p>
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
      ...mapGetters(['notices', 'isLoggedIn'])
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
  /* display : flex; */
  /* justify-content: center;
  align-content: center; */
}
/* .table{
  color: rgb(194, 48, 51);
  background-color: white !important;
  margin : auto;
  justify-content: center;
  align-content: center;

}

.table-head{
  background-color: rgb(236, 157, 56) !important;
  color : rgb(241, 241, 241);
  text-decoration-line: none;

}
.buttonbundle{
  float: right;

}
.makenewbutton{
  margin-top:15px;
} */

#a{
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
  margin: 2px;
}
#button-review{
   background-color: #653FD3;
   border-color : #653FD3;
   border-radius: 10%;
   margin: 15px;
   width: 100px;
   text-align: center;

}
</style>
