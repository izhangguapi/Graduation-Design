<template>
  <div>
    <el-row>
      <el-col :span="16" :offset="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">比赛列表</span>
            <el-button @click="$router.push('/management/competition')" type="primary" round style="float: right;"
                       size="mini">添加比赛
            </el-button>
          </div>
          <el-table :data="contests" fit :current-row-key="contests.contestId">
            <el-table-column prop="contestTitle" label="比赛名称" align="center"></el-table-column>
            <el-table-column prop="status" label="审核状态" width="100%" align="center">
              <template v-slot="scope">
                <el-tag v-if="scope.row.status" type="success">通过</el-tag>
                <el-tag v-else-if="scope.row.statusText" type="warning">审核中</el-tag>
                <el-tag v-else type="danger">未通过</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="number" label="报名人数" width="100%" align="center"></el-table-column>
            <el-table-column label="操作" width="150%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="mini" @click="sendMessage(scope.row.contestId,scope.row.contestTitle)">
                  发送消息
                </el-button>
                <el-button type="text" size="mini" @click="updateContests(scope.row.contestId)">修改比赛</el-button>
              </template>
            </el-table-column>
            <el-table-column label="删除" width="100%" align="center">
              <template v-slot="scope">
                <el-popconfirm title="这是一段内容确定删除吗？" @confirm="removeContest(scope.row.contestId)">
                  <el-button slot="reference" type="danger" size="mini" round>删除</el-button>
                </el-popconfirm>
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
  name: "Management",
  data() {
    return {
      visible: false,
      contests: []
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
        this.contests = res.data.data;
      });
    },
    sendMessage(cid, title) {
      this.$router.push({name: 'management-message', params: {contestId: cid, contestTitle: title}});
    },
    updateContests(cid) {
      this.$router.push({name: 'management-competition', params: {contestId: cid}});
    },
    removeContest(id) {
      console.log(id);
      this.visible = false;
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
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}
</style>