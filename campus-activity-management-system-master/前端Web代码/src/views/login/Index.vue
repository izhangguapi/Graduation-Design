<template>
  <div class="login-page">
    <Card class="login-card">
      <h2 class="login-title" slot="title">
        <img
          class="logo"
          src="https://www.yishuzi.cn/image.png?fsize=30&font=cmll.ttf&text=mineCampus&mirror=no&color=f49bc1&vcolor=6ccff7&bgcolor=fff&alpha=no&output=png&spacing=4&shadow=no&transparent=no&icon=no&iconic=&top_spacing=5&left_spacing=6&icon_size=48"
        />
      </h2>
      <!-- <h3 class="login-text">校园活动服务平台</h3> -->
      <Form
        class="login-form"
        :model="user"
        label-position="left"
        :rules="ruleInline"
        ref="loginForm"
      >
        <FormItem class="item" label="账号" prop="username">
          <Input
            class="item-content"
            type="text"
            v-model="user.username"
          ></Input>
        </FormItem>
        <FormItem class="item" label="密码" prop="password">
          <Input
            class="item-content"
            type="password"
            v-model="user.password"
          ></Input>
        </FormItem>
        <FormItem class="login-btn">
          <Button
            type="primary"
            size="large"
            :loading="isFetching"
            @click.prevent="submit"
            ghost
            class="login-button"
            >登 录</Button
          ><Button type="text" class="register-button" @click="register"
            >立即注册</Button
          >
        </FormItem>
      </Form>
    </Card>
    <div class="bg">
      <LazyloadImg :src="target"></LazyloadImg>
    </div>
  </div>
</template>

<script>
import { login } from "@/store/api/auth";
import LazyloadImg from "@/components/LazyloadImg";

export default {
  components: { LazyloadImg },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      ruleInline: {
        username: [
          {
            required: true,
            message: "请填写有效的账号.",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请填写密码.",
            trigger: "blur",
          },
        ],
      },
      isFetching: false,
      target: "https://i.loli.net/2019/04/23/5cbf0a3fe48e9.png",
    };
  },
  methods: {
    submit() {
      this.$refs.loginForm.validate((isValid) => {
        if (isValid) {
          this.login();
        } else {
          this.$Message.error("请输入有效的账号密码");
        }
      });
    },
    login() {
      this.isFetching = true;
      window.sessionStorage.setItem('password', this.user.password);
      login(this.user)
        .then((res) => {
          this.$Message.success("登录成功");
          this.isFetching = false;
          window.sessionStorage.setItem('userprofile', res.data.id);
          // // 管理员或者超级管理员进入后台
          if (res.data.status === "admin") {
            this.$router.push({ name: "admin-activities" });
          }

          // 学生进入前台
          if (res.data.status === "stu") {
            // const { query } = this.$route;
            // const to = query.hasOwnProperty("q") ? query.q : { name: "home" };
            // this.$router.replace(to);
            this.$router.push({ name: "activity" });
          }
        })
        .catch(() => {
          this.isFetching = false;
          this.$Message.error("登陆失败,请检查账号和密码是否正确！");
        });
    },
    register() {
      this.$router.push({ name: "register" });
    },
  },
};
</script>

<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.login {
  &-page {
    width: 100%;
    height: 785px;
    position: relative;
    background: white;

    .ivu-form-item-label {
      font-size: 15px;
      line-height: 2;
      display: inline-block;
      width: 60px;
    }

    .logo {
      width: 300px;
    }
  }
  &-card {
    width: 470px;
    border: none;
    position: absolute;
    top: 32%;
    left: 50%;
    z-index: 2;
    transform: translate(-50%, -50%);

    .ivu-card-head {
      border-bottom: none;
      padding: 15px 0 0;
    }
    .ivu-card-body {
      padding: 0 50px;
    }
  }
  &-title {
    text-align: center;
    // position: absolute;
    // top: 16%;
    // left: 50%;
    // transform: translate(-50%, -50%);
  }

  &-text {
    text-align: center;
  }

  &-btn {
    text-align: center;
    .login-button {
      padding: 10px 80px;
      font-size: 16px;
      font-weight: 400;
    }
    .register-button {
      padding: 20px 80px 0px 80px;
      font-size: 10px;
    }
  }
}
.login-form {
  .item-content {
    width: 80%;
  }
}
.bg {
  width: 100%;
  text-align: center;
  position: absolute;
  bottom: 0;
}
</style>
