<template>
  <div>
    <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
      <h3 style="text-align: center">登录</h3>
      <el-tabs tab-position="top" style="height: 100px" stretch :before-leave="leaveTab">
        <el-tab-pane label="手机号登录">
          <el-form-item prop="phone">
            <el-input tpye="text" v-model="loginForm.phone" placeholder="请输入手机号"></el-input>
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="邮箱登录">
          <el-form-item prop="email">
            <el-input tpye="text" v-model="loginForm.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <el-form-item prop="password">
        <el-input tpye="text" v-model="loginForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="captcha">
        <el-input tpye="text" v-model="loginForm.captcha" placeholder="点击图片更换验证码" style="width: 60%"></el-input>
        <img :src="'/api/captcha?dateTime=' + loginForm.datetime" @click="updateCaptcha" alt="验证码"
             style="float: right; cursor: pointer"/>
      </el-form-item>
      <el-form-item style="margin-bottom: 0">
        <el-link :underline="false" class="rememberMe">
          <el-checkbox v-model="checked">保持登录</el-checkbox>
        </el-link>
        <el-link href="https://zhangguapi.com" target="_blank" :underline="false" class="forgot">忘记密码？</el-link>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 48%" @click="login" round>登录</el-button>
        <el-button type="success" style="width: 48%; float: right" @click="goToRegisterPage" round>注册</el-button>
      </el-form-item>
    </el-form>
    <div class="img">
      <img src="https://i.loli.net/2019/04/23/5cbf0a3fe48e9.png" alt=""/>
    </div>
  </div>
</template>

<script>
import {postRequest} from "@/utils/api";

export default {
  name: "Login",
  data() {
    return {
      checked:true,
      loginForm: {
        phone: "13886961359",
        email: "",
        password: "13886961359",
        datetime: "",
        captcha: "",
      },
      rules: {
        password: [{required: true, message: "密码不能为空", trigger: "blur"},],
        captcha: [{required: true, message: "验证码不能为空", trigger: "blur"},],
      },
    };
  },
  mounted() {
    // 页面启动给datetime赋值时间戳
    this.loginForm.datetime = new Date().getTime();
    // 判断是否保存了账号信息
    if (localStorage.uid){
      this.$message.success("自动登录成功")
      this.$router.push("/home");
    }
  },
  methods: {
    // 点击更换验证码（将来更换验证方式）
    updateCaptcha() {
      this.loginForm.datetime = new Date().getTime();
    },
    /**
     * 根据tabs标签页返回相应的验证规则
     * @param activeName 切换标签后的新值
     */
    leaveTab(activeName) {
      if (activeName === "0") {
        delete this.rules.email;
        this.rules.phone = [
          {required: true, message: "手机号不能为空", trigger: "blur"},
        ];
        this.loginForm.phone = "";
      } else {
        delete this.rules.phone;
        this.rules.email = [
          {required: true, message: "邮箱不能为空", trigger: "blur"},
        ];
        this.loginForm.email = "";
      }
      this.loginForm.password = "";
    },
    //登录点击事件
    login() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          postRequest("/login", this.loginForm).then((resp) => {
            if (this.checked){
              localStorage.setItem("uid", resp.data.data[0].userId);
              localStorage.setItem("name", resp.data.data[0].name);
              localStorage.setItem("gid", resp.data.data[0].groupId);
            }else {
              sessionStorage.setItem("uid", resp.data.data[0].userId)
              sessionStorage.setItem("name", resp.data.data[0].name);
              sessionStorage.setItem("gid", resp.data.data[0].groupId);
            }
            this.$router.push("/home");
          });
        } else {
          this.$message.error("请输入完整！");
        }
      });
    },
    //跳转注册页面
    goToRegisterPage() {
      this.$router.push("/register");
    }
  },
};
</script>
<style>
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px;
  background: #fff;
  border: 1px solid #eeeeee;
  box-shadow: 0 0 25px #cac6c6;
}

.rememberMe {
  margin: 0 0 10px 0;
}

.forgot {
  float: right;
  margin: 0 0 10px 0;
}

.el-tabs {
  margin-bottom: 22px;
}

.img {
  margin-top: -150px;
  text-align: center;
}
</style>