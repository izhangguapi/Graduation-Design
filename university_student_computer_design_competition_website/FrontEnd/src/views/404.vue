<template>
  <el-result title="404" subTitle="抱歉，未找到页面！">
    <template slot="icon">
      <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" alt="">
    </template>
    <template slot="extra">
      <el-button type="primary" size="medium" @click="back">返回首页</el-button>
    </template>
  </el-result>
</template>

<script>
export default {
  name: "404",
  methods:{
    back(){
      this.$router.push("/")
    }
  }
}
</script>

<style lang="less" scoped>

</style>