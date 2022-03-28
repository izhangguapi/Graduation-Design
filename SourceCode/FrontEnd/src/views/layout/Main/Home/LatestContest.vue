<template>
  <el-card class="box-card" shadow="never">
    <div style="display: flex;justify-content: space-between;">
      <h1 style="cursor: pointer;" @click="toContest(contest.contestId)">
        最新比赛：<span style="color: #659fdc">{{ contest.contestTitle }}</span>
      </h1>
      <div style="display: flex; flex-direction: column; align-items: flex-end; justify-content: center;">
        <span style="font-size: 14px;color: #909399">{{text}}</span>
        <span :style="{color: spanColor}">{{ day }} 天 {{ hour }} 时 {{ min }} 分 {{ seconds }} 秒</span>
      </div>
    </div>
    <el-divider></el-divider>
    <el-steps :active="active" align-center>
      <el-step title="报名开始" :description="contest.regStartTime"></el-step>
      <el-step title="结束报名" :description="contest.regEndTime"></el-step>
      <el-step title="比赛开始" :description="contest.startTime"></el-step>
      <el-step title="比赛结束" :description="contest.endTime"></el-step>
    </el-steps>
  </el-card>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "LatestContest",
  data() {
    return {
      active: 0,
      text: '',
      day: 0,
      hour: 0,
      min: 0,
      seconds: 0,
      spanColor:'',
      contest: {}
    }
  },
  //跑马灯高度自适应
  mounted() {
    getRequest("/latestContest").then(res => {
      let data = res.data.data;
      this.contest = data;

      let nowTime = new Date();
      let regStartTime = new Date(data.regStartTime);
      let regEndTime = new Date(data.regEndTime);
      let startTime = new Date(data.startTime);
      let endTime = new Date(data.endTime);
      if (nowTime < regStartTime) {
        this.formatTimeStamp(Math.floor((regStartTime - nowTime) / 1000))
        this.text = '距离报名开始还剩';
        this.spanColor='#67C23A';
        this.active = 0;
      } else if (nowTime < regEndTime) {
        this.formatTimeStamp(Math.floor((regEndTime - nowTime) / 1000));
        this.text = '距离报名结束还剩';
        this.spanColor='#E6A23C';
        this.active = 1;
      } else if (nowTime < startTime) {
        this.formatTimeStamp(Math.floor((startTime - nowTime) / 1000));
        this.text = '距离比赛开始还剩';
        this.spanColor='#409EFF';
        this.active = 2;
      } else if (nowTime < endTime) {
        this.formatTimeStamp(Math.floor((endTime - nowTime) / 1000));
        this.text = '距离比赛结束还剩';
        this.spanColor='#F56C6C';
        this.active = 3;
      } else {
        this.text = '比赛已结束';
        this.active = 4;
      }
    })
    // if (this.contest.regStartTime);
  },
  methods: {
    // 跳转页面
    toContest() {
      this.$router.push("/find/" + this.contest.contestId);
    },
    // 计算时间 倒计时
    formatTimeStamp(timeStamp) {
      let day;
      let hour;
      let min;
      let seconds;

      setInterval(() => {
        // 计算整数天数
        day = Math.floor(timeStamp / 86400);
        // 取得算出天数后剩余的秒数
        let afterDay = timeStamp - day * 86400;
        // 计算整数小时数
        hour = Math.floor(afterDay / 3600);
        // 取得算出小时数后剩余的秒数
        let afterHour = timeStamp - day * 86400 - hour * 3600;
        // 计算整数分
        min = Math.floor(afterHour / 60);
        // 取得算出分后剩余的秒数
        seconds = Math.floor(timeStamp - day * 86400 - hour * 3600 - min * 60);

        this.day = day;
        this.hour = hour;
        this.min = min;
        this.seconds = seconds;

        timeStamp--;
      }, 1000)
    }
  }
}
</script>

<style lang="less" scoped>
.date{
  color: #F56C6C;
}

</style>