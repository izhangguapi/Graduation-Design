<template>
  <div id="app">
    <transition name="fade" mode="out-in">
    <router-view/>
    </transition>
  </div>
</template>
<style>
body{
  margin: 0;
  background-color: #f4f4f4;
}
::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to{
  opacity: 0;
}
</style>