<template>
  <div class="container">
    <div id="myaccount-title"><h2>회원 탈퇴</h2></div>
    <div id="inner-myaccount">
      <div id="text">
        <h4>
          계정 삭제 시 프로필 및 개인 이용 기록이 삭제됩니다.
        </h4>
        <br />
        <h4>
          탈퇴 후 회원님의 이용정보가 삭제되어 복구 불가능합니다. 회원정보 및
          개인 이용기록은 모두 삭제되며, 삭제된 데이터는 복구 불가능합니다.
        </h4>
        <br />
      </div>
      <div id="inner-myaccount2">
        <form @submit.prevent>
          <input
            id="checkbox"
            type="checkbox"
            name="withdrawal-agree"
            v-model="withdrawalAgree"
          />
          <!-- <input type="button" @click="this.deleteaccount" /> -->
        </form>
        <div>
          계정을 삭제하면 되돌릴 수 없으며, 삭제한 데이터를 복구할 수 없음을
          이해했습니다.
        </div>
      </div>
    </div>
    <button id="buttonDelete" @click="this.deleteaccount">계정삭제</button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      withdrawalAgree: false
    };
  },

  methods: {
    ...mapActions(["deleteUser"]),
    deleteaccount() {
      if (this.withdrawalAgree == true) {
        console.log("???");
        alert("???");
        this.deleteUser();
      } else {
        console.log("???");
        alert("동의를 눌러주세요");
      }
    }
  }
};
</script>

<style scoped>
#inner-myaccount {
  border: 0.1px solid rgb(134, 132, 132);
  /* width: 200%; */
  width: auto;
  height: 300%;
  padding: 30px;
  border-color: rgb(134, 132, 132);
  border-radius: 10px;
}

input[type="checkbox"] {
  /* width: 35px; */
  -ms-transform: scale(2); /* IE */
  -moz-transform: scale(2); /* FF */
  -webkit-transform: scale(2); /* Safari and Chrome */
  -o-transform: scale(2); /* Opera */
  transform: scale(3);
  /* padding: 10px; */
  display: flex;
  margin-left: 10px;
  margin-right: 50px;
}
#checkbox {
  font-size: 110%;
  display: inline;
}
.container {
  display: flex;
  flex-direction: column;
  margin-bottom: 100px;
  height: 100%;
}
#myaccount-title {
  margin-top: 50px;
  margin-bottom: 60px;
}
#inner-myaccount2 {
  text-align: left;
  display: flex;
  margin-top: 10px;
  margin-bottom: 10px;
  background-color: #fcf5f3;
  border: solid #ffcdd2;
  border-radius: 5%;
  align-items: center;
  padding: 10px;
}
#buttonDelete {
  margin: 10px;
  background-color: #eb412b;
  color: white;
  border-radius: 10%;
  border: white;
  width: 100px;
}
#buttonDelete:hover {
  background-color: #db6f7d;
}
#text {
  border: rgb(190, 181, 181);
  border-width: 5px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}
</style>
