<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4" style="position:relative;">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">选择需要发送消息的对象</span>
            <el-button style="float: right" size="mini" type="primary" round @click="$router.go(-1);">返回</el-button>
          </div>
          <el-table :data="data" fit :current-row-key="data.contestId">
            <el-table-column type="selection"></el-table-column>
            <el-table-column prop="contestTitle" label="姓名" align="center"></el-table-column>
            <el-table-column prop="contestTitle" label="学校" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Score",
  data() {
    return {
      data: []
    }
  },
  mounted() {
    // getRequest("/contests/gid", {gid: sessionStorage.gid}).then((res) => {
    //   this.data = res.data.data;
    //   console.log(this.data)
    // })
    console.log(this.$route.params.contestId)
  },
  methods: {}
}
</script>

<style lang="less" scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>