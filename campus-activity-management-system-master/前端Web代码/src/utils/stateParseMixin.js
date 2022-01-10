import _ from 'lodash';

export default {
  computed: {
    isFulfill() {
      if (_.isNil(this.state)) {
        return false;
      }
      return this.state.isFulfill;
    },
    isFetching() {
      if (_.isNil(this.state)) {
        return false;
      }
      return this.state.isFetching;
    },
    isError() {
      if (_.isNil(this.state)) {
        return false;
      }
      return this.state.isError;
    },
    error() {
      if (_.isNil(this.state)) {
        return null;
      }
      return this.state.error;
    },
  },
};
