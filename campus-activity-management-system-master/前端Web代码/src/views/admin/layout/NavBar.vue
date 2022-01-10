<template>
	<section class='admin-nav'>
		<p class="menu-collapse" name="menu-collapse">
			<span class="logo">
				<img src="https://www.yishuzi.cn/image.png?fsize=30&font=cmll.ttf&text=mineCampus&mirror=no&color=f49bc1&vcolor=6ccff7&bgcolor=fff&alpha=no&output=png&spacing=4&shadow=no&transparent=no&icon=no&iconic=&top_spacing=5&left_spacing=6&icon_size=48">
			</span>
		</p>
		<Menu class='nav' theme="light" :active-name="activeItem" :open-names="openItem">
			<template v-for="(item, index) in menu">
				<template v-if="item.hasOwnProperty('children')">
					<Submenu :name="item.name" :key="index">
						<template slot="title">
							<Icon :type=item.icon size="22" />
							<span class="menu-item-text"> {{item.title}}</span>
						</template>
						<template v-for="(child, index) in item.children">
							<router-link :to="child.to" :key="index">
								<MenuItem class="sub-item" :name="child.name">
								{{child.title}}
								</MenuItem>
							</router-link>
						</template>
					</Submenu>
				</template>

				<template v-else>
					<router-link :to="item.to" :key="index">
						<MenuItem :name="item.name">
						<Icon :type=item.icon size="22" />
						{{item.title}}
						</MenuItem>
					</router-link>
				</template>
			</template>
		</Menu>
	</section>

</template>

<script>
import navData from '@/const/adminNav';

export default {

	computed: {
		menu() {
			return navData;
		},
		openItem() {
			// const { menu } = this;
			// return _.map(menu, (item, name) => item.name);
			return ['admin-activity'];
		},
		activeItem() {
			const routePath = this.$route.path;
			const pathArr = routePath.split('/');
			return `${pathArr[1]}-${pathArr[2]}`;
		}
	}
}
</script>

<style type='text/less' lang='less'>
.admin-nav {
	flex-basis: 180px;
}
.nav {
	min-height: 730px;
	.ivu-menu-light.ivu-menu-horizontal .ivu-menu-item,
	.ivu-menu-light.ivu-menu-horizontal .ivu-menu-submenu {
		font-size: 15px;
	}
	.ivu-menu-submenu-title {
		padding: 21px 24px !important;
	}
	.ivu-menu-item-active:not(.ivu-menu-submenu),
	.ivu-menu-item-active:not(.ivu-menu-submenu):hover {
		color: white !important;
		background: #2d8cf0 !important;
	}
	.sub-item {
		padding-left: 56px !important;
	}
}
.menu-collapse {
	height: 65px;
	text-align: center;
	background: #ffffff;

	.logo {
		img {
			margin-top: 10px;
			width: 80%;
		}
	}
}
</style>
