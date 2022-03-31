import Vue from 'vue'
import VueRouter from 'vue-router'
import layout from '@/views/layout'

Vue.use(VueRouter)

const router = new VueRouter({
    // base: '/',
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
        // {
        //     name: 'index', // 主要布局
        //     path: '/',
        //     redirect: {
        //         path: '/',
        //     }
        // },
        {
            path: '/',
            component: layout,
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
            component: layout,
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
            path: '/manage',
            component: layout,
            children: [{
                name: 'manage',
                path: '',
                meta: {
                    title: '管理比赛'
                },
                component: () => import('@/views/layout/Main/Manage')
            }, {
                name: 'manage-contest',
                path: 'contest',
                meta: {
                    title: '发布或修改比赛'
                },
                component: () => import('@/views/layout/Main/Manage/Contest')
            }, {
                name: 'manage-review',
                path: 'review',
                meta: {
                    title: '发送消息'
                },
                component: () => import('@/views/layout/Main/Manage/Review')
            }, {
                name: 'manage-score',
                path: 'score',
                meta: {
                    title: '评审比赛'
                },
                component: () => import('@/views/layout/Main/Manage/Score')
            }]
        },
        {
            path: '/mine',
            component: layout,
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
            component: layout,
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
            component: layout,
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
            component: layout,
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
        },
        {
            path: '/admin',
            component: () => import('@/views/Admin'),
            children: [{
                name: 'admin',
                path: '',
                meta: {
                    title: '后台管理',
                },
                component: () => import('@/views/Admin/Main')
            }, {
                name: 'groups',
                path: 'groups',
                meta: {
                    title: '组管理',
                },
                component: () => import('@/views/Admin/Main/Groups')
            }, {
                name: 'users',
                path: 'users',
                meta: {
                    title: '用户管理',
                },
                component: () => import('@/views/Admin/Main/Users')
            }, {
                name: 'contests',
                path: 'contests',
                meta: {
                    title: '比赛管理',
                },
                component: () => import('@/views/Admin/Main/Contests')
            }, {
                name: 'messages',
                path: 'messages',
                meta: {
                    title: '消息管理',
                },
                component: () => import('@/views/Admin/Main/Messages')
            }, {
                name: 'scores',
                path: 'scores',
                meta: {
                    title: '报名评分管理',
                },
                component: () => import('@/views/Admin/Main/Scores')
            }, {
                name: 'review-contests',
                path: 'reviewContests',
                meta: {
                    title: '审核比赛',
                },
                component: () => import('@/views/Admin/Main/Review/Contests')
            }]
        },
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
    if (to.meta["title"]) {
        document.title = to.meta["title"];
    }
    next()
})
export default router
