<!--
 * @Author: your name
 * @Date: 2021-09-08 00:28:50
 * @LastEditTime: 2021-09-08 00:31:33
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \front-end\src\views\register\Index.vue
-->
<!--
 * @Author: your name
 * @Date: 2021-09-03 00:52:15
 * @LastEditTime: 2021-09-06 07:00:19
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vue\src\components\users\Register.vue
-->
<template>
  <div class="register_container">
    <div class="register_box">
        <img style="padding: 0 50px"
             src="https://www.yishuzi.cn/image.png?fsize=30&font=cmll.ttf&text=mineCampus&mirror=no&color=f49bc1&vcolor=6ccff7&bgcolor=fff&alpha=no&output=png&spacing=4&shadow=no&transparent=no&icon=no&iconic=&top_spacing=5&left_spacing=6&icon_size=48"
        />
      <!-- 注册表单 -->
      <el-form ref="form" :model="form" :rules="rules" class="register_form">
        <!-- 选择用户身份 -->
        <el-form-item prop="status">
          <el-select v-model="form.status" style="width: 100%">
            <el-option
              v-for="item in status"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- 输入用户账号 -->
        <el-form-item prop="username">
          <el-input
            prefix-icon="el-icon-user"
            v-model="form.username"
            clearable
          >
          </el-input>
        </el-form-item>
        <!-- 输入用户密码 -->
        <el-form-item prop="password">
          <el-input
            prefix-icon="el-icon-lock"
            v-model="form.password"
            show-password
            clearable
          >
          </el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item class="btn">
          <el-button
            type="primary"
            round
            @click="submitForm('form')"
            style="width: 200px"
            >立即注册</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="goToLoginPage"
            >已有账号，点击登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style lang="less" scoped>
.register_container {
  background-color: #2b4b6b;
  height: 100%;
}
.register_box {
  width: 450px;
  height: 350px;
  background-color: #fff;
  border-radius: 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.btn {
  display: flex;
  justify-content: center;
}
.register_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 70px;
  transform: translate(0, 5%);
  box-sizing: border-box;
}
</style>
<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入账号"));
      } else {
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      isAdmin: false,
      adminCode: false,
      form: {
        status: "stu",
        username: "",
        password: "",
      },
      rules: {
        status: [],
        username: [{ validator: validatePass, trigger: "blur" }],
        password: [{ validator: validatePass2, trigger: "blur" }],
      },
      status: [
        {
          value: "stu",
          label: "学生",
        },
        {
          value: "admin",
          label: "管理员",
        },
      ],
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (!valid) return console.log("error valid");
        this.$http
          .post("/users/register", this.form)
          .then((result) => {
            console.log(result);
            if (result.data.code !== 200)
              return this.$message.error("注册失败");
            this.$message.success("注册成功");
            // window.sessionStorage.setItem('token', result.data.token)
            this.$router.push("/users/login");
          })
          .catch((error) => {
            // 【务必注意】这里的error输出的不是一个对象【error.response才是一个对象】
            console.log(error);
            if (error.response) {
              // 请求已发出，但服务器响应的状态码不在 2xx 范围内
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
            } else {
              // Something happened in setting up the request that triggered an Error
              console.log("Error", error.message);
            }
            console.log(error.config);
          });
      });
    },
    beAdmin() {
      if (this.isAdmin) this.adminCode = true;
      else this.adminCode = false;
    },
    goToLoginPage() {
      this.$router.push("/users/login");
    },
  },
};
</script>
