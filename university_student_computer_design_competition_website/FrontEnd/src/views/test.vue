<template>
  <div>
    <mdEditor v-model="value"/>
  <textarea v-model="value"></textarea></div>
</template>

<script>

const mdEditor = require('mavon-editor')
import 'mavon-editor/dist/css/index.css'

export default {
  name: "test",
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    return {value: '1'}
  }
}
</script>

<style lang="less" scoped>

</style>