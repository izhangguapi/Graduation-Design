<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="10" :offset="5">
        <Information></Information>
      </el-col>
      <el-col :span="4">
        <Competition></Competition>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Information from "@/views/layout/Main/Mine/Information";
import Competition from "@/views/layout/Main/Mine/Competition";

export default {
  name: "index",
  components: {Information, Competition},
}
</script>

<style scoped>

</style>