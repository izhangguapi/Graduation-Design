<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <!--返回上页-->
        <button class="btn" @click="$router.go(-1);" style="cursor: pointer;"><i class="fa fa-circle-arrow-left"></i>&nbsp;返回
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
                    style="min-height: 600px" :toolbarsFlag="false"/>
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
            <el-button type="primary" round @click="submit">{{btnText}}</el-button>
          </div>
          <div style="text-align: center" v-else>
            <el-button type="danger" round disabled>{{ btnText }}</el-button>
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
  inject:['reload'],                                 //注入App里的reload方法
  components: {
    'mdEditor': mdEditor.mavonEditor
  },
  data() {
    return {
      btnHidden: false,
      btnText:'不可报名',
      promulgator: 0,
      contestTitle: '',
      contestText: '',
      contestId: '',
      name: '',
      groupName: '',
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
    getRequest("/scores/" + this.$store.state.uid + "/" + this.contestId).then((res) => {
      if (this.$store.state.gid === '2') {
        if (res.data.data === 0){
          this.btnHidden = true;
          this.btnText='立即报名';
        }else{
          this.btnHidden = false;
          this.btnText='已报名';
        }
      } else {
        this.btnHidden = false;
      }
    });
    // 查询id为this.$route.params.contestId的比赛并显示
    getRequest("/contests/" + this.contestId).then((res) => {
      const data = res.data.data;
      if (data) {
        document.title = this.contestTitle = data.contestTitle;
        this.contestText = data.contestText;
        this.name = data.name;
        this.groupName = data.groupName;
        this.promulgator = data.promulgator;
        this.activities[0].timestamp = data.regStartTime;
        this.activities[1].timestamp = data.regEndTime;
        if (new Date() > new Date(data.regEndTime)) {
          this.btnText='报名时间已过';
          this.btnHidden = false;
        }
        this.activities[2].timestamp = data.startTime;
        this.activities[3].timestamp = data.endTime;
      } else {
        this.$router.push("/404");
      }
    });
  },
  methods: {
    /**
     * 点击报名按钮
     */
    submit() {
      getRequest("/addScores/" + this.$store.state.uid + "/" + this.contestId).then((resp) => {
        if (resp.data.data === 1) {
          this.$message.success("报名成功。")
          this.btnHidden = false;
          // 发送一条消息给报名用户
          const obj = {
            recipient: Number(this.$store.state.uid),
            title: '报名成功通知',
            text: '恭喜您成功报名比赛：' + this.contestTitle,
            sender: this.promulgator
          };
          postRequest("/messages/insert", obj).then((res) => {
            if (res.data.data===1){
              this.reload();
            }
          })
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