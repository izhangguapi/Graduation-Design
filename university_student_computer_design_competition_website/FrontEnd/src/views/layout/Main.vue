<template>
  <el-main>
    <transition name="fade" mode="out-in">
      <router-view>
      </router-view>
    </transition>
  </el-main>
</template>

<style lang='less'>
.el-main {
  width: 100%;
  padding-top: 80px;
  min-height: 800px;
}
.el-card:hover{
  box-shadow: 0 2px 12px 0 rgb(255 100 100 / 80%);
}
</style>