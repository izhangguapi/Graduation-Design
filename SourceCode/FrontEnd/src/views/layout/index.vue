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

export default {
  name: 'Layout',
  components: {Header, Footer}
}
</script>

<style lang="less" scoped>
.el-main {
  width: 100%;
  padding-top: 100px;
  min-height: 800px;
}
</style>