<template>
  <el-container direction="vertical">
    <!--顶部-->
    <Header></Header>
    <!--中间-->
    <Main></Main>
    <!--底部-->
    <Footer></Footer>
  </el-container>
</template>

<script>
import Header from '@/views/layout/Header';
import Main from "@/views/layout/Main";
import Footer from '@/views/layout/Footer'
import {login} from "@/utils/login";

export default {
  name: 'Layout',
  components: {Header, Main, Footer},
  created() {
    if (!this.$store.state.isLogin) login();
  }
}
</script>