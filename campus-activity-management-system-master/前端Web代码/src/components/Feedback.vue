<template>
	<Card class='feedback-page'>
		<h2 class="card-header"> 意见反馈 </h2>
		<p class="welcome-text">欢迎反馈问题，您的意见与建议就是我们的动力！</p>
		<p class="text">我们会认真查阅您反馈的每一个问题，在这里您可以提出遇到的问题，也可以发表自己的建议和想法。</p>
		<p class="text">针对您宝贵的建议与意见，我们会不断完善校园活动服务平台，希望能给您带来更好的使用体验！</p>
		<Form :model="feedback" class="feedback-form">
			<FormItem class="item" label="请您选择问题类别，以便我们更好的处理：">
				<Select class="item-content" v-model="feedback.type" placeholder="选择问题类别">
					<Option value="feature">功能欠缺/不好用</Option>
					<Option value="ui">页面效果</Option>
					<Option value="advice">一点建议</Option>
					<Option value="others">其他方面</Option>
				</Select>
			</FormItem>
			<FormItem class="item" label="问题与建议描述：">
				<Input v-model="feedback.content" placeholder="详细描述您遇到的问题，有助于我们快速分析并定位原因，帮您解决问题，比如：1、具体描述遇到了什么问题？2、问题出现前您做过什么操作？" />
			</FormItem>
			<FormItem class="item action-item">
				<Button class="action-btn" type="primary" @click.prevent="addFeedback">提交反馈</Button>
			</FormItem>
		</Form>

	</Card>
</template>

<script>
import { updateFeedback } from '@/store/api/feedback.js'
import { mapState } from 'vuex';

export default {
	data() {
		return {
			feedback: {
				type: '',
				content: ''
			}
		};
	},
	computed: {
		...mapState({ user: 'profile' }),
		userId() {
			if (this.user.isFulfill) {
				return this.user.payload.results[0].user_id;
			}
			return '';
		},
	},
	methods: {
		addFeedback() {
			const params = {...this.feedback};
			params.userId = this.userId;
			updateFeedback({...params})
				.then(() => {
					this.$Message.success('反馈成功');
					this.reset();
				})
		},
		reset() {
			this.feedback = {
				type: '',
				content: ''
			};
		}
	}
}
</script>
<style type='text/less' lang='less'>
.feedback-page {
	min-height: 680px;
	padding: 5px 15px;
}
.welcome-text {
	padding: 25px;
	font-size: 18px;
}
.text {
	margin: 10px 25px;
	font-size: 15px;
}
.feedback-form {
	padding-left: 25px;
	.item {
		margin-top: 50px;
		width: 85%;
	}
	.action-item {
		text-align: center;
		.action-btn {
			font-size: 13px;
			padding: 10px 70px;
		}
	}
	.ivu-form-item-label {
		font-size: 15px;
	}
	input {
		margin-top: 15px;
	}
}
</style>