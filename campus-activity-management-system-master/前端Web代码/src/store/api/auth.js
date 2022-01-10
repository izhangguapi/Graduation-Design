/*
 * @Author: your name
 * @Date: 2021-09-01 19:47:09
 * @LastEditTime: 2021-09-07 23:14:12
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \front-end\src\store\api\auth.js
 */
import axios from '@/utils/axios';

// const BASE_URL = process.env.VUE_APP_BASE_URL;
// const BASE_URL = '';

const USER_LOGIN = '/users/login';
export const login = ({
  username,
  password
}) => axios.post(USER_LOGIN, {
  username,
  password
});


export const getProfile = () => {
  let id = window.sessionStorage.getItem("userprofile");
  let GET_USER_INFO = `/users/${id}`;
  return axios.get(GET_USER_INFO);
}

const USER_LOGOUT = '/users/logout';
export const signOut = () => {
  window.sessionStorage.clear();
  return axios.get(USER_LOGOUT);
};
