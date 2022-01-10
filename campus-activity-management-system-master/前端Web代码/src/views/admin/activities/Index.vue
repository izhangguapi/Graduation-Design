<template>
	<Card class='activities-page'>
		<h2 class="card-header" slot="title"> 活动信息管理 </h2>

		<div class="action-list">
			<Button class="btn" type="warning" @click.prevent="deleteSelectItem" :loading="isFetching">
				<i class="fa fa-lg fa-trash-o" aria-hidden="true"></i> 批量删除
			</Button>
			<Button class="btn" type="success" @click.prevent="toAddActivity" :loading="isFetching">
				<i class="fa fa-paper-plane" aria-hidden="true"></i> 发布活动
			</Button>
<!--			<Input class="search-text" search type="text" v-model="searchText" placeholder="搜索活动" @change=""></Input>-->
		</div>

		<ETable class="activity-table" :fit="true" :data="allActivities" :loading="isFetching" ref="activityTable" border @selection-change="handleSelectionChange">
			<EColumn type="selection" width="40">
			</EColumn>
            <EColumn prop="activity_id" label="活动ID" width="100" fixed>
                <template slot-scope="scope">
                    {{ scope.row.aid }}
                </template>
            </EColumn>
			<EColumn prop="activity_name" label="活动名称" width="300" fixed>
				<template slot-scope="scope">
					<router-link :to="{name: 'admin-activity-detail',params: {aid: scope.row.aid}}">
						{{ scope.row.aname }}
					</router-link>
				</template>
			</EColumn>
			<EColumn prop="activity_type" label="活动类型" width="100" sortable>
				<template slot-scope="scope">
					{{ scope.row.asort | formatType }}
				</template>
			</EColumn>
			<EColumn prop="activity_time" label="活动时间" width="210" sortable>
				<template slot-scope="scope">
					{{ scope.row.atime }}
				</template>
			</EColumn>
			<EColumn prop="activity_creator" label="活动发布人" width="90">
                <template slot-scope="scope">
                    {{ scope.row.uusername }}
                </template>
            </EColumn>
			<EColumn class="action-column" label="操作" width="290">
				<template slot-scope="scope">
					<Button class="action-btn" type="primary" @click="toDetail(scope.row.aid)">
						查看详情
					</Button>
					<Button class="action-btn" type="success" @click="ShowModal(scope.row)"> 编辑 </Button>
					<Button class="action-btn" type="warning" @click="deleteActivity(scope.row.aid)"> 删除 </Button>
				</template>
			</EColumn>
		</ETable>

		<updateActivityModal :isShowModal="isShowModal" :info="currentActivity" @change="updateActivity" @close="closeModal"></updateActivityModal>

	</Card>
</template>

<script>
import _ from 'lodash';
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';
import updateActivityModal from './updateActivityModal';
import { updatePublished } from '@/store/api/user'
import { removeActivity } from '@/store/api/admin';
import {updateActivityEnroll} from "@/store/api/activity";

export default {
	components: { updateActivityModal },
	mixins: [stateParseMixin],
	data() {
		return {
			currentPage: 1,
			pageOpts: [3, 5, 10, 20],
			pageSize: 10,
			searchText: '',
			isShowModal: false,
			currentActivity: {},
			latestActivity: {},
            multipleSelection: []
		};
	},
	filters: {
		formatScore(num) {
			if (_.isNil(num)) {
				return '0.0'
			}
			return num.toFixed(1);
		},
		formatType(type) {
			if (type === 'online') {
				return '线上活动'
			}
			return '线下活动';
		}
	},
	computed: {
		...mapState('admin', { state: 'activities' }),
		allActivities() {
			if (this.isFulfill) {
                // console.log(this.state.payload);
				return this.state.payload.data;
			}
			return [];
		},
	},
	methods: {
		...mapActions('admin', ['getActivities']),
		load() {
			this.getActivities()
				.catch(() => {
					this.$Message.error('活动列表请求出现错误！');
				});
		},
		toDetail(activityId) {
			this.$router.push({ name: 'admin-activity-detail', params: { aid: activityId } });
		},
		ShowModal(info) {
			this.currentActivity = info;
			this.isShowModal = true;
		},
		updateActivity(info) {
			const params = { ...info };
			updatePublished(params)
				.then(() => {
					this.$Message.success('修改活动信息成功！');
					this.load();
				})
				.catch(() => {
					this.$Message.error('修改活动信息失败！');
				});
		},
		closeModal() {
			this.isShowModal = false;
		},
		deleteActivity(activityId) {
			this.$Modal.confirm({
				title: '删除活动',
				content: '<p>确定要删除活动吗？</p>',
				onOk: () => {
                    let uid = window.sessionStorage.getItem('userprofile');
					removeActivity(activityId, uid)
						.then(() => {
							this.$Message.success('删除活动成功！');
							this.load();
						})
						.catch(() => {
							this.$Message.error('删除活动失败！');
						});
				},
			});
		},
        deleteSelectItem() {
            this.$Modal.confirm({
                title: ' 确认删除',
                content: '<p>确定要批量删除活动吗？</p>',
                onOk: () => {
                    let uid = window.sessionStorage.getItem('userprofile');
                    //遍历获得多选选中的index值
                    this.multipleSelection.forEach((value) => {
                        //遍历多选框获取的数据
                        this.allActivities.forEach((v) => {
                            //遍历数据表，任意一个属性值相同，则说明该数据被选中，其i则为索引值
                            if(v.aid === value.aid){
                                removeActivity(value.aid, uid);
                            }
                        });
                    });
                    this.$message.success('删除成功');
                    window.location.reload();
                },
                onCancel: () => {
                    this.$Message.info('取消删除');
                }
            });
        },
		toAddActivity() {
			this.$router.push({name: 'admin-add-activity'});
		},
        handleSelectionChange(val) {
            this.multipleSelection = val;
            // console.log(val);
        }
	},
	created() {
		this.load();
	},
}
</script>
<style type='text/less' lang='less'>
.activities-page {
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

.activity-table {
	margin-top: 20px;
	width: 100%;
	overflow-x: auto;
	td {
		padding: 10px 2px;
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
