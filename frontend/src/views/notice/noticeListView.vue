<template>
    <!-- table -->
    <div class="table-cocover">
      <div class="table-cover">
        <table class="table table-hover">
          <thead class="table-head">
            <tr>
              <th scope="col" v-for="(header,idx) in headers" :key="idx"> {{ header }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="notice in notices" :key="notice.no" >
              <!-- <th scope="row" >
                <td>{{ notice.user.username }}</td></th> -->
              <th scope="row">
                <td>
                  <router-link style="text-decoration:none"
                    :to="{ name: 'notice', params: {noticeno: notice.no} }">
                    {{ notice.noticeTitle }}
                    </router-link>
                </td></th>
              <th scope="row">
                <td>{{ timeForToday(notice.writeDate) }}</td></th>
            </tr>
          </tbody>
        </table>
        <div class="buttonbundle">
          <router-link class="routerlink" :to="{ name: 'noticeNew' }">
            <b-button class="makenewbutton" squared>새로 만들기</b-button>
          </router-link>
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
        headers: ['작성자', '제목', '작성시간']
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
</style>
