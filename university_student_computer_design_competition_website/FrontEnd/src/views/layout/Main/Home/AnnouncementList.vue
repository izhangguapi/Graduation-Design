<template>
<div>
  <el-card shadow="never">
    <div slot="header" class="clearfix">
      <span>公告通知</span>
    </div>
    <div v-for="i in 5" :key="i" class="text item">
      {{ '列表内容 ' + i }}
    </div>
    <router-link class="more-link" :to="{path:'/'}">
      查看更多...
    </router-link>
  </el-card>
</div>
</template>

<script>
export default {
  name: "AnnouncementList"
}
</script>

<style scoped>
.more-link {
  padding-top: 10px;
  display: block;
  text-align: center;
  font-size: 16px;
  letter-spacing: 3px;
  font-weight: 500;
  color: #659fdc;
  text-decoration:none;
}
</style>