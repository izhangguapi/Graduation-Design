<template>
  <el-menu :default-active="activeMenu" mode="horizontal" active-text-color="#F56C6C" router>
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
      navBarList: []
    }
  },
  mounted() {
    this.loadingMenu();
  },
  methods: {
    loadingMenu() {
      let gid = this.$store.state.gid;
      let navBarList = [{path: '/', title: '首页', icon: 'fa fa-house-chimney'},
        {path: '/find', title: '发现', icon: 'fa fa-infinity'},
        {path: '/mine', title: '个人中心', icon: 'fa fa-graduation-cap'}]
      if (gid && gid !== 2) {
        navBarList.splice(2, 0, {path: '/manage', title: '管理', icon: 'fa fa-flag'});
      }
      this.navBarList = navBarList;
    }
  },
  computed: {
    activeMenu() {
      return "/" + this.$route.path.split("/")[1];
    }
  },
  watch: {
    "$store.state.gid"() {
      this.loadingMenu();
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