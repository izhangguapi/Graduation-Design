<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>比赛列表</span>
      </div>
      <div class="list">
        <el-row>
          <el-col v-for="(item,i) in lists" :span="9" :key="item.contestId" :offset="2" style="margin-bottom: 30px">
            <el-card :body-style="{ padding: '0' }">
              <router-link :to="{ name: 'find-detail', params: {contestId: item.contestId,name:item.name} }">
                <img :src="item.url" class="image" alt="">
              </router-link>
              <div style="padding: 14px;">
                <span>{{ item.name }}</span>
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
export default {
  name: "competitionList",
  data() {
    return {
      currentDate: '2021-06-01',
      lists: [{
        contestId: '1',
        name: '比赛1',
        url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png'
      }, {
        contestId: '2',
        name: '比赛2',
        url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png'
      }, {
        contestId: '3',
        name: '比赛3',
        url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png'
      }, {
        contestId: '4',
        name: '比赛4',
        url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png'
      }, {
        contestId: '5',
        name: '比赛5',
        url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png'
      }],
    }
  },
  mounted() {


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
  height: 200px;
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