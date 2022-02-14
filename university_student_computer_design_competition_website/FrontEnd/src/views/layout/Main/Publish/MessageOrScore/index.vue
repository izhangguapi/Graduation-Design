<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">比赛列表</span>
          </div>
          <el-table :data="data" fit :current-row-key="data.contestId">
            <el-table-column prop="contestTitle" label="比赛名称" align="center"></el-table-column>
            <el-table-column prop="number" label="报名人数" width="100%" align="center"></el-table-column>
            <el-table-column fixed="right" label="评审操作" width="100%" align="center">
              <template v-slot="scope">
                <el-button type="text" size="small"
                           @click="$router.push({name: 'publish-message', params: {contestId: scope.row.contestId}})">发送消息
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "MessageOrScore",
  data() {
    return {
      data: [],
    }
  },
  mounted() {
    getRequest("/contests/gid", {gid: sessionStorage.gid}).then((res) => {
      this.data = res.data.data;
      console.log(this.data)
    })
  }
  // methods: {
  //   clickTable(row) {
  //     this.show = !this.show;
  //     console.log(row.contestId)
  //   }
  // }
}
</script>

<style scoped>

</style>