<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>比赛列表</span>
      </div>
      <div class="list">
        <el-row>
          <el-col v-for="(item) in lists" :span="9" :key="item.contestId" :offset="2" style="margin-bottom: 30px">
            <el-card :body-style="{ padding: '0' }">
              <router-link :to="{ name: 'find-detail', params: {contestId: item.contestId,name:item.name} }">
                <img :src="item.url" class="image" alt="">
              </router-link>
              <div style="padding: 14px;">
                <span style="text-overflow: ellipsis;text-overflow: ellipsis;">{{ item.name }}</span>
                <div class="bottom clearfix">
                  <time class="time">{{ currentDate }}-{{ currentDate }}</time>
                  <el-button type="text" class="button">操作按钮</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <router-link class="more-link" :to="{path:'/find'}">
        查看更多...
      </router-link>
    </el-card>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "competitionList",
  data() {
    return {
      currentDate: '2021-06-01',
      lists: [{
        contestId: '1',
        name: '2021年度全国大学生算法设计编程挑战赛',
        url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png'
      }, {
        contestId: '2',
        name: '2021年度阿里巴巴人工智能对抗算法竞赛',
        url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png'
      }, {
        contestId: '3',
        name: '2021年度中国国际艺术博览会设计视觉形象设计大赛',
        url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png'
      }, {
        contestId: '4',
        name: '2022年度世界大学生超级计算机竞赛',
        url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png'
      }, {
        contestId: '5',
        name: '2022年度中国高校计算机大赛-网络技术挑战赛',
        url: 'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png'
      }],
    }
  },
  mounted() {
    getRequest("/Scores").then((resp) => {
      console.log(resp.data);
    });
  },
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
  text-decoration: none;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  height: auto;
  display: block;
}

.clearfix:before, .clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>