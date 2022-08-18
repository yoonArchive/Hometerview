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
                <td><a
          class="btn btn-primary"
          data-bs-toggle="modal"
          href="#exampleModalToggle"
          role="button"

          >보기</a
        ></td>
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
                  <p>스터디 이름 : {{ a.study.stdName }}</p>
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

                <video v-if="isActive" controls height="400" :src="a.videoUrl"></video>
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
      isActive: true
    };
  },

  computed: {
    ...mapGetters(["recordings"])
  },
  methods: {
    ...mapActions(["getRecordings"]),
    kim() {
      console.log('이즈액티브'+this.isActive)
    }

  },
  created() {
    this.getRecordings();
  },
  mounted() {}
};
</script>


<style scoped>
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
  /* text-align: center; */
  /* align-items: center; */
  width: 1000px;
}
th td{
  /* text-align: center; */
  /* padding: 30px; */

  /* margin-left: auto;
  margin-right: auto; */
  /* margin-top: 40px; */

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

</style>
