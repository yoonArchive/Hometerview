<template>
  <div class="container">
    <div id="myaccount-title"><h2>회원 탈퇴</h2></div>
    <div id="inner-myaccount">
      <div id="text">
        <span>
          계정 삭제 시 프로필 및 개인 이용 기록이 삭제됩니다.
        </span>
        <br />
        <span>
          탈퇴 후 회원님의 이용정보가 삭제되어 복구 불가능합니다.<br />
          회원정보 및 개인 이용기록은 모두 삭제되며, 삭제된 데이터는 복구
          불가능합니다.
        </span>
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
        <div class="warning">
          계정을 삭제하면 되돌릴 수 없으며, 삭제한 데이터를 복구할 수 없음을
          이해했습니다.
        </div>
      </div>
      <button id="buttonDelete" class="btn" @click="this.deleteaccount">
        계정삭제
      </button>
    </div>
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
input[type="checkbox"] {
  width: 10px;
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
  font-size: 90%;
  display: inline;
}

#myaccount-title {
  font-family: "티머니 둥근바람 볼드";
  margin-bottom: 50px;
}

#inner-myaccount {
  border: 2px solid;
  padding: 35px;
  border-color: rgb(241, 241, 241);
  border-radius: 20px;
  width: 800px;
}
#inner-myaccount2 {
  text-align: left;
  display: flex;
  margin-top: 10px;
  margin-bottom: 30px;
  background-color: #fcf5f3;
  border: solid #ffcdd2;
  /* border-radius: %; */
  align-items: center;
  padding: 15px;
}
/* #buttonDelete {
  margin: 10px;
  background-color: #eb412b;
  color: white;
  border-radius: 10%;
  border: white;
  width: 100px;
}
#buttonDelete:hover {
  background-color: #db6f7d;
} */

#text {
  border: rgb(190, 181, 181);
  border-width: 5px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.warning {
  font-size: 17px;
}

.btn {
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
  width: 11em;
  letter-spacing: 0.15em;
  text-align: center;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.btn:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
.btn:active {
  background-color: rgba(161, 104, 253, 0.15);
}
</style>
