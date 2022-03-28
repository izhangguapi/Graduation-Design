<template>
  <el-menu :default-active="$route.path" class="el-menu-vertical-demo" router :collapse="isCollapse">
    <div class="collapse" @click="isCollapse=!isCollapse">
      <i :class="isCollapse?'fa fa-angles-right':'fa fa-angles-left'"></i>
    </div>
    <template v-for="(item,key) in menuList">
      <el-menu-item v-if="item.index" :index="item.index">
        <i :class="item.icon"></i>
        <span slot="title">{{ item.title }}</span>
      </el-menu-item>
      <el-submenu v-else :index="key+''">
        <template slot="title">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.title }}</span>
        </template>
          <el-menu-item v-for="(child,childKey) in item.children" :index='child.index' :key="key+'-'+childKey">
            {{ child.childrenTitle }}
          </el-menu-item>
      </el-submenu>
    </template>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      isCollapse: false,
      menuList: [{
        title: '首页',
        index: '/admin',
        icon: 'fa fa-house-user'
      }, {
        title: '组管理',
        index: '/admin/groups',
        icon: 'fa fa-user-group'
      }, {
        title: '用户管理',
        index: '/admin/users',
        icon: 'fa fa-users-gear'
      }, {
        title: '比赛管理',
        index: '/admin/contests',
        icon: 'fa fa-flag'
      }, {
        title: '消息管理',
        index: '/admin/messages',
        icon: 'fa fa-comment-dots'
      }, {
        title: '报名评分管理',
        index: '/admin/scores',
        icon: 'fa fa-receipt'
      }, {
        title: '审核管理',
        icon: 'fa fa-list-check',
        children: [{
          // 发布比赛审核，消息审核
          childrenTitle: '审核比赛',
          index: '/admin/reviewContests'
        }]
      }]
    }
  }
}
</script>

<style lang="less" scoped>
.collapse {
  height: 56px;
  line-height: 56px;
  font-size: 20px;
  color: #F56C6C;
  padding: 0 20px;
  cursor: pointer;
  text-align: center;
}

.el-menu {
  height: 100%;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
}
</style>