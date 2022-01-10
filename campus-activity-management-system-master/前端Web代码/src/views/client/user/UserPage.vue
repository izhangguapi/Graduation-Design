<template>
	<Card class='user-page'>
		<div class="user-update">
<!--			<Button class="update-btn" type="warning" ghost @click.prevent="showInfoModal">修改个人信息</Button>-->
			<Button class="update-btn" type="warning" ghost @click.prevent="showPwdModal">修改个人密码</Button>
		</div>

<!--		<Modal v-model="isShowInfoModal" title="修改个人信息" @on-ok="updateInfo" :mask-closable=false>-->
<!--			<Form class="user-form">-->
<!--				<FormItem>-->
<!--					<label class="item-label">院系：</label>-->
<!--					<Input type="text" v-model="userInformation.user_department" disabled>-->
<!--					</Input>-->
<!--				</FormItem>-->
<!--				<FormItem>-->
<!--					<label class="item-label">专业：</label>-->
<!--					<Input type="text" v-model="userInformation.user_profession" disabled>-->
<!--					</Input>-->
<!--				</FormItem>-->
<!--			</Form>-->
<!--		</Modal>-->

		<Modal v-model="isShowPwdModal" title="修改个人密码" @on-ok="updatePwd" :mask-closable=false>
			<Form class="user-form">
				<FormItem>
					<label class="item-label">原始密码：</label>
					<Input type="password" v-model="userPwd.oldPwd" placeholder="输入旧密码">
					</Input>
				</FormItem>
				<FormItem>
					<label class="item-label">新密码：</label>
					<Input type="password" v-model="userPwd.newPwd" placeholder="输入新密码">
					</Input>
				</FormItem>
				<FormItem>
					<label class="item-label">确认新密码：</label>
					<Input type="password" v-model="userPwd.confirmPwd" placeholder="再输入一次新密码">
					</Input>
				</FormItem>
			</Form>
		</Modal>

		<div class="user-infos">
			<!-- 头像 -->
			<template v-if="userInformation.usex === '女'">
				<Avatar class="user-avatar girl-avatar" size="large" :loading="isFetching" src="https://img.tukuppt.com/png_preview/00/05/38/DCoSdpcUVs.jpg!/fw/780">
				</Avatar>
			</template>
			<template v-else>
				<Avatar class="user-info-avatar boy-avatar" size="large" :loading="isFetching" src="https://img.tukuppt.com/png_preview/00/05/38/FBeq0yz5rH.jpg!/fw/780">
				</Avatar>
			</template>

			<p class="user-name">{{ userInformation.username }}</p>

			<p class="user-department">
				<i class="fa fa-graduation-cap" aria-hidden="true"></i>
				天津大学智能与计算学部
			</p>
		</div>

		<Tabs class="user-tabs">
            <TabPane label="发布过的活动" icon="ios-color-wand">
                <UserPublished :userId="userInformation.id"></UserPublished>
            </TabPane>
			<TabPane label="参与过的活动" icon="md-contacts">
				<UserEnrolled :userId="userInformation.id"></UserEnrolled>
			</TabPane>
            <TabPane label="收藏的活动" disabled icon="ios-bookmark">
                <UserCollections :userId="userInformation.id"></UserCollections>
            </TabPane>
		</Tabs>
	</Card>
</template>

<script>
import { mapState } from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';
import { updateUserPwd } from '@/store/api/user';
import UserEnrolled from './UserEnrolled';
import UserPublished from './UserPublished';
import UserCollections from './UserCollections';

export default {
	components: { UserEnrolled, UserPublished, UserCollections },
	mixins: [stateParseMixin],
	data() {
		return {
			isShowInfoModal: false,
			isShowPwdModal: false,
			userPwd: {
				oldPwd: '',
				newPwd: '',
				confirmPwd: ''
			},
		};
	},
	computed: {
		...mapState({ state: 'profile' }),
		userInformation() {
			if (this.isFulfill) {
                // console.log("用户主页用户信息");
                // console.log(this.state.payload);
				return this.state.payload.data;
			}
			return {};
		}
	},
	methods: {
		// showInfoModal() {
		// 	this.isShowInfoModal = true;
		// },
		showPwdModal() {
			this.isShowPwdModal = true;
		},
		updatePwd() {
			const { oldPwd, newPwd, confirmPwd } = this.userPwd;
			const id = this.userInformation.id;
            const username = this.userInformation.username;
            const status = this.userInformation.status;
            const password = newPwd;
			if (newPwd === oldPwd) {
				this.$Message.warning('新密码不能和原始密码一样哦，请重新输入哦！');
				this.emptyPwd();
			} else if (newPwd !== confirmPwd) {
				this.$Message.warning('新密码和确认密码要一致哦！');
				this.emptyPwd();
			} else if (oldPwd !== window.sessionStorage.getItem('password')) {
                this.$Message.warning('请填写正确的原始密码！');
                this.emptyPwd();
            } else {
				updateUserPwd({ id, username, password, status })
					.then(() => {
						this.$Message.success('密码修改成功！');
						this.emptyPwd();
						this.$router.replace({ name: 'login' });
					})
					.catch((err) => {
						this.$Message.error(err.message);
					})
			}

		},
		emptyPwd() {
			this.userPwd.oldPwd = '';
			this.userPwd.newPwd = '';
			this.userPwd.confirmPwd = '';
		}
	}
}
</script>
<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.user {
	&-page {
		width: 100%;
		min-height: 180px;
		padding: 15px 0 10px;
	}

	&-update {
		text-align: right;
		.update-btn {
			margin-right: 20px;
		}
	}

	&-form {
		padding: 10px 20px;
	}

	&-infos {
		.flex-vertical();
		p {
			font-size: 16px;
			line-height: 50px;
		}
	}

	&-info-avatar {
		width: 120px;
		height: 120px;
		background: transparent;
	}

	&-name {
		font-size: 25px !important;
		margin-top: 10px;
		font-weight: 500;
		letter-spacing: 2px;
		color: black;
	}

	&-profession,
	&-department {
		color: rgb(70, 80, 95);
	}

	&-tabs {
		padding: 45px 70px;
	}
}
</style>
