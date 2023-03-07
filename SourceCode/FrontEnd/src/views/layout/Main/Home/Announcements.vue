<template>
  <div>
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <span style="line-height: 28px;font-size: 20px">公告通知</span>
      </div>
      <div v-for="item in list" :key="item.messageId" class="text item">
        <el-link @click="$router.push({name:'messages-detail',params: {messageId:item.messageId}})">
          {{ item.title }}
        </el-link>
      </div>
      <router-link v-if="list" class="more-link" :to="{name:'announcement'}">
        查看更多...
      </router-link>
      <div v-else>暂无数据</div>
    </el-card>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "AnnouncementList",
  data() {
    return {
      // 虚假数据
      list: []
    }
  },
  mounted() {
    // 获取公告
    getRequest("/announcement").then((res) => {
      if (res.data){
        this.list = res.data.data;
      }
    });
  }
}
</script>

<style lang="less" scoped>
.more-link {
  padding-top: 10px;
  display: block;
  text-align: center;
  font-size: 16px;
  letter-spacing: 3px;
  font-weight: 500;
  color: #659fdc;
  text-decoration: none;
}
</style>