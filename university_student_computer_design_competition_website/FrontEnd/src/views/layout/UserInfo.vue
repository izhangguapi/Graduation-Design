<template>
  <div class="user-info">
    <el-badge :value="msg" :max="99" :hidden="hidden" class="item">
      <el-button size="small">消息</el-button>
    </el-badge>
    <el-dropdown>
      <div class="el-dropdown-link">
        <el-link>
          <el-avatar :size="48" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-link>
      </div>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click="goToPersonalPage">个人中心</el-dropdown-item>
        <el-dropdown-item divided>发布比赛</el-dropdown-item>
        <el-dropdown-item >发布消息</el-dropdown-item>
        <el-dropdown-item >发布评分</el-dropdown-item>
        <el-dropdown-item>后台管理</el-dropdown-item>
        <el-dropdown-item divided>退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {
      msg: 5, //右上角消息数量
      hidden: false,//控制小圆圈是否显示，如果msg为0则值为true
    }
  },
  methods: {
    goToPersonalPage() {
      // this.$router.push("/Personal");
    }
  }
}
</script>

<style lang="less" scoped>
// 用户
.user-info {

  height: 100%;
  background-clip: content-box;
  padding: 0 10px;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  .item {
    margin-top: 10px;
    margin-right: 30px;
  }

  //.avatar {
  //  margin: 0;
  //  height: 44px;
  //  cursor: pointer;
  //}

}
</style>