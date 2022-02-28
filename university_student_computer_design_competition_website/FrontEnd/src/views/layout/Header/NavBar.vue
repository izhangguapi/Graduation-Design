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
  computed: {
    activeMenu() {
      const path = this.$route.path.split("/");
      return "/" + path[1];
    },
    navBarList(){
      const gid = this.$store.state.gid;
      if (gid === '2' || gid === undefined) {
        return [
          {path: '/home', title: '首页', icon: 'fa fa-house-chimney'},
          {path: '/find', title: '发现', icon: 'fa fa-infinity'},
          {path: '/mine', title: '个人中心', icon: 'fa fa-graduation-cap'}
        ];
      } else {
        return [
          {path: '/home', title: '首页', icon: 'fa fa-house-chimney'},
          {path: '/find', title: '发现', icon: 'fa fa-infinity'},
          {path: '/publish', title: '发布', icon: 'fa fa-flag'},
          {path: '/mine', title: '个人中心', icon: 'fa fa-graduation-cap'}
        ];
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
