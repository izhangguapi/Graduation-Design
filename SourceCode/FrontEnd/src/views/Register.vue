<template>
  <div class="registerContainer">
    <!-- 注册表单 -->
    <el-form :rules="rules" ref="registerForm" :model="registerForm" label-position="right" label-width="70px"
             :validate-on-rule-change="false">
      <!-- logo-->
      <img src="src/assets/logo.png" alt=""/>
      <el-tabs v-model="activeSting" stretch>
        <!-- 第一步-->
        <el-tab-pane label="注册账号" name="0" disabled>
          <!-- 姓名 -->
          <el-form-item prop="name" label="姓名：">
            <el-input prefix-icon="fa fa-user-graduate" v-model="registerForm.name" placeholder="请输入0-5位汉字"></el-input>
          </el-form-item>
          <!-- 输入用户手机号 -->
          <el-form-item prop="phone" label="电话：">
            <el-input prefix-icon="fa-solid fa-phone" v-model="registerForm.phone" placeholder="请输入11位数字"></el-input>
          </el-form-item>
          <!-- 输入用户邮箱 -->
          <el-form-item prop="email" label="邮箱：">
            <el-input prefix-icon="fa fa-envelope" v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <!-- 输入用户密码 -->
          <el-form-item prop="password" label="密码：">
            <el-popover placement="top-start" width="200" trigger="hover"
                        content="请输入6-16位密码，且至少包含一位数字和字母，可用特殊字符有$@!%*?&.">
              <el-input prefix-icon="fa fa-unlock-keyhole" v-model="registerForm.password" show-password
                        placeholder="请输入6-16位密码" slot="reference"></el-input>
            </el-popover>
          </el-form-item>
        </el-tab-pane>
        <!-- 第二步-->
        <el-tab-pane label="输入信息" name="1" disabled>
          <!-- 学校 -->
          <el-form-item prop="school" label="学校：">
            <el-input prefix-icon="fa fa-university" v-model="registerForm.school"></el-input>
          </el-form-item>
          <!-- 性别 -->
          <el-form-item prop="sex" label="性别：">
            <el-radio class="radioL" v-model="registerForm.sex" label="true" border>男</el-radio>
            <el-radio class="radioR" v-model="registerForm.sex" label="false" border>女</el-radio>
          </el-form-item>
          <!-- 出生年月日 -->
          <el-form-item prop="birthday" label="生日：">
            <el-date-picker v-model="registerForm.birthday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
                            prefix-icon="fa fa-calendar-days" value-format="yyyy-MM-dd"
                            style="width: 100%"></el-date-picker>
          </el-form-item>
          <!-- 地址 -->
          <el-form-item prop="address" label="地址：">
            <el-input prefix-icon="fa fa-map-marker" v-model="registerForm.address"></el-input>
          </el-form-item>
        </el-tab-pane>
        <!-- 第三步-->
        <el-tab-pane name="2" disabled style="text-align: center;" label="选择身份">
          <!-- 选择用户身份 -->
          <el-select placeholder="请选身份" :disabled="selectDisabled" style="width: 100%;margin: 20px auto 30px auto"
                     v-model="selectIdentity">
            <el-option key="1" label="学生" :value="1" @click.native="student"></el-option>
            <el-option key="2" label="老师" :value="2" @click.native="teacher"></el-option>
          </el-select>
        </el-tab-pane>
      </el-tabs>
      <el-steps :active="active" align-center>
        <el-step title="第一步"></el-step>
        <el-step title="第二步"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>
      <div class="btn">
        <el-button v-if="active < 2" @click="next()" type="primary" round>下一步</el-button>
        <el-button v-if="active === 3" @click="submitForm" type="success" round>提&nbsp;&nbsp;&nbsp;&nbsp;交</el-button>
      </div>
      <el-row>
        <el-link type="primary" @click="goToLoginPage" style="float:right;padding: 10px 0 5px 0;">已有账号，点击登录</el-link>
      </el-row>
      <!--对话框-->
      <el-dialog title="加入组或者创建组" :visible.sync="outerVisible" center width="20%">
        <div style="text-align: center">
          <el-button type="primary" @click="joinGroup" round>加入组</el-button>
          <el-button type="primary" @click="innerVisible = true" round>创建组</el-button>
        </div>
        <el-dialog width="30%" title="创建组" :visible.sync="innerVisible" append-to-body>
          <el-form :rules="groupRules" ref="group" :model="group">
            <el-form-item prop="groupName" label="名称：">
              <el-input v-model="group.groupName" maxlength="10" show-word-limit placeholder="请输入组名称"></el-input>
            </el-form-item>
            <el-form-item prop="encoding" label="编码：">
              <el-input v-model="group.encoding" maxlength="5"  show-word-limit placeholder="请输入五位组编码"
                        @blur="group.encoding = group.encoding.replace(/\s*/g,'').toUpperCase()"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="danger" @click="innerVisible = false" round>取消</el-button>
            <el-button type="primary" @click="insertGroup" round>确定</el-button>
          </span>
        </el-dialog>
      </el-dialog>
      <!--对话框结束-->
    </el-form>
  </div>
</template>

<script>
import {getRequest, postRequest} from "@/utils/api";

export default {
  name: "register",
  // 数据
  data() {
    return {
      active: 0,//控制步骤 默认0
      selectIdentity: "",// 选择身份
      registerForm: {//提交的表单信息
        phone: '',
        email: '',
        password: '',
        name: '',
        sex: '',
        birthday: '',
        school: '',
        address: '',
        groupId: '',
      },
      rules: {},//form表单校验
      // 添加组的表单
      group: {
        groupName: '',
        encoding: ''
      },
      //创建组的form表单校验
      groupRules: {
        groupName: [{required: true, message: "名称不能为空", trigger: "blur"},
          {pattern: /^[\u4e00-\u9fa5_a-zA-Z\d]{1,10}$/, message: "请输入1-10位字符"}],
        encoding: [{required: true, message: "编码不能为空", trigger: "blur"},
          {pattern: /^[\da-zA-Z]{5}$/, message: "请输入五位字母或数字组合字符"}]
      },
      outerVisible: false,// 控制外层选择加入创建弹出框显示关闭
      innerVisible: false,// 控制内层创建组弹出框显示关闭
      selectDisabled: false// 控制选择身份的禁用与开启
    };
  },
  // 监视属性
  watch: {
    // 根据页面动态设置表单校验规则
    active: {
      handler() {
        switch (this.active) {
          case 0:
            // // 定义姓名的格式
            // const name = (rule, value, callback) => {
            //   if (value) {
            //     let rgx = /^[\u4e00-\u9fa5_a-zA-Z0-9-]{2,12}$/;
            //     if (value.match(rgx) == null) {
            //       return callback(new Error('请输入2-12位汉字、字母、数字'))
            //     } else {
            //       callback();
            //     }
            //   }
            // };
            // // 定义手机号的格式
            // const phone = (rule, value, callback) => {
            //   if (value) {
            //     let rgx = /^[1]+[0-9]{10}$/;
            //     if (value.match(rgx) == null) {
            //       return callback(new Error('请检查输入格式'))
            //     } else {
            //       callback();
            //     }
            //   }
            // };
            // // 定义邮箱的格式
            // const email = (rule, value, callback) => {
            //   if (value) {
            //     let rgx = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            //     if (value.match(rgx) == null) {
            //       return callback(new Error('请检查邮箱的格式'))
            //     } else {
            //       callback();
            //     }
            //   }
            // };
            // // 定义密码的格式
            // const password = (rule, value, callback) => {
            //   if (value) {
            //     //         (?=.*\\d.*)(?=.*[a-zA-Z].*)
            //     let rgx = /^(?=.*\d)(?=.*[a-zA-Z])[A-Za-z\d$@!%*?&.]{6,16}$/;
            //     if (value.match(rgx) == null) {
            //       return callback(new Error('请检查密码格式'))
            //     } else {
            //       callback();
            //     }
            //   }
            // };
            // 设置form表单第一步校验规则
            this.rules = {
              name: [{required: true, message: "姓名不能为空", trigger: "blur"},
                {pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9-]{1,12}$/, message: "请输入1-12位汉字、字母、数字"}],
              phone: [{required: true, message: "电话不能为空", trigger: "blur"},
                {pattern: /^[1]+[0-9]{10}$/, message: "请检查手机号的格式"}],
              email: [{required: true, message: "邮箱不能为空", trigger: "blur"},
                {pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: "请检查邮箱的格式"}],
              password: [{required: true, message: "密码不能为空", trigger: "blur"},
                {pattern: /^(?=.*\d)(?=.*[a-zA-Z])[A-Za-z\d$@!%*?&.]{6,16}$/, message: "请检查密码格式"}]
            };
            break;
          case 1:
            // 设置form表单第二步校验规则
            this.rules = { // 设置第二步分的form表单校验
              school: [{required: true, message: "学校不能为空", trigger: "blur"},],
              sex: [{required: true, message: "性别不能为空", trigger: "blur"},],
              birthday: [{required: true, message: "生日不能为空", trigger: "blur"},],
              address: [{required: true, message: "地址不能为空", trigger: "blur"},]
            };
            break
        }
      },
      immediate: true
    }
  },
  // 方法
  methods: {
    // 下一步
    next() {
      // 表单校验
      this.$refs.registerForm.validate((valid) => {
        if (valid) { // 校验成功
          // 判断当前是第几步
          if (this.active === 0) { // 第一步
            //查询输入的手机号或者邮箱是否存在如果存在则清空手机号和邮箱，否则进入下一步
            postRequest("/registerVerify", this.registerForm).then((resp) => {
              if (resp) {
                // Message.success(resp.data.msg)
                this.active++;
              } else {
                // Message.error(resp.data.msg)
                this.registerForm.phone = '';
                this.registerForm.email = '';
              }
            });
          } else {
            this.active++;
          }
        } else { // 校验失败
          this.$message.error("请输入完整再进行下一步吧！");
        }
      });
    },
    // 提交表单
    submitForm() {
      // 第三步输入完成进行判断/register
      postRequest("/register", this.registerForm).then((res) => {
        console.log(res.data)
        // if (res.data.data) {
        //   // 登录成功将信息存入session
        //   sessionStorage.setItem("uid", res.data.data)
        //   sessionStorage.setItem("name", this.registerForm.name);
        //   sessionStorage.setItem("gid", this.registerForm.groupId);
        //   this.$router.push("/");
        // } else {
        //   this.active = 0;
        // }
      });
    },
    // 跳转到登录
    goToLoginPage() {
      this.$router.push("/login");
    },
    // 选择学生
    student() {
      this.registerForm.groupId = 2;
      this.active = 3;
    },
    // 选择老师
    teacher() {
      this.active = 2;
      this.selectIdentity = '';
      this.outerVisible = true;
    },
    // 创建组
    insertGroup() {
      this.$refs.group.validate((valid) => {
        if (valid) {
          console.log(1)
          postRequest("/insertGroup", this.group).then((resp) => {
            if (resp) {
              this.registerForm.groupId = resp.data.data;
              this.innerVisible = false;
              this.outerVisible = false;
              this.selectDisabled = true;
              this.active = 3;
              this.selectIdentity = 2;
            } else {
              this.selectIdentity = 0;
            }
          });
        }
      })
    },
    // 加入组
    joinGroup() {
      this.$prompt('请输入5为组编码', '加入或创建组', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[0-9A-Za-z]{5}$/,
        inputErrorMessage: '编码格式不正确',
        roundButton: true
      }).then(({value}) => {
        //去数据库搜索输入的编码是否存在 groupId JSON.stringify(
        getRequest("/encoding/" + value.toUpperCase()).then((resp) => {
          console.log(resp)
          if (resp.data.data) {
            this.$message({type: 'success', message: '加入组成功'});
            this.registerForm.groupId = resp.data.data[0].groupId;
            this.innerVisible = false;
            this.outerVisible = false;
            this.selectDisabled = true;
            this.active = 3;
          } else {
            console.log(resp.data.groupId)
            this.$message({type: 'warning', message: '暂无该组，请创建'});
            this.group.encoding = value.toUpperCase();
            this.innerVisible = true;
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    }
  },
  // 计算属性
  computed: {
    activeSting: {
      get() {
        let active = this.active;
        if (this.active === 3) {
          active -= 1;
        }
        return active.toString();
      },
      set(newValue) {
        return newValue;
      }
    }
  }
};

</script>

<style lang="less" scoped>
.registerContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 200px auto;
  width: 400px;
  padding: 15px 35px;
  background: #fff;
  border: 1px solid #eeeeee;
  box-shadow: 0 0 25px #cac6c6;
}

.btn {
  margin-top: 10px;
  text-align: center;
}

.radioL {
  margin-right: 10px;
  text-align: center;
  width: 155px;
}

.radioR {
  margin-left: 10px;
  margin-right: 0;
  text-align: center;
  width: 155px;
}
</style>