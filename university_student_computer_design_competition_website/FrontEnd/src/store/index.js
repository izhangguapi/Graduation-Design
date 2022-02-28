import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const actions = {}
const modules = {}
const mutations = {}
const state = {
    uid: undefined,
    gid: undefined,
    name: undefined,
    isLogin: false
}

export default new Vuex.Store({
    state,
    mutations,
    actions,
    modules
})
