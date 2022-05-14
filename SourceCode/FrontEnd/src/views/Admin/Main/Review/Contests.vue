<template>
  <div>
    <div class="box">
      <el-input v-model="input" placeholder="请输入搜索值" class="box-input mr20" size="mini" clearable></el-input>
      <el-button type="primary" icon="fa fa-magnifying-glass" size="mini" :disabled="searchButton"
                 @click="searchContests">搜索
      </el-button>
    </div>
    <el-table :data="tableData" border fit>
      <el-table-column prop="contestId" label="ID" align="center"></el-table-column>
      <el-table-column prop="contestTitle" label="比赛标题" align="center"></el-table-column>
      <el-table-column prop="groupName" label="所属组" align="center"></el-table-column>
      <el-table-column prop="name" label="发布人" align="center"></el-table-column>
      <el-table-column label="评审状态" align="center">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status" type="success">通过</el-tag>
          <el-tag v-else-if="scope.row.statusText" type="danger">失败</el-tag>
          <el-tag v-else type="warning">审核中</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot="scope">
          <el-button type="warning" size="mini" @click="check(scope.row)" round>查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="比赛详情" :visible.sync="dialog" width="50%">

      <img :src="srcUrl" alt="封面图" style="width: 100%;height: auto">
      <mdEditor v-model="contestText" :ishljs="true" :subfield="false" :toolbars="{}" defaultOpen="preview"
                style="min-height: 300px" :toolbarsFlag="false"/>

      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="pass">通过</el-button>
        <el-button type="danger" @click="fail">不通过</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import {getRequest, putRequest} from "@/utils/api";
import mdEditor from "mavon-editor";

export default {
  name: "ReviewContests",
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    return {
      input: '',
      dialog: false,
      multipleSelection: [],
      tableData: [],
      contestText: '',
      srcUrl:'',
      operation: {
        contestId: null,
        status: false,
        statusText: null
      }
    }
  },
  computed: {
    deleteButton() {
      return !this.multipleSelection.length;
    },
    searchButton() {
      return !this.input;
    }
  },
  // 监听搜索输入框，为空就查询
  watch: {
    input(newValue) {
      if (newValue.trim() === "") {
        this.selectContests();
      }
    }
  },
  methods: {
    // 查询用户组
    selectContests() {
      getRequest("/selectContests").then(res => {
        // console.log(res.data.data)
        this.tableData = res.data.data;
      })
    },
    // 搜索用户组
    searchContests() {
      // console.log(this.input.trim())
      getRequest("/searchContests", {query: this.input.trim()}).then(res => {
        this.tableData = res.data.data;
      })
    },
    check(row) {
      this.operation.contestId = row.contestId;
      this.contestText = row.contestText;
      this.srcUrl = row.url;
      this.dialog = true;
    },
    pass() {
      this.operation.status = true;
      this.updateContest();
    },
    fail() {
      this.$prompt('请输入不予通过的理由', '不通过', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({value}) => {
        this.operation.statusText = value;
        this.updateContest();
      });
    },
    updateContest() {
      putRequest("/updateContest", this.operation).then(res => {
        if (res.data.status) {
          this.$message.success("审核成功。");
          this.selectContests();
        } else {
          this.$message.error("审核失败！");
        }
      })
      this.dialog = false;
    }
  },
  mounted() {
    this.selectContests();
  }
}
</script>

<style scoped>

</style>