<template>
    <div class='filter-lists'>
        <ul class='activity-items'>
            <template v-for="(item, index) in lists">
                <li class="activity-item" :key=index>
                    <div class="activity-info">
                        <router-link :to="{ name: 'find-detail', params: {aid: item.aid} }">
                            <h3 class="activity-title"> {{ item.aname }} </h3>
                        </router-link>

                        <p class="activity-brief"> {{ item.acontent }}</p>

                        <p class="activity-deadline">
                            <span class="activity-tag">活动时间：</span>
                            {{ item.atime }}
                        </p>

                        <!--						<p class="activity-times">-->
                        <!--							<span class="activity-tag">活动时间：</span>-->
                        <!--							{{ ` ${item.activity_start} - ${item.activity_end} ` }}-->
                        <!--						</p>-->

                        <template v-if="item.asort === 'offline'">
                            <p class="activity-address">
                                <span class="activity-tag">活动地点：</span>
                                {{ item.aadd }}
                            </p>
                        </template>
                    </div>
                </li>
            </template>
        </ul>

    </div>
</template>

<script>

export default {
    props: {
        lists: {
            type: Array,
            default: () => []
        }
    },
    data() {
        return {
            initalScore: 0.0
        };
    },
}
</script>
<style type='text/less' lang='less'>
.activity {
    &-item {
        position: relative;
        width: 100%;
        height: 270px;
        display: flex;
        justify-content: flex-start;
        padding: 20px 35px;
        margin-bottom: 20px;
        border: 2px solid transparent;
        border-bottom: 1px solid #ccc;
        border-radius: 5px;
        transition: all 0.2s ease-in;
    }

    &-item:hover {
        border: 2px solid #2d8cf0;
    }

    &-items:first-child {
        margin-top: 50px;
    }

    &-poster {
        width: 45%;
        height: 220px;
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
    }

    &-info {
        width: 50%;
        margin-left: 20px;

        p {
            margin-top: 30px;
        }
    }

    &-score {
        font-size: 15px;
        height: 40px;
        line-height: 40px;
        padding: 1px 30px !important;
        position: absolute;
        right: 15px;
        top: 5px;
    }

    &-brief,
    &-tag {
        font-weight: 500;
    }

    &-brief {
        color: #525a6e;
    }
}
</style>
