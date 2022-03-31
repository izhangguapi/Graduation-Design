<template>
  <div>
    <el-button @click="logOut">退出登录</el-button>
  </div>
</template>

<script>
export default {
  name: "Header",
  methods: {
    // 退出登录
    logOut() {
      localStorage.clear();
      this.$router.push("/login");
      this.$store.state.isAdmin = false;
      this.$store.state.uid = undefined;
      this.$store.state.name = undefined;
      this.$store.state.gid = undefined;
    },
  }
}
</script>

<style scoped lang="less">
div {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: flex-end;
}

.el-button {
  align-self: center;
}
</style>