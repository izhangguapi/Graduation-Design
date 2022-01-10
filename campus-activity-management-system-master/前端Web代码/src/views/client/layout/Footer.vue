<template>
	<footer class="foot">
		<Divider dashed />
		<p class="tips">
			<span>建议反馈</span>
			<span>举报投诉</span>
			<span>隐私政策</span>
		</p>
		<p>联系我们 © 2021 mineCampus</p>
	</footer>
</template>

<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.foot {
	width: 100%;
	min-height: 80px;
	background: #f4f4f4;
	font-size: 13px;
	.flex-vertical;

	.ivu-divider-dashed {
		background: 0 0;
		border-top: 1px dashed #8292a2;
	}
}

.tips {
	margin-bottom: 20px;
	span {
		margin-left: 20px;
	}
}
</style>
