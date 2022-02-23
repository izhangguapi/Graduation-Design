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
import Header from './Header';
import Main from "./Main";
import Footer from './Footer'
import {login} from "@/utils/login";

export default {
  name: 'Layout',
  components: {Header, Main, Footer},
  mounted() {
    login(this.$route.path);
  }
}
</script>

<style scoped>
</style>