<template>
  <el-container direction="vertical">
    <!--顶部-->
      <Header></Header>
    <!--中间-->
    <el-main>
      <transition name="fade" mode="out-in">
        <router-view/>
      </transition>
    </el-main>
    <!--底部-->
    <Footer></Footer>
  </el-container>
</template>

<script>
import Header from '@/views/layout/Header';
import Footer from '@/views/layout/Footer'
import {login} from "@/utils/login";

export default {
  name: 'Layout',
  components: {Header, Footer},
  created() {
    if (!this.$store.state.isLogin) login();
  }
}
</script>

<style lang="less" scoped>
.el-main {
  width: 100%;
  padding-top: 100px;
  min-height: 800px;
}
.el-card:hover{
  box-shadow: 0 2px 12px 0 rgb(255 100 100 / 50%);
}
</style>