<template>
  <div class="wrap">
    <div class="notice">
      <div class="notice-top">
        <!-- {{notice}} -->
        <p>번호 {{notice.noticeNo}} </p>
        <p>제목 {{notice.noticeTitle}} </p>
        <p>작성자 : 관리자</p>
      </div>
      <div class="notice-content">
        <h1>내용 {{notice.noticeContents}}</h1>
      </div>
      <div class="notice-bottom">
        <!-- <div class="component1" v-if="isAuthor"> -->
          <span class="edit">
            <router-link :to="{ name: 'noticeEdit', params: { noticeNo } }">
              <button>Edit</button>
            </router-link>
          </span>
          <span class="delete">
            <button @click="deleteNotice(noticeNo)">Delete</button>
          </span>
        </div>
      </div>
    </div>
  <!-- </div> -->
      <div>
        <router-link class="routerlink" :to="{ name: 'notices' }" id="a">
            <button>목록으로</button>
          </router-link>
      </div>

</template>

<script>

import { mapGetters, mapActions } from 'vuex'

  export default {
    name: 'noticeDetail',
    components: {  },
    data() {
      return {
        noticeNo: this.$route.params.noticeNo,
        // notice: useStore.state.notice,
        animating: false
      }
    },
    computed: {
      ...mapGetters(['isAuthor', 'notice']),

    },
    methods: {
      ...mapActions([
        'fetchNotice',
        'deleteNotice',
      ]),

    onIconAnimationEnds() {
      this.animating = false;
    },
    },
    created() {
      this.fetchNotice(this.noticeNo)
    },
    mounted(){
      console.log('주소번호'+this.$route.params.noticeNo)



    }
  }
</script>

<style scoped >

</style>
