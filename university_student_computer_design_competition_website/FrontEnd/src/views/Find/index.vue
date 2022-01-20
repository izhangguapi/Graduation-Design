<template>

    <div>
      <el-row :gutter="20">
        <el-col :span="16">
          <el-card>
            <div slot="header" class="clearfix">
              <span>卡片名称</span>
              <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{'列表内容 ' + o }}
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card>
            <div slot="header" class="clearfix">
              <span>公告通知</span>
            </div>
            <div v-for="i in 5" :key="i" class="text item">
              {{'列表内容 ' + i }}
            </div>
            <router-link class="more-link" :to="{name: 'find'}">
              查看更多...
            </router-link>
          </el-card>
        </el-col>
      </el-row>
    </div>
</template>

<script>
export default {
  name: "Find"
}
</script>

<style scoped>

</style>