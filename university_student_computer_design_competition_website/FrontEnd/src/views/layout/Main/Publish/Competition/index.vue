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
            <mdEditor v-model="contestForm.text" :ishljs="true" style="min-height: 600px"/>
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
              <el-button type="primary" @click="submitForm">立即创建</el-button>
            </el-form-item>
          </el-card>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import {postRequest} from "@/utils/api";

import 'mavon-editor/dist/css/index.css'

const mdEditor = require('mavon-editor')

export default {
  name: "Competition",
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
      contestForm: {
        contestTitle: '',
        contestText: '',
        url: '',
        text: '## 请输入比赛内容\n',
        promulgator: 0,
        groupId: 0,
        regStartTime: '',
        regEndTime: '',
        startTime: '',
        endTime: ''
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
    const gid = sessionStorage.gid;
    const uid = sessionStorage.uid;
    if (gid === '' && uid === '') {
      this.$message.error("获取用户组失败！请重新登录。");
    } else {
      this.contestForm.groupId = gid;
      this.contestForm.promulgator = uid;
    }
  },
  methods: {
    // 提交
    submitForm() {
      this.contestForm.contestText = this.contestForm.text; // .replace(/\n/ig, "<br>")
      this.$refs.contestForm.validate((valid) => {
        if (valid) {
          postRequest("/addContests", this.contestForm).then((resp) => {
            // console.log(resp);
            if (resp) {
              // this.$confirm(resp.data.msg, '信息', {confirmButtonText: '确定', type: 'success', center: true})
              this.$message.success(resp.data.msg);
              this.$router.push("/home");
            }
          })
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

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.el-date-editor.el-input {
  width: 100%;
}
</style>