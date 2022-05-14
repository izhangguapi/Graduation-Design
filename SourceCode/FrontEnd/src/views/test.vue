<template>

</template>

<script>

import {getRequest, postRequest} from "@/utils/api";

const mdEditor = require('mavon-editor')
import 'mavon-editor/dist/css/index.css'
import {login} from "@/utils/login";

export default {
  name: "test",
  data() {
    return {

    }
  },
  methods:{

  },
  mounted() {

    getRequest("/currentUser").then(res=>{
      // console.log(res.data);
    })
  }
}
</script>

<style lang="less" scoped>

</style>