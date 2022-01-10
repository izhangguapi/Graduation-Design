<template>
	<section class="detail">
		<Card>
			<!-- 标题 -->
			<h1 slot="title" class="title">
				{{ activityInfo.aname }}
				<span class="status">
					{{ status[activityInfo.current_status] }}
				</span>
			</h1>

			<!-- 活动信息 -->
			<p class="info brief">
				<Divider>简介</Divider>
				<span class="info-text">{{ activityInfo.acontent }}</span>
			</p>
			<p class="info deadline">
				<Divider>活动时间</Divider>
				<span class="info-text">{{ activityInfo.atime }}</span>
			</p>
<!--			<p class="info period">-->
<!--				<Divider>活动时间</Divider>-->
<!--				<span class="info-text">-->
<!--					{{ `${activityInfo.activity_start} — ${activityInfo.activity_end}` }}-->
<!--				</span>-->
<!--			</p>-->
			<template v-if="activityInfo.asort == 'offline'">
				<p class="info address">
					<Divider>活动地点</Divider>
					<span class="info-text">
						{{ activityInfo.aadd }}
					</span>
				</p>
			</template>
			<p class="info concat">
				<Divider>组织者</Divider>
				<span class="info-text">
					{{ activityInfo.uusername }}
				</span>
			</p>

			<!-- 报名 -->
			<div class="action-btn">
				<template v-if="activityInfo.astate">
					<Button class="btn" icon="ios-sunny" type="warning" size="large" @click.prevent="deleteEnroll" :disabled="!activityInfo.astate">取消报名</Button>
				</template>
				<template v-else>
					<Button class="btn" icon="ios-sunny-outline" type="warning" size="large" @click.prevent="addEnroll" ghost>立即报名</Button>
				</template>
			</div>
		</Card>
	</section>
</template>

<script>
import _ from 'lodash';
// import moment from 'moment';
import { mapState, mapActions } from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';
import { updateActivityEnroll, deleteActivityEnroll } from '@/store/api/activity';

export default {
	mixins: [stateParseMixin],
	data() {
		return {
			status: {
				'processing': '进行中',
				'over': '已结束',
				'waitting': '未开始'
			}
		};
	},
	computed: {
		...mapState('activity', { state: 'activityInfo' }),
		userId() {
			return window.sessionStorage.getItem('userprofile');
		},
		activityInfo() {
			if (this.isFulfill) {
                // console.log("活动信息");
                // console.log(this.state.payload);
				return this.state.payload.data;
			}
			return '';
		},
		// hasBackPoster() {
		// 	const { activityInfo } = this;
		// 	if (this.isFulfill) {
		// 		return _.has(activityInfo, 'activity_poster_back') && activityInfo.activity_poster_back !== '' && !_.isNil(activityInfo.activity_poster_back);
		// 	}
		// 	return false;
		// },
		// isEnrollExpired() {
		// 	const now = moment().format('YYYY-MM-DD HH:mm:ss');
		// 	if (this.isFulfill) {
		// 		return this.activityInfo.activity_enroll_deadline > now;
		// 	}
		// 	return true;
		// },
		// isUploadExpired() {
		// 	const now = moment().format('YYYY-MM-DD');
		// 	if (this.isFulfill) {
		// 		return this.activityInfo.activity_end < now;
		// 	}
		// 	return true;
		// }
	},
	methods: {
		...mapActions('activity', ['getActivityInfo']),
		load() {
            const { params: { aid } } = this.$route;
            window.sessionStorage.setItem('aid', aid);
            this.getActivityInfo(aid);
		},
		addEnroll() {
			this.$Modal.confirm({
				title: ' 确认报名',
				content: '<p>确定要参与报名吗？</p>',
				onOk: () => {
					updateActivityEnroll()
						.then(() => {
							this.$Message.success('报名成功！');
                            window.location.reload();
                        })
						.catch(() => {
							this.$Message.error('报名失败！');
						});
				},
				onCancel: () => {
					this.$Message.info('取消报名');
				}
			});
		},
        deleteEnroll() {
            deleteActivityEnroll()
            .then(() =>{
                this.$message.success('取消报名成功！');
                window.location.reload();
            })
            .catch(() =>{
                this.$message.error('取消报名失败！')
            })
        }
	},
	mounted() {
			this.load();
	},
	watch: {
		userId() {
			this.load();
		},
		activityInfo(newVal) {
			this.hasEnroll = newVal.hasEnroll;
		},
	}
}
</script>

<style type='text/less' lang='less'>
.detail {
	.title {
		margin: 10px;
		font-weight: 500;
	}

	.ivu-card-body {
		padding: 30px;
	}

	.poster {
		width: 100%;
		min-height: 200px;
		padding: 0 10%;
		position: relative;
		display: flex;
		flex-direction: column;
		.img {
			padding: 10px;
		}
	}

	.status {
		font-size: 15px;
		font-weight: 500;
		color: #ee4b4b;
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

.action-btn {
	margin-top: 50px;
	display: flex;
	justify-content: center;

	button {
		margin-right: 30px;
	}

	.btn {
		padding: 10px 25px;
		font-size: 15px;
		border-radius: 8px;
	}
}
</style>
