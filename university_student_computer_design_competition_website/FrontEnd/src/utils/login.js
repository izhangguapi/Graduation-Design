import store from "@/store";
import {Message} from "element-ui";
import {postRequest} from "@/utils/api";

// 判断是否保存了账号信息，用于自动登录
export function login() {
    console.log("自动登录");
    console.log(store.state);
    // localStorage存入账号密码
    if (localStorage.phone && localStorage.password || localStorage.email) {
        let loginForm = {
            phone: localStorage.phone,
            email: localStorage.email,
            password: localStorage.password
        }
        postRequest("/automaticLogin", loginForm).then((res) => {
            Message.success(res.data.msg);
            let data = res.data.data;
            if (data){
                store.state.uid = data.userId;
                store.state.name = data.name;
                store.state.gid = data.groupId;
                store.state.isLogin = true;
            }
        })
    } else {
        Message.warning("请登录！！！");
        return false;
    }
    console.log("自动登录完成");

    // if (localStorage.uid && localStorage.name && localStorage.gid) {
    //     store.state.uid = localStorage.uid;
    //     store.state.name = localStorage.name;
    //     store.state.gid = localStorage.gid;
    //     store.state.isLogin = true;
    //     Message.success("自动登录成功。");
    //     return true;
    // }
}