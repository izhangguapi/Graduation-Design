<template>
  <div>
    <div class="box">
      <el-button type="danger" icon="fa fa-trash-can" class="mr20" size="mini"
                 :disabled="deleteButton" @click="deleteGroups">批量删除
      </el-button>
      <el-button type="primary" icon="fa fa-plus" size="mini" class="mr20" @click="insertOrUpdate()">添加</el-button>
      <el-input v-model="pageQuery.query" placeholder="请输入搜索值" class="box-input mr20" size="mini" clearable></el-input>
      <el-button type="primary" icon="fa fa-magnifying-glass" size="mini" :disabled="searchButton"
                 @click="getGroupsList">搜索
      </el-button>
    </div>
    <el-table :data="tableData" @selection-change="handleSelectionChange" border fit>
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="groupId" label="ID" align="center"></el-table-column>
      <el-table-column prop="groupName" label="组名称" align="center"></el-table-column>
      <el-table-column prop="encoding" label="组编码" align="center"></el-table-column>
      <el-table-column prop="number" label="人数" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot="scope">
          <el-button type="warning" size="mini" @click="insertOrUpdate(scope.row)" round>编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageQuery.currentPage"
        :page-sizes="[10, 25, 50]"
        :page-size="pageQuery.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <el-dialog :title="groupTitle" :visible.sync="dialog" width="20%">
      <el-form :model="group" :rules="groupRules" ref="group">
        <el-form-item label="组名称" prop="groupName">
          <el-input v-model="group.groupName" autocomplete="off" maxlength="10" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="组编码" prop="encoding">
          <el-input v-model="group.encoding" autocomplete="off" maxlength="5" show-word-limit
                    @input="group.encoding = group.encoding.replace(/\s*/g, '').toUpperCase()"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">取 消</el-button>
        <el-button v-show="group.groupId" type="primary" @click="updateGroup">修 改</el-button>
        <el-button v-show="!group.groupId" type="primary" @click="insertGroups">添 加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {postRequest, putRequest} from "@/utils/api";

export default {
  name: "Groups",
  data() {
    return {
      // 分页查询参数
      pageQuery: {
        query: null,
        currentPage: 1,
        pageSize: 10
      },
      // 总数
      total: 0,
      // 对话框显示隐藏
      dialog: false,
      multipleSelection: [],
      tableData: [],
      groupTitle: '',
      group: {
        groupName: '',
        encoding: ''
      },
      groupRules: {
        groupName: [{required: true, message: "名称不能为空", trigger: "blur"},
          {pattern: /^[\u4e00-\u9fa5_a-zA-Z\d]{1,10}$/, message: "请输入1-10位字符"}],
        encoding: [{required: true, message: "编码不能为空", trigger: "blur"},
          {pattern: /^[\da-zA-Z]{5}$/, message: "请输入五位字母或数字组合字符"}]
      }
    }
  },
  computed: {
    deleteButton() {
      return !this.multipleSelection.length;
    },
    searchButton() {
      return !this.pageQuery.query;
    }
  },
  // 监听搜索输入框，为空就查询
  watch: {
    "pageQuery.query"(newValue) {
      if (newValue.trim() === "") {
        this.getGroupsList();
      }
    }
  },
  methods: {
    // 初始页currentPage、初始每页数据数pageSize和数据data
    handleSizeChange(size) {
      this.pageQuery.pageSize = size;
      this.getGroupsList();
    },
    handleCurrentChange(currentPage) {
      this.pageQuery.currentPage = currentPage;
      this.getGroupsList();
    },
    // 查询用户组
    getGroupsList() {
      postRequest("/groups/list", this.pageQuery).then(res => {
        // console.log(res.data);
        this.tableData = res.data.data.records;
        this.total = res.data.data.total;
      })

    },
    // 多选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 删除用户组
    deleteGroups() {
      postRequest("/deleteGroups", this.multipleSelection).then(res => {
        if (res) {
          this.$message.success("删除成功。");
          this.getGroupsList();
        }
      })
    },
    // 判断是添加还是修改
    insertOrUpdate(data) {
      if (data === undefined) {
        this.groupTitle = '添加用户组';
        this.group = {};
      } else {
        this.groupTitle = '修改用户组';
        this.group = data;
      }
      this.dialog = true;
    },
    // 添加用户组
    insertGroups() {
      // console.log("添加")
      this.$refs.group.validate((valid) => {
        if (valid) {
          postRequest("/insertGroup", this.group).then(res => {
            this.$message.success(res.data.msg);
            this.getGroupsList();
            this.dialog = false;
          });
        }
      })
    },
    // 修改用户组
    updateGroup() {
      // console.log("修改")
      this.$refs.group.validate((valid) => {
        if (valid) {
          putRequest("/updateGroup", this.group).then(res => {
            if (res.data.status) {
              this.$message.success("修改成功。");
              this.getGroupsList();
            } else this.$message.error("修改失败！");
          });
        }
        this.dialog = false;
      })
    }
  },
  mounted() {
    this.getGroupsList();
  }
}
</script>

<!--<style lang="less" scoped>-->
<!--.box {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.box-input {-->
<!--  width: 200px;-->
<!--  display: inline-block;-->
<!--}-->

<!--.mr20 {-->
<!--  margin-right: 20px;-->
<!--}-->
<!--</style>-->