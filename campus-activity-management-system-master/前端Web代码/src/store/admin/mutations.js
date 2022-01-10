import {
  generateAsyncMutation
} from '@/store/generateAsync';
import * as types from './mutationTypes';

export default {
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITIES,
    statePath: 'activities'
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITY_DETAIL,
    statePath: 'activityDetail'  
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ENROLLED_LIST,
    statePath: 'enrolledList'  
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_ACTIVITY_WORKS,
    statePath: 'activityWorks'  
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_APPROVALS,
    statePath: 'approvals'
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_APPROVAL_DETAIL,
    statePath: 'approvalDetail'
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_STUDENTS,
    statePath: 'students'
  }),
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_MANAGERS,
    statePath: 'managers'
  })
}