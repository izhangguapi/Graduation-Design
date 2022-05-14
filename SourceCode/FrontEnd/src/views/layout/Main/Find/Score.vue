<template>
  <div style="margin-top: 10%;">
    <el-row :gutter="20">
      <el-col :span="10" :offset="7">
        <el-card>
          <el-descriptions :column="3" :title="contestTitle" border :contentStyle="{'text-align': 'center'}"
                           :labelStyle="{'text-align': 'center'}">
            <el-descriptions-item label="参赛人">{{ contestant }}
            </el-descriptions-item>
            <el-descriptions-item label="评审人">{{ judge }}
            </el-descriptions-item>
            <el-descriptions-item label="排名" :contentStyle="{'color':color,'text-align': 'center'}">{{ ranking }}
            </el-descriptions-item>
            <el-descriptions-item label="评语">{{ text }}</el-descriptions-item>
          </el-descriptions>
          <div style="text-align: center;font-size: 30px;font-weight: bold;margin-top: 20px;">
            得分：{{ result }}
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Result",
  data() {
    return {
      scoresId: 0,
      contestId: 0,
      contestant: "",
      judge: "",
      ranking: '',
      result: 0,
      contestTitle: '',
      text: "",
      color: '#409EFF'
    }
  },
  mounted() {
    const id = this.$route.params.scoresId
    if (id) {
      getRequest("/scores/" + id).then((res) => {
        let data = res.data.data
        if (data) {
          this.contestId = data.contestId;
          this.contestTitle = data.contestTitle;
          this.contestant = data.contestant;
          this.judge = data.judge;
          this.result = data.result;
          this.text = data.text;
          this.contestTitle = data.contestTitle;
          if (data.ranking === 0) {
            this.ranking = '未知';
            this.$notify.info({
              title: '排名未更新',
              message: '若排名显示未知，则该比赛并未全部评审完',
              offset: 200,
              duration: 0
            });
            this.color = '#F56C6C';
          } else this.ranking = data.ranking;
        } else this.$message.info("此比赛暂无结果。");
      })
    } else {
      this.$message.error("查询比赛结果失败！！！");
    }
  }
}
</script>

<style lang="less" scoped>

</style>