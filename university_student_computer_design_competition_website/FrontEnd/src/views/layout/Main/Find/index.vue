<template>
    <div>
      <el-row :gutter="20">
        <el-col :span="16" :offset="4">
          <competitionList></competitionList>
        </el-col>
      </el-row>
    </div>
</template>

<script>
import competitionList from "./CompetitionList";


export default {
  name: "Find",
  components: {competitionList},
}
</script>

<style lang="less" scoped>

</style>