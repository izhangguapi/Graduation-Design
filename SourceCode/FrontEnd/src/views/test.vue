<template>


  <el-popover
      placement="top-start"
      title="标题"
      width="200"
      trigger="click"
      content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
    <!--<el-button slot="reference">hover 激活</el-button>-->
    <el-button slot="reference" type="danger" size="mini" plain>危险按钮</el-button>
    <el-tag slot="reference">hover 激活</el-tag>
  </el-popover>
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