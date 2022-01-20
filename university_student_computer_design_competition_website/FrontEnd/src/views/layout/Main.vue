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
  padding: 80px 5% 20px;
  min-height: 800px;
}
</style>