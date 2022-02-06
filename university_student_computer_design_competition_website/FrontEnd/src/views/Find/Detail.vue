<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <!--返回上页-->
        <button class="btn" @click="goBack" style="cursor: pointer;"><i class="fa fa-circle-arrow-left"></i>&nbsp;返回
        </button>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12" :offset="4">
        <!--左侧信息-->
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
      <!--右侧信息-->
      <el-col :span="4">
        <el-card>
          <div class="block">
            <el-timeline>
              <el-timeline-item v-for="(activity, index) in activities" :key="index" :icon="activity.icon"
                                :type="activity.type" :color="activity.color" :size="activity.size"
                                :timestamp="activity.timestamp">
                {{ activity.content }}
              </el-timeline-item>
            </el-timeline>
          </div>
          <el-divider></el-divider>
          <div class="text item">
            发布人：<samp class="itemRight">{{ name }}</samp>
          </div>
          <div class="text item">
            所属组：<samp class="itemRight">{{ groupName }}</samp>
          </div>
          <div style="text-align: center" v-if="btnHidden">
            <el-button type="primary" round @click="apply">立即报名</el-button>
          </div>
          <div style="text-align: center" v-else>
            <el-button type="primary" round disabled>不可报名</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest} from "@/utils/api";

const mdEditor = require('mavon-editor')
import 'mavon-editor/dist/css/index.css'

export default {
  name: "Main",
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    return {
      btnHidden: false,
      contestTitle: '',
      contestText: '',
      contestId:'',
      name: '',
      groupName: '',
      startTime: '',
      endTime: '',
      activities: [{
        content: '报名开始',
        timestamp: '2022-02-22 22:22',
        size: 'large',
        type: 'primary'
      }, {
        content: '报名结束',
        timestamp: '2022-02-22 22:22',
        size: 'large',
        type: 'success'
      }, {
        content: '比赛开始',
        timestamp: '2022-02-22 22:22',
        size: 'large',
        type: 'warning'
      }, {
        content: '比赛结束',
        timestamp: '2022-02-22 22:22',
        size: 'large',
        type: 'danger'
      }],
    }
  },
  mounted() {
    this.contestId = this.$route.params.contestId;
    // 查询当前用户是否已报名此比赛
    getRequest("/Scores/" + sessionStorage.uid + "/" + this.contestId).then((resp) => {
      // console.log(resp.data);
      // console.log(resp.data.data === 0);
      // console.log("/Scores/" + sessionStorage.uid + "/" + this.contestId);
      if (resp.data.data === 0) {
        this.btnHidden = true;
      }
      sessionStorage.gid === 2 ? this.btnHidden = true: this.btnHidden = false;
      sessionStorage.gid === 2 ? console.log('是') : console.log("否");
    });


    // 查询id为this.$route.params.contestId的比赛并显示
    getRequest("/Contests/" + this.contestId).then((resp) => {
      const data = resp.data.data;
      console.log(data);
      if (data) {
        document.title = this.contestTitle = data.contestTitle;
        this.contestText = data.contestText.replace(/<br>/ig, "\n");
        this.name = data.name;
        this.groupName = data.groupName;
        this.startTime = data.startTime;
        this.endTime = data.endTime;
        this.activities[0].timestamp = data.regStartTime.substring(0, 19);
        this.activities[1].timestamp = data.regEndTime.substring(0, 19);
        this.activities[2].timestamp = data.startTime.substring(0, 19);
        this.activities[3].timestamp = data.endTime.substring(0, 19);
      } else {
        this.$message.error("未获取到此文章");
        this.$router.push("/404");
      }
    });
  }, methods: {
    /**
     * 点击返回
     */
    goBack() {
      this.$router.go(-1);
    },
    /**
     * 点击报名按钮
     */
    apply() {
      getRequest("/addScores/" + sessionStorage.uid + "/" + this.contestId).then((resp) => {
        console.log(resp.data);
        if (resp.data.data === 1) {
          this.$message.success("报名成功。")
          this.btnHidden = false;
        } else {
          this.$message.error("报名失败！")
        }
      })

    }
  }
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
  font-size: 16px;
}

.item {
  padding: 0 20px 15px;
}

.box-card {
  width: 480px;
}

.v-note-wrapper {
  box-shadow: none !important;
}

.itemRight {
  float: right;
}
</style>