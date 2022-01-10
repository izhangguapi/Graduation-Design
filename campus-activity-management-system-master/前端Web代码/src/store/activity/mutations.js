import { generateAsyncMutation } from '@/store/generateAsync';
import * as types from './mutationTypes';

export default {
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITIES,
    statePath: 'activities'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITY_TYPE_LISTS,
    statePath: 'activityTypeLists'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_LATEST_ACTIVITIES,
    statePath: 'latestActivities'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITY_INFO,
    statePath: 'activityInfo'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITY_COMMENTS,
    statePath: 'activityComments'
  })


}