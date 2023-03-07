import axios from 'axios'
import {Message} from "element-ui";

// 请求后端接口的前缀
let base = '/api';

//请求拦截
axios.interceptors.request.use(config => {
    //如果有token，统一带上
    const token = localStorage.token;
    if (token) { // 判断是否存在token，如果存在的话，则每个http header都加上token
        config.headers.Authorization = `${token}`;
    }
    return config
})

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
    return success;
}, error => {
    switch (error.response.status) {
        case 500:
            console.log("内部服务器错误！")
            break;
        case 400:
            console.log("错误的请求！")
            break;
        default:
            console.log("未知错误！")
            break;
    }
    return error;

})

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
        url: `${base}${url}`,
        method: 'delete',
        data: parameter
    })
}

//put请求用来更新数据库
export function putRequest(url, parameter) {
    return axios({
        url: `${base}${url}`,
        method: 'put',
        data: parameter
    })
}

//get请求，用来查询数据库
export function getRequest(url, parameter) {
    return axios({
        url: `${base}${url}`,
        method: 'get',
        params: parameter
    })
}