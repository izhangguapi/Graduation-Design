import actions from './actions';
import mutations from './mutations';

export default {
  namespaced: true,
  state: {
    activities: {},
    activityDetail: {},
    enrolledList: {},
    activityWorks: {},
    approvals: {},
    approvalDetail: {},
    students: {},
    managers: {},
  },
  actions,
  mutations
};