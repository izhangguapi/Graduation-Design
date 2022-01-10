<template>
	<Card class='students-page'>
		<h2 class="card-header" slot="title"> 用户信息管理 </h2>

		<div class="action-list">
			<Button class="btn" type="warning" @click.prevent="deleteSelectItem" :loading="isFetching">
				<i class="fa fa-lg fa-trash-o" aria-hidden="true"></i> 批量删除
			</Button>
<!--			<Button class="btn" type="success" @click.prevent="" :loading="isFetching">-->
<!--				<i class="fa fa-paper-plane" aria-hidden="true"></i> 导入学生-->
<!--			</Button>-->
<!--			<Input class="search-text" search type="text" v-model="searchText" placeholder="搜索学生" @change=""></Input>-->
		</div>

		<ETable class="students-table" :fit="true" :data="allStudents" :loading="isFetching" ref="studentsTable" border stripe  @selection-change="handleSelectionChange">
			<EColumn type="selection" width="40">
			</EColumn>
            <EColumn prop="user_account" label="用户ID" fixed sortable>
                <template slot-scope="scope">
                    {{scope.row.id}}
                </template>
            </EColumn>
            <EColumn prop="user_name" label="用户名称">
                <template slot-scope="scope">
                    {{scope.row.username}}
                </template>
            </EColumn>
            <EColumn prop="user_name" label="用户密码">
                <template slot-scope="scope">
                    {{scope.row.password}}
                </template>
            </EColumn>
            <EColumn prop="user_status" label="用户身份">
                <template slot-scope="scope">
                    {{scope.row.status}}
                </template>
            </EColumn>
			<EColumn class="action-column" label="操作" width="300">
				<template slot-scope="scope">
					<Button class="action-btn" type="success" @click="resetPwd(scope.row)"> 重置密码 </Button>
					<Button class="action-btn" type="warning" @click="deleteUser(scope.row.id)"> 删除 </Button>
				</template>
			</EColumn>
		</ETable>
    </Card>
</template>

<script>
import _ from 'lodash';
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';
import {updateUserPwd} from "@/store/api/user";
import {removeActivity, removeUser} from "@/store/api/admin";

export default {
	components: {},
	mixins: [stateParseMixin],
	data() {
		return {
			// currentPage: 1,
			// pageOpts: [3, 5, 10, 20],
			// pageSize: 10,
			// searchText: ''
            multipleSelection: []
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
		...mapState('admin', { state: 'students' }),
		allStudents() {
			if (this.isFulfill) {
				return this.state.payload.data;
			}
			return [];
		},
	},
	methods: {
		...mapActions('admin', ['getStudents']),
		load() {
			this.getStudents()
				.catch(() => {
					this.$Message.error('学生信息列表请求出现错误！');
				});
		},
        resetPwd(item) {
            item.password = item.username;
            // console.log(item);
            updateUserPwd(item);
        },
        deleteUser(id){
            this.$Modal.confirm({
                title: '删除该用户',
                content: '<p>确定要删除该用户吗？</p>',
                onOk: () => {
                    let uid = Number(window.sessionStorage.getItem('userprofile'));
                    console.log(id);
                    console.log(uid);
                    if (id !== uid) {
                        removeUser(id)
                                .then(() => {
                                    this.$Message.success('删除用户成功！');
                                    this.load();
                                })
                                .catch(() => {
                                    this.$Message.error('删除用户失败！');
                                });
                    } else {
                        this.$Message.info('你无法删除你自己！');
                    }
                },
            });

        },
        deleteSelectItem() {
            this.$Modal.confirm({
                title: ' 确认删除',
                content: '<p>确定要批量删除活动吗？</p>',
                onOk: () => {
                    //遍历获得多选选中的index值
                    this.multipleSelection.forEach((value) => {
                        //遍历多选框获取的数据
                        this.allStudents.forEach((v) => {
                            //遍历数据表，任意一个属性值相同，则说明该数据被选中，其i则为索引值
                            if(v.id === value.id){
                                removeUser(value.id);
                            }
                        });
                    });
                    this.$message.success('删除成功');
                    // window.location.reload();
                },
                onCancel: () => {
                    this.$Message.info('取消删除');
                }
            });
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
.students-page {
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

.students-table {
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
