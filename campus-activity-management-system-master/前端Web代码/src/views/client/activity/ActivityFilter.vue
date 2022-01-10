<template>
	<Card class="filter-page">
		<div class="filter-items">
			<p class="filter-item activity-types">
				<span class="label">活动类型：</span>
				<RadioGroup v-model="activityType" type="button" size="large">
					<Radio label="online">
						<span>线上活动</span>
					</Radio>
					<Radio label="offline">
						<span>线下活动</span>
					</Radio>
				</RadioGroup>
			</p>
<!--			<p class="filter-item activity-status">-->
<!--				<span class="label">活动状态：</span>-->
<!--				<RadioGroup v-model="activityStatus" type="button" size="large">-->
<!--					<Radio label="all">-->
<!--						<span>全部</span>-->
<!--					</Radio>-->
<!--					<Radio label="waitting">-->
<!--						<span>未开始</span>-->
<!--					</Radio>-->
<!--					<Radio label="processing">-->
<!--						<span>进行中</span>-->
<!--					</Radio>-->
<!--					<Radio label="over">-->
<!--						<span>已结束</span>-->
<!--					</Radio>-->
<!--				</RadioGroup>-->
<!--			</p>-->
		</div>

		<template v-if="isFetching">
			<Spin size="large"></Spin>
		</template>
		<template v-else-if="isEmpty">
			<div class="empty-tips">
				<p><i class="fa fa-frown-o" aria-hidden="true"></i></p>
				<p>该查询没有数据</p>
			</div>
		</template>
		<template v-else>
			<FilterLists :lists="allActivities"></FilterLists>
		</template>

<!--		<Page class="pagination"-->
<!--					:total=totalPage-->
<!--					:current="currentPage"-->
<!--					:page-size-opts="pageOpts"-->
<!--					:page-size="pageSize"-->
<!--					@on-page-size-change="changePageSize"-->
<!--					@on-change="changePage" show-elevator show-sizer />-->
	</Card>
</template>

<script>
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';
import FilterLists from './FilterLists';

export default {
	components: { FilterLists },
	mixins: [stateParseMixin],
	data() {
		return {
			activityType: 'online',
			// activityStatus: 'all',
			// currentPage: 1,
			// pageOpts: [3, 5, 10, 20],
			// pageSize: 5,
		};
	},
	computed: {
		...mapState('activity', { state: 'activities' }),
		allActivities() {
			if (this.isFulfill) {
                console.log(this.state.payload);
				return this.state.payload.data;
			}
			return [];
		},
		isEmpty() {
			return this.allActivities.length === 0;
		}
	},
	methods: {
		...mapActions('activity', ['getActivities']),
		load() {
			const { activityType } = this;
			this.getActivities( activityType )
				.catch(() => {
					this.$Message.error('活动列表请求出现错误！');
				});
		},
	},
	mounted() {
		this.load();
	},
	watch: {
		activityType(newVal, oldVal) {
			if (newVal !== oldVal) {
				this.load();
			}
		},
	}
}
</script>
<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.filter {
	&-page {
		width: 100%;
		padding: 0 3% 1%;
	}

	&-items {
		font-size: 14px;
		.label {
			font-weight: 500;
		}
	}

	&-item {
		margin-top: 30px;
	}
}
.pagination {
	margin-top: 50px;
	text-align: center;
}

.empty-tips {
	width: 100%;
	height: 200px;
	.flex-vertical();
	margin-top: 50px;
	border: 1px solid #d8d8d8;
	font-size: 21px;
	font-weight: 500;
}
</style>
