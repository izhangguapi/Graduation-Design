<template>
  <div>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-card shadow="never" class="box-card">
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">搜索结果</span>
          </div>
          <el-table :data="data" :current-row-key="data.contestId" fit @row-click="clickTable">
            <el-table-column prop="contestTitle" label="标题" resizable align="center"></el-table-column>
            <el-table-column prop="regEndTime" label="报名结束时间" resizable align="center">
              <template v-slot="scope">
                <el-tag :type="new Date(scope.row.regEndTime)<new Date() ? 'danger' : 'success'" disable-transitions>
                  {{ scope.row.regEndTime }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
           <!--分页-->
          <!--<el-pagination layout="prev, pager, next" :total="total" style="text-align: center" @current-change="page"></el-pagination>-->
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

export default {
  name: "Search",
  data() {
    return {
      search: '',
      data:[]
    }
  },
  watch: {
    '$route'(to) {
      this.search = to.params.search;
    },
    search() {
      this.loading(this.search);
    }
  },
  mounted() {
    this.search = this.$route.params.search;
  },
  methods: {
    loading(query) {
      getRequest("/search?query="+query).then((res)=>{
        this.data = res.data.data;
      })
    },
    clickTable(row){
      this.$router.push({name: 'find-detail', params: {contestId: row.contestId}})
    }
  }
}
</script>

<style lang="less" scoped>

</style>