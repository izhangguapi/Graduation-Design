import axios from '@/utils/axios';

const BASE_URL = '/api/feedback';
export const updateFeedback = ((params) => {
  return axios.post(BASE_URL, { params });
});