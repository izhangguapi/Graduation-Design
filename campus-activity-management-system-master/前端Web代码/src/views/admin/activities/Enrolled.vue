<template>
	<Card class='enrolled'>
		<GoBack></GoBack>
		<h2>报名名单</h2>

		<h3 class="table-title">{{activityName}}</h3>
		<ETable class="enrolled-table" :fit="true" :data="enrolledList" :loading="isFetching" ref="studentsTable" border stripe>
			<EColumn prop="user_account" label="用户ID" fixed sortable>
                <template slot-scope="scope">
                    {{scope.row.uid}}
                </template>
            </EColumn>
			<EColumn prop="user_name" label="用户名称"><template slot-scope="scope">
                {{scope.row.uname}}
            </template></EColumn>
<!--			<EColumn prop="user_sex" label="性别"></EColumn>-->
<!--			<EColumn prop="user_grade" label="年级" sortable></EColumn>-->
<!--			<EColumn prop="user_department" label="院系" width="200" sortable></EColumn>-->
<!--			<EColumn prop="user_profession" label="专业" width="200" sortable></EColumn>-->
<!--			<EColumn prop="user_class" label="班级"></EColumn>-->
<!--			<EColumn prop="user_phone" label="联系方式"></EColumn>-->
		</ETable>

	</Card>
</template>

<script>
import GoBack from '@/components/GoBack';
import { mapState, mapActions } from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';
import { download } from '@/utils/download';

export default {
	components: { GoBack },
	mixins: [stateParseMixin],
	data() {
		return {};
	},
	computed: {
		...mapState('admin', { state: 'enrolledList' }),
		enrolledList() {
			if (this.isFulfill) {
				return this.state.payload.data;
			}
			return [];
		},
		activityId() {
			const { params: { aid } } = this.$route;
			return aid;
		},
		activityName() {
			if (this.isFulfill) {
				return this.state.payload.data[0].aname;
			}
			return '';
		}
	},
	methods: {
		...mapActions('admin', ['getEnrolledList']),
		load() {
			const { params: { aid } } = this.$route;
			this.getEnrolledList( aid );
		}
	},
	mounted() {
		this.load();
	},
}
</script>
<style type='text/less' lang='less'>
.enrolled {
	position: relative;
	.table-title {
		font-size: 18px;
		font-weight: 500;
		text-align: center;
		margin-bottom: 20px;
	}
	.download-btn {
		position: absolute;
		right: 20px;
		top: 55px;
	}
	.enrolled-table {
		td .cell {
			text-align: center;
		}
	}
}
</style>
