<template>
  <el-menu :default-active="$route.path" class="el-menu-vertical-demo" router>
    <el-menu-item index="/admin">
      <i class="el-icon-menu"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu v-for="(item,index) in menuList" :key="index" :index="item.id">
      <template slot="title">
        <i :class="item.icon"></i>
        <span>{{ item.title }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="child in item.children" :key="child.id" :index='child.index'>
          {{ child.childrenTitle }}
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      menuList: [{
        id:'1',
        title: '导航1',
        icon: 'el-icon-menu',
        children: [{
          id:'1-1',
          index: '/admin/users',
          childrenTitle: '用户管理'
        }, {
          id:'1-2',
          index: '/admin/groups',
          childrenTitle: '组管理'
        }]
      }, {
        id:'2',
        title: '导航2',
        icon: 'el-icon-menu',
        children: [{
          id:'2-1',
          index: '/admin/contests',
          childrenTitle: '比赛管理'
        }, {
          id:'2-2',
          index: '/admin/messages',
          childrenTitle: '消息管理'
        }, {
          id:'2-3',
          index: '/admin/scores',
          childrenTitle: '报名评分管理'
        }]
      }]
    }
  }
}
</script>

<style scoped>

</style>