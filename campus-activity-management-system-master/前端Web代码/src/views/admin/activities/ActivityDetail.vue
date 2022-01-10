<template>
	<Card class='detail'>
		<GoBack></GoBack>
		<h2>活动详情管理</h2>
		<h3 class="them"> {{ activityInfo.aname }} </h3>

		<!-- 活动信息 -->
		<p class="info brief">
			<Divider>简介</Divider>
			<span class="info-text">{{ activityInfo.acontent }}</span>
		</p>

		<p class="info deadline">
			<Divider>报名截止时间</Divider>
			<span class="info-text">{{ activityInfo.atime }}</span>
		</p>

		<template v-if="activityInfo.asort == 'offline'">
			<p class="info address">
				<Divider>活动地点</Divider>
				<span class="info-text">
					{{ activityInfo.aadd }}
				</span>
			</p>
		</template>

		<p class="info concat">
			<Divider>主办人</Divider>
			<span class="info-text">
				{{ activityInfo.uusername }}
			</span>
		</p>

		<div class="action-btn">
			<Button class="btn" icon="ios-build" type="primary" size="large" @click.prevent="showActivityModal(activityInfo)">编辑活动信息</Button>
			<Button class="btn" icon="md-contacts" type="warning" size="large" @click.prevent="toEnrolled">查看报名名单</Button>
		</div>

		<Modal v-model="isShowActivityModal" title="编辑活动信息" @on-ok="updateActivity" width="600">
			<Form class="detail-form">
				<FormItem label="活动主题">
					<Input class="item-content" disabled type="text" v-model="currentActivity.aname">
					</Input>
				</FormItem>
				<FormItem label="活动简介">
					<Input class="item-content" type="text" v-model="currentActivity.acontent">
					</Input>
				</FormItem>
				<FormItem label="活动地点" v-if="currentActivity.asort === 'offline'">
					<Input class="item-content" type="text" v-model="currentActivity.aadd">
					</Input>
				</FormItem>
				<FormItem label="活动时间">
					<DatePicker class="item-content" :value="currentActivity.atime" type="datetime" format="yyyy-MM-dd HH:mm:ss" :confirm=true @on-change="changeDeadline"></DatePicker>
				</FormItem>
			</Form>
		</Modal>

	</Card>
</template>

<script>
import _ from 'lodash';
import { mapState, mapActions } from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';
import GoBack from '@/components/GoBack';
import { updatePublished } from '@/store/api/user'
import { download } from '@/utils/download';

export default {
	components: { GoBack },
	mixins: [stateParseMixin],
	data() {
		return {
			isShowActivityModal: false,
			currentActivity: {},
		};
	},
	computed: {
		...mapState('admin', { state: 'activityDetail' }),
		activityInfo() {
			if (this.isFulfill) {
				return this.state.payload.data;
			}
			return '';
		}
	},
	methods: {
		...mapActions('admin', ['getActivityDetail']),
		load() {
			const { params: { aid } } = this.$route;
			this.getActivityDetail( aid );
		},
		showActivityModal(item) {
			this.isShowActivityModal = true;
			this.currentActivity = _.cloneDeep(item);
		},
		changeDeadline(newDate) {
			this.currentActivity.atime = newDate;
		},
		updateActivity() {
			const params = { ...this.currentActivity };
			updatePublished(params)
				.then(() => {
					this.$Message.success('修改活动信息成功！');
                    window.location.reload();
				})
				.catch(() => {
					this.$Message.success('修改活动信息失败！');
				});
		},
		toEnrolled() {
			const { params: { aid } } = this.$route;
			this.$router.push({ name: 'admin-activity-enrolled', params: { aid } });
		},
		exportExcel(activityId) {
			const url = '/api/activity/downloadEnrolls/' + activityId;
			download(url);
		}
	},
	mounted() {
		this.load();
	},
}
</script>
<style type='text/less' lang='less'>
.detail {
	.them {
		margin: 20px 0;
		text-align: center;
		font-size: 18px;
	}

	.ivu-card-body {
		padding: 10px 20px;
	}

	.poster {
		width: 100%;
		min-height: 200px;
		padding: 0 10%;
		position: relative;
		display: flex;
		flex-direction: column;
		justify-content: center;
		.img {
			padding: 10px;
		}
	}

	.status {
		font-size: 15px;
		font-weight: 500;
		color: #ee4b4b;
	}

	.action-btn {
		margin-top: 30px;
		display: flex;
		justify-content: center;

		button {
			margin: 0 15px;
		}

		.btn {
			padding: 10px 25px;
			font-size: 15px;
			border-radius: 8px;
		}
		.works-btn {
			background-color: #909399;
			border-color: #909399;
		}
	}

	&-form {
		.ivu-form-item-label {
			display: block;
			width: 100%;
			text-align: left;
		}
		.item-content {
			display: block;
			width: 90%;
		}
		.concat-item {
			display: inline-block;
			width: 44%;
		}
	}
}
.info {
	width: 100%;
	text-align: center;
	font-size: 15px;

	.ivu-divider {
		font-size: 17px;
		color: #4d739a;
		margin: 40px 0 10px;
	}
}
</style>
