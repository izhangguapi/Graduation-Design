<template>
  <el-carousel type="card" :height="bannerHeight+'px'">
    <el-carousel-item v-for="(item,i) in carouselImages" :key="i">
      <img class="carouselImg" :src="item.url" :alt="item.link" ref="image" @load="imgLoading"/>
    </el-carousel-item>
  </el-carousel>
</template>

<script>

export default {
  name: "Banner",
  data() {
    return {
      bannerHeight: '',
      count:1,
      carouselImages: [
        {url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png', link: '比赛1'},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903844.gif', link: '比赛2'},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903848.jpg', link: '比赛3'},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg', link: '比赛4'},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903853.jpg', link: '比赛5'}
      ],
    }
  },
  //跑马灯高度自适应
  mounted() {
    window.addEventListener('resize',() => {
      this.bannerHeight=this.$refs.image[0].height;
    },false)
  },
  methods: {
    imgLoading() {
      this.$nextTick(()=>{
        this.bannerHeight=this.$refs.image[0].height;
      });
    }
  }
}
</script>

<style scoped>
.carouselImg {
  width: 100%;
  height: auto;
}
</style>