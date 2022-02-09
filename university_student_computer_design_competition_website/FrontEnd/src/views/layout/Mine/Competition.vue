<template>
  <el-card class="box-card" shadow="never" style="height: 552px">
    <div slot="header" class="clearfix">
      <span>报名过的比赛</span>
    </div>
    <div v-for="item in contests" :key="item.scoresId" class="text item">
      <el-link  @click="$router.push({name:'find-result',params: {scoresId:item.scoresId}})">{{item.contestTitle}}</el-link>
    </div>
    <div v-if="contests.length===0" class="text item">
      <el-link>你还没有报名哦。</el-link>
    </div>
  </el-card>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Competition",
  data() {
    return {
      contests: []
    }
  },
  mounted() {
    getRequest("/contests/user/" + sessionStorage.uid).then((res) => {
       const data = res.data.data;
      console.log(data);
      if (data) {
        this.contests = data;
      } else {

      }
    });
  },
  methods:{

  }
}
</script>

<style scoped>

</style>