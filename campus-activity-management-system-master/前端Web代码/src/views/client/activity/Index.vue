<template>
	<div class='home-activity'>
		<!-- 活动列表 -->
		<ActivityLists :selectedType="showActivityLists"></ActivityLists>

		<!-- 查看更多 -->
		<router-link class="more-link" :to="{name: 'find'}">
			查看更多....
		</router-link>
	</div>
</template>

<script>
import stateParseMixin from '@/utils/stateParseMixin';
import ActivityLists from './ActivityLists';


export default {
	components: { ActivityLists },
	mixins: [stateParseMixin],
	data() {
		return {
			showActivityLists: 'online',
		};
	},
}
</script>

<style type='text/less' lang='less'>
.home-activity {
	.ivu-card-body {
		padding: 4px;
	}

	.choose-tag {
		height: 60px;
		font-weight: 600;
		margin: 20px 0 15px 10px;
		background: transparent;
		border: none;
	}
}

.carousel {
	width: 95%;
	height: 300px;
	margin: 0 auto;
}
.carousel-img {
	width: 100%;
	height: 280px;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}

.more-link {
	padding: 0 10px 20px;
	display: block;
	text-align: center;
	font-size: 16px;
	letter-spacing: 3px;
	font-weight: 500;
	color: #659fdc;
}
</style>
