import actions from './actions';
import mutations from './mutations';

export default {
  namespaced: true,
  state: {
    latestActivities: {},
    activityTypeLists: {},
    activityInfo: {},
    activityComments: {},
    activities: {},
  },
  actions,
  mutations
};
