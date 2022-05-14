import store from "@/store";
import {getRequest} from "@/utils/api";

export function getUser() {
    // localStorage获取token
    if (localStorage.token) {
        getRequest("/currentUser").then(res=>{
            // console.log(res);
            let data = res.data.data;
            if (data){
                store.state.uid = data.uid;
                store.state.name = data.name;
                store.state.gid = data.gid;
                store.state.isAdmin = data.isAdmin;
            }
        })
        return true;
        // 舍弃
        // let loginForm = {
        //     phone: localStorage.phone,
        //     email: localStorage.email,
        //     password: localStorage.password
        // }
        // postRequest("/automaticLogin", loginForm).then(res => {
        //     console.log(res);
        //     let token = res.data.data;
        //     localStorage.setItem("token",token)
        //     console.log(token);
        //
        //     if (data){
        //         store.state.uid = data.userId;
        //         store.state.name = data.name;
        //         store.state.gid = data.groupId;
        //     }
        // })
    } else {
        return false;
    }
}