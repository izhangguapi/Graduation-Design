<template>
  <el-menu :default-active="activeMenu" mode="horizontal" active-text-color="#ff6464" router>
    <el-menu-item v-for="(item,i) in navBarList" :key="i" :index="item.path" :route="item.path">
      <i :class="item.icon" style="font-size: 18px;"></i>{{ item.title }}
    </el-menu-item>
  </el-menu>
</template>

<script>

export default {
  name: "NavBar",
  data() {
    return {
      navBarList: [{path: '/home', title: '首页', icon: 'fa fa-house-chimney'},
        {path: '/find', title: '发现', icon: 'fa fa-infinity'},
        {path: '/management', title: '管理', icon: 'fa fa-flag'},
        {path: '/mine', title: '个人中心', icon: 'fa fa-graduation-cap'}]
    }
  },
  computed: {
    activeMenu() {
      console.log("/" + this.$route.path.split("/")[1]);
      return "/" + this.$route.path.split("/")[1];
    }
  },
  watch: {
    "$store.state.gid"() {
      let gid = this.$store.state.gid;
      console.log(gid);
      if (gid === 2 || gid === undefined || !gid) {
        this.navBarList.splice(2, 1);
      }
    }
  }
}
</script>

<style lang='less' scoped>
.el-menu {
  border: none;

  i {
    margin-right: 5px;
  }
}

</style>
