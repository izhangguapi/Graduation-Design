<template>
  <el-container direction="vertical">
    <!--顶部-->
      <GeneralHeader></GeneralHeader>
    <!--中间-->
      <Container></Container>
    <!--底部-->
      <GeneralFooter></GeneralFooter>
  </el-container>

</template>

<script>
import GeneralHeader from './GeneralHeader';
import Container from "./Container";
import GeneralFooter from './GeneralFooter'

export default {
  components: {GeneralHeader, Container, GeneralFooter},
}
</script>

<style scoped>
</style>