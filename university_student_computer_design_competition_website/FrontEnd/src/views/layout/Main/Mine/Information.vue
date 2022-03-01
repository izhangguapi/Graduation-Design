<template>
  <el-card class="box-card" shadow="never" style="height: 500px">
    <el-descriptions title="用户信息" :column="1" border :labelStyle="{'text-align': 'center'}">
      <template slot="extra" v-if="inputDisabled">
        <el-button type="primary" @click="update" size="small" round>修改信息</el-button>
      </template>
      <template slot="extra" v-else>
        <el-button type="success" @click="submit" size="small" round>保存修改</el-button>
        <el-button type="danger" @click="cancel" size="small" round>取消修改</el-button>
      </template>
      <el-descriptions-item label="姓名">
        <input :disabled="inputDisabled" v-model="fromData.name"></input>
      </el-descriptions-item>
      <el-descriptions-item label="用户组" :contentStyle="{backgroundColor:inputDisabled?'#fff':'#fafafa'}">
        <div :style="{backgroundColor:inputDisabled?'#fff':'#fafafa'}">
          {{ groupName }}
          <el-button type="danger" @click="upDateGroup" size="small" round v-if="groupName!=='学生'">
            修改用户组
          </el-button>
        </div>
      </el-descriptions-item>
      <el-descriptions-item label="性别" :contentStyle="{backgroundColor:inputDisabled?'#fff':'#fafafa'}">
        <div :style="{backgroundColor:inputDisabled?'#fff':'#fafafa'}"> {{ sex }}</div>
      </el-descriptions-item>
      <el-descriptions-item label="出生日期">
        <el-date-picker :disabled="inputDisabled" v-model="fromData.birthday" type="date"
                        value-format="yyyy-MM-dd" format="yyyy 年 MM 月 dd 日"></el-date-picker>
      </el-descriptions-item>
      <el-descriptions-item label="学校">
        <input :disabled="inputDisabled" v-model="fromData.school"></input>
      </el-descriptions-item>
      <el-descriptions-item label="手机号" :contentStyle="{backgroundColor:inputDisabled?'#fff':'#fafafa'}">
        <div :style="{backgroundColor:inputDisabled?'#fff':'#fafafa'}"> {{ phone }}</div>
      </el-descriptions-item>
      <el-descriptions-item label="邮箱" :contentStyle="{backgroundColor:inputDisabled?'#fff':'#fafafa'}">
        <div :style="{backgroundColor:inputDisabled?'#fff':'#fafafa'}"> {{ email }}</div>
      </el-descriptions-item>
      <el-descriptions-item label="地址">
        <textarea :disabled="inputDisabled" v-model="fromData.address"></textarea>
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>

<script>
import {getRequest, putRequest} from "@/utils/api";
import {login} from "@/utils/login";

export default {
  name: "information",
  data() {
    return {
      inputDisabled: true,
      fromData: {
        userId: '',
        name: '',
        birthday: '',
        school: '',
        address: ''
      },
      sex: '',
      groupName: '',
      phone: '',
      email: ''
    }
  },
  mounted() {
      const uid = this.fromData.userId = this.$store.state.uid;
      getRequest("/user/" + uid).then((res) => {
        const data = res.data.data;
        if (data) {
          this.fromData.name = data.name;
          data.sex ? this.sex = '男' : this.sex = '女';
          this.fromData.birthday = data.birthday;
          this.fromData.school = data.school;
          this.fromData.address = data.address;
          this.phone = data.phone;
          this.email = data.email;
          this.groupName = data.groupName;
        }
      });
  },
  methods: {
    update() {
      this.inputDisabled = false;
    },
    submit() {
      if (this.fromData.name.trim() === '' || this.fromData.birthday.trim() === '' || this.fromData.address.trim() === '' || this.fromData.school.trim() === '') {
        this.$message.error("不允许为空");
      } else {
        putRequest("/user/update", this.fromData).then((res) => {
          if (res.data.data) {
            this.$message.success("修改成功");
            this.inputDisabled = true;
          } else {
            this.$message.error("修改失败");
          }
        });
      }
    },
    cancel() {
      this.inputDisabled = true;
    },
    upDateGroup() {
      alert("修改用户组");
    }
  }
}
</script>

<style scoped>
input, textarea {
  border: none;
  outline: none;
  width: 100%;
  background-color: #fff;
  resize: none;
}
</style>