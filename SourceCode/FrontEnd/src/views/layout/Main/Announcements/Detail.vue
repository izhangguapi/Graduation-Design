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
            <div class="title">{{ data.title }}</div>
            <div class="time">{{ data.time }}</div>
            <div class="text">{{ data.text }}</div>
            <div class="name">
              <el-tag type="success">{{ data.name }}</el-tag>
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
      data:{}
    }
  },
  watch: {
    $route() {
      this.announcementLoading();
    }
  },
  mounted() {
    this.announcementLoading();
  },
  methods: {
    announcementLoading() {
      getRequest("/messages/announcement/" + this.$route.params.messageId).then((res) => {
        const data = res.data.data;
        // console.log(data);
        if (data.recipient === 1 || data.recipient === this.$store.state.uid) {
          this.data = data;
          if (this.data.recipient !== this.data.sender && !this.data.status) {
            // console.log(this.$route.params.messageId)
            putRequest("/messages/status", {messageId: this.$route.params.messageId, status: true});
          }
        } else {
          this.$message.error("消息查询失败！");
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
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