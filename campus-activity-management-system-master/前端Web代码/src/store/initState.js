import _ from 'lodash';

const initState = {
  profile: {}
};

export default _.reduce(initState, (result, v, key) => ({
  ...result,
  [key]: {payload: v, isFetching: false, isError: false, error: null, isFulfill: false}
}), {});
