<template>
	<header class="head">
		<span class="logo">
			<img src="https://www.yishuzi.cn/image.png?fsize=30&font=cmll.ttf&text=mineCampus&mirror=no&color=f49bc1&vcolor=6ccff7&bgcolor=fff&alpha=no&output=png&spacing=4&shadow=no&transparent=no&icon=no&iconic=&top_spacing=5&left_spacing=6&icon_size=48">
		</span>

		<Navbar></Navbar>

		<div class="search">
			<Input placeholder="活动搜索..." />
			<Button class="search-btn" shape="circle" icon="ios-search"></Button>
		</div>

		<div class="user-info">
			<Menu mode="horizontal" @on-select="menuSelected">
				<Submenu index="userAvatar" name="user">
					<template slot="title">
						<Avatar class="user-avatar" size="large" :loading="isFetching">{{ getUserName }}</Avatar>
					</template>
					<MenuItem class="logout-btn" name="signOut">
					退出登录
					</MenuItem>
				</Submenu>
			</Menu>
		</div>
	</header>
</template>

<script>
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';
import Navbar from './Navbar';
import _ from 'lodash';

export default {
	mixins: [stateParseMixin],
	components: { Navbar },
	data() {
		return {
			userName: ''
		};
	},
	computed: {
		...mapState({ state: 'profile' }),
		getUserName() {
			if (this.isFulfill) {
				const nameArr = this.state.payload.data.username.split("");
				return _.slice(nameArr, 0)[0];
			}
			return '';
		},
	},
	methods: {
		...mapActions({
			signOut: 'signOut',
			getProfile: 'getProfile'
		}),
		menuSelected(name) {
			if (name === 'signOut') {
				this.signOut();
				this.$router.push({ name: 'login' });
			}
		},
	},
	created() {
		this.getProfile();
	}
}
</script>
<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.head {
	width: 100%;
	height: 65px;
	margin-bottom: 30px;
	background: #ffffff;
	box-shadow: 0 1px 3px rgba(26, 26, 26, 0.1);
	background-clip: content-box;
	display: flex;
	align-items: center;
	justify-content: space-between;

	.logo {
		display: inline-block;
		flex-basis: 200px;
		img {
			margin-left: 8%;
			width: 90%;
		}
	}
}

.user-info {
	flex-basis: 270px;
	padding: 0 10px;
	display: flex;
	align-items: center;
	justify-content: flex-end;
}

.user-avatar {
	font-size: 15px;
	font-weight: 500;
	background: @color-primary !important;
}

.news {
	margin-right: 20px;
}

.search {
	flex-basis: 280px;
	flex-shrink: 20;
	margin: 0 20px;
	.ivu-input-wrapper {
		width: 80%;
	}
	&-btn{
		margin-left: 2px;
		font-size: 19px !important;
    border: none !important;
	}
}
</style>
