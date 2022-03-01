<template>
  <div>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-card shadow="never" class="box-card">
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">公告中心</span>
          </div>
          <el-table :data="data" :current-row-key="data.messageId" fit @row-click="clickTable">
            <el-table-column prop="title" label="标题" resizable align="center"></el-table-column>
            <el-table-column prop="time" label="时间" resizable align="center"></el-table-column>
          </el-table>
          <el-pagination layout="prev, pager, next" :total="total" style="text-align: center" @current-change="page"></el-pagination>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "index",
  data() {
    return {
      total:50,
      data: []
    }
  },
  mounted() {
    this.page(1);
  },
  methods: {
    clickTable(row) {
      this.$router.push({name: 'messages-detail', params: {messageId: row.messageId}})
    },
    page(current){
      getRequest("/messages/announcementPage/"+current).then((res) => {
        const data = res.data.data;
        this.total = data.total;
        this.data = data.records;
      })
    }
  }
}
</script>

<style scoped>

</style>