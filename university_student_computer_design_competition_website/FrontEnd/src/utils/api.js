import axios from 'axios'
import {Message} from "element-ui";

//响应拦截器
// axios.interceptors.response.use(res => {
//     // 请求成功对响应数据做处理
//     // 该返回的数据则是axios.then(res)中接收的数据
//     return res
// }, err => {
//     // 在请求错误时要做的事儿
//     // 该返回的数据则是axios.catch(err)中接收的数据
//     return Promise.reject(err)
// })
axios.interceptors.response.use(success => {
    switch (success.data.code) {
        case 100:
            return success;
        case 200:
            Message.success(success.data.msg);
            return success;
        case 201:
            Message.error(success.data.msg);
            return null;
        default:
            Message.warning("未知信息！！！");
            return null;
    }
}, error => {
    Message.error(error.data.msg);
})

let base = '/api';
//
// //传送json格式的get请求
// export const getRequest = (url, params) => {
//     return axios({
//         method: 'post',
//         url: `${base}${url}`,
//         data: params
//     })
//
// }
//post请求，用来增添数据到数据库
export function postRequest(url, parameter) {
    return axios({
        url: `${base}${url}`,
        method: 'post',
        data: parameter
    })
}

//delete请求，用来删除数据库信息
export function deleteRequest(url, parameter) {
    return axios({
        url: url,
        method: 'delete',
        data: parameter
    })
}

//put请求用来更新数据库
export function putRequest(url, parameter) {
    return axios({
        url: url,
        method: 'put',
        data: parameter
    })
}

//get请求，用来查询数据库
export function getRequest(url, parameter) {
    return axios({
        url: url,
        method: 'get',
        params: parameter
    })
}