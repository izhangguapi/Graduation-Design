<template>
  <div>
    <div class="box">
      <!--<el-button type="danger" icon="fa fa-trash-can" class="mr20" size="mini"-->
      <!--           :disabled="deleteButton" @click="deleteGroups">批量删除-->
      <!--</el-button>-->
      <!--<el-button type="primary" icon="fa fa-plus" size="mini" class="mr20" @click="insertOrUpdate()">添加</el-button>-->
      <el-input v-model="pageQuery.query" placeholder="请输入搜索值" class="box-input mr20" size="mini" clearable></el-input>
      <el-button type="primary" icon="fa fa-magnifying-glass" size="mini" @click="getUserList">搜索</el-button>
    </div>

    <el-table :data="tableData" style="width: 100%" border>
      <el-table-column prop="userId" label="ID" align="center"></el-table-column>
      <el-table-column prop="name" label="姓名" align="center"></el-table-column>
      <el-table-column prop="sex" label="性别" :formatter="sexFormat" align="center"></el-table-column>
      <el-table-column prop="school" label="学校" align="center"></el-table-column>
      <el-table-column prop="address" label="地址" align="center"></el-table-column>
      <el-table-column prop="phone" label="电话" align="center"></el-table-column>
      <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
      <el-table-column prop="groupName" label="用户组" align="center"></el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template v-slot="scope">
          <el-button type="text" size="small" @click="updateStudent(scope.row)">编辑</el-button>
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

    <!-- 修改信息 -->
    <el-dialog title="提示" :visible.sync="update" width="30%">
      <el-form :model="updateData" ref="updateData" label-width="100px" class="demo-ruleForm" :rules="rules"
               :visible.sync="update">
        <el-form-item label="姓名">
          <el-input v-model.number="updateData.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="updateData.sex">
            <el-radio :label="true">男</el-radio>
            <el-radio :label="false">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日">
          <div class="block">
            <el-date-picker v-model="updateData.birthday" type="date" placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="学校">
          <el-input v-model.number="updateData.school" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model.number="updateData.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model.number="updateData.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model.number="updateData.email" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="update = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('updateData')">提交</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import {postRequest, putRequest} from "@/utils/api";

export default {
  name: "Users",
  data() {
    return {
      // 修改数据
      updateData: {},
      update: false,
      // 详情展示
      drawer: false,
      // 详情表格数据
      studentData: {},
      // 表格数据
      tableData: [],
      // 总数
      total: 0,
      // 分页查询参数
      pageQuery: {
        query: null,
        currentPage: 1,
        pageSize: 10
      },
      // 手机号验证
      rules: {
        phone: [
          {message: "请输入手机号码", trigger: "change"},
          {validator: this.checkPhone, trigger: "change"},
        ],
        age: [
          {required: true, message: "年龄不能为空"},
          {type: "number", message: "年龄必须为数字值"},
        ],
      },
    };
  },
  methods: {
    updateStudent(row) {
      this.update = true;
      this.updateData = row;
    },

    //数据修改提交
    submitForm() {
      putRequest("/user/update", this.updateData).then((res) => {
        // console.log(res)
        if (res.data.code === 200) {
          this.update = false;
          this.$message({
            message: "修改成功",
            type: "success",
          });
        }
      });
    },

    //验证手机号
    checkPhone(rule, value, callback) {
      if (!/^1[34578]\d{9}$/.test(value)) {
        callback(new Error("请输入正确的手机号码!"));
      }
    },

    //性别解析
    sexFormat(row) {
      if (row.sex) {
        return "男";
      } else {
        return "女";
      }
    },
    // 初始页currentPage、初始每页数据数pageSize和数据data
    handleSizeChange(size) {
      this.pageQuery.pageSize = size;
      this.getUserList();
    },
    handleCurrentChange(currentPage) {
      this.pageQuery.currentPage = currentPage;
      this.getUserList();
    },
    //查所有用户
    getUserList() {
      postRequest("/users/list", this.pageQuery).then((res) => {
        // console.log(res.data);
        this.tableData = res.data.data.records;
        this.total = res.data.data.total;
      });
    },
  },
  mounted() {
    this.getUserList();
  },
};
</script>

<style scoped>
</style>