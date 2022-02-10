<template>
  <div>
    <el-card class="box-card" shadow="never" style="margin-bottom: 20px;">
      <div slot="header" class="clearfix">
        <span>已评审的比赛</span>
      </div>
      <div v-for="item in contests" :key="item.scoresId" class="text item">
        <el-link @click="$router.push({name:'find-result',params: {scoresId:item.scoresId}})">{{ item.contestTitle }}
        </el-link>
      </div>
      <div v-if="contests.length===0 && contestsUnfinished.length===0" class="text item">
        <el-link>你还没有报名任何比赛哦</el-link>
      </div>
    </el-card>
    <el-card class="box-card" shadow="never">
      <div slot="header" class="clearfix">
        <span>未评审的比赛</span>
      </div>
      <div v-for="item in contestsUnfinished" class="text item">
        <el-link @click="$message.info('该比赛还未进行评审！')">{{ item.contestTitle }}</el-link>
      </div>
      <div v-if="contests.length===0 && contestsUnfinished.length===0" class="text item">
        <el-link>你还没有报名任何比赛哦</el-link>
      </div>
    </el-card>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Competition",
  data() {
    return {
      contests: [],
      contestsUnfinished: []
    }
  },
  mounted() {
    getRequest("/scores/contest/" + sessionStorage.uid).then((res) => {
      const data = res.data.data;
      console.log(data);
      if (data) {
        this.contests = data;
      }
    });
    getRequest("/scores/contestNot/" + sessionStorage.uid).then((res) => {
      const data = res.data.data;
      console.log(data);
      if (data) {
        this.contestsUnfinished = data;
      }
    });
  },
  methods: {}
}
</script>

<style scoped>

</style>