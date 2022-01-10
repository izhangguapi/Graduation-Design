export default [{
    title: '活动管理',
    icon: 'md-color-wand',
    name: 'admin-activity',
    children: [{
        title: '活动信息管理',
        name: 'admin-activities',
        to: {
          path: '/admin/activities'
        },
      },
      {
        title: '待审批活动',
        name: 'admin-approvals',
        to: {
          path: '/admin/approvals'
        },
      },
    ]
  },
  {
    title: '用户信息管理',
    icon: 'md-infinite',
    name: 'admin-students',
    to: {
      path: '/admin/students',
    },
  },
  // {
  //   title: '活动管理员管理',
  //   icon: 'md-aperture',
  //   name: 'admin-managers',
  //   to: {
  //     path: '/admin/managers',
  //   },
  // }
];
