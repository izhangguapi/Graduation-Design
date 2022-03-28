import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const state = {
    // 用户id
    uid: undefined,
    // 用户姓名
    gid: undefined,
    // 组id
    name: undefined,
    // 是否为管理员
    isAdmin:false,
    // 已打开的标签页
    tabs:[
        {route: '/admin', name: '首页'}
    ],
    // 激活状态
    activeIndex:'/admin'
}
const mutations = {
    // 添加标签页
    addTab (state, data) {
        this.state.tabs.push(data);
    },
    // 删除标签页
    removeTab (state, route) {
        let index = 0;
        for (let option of state.tabs) {
            if (option.route === route) {
                break;
            }
            index++;
        }
        this.state.tabs.splice(index, 1);
    },
    // 设置当前激活的tab
    setActiveIndex (state, index) {
        this.state.activeIndex = index;
    }
}
const actions = {}
const modules = {}

export default new Vuex.Store({
    state,
    mutations,
    actions,
    modules
})
