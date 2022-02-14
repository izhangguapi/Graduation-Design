<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12" :offset="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">若要发送消息请先选中</span>
            <el-button style="float: right" size="mini" type="primary" round @click="$router.go(-1);">返回</el-button>
          </div>
          <el-table :data="data" @selection-change="handleSelectionChange" fit>
            <el-table-column type="selection"></el-table-column>
            <el-table-column prop="name" label="姓名" align="center"></el-table-column>
            <el-table-column prop="school" label="学校" align="center"></el-table-column>
            <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
            <el-table-column prop="state" label="评审状态" align="center">
              <template v-slot="scope">
                <el-tag :type="scope.row.state ? 'danger' : 'success'" disable-transitions>
                  {{ scope.row.state ? '已评审' : '未评审' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="100%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="small"
                           @click="$router.push({name: 'publish-score', params: {scoresId: scope.row.scoresId}})">
                  {{ scope.row.state ? '查看' : '评审' }}
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">发送消息</span>
          </div>
          <el-form label-position="top">
            <el-form-item label="标题">
              <el-input placeholder="请输入标题" v-model="title" maxlength="20" show-word-limit
                        :rules="[{ required: true, message: '标题不能为空'}]"></el-input>
            </el-form-item>
            <el-form-item label="内容">
              <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="text"
                        :rules="[{ required: true, message: '内容不能为空'}]"></el-input>
            </el-form-item>
            <el-form-item style="float: right">
              <el-button type="primary" round @click="send">发送</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Message",
  data() {
    return {
      data: [],
      multipleSelection: [],
      title: '',
      text: ''
    }
  },
  mounted() {
    getRequest("/scores/users", {cid: this.$route.params.contestId}).then((res) => {
      this.data = res.data.data;
      console.log(res.data.data)
    })
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = [];
      val.forEach((val) => {
        this.multipleSelection.push({userId: val.userId});
      });
    },
    send() {
      let length = this.multipleSelection.length
      if (length) {
        for (let i = 0; i < length; i++) {
          this.$set(this.multipleSelection[i], 'title', this.title);
          this.$set(this.multipleSelection[i], 'text', this.text);
        }
        console.log(this.multipleSelection);
      } else {
        this.$message.warning("请在左侧选中需要发送的学生！")
      }
    }
  }
}
</script>

<style scoped>
input.el-input__inner {
  padding-right: 48px !important;
}
</style>