<template>
	<Card class='managers-page'>
		<h2 class="card-header" slot="title"> 活动管理员管理 </h2>

		<div class="action-list">
			<Button class="btn" type="warning" @click.prevent="" :loading="isFetching">
				<i class="fa fa-lg fa-trash-o" aria-hidden="true"></i> 批量删除
			</Button>
			<Button class="btn" type="success" @click.prevent="" :loading="isFetching">
				<i class="fa fa-paper-plane" aria-hidden="true"></i> 导入活动管理员
			</Button>
			<Input class="search-text" search type="text" v-model="searchText" placeholder="搜索活动管理员" @change=""></Input>
		</div>

		<Table class="managers-table" :fit="true" :data="allManagers" :loading="isFetching" ref="activityTable" border stripe>
			<Column type="selection" width="40">
			</Column>
			<Column prop="user_account" label="账号" fixed></Column>
			<Column prop="user_name" label="名字" sortable></Column>
			<Column prop="user_sex" label="性别" sortable></Column>
			<Column prop="user_phone" label="联系方式"></Column>
			<Column class="action-column" label="操作">
				<template slot-scope="scope">
					<Button class="action-btn" type="success" @click=""> 编辑 </Button>
					<Button class="action-btn" type="warning" @click=""> 删除 </Button>
				</template>
			</Column>
		</Table>

		<Page class="pagination" :total=totalPage :current="currentPage" :page-size-opts="pageOpts" :page-size="pageSize" @on-page-size-change="changePageSize" @on-change="changePage" show-elevator show-sizer />
	</Card>
</template>

<script>
import _ from 'lodash';
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';

export default {
	components: {},
	mixins: [stateParseMixin],
	data() {
		return {
			currentPage: 1,
			pageOpts: [3, 5, 10, 20],
			pageSize: 10,
			searchText: ''
		};
	},
	filters: {
		formatScore(num) {
			if (_.isNil(num)) {
				return '0.0'
			}
			return num.toFixed(1);
		}
	},
	computed: {
		...mapState('admin', { state: 'managers' }),
		...mapState({ user: 'profile' }),
		userType() {
			if (this.user.isFulfill) {
				return this.user.payload.results[0].user_type;
			}
			return '';
		},
		allManagers() {
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
		...mapActions('admin', ['getManagers']),
		load() {
			const { userType, currentPage, pageSize } = this;
			this.getManagers({ userType, currentPage, pageSize })
				.catch(() => {
					this.$Message.error('活动管理员信息列表请求出现错误！');
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
		}
	},
	mounted() {
		this.load();
	},
	watch: {
		userType() {
			this.load();
		}
	}
}
</script>
<style type='text/less' lang='less'>
.managers-page {
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

.managers-table {
	margin-top: 20px;
	width: 100%;
	overflow-x: auto;
	td {
		padding: 10px 2px;
		text-align: center;
	}
	th {
		.cell {
			text-align: center;
		}
	}
}

.action-list {
	margin: 10px 0 30px 10px;
	.btn {
		margin-right: 20px;
	}
	.search-text {
		text-indent: 5px;
		display: inline-block;
		width: 30%;
		margin-left: 20px;
	}
}
</style>