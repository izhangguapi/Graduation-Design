<template>
	<div class='activity-detail-page'>
		<GoBack></GoBack>
		<ActivityDetailCtrl></ActivityDetailCtrl>
	</div>
</template>

<script>
import GoBack from '@/components/GoBack';
import ActivityDetailCtrl from './Detail.vue';

export default {
	components: { ActivityDetailCtrl, GoBack },
}
</script>
<style type='text/less' lang='less'>
.activity-detail-page {
	padding: 0 5%;
}
</style>
