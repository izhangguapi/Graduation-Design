<template>
  <div>
    <el-form ref="form" :model="loginForm" class="loginContainer">
      <h3 class="loginTitle">登录</h3>
      <el-form-item>
        <el-input tpye="text" v-model="loginForm.phone" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input tpye="text" v-model="loginForm.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input tpye="text" v-model="loginForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input tpye="text" v-model="loginForm.code" placeholder="点击图片更换验证码"></el-input>

      </el-form-item>
      <img :src="captchaUrl" alt="验证码">
      <el-form-item>
        <el-checkbox>记住我</el-checkbox>
        <el-button type="primary" style="width: 100%" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      captchaUrl:'',
      loginForm: {
        phone: '13886961359',
        email: '',
        password: '13886961359',
        code: ''
      }
    }
  },
  methods:{
    login(){
      getRequest('/users/login',this.loginForm).then(resp=>{
        //alert(resp)
      })
  }
}
};
</script>
<style>
.loginContainer{
  border-radius: 15px ;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px;
  background: #fff;
  border: 1px solid #eeeeee;
  box-shadow: 0 0 25px #cac6c6;
}
.loginTitle{

  text-align: center;
}

</style>