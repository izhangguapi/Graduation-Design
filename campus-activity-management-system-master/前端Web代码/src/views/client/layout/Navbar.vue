<template>
	<Menu class='client-nav' mode="horizontal" :active-name=activeItem>
		<template v-for="(item, index) in menu">
			<template v-if="item.hasOwnProperty('children')">
				<Submenu :name="item.name" :key="index">
					<template slot="title">
						<Icon :type=item.icon size="22" />
						<span class="menu-item-text"> {{item.title}}</span>
					</template>
					<template v-for="(child, index) in item.children">
						<router-link :to="child.to" :key="index">
							<MenuItem :name="child.name">
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
</template>

<script>
import navData from '@/const/nav';

export default {
	// 监听属性
	computed: {
		menu() {
			return navData;
		},
		activeItem() {
			const routePath = this.$route.path;
			const pathArr = routePath.split('/');
            // console.log(pathArr);
			return `${pathArr[1]}`;
		}
	}
}
</script>

<style type='text/less' lang='less'>
.client-nav {
	flex-basis: 600px;
	.ivu-menu-light.ivu-menu-horizontal .ivu-menu-item,
	.ivu-menu-light.ivu-menu-horizontal .ivu-menu-submenu {
		font-size: 15px;
	}
}
</style>
