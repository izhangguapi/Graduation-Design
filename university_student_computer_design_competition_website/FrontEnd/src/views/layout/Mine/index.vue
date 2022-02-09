<template>
<div>
  <el-row :gutter="20">
    <el-col :span="10" :offset="4">
      <Information></Information>
    </el-col>
    <el-col :span="6">
      <Competition></Competition>
    </el-col>
  </el-row>
</div>
</template>

<script>
import Information from "@/views/layout/Mine/Information";
import Competition from "@/views/layout/Mine/Competition";

export default {
  name: "index",
  components: {Information, Competition},
}
</script>

<style scoped>

</style>