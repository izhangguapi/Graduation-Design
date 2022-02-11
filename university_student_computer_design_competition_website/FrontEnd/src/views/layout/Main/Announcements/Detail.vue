<template>
  <div>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-card shadow="never" class="box-card">
          <div slot="header" class="clearfix">
            <span>公告详情</span>
            <el-button @click="$router.go(-1)" type="primary" round style="float: right;" size="mini">返回</el-button>
          </div>
          <div style="padding: 20px 100px ">
            <div style="text-align: center;font-weight: bold;font-size: 30px">{{ title }}</div>
            <div style="text-align: center;font-size: 14px;color: #909399">{{ time }}</div>
            <div style="font-size: 15px;color: #606266">{{ text }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Announcement",
  data() {
    return {
      title: "标题",
      time: "时间",
      text: "内容"
    }
  },
  mounted() {
    getRequest("/messages/announcement/" + this.$route.params.messageId).then((res) => {
      const data = res.data.data;
      console.log(data);
      this.title = data.title;
      this.time = data.time;
      this.text = data.text;
    })
  }
}
</script>

<style scoped>

</style>