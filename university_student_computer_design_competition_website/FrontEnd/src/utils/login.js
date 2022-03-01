import store from "@/store";
import {Message} from "element-ui";
import {postRequest} from "@/utils/api";

// 判断是否保存了账号信息，用于自动登录
export function login() {
    console.log("自动登录");
    // localStorage存入账号密码
    if (localStorage.phone && localStorage.password || localStorage.email) {
        let loginForm = {
            phone: localStorage.phone,
            email: localStorage.email,
            password: localStorage.password
        }
        postRequest("/automaticLogin", loginForm).then((res) => {
            let data = res.data.data;
            if (data){
                store.state.uid = data.userId;
                store.state.name = data.name;
                store.state.gid = data.groupId;
                store.state.isLogin = true;
            }
        })
    } else {
        return false;
    }
    console.log("自动登录完成");
}