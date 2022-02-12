<template>
  <el-carousel type="card" :height="height+'px'">
    <el-carousel-item v-for="(item,i) in carouselImages" :key="i">
      <img class="carouselImg" :src="item.url" :alt="item.link" ref="height" @load="imgLoad"/>
    </el-carousel-item>
  </el-carousel>
</template>

<script>

export default {
  name: "Banner",
  data() {
    return {
      height: '',
      carouselImages: [
        {url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png', link: 1},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903844.gif', link: 2},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903848.jpg', link: 3},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg', link: 4},
        {url: 'https://pic.imgdb.cn/item/61e95e482ab3f51d91903853.jpg', link: 5}
      ],
    }
  },
  //跑马灯高度自适应
  mounted() {
    window.addEventListener('resize', () => {
      this.height = this.$refs.height[0].height;
      this.imgLoad();
    }, false)
  },
  methods: {
    imgLoad() {
      this.$nextTick(() => {
        this.height = this.$refs.height[0].height;
      })
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