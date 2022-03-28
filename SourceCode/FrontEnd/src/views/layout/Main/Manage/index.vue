<template>
  <div>
    <el-row>
      <el-col :span="16" :offset="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">比赛列表</span>
            <el-button @click="$router.push('/manage/contest')" type="primary" round style="float: right;"
                       size="mini">添加比赛
            </el-button>
          </div>
          <el-table :data="contests" fit :current-row-key="contests.contestId">
            <el-table-column prop="contestTitle" label="比赛名称" align="center"></el-table-column>
            <el-table-column prop="name" label="发布人" width="150%" align="center"></el-table-column>
            <el-table-column prop="status" label="审核状态" width="150%" align="center">
              <template v-slot="scope">
                <el-tag v-if="scope.row.status" type="success">通过</el-tag>
                <el-popover
                    v-else-if="scope.row.statusText"
                    placement="top-start"
                    width="150"
                    trigger="hover"
                    :content="scope.row.statusText">
                  <el-button slot="reference" type="danger" size="mini" plain>失败</el-button>
                </el-popover>
                <el-tag v-else type="warning">审核中</el-tag>
              </template>

            </el-table-column>
            <el-table-column prop="number" label="报名人数" width="100%" align="center"></el-table-column>
            <el-table-column label="操作" width="150%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="mini" v-if="scope.row.status"
                           @click="sendMessage(scope.row.contestId,scope.row.contestTitle)">
                  发送消息
                </el-button>
                <el-button type="text" size="mini" v-if="scope.row.status || scope.row.statusText"
                           @click="updateContests(scope.row.contestId)">修改比赛
                </el-button>
              </template>
            </el-table-column>
            <el-table-column label="删除" width="110%" align="center">
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
import {deleteRequest, getRequest} from "@/utils/api";

export default {
  name: "Manage",
  data() {
    return {
      visible: false,
      contests: []
    }
  },
  watch: {
    "$store.state.gid"() {
      this.isStudent();
    }
  },
  methods: {
    isStudent() {
      if (this.$store.state.gid === 2) {
        this.$message.warning("此功能暂未对学生开发！");
        this.$router.go(-1);
      }
      this.contestsLoading();
    },
    contestsLoading() {
      getRequest("/contests/gid", {gid: this.$store.state.gid}).then((res) => {
        this.contests = res.data.data;
      });
    },
    sendMessage(cid, title) {
      this.$router.push({name: 'manage-message', params: {contestId: cid, contestTitle: title}});
    },
    updateContests(cid) {
      this.$router.push({name: 'manage-contest', params: {contestId: cid}});
    },
    removeContest(id) {
      deleteRequest("/deleteContest/" + id).then(res => {
        console.log(res.data);
        if (res.data.data) {
          this.$message.success("删除成功。")
          this.contestsLoading();
        } else {
          this.$message.error("删除失败！")
        }
      })
      this.visible = false;
    }
  },
  mounted() {
    this.isStudent();
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