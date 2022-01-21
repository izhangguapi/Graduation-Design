import {Message} from "element-ui";

// 判断是否保存了账号信息，用于自动登录
export function login() {
    if (sessionStorage.uid && sessionStorage.name && sessionStorage.gid){
        return null;
    }else if (localStorage.uid && localStorage.name && localStorage.gid){
        sessionStorage.setItem("uid",localStorage.uid)
        sessionStorage.setItem("name",localStorage.name)
        sessionStorage.setItem("gid",localStorage.gid)
        return null;
    }else{
        Message.warning("请登录！！！")
    }
}