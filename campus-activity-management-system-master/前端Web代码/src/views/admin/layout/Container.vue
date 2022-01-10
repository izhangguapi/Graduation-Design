<template>
	<section class="admin-main">
		<transition name="fade-transform" mode="out-in">
			<router-view></router-view>
		</transition>
	</section>
</template>

<style type='text/less' lang='less'>
.admin-main {
	width: 100%;
	height: 700px;
	padding: 5px 15px;
	overflow-y: auto;
}
</style>