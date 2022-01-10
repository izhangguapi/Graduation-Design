<template>
	<div class="admin-frame">
		<NavBar></NavBar>
		<div class="main">
			<HeadCtrl></HeadCtrl>
			<ContainCtrl></ContainCtrl>
		</div>
	</div>
</template>

<script>
import HeadCtrl from "./Header";
import NavBar from "./NavBar";
import ContainCtrl from "./Container";

export default {
	components: { HeadCtrl, NavBar, ContainCtrl },
	data() {
		return {};
	},
	computed: {},
	methods: {},
	mounted() { }
};
</script>
<style type='text/less' lang='less'>
.admin-frame {
	display: flex;
	flex-wrap: nowrap;
	width: 100%;
	height: 100%;
	overflow-x: hidden;
	.main {
		width: 100%;
	}
}
</style>