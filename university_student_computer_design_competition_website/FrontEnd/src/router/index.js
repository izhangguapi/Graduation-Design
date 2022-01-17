import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    base: '/',
    mode: 'history',
    routes: [{
        name: '登录',
        path: '/login',
        component: () => import('@/views/login'),
    }, {
        name: '注册',
        path: '/register',
        component: () => import('@/views/register')
    }, {
        name: '主页面',
        path: '/',
        component: () => import('@/views/layout/Home'),
        redirect: {
            path: '/home',
        }
    }, {
        path: '/home',
        meta:{
            icon: 'el-icon-s-home'
        },
        component: () => import('@/views/layout/Home'),
        children: [{
            name: '首页',
            path: '',
            component: () => import('@/views/HomeMain')
        }]
    }, {
        path: '/find',
        meta:{
            icon: 'el-icon-s-opportunity'
        },
        component: () => import('@/views/layout/Home'),
        children: [{
            name: '发现',
            path: '',
            component: () => import('@/views/FindMain')
        }]
    }]
})

export default router
