import actions from './actions';
import mutations from './mutations';

export default {
  namespaced: true,
  state: {
    info: {},
    // userCollections: {},
    userEnrolled: {},
    userPublished: {}
  },
  actions,
  mutations
};
