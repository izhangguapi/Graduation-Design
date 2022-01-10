<template>
    <Card class="publish-page">
        <h2>校园活动发布</h2>
        <Form :model="newActivity" class="publish-form" ref="publishForm">
            <Row :gutter="24">
                <Col span="12">
                    <FormItem prop="name" label="活动主题">
                        <Input class="item-content" type="text" v-model="newActivity.aname" placeholder="活动主题">
                        </Input>
                    </FormItem>
                </Col>
                <Col span="12">
                    <FormItem prop="type" label="活动类型">
                        <Select class="item-content" v-model="newActivity.asort" placeholder="选择活动类型">
                            <Option value="online">线上活动</Option>
                            <Option value="offline">线下活动</Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>
            <Row :gutter="24">
                <Col span="12">
                    <FormItem prop="address" label="活动地点">
                        <Input class="item-content" type="text" v-model="newActivity.aadd"
                               placeholder="如果为线下类型的活动，请输入活动地点。">
                        </Input>
                    </FormItem>
                </Col>
                <Col span="12">
                    <FormItem prop="brief" label="活动简介">
                        <Input class="item-content" type="text" v-model="newActivity.acontent"
                               placeholder="简单介绍一下活动，方便同学们了解。">
                        </Input>
                    </FormItem>
                </Col>
            </Row>
            <Row :gutter="24">
                <Col span="12">
                    <FormItem prop="deadline" label="活动时间">
                        <DatePicker class="date-picker"
                                    v-model="newActivity.atime"
                                    type="datetime"
                                    format="yyyy-MM-dd HH:mm:ss"
                                    :confirm=true
                                    @on-change="confirmDeadline"></DatePicker>
                    </FormItem>
                </Col>
                <Col :span="12">
                    <FormItem prop="concat" label="活动联系人">
                        <Input class="concat" type="text" v-model="newActivity.uusername" placeholder="自动获取您的用户名称" disabled>
                        </Input>
                    </FormItem>
                </Col>
            </Row>
<!--            <Row :gutter="24">-->
<!--                <Col span="12">-->
<!--                    <FormItem prop="dateTime" label="活动开始时间">-->
<!--                        <DatePicker class="date-picker"-->
<!--                                    v-model="newActivity.start"-->
<!--                                    type="date"-->
<!--                                    format="yyyy-MM-dd"-->
<!--                                    :confirm=true-->
<!--                                    @on-change="confirmStart"></DatePicker>-->
<!--                    </FormItem>-->
<!--                </Col>-->
<!--                <Col span="12">-->
<!--                    <FormItem prop="dateTime" label="活动结束时间">-->
<!--                        <DatePicker class="date-picker"-->
<!--                                    v-model="newActivity.end"-->
<!--                                    type="date"-->
<!--                                    format="yyyy-MM-dd"-->
<!--                                    :confirm=true-->
<!--                                    @on-change="confirmEnd"></DatePicker>-->
<!--                    </FormItem>-->
<!--                </Col>-->
<!--            </Row>-->
            <Row>
                <FormItem>
                    <div class="actions">
                        <Button class="btn" type="primary" :loading="isLoading" @click.prevent="addApproval">提交发布申请
                        </Button>
                    </div>
                </FormItem>
            </Row>
        </Form>
    </Card>
</template>

<script>
import {mapState} from 'vuex';
import {updateApproval} from '@/store/api/activity';

export default {
    data() {
        return {
            isLoading: false,
            hasAddition: false,
            newActivity: {
                aname: '',
                asort: '',
                acontent: '',
                aadd: '',
                atime: '',
                start: '',
                end: '',
                uusername: '',
                uid: ''
            },
        };
    },
    computed: {
        ...mapState({user: 'profile'}),
        userInformation() {
            return this.user.payload.data;
        }
    },
    methods: {
        confirmDeadline(datetime) {
            const {end} = this.newActivity;
            if (end && end < datetime) {
                this.$Message.warning('报名截止日期不能大于结束日期，请重新选择');
                datetime = '';
            }
            this.newActivity.atime = datetime;
        },
        confirmStart(datetime) {
            const {end} = this.newActivity;
            if (end && end < datetime) {
                this.$Message.warning('开始日期不能大于结束日期，请重新选择');
                datetime = '';
            }
            this.newActivity.start = datetime;
        },
        confirmEnd(datetime) {
            const {start} = this.newActivity;
            if (start && start > datetime) {
                this.$Message.warning('结束日期不能小于开始日期，请重新选择');
                datetime = '';
            }
            this.newActivity.end = datetime;
        },
        isVaild() {
            const {
                aname,
                asort
            } = this.newActivity;

            if (aname && asort ) {
                return true;
            }
            return false;
        },
        async addApproval() {
            const params = {...this.newActivity};
            params.uid = this.userInformation.id;
            params.uusername = this.userInformation.username;
            console.log(params);
            const isVaild = this.isVaild();
            this.isLoading = true;
            if (isVaild) {
                await updateApproval(params)
                        .then(() => {
                            this.isLoading = false;
                            this.$Message.success('提交活动申请成功！');
                            this.reset();
                        })
                        .catch(() => {
                            this.isLoading = false;
                            // this.$Message.error('活动发布申请失败，请重试！');
                        })
            } else {
                this.isLoading = false;
                this.$Message.error('活动信息不符合规范哦，请补充完整重新提交！');
            }
        },
    },
}
</script>
<style type='text/less' lang='less'>
.publish {
    &-form {
        padding: 2% 4%;

        .ivu-form-item-label {
            width: 100%;
            display: block;
            text-align: left;
            font-size: 14px;
        }

        .item-content {
            width: 90%;
            margin-top: 10px;
        }

        .concat {
            width: 90%;
        }

        .date-picker {
            display: block;
            width: 90%;
        }

        .ivu-date-picker-focused input {
            box-shadow: none;
        }

        .tip {
            line-height: 30px;
        }

        .actions {
            text-align: center;

            .btn {
                padding: 10px 40px;
                margin-left: 20px;
            }
        }

        .addition-items {
            margin-top: 10px;
            padding: 5px 0;
            border: 1px solid transparent;
            border-radius: 5px;
            transition: all 0.2s linear;
            vertical-align: middle;
            line-height: 1;

            span {
                display: inline-block;
            }

            .item-label {
                width: 12%;
                text-align: right;
            }

            .item-content {
                max-width: 80%;
            }

            &:hover {
                border: 1px solid #abcdec;
            }
        }

        .addition-item {
            margin-top: 10px;
        }

        .addition-key {
            width: 12%;
        }

        .addition-val {
            width: 50%;
        }
    }
}
</style>
