<template>
    <div>
      <!--跑马灯-->
      <el-carousel :interval="4000" type="card" height="300px">
        <el-carousel-item v-for="(item,i) in carouselImages" :key="i">
          <img class="carouselImg" :src="item.url" :alt="item.link"/>
        </el-carousel-item>
      </el-carousel>
      <!--布局-->
      <el-row :gutter="20">
        <!--左侧卡片-->
        <el-col :span="16">
          <el-card>
            <div slot="header" class="clearfix">
              <span>卡片名称</span>
              <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{'列表内容 ' + o }}
            </div>
          </el-card>
        </el-col>
        <!--右侧公告通知-->
        <el-col :span="8">
          <el-card>
            <div slot="header" class="clearfix">
              <span>公告通知</span>
            </div>
            <div v-for="i in 5" :key="i" class="text item">
              {{'列表内容 ' + i }}
            </div>
            <router-link class="more-link" :to="{name: 'find'}">
              查看更多...
            </router-link>
          </el-card>
        </el-col>
      </el-row>
    </div>
</template>

<script>
export default {
  name: "Home",
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
.carouselImg {
  width: 100%;
  height: 100%;
}
.more-link {
  /*padding: 0 10px 10px;*/
  display: block;
  text-align: center;
  font-size: 16px;
  letter-spacing: 3px;
  font-weight: 500;
  color: #659fdc;
}
</style>