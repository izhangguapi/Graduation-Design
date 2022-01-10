import { generateAsyncAction } from '@/store/generateAsync';
import * as api from '@/store/api/admin';
import * as types from './mutationTypes';

export default {
  getActivities: generateAsyncAction(api.getActivities, {
    mutateType: types.LOAD_ACTIVITIES,
  }),
  getActivityDetail: generateAsyncAction(api.getActivityDetail, {
    mutateType: types.LOAD_ACTIVITY_DETAIL,
  }),
  getEnrolledList: generateAsyncAction(api.getEnrolledList, {
    mutateType: types.LOAD_ENROLLED_LIST,
  }),
  getActivityWorks: generateAsyncAction(api.getActivityWorks, {
    mutateType: types.LOAD_ACTIVITY_WORKS,
  }),
  getApprovals: generateAsyncAction(api.getApprovals, {
    mutateType: types.LOAD_APPROVALS,
  }),
  getApprovalDetail: generateAsyncAction(api.getApprovalDetail, {
    mutateType: types.LOAD_APPROVAL_DETAIL
  }),
  getStudents: generateAsyncAction(api.getStudents, {
    mutateType: types.LOAD_STUDENTS,
  }),
  getManagers: generateAsyncAction(api.getManagers, {
    mutateType: types.LOAD_MANAGERS,
  })
};
