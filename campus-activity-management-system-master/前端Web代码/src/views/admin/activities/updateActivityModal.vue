<template>
	<Modal v-model="isShowActivityModal" title="编辑活动信息" @on-ok="updateActivity" @on-cancel="closeModal" width="600">
            <Form class="detail-form">
                <FormItem label="活动主题">
                    <Input class="item-content" disabled type="text" v-model="currentActivity.aname">
                    </Input>
                </FormItem>
                <FormItem label="活动简介">
                    <Input class="item-content" type="text" v-model="currentActivity.acontent">
                    </Input>
                </FormItem>
                <FormItem label="活动地点" v-if="currentActivity.asort === 'offline'">
                    <Input class="item-content" type="text" v-model="currentActivity.aadd">
                    </Input>
                </FormItem>
                <FormItem label="活动时间">
                    <DatePicker class="item-content" :value="currentActivity.atime" type="datetime" format="yyyy-MM-dd HH:mm:ss" :confirm=true @on-change="changeDeadline"></DatePicker>
                </FormItem>
            </Form>
	</Modal>
</template>

<script>

export default {
	components: {},
	props: {
		isShowModal: {
			type: Boolean,
			defalut: false
		},
		info: {
			type: Object,
			defalut: null
		}
	},
	data() {
		return {
			currentActivity: {},
			isShowActivityModal: false,
		};
	},
	computed: {

	},
	methods: {
		updateActivity() {
			const info = { ...this.currentActivity };
			this.$emit('close');
			this.$emit('change', info);
		},
		closeModal() {
			this.$emit('close');
		},
		changeDeadline(newDate) {
			this.currentActivity.activity_enroll_deadline = newDate;
		},
	},
	watch: {
		info(newVal) {
			this.currentActivity = { ...newVal };
		},
		isShowModal(newVal) {
			this.isShowActivityModal = newVal;
		}
	}
}
</script>
<style type='text/less' lang='less'>
.detail-form {
	.ivu-form-item-label {
		display: block;
		width: 100%;
		text-align: left;
	}
	.item-content {
		display: block;
		width: 90%;
	}
	.concat-item {
		display: inline-block;
		width: 44%;
	}
}
</style>
