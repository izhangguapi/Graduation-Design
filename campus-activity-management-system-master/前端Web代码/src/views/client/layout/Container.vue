<template>
	<section class="app-main">
		<transition name="fade-transform" mode="out-in">
			<router-view></router-view>
		</transition>
	</section>
</template>


<style type='text/less' lang='less'>
.app-main {
	width: 100%;
	padding: 0 4%;
	min-height: 730px;
	background: #f4f4f4;
}
</style>