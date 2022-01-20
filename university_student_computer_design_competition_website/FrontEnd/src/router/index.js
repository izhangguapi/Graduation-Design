import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    base: '/',
    mode: 'history',
    routes: [{
        name: 'login',
        path: '/login',
        meta: {
            title: '登录'
        },
        component: () => import('@/views/Login'),
    }, {
        name: 'register',
        path: '/register',
        meta: {
            title: '注册'
        },
        component: () => import('@/views/Register')
    }, {
        name: 'index', // 主要布局
        path: '/',
        component: () => import('@/views/layout'),
        redirect: {
            path: '/home',
        }
    }, {
        path: '/home',
        component: () => import('@/views/layout'),
        children: [{
            name: 'home',
            path: '',
            meta: {
                title: '首页',
            },
            component: () => import('@/views/Home')
        }]
    }, {
        path: '/find',
        component: () => import('@/views/layout'),
        children: [{
            name: 'find',
            path: '',
            meta: {
                title: '发现',
            },
            component: () => import('@/views/Find')
        }]
    }, {
        path: '/publish',
        component: () => import('@/views/layout'),
        children: [{
            name: 'publish',
            path: '',
            meta: {
                title: '发布',
            },
            component: () => import('@/views/Publish')
        }]
    }, {
        path: '/mine',
        component: () => import('@/views/layout'),
        children: [{
            name: 'mine',
            path: '',
            meta: {
                title: '个人中心'
            },
            component: () => import('@/views/Mine')
        }]
    }]
})
/* 路由发生变化修改页面title */
router.beforeEach((to, from, next) => {
    console.log(to);
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})
export default router
