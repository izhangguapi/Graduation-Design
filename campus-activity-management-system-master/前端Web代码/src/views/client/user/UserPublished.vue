<template>
    <div class="published-lists">
        <template v-if="isEmpty">
            <div class="empty-tips">
                <p><i class="fa fa-meh-o" aria-hidden="true"></i></p>
                <p> 你还没发布过活动哦！</p>
            </div>
        </template>
        <template v-else v-for="(item, index) in publishedLists">
            <Card class="activity-card" :key="index">
                <h3 class="activity-title">
                    <!--					<template v-if="item.activity_approval_status == 1">-->
                    <router-link slot="title" :to="'/find/'+item.aid">
                        {{ item.aname }}
                    </router-link>
                    <!--					</template>-->
                    <!--					<template v-else>-->
                    <!--						{{ item.aname }}-->
                    <!--					</template>-->
                </h3>
                <span class="activity-status" slot="extra"> {{ status[1] }}</span>
                <div class="actions">
                    <!--                    <template v-if="item.activity_approval_status == 1">-->
                    <Button class="action-btn" type="warning" ghost @click.prevent="showActivityModal(item)">
                        编辑活动
                    </Button>
                    <Button class="action-btn" type="warning" ghost @click.prevent="showParticipantsList(item.aid)">
                        参与人员名单
                    </Button>
                    <Button class="action-btn" type="warning" ghost @click.prevent="deleteActivityFunction(item.aid)">
                        删除活动
                    </Button>
                    <!--                        <template v-if="item.activity_type == 'online'">-->
                    <!--                            <Button class="action-btn" type="warning" ghost @click.prevent="toWorks(item.activity_id)">-->
                    <!--                                查看活动作品-->
                    <!--                            </Button>-->
                    <!--                        </template>-->
                    <!--                    </template>-->
                    <!--                    <template v-if="item.activity_approval_status == 2">-->
                    <!--                        <Button class="action-btn" type="warning" ghost-->
                    <!--                                @click.prevent="showAdviceModal(item.activity_approval_advice)">查看反馈意见-->
                    <!--                        </Button>-->
                    <!--                    </template>-->
                </div>
            </Card>
        </template>

        <Modal v-model="isShowParticipantsList" title="参加活动人员名单" >
            <Table :columns="participantsListColumns" :data="participants"></Table>
        </Modal>

        <Modal v-model="isShowActivityModal" title="编辑活动信息" @on-ok="updateActivity" :mask-closable=false>
            <Form class="user-form">
                <FormItem>
                    <label class="item-label">活动主题：</label>
                    <Input type="text" v-model="currentActivity.aname" disabled>
                    </Input>
                </FormItem>
                <FormItem>
                    <label class="item-label">活动简介：</label>
                    <Input type="text" v-model="currentActivity.acontent">
                    </Input>
                </FormItem>
                <FormItem v-if="currentActivity.asort === 'offline'">
                    <label class="item-label">活动地点：</label>
                    <Input type="text" v-model="currentActivity.aadd">
                    </Input>
                </FormItem>
                <FormItem>
                    <label class="item-label">活动时间：</label>
                    <DatePicker :value="currentActivity.atime" type="datetime"
                                format="yyyy-MM-dd HH:mm:ss" :confirm=true @on-change="changeDeadline"></DatePicker>
                </FormItem>
<!--                <FormItem>-->
<!--                    <label class="item-label">活动开始时间：</label>-->
<!--                    <DatePicker :value="currentActivity.activity_start" type="date" format="yyyy-MM-dd" :confirm=true-->
<!--                                @on-change="changeStartTime"></DatePicker>-->
<!--                </FormItem>-->
<!--                <FormItem>-->
<!--                    <label class="item-label">活动结束时间：</label>-->
<!--                    <DatePicker :value="currentActivity.activity_end" type="date" format="yyyy-MM-dd" :confirm=true-->
<!--                                @on-change="changeEndTime"></DatePicker>-->
<!--                </FormItem>-->
<!--                <FormItem>-->
<!--                    <label class="item-label">联系方式：</label>-->
<!--                    <Input class="concat-item" type="text" v-model="currentActivity.activity_concat_name"></Input>-->
<!--                    - -->
<!--                    <Input class="concat-item" type="text" v-model="currentActivity.activity_concat_phone"></Input>-->
<!--                </FormItem>-->
            </Form>
        </Modal>
    </div>
</template>

<script>
import _ from 'lodash';
import {mapState, mapActions} from 'vuex';
import stateParseMixin from '@/utils/stateParseMixin';
import {updatePublished, participantsList} from '@/store/api/user';
import {deleteActivity} from "@/store/api/activity";
// import {download} from '@/utils/download';

export default {
    props: {
        userId: {
            type: Number,
            require: true,
        }
    },
    mixins: [stateParseMixin],
    data() {
        return {
            isShowActivityModal: false,
            isShowParticipantsList: false,
            currentActivity: {},
            status: {
                0: '待审核',
                1: '审核通过',
                2: '审核失败'
            },
            participantsListColumns: [
                {
                    title: '用户ID',
                    key: 'uid'
                },
                {
                    title: '用户名',
                    key: 'uname'
                }
            ],
            participants: []
        };
    },
    computed: {
        ...mapState('user', {state: 'userPublished'}),
        publishedLists() {
            if (this.isFulfill) {
                // console.log("public");
                // console.log(this.state.payload.data);
                return this.state.payload.data;
            }
            return [];
        },
        isEmpty() {
            return this.publishedLists.length === 0;
        },
    },
    methods: {
        ...mapActions('user', ['getUserPublished']),
        loadData() {
            const {userId} = this;
            this.getUserPublished(userId);
        },
        showActivityModal(item) {
            this.isShowActivityModal = true;
            this.currentActivity = _.cloneDeep(item);
        },
        showParticipantsList(aid) {
            this.isShowParticipantsList = true;
            participantsList(aid)
                    .then((res)=>{
                        this.participants = res.data;
                    });
        },
        changeDeadline(newDate) {
            this.currentActivity.atime = newDate;
        },
        // changeStartTime(newDate) {
        //     this.currentActivity.activity_start = newDate;
        // },
        // changeEndTime(newDate) {
        //     this.currentActivity.activity_end = newDate;
        // },
        updateActivity() {
            const params = {...this.currentActivity};
            console.log(params);
            updatePublished(params)
                    .then(() => {
                        this.$Message.success('修改活动信息成功！');
                        window.location.reload();
                    })
                    .catch(() => {
                        this.$Message.error('修改活动信息失败！');
                    });
        },
        // showAdviceModal(advice) {
        //     this.$Modal.info({
        //         title: '活动申请反馈意见',
        //         content: `<p>${advice}</p>`,
        //     });
        // },
        deleteActivityFunction(aid) {
            deleteActivity(aid, this.userId)
                    .then(() => {
                        this.$Message.success('删除活动成功！');
                        window.location.reload();
                    })
                    .catch(() => {
                        this.$Message.error('删除活动失败！');
                    });
        },
        // toWorks(activityId) {
        //     this.$router.push({name: 'user-works', params: {aid: activityId}});
        // }
    },
    // mounted() {
    //     this.loadData();
    // },
    watch: {
        userId() {
            this.loadData();
        }
    }
}
</script>
<style type='text/less' lang='less'>
@import "~@/css/vars.less";

.published-lists {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;

    .activity {
        &-card {
            width: 31.2%;
            margin-bottom: 30px;
            margin-right: 2.1%;
        }

        &-title,
        &-title a {
            color: #484041;
            line-height: 2;
        }

        &-poster {
            width: 100%;
            height: 290px;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            transition: all 0.2s ease-in;
        }
    }

    .empty-tips {
        width: 100%;
        height: 200px;
        .flex-vertical();
        margin-top: 50px;
        font-size: 17px;
    }

    .actions {
        margin-top: 30px;
        display: flex;
        justify-content: space-around;

        .action-btn {
            margin-left: 10px;
        }
    }
}

.concat-item {
    width: 34%;
}
</style>
