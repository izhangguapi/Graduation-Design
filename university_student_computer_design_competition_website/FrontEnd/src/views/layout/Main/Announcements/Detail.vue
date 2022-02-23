<template>
  <div>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-card shadow="never" class="box-card">
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">公告详情</span>
            <el-button @click="$router.go(-1)" type="primary" round style="float: right;" size="mini">返回</el-button>
          </div>
          <div style="padding: 20px 20%">
            <div class="title">{{ title }}</div>
            <div class="time">{{ time }}</div>
            <div class="text">{{ text }}</div>
            <div class="name">
              <el-tag type="success">{{ name }}</el-tag>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest, putRequest} from "@/utils/api";

export default {
  name: "Announcement",
  data() {
    return {
      title: '',
      time: '',
      text: '',
      name: '',
    }
  },
  watch: {
    $route() {
      this.announcementLoading();
    }
  },
  mounted() {
    putRequest("/messages/state",{messageId:this.$route.params.messageId,state:true});
    this.announcementLoading();
  },
  methods: {
    announcementLoading() {
      getRequest("/messages/announcement/" + this.$route.params.messageId).then((res) => {
        const data = res.data.data;
        if (data.recipient === 1 || data.recipient.toString() === this.$store.state.uid) {
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

.text {
  font-size: 15px;
  color: #606266;
  white-space: pre-wrap;
}

.name {
  font-size: 15px;
  color: #606266;
  float: right;
  margin: 20px 0;
}
</style>