<template>
	<Card class='works'>
		<GoBack></GoBack>
		<h2 class="activity-name">{{activityName}}</h2>

		<div class="work-lists" v-if="!isEmpty">
			<template v-for="(item, index) in worksList">
				<Card class="work" :key="index">
					<h3 class="work-name" slot="title">{{item.work_name}}</h3>
					<div class="work-img">
						<LazyloadImg :src="item.work_front_img"></LazyloadImg>
					</div>
					<div class="work-info">
						<p class="author">
							<span>
								{{`${item.user_department} - ${item.user_grade}级 ${item.user_profession}${item.user_class}班`}}
							</span>
							<span>{{item.user_name}}</span>
						</p>
						<p class="brief" v-if="item.work_brief">
							{{item.work_brief}}
						</p>
						<p class="content" v-if="item.work_content">
							{{item.work_content}}
						</p>
					</div>
				</Card>
			</template>
		</div>

		<div class="empty-box" v-else>
			<p><i class="fa fa-meh-o" aria-hidden="true"></i></p>
			<p>该活动暂时还没收集到作品哦!</p>
		</div>

	</Card>
</template>

<script>
import GoBack from '@/components/GoBack';
import LazyloadImg from '@/components/LazyloadImg';
import { mapState, mapActions } from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';

export default {
	components: { GoBack, LazyloadImg },
	mixins: [stateParseMixin],
	data() {
		return {};
	},
	computed: {
		...mapState('admin', { state: 'activityWorks' }),
		worksList() {
			if (this.isFulfill) {
				return this.state.payload.results.data;
			}
			return {};
		},
		activityName() {
			if (this.isFulfill) {
				return this.state.payload.results.activity_name;
			}
			return '';
		},
		isEmpty() {
			if (this.worksList.length > 0) {
				return false;
			}
			return true;
		}
	},
	methods: {
		...mapActions('admin', ['getActivityWorks']),
		load() {
			const { params: { aid } } = this.$route;
			this.getActivityWorks({ activityId: aid });
		},
	},
	mounted() {
		this.load();
	},
}
</script>
<style type='text/less' lang='less'>
.works {
	.activity-name {
		margin: 10px 0 30px;
	}
}
.work {
	width: 32%;
	margin-bottom: 30px;
	margin-left: 10px;
	align-self: baseline;
	border-radius: 0;

	&-lists {
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
	}

	&-name {
		color: #241917;
	}

	&-info {
		border-top: 1px solid #93a8d261;
		p {
			margin-top: 10px;
		}
		.author {
			font-weight: 500;
		}
	}

	&-img {
		position: relative;
		padding: 10px 10px 20px;
		min-height: 620px;
	}
}
.empty-box {
	border: 1px solid #98aac142;
	min-height: 300px;
	font-size: 18px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>