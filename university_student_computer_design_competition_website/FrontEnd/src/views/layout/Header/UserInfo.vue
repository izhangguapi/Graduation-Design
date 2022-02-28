<template>
  <div class="user-info">
    <el-badge :value="unread" :max="99" :hidden="badgeHidden" class="item">
      <el-button size="small" icon="fa fa-bell" @click="drawerControl">消息</el-button>
    </el-badge>
    <el-dropdown>
      <div class="el-dropdown-link">
        <el-link>
          <el-avatar :size="48" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-link>
      </div>
      <el-dropdown-menu slot="dropdown">
        <template v-if="isLogin">
          <el-dropdown-item @click.native="$router.push('/mine')">个人中心</el-dropdown-item>
          <!--<el-dropdown-item divided>发布比赛</el-dropdown-item>-->
          <!--<el-dropdown-item>发布消息</el-dropdown-item>-->
          <!--<el-dropdown-item>发布评分</el-dropdown-item>-->
          <!--<el-dropdown-item>后台管理</el-dropdown-item>-->
          <el-dropdown-item divided @click.native="logOut">退出登录</el-dropdown-item>
        </template>
        <template v-else>
          <el-dropdown-item @click.native="$router.push('/login')">立即登录</el-dropdown-item>
        </template>
      </el-dropdown-menu>
    </el-dropdown>
    <!--消息抽屉-->
    <div>
      <el-drawer
          :append-to-body="true"
          :modal-append-to-body="false"
          :visible.sync="drawer"
          direction="rtl"
          size="50%">
        <el-table :data="data" :current-row-key="data.messageId" fit @row-click="clickTable">
          <el-table-column property="title" label="标题" align="center"></el-table-column>
          <el-table-column property="time" label="时间" align="center"></el-table-column>
          <el-table-column property="name" label="发布人" align="center" width="100%">
            <template v-slot="scope">
              <el-tag :type="scope.row.name === '管理员' ? 'danger' : ''" disable-transitions>
                {{ scope.row.name }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column property="name" label="状态" align="center" width="100%">
            <template v-slot="scope">
              <el-tag :type="scope.row.state ? 'info' : 'warning'" disable-transitions>
                {{ scope.row.state ? '已读' : '未读' }}
              </el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-drawer>
    </div>
  </div>

</template>

<script>
import {getRequest, putRequest} from "@/utils/api";

export default {
  name: "UserInfo",
  data() {
    return {
      unread: 0, //右上角未读消息数量
      badgeHidden : true,
      drawer: false,
      data: []
    }
  },
  computed:{
    // 判断是否存在登录信息
    isLogin(){
      return this.$store.state.isLogin;
    }
  },
  mounted() {
    // 获取未读消息
    this.userInfoLoading();
  },
  methods: {
    drawerControl() {
      this.userInfoLoading();
      this.drawer = true;
    },
    userInfoLoading(){
      console.log("执行消息获取");
      if(this.$store.state.uid){
        getRequest("/messages/recipient/" + this.$store.state.uid).then((res => {
          const data = res.data.data;
          console.log(data);
          let unread = 0;
          for (let i = 0; i < data.length; i++) {
            if (!data[i].state) {
              unread++;
            }
          }
          console.log(unread);
          this.badgeHidden = unread === 0;
          this.unread = unread;
          this.data = data;
        }));
      }
    },
    // 退出登录
    logOut() {
      this.$store.state.isLogin = false;
      this.$store.state.uid = false;
      this.$store.state.name = false;
      this.$store.state.gid = false;
      localStorage.clear();
      this.$router.push("/login");
    },
    // 点击表格一列进行跳转
    clickTable(row) {
      let id = row.messageId;
      // 隐藏抽屉
      this.drawer = false;
      // 跳转页面
      this.$router.push({name: 'messages-detail', params: {messageId: id}});
      // 未读变已读
      putRequest("/messages/state",{messageId:id,state:true});
      // 修改data数据
      this.userInfoLoading();
    }
  }
}
</script>

<style lang="less" scoped>
// 用户
.user-info {
  height: 48px;
  background-clip: content-box;
  padding: 6px 10px;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  .item {
    margin-top: 10px;
    margin-right: 30px;
  }
}
</style>