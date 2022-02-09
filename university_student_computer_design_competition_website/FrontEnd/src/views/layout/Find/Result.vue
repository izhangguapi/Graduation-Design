<template>
  <div>
    比赛结果
  </div>

</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Result",
  data(){
    return{
      scoresId:'',
    }
  },
  mounted() {
    console.log(this.$route.params.scoresId);
    if (this.$route.params.scoresId){
      alert(this.$route.params.scoresId)
      // getRequest("").then((res)=>{
      //
      // })
    }else {
      this.$message.error("查询比赛结果失败！！！");
      this.$router.go(-1);
    }

  }
}
</script>

<style scoped>

</style>