<template>
  <div class="container">
     <div class="table-cocover">
      <div class="table-cover">
        <table class="table table-hover">
          <thead class="table-head">
            <tr>
              <th scope="col" v-for="(header,idx) in headers" :key="idx"> {{ header }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(a, index) in recordings" >
              <th scope="row" >
                <td>{{ index+1 }}</td></th>

              <th >
                <td>
                  <p>{{ a.study.stdName }}</p>
                </td></th>
                 <th>
                <td>{{ a.study.stdDetail }}</td>
              </th>
              <th>
                <td>{{ (a.saveDate) }}</td>
              </th>
              <th>
                <td>
        <button class="btn btn-outline-primary"
          data-bs-toggle="modal"
          href="#exampleModalToggle"
          role="button"

          id="see"
          @click="getNo(a.videoUrl, a.study.stdName)"
          >보기</button>
        </td>
              </th>


              <div
          class="modal fade"
          id="exampleModalToggle"
          aria-hidden="true"
          aria-labelledby="exampleModalToggleLabel"
          tabindex="-1"

        >
          <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel">
                  <p>스터디 이름 : {{ name }} </p>
                </h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                 @click="isActive==false"

                ></button>
              </div>
              <div class="modal-body">

                <video v-if="isActive" controls height="400" :src="url"></video>
              </div>
            </div>
          </div>
        </div>
            </tr>
          </tbody>
        </table>

      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  components: {},
  data() {
    return {
      headers: ['번호', '스터디 이름', '스터디 내용', '녹화 날짜', '영상'],
      isActive: true,
      url: '',
      name:''
    };
  },

  computed: {
    ...mapGetters(["recordings"])
  },
  methods: {
    ...mapActions(["getRecordings"]),
    kim() {
      console.log('이즈액티브'+this.isActive)
    },
    getNo(res,name) {
      this.url = res
      this.name = name
    }

  },
  created() {
    this.getRecordings();
  },
  mounted() {}
};
</script>


<style scoped>
.id{
  height: 100%;
}
.container {
  display: flex;
  height: 100%;
  width: 100%;
}
.modal-content {
  width: 100%;
}
.modal-body {
  align-items: center;
  text-align: center;
}
.table{

  width: 1000px;
}

.table-cocover{
  display : flex;
  justify-content: center;
  align-items: center;
}
.table-cover{
  width : 100%;
  padding-top : 50px;
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
  width: 80px;
}
</style>
