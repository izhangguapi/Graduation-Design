<template>
  <div>
    <div class="navigation">
      <el-tabs tab-position="left" value="publish-competition" @tab-click="handleClick">
        <el-tab-pane label="发布比赛" name="publish-competition"></el-tab-pane>
        <el-tab-pane label="发布消息" name="publish-message"></el-tab-pane>
        <el-tab-pane label="评审比赛" name="publish-score"></el-tab-pane>
      </el-tabs>
    </div>
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>

export default {
  name: "index",
  data() {
    return {};
  },
  mounted() {
    if (sessionStorage.gid === '2') {
      this.$message.warning("此功能暂未对学生开发！");
      this.$router.go(-1);
    }
  },
  methods: {
    handleClick(tab) {
      // console.log(tab.$parent.currentName);
      this.$router.push({name: tab.$parent.currentName});
    }
  }
}
</script>

<style scoped>
.navigation {
  position: fixed;
  top: 40%;
  left: 0;
  z-index: 255;
}
</style>