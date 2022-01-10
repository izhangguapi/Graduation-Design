<template>
	<Card class='approvals-page'>
		<h2 class="card-header" slot="title"> 待审批活动 </h2>

		<ETable class="approval-table" :fit="true" :data="pendingReview" :loading="isFetching" ref="activityTable" border stripe>
			<EColumn prop="activity_name" label="活动名字" width="185" fixed>
				<template slot-scope="scope">
					<router-link :to="{name: 'admin-approval-detail', params: {aid: scope.row.aid}}">
						{{ scope.row.aname }}
					</router-link>
				</template>
			</EColumn>
			<EColumn prop="activity_type" label="活动类型" width="100" sortable>
				<template slot-scope="scope">
					{{ scope.row.asort | formatType }}
				</template>
			</EColumn>
			<EColumn prop="activity_start" label="活动时间" width="190" sortable>
				<template slot-scope="scope">
					{{scope.row.atime}}
				</template>
			</EColumn>
			<EColumn prop="activity_creator" label="活动申请人" width="90">
                <template slot-scope="scope">
                    {{scope.row.uname}}
                </template>
            </EColumn>
			<EColumn class="action-column" label="操作" width="285">
				<template slot-scope="scope">
					<Button class="action-btn" type="primary" @click="toDetail(scope.row.activity_id)"> 查看详情 </Button>
					<Button class="action-btn" type="success" @click="pastApproval(scope.row.activity_id)"> 通过审核 </Button>
					<Button class="action-btn" type="warning" @click="showFailApproval(scope.row.activity_id)"> 不通过 </Button>
				</template>
			</EColumn>
		</ETable>

		<Modal class="advice-modal" v-model="isShowModal" title="不通过活动审批申请" @on-ok="failApproval">
			<p class="tip">提点建议，可以让学生更好地完善活动信息哦！</p>
			<label class="label">活动建议/活动意见：</label>
			<Input v-model="advice" placeholder="可以说说您对活动的看法哦.."></Input>
		</Modal>

		<Page class="pagination" :total=totalPage :current="currentPage" :page-size-opts="pageOpts" :page-size="pageSize" @on-page-size-change="changePageSize" @on-change="changePage" show-elevator show-sizer />
	</Card>
</template>

<script>
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';
import { updateApprovalStatus } from '@/store/api/admin';

export default {
	components: {},
	mixins: [stateParseMixin],
	data() {
		return {
			currentPage: 1,
			pageOpts: [3, 5, 10, 20],
			pageSize: 10,
			advice: '',
			isShowModal: false,
			currentApproval: undefined,
		};
	},
	filters: {
		formatType(type) {
			if (type === 'online') {
				return '线上活动'
			}
			return '线下活动';
		}
	},
	computed: {
		...mapState('admin', { state: 'approvals' }),
		...mapState({ user: 'profile' }),
		userInformation() {
			return this.user.payload.results[0];
		},
		pendingReview() {
			if (this.isFulfill) {
				return this.state.payload.results.data;
			}
			return [];
		},
		totalPage() {
			if (this.isFulfill) {
				return this.state.payload.results.total;
			}
			return 0;
		}
	},
	methods: {
		...mapActions('admin', ['getApprovals']),
		load() {
			const { currentPage, pageSize } = this;
			this.getApprovals({ currentPage, pageSize })
				.catch(() => {
					this.$Message.error('活动列表请求出现错误！');
				});
		},
		changePage(currentIndex) {
			this.currentPage = currentIndex;
			this.load();
		},
		changePageSize(pageSize) {
			this.pageSize = pageSize;
			this.currentPage = 1;
			this.load();
		},
		toDetail(activityId) {
			this.$router.push({ name: 'admin-approval-detail', params: { aid: activityId } });
		},
		pastApproval(activityId) {
			this.$Modal.confirm({
				title: '通过活动审批申请',
				content: '<p>确定要通过活动审批申请吗？</p>',
				onOk: () => {
					const managerId = this.userInformation.user_id;
					updateApprovalStatus({ activityId, managerId, status: 1 })
						.then(() => {
							this.$Message.success('活动已成功发布！');
							this.load();
						})
						.catch(() => {
							this.$Message.error('通过审核失败，请重试！');
							this.load();
						});
				},
			});
		},
		showFailApproval(activityId) {
			this.isShowModal = true;
			this.currentApproval = activityId;
		},
		failApproval() {
			const managerId = this.userInformation.user_id;
			const { currentApproval, advice } = this;
			updateApprovalStatus({ activityId: currentApproval, managerId, status: 2, advice })
				.then(() => {
					this.$Message.success('活动已成功反馈！');
					this.load();
				})
				.catch(() => {
					this.$Message.error('反馈失败，请重试！');
					this.load();
				});
		},
	},
	created() {
		this.load();
	},
}
</script>
<style type='text/less' lang='less'>
.approvals-page {
	padding: 20px 5px;

	.pagination {
		margin-top: 30px;
		text-align: right;
	}

	.action-btn {
		margin-left: 7px;
		margin-top: 5px;
	}
}

.approval-table {
	margin-top: 20px;
	width: 100%;
}

.advice-modal {
	.tip, .label {
		line-height: 2;
		font-size: 14px;
	}
	.tip {
		margin-bottom: 10px;
	}
}
</style>
