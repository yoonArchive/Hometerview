<template>
  <banner-vue></banner-vue>
  <el-row :gutter="20">
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
  </el-row>

</template>

<script>
import BannerVue from '../../components/studyrecruitment/banner'
export default {
  components:{
    BannerVue,
  },
  setup(){

  }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;
  border:1px black solid;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  border:1px red solid;
}
</style>
