import { generateAsyncMutation } from '@/store/generateAsync';
import * as types from './mutationTypes';

export default {
  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_USERINFO,
    statePath: 'info'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_USERCOLLECTIONS,
    statePath: 'userCollections'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_USERENROLLED,
    statePath: 'userEnrolled'
  }),

  ...generateAsyncMutation(undefined, {
    mutateType: types.LOAD_USERPUBLISHED,
    statePath: 'userPublished'
  }),
};
