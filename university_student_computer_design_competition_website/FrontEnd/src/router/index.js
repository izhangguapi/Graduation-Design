import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    base: '/',
    mode: 'history',
    routes: [{
        name: '登录',
        path: '/login',
        component: () => import('@/views/Login'),
    }, {
        name: '注册',
        path: '/register',
        component: () => import('@/views/Register')
    }, {
        name: '主页面',
        path: '/',
        component: () => import('@/views/layout'),
        redirect: {
            path: '/home',
        }
    }, {
        path: '/home',
        component: () => import('@/views/layout'),
        children: [{
            name: '首页',
            path: '',
            component: () => import('@/views/Home')
        }]
    }, {
        path: '/find',
        component: () => import('@/views/layout'),
        children: [{
            name: '发现',
            path: '',
            component: () => import('@/views/Find')
        }]
    }, {
        path: '/publish',
        component: () => import('@/views/layout'),
        children: [{
            name: '发布',
            path: '',
            component: () => import('@/views/Publish')
        }]
    }, {
        path: '/mine',
        component: () => import('@/views/layout'),
        children: [{
            name: '个人中心',
            path: '',
            component: () => import('@/views/Mine')
        }]
    }]
})

export default router
