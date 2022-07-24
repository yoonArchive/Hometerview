<template>

  <form @submit.prevent="onSubmit">
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
  </form>
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
.wrap{  width: 70vw; height: 50vh; margin: auto; padding-top : 30px;}
.notice{ display: flex; border: 1px solid; flex-direction: column; width: auto; height:100%; font-family : '엘리스디지털regular';}
.notice-top{ border : 1px solid; background-color: white; padding: 10px; font-weight: bold;}
.notice-content{padding: 20px; color:white;}
.notice-textarea{resize : none; background-color: white; color: black}
.form-button{ margin-left : auto; margin-top : auto;}
#content{
  height : 250px;
}
</style>
