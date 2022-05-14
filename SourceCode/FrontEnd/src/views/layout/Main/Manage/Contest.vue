<template>
  <div>
    <!--表单-->
    <el-form ref="contestForm" :model="contestForm" label-width="80px" label-position="top" :rules="rules">
      <!--输入比赛名称-->
      <el-row :gutter="20">
        <el-col :span="16" :offset="4">
          <el-card shadow="never">
            <el-form-item label="比赛名称" prop="contestTitle">
              <el-input v-model="contestForm.contestTitle" maxlength="50" show-word-limit
                        placeholder="输入比赛名称"></el-input>
            </el-form-item>
            <el-form-item label="图片url" prop="url">
              <el-input v-model="contestForm.url" maxlength="255" show-word-limit
                        placeholder="输入图片的地址，例如：https://pic.imgdb.cn/item/61e95e482ab3f51d91903853.jpg"></el-input>
            </el-form-item>
          </el-card>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <!--左侧编辑内容-->
        <el-col :span="12" :offset="4">
          <el-card shadow="never" :body-style="{ padding: '0'}">
            <mdEditor v-model="contestForm.contestText" :ishljs="true" style="min-height: 600px"/>
          </el-card>
        </el-col>
        <!--右侧输入其他信息-->
        <el-col :span="4">
          <el-card shadow="never" style="min-height: 600px">
            <el-form-item label="报名开始时间" prop="regStartTime">
              <el-date-picker v-model="contestForm.regStartTime" type="datetime" placeholder="选择日期时间"
                              default-time="08:00:00" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item label="报名结束时间" prop="regEndTime">
              <el-date-picker v-model="contestForm.regEndTime" type="datetime" placeholder="选择日期时间"
                              default-time="23:59:59" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item label="比赛开始时间" prop="startTime">
              <el-date-picker v-model="contestForm.startTime" type="datetime" placeholder="选择日期时间"
                              default-time="08:00:00" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item label="比赛结束时间" prop="endTime">
              <el-date-picker v-model="contestForm.endTime" type="datetime" placeholder="选择日期时间"
                              default-time="23:59:59" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item style="float: right;padding-top: 60px">
              <el-button @click="resetForm('ruleForm')">重置</el-button>
              <el-button type="primary" @click="submitForm">{{ btnText }}</el-button>
            </el-form-item>
          </el-card>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import {getRequest, postRequest, putRequest} from "@/utils/api";

import 'mavon-editor/dist/css/index.css'

const mdEditor = require('mavon-editor')

export default {
  name: "Contest",
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    const regEndTimeCheckout = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('报名结束时间不能为空'));
      } else if (value < this.contestForm.regStartTime) {
        callback(new Error('报名结束时间不能小于报名开始时间'));
      } else {
        callback();
      }
    };
    const startTimeCheckout = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('报名结束时间不能为空'));
      } else if (value < this.contestForm.regEndTime) {
        callback(new Error('比赛开始时间不能小于报名结束时间'));
      } else {
        callback();
      }
    };
    const endTimeCheckout = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('报名结束时间不能为空'));
      } else if (value < this.contestForm.startTime) {
        callback(new Error('比赛结束时间不能小于比赛开始时间'));
      } else {
        callback();
      }
    };
    return {
      addOrUpdate: true,
      btnText: '立即发布',
      contestForm: {
        contestTitle: '',
        contestText: '## 请输入比赛内容\n',
        url: '',
        promulgator: 0,
        groupId: 0,
        regStartTime: '',
        regEndTime: '',
        startTime: '',
        endTime: '',
        status: false
      },
      rules: {
        contestTitle: [{required: true, message: '请输入比赛名称', trigger: 'blur'}],
        url: [{required: true, message: '请输入图片地址', trigger: 'blur'}],
        regStartTime: [{required: true, message: '报名开始时间不能为空', trigger: 'blur'}],
        regEndTime: [{required: true, validator: regEndTimeCheckout, trigger: 'blur'}],
        startTime: [{required: true, validator: startTimeCheckout, trigger: 'blur'}],
        endTime: [{required: true, validator: endTimeCheckout, trigger: 'blur'}]
      }
    };
  },
  mounted() {
    const gid = this.$store.state.gid;
    const uid = this.$store.state.uid;
    if (gid === undefined && uid === undefined) {
      this.$message.error('获取用户信息失败！');
      this.$router.push("/404");
    } else {
      this.contestForm.groupId = gid;
      this.contestForm.promulgator = uid;
    }
    // 判断是否存在contestId，存在则是修改，否则是新增
    if (this.$route.params.contestId) {
      this.addOrUpdate = false;
      this.btnText = '立即修改';
      document.title = '修改比赛';
      getRequest("/contestForUpdate/" + this.$route.params.contestId).then((res) => {
        const data = res.data.data;
        if (data) {
          this.contestForm.contestTitle = data.contestTitle;
          this.contestForm.contestId = this.$route.params.contestId;
          this.contestForm.contestText = data.contestText;
          this.contestForm.url = data.url;
          this.contestForm.regStartTime = data.regStartTime;
          this.contestForm.regEndTime = data.regEndTime;
          this.contestForm.startTime = data.startTime;
          this.contestForm.endTime = data.endTime;
        } else {
          this.$router.push("/404");
        }
      });
    } else {
      document.title = '发布比赛';
    }
  },
  methods: {
    // 提交
    submitForm() {
      this.$refs.contestForm.validate((valid) => {
        if (valid) {
          if (this.addOrUpdate) {
            // 添加
            postRequest("/addContest", this.contestForm).then(res => {
              if (res.data.status) {
                this.$message.success('发布成功，等待审核。');
                this.$router.push("/manage");
              } else {
                this.$message.error('发布失败！');
              }
            })
          } else {
            // 修改
            putRequest("/updateContest", this.contestForm).then(res => {
              if (res.data.status) {
                this.$message.success('修改成功，等待审核。');
              } else {
                this.$message.error('修改失败！');
              }
              this.$router.push("/manage");
            })
          }
        } else {
          this.$message.error("请修改错误项！")
          return false;
        }
      });
    },
    // 重置
    resetForm() {
      this.$refs.contestForm.resetFields();
    }
  }
}
</script>

<style lang="less" scoped>
.el-row {
  margin-bottom: 20px;
}

.el-date-editor.el-input {
  width: 100%;
}
</style>