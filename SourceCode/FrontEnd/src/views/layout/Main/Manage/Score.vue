<template>
  <div style="margin-top: 10%;">
    <el-row :gutter="20">
      <el-col :span="10" :offset="7">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="line-height: 28px;font-size: 20px">{{ data.contestTitle }}</span>
            <el-button style="float: right" size="mini" type="primary" round
                       @click="$router.push({name: 'manage-review', params: {contestId:data.contestId,contestTitle:data.contestTitle}})">
              返回
            </el-button>
          </div>

          <el-descriptions title="评审信息" border :contentStyle="{'text-align': 'center'}"
                           :labelStyle="{'text-align': 'center'}">
            <el-descriptions-item label="用户名">{{ data.name }}</el-descriptions-item>
            <el-descriptions-item label="评审人">{{ data.judge }}</el-descriptions-item>
            <el-descriptions-item label="排名" :contentStyle="{'color':data.ranking === '未知' ? '#F56C6C':'#409EFF','text-align': 'center'}">{{ data.ranking }}
            </el-descriptions-item>
            <el-descriptions-item label="手机号">{{ data.phone }}</el-descriptions-item>

            <el-descriptions-item label="学校">
              <el-tag size="small">{{ data.school }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="得分">
              <input :disabled="data.status" v-model="data.result" maxlength="3"
                     placeholder="得分"
                     onkeyup="value=value.replace(/\D/g,'')"
                     style="width: 30px;text-align: center"></input>
            </el-descriptions-item>
            <el-descriptions-item label="评语">
              <textarea placeholder="请输入评语" :disabled="data.status" v-model="data.text" style="width: 100%;"></textarea>
            </el-descriptions-item>
          </el-descriptions>
          <div style="float: right;margin: 20px 0">
            <template v-if="data.status">
              <el-button type="warning" round @click="data.status = false">修改</el-button>
            </template>
            <template v-else>
              <el-button type="success" round @click="scoreReview">评审</el-button>
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
      data: {
        contestTitle: '',
        scoresId: '',
        name: '',
        phone: '',
        school: '',
        status: false,
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
        status: true,
        text: ''
      }
    }
  },
  mounted() {
    this.data = this.$route.params;
    this.scoreLoading(this.data.scoresId);
  },
  methods: {
    // 加载
    scoreLoading(id) {
      // 根据id查询
      if (id) {
        getRequest("/scores/" + id).then((res) => {
          // console.log(res);
          this.data = res.data.data;
          // this.$set(this.data, 'ranking', data.ranking === undefined ? '未知' : data.ranking);
          // this.$set(this.data, 'judge', data.judge);
          // this.$set(this.data, 'result', data.result);
          // this.$set(this.data, 'text', data.text);
          if (this.data.ranking === 0) {
            this.data.ranking = '未知';
            this.$notify.info({
              title: '排名未更新',
              message: '若排名显示未知，则该比赛并未全部评审完',
              offset: 200,
              duration: 5000
            });
            this.color = '#F56C6C';
          } else this.ranking = data.ranking;
        })
      }
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
        this.submitData.judge = Number(this.$store.state.uid);
        this.submitData.result = result;
        this.submitData.text = this.data.text;
        // 修改数据
        putRequest("/scores/update", this.submitData).then((res) => {
          // console.log(res)
          if (res.data.status) {
            this.$message.success("评审成功。")
            // 发送通知
            this.message();
            this.data.status = true;
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
      if (this.data.judge) {
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
        sender: Number(this.$store.state.uid)
      };
      // console.log(obj);
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