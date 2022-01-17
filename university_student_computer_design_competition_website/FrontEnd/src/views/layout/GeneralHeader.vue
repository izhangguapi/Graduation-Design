<template>
  <el-header>
    <el-row :gutter="20">
      <el-col :span="4">
        <img src="../../assets/image.png" alt="logo" class="logo">
      </el-col>
      <el-col :span="8">
        <NavBar></NavBar>
      </el-col>
      <el-col :span="8">
        <div class="search">
          <input placeholder="活动搜索..."/>
          <i class="el-icon-search"></i>
        </div>
      </el-col>
      <el-col :span="4">
        <UserInfo></UserInfo>
      </el-col>
    </el-row>

  </el-header>
</template>

<script>
import NavBar from "@/views/layout/NavBar";
import UserInfo from "@/views/layout/UserInfo";

export default {
  name: "GeneralHeader",
  components: {NavBar, UserInfo},
  data() {
    return {
      input: ''
    }
  }
}
</script>
<style lang="less">


.el-header {
  box-shadow: 0 1px 3px rgba(50, 50, 50, 0.5);

  .el-row{
    height: 100%;
  }

  //logo
  .logo {
    margin: 8px 0;
    height: 44px;
  }

  //搜索框
  .search {
    height: 100%;

    input {
      width: 70%;
      margin: 10px auto;
      height: 40px;
      line-height: 40px;
      border: none;
      outline: none;
      border-bottom: 1px solid #aaaaaa;

      &:focus {
        border: none;
        border-bottom: 1px solid #ff6464;
      }
    }

    i {
      cursor: pointer;
      speak: none;
      font-style: normal;
      font-weight: 400;
      font-variant: normal;
      text-transform: none;
      line-height: 1;
      font-size: 20px !important;
      margin: 0 auto;
      border: none !important;
      background-color: #fff;
      transition: color .2s linear, background-color .2s linear, border .2s linear, box-shadow .2s linear;

      &:hover {
        color: #ff6464;
      }
    }
  }


}


</style>
