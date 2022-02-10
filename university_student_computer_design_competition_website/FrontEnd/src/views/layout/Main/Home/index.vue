<template>
  <div>
    <!--跑马灯-->
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <Banner></Banner>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <!--左侧卡片-->
      <el-col :span="12" :offset="4">
        <competitionList></competitionList>
      </el-col>
      <!--右侧公告通知-->
      <el-col :span="4">
        <AnnouncementList></AnnouncementList>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import competitionList from "../Find/CompetitionList";
import AnnouncementList from "./AnnouncementList";
import Banner from "./Banner";

export default {
  name: "Home",
  components: {Banner, competitionList, AnnouncementList},
  data() {
    return {
      carouselImages: [
        {url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png', link: 1},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903844.gif', link: 2},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903848.jpg', link: 3},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg', link: 4},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903853.jpg', link: 5}
      ],
    }
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
  text-decoration:none;
}
</style>