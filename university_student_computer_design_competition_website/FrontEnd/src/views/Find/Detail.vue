<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <!--<el-page-header @back="goBack" content="详情页面" class="btn"></el-page-header>-->
        <Button class="btn" @click="this.$router.go(-1);"><i class="fa fa-circle-arrow-left"></i>&nbsp;返回上一级</Button>
      </el-col>
    </el-row>


    <el-row :gutter="20">
      <el-col :span="13" :offset="4">
        <el-card :body-style="{ padding: '0' ,backgroundColor: 'rgb(251, 251, 251)'}">
          <div style="font-size: 24px;color: #303133;text-align: center;width: 100%;padding-top: 20px">
            {{ contestTitle }}
          </div>
          <el-divider></el-divider>
          <mdEditor v-model="contestText" :ishljs="true" :subfield="false" :toolbars="{}" defaultOpen="preview"
                    style="min-height: 600px" :toolbarsFlag="false">
          </mdEditor>
        </el-card>
      </el-col>
      <el-col :span="3">
        <el-card>
          <div class="text item">
            发布人：{{ name }}
          </div>
          <div class="text item">
            所属组：{{ groupName }}
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest, postRequest} from "@/utils/api";

const mdEditor = require('mavon-editor')
import 'mavon-editor/dist/css/index.css'

export default {
  name: "Main",
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    return {
      contestTitle: '',
      contestText: '',
      name: '',
      groupName: '',
      startTime: '',
      endTime: '',
    }
  },
  mounted() {
    // const id = this.$route.params.contestId;
    // 查询id为this.$route.params.contestId的比赛并显示
    getRequest("/Contests/" + this.$route.params.contestId).then((resp) => {
      const data = resp.data.data
      if (data) {
        // console.log(data);
        this.contestText = data.contestText.replace(/<br>/ig, "\n");
        this.contestTitle = data.contestTitle;
        this.name = data.name;
        this.groupName = data.groupName;
        this.startTime = data.startTime;
        this.endTime = data.endTime;
        document.title = data.contestTitle;
      } else {
        this.$message.error("未获取到此文章");
        this.$router.push("/404");
      }
    });
  }, methods: {}
}
</script>

<style scoped lang="less">
.btn {
  padding: 5px;
  margin-bottom: 10px;
  color: #515a6e;
  background-color: transparent;
  border-color: transparent;

  &:hover {
    color: #ff6464;
  }
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
.v-note-wrapper {
  box-shadow:none !important;
}
</style>