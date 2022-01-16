<template>
  <div>
    <h1>Welcome!{{msg}}</h1>
  </div>
</template>
<script>
export default {
  data() {
    return {
      msg: this.$route.query.data
    };
  },
//   data() { //这种方式也可以
//     return {
//       msg: null
//     };
//   },
  // created() {
  //   this.msg = this.$route.query.data;
  // }
}
</script>