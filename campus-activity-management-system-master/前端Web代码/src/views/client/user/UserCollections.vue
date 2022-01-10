<template>
	<div class='collections-lists'>
		<template v-if="isEmpty">
			<div class="empty-tips">
				<p><i class="fa fa-meh-o" aria-hidden="true"></i></p>
				<p>你暂时没有收藏过活动哦,</p>
				<p>赶紧去首页看看有哪些喜欢的活动吧！</p>
			</div>
		</template>
		<template v-else v-for="(item, index) in collectionLists">
			<Card class="activity-card" :key="index">
				<router-link slot="title" :to="{ name: 'find-detail', params: {aid: item.activity_id} }">
					<h3 class="activity-title"> {{ item.activity_name }} </h3>
				</router-link>

				<router-link :to="{ name: 'find-detail', params: {aid: item.activity_id} }">
					<div class="activity-poster" :style="{ backgroundImage: 'url(' + item.activity_poster_front +')'}">
					</div>
				</router-link>

				<p class="activity-brief"> {{ item.activity_brief }} </p>
			</Card>
		</template>
	</div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';

export default {
	props: {
		userId: {
			type: Number,
			require: true,
			default: 0,
		}
	},
	mixins: [stateParseMixin],
	data() {
		return {};
	},
	computed: {
		...mapState('user', { state: 'userCollections' }),
		collectionLists() {
			if (this.isFulfill) {
				return this.state.payload.results;
			}
			return [];
		},
		isEmpty() {
			return this.collectionLists.length === 0;
		}
	},
	methods: {
		...mapActions('user', ['getUserCollections']),
		loadData() {
			const { userId } = this;
			this.getUserCollections({ userId });
		}
	},
	mounted() {
		this.loadData();
	},
	watch: {
		userId() {
			this.loadData();
		}
	}
}
</script>
<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.collections-lists {
	display: flex;
	justify-content: flex-start;
	flex-wrap: wrap;

	.activity {
		&-card {
			width: 31%;
			margin-bottom: 30px;
			margin-right: 2.2%;
		}

		&-poster {
			width: 100%;
			height: 290px;
			background-position: center;
			background-repeat: no-repeat;
			background-size: cover;
			transition: all 0.2s ease-in;
		}
	}

	.empty-tips {
		width: 100%;
		height: 200px;
		.flex-vertical();
		margin-top: 50px;
		font-size: 17px;
	}
}
</style>
