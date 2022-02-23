import store from "@/store";
import {Message} from "element-ui";

// 判断是否保存了账号信息，用于自动登录
export function login(path) {
    console.log(store.state);
    if (localStorage.uid && localStorage.name && localStorage.gid){
        store.state.uid = localStorage.uid;
        store.state.name = localStorage.name;
        store.state.gid = localStorage.gid;
        store.state.isLogin = true;
        return true;
    }else if(path==="/login" || store.state.uid && store.state.name && store.state.gid){
        return false;
    } else {
        Message.warning("请登录！！！");
        return false;
    }
}