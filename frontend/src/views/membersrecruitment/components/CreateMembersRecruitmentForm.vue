<template>
  <div class="container">
    <h6>스터디 그룹을 만들어 보세요!</h6>
    <form @submit.prevent="submitType(action)" enctype="">
      <div class="py-2 row">
        <label class="title" for="recruitTitle">모집글 제목</label>
        <input
          class="box1"
          v-model="newrecruitmentInfo.recruitTitle"
          type="text"
          id="recruitTitle"
          placeholder="모집글 제목을 작성하세요"
        />
      </div>
      <div class="py-2">
        <span class="title"> 카테고리 </span>
        <label for="stdType" class="type" style="margin-left:13%"
          >기업 면접</label
        >
        <input
          v-model="newrecruitmentInfo.stdType"
          class="radio"
          name="stdType"
          type="radio"
          id="stdType"
          value="COM"
          @click="isCompany('COM')"
          checked
        />
        <label for="stdType" class="type">자율 면접</label>
        <input
          v-model="newrecruitmentInfo.stdType"
          class="radio"
          name="stdType"
          type="radio"
          id="stdType"
          value="FREE"
          @click="isCompany('FREE')"
        />
      </div>
      <div class="py-2 row">
        <label class="title" for="comName">기업명 </label>
        <input
          class="box1"
          v-model="newrecruitmentInfo.comName"
          type="text"
          id="comName"
          :disabled="company"
          placeholder="기업명을 입력하세요"
        />
      </div>
      <div class="py-2">
        <label class="title " for="startDate">기간</label>
        <input v-model="newrecruitmentInfo.startDate" type="date" id="Date" />
        ~
        <input v-model="newrecruitmentInfo.endDate" type="date" id="Date" />
        <!-- <label class="title " for="startDate">
          시작일
          <input v-model="newrecruitmentInfo.startDate" type="date" id="Date" />
        </label>
        <label class="title" for="endDate"
          >종료일
          <input v-model="newrecruitmentInfo.endDate" type="date" id="Date" />
        </label> -->
      </div>

      <div class="py-2 row">
        <label class="title" for="stdDay">진행 일시 </label>
        <input
          class="box1"
          v-model="newrecruitmentInfo.stdDay"
          type="text"
          id="stdDay"
          placeholder="진행 일시를 입력하세요"
        />
      </div>
      <div class="py-2 row">
        <label class="title" for="stdLimit"> 모집 인원 </label>
        <input
          class="box1"
          v-model="newrecruitmentInfo.stdLimit"
          type="number"
          id="stdLimit"
          placeholder="4"
          style="width:70px"
        />
      </div>
      <div class="py-2 row">
        <label class="title" for="stdName">스터디 이름 </label>
        <input
          class="box1"
          v-model="newrecruitmentInfo.stdName"
          type="text"
          id="stdName"
          placeholder="스터디 이름을 입력하세요"
        />
      </div>
      <div class="py-2 row">
        <label class="title" for="stdDetail"> 스터디 설명 </label>
        <input
          class="box2"
          v-model="newrecruitmentInfo.stdDetail"
          type="text"
          id="stdDetail"
          placeholder="스터디 설명을 작성하세요."
        />
      </div>
      <!-- <div>
        이미지 :
        <label for="stdImg">
           <button id="file-button">파일</button>
          <input multiple @change="onInputimage" ref="studyImage" type="file" id="stdImg" style="display:none">
          <input v-model="newrecruitmentInfo.stdImg" type="image" id="stdImg" >
        </label>
      </div> -->
      <div>
        <div class="py-2 row">
          <label for="inputImage" class="title">대표 이미지</label>
          <!-- <input type="text" id="dump" disabled>
          <button type="button" @click="findImage">Add</button> -->
          <input
            class="box3"
            type="file"
            id="inputImage"
            ref="inputImage"
            @change="imageSelect"
            multiple
          />
        </div>
        <button class="form-button-submit button">
          {{ action }}
        </button>
        <!-- <div class="col-12">
          <a href="#" class="form-button-submit button icon solid fa-envelope"
            >Send Message</a
          >
        </div> -->
      </div>
    </form>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "CreateMembersRecruitmentForm",

  props: {
    recruitDetail: Object,
    action: String,
    recruitNo: String
  },

  data() {
    return {
      company: false,
      newrecruitmentInfo: {
        stdImg: File,
        comName: "",
        endDate: "",
        recruitTitle: "",
        startDate: "",
        stdDay: "",
        stdDetail: "",
        stdLimit: "4",
        stdName: "",
        stdType: ""
      }
    };
  },
  computed: {
    findImage() {
      document.getElementById("inputImage").click();
    },
    imageSelect() {
      this.newrecruitmentInfo.stdImg = this.$refs.inputImage.files[0];
      console.log(this.newrecruitmentInfo.stdImg);
    }
  },
  methods: {
    ...mapActions([
      "createRecruitment",
      "updateRecruitmentDetail",
      "deleteRecruitmentDetail"
    ]),

    upload() {
      const formData = new FormData();
      formData.append("multipartFile", this.newrecruitmentInfo.stdImg);
      formData.append(
        "recruitInfoReq.recruitTitle",
        this.newrecruitmentInfo.comName
      );
      formData.append(
        "recruitInfoReq.endDate",
        this.newrecruitmentInfo.endDate
      );
      formData.append(
        "recruitInfoReq.recruitTitle",
        this.newrecruitmentInfo.recruitTitle
      );
      formData.append(
        "recruitInfoReq.startDate",
        this.newrecruitmentInfo.startDate
      );
      formData.append("recruitInfoReq.stdDay", this.newrecruitmentInfo.stdDay);
      formData.append(
        "recruitInfoReq.stdDetail",
        this.newrecruitmentInfo.stdDetail
      );
      formData.append(
        "recruitInfoReq.stdLimit",
        this.newrecruitmentInfo.stdLimit
      );
      formData.append(
        "recruitInfoReq.stdName",
        this.newrecruitmentInfo.stdName
      );
      formData.append(
        "recruitInfoReq.stdType",
        this.newrecruitmentInfo.stdType
      );
      return formData;
    },
    isCompany(type) {
      if (type === "COM") {
        this.company = false;
      } else {
        this.company = true;
      }
    },

    async submitType(action) {
      if (action === "만들기") {
        const formData = await this.upload();
        await this.createRecruitment(formData);
      } else if (action === "수정하기") {
        this.updateRecruitmentDetail([this.recruitNo, this.newrecruitmentInfo]);
      }
    }
  }
};
</script>

<style scoped>
h6 {
  color: #3c1c9f;
  margin-right: 30px;
}
.type,
.radio {
  font-size: 15px;
  margin-left: 15px;
}
input[type="text"]:focus {
  border: 2px solid #555;
}
.container {
  border: #d76d77;
  border-radius: 10%;
  display: flex;
  flex-direction: column;
}
.create-form {
  width: 550px;
  height: 1000px;
  background: #f3f4ff;
}
.title {
  width: 130px;
  height: 24px;
  font-family: "ABeeZee";
  font-style: normal;
  font-weight: 1000;
  font-size: 16px;
  line-height: 150%;
  color: #653fd3;
  margin-top: 4px;
  margin-left: 4px;
}
.box1 {
  width: 250px;
  height: 38px;
  font-size: 15px;
  background: #ffffff;
  border: 1px solid #ced4da;
  border-radius: 4px;
  margin-left: 15px;
}
.box1:disabled {
  background-color: #6c6d6ea2;
  content: "";
}
.box2 {
  width: 250px;
  line-height: 100px;
  font-size: 15px;
  background: #ffffff;
  border: 1px solid #ced4da;
  border-radius: 4px;
  margin-top: 5px;
  margin-left: 15px;
}
.box3 {
  width: 280px;
  height: 38px;
  font-size: 15px;
  background: #ffffff;
  border: 1px solid #ced4da;
  border-radius: 4px;
  padding-top: 4px;
  margin-left: 15px;
}
#stdLimit {
  width: 50px;
  border: 1px solid #ced4da;
  border-radius: 4px;
}

#Date {
  width: 100px;
  margin-right: 10px;
  background-color: #ffffff;
  color: #ced4da;
  border-radius: 10%;
  font-size: 14px;
  text-align: center;
  color: rgb(95, 94, 94);
  border: 0;
  outline: 0;
}
#Date:hover {
  background-color: #efefef;
}
#Date:active {
  background-color: #d1d1d1;
}
#endDate {
  width: 100px;
  margin-right: 0px;
}
input[type="button"],
input[type="submit"],
input[type="reset"],
button,
.button {
  -moz-transition: all 0.25s ease-in-out;
  -webkit-transition: all 0.25s ease-in-out;
  -ms-transition: all 0.25s ease-in-out;
  transition: all 0.25s ease-in-out;
  -webkit-appearance: none;
  position: relative;
  display: inline-block;
  background: #ed786a;
  color: #fff !important;
  text-transform: uppercase;
  border-radius: 4px;
  border: 0;
  outline: 0;
  font-size: 1em;
  box-shadow: 0.125em 0.175em 0 0 rgba(0, 0, 0, 0.125);
  font-weight: 500;
  text-align: center;
  font-size: 0.7em;
  letter-spacing: 2px;
  padding: 0.65em 2em 0.65em 2em;
  margin-top: 0.5em;
  margin-bottom: 2em;
}
input[type="button"]:hover,
button:hover,
.button:hover {
  background: #fd887a;
}
input[type="button"]:active,
button:active,
.button:active {
  background: #ed786a;
}
</style>
