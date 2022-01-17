<template>
  <div>
    <!-- 注册表单 -->
    <el-form :rules="rules" ref="registerForm" :model="registerForm" class="registerContainer">
      <!-- logo-->
      <img src="../assets/image.png" alt=""/>
      <el-tabs v-model="activeName" stretch @tab-click="handleClick">
        <!-- 第一步-->
        <el-tab-pane label="注册账号" name="1" :disabled="disabled">
          <!-- 输入用户手机号 -->
          <el-form-item prop="phone">
            <el-input prefix-icon="fa fa-phone" v-model="registerForm.phone"></el-input>
          </el-form-item>
          <!-- 输入用户邮箱 -->
          <el-form-item prop="email">
            <el-input prefix-icon="fa fa-envelope-o" v-model="registerForm.email"></el-input>
          </el-form-item>
          <!-- 输入用户密码 -->
          <el-form-item prop="password">
            <el-input prefix-icon="fa fa-lock" v-model="registerForm.password" show-password></el-input>
          </el-form-item>
        </el-tab-pane>

        <!-- 第二步-->
        <el-tab-pane label="输入信息" name="2" :disabled="disabled">
          <!-- 姓名 -->
          <el-form-item prop="name">
            <el-input prefix-icon="fa fa-user-o" v-model="registerForm.name"></el-input>
          </el-form-item>
          <!-- 性别 -->
          <el-form-item prop="sex">
            <el-radio class="radioL" v-model="registerForm.sex" label="1" border>男</el-radio>
            <el-radio class="radioR" v-model="registerForm.sex" label="0" border>女</el-radio>
          </el-form-item>
          <!-- 出生年月日 -->
          <el-form-item prop="birthday">
            <el-date-picker v-model="registerForm.birthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd" style="width: 100%"></el-date-picker>
          </el-form-item>
          <!-- 学校 -->
          <el-form-item prop="school">
            <el-input prefix-icon="fa fa-university" v-model="registerForm.school"></el-input>
          </el-form-item>
          <!-- 地址 -->
          <el-form-item prop="address">
            <el-input prefix-icon="fa fa-map-marker" v-model="registerForm.address"></el-input>
          </el-form-item>
        </el-tab-pane>
        <!-- 第三步-->
        <el-tab-pane label="选择身份" name="3" style="text-align: center;" disabled>
          <!-- 选择用户身份 -->
          <el-form-item>
            <el-select v-model="registerForm.groupId" placeholder="请选身份" style="width: 100%">
              <el-option v-for="item in groups" :key="item.value" :label="item.groupName"
                         :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-button @click="submitForm" class="submit" style="margin-bottom: 50px">提&nbsp;&nbsp;&nbsp;&nbsp;交
          </el-button>
        </el-tab-pane>
      </el-tabs>
      <el-steps :active="active" align-center>
        <el-step title="步骤 1"></el-step>
        <el-step title="步骤 2"></el-step>
        <el-step title="步骤 3"></el-step>
      </el-steps>
      <div class="btn">
        <el-button v-if="active!==2" @click="next" class="lastBtn">下一步</el-button>
      </div>
      <el-row>
        <el-link type="primary" @click="goToLoginPage" style="float:right;padding: 10px 0 5px 0;">已有账号，点击登录</el-link>
      </el-row>
    </el-form>
  </div>

  <!--  <div>-->


  <!--  </div>-->
</template>

<script>
import {getRequest, postRequest} from "@/utils/api";
import el from "element-ui/src/locale/lang/el";
import {Message} from "element-ui";

export default {
  name: "register",
  data() {
    return {
      activeName: '3',//控制tabs
      active: 0,//控制步骤
      disabled: false,//设置标签页的禁用或启用
      registerForm: {//提交的表单信息
        phone: '',
        email: '',
        password: '',
        name: '',
        sex: '',
        birthday: '',
        school: '',
        address: '',
        groupId: '',
      },
      rules: {
        phone: [{required: true, message: "电话不能为空", trigger: "blur"},],
        email: [{required: true, message: "邮箱不能为空", trigger: "blur"},],
        password: [{required: true, message: "密码不能为空", trigger: "blur"},],
      },
      value: '',//默认选择学生
      groups: [//多选框选项
        {groupName: "学生", value: 0},
        {groupName: "老师", value: 1}
      ]
    };
  },
  watch: {
    // 点击步骤按钮，标签页步骤跟着联动
    active() {
      this.activeName = this.active + 1 + "";
      //控制提交按钮的禁用
      switch (this.active) {
        case 0:
          this.rules = {
            group: [{required: true, message: "身份不能为空", trigger: "blur"},],
            phone: [{required: true, message: "电话不能为空", trigger: "blur"},],
            email: [{required: true, message: "邮箱不能为空", trigger: "blur"},],
            password: [{required: true, message: "密码不能为空", trigger: "blur"},],
          };
          break;
        case 1:
          this.rules.name = [{required: true, message: "姓名不能为空", trigger: "blur"},];
          this.rules.sex = [{required: true, message: "性别不能为空", trigger: "blur"},];
          this.rules.birthday = [{required: true, message: "生日不能为空", trigger: "blur"},];
          this.rules.school = [{required: true, message: "学校不能为空", trigger: "blur"},];
          this.rules.address = [{required: true, message: "地址不能为空", trigger: "blur"},];
          break;
        default:
          break;
      }
    },
    'registerForm.groupId': {
      handler() {
        if (this.registerForm.groupId) {
          this.$prompt('请输入5为组编码', '加入或创建组', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /^[0-9A-Za-z]{5}$/,
            inputErrorMessage: '编码格式不正确'
          }).then(({value}) => {
            //去数据库搜索输入的编码是否存在 groupId JSON.stringify(
            getRequest("/api/encoding/" + value.toUpperCase()).then((resp) => {
              console.log(resp.data.data)
              if (resp.data.data) {
                //拿到组id，更新数据库 resp.data.data[0].groupId
                this.$message({type: 'success', message: '加入组'});
              } else {
                this.$message({type: 'success', message: '创建组'});
              }
            });

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });
        }

      }
    }
  },
  methods: {
    handleClick() { // 点击标签页，步骤跟着联动
      this.active = Number(this.activeName) - 1;
    },
    next() { // 下一步
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          this.$message.success("校验成功。");
          if (++this.active > 2) this.active = 2;
        } else {
          this.$message.error("请输入完整再进行下一步吧！");
        }
      });
    },
    submitForm() {

      // this.$refs[formName].validate(async (valid) => {
      //   if (!valid) return console.log("error valid");
      //   this.$http
      //       .post("/users/register", this.form)
      //       .then((result) => {
      //         console.log(result);
      //         if (result.data.code !== 200)
      //           return this.$message.error("注册失败");
      //         this.$message.success("注册成功");
      //         // window.sessionStorage.setItem('token', result.data.token)
      //         this.$router.push("/users/login");
      //       })
      //       .catch((error) => {
      //         // 【务必注意】这里的error输出的不是一个对象【error.response才是一个对象】
      //         console.log(error);
      //         if (error.response) {
      //           // 请求已发出，但服务器响应的状态码不在 2xx 范围内
      //           console.log(error.response.data);
      //           console.log(error.response.status);
      //           console.log(error.response.headers);
      //         } else {
      //           // Something happened in setting up the request that triggered an Error
      //           console.log("Error", error.message);
      //         }
      //         console.log(error.config);
      //       });
      // });
    },
    goToLoginPage() {
      this.$router.push("/login");
    },
  },
};

</script>

<style scoped>
.registerContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 200px auto;
  width: 350px;
  padding: 15px 35px;
  background: #fff;
  border: 1px solid #eeeeee;
  box-shadow: 0 0 25px #cac6c6;
}

.btn {
  margin-top: 10px;
  text-align: center;
}

.radioL {
  margin-right: 10px;
  text-align: center;
  width: 165px;
}

.radioR {
  margin-left: 10px;
  margin-right: 0;
  text-align: center;
  width: 165px;
}

</style>