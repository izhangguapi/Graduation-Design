<template>
  <div>
    <div class="navigation">
      <el-tabs tab-position="left" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="发布比赛" name="competition"></el-tab-pane>
        <el-tab-pane label="管理比赛" name="select"></el-tab-pane>
      </el-tabs>
    </div>
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>

export default {
  name: "Publish",
  data() {
    return {
      activeName: ''
    };
  },
  mounted() {
    if (this.$store.state.gid === '2') {
      this.$message.warning("此功能暂未对学生开发！");
      this.$router.go(-1);
    }
    this.activeName = this.$route.path.split("/").length === 2 ? "competition" : "select";
    console.log(this.$route.path.split("/").length);
  },
  methods: {
    handleClick(tab) {
      console.log("publish-" + tab.$parent.currentName);
      this.$router.push({name: "publish-" + tab.$parent.currentName});
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