<template>
    <div>
      <el-row :gutter="20">
        <el-col :span="16" :offset="4">
          <ContestList></ContestList>
        </el-col>
      </el-row>
    </div>
</template>

<script>
import ContestList from "./ContestList";


export default {
  name: "Find",
  components: {ContestList},
}
</script>

<style lang="less" scoped>

</style>