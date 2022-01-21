<template>
  <div>
    <!--表单-->
    <el-form ref="contestForm" :model="contestForm" label-width="80px" label-position="top" :rules="rules">
      <!--输入比赛名称-->
      <el-row :gutter="20">
        <el-col :span="16" :offset="4">
          <el-card>
            <el-form-item label="比赛名称" prop="contestTitle">
              <el-input v-model="contestForm.contestTitle"></el-input>
            </el-form-item>
          </el-card>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <!--左侧编辑内容-->
        <el-col :span="12" :offset="4">
          <mdEditor v-model="contestForm.contestText" :ishljs="true" style="min-height: 600px"/>
        </el-col>
        <!--右侧输入其他信息-->
        <el-col :span="4">
          <el-card style="min-height: 600px">
            <el-form-item label="发布人">
              <el-input v-model="contestForm.name" disabled></el-input>
            </el-form-item>
            <el-form-item label="所属组">
              <el-input v-model="contestForm.groupName" disabled></el-input>
            </el-form-item>
            <el-form-item label="报名开始时间" prop="startTime">
              <el-date-picker v-model="contestForm.startTime" type="datetime" placeholder="选择日期时间"
                              default-time="08:00:00" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item label="报名结束时间" prop="endTime">
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
import {getRequest, postRequest} from "@/utils/api";

const mdEditor = require('mavon-editor')
import 'mavon-editor/dist/css/index.css'

export default {
  name: "index",
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    const endTimeCheckout = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('报名结束时间不能为空'));
      } else if (value < this.contestForm.startTime) {
        callback(new Error('结束时间不能小于开始时间'));
      } else {
        callback();
      }
    };
    return {
      contestForm: {
        contestTitle: '',
        contestText: '## 请输入比赛内容\n',
        name:'',
        promulgator: '',
        groupId: '',
        groupName: '',
        startTime: '',
        endTime: '',
      },
      rules: {
        contestTitle: [{ required: true,message: '请输入比赛名称', trigger: 'blur'}],
        startTime: [{required: true, message: '请输入报名开始时间', trigger: 'blur'}],
        endTime: [{required: true, validator: endTimeCheckout, trigger: 'blur'}]
      }
    };
  },
  mounted() {
    // 页面启动给datetime赋值时间戳
    const gid = sessionStorage.gid;
    if (gid === ""){
      this.$message.error("获取用户组失败！");
    }else {
      getRequest("/group/"+gid).then((resp)=>{
        this.contestForm.promulgator = sessionStorage.uid;
        this.contestForm.name = sessionStorage.name;
        this.contestForm.groupId = gid;
        this.contestForm.groupName = resp.data.data;
      })

    }


  },
  methods: {
    // 提交
    submitForm() {
      this.$refs.contestForm.validate((valid) => {
        if (valid) {
          postRequest("/addContests",this.contestForm).then((resp)=>{
            this.$message.success(resp.data.msg)
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