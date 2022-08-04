<template>
  <div>
    <!--跑马灯-->
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <LatestContest></LatestContest>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <!--左侧卡片-->
      <el-col :span="12" :offset="4">
        <ContestList></ContestList>
      </el-col>
      <!--右侧公告通知-->
      <el-col :span="4">
        <Announcements></Announcements>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ContestList from "../Find/ContestList";
import Announcements from "./Announcements";
import LatestContest from "./LatestContest";

export default {
  name: "Home",
  components: {LatestContest, ContestList, Announcements}
}
</script>
<style lang="less">
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
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
</style>