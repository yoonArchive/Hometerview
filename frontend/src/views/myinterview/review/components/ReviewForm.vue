<!--
<template>
  <div class="container">


    <div class="inner-container">
    <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="title" class="form-label" >제목: </label>
      <input type="text" class="form-control" v-model="newreview.reviewTitle" id="title" />
    </div>
    <div class="mb">
      <label for="exampleFormControlTextarea1" class="form-label">내용: </label>
      <textarea class="form-control" v-model="newreview.reviewContents" type="text" id="content" rows="3"></textarea>
    </div>
    <div>
        <input type="radio" name="reviewType" value="REAL" v-model="newreview.reviewType">
        <label for="newreview.reviewType">REAL</label>
        <input  type="radio" name="reviewType" value="FAKE" v-model="newreview.reviewType">
        <label for="newreview.reviewType">FAKE</label>
    </div>



    <div>
      <input v-model="newreview.reviewDate" type="date" name="reviewDate" >
    </div>
    <div>
      <input type="radio" name="reviewType" value="REAL" v-model="newreview.reviewType">
      <label for="newreview.reviewType">REAL</label>
      <input  type="radio" name="reviewType" value="FAKE" v-model="newreview.reviewType">
      <label for="newreview.reviewType">FAKE</label>
    </div>


    <div class="buttonbundle">
        <button id="button-review" v-if="action==='create'">만들기</button>
        <button id="button-review" v-else>수정하기</button>
    </div>
     </form>
    </div>

  </div>

</template>

<script>
import { mapActions } from 'vuex'
// import review from '../../../main/store/modules/review'
  const day1 = new Date();
  const today = day1.toLocaleString('ko-kr');

  //.toLocaleString('ko-kr')
  export default {
    name: 'reviewForm',
    props: {
      review: Object,
      action: String
    },
    data() {
      return {
        newreview: {
          // title: this.review.reviewTitle,
          // content: this.review.reviewContents,
          // reviewNo : this.$route.params.reviewNo,
          reviewContents: this.review.reviewContents,
          reviewTitle: this.review.reviewTitle,
          reviewType: this.review.reviewType,
          reviewDate: this.review.reviewDate
          }




      }
    },

    methods: {
      ...mapActions(['createReview', 'updateReview']),
      onSubmit() {
        if (this.action === 'create') {
          // this.action = '작성하기'
          this.createReview(this.newreview)
        } else if (this.action === 'update') {
          const payload = {
            reviewNo: this.review.reviewNo,
            ...this.newreview,
          }
          this.updateReview(payload)
        }
      },
    },


  }
</script>

<style scoped>

.form-control:focus{
  border: solid 3px #856ccf;
  outline: solid 1px #9376e9;
}
.container{
  display: flex;
  width: 100%;
}
#button-review{
   background-color: #653FD3;
   border-color : #653FD3;
   border-radius: 10%;
   margin: 15px;
   width: 100px;
   color: white;
}
.inner-container{
  flex-direction: column;
  width: 100%;
  height: 100%;
}
</style> -->


<template>
   <transition name="modal">
   <div class="modal-mask">
    <div class="modal-wrapper">
     <div class="modal-container">


      <div class="modal-header">
       <slot name="header">
        회고록을 작성해 보세요
       </slot>
      </div>


      <div class="modal-body">
       <slot name="body">

    <!-- <form @submit.prevent="onSubmit"> -->
      <div class="mb-3">
        <label for="title" class="form-label" >제목: </label>
        <input type="text" class="form-control" v-model="newreview.reviewTitle" id="title" />
      </div>
      <div class="mb">
        <label for="exampleFormControlTextarea1" class="form-label">내용: </label>
        <textarea class="form-control" v-model="newreview.reviewContents" type="text" id="content" rows="3"></textarea>
      </div>

      <div>
        <input v-model="newreview.reviewDate" type="date" name="reviewDate" required>
      </div>

      <div>
        <input type="radio" name="reviewType" value="REAL" v-model="newreview.reviewType" required>
        <label for="newreview.reviewType">REAL</label>
        <input  type="radio" name="reviewType" value="FAKE" v-model="newreview.reviewType" required>
        <label for="newreview.reviewType">FAKE</label>
      </div>
       <button id="button-review"   v-if="action==='create'" @click="$emit('close'), createReview(this.newreview)">만들기</button>
      <button id="button-review" v-else  @click="$emit('close'), updateReview(this.newreview)">수정하기</button>
        <!-- </form> -->

        </slot>
        </div>


      <div class="modal-footer">
       <slot name="footer">

        <button class="modal-default-button" @click="$emit('close')">

         닫기</button>

       </slot>
      </div>
     </div>
    </div>
   </div>
  </transition>
</template>


<script>


import { mapActions, mapGetters } from 'vuex'
export default {
  props: {
      review: Object,
      action: String
    },
    data() {
      return {
        newreview: {
          // title: this.review.reviewTitle,
          // content: this.review.reviewContents,
          reviewNo : this.$route.params.reviewNo,
          reviewContents: this.review.reviewContents,
          reviewTitle: this.review.reviewTitle,
          reviewType: this.review.reviewType,
          reviewDate: this.review.reviewDate
          }




      }
    },


  compute: {

    ...mapGetters(['currentReview'])
  },
  methods: {

      ...mapActions(['createReview', 'updateReview','getReviewDetail']),
      onSubmit() {
        if (this.action === 'create') {
          // this.action = '작성하기'
          this.createReview(this.newreview)
        } else if (this.action === 'update') {
          const payload = {
            reviewNo: this.review.reviewNo,
            ...this.newreview,
          }
          this.updateReview(payload)
        }
      },

      created(){
        getReviewDetail(this.review.reviewNo)
      }
  },



}
</script>


<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}


.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
  color:#555555;
}


.modal-container {
  width: 400px;
  height: auto;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}


.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}


.modal-body {
  margin: 20px 0;
}


.modal-default-button {
  float: right;
}


/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */


.modal-enter {
  opacity: 0;
}


.modal-leave-active {
  opacity: 0;
}


.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>

