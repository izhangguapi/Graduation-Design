<template>
	<Card class="work-page">
		<GoBack></GoBack>
		<h2 class="page-title">上传活动作品</h2>
		<Form :model="newWork" class="work-form" ref="workForm">
			<Row :gutter="24">
				<Col span="12">
				<FormItem prop="name" label="作品主题">
					<Input class="item-content" type="text" v-model="newWork.name" placeholder="作品主题">
					</Input>
				</FormItem>
				</Col>
				<Col span="12">
				<FormItem prop="brief" label="作品简介">
					<Input class="item-content" type="text" v-model="newWork.brief" placeholder="对作品内容进行简单的介绍可以加深作品给人的印象哦...">
					</Input>
				</FormItem>
				</Col>
			</Row>
			<Row>
				<FormItem prop="content" label="作品文本内容">
					<Input type="text" v-model="newWork.content" placeholder="如果作品是文本类型的，需要输入作品文本内容哦...">
					</Input>
				</FormItem>
			</Row>
			<Row :gutter="24">
				<Col span="14">
				<FormItem prop="frontWork" label="作品图片（正面）">
					<Upload type="drag" 
                  :before-upload="frontWorkUploaded" 
                  action="null" 
                  accept="image/jpeg,image/png,image/jpg"
                  :format="['jpg','jpeg','png']" :loading="isLoading">
						<div style="padding: 20px 0">
							<Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
							<p>{{frontWorkTip}}</p>
						</div>
					</Upload>
				</FormItem>
				</Col>
				<Col span="10">
				<FormItem>
					<label class="item-label">
						<Icon type="md-information-circle" /> 温馨提示：
					</label>
					<p class="tip">1. 建议作者们都在作品图片右下角附上个人信息，更便于活动工作人识别。</p>
					<p class="tip">2. 建议选用分辨率高的作品图片,显示的信息会更加清晰哦。</p>
					<p class="tip">3. 针对设计类型的线上活动才需要上传作品图片哦。</p>
          <p class="tip">4. 如果没有作品反面的图，可以选择不添加图片。</p>
					<p class="tip">5. 仅支持jpg、jpeg、png格式。</p>
					<p class="tip">6. 精致的作品设计能吸引更多关注哦！</p>
				</FormItem>
				</Col>
			</Row>
			<Row :gutter="24">
				<Col span="14">
				<FormItem label="作品图片（反面）">
					<Upload type="drag" 
                  :before-upload="backWorkUploaded" 
                  action="null" 
                  accept="image/jpeg,image/png,image/jpg"
                  :format="['jpg','jpeg','png']" :disabled="isLoading">
						<div style="padding: 20px 0">
							<Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
							<p>{{backWorkTip}}</p>
						</div>
					</Upload>
				</FormItem>
				</Col>
			</Row>
			<FormItem class="action-item">
				<Button class="btn" type="primary" :loading="isLoading" @click.prevent="uploadWorks">提交活动作品</Button>
			</FormItem>

		</Form>

	</Card>
</template>

<script>
import GoBack from '@/components/GoBack';
import { mapState } from 'vuex';
import { uploadImg } from '@/utils/uploadImage';
import { addActivityWork } from '@/store/api/activity';

export default {
	components: { GoBack },
	data() {
		return {
			isLoading: false,
			newWork: {
        name: '',
        brief: '',
				content: '',
				frontWork: '',
				backWork: ''
			},
			frontWorkTip: '选择图片或者拖拽图片进行上传',
			backWorkTip: '选择图片或者拖拽图片进行上传',
		};
	},
	computed: {
		...mapState({ user: 'profile' }),
		userId() {
			return this.user.payload.results[0].user_id;
		}
	},
	methods: {
      frontWorkUploaded(file) {
        this.newWork.frontWork = file;
        this.frontWorkTip = `${file.name} 已选择成功`;
        return false;
      },
      backWorkUploaded(file) {
        this.newWork.backWork = file;
        this.backWorkTip = `${file.name} 已选择成功`;
        return false;
      },
      async uploadWorks() {

        this.isLoading = true;

        const params = {...this.newWork};
        params.author = this.userId;
        params.activityId = this.$route.params.aid;
        
				const { frontWork, backWork } = params;
				if (frontWork instanceof File) {
					await uploadImg(frontWork)
						.then((res) => {
							params.frontWork = res.data.data.url;
						})
						.catch(() => {
							this.$Message.error('图片上传失败，请重试！');
							return;
						});
				}

				if (backWork instanceof File) {
					await uploadImg(backWork)
						.then((res) => {
							params.backWork = res.data.data.url;
						})
						.catch(() => {
							this.$Message.error('图片上传失败，请重试！');
							return;
						});
        }
        
				await addActivityWork(params)
					.then(() => {
						this.isLoading = false;
						this.$Message.success('上传活动作品成功！');
						this.reset();
					})
					.catch(() => {
						this.isLoading = false;
						this.frontWorkTip = '选择图片或者拖拽图片进行上传';
						this.backWorkTip = '选择图片或者拖拽图片进行上传';
						this.$Message.error('上传活动作品失败，请重试！');
					})        
      },
      reset() {
				this.frontWorkTip = '选择图片或者拖拽图片进行上传';
				this.backWorkTip = '选择图片或者拖拽图片进行上传';
        this.newWork = {
					name: '',
					brief: '',
					content: '',
					frontWork: '',
					backWork: ''
				}
      },
  },
}
</script>
<style type='text/less' lang='less'>
.work-form {
	padding: 1% 4%;
	.ivu-form-item-label {
		width: 100%;
		display: block;
		text-align: left;
		font-size: 14px;
	}

	.item-content {
		width: 98%;
		margin-top: 10px;
  }

  .action-item {
    text-align: center
  }
  
  .btn {
    width: 25%;
		padding: 10px 50px;    
  }
}
</style>