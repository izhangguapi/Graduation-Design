<template>
  <div style="margin-top: 10%;">
    <el-row :gutter="20">
      <el-col :span="10" :offset="7">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">{{ this.data.contestTitle }}</span>
            <el-button style="float: right" size="mini" type="primary" round
                       @click="$router.push({name: 'publish-message', params: {contestId:data.contestId,contestTitle:data.contestTitle}})">
              返回
            </el-button>
          </div>

          <el-descriptions title="评审信息" border :contentStyle="{'text-align': 'center'}"
                           :labelStyle="{'text-align': 'center'}">
            <el-descriptions-item label="用户名">{{ data.name }}</el-descriptions-item>
            <el-descriptions-item label="评审人">{{ data.judge }}</el-descriptions-item>
            <el-descriptions-item label="排名">{{ data.ranking }}</el-descriptions-item>
            <el-descriptions-item label="手机号">{{ data.phone }}</el-descriptions-item>

            <el-descriptions-item label="学校">
              <el-tag size="small">{{ data.school }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="得分">
              <input :disabled="inputDisabled" v-model="data.result" maxlength="3"
                     onkeyup="value=value.replace(/[^\d]/g,'')"
                     style="width: 30px;text-align: center"></input>
            </el-descriptions-item>
            <el-descriptions-item label="评语">
              <textarea :disabled="inputDisabled" v-model="data.text" style="width: 100%;"></textarea>
            </el-descriptions-item>
          </el-descriptions>
          <div style="float: right;margin: 20px 0">
            <template v-if="inputDisabled">
              <el-button type="warning" round @click="scoreModify">修改</el-button>
            </template>
            <template v-else>
              <el-button type="success" round @click="scoreReview">评审</el-button>
              <el-button type="danger" round @click="inputDisabled = !inputDisabled">取消</el-button>
            </template>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getRequest, postRequest, putRequest} from "@/utils/api";

export default {
  name: "Score",
  data() {
    return {
      inputDisabled: true,
      data: {
        contestTitle: '',
        scoresId: '',
        name: '',
        phone: '',
        school: '',
        state: '',
        ranking: '',
        judge: '',
        result: '',
        text: '',
        userId: ''
      },
      submitData: {
        scoresId: '',
        judge: '',
        result: '',
        state: true,
        text: ''
      }
    }
  },
  mounted() {
    this.data = this.$route.params;
    this.scoreLoading(this.data.scoresId);
    // getRequest("/contests/gid", {gid: sessionStorage.gid}).then((res) => {
    //   this.data = res.data.data;
    //   console.log(this.data)
    // })
  },
  methods: {
    // 加载
    scoreLoading(id) {
      // 根据id查询
      if (this.data.state) {
        getRequest("/scores/" + id).then((res) => {
          const data = res.data.data;
          this.$set(this.data, 'ranking', data.ranking === undefined ? '未知' : data.ranking);
          this.$set(this.data, 'judge', data.judge);
          this.$set(this.data, 'result', data.result);
          this.$set(this.data, 'text', data.text);
        })
      } else {
        this.inputDisabled = false;
        this.data.judge = this.data.ranking = '暂无';
      }
    },
    // 修改
    scoreModify() {
      this.inputDisabled = !this.inputDisabled;
    },
    // 评审
    scoreReview() {
      let result = parseInt(this.data.result);
      if (result > 100 || result < 1 || isNaN(result)) {
        this.$message.error('分数只能在0-100之间！')
      } else if (this.data.text === undefined) {
        this.$message.error('评语不能为空！')
      } else {
        this.submitData.scoresId = this.data.scoresId;
        this.submitData.judge = Number(sessionStorage.uid);
        this.submitData.result = result;
        this.submitData.text = this.data.text;
        this.inputDisabled = !this.inputDisabled;
        // 修改数据
        putRequest("/scores/update",this.submitData).then((res) => {
          if (res.data.data) {
            this.$message.success("评审成功。")
            // 发送通知
            this.message();
            this.data.state = true;
            this.scoreLoading(this.data.scoresId);
          } else {
            this.$message.error("评审失败！")
          }
        })
      }
    },
    message() {
      let title = '';
      let text = '';
      if (this.data.state) {
        title = '比赛结果改动通知';
        text = '您报名的比赛：《' + this.data.contestTitle + '》结果已修改。';
      } else {
        title = '比赛评审完成通知';
        text = '恭喜您报名的比赛：《' + this.data.contestTitle + '》已经评审完成。';
      }
      const obj = {
        recipient: this.data.userId,
        title: title,
        text: text,
        sender: Number(sessionStorage.uid)
      };
      postRequest("/messages/insert", obj);
    }
  }
}
</script>

<style lang="less" scoped>
input, textarea {
  border: none;
  outline: none;
  background-color: #fff;
  resize: none;
}
</style>