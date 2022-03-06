<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">比赛列表</span>
            <el-button @click="$router.push('/management/competition')" type="primary" round style="float: right;" size="mini">添加比赛</el-button>
          </div>
          <el-table :data="data" fit :current-row-key="data.contestId">
            <el-table-column prop="contestTitle" label="比赛名称" align="center"></el-table-column>
            <el-table-column prop="number" label="报名人数" width="200%" align="center"></el-table-column>
            <el-table-column fixed="right" label="操作" width="200%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="small"
                           @click="$router.push({name: 'management-message', params: {
                             contestId: scope.row.contestId,
                             contestTitle:scope.row.contestTitle
                           }})">发送消息
                </el-button>
                <el-button type="text" size="small"
                           @click="$router.push({name: 'management-competition', params: {
                             contestId: scope.row.contestId
                           }})">修改比赛
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "MessageOrScore",
  data() {
    return {
      data: [],
    }
  },
  watch: {
    "$store.state.isLogin"() {
      this.isStudent();
      this.getContests();
    }
  },
  methods: {
    isStudent() {
      if (this.$store.state.gid === 2) {
        this.$message.warning("此功能暂未对学生开发！");
        this.$router.go(-1);
      }
    },
    getContests() {
      getRequest("/contests/gid", {gid: this.$store.state.gid}).then((res) => {
        this.data = res.data.data;
      })
    }
  },
  mounted() {
    if (this.$store.state.isLogin) {
      this.isStudent();
      this.getContests();
    }
  }
}
</script>

<style lang="less" scoped>

</style>