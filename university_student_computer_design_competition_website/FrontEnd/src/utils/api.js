import axios from 'axios'
import {Message} from "element-ui";
// 测试后台服务器是否正常运行

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
    //console.log(success)
    switch (success.data.code) {
        case 100:
            return success;
        case 200:
            //Message.success(success.data.msg);
            return success;
        case 201:
            Message.error(success.data.msg);
            return null;
        default:
            return success;
    }
}, error => {
    switch (error.response.status) {
        case 500:
            Message.error("内部服务器错误！！！");
            break;
        default:
            Message.error("未知错误！！！");
            break;
    }
})
// url前缀
let base = '/api';
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