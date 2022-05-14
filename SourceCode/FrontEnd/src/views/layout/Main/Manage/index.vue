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
            <!--审核状态-->
            <el-table-column prop="status" label="审核状态" width="150%" align="center">
              <template v-slot="scope">
                <el-tag v-if="scope.row.status" type="success">通过</el-tag>
                <el-popover v-else-if="scope.row.statusText" placement="top-start" width="150" trigger="hover"
                            :content="scope.row.statusText">
                  <el-button slot="reference" type="danger" size="mini" plain>失败</el-button>
                </el-popover>
                <el-tag v-else type="warning">审核中</el-tag>
              </template>
            </el-table-column>
            <!--报名人数-->
            <el-table-column prop="number" label="报名人数" width="100%" align="center"></el-table-column>

            <!--操作-->
            <el-table-column label="操作" width="150%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="mini" v-if="scope.row.status"
                           @click="dialogVisible=true;cid=scope.row.contestId">
                  发送消息
                </el-button>
                <el-button type="text" size="mini" v-if="scope.row.status || scope.row.statusText"
                           @click="updateContests(scope.row.contestId)">修改比赛
                </el-button>
              </template>
            </el-table-column>
            <!--评审-->
            <el-table-column label="评审" width="110%" align="center">
              <template v-slot="scope">
                <el-popover v-if="!scope.row.status" placement="top-start" trigger="hover" content="审核未完成">
                  <el-button slot="reference" type="info" size="mini" round>禁用</el-button>
                </el-popover>

                <el-button v-else-if="new Date() > new Date(scope.row.regEndTime)" type="success" size="mini" round
                           @click="sendMessage(scope.row.contestId)">评审
                </el-button>

                <el-popover v-else placement="top-start" trigger="hover" content="报名未结束，不可评审">
                  <el-button slot="reference" type="warning" size="mini" round>禁用</el-button>
                </el-popover>

              </template>
            </el-table-column>
            <!--删除-->
            <el-table-column label="删除" width="110%" align="center">
              <template v-slot="scope">
                <el-popconfirm title="这是一段内容确定删除吗？"
                               @confirm="removeContest(scope.row.contestId,scope.row.contestTitle)">
                  <el-button slot="reference" type="danger" size="mini" round>删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
        </el-card>

      </el-col>
    </el-row>

    <el-dialog title="发送消息" :visible.sync="dialogVisible" width="30%">
      <el-form label-position="top">
        <el-form-item label="标题" prop="title">
          <el-input placeholder="请输入标题" v-model="title" maxlength="20" show-word-limit
                    :rules="[{ required: true, message: '标题不能为空'}]"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="text">
          <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="text"
                    :rules="[{ required: true, message: '内容不能为空'}]"></el-input>
        </el-form-item>
        <el-form-item style="float: right">
          <el-button round @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" round @click="send">发送</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {deleteRequest, getRequest, postRequest} from "@/utils/api";

export default {
  name: "Manage",
  data() {
    return {
      // 发送消息对话框
      dialogVisible: false,
      // 删除提示框
      visible: false,
      // 比赛列表
      contests: [],
      // 比赛id
      cid: undefined,
      // 发送消息的标题
      title: '',
      // 发送消息的内容
      text: ''
    }
  },
  watch: {
    "$store.state.gid"() {
      this.isStudent();
    }
  },
  methods: {
    isStudent() {
      if (this.$store.state.gid && this.$store.state.gid !== 2) {
        this.contestsLoading();
      }else {
        this.$message.warning("请登录！");
        this.$router.push("/login");
      }
    },
    contestsLoading() {
      getRequest("/contests/gid", {gid: this.$store.state.gid}).then(res => {
        // console.log(res)
        if (res) {
          this.contests = res.data.data;
        }
      });
    },
    // 传入比赛id查询评审的学生
    sendMessage(cid, title) {
      this.$router.push({name: 'manage-review', params: {contestId: cid, contestTitle: title}});
    },
    // 更新比赛
    updateContests(cid) {
      this.$router.push({name: 'manage-contest', params: {contestId: cid}});
    },
    // 删除比赛
    removeContest(id) {
      deleteRequest("/deleteContest/" + id).then(res => {
        // console.log(res.data);
        if (res.data.status) {
          this.$message.success("删除成功。")
        } else {
          this.$message.error("删除失败！")
        }
        this.contestsLoading();
      })
      this.visible = false;
    },
    // 发送消息
    send() {
      if (this.title.trim() !== '' && this.text.trim() !== '') {
        const jsonObject = {cid: this.cid, uid: this.$store.state.uid, title: this.title, text: this.text}
        // console.log(jsonObject);
        postRequest("/messages/save", jsonObject).then((res) => {
          // console.log(res.data);
          if (res.data.status) {
            this.$message.success("发送成功。")
          } else {
            this.$message.error("发送失败！")
          }
          this.dialogVisible = false;
        });
      } else {
        this.$message.warning("请在左侧勾选学生或检测标题内容是否为空！")
      }
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