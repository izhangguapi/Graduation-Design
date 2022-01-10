import axios from '@/utils/axios';
import _ from 'lodash';

// const BASE_URL = '';

// 全部活动信息
const LOAD_ACTIVITIES = '/activities/allactivities';
export const getActivities = () => {
  return axios.get(LOAD_ACTIVITIES);
};

// 活动信息详情
export const getActivityDetail = ( aid ) => {
  let uid = window.sessionStorage.getItem('userprofile');
  let LOAD_ACTIVITY_DETAIL = `/activities/detail?aid=${aid}&uid=${uid}`
  return axios.get(LOAD_ACTIVITY_DETAIL);
};

// 活动报名名单
export const getEnrolledList = (activityId) => {
  let LOAD_ENROLLED_LIST = `/link/aid/${activityId}`
  return axios.get(LOAD_ENROLLED_LIST);
};

// 删除活动
export const removeActivity = (aid, uid) => {
  let REMOVE_ACTIVITY = `/activities/delete?aid=${aid}&uid=${uid}`
  return axios.delete(REMOVE_ACTIVITY);
};

// 增加活动
const UPDATE_ACTIVITY = `/addActivity`;
export const updateActivity = ((params) => {
  if (_.isObject(params.addition)) {
    const addition = JSON.stringify(params.addition);
    params.addition = addition;
  }
  if(!_.has(params, 'backPoster')) {
    params['backPoster'] = '';
  }
  if(!_.has(params, 'address')) {
      params['address'] = '';
  }
  return axios.post( UPDATE_ACTIVITY, params, { paramType: 'form' });
});

// TODO: 全部待审批活动
// const LOAD_APPROVALS = `${BASE_URL}/approvals`;
// export const getApprovals = ({ currentPage, pageSize }) => {
//   const params = {
//     currentPage,
//     pageSize
//   };
//   return axios.get(LOAD_APPROVALS, { params });
// };

// TODO: 待审批活动信息详情
// const LOAD_APPROVAL_DETAIL = `${BASE_URL}/approval`;
// export const getApprovalDetail = ({ activityId }) => {
//   const params = { activityId };
//   return axios.get(LOAD_APPROVAL_DETAIL, { params });
// };

// TODO: 更改待审核活动状态
// const UPDATE_APPROVAL_STATUS = `${BASE_URL}/approvalStatus`;
// export const updateApprovalStatus = ({activityId, managerId, status, advice = ''}) => {
//   const params = {
//     activityId,
//     managerId,
//     status,
//     advice
//   };
//   return axios.put(UPDATE_APPROVAL_STATUS, {params});
// };

// 全部用户信息
const LOAD_STUDENTS = `/users/allusers`;
export const getStudents = () => {
  return axios.get(LOAD_STUDENTS);
};

// 删除用户
export const removeUser =(id) => {
  let DELETE_USER = `/users/delete/${id}`
  return axios.delete(DELETE_USER);
}
