<template>
  <el-menu :default-active="this.$route.path" router mode="horizontal">
      <el-menu-item v-for="(item,i) in navBarList" :key="i" :index="item.path">
        <i :class="item.icon" style="font-size: 18px;"></i>{{ item.title }}</el-menu-item>
  </el-menu>
</template>

<script>

export default {
  data(){
    return{
      navBarList:[
        {path:'/home', title: '首页',icon: 'fa fa-house-chimney'},
        {path:'/find',title: '发现',icon: 'fa fa-infinity'},
        {path:'/publish',title: '发布',icon: 'fa fa-flag'},
        {path:'/mine',title: '个人中心',icon: 'fa fa-graduation-cap'},
      ]
    }
  },
  mounted() {
    console.log(this.$router.options.routes);

    console.log(this.$route.path);
  }
}
</script>

<style lang='less' scoped>
.el-menu{
  border: none;
  i{
    margin-right: 5px;
  }
}

</style>
