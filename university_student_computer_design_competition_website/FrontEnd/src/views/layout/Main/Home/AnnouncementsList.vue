<template>
  <div>
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <span>公告通知</span>
      </div>
      <div v-for="item in list" :key="item.messageId" class="text item">
        <el-link @click="$router.push({name:'announcement-detail',params: {messageId:item.messageId}})">
          {{ item.title }}
        </el-link>
      </div>
      <router-link class="more-link" :to="{name:'announcement'}">
        查看更多...
      </router-link>
    </el-card>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "AnnouncementList",
  data() {
    return {
      list: []
    }
  },
  mounted() {
    getRequest("/messages/announcement").then((res) => {
      console.log(res.data.data);
      this.list = res.data.data;
    });
  }
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
  text-decoration: none;
}
</style>