<template>
   <transition name="modal">
   <div class="modal-mask">
    <div class="modal-wrapper">
     <div class="modal-container">


      <div class="modal-header">
       <slot name="header">
        DDAY 를 설정해보세요
        <button @click="checkdday()"></button>
       </slot>
      </div>


      <div class="modal-body">
       <slot name="body">
          <input type="text" v-model="ddays.ddayTitle" required>
          <input type="date" v-model="ddays.ddayDate" required>
       </slot>
      </div>


      <div class="modal-footer">
       <slot name="footer">
        <button class="modal-default-button" @click="updateDday([dday.ddayNo, ddays]), $emit('close')">수정하기</button>

        <button class="modal-default-button" @click="$emit('close')">

         닫기</button>
        {{this.ddaykim}}
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
  props:{
    dday: Object,
    key: Number
  },
  data(){
    return {
      ddays:{
      ddayDate:this.dday.ddayDate,
      ddayTitle:this.dday.ddayTitle


},
  ddaykim:{

      }


    }

  },
  computed:{
    ...mapGetters(['currentDdays'])
  },
  methods: {
    ...mapActions([
      "createDday",
      "updateDday"

      ]),

    checkModal(){
      if (this.dday.ddayNo != this.key){
        this.$emit('close')

      } },

    checkdday() {
      for (aa of currentDdays){
        if (aa.ddayNo ===this.key ){
          this.ddayKim = aa
        }
      }console.log('kim')
    },




  mounted() {
    console.log('밑에 디데이'+this.dday)
    console.log('키' + this.key)
    this.checkModal()

  },
  created(){
    console.log('폼생성')
    this.checkModal()
  }
}}
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
