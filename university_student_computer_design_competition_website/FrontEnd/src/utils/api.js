import axios from 'axios'
import {Message} from "element-ui";

//响应拦截器
axios.interceptors.response.use(success => {
    //业务逻辑错误
    if (success.status && success.status === 200) {
        if (success.data.code === 500) {
            Message.error({message: success.data.message});
        }
    }
}, error => {
    Message.error({message: error.data.message});
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