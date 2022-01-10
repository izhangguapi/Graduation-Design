<template>
	<div class='page'>
		<HeadCtrl></HeadCtrl>
		<ContainCtrl></ContainCtrl>
		<FootCtrl></FootCtrl>
	</div>
</template>

<script>
import HeadCtrl from './Header';
import FootCtrl from "./Footer";
import ContainCtrl from './Container'

export default {
	components: { HeadCtrl, FootCtrl, ContainCtrl },
}
</script>

<style type='text/less' lang='less'>
</style>