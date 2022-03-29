<template>
  <div id="app">
    <transition name="fade" mode="out-in">
      <router-view v-if="isRouterAlive"/>
    </transition>
  </div>
</template>

<script>
import {getUser} from "@/utils/login";

export default {
  name: 'App',
  provide() {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true                    //控制视图是否显示的变量
    }
  },
  beforeCreate() {
    getUser();
  },
  methods: {
    reload() {
      this.isRouterAlive = false;            //先关闭，
      this.$nextTick(function () {
        this.isRouterAlive = true;         //再打开
      })
    }
  },
  mounted() {
    if (this.$store.state.isAdmin){
      this.$router.push("/admin");
    }
  },
  watch:{
    "$store.state.isAdmin"() {
      if (this.$store.state.isAdmin){
        this.$router.push("/admin");
      }
    }
  }
}
</script>

<style lang="less">
//body {
//  margin: 0;
//  background-color: #f4f4f4;
//}
html, body, #app {
  height: 100%;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
}

.el-card:hover{
  box-shadow: 0 2px 12px 0 rgb(255 100 100 / 50%);
}

::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

[class*="fa-"] + span {
  margin-left: 5px;
}
</style>