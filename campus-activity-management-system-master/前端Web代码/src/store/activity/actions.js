import {
  generateAsyncAction
} from '@/store/generateAsync';
import * as api from '@/store/api/activity';
import * as types from './mutationTypes';

export default {
  // 活动筛选后的全部活动
  getActivities: generateAsyncAction(api.getActivities, {
    mutateType: types.LOAD_ACTIVITIES
  }),

  //  首页活动列表
  getActivityTypeLists: generateAsyncAction(api.getActivityTypeLists, {
    mutateType: types.LOAD_ACTIVITY_TYPE_LISTS
  }),

  // 活动详情
  getActivityInfo: generateAsyncAction(api.getActivityInfo, {
    mutateType: types.LOAD_ACTIVITY_INFO
  }),
};
