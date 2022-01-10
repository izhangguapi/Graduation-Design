<template>
	<header class="head admin-head">
		<Menu class="user-info" mode="horizontal" @on-select="menuSelected">
			<Submenu index="userAvatar" name="user">
				<template slot="title">
					<Avatar class="user-avatar" size="large" :loading="isFetching">{{ getUserName }}</Avatar>
				</template>
				<MenuItem class="logout-btn" name="signOut">退出登录</MenuItem>
			</Submenu>
		</Menu>
	</header>
</template>


<script>
import stateParseMixin from '@/utils/stateParseMixin';
import { mapState, mapActions } from 'vuex';
import _ from 'lodash';

export default {
	mixins: [stateParseMixin],
	data() {
		return {
			selected: '',
			userName: ''
		};
	},
	computed: {
		...mapState({ state: 'profile' }),
		getUserName() {
			if (this.isFulfill) {
                // console.log(this.state.payload);
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
};
</script>
<style type='text/less' lang='less'>
.admin-head {
	margin-bottom: 20px;
	width: 100%;
	position: relative;
	.user-info {
		position: absolute;
		right: 10px;
	}
}
</style>
