<template>
  <div>
    <div class="box">
      <el-button type="danger" icon="fa fa-trash-can" class="mr20" size="mini"
                 :disabled="deleteButton" @click="deleteGroups">批量删除
      </el-button>
      <el-button type="primary" icon="fa fa-plus" size="mini" class="mr20" @click="insertOrUpdate()">添加</el-button>
      <el-input v-model="input" placeholder="请输入搜索值" class="box-input mr20" size="mini" clearable></el-input>
      <el-button type="primary" icon="fa fa-magnifying-glass" size="mini" :disabled="searchButton"
                 @click="searchGroups">搜索
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

    <el-dialog :title="groupTitle" :visible.sync="dialog" width="20%">
      <el-form :model="group" :rules="groupRules" ref="group">
        <el-form-item label="组名称" prop="groupName">
          <el-input v-model="group.groupName" autocomplete="off" maxlength="10" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="组编码" prop="encoding">
          <el-input v-model="group.encoding" autocomplete="off" maxlength="5" show-word-limit
                    @blur="group.encoding = group.encoding.replace(/\s*/g,'').toUpperCase()">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog = false">取 消</el-button>
        <el-button v-if="groupTitle==='添加用户组'" type="primary" @click="insertGroups">添 加</el-button>
        <el-button v-else type="primary" @click="updateGroup">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getRequest, postRequest, putRequest} from "@/utils/api";

export default {
  name: "Groups",
  data() {
    return {
      input: '',
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
      return !this.input;
    }
  },
  // 监听搜索输入框，为空就查询
  watch: {
    input(newValue) {
      if (newValue.trim() === "") {
        this.selectGroupsList();
      }
    }
  },
  methods: {
    // 查询用户组
    selectGroupsList() {
      getRequest("/selectGroupsList").then(res => {
        this.tableData = res.data.data;
      })
    },
    // 多选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 删除用户组
    deleteGroups() {
      postRequest("/deleteGroups", this.multipleSelection).then(res => {
        if (res.data.data) {
          this.$message.success("删除成功。");
          this.selectGroupsList();
        } else this.$message.error("删除失败，因为组下有用户。");
      })
    },
    // 搜索用户组
    searchGroups() {
      console.log(this.input.trim())
      getRequest("/searchGroups", {query: this.input.trim()}).then(res => {
        this.tableData = res.data.data;
      })
    },
    // 判断是添加还是修改
    insertOrUpdate(data) {
      if (data === undefined) {
        this.groupTitle = '添加用户组';
        this.group = {};
      } else {
        this.groupTitle = '修改用户组';
        this.group.groupId = data.groupId;
        this.group.groupName = data.groupName;
        this.group.encoding = data.encoding;
      }
      this.dialog = true;
    },
    // 添加用户组
    insertGroups() {
      this.$refs.group.validate((valid) => {
        if (valid) {
          postRequest("/insertGroup", this.group).then(res => {
            if (res.data.code === 200) {
              this.$message.success(res.data.msg);
              this.selectGroupsList();
            } else this.$message.error(res.data.msg);
          });
        }
        this.dialog = false;
      })
    },
    // 修改用户组
    updateGroup() {
      this.$refs.group.validate((valid) => {
        if (valid) {
          putRequest("/updateGroup", this.group).then(res => {
            if (res.data.data) {
              this.$message.success("修改成功。");
              this.selectGroupsList();
            } else this.$message.error("修改失败！");
          });
        }
        this.dialog = false;
      })
    }
  },
  mounted() {
    this.selectGroupsList();
  }
}
</script>

<style lang="less" scoped>
.box {
  margin-bottom: 20px;
}

.box-input {
  width: 200px;
  display: inline-block;
}

.mr20 {
  margin-right: 20px;
}
</style>