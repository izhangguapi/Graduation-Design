<template>
  <el-menu :default-active="this.$route.path" :router="true" class="el-menu-demo" mode="horizontal">

      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.path">{{ item.name }}</el-menu-item>

  </el-menu>
</template>

<script>

export default {
  data(){
    return{
      navList:[
        {path:'/home', name:'主页'},
        {path:'/find',name:'发现'}
      ]
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  // 监听属性
  computed: {

  }
}
</script>

<style lang='less' scoped>

</style>
