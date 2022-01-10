import axios from '@/utils/axios';

const BASE_URL = '/activities';
// 活动筛选， 返回适合条件的全部活动
export const getActivities = (asort) => {
    let ACTIVITIES_URL = `${BASE_URL}/sort/${asort}`;
    return axios.get(ACTIVITIES_URL);
};

// 首页活动列表
let ACTIVITY_TYPE_LISTS = `${BASE_URL}/allactivities`;
export const getActivityTypeLists = () => {
    return axios.get(ACTIVITY_TYPE_LISTS);
};

// 活动详情
export const getActivityInfo = (aid) => {
    let uid = window.sessionStorage.getItem("userprofile");
    let ACTIVITY_INFO = `${BASE_URL}/detail?aid=${aid}&uid=${uid}`;
    return axios.get(ACTIVITY_INFO);
};

// 活动报名
export const updateActivityEnroll = () => {
    let uid = window.sessionStorage.getItem("userprofile");
    let  aid = window.sessionStorage.getItem("aid");
    let UPDATE_ACTIVITY_ENROLL = `/link/sign?aid=${aid}&uid=${uid}`;
    return axios.post(UPDATE_ACTIVITY_ENROLL);
};

// 取消报名
export const deleteActivityEnroll = () => {
    let uid = window.sessionStorage.getItem("userprofile");
    let  aid = window.sessionStorage.getItem("aid");
    let DELETE_ACTIVITY_ENROLL = `/link/quit?aid=${aid}&uid=${uid}`;
    return axios.delete(DELETE_ACTIVITY_ENROLL);
}

// 活动发布申请
const UPDATE_ACTIVITY_APPROVAL = `${BASE_URL}/newactivity`;
export const updateApproval = ({aname, atime, uusername, aadd, acontent, asort, uid}) => {
    const params = {aname, atime, uusername, aadd, acontent, asort, uid};
    return axios.post(UPDATE_ACTIVITY_APPROVAL, params);
};

// 删除活动
export const deleteActivity = (aid, uid) => {
    let DELETE_ACTIVITY = `${BASE_URL}/delete?aid=${aid}&uid=${uid}`;
    return axios.delete(DELETE_ACTIVITY);
}
