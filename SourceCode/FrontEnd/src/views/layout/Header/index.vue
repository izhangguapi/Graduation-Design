<template>
  <el-header>
  <el-row :gutter="20">
      <el-col :span="6" :push="1">
        <img src="../../../assets/logo.png" alt="logo" class="logo">
      </el-col>
      <el-col :span="8">
        <NavBar></NavBar>
      </el-col>
      <el-col :span="6">
        <div class="search">
          <input v-model="search" placeholder="搜索比赛" @keyup.enter="press"/>
          <i class="fa fa-magnifying-glass" @click="$router.push({name: 'search', params: {search: search}})"></i>
        </div>
      </el-col>
      <el-col :span="4" :pull="1">
        <UserInfo></UserInfo>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
import NavBar from "@/views/layout/Header/NavBar";
import UserInfo from "@/views/layout/Header/UserInfo";

export default {
  name: "Header",
  components: {NavBar, UserInfo},
  data() {
    return {
      search: ''
    }
  },
  methods:{
    press(){
      this.$router.push({name: 'search', params: {search: this.search}})
    }
  }
}
</script>
<style lang="less" scoped>
.el-header {
  box-shadow: 0 1px 3px rgba(255, 100, 100, 0.5);
  background-color: #fff;
  position: fixed;
  width: 100%;
  z-index: 255;
  overflow: hidden;

  //logo
  .logo {
    margin: 8px 0;
    height: 40px;
  }

  //搜索框
  .search {
    height: 100%;

    input {
      width: 60%;
      margin: 10px auto;
      height: 40px;
      line-height: 40px;
      border: none;
      outline: none;
      border-bottom: 1px solid #aaaaaa;
      transition: color .2s linear, background-color .2s linear, border .2s linear, box-shadow .2s linear;

      &:focus {
        border-bottom: 1px solid #F56C6C;
      }
    }

    i {
      position: relative;
      top: 5px;
      width: auto;
      height: auto;
      display: inline;
      cursor: pointer;
      font-size: 18px;
      margin-top: 50px;
      transition: color .2s linear, background-color .2s linear, border .2s linear, box-shadow .2s linear;

      &:hover {
        color: #F56C6C;
      }
    }
  }
}
</style>
