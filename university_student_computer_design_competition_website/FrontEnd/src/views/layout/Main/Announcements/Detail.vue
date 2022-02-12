<template>
  <div>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-card shadow="never" class="box-card">
          <div slot="header" class="clearfix">
            <span>公告详情</span>
            <el-button @click="$router.go(-1)" type="primary" round style="float: right;" size="mini">返回</el-button>
          </div>
          <div style="padding: 20px 20%">
            <div class="title">{{ title }}</div>
            <div class="time">{{ time }}</div>
            <div class="text">{{ text }}</div>
            <div class="name" style="float: right">{{ name }}</div>
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
      id:0,
      title: '',
      time: '',
      text: '',
      name: '',
    }
  },
  watch:{
    $route(to,from){
      console.log(to);
      this.id = to.params.messageId;
      return this.id;
    }
  },
  mounted() {
    getRequest("/messages/announcement/" + this.$route.params.messageId).then((res) => {
      const data = res.data.data;
      console.log(data);
      console.log(data.recipient);
      console.log(sessionStorage.uid);
      if (data.recipient === 1 || data.recipient.toString() === sessionStorage.uid) {
        this.title = data.title;
        this.time = data.time;
        this.text = data.text;
        this.name = data.name;
      } else {
        this.$message.error("消息查询失败！");
      }


    })
  }
}
</script>

<style scoped>
.title {
  text-align: center;
  font-weight: bold;
  font-size: 30px
}

.time {
  margin: 20px 0;
  text-align: center;
  font-size: 14px;
  color: #909399
}

.text ,.name{
  font-size: 15px;
  color: #606266
}
</style>