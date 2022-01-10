import axios from '@/utils/axios';

// 参加的活动列表
export const getUserEnrolled = ( uid ) => {
    let ENROLLED_URL = `/link/uid/${uid}`;
    return axios.get(ENROLLED_URL);
};

// 发布的活动列表
export const getUserPublished = ( uid ) => {
    let PUBLISHED_URL = `/activities/my/${uid}`;
    return axios.get(PUBLISHED_URL);
};

// 修改密码
const UPDATE_PWD = '/users/update';
export const updateUserPwd = ({ id, username, password, status }) => {
    const params = { id, username, password, status };
    // console.log(params);
    return axios.post(UPDATE_PWD, params);
};

// 修改活动信息
const UPDATE_PUBLISHEDINFO = `/activities/update`;
export const updatePublished = (params) => {
    return axios.post(UPDATE_PUBLISHEDINFO, params);
};

// 获取参与活动人员列表
export const participantsList = (aid) => {
    let PARTICIPANTS_LIST = `/link/aid/${aid}`;
    return axios.get(PARTICIPANTS_LIST);
}

// TODO: 修改用户信息接口
// const UPDATE_INFO = `${BASE_URL}/update/info`;
// export const updateUserInfo = ({ userId, oldPhone, newPhone }) => {
//     const params = { userId, oldPhone, newPhone };
//     return axios.put(UPDATE_INFO, { params });
// };

// TODO: 收藏功能接口
// const COLLECTIONS_URL = `${BASE_URL}/collections`;
// export const getUserCollections = ({ userId }) => {
//     const params = { userId };
//     return axios.get(COLLECTIONS_URL, { params });
// };
