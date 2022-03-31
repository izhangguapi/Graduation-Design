<template>
  <el-container>
    <!--左侧功能-->
    <Aside></Aside>
    <!--左侧功能结束-->
    <el-container>
      <!--顶部功能-->
      <el-header>
        <Header></Header>
      </el-header>
      <!--顶部功能结束-->
      <!--顶部标签-->
      <el-main v-if="isAdmin">
        <!-- 此处放置el-tabs代码 -->
        <div>
          <el-tabs v-model="activeIndex" type="border-card" @tab-click='clickTab'
                   @tab-remove='removeTab'>
            <el-tab-pane v-for="item in tabs" :key="item.name" :label="item.name" :name="item.route"
                         :closable="item.name !=='首页'">
            </el-tab-pane>
            <router-view/>
          </el-tabs>
        </div>
      </el-main>
      <!--顶部标签结束-->
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/views/Admin/Aside";
import Header from "@/views/Admin/Header";

export default {
  name: "Admin",
  components: {Aside, Header},
  methods: {
    //tab标签点击时，切换相应的路由
    clickTab(tab) {
      this.$router.push({path: this.activeIndex});
    },
    // 删除标签页
    removeTab(targetName) {
      this.$store.commit('removeTab', targetName);
      if (this.activeIndex === targetName) {
        // 设置当前激活的路由
        if (this.tabs && this.tabs.length >= 1) {
          this.$store.commit('setActiveIndex', this.tabs[this.tabs.length - 1].route);
          this.$router.push({path: this.activeIndex});
        } else {
          this.$router.push({path: '/'});
        }
      }
    }
  },
  mounted() {
    // 判断不是管理员则返回上一层
    if (!this.isAdmin) {
      this.$router.push("/login");
    }else {
      // 刷新时以当前路由做为tab加入tabs
      if (this.$route.path !== '/admin') {
        // 当前路由不是首页时，添加首页添加到store里，并设置激活状态
        this.$store.commit('addTab', {route: this.$route.path, name: this.$route.meta["title"]});
        this.$store.commit('setActiveIndex', this.$route.path);
      }
    }
  },
  computed: {
    isAdmin(){
      return this.$store.state.isAdmin;
    },
    tabs() {
      return this.$store.state.tabs;
    },
    activeIndex: {
      get() {
        return this.$store.state.activeIndex;
      },
      set(val) {
        this.$store.commit('setActiveIndex', val);
      }
    }
  },
  watch: {
    '$route'(to) {
      // 判断标签是否已经打开
      // 如果已经打开的 ，将其置为active
      // 否则，将其放入队列里
      let flag = false;
      for (let item of this.tabs) {
        if (item.route === to.path) {
          this.$store.commit('setActiveIndex', to.path)
          flag = true;
          break;
        }
      }
      if (!flag) {
        this.$store.commit('addTab', {route: to.path, name: to.meta.title});
        this.$store.commit('setActiveIndex', to.path);
      }
    },
    "$store.state.isAdmin"() {
      // 判断不是管理员则返回上一层
      if (!this.isAdmin) {
        this.$router.push("/login");
      }
    }
  }
}
</script>

<style lang="less">
.el-container {
  height: 100%;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
}

.el-tabs__nav .el-tabs__item:nth-child(1) span {
  display: none;
}

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