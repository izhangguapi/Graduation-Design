<template>
  <Button class="btn" type="text" @click="back"><i class="fa fa-arrow-left"></i> 返回上一级</Button>
</template>

<script type="text/ecmascript-6">
  export default {
    methods: {
      back() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style type="text/less" lang="less" scoped>
  .btn {
    padding: 5px;
    margin-bottom: 10px;
  }
</style>
