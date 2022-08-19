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
                <label for="title" class="form-label">제목: </label>
                <input
                  type="text"
                  class="form-control"
                  v-model="newreview.reviewTitle"
                  id="title"
                />
              </div>
              <div class="mb">
                <label for="exampleFormControlTextarea1" class="form-label"
                  >내용:
                </label>
                <textarea
                  class="form-control"
                  v-model="newreview.reviewContents"
                  type="text"
                  id="content"
                  rows="3"
                  required
                ></textarea>
              </div>

              <div>
                <input
                  class="form-control"
                  v-model="newreview.reviewDate"
                  type="date"
                  name="reviewDate"
                  required
                />
              </div>

              <div>
                <input
                  type="radio"
                  name="reviewType"
                  value="REAL"
                  v-model="newreview.reviewType"
                  class="form-check-input"
                  required
                />

                <label for="newreview.reviewType">실제</label>
                <input
                  type="radio"
                  name="reviewType"
                  value="FAKE"
                  v-model="newreview.reviewType"
                  class="form-check-input"
                  required
                />
                <label for="newreview.reviewType">모의</label>
              </div>
              <!-- <button
                class="btn btn-outline-primary"
                v-if="action === 'create'"
                @click="$emit('close'), createReview(this.newreview)"
              >
                만들기
              </button>
              <button
                class="btn btn-outline-primary"
                v-else
                @click="$emit('close'), updateReview(this.newreview)"
              >
                수정하기
              </button>
              <button class="btn btn-outline-primary" @click="$emit('close')">
                닫기
              </button> -->
              <!-- </form> -->
            </slot>
          </div>
          <div class="modal-footer">
            <slot name="footer">
              <button
                class="btn btn-outline-primary"
                v-if="action === 'create'"
                @click="$emit('close'), createReview(this.newreview)"
              >
                만들기
              </button>
              <button
                class="btn btn-outline-primary"
                v-else
                @click="$emit('close'), updateReview(this.newreview)"
              >
                수정하기
              </button>
              <button
                id="button-review"
                class="btn btn-outline-primary"
                @click="$emit('close'), deleteReview1(this.id)"
              >
                삭제하기
              </button>
              <button class="btn btn-outline-primary" @click="$emit('close')">
                닫기
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: {
    review: Object,
    action: String,
    currentday: Date,
    fetchReview: Object,
    id: Number
  },
  data() {
    return {
      newreview: {
        // title: this.review.reviewTitle,
        // content: this.review.reviewContents,
        // reviewNo : this.$route.params.reviewNo,
        // reviewContents: this.currentReview.reviewContents,
        // reviewTitle: this.currentReview.reviewTitle,
        // reviewType: this.currentday,
        // reviewDate: this.currentReview.reviewDate

        reviewNo: this.fetchReview.reviewNo,
        reviewContents: this.fetchReview.reviewContents,
        reviewTitle: this.fetchReview.reviewTitle,
        reviewType: this.fetchReview.reviewType,
        reviewDate: this.fetchReview.reviewDate
      }
    };
  },

  compute: {
    ...mapGetters(["currentReview"])
  },
  methods: {
    ...mapActions([
      "createReview",
      "updateReview",
      "getReviewDetail",
      "deleteReview1"
    ]),
    onSubmit() {
      if (this.action === "create") {
        // this.action = '작성하기'
        this.createReview(this.newreview);
      } else if (this.action === "update") {
        const payload = {
          reviewNo: this.review.reviewNo,
          ...this.newreview
        };
        this.updateReview(payload);
      }
    },

    created() {
      getReviewDetail(this.key);
      console.log("날짜" + this.currentday);
    }
  }
};
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
  color: #555555;
}

.modal-container {
  width: 400px;
  height: auto;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
  border-radius: 20px;
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
.btn-outline-primary {
  --bs-btn-color: #9c7bfd;
  --bs-btn-border-color: #9c7bfd;
  --bs-btn-hover-color: #fff;
  --bs-btn-hover-bg: #9c7bfd;
  --bs-btn-hover-border-color: #9c7bfd;
  --bs-btn-focus-shadow-rgb: 13, 110, 253;
  --bs-btn-active-color: #fff;
  --bs-btn-active-bg: #9c7bfd;
  --bs-btn-active-border-color: #9c7bfd;
  --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
  --bs-btn-disabled-color: #9c7bfd;
  --bs-btn-disabled-bg: transparent;
  --bs-btn-disabled-border-color: #9c7bfd;
  --bs-gradient: none;
  border-width: 0.23vh;
}
.form-check-input:checked {
  background-color: #6a46d1;
  border-color: #6a46d1;
}
</style>
