<template>

  <!-- <form @submit.prevent="onSubmit">
    <div class="wrap">
      <div class="notice">
        <div class="notice-top">
          <label class="col-sm-2" for="title">제목: </label>
          <input class="col-sm-10" v-model="newnotice.noticeTitle" type="text" id="title" />
        </div>
        <div class="notice-content">
          <textarea class="col-sm-12 notice-textarea" v-model="newnotice.noticeContents" type="text" id="content"></textarea>
        </div>
        <div class="form-button">
           <button>{{ action }}</button>
        </div>
      </div>
    </div>
  </form> -->


    <div class="container">
    <div class="title">
      <h1>공지사항 작성</h1>
    </div>
    <div class="inner-container">
    <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="title" class="form-label col-sm-2" >제목: </label>
      <input type="text" class="form-control has-success col-sm-10" v-model="newnotice.noticeTitle" id="title" />
    </div>
    <div class="mb-3">
      <label for="exampleFormControlTextarea1" class="form-label"></label>
      <textarea class="form-control col-sm-12"  v-model="newnotice.noticeContents" type="text" id="content" rows="3"></textarea>
    </div>
    <div class="buttonbundle">
        <button id="button-review" v-if="action==='create'"><p id="a">작성하기</p></button>
        <button id="button-review" v-else><p id="a">수정하기</p></button>
        <button id="button-review">
          <router-link class="routerlink" :to="{ name: 'notices' }" >
            <p id="a">목록</p>
          </router-link>
        </button>
    </div>


     </form>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'

  export default {
    name: 'noticeForm',
    props: {
      notice: Object,
      action: String,
    },
    data() {
      return {
        newnotice: {
          // title: this.notice.noticeTitle,
          // content: this.notice.noticeContents,
          noticeNo : this.$route.params.noticeNo,
          noticeContents: this.notice.noticeContents,
          noticeTitle: this.notice.noticeTitle,
          writeDate: ''
                  },


      }
    },

    methods: {
      ...mapActions(['createNotice', 'updateNotice']),
      onSubmit() {
        if (this.action === 'create') {
          // this.action = '작성하기'
          this.createNotice(this.newnotice)
        } else if (this.action === 'update') {
          const payload = {
            noticeNo: this.notice.noticeNo,
            ...this.newnotice,
          }
          this.updateNotice(payload)
        }
      },
    },

  }
</script>

<style scoped>


.container{
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 50%;

}
.inner-container{
  padding-top: 100px;
  height: 50%;
}
.title{
  display: flex;
  justify-content: center;
  margin-top: 50px;
}

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

.form-control{
  background-color: #653FD3;
}
</style>
