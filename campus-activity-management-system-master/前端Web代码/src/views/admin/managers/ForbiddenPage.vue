<template>
	<Card class="forbidden-page">
		<h2 class="card-header" slot="title"> 活动管理员管理 </h2>
		<div class="forbidden-wrapper">
			<LazyloadImg class="forbidden-img" :src="target"></LazyloadImg>
		</div>
	</Card>
</template>

<script>
import LazyloadImg from '@/components/LazyloadImg';

export default {
	components: { LazyloadImg },
	data() {
		return {
			target: 'https://i.loli.net/2019/04/23/5cbea355cd3e0.png',
		};
	},
}
</script>
<style type='text/less' lang='less'>
.forbidden {
	&-wrapper {
		min-height: 500px;
		position: relative;
	}
	&-img {
		width: 100%;
		height: 100%;
		text-align: center;
		img {
			width: 96%;
		}
	}
}
</style>