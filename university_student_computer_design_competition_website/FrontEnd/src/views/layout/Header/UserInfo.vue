<template>
  <div class="user-info">
    <el-badge :value="msg" :max="99" :hidden="badgeHidden" class="item">
      <el-button size="small" icon="fa fa-bell">消息</el-button>
    </el-badge>
    <el-dropdown>
      <div class="el-dropdown-link">
        <el-link>
          <el-avatar :size="48" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-link>
      </div>
      <el-dropdown-menu slot="dropdown">
        <template v-if="isLogin">
          <el-dropdown-item @click.native="goToMine">个人中心</el-dropdown-item>
          <!--<el-dropdown-item divided>发布比赛</el-dropdown-item>-->
          <!--<el-dropdown-item>发布消息</el-dropdown-item>-->
          <!--<el-dropdown-item>发布评分</el-dropdown-item>-->
          <!--<el-dropdown-item>后台管理</el-dropdown-item>-->
          <el-dropdown-item divided @click.native="logOut">退出登录</el-dropdown-item>
        </template>
        <template v-else>
          <el-dropdown-item @click.native="goToLogin">立即登录</el-dropdown-item>
        </template>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import {login} from "@/utils/login";

export default {
  name: "UserInfo",
  data() {
    return {
      msg: 5, //右上角消息数量
      badgeHidden: false, //控制小圆圈是否显示，如果msg为0则值为true
      isLogin: true, // 根据用户是否登录控制显示右上角列表选项
    }
  },
  mounted() {
    // 判断是否存在登录信息
    this.isLogin = login();
  },
  methods: {
    goToLogin() {
      this.$router.push("/login");
    },
    goToMine() {
      this.$router.push("/mine");
    },
    logOut() {
      sessionStorage.clear();
      localStorage.clear();
      this.$router.push("/login");
    }
  }
}
</script>

<style lang="less" scoped>
// 用户
.user-info {
  height: 48px;
  background-clip: content-box;
  padding: 6px 10px;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  .item {
    margin-top: 10px;
    margin-right: 30px;
  }
}
</style>