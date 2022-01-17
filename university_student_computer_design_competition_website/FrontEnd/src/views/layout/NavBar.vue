<template>
  <el-menu :default-active="this.$route.path" route mode="horizontal">
      <el-menu-item v-for="(item,i) in navBarList" :key="i" :index="item.path">
        <i :class="item.icon"></i>{{ item.title }}</el-menu-item>
  </el-menu>
</template>

<script>

export default {
  data(){
    return{
      navBarList:[
        {path:'/home', title: '首页',icon: 'el-icon-s-home'},
        {path:'/find',title: '发现',icon: 'el-icon-s-opportunity'}
      ]
    }
  },
  mounted() {
    console.log(this.$router.options.routes);
  }
}
</script>

<style lang='less' scoped>

</style>
