import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    base: '/',
    mode: 'history',
    routes: [
        {
            name: 'test',
            path: '/test',
            meta: {
                title: '测试'
            },
            component: () => import('@/views/test'),
        },
        {
            name: 'login',
            path: '/login',
            meta: {
                title: '登录'
            },
            component: () => import('@/views/Login'),
        },
        {
            name: 'register',
            path: '/register',
            meta: {
                title: '注册'
            },
            component: () => import('@/views/Register')
        },
        {
            name: 'index', // 主要布局
            path: '/',
            component: () => import('@/views/layout'),
            redirect: {
                path: '/home',
            }
        },
        {
            path: '/home',
            component: () => import('@/views/layout'),
            children: [{
                name: 'home',
                path: '',
                meta: {
                    title: '首页',
                },
                component: () => import('@/views/layout/Main/Home')
            }]
        },
        {
            path: '/find',
            component: () => import('@/views/layout'),
            children: [{
                name: 'find',
                path: '',
                meta: {
                    title: '发现',
                },
                component: () => import('@/views/layout/Main/Find')
            }, {
                name: 'find-score',
                path: 'result',
                meta: {
                    title: '比赛结果'
                },
                component: () => import('@/views/layout/Main/Find/Score')
            }, {
                name: 'find-detail',
                path: ':contestId',
                meta: {
                    title: '详情'
                },
                component: () => import('@/views/layout/Main/Find/Detail')
            }]
        },
        {
            path: '/publish',
            component: () => import('@/views/layout'),
            children: [{
                path: '',
                component: () => import('@/views/layout/Main/Publish'),
                children: [{
                    name: 'publish-competition',
                    path: '',
                    meta: {
                        title: '发布比赛'
                    },
                    component: () => import('@/views/layout/Main/Publish/Competition')
                }, {
                    name: 'publish-message',
                    path: 'message',
                    meta: {
                        title: '发布消息'
                    },
                    component: () => import('@/views/layout/Main/Publish/Message')
                },{
                    name: 'publish-score',
                    path: 'score',
                    meta: {
                        title: '评审比赛'
                    },
                    component: () => import('@/views/layout/Main/Publish/Score')
                }]
            }]
        },
        {
            path: '/mine',
            component: () => import('@/views/layout'),
            children: [{
                name: 'mine',
                path: '',
                meta: {
                    title: '个人中心'
                },
                component: () => import('@/views/layout/Main/Mine')
            }]
        },
        {
            path: '/announcement',
            component: () => import('@/views/layout'),
            children: [{
                name: 'announcement',
                path: '',
                meta: {
                    title: '公告中心',
                },
                component: () => import('@/views/layout/Main/Announcements')
            }]
        },
        {
            path: '/messages',
            component: () => import('@/views/layout'),
            children: [{
                name: 'messages-detail',
                path: ':messageId',
                meta: {
                    title: '内容',
                },
                component: () => import('@/views/layout/Main/Announcements/Detail')
            }]
        },
        {
            path: '/search',
            component: () => import('@/views/layout'),
            children: [{
                name: 'search',
                path: ':search',
                meta: {
                    title: '搜索结果'
                },
                component: () => import('@/views/layout/Main/Search')
            }]
        },
        {
            name: 'NotFound',
            path: '/404',
            component: () => import('@/views/404')
        },
        {
            path: '*',    // 此处需特别注意至于最底部
            redirect: '/404',
            meta: {
                title: '公告内容',
            },
        }
    ]
})

//路由导航冗余报错（路由重复）
//获取原型对象上的push函数
const VueRouterPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

/* 路由发生变化修改页面title */
router.beforeEach((to, from, next) => {
    // console.log(to);
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})
export default router
