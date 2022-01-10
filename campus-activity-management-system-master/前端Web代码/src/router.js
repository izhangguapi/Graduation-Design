import Vue from 'vue'
import Router from 'vue-router'
import iView from 'iview';

import HomePage from '@/views/client/layout/HomePage.vue';
import AdminHost from '@/views/admin/layout/Index.vue';
import CilentIndex from '@/views/client/activity/Index.vue';

Vue.use(iView);
Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    // 登录
    {
      path: '/users/login',
      name: 'login',
      component: () => import('@/views/login/Index.vue'),
    },

    // 注册
    {
      path: '/users/register',
      name: 'register',
      component: () => import('@/views/register/Index.vue'),
    },

    // 前台
    {
      path: '/',
      name: 'home',
      component: HomePage,
      redirect: {
        name: 'activity'
      }
    },

    // 首页
    {
      path: '/activities',
      component: HomePage,
      children: [{
        name: 'activity',
        path: '',
        component: CilentIndex,
      }]
    },

    // 发现
    {
      path: '/find',
      component: HomePage,
      children: [{
          name: 'find',
          path: '',
          component: () => import('@/views/client/activity/ActivityFilter.vue'),
        },
        {
          name: 'find-detail',
          path: ':aid',
          component: () => import('@/views/client/activity/ActivityPost.vue'),
        },
        {
          name: 'post-work',
          path: ':aid/work/new',
          component: () => import('@/views/client/activity/PostWork.vue'),
        }
      ]
    },

    // 活动发布
    {
      path: '/publish',
      component: HomePage,
      children: [{
        name: 'publish',
        path: '',
        component: () => import('@/views/client/publish/Index.vue'),
      }]
    },

    // 个人主页
    {
      path: '/user',
      component: HomePage,
      children: [{
          name: 'user',
          path: '',
          component: () => import('@/views/client/user/UserPage.vue'),
        },
        {
          name: 'user-works',
          path: ':aid/works',
          component: () => import('@/views/client/user/ActivityWorks.vue'),
        }
      ]
    },

    // 意见反馈
    {
      path: '/feedback',
      component: HomePage,
      children: [{
        name: 'feedback',
        path: '',
        component: () => import('@/components/Feedback'),
      }]
    },

    // 管理后台
    {
      path: '/admin',
      component: AdminHost,
      children: [{
          name: 'admin-activities',
          path: 'activities',
          component: () => import('@/views/admin/activities/Index.vue'),
        },
        {
          name: 'admin-add-activity',
          path: 'activities/new',
          component: () => import('@/views/admin/activities/NewActivity.vue'),
        },
        {
          name: 'admin-activity-detail',
          path: 'activities/:aid',
          component: () => import('@/views/admin/activities/ActivityDetail.vue'),
        },
        {
          name: 'admin-activity-enrolled',
          path: 'activities/:aid/enrolled',
          component: () => import('@/views/admin/activities/Enrolled.vue'),
        },
        {
          name: 'admin-activity-works',
          path: 'activities/:aid/works',
          component: () => import('@/views/admin/activities/ActivityWorks.vue'),
        },
        {
          name: 'admin-approvals',
          path: 'approvals',
          component: () => import('@/views/admin/activities/Approvals.vue'),
        },
        {
          name: 'admin-approval-detail',
          path: 'approvals/:aid',
          component: () => import('@/views/admin/activities/ApprovalDetail.vue'),
        },
        {
          name: 'admin-students',
          path: 'students',
          component: () => import('@/views/admin/students/Index.vue'),
        },
        // {
        //   name: 'admin-managers',
        //   path: 'managers',
        //   component: () => import('@/views/admin/managers/Index.vue'),
        // },
        // {
        //   name: 'forbidden',
        //   path: 'managers/forbidden',
        //   component: () => import('@/views/admin/managers/ForbiddenPage'),
        // },
        // {
        //   name: 'admin-concat',
        //   path: 'concat',
        //   component: () => import('@/components/Feedback'),
        // },
      ]
    }
  ]
});

router.beforeEach((to, from, next) => {
  iView.LoadingBar.config({
    color: '#008bf8',
    failedColor: '#f0ad4e',
    height: 4
  });
  iView.LoadingBar.start();
  next();
});

router.afterEach(() => {
  window.scrollTo(0, 0);
  iView.LoadingBar.update('50%');
  iView.LoadingBar.finish();
});


export default router;
