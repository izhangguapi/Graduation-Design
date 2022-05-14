<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">发送消息请先选中</span>
            <el-button style="float: right" size="mini" type="primary" round @click="$router.push('/manage')">
              返回
            </el-button>
          </div>
          <el-table :data="data" fit>
            <el-table-column prop="name" label="姓名" align="center"></el-table-column>
            <el-table-column prop="school" label="学校" align="center"></el-table-column>
            <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
            <el-table-column prop="status" label="评审状态" align="center">
              <template v-slot="scope">
                <!--<el-tag :type="scope.row.status ? 'danger' : 'success'" disable-transitions>-->
                <!--  {{ scope.row.status ? '已评审' : '未评审' }}-->
                <!--</el-tag>-->
                <!--<el-button v-if="scope.row.status" slot="reference" type="danger" size="mini"  round>{{ scope.row.status ? '已评审' : '未评审' }}</el-button>-->
                <!--<el-button v-else slot="reference" type="success" size="mini" round>未评审</el-button>-->
                <el-button slot="reference" :type="scope.row.status ? 'danger' : 'success'" size="mini" round
                           @click="go(scope.row)">{{ scope.row.status ? '已评审' : '未评审' }}
                </el-button>

              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="100%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="small" @click="go(scope.row)">
                  {{ scope.row.status ? '查看' : '评审' }}
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
import {getRequest, postRequest} from "@/utils/api";

export default {
  name: "Message",
  data() {
    return {
      params: {},
      // contestId:'',
      // contestTitle:'',
      data: [],
      multipleSelection: [],
      title: '',
      text: ''
    }
  },
  mounted() {
    this.params = this.$route.params;
    if (this.params.contestId) {
      getRequest("/scores/users", {cid: this.params.contestId}).then((res) => {
        // console.log(res.data.data);
        this.data = res.data.data;
      })
    }
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = [];
      const sender = this.$store.state.uid;
      val.forEach((val) => {
        this.multipleSelection.push({recipient: val.userId, sender: sender});
      });
    },
    send() {
      let length = this.multipleSelection.length
      if (length && this.title.trim() !== '' && this.text.trim() !== '') {
        for (let i = 0; i < length; i++) {
          this.$set(this.multipleSelection[i], 'title', this.title);
          this.$set(this.multipleSelection[i], 'text', this.text);
        }
        // console.log(this.multipleSelection);
        postRequest("/messages/save", this.multipleSelection).then((res) => {
          // console.log(res.data.data);
          if (res.data.status) {
            this.$message.success("发送成功。")
          } else {
            this.$message.error("发送失败！")
          }
        });
      } else {
        this.$message.warning("请在左侧勾选学生或检测标题内容是否为空！")
      }
    },
    go(data) {
      // console.log(data);
      this.params.name = data.name;
      this.params.scoresId = data.scoresId;
      this.params.phone = data.phone;
      this.params.school = data.school;
      this.params.userId = data.userId;
      this.params.status = data.status;
      // console.log(this.params);
      this.$router.push({name: 'manage-score', params: this.params})
    }
  }
}
</script>

<style lang="less" scoped>

</style>