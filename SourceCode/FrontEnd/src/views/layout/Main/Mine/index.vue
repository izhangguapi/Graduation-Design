<template>
  <div v-if="isLogin">
    <el-row :gutter="20">
      <el-col :span="10" :offset="5">
        <Information></Information>
      </el-col>
      <el-col :span="4">
        <Contest></Contest>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Information from "@/views/layout/Main/Mine/Information";
import Contest from "@/views/layout/Main/Mine/Contest";
import {login} from "@/utils/login";

export default {
  name: "index",
  components: {Information, Contest},
  computed:{
    isLogin(){
      return this.$store.state.isLogin;
    }
  }
}
</script>

<style lang="less" scoped>

</style>