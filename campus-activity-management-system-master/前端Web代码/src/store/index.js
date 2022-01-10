import Vue from 'vue';
import Vuex from 'vuex';

import {getProfile, signOut } from './api/auth';
import initState from './initState';
import { LOAD_PROFILE, SIGN_OUT } from './mutationTypes';
import { generateAsyncAction, generateAsyncMutation } from './generateAsync';

import activityModule from './activity';
import userModule from './user';
import adminModule from './admin';

Vue.use(Vuex);

export default new Vuex.Store({
  state: initState,
  strict: true,
  modules: {
    activity: activityModule,
    user: userModule,
    admin: adminModule,
  },
  actions: {
    getProfile: generateAsyncAction(getProfile, {mutateType: LOAD_PROFILE}),
    signOut: generateAsyncAction(signOut, {mutateType: SIGN_OUT})
  },
  mutations: {
    ...generateAsyncMutation(undefined, {mutateType: LOAD_PROFILE, statePath: 'profile'}),
    ...generateAsyncMutation(initState, {mutateType: SIGN_OUT})
  }
});
