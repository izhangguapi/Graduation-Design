import Vue from 'vue';
import _ from 'lodash';

const PENDDING_STATE = 'PENDDING';
const FULFILL_STATE = 'FULFILL';
const REJECTED_STATE = 'REJECTED';

export const generateAction = ({
    mutateType,
    meta
}) => (...arg) => {
    const [{
        commit
    }, payload] = arg;
    commit(mutateType, {
        payload,
        meta
    });
};

export const generateAsyncAction = (action, {
    mutateType,
    meta
}) => (...arg) => {
    const [{
        commit
    }, ...payload] = arg;
    commit(`${mutateType}_${PENDDING_STATE}`, {
        payload,
        meta
    });
    return action(...payload)
        .then(response => {
            commit(`${mutateType}_${FULFILL_STATE}`, {
                response,
                payload,
                meta
            });
            return response;
        })
        .catch(error => {
            commit(`${mutateType}_${REJECTED_STATE}`, {
                error,
                payload,
                meta
            });
        });
};

export const generateMutation = (mutation, {
    mutateType,
    statePath
}) => ({
    [mutateType]: (state, currentState) => {
        const payload = {
            ...state[statePath].payload,
            ...currentState.payload
        };
        Vue.set(state, statePath, {
            payload
        });
    }
});

function defaultMergeStrategy(val) {
    return val;
}

function gainState(target, props) {
    if (_.isArray(props)) {
        return props.length > 0 ? gainState(target[props.shift()], props) : target;
    } else if (_.isString(props)) {
        return gainState(target, props.split('.'));
    }
    return target;
}

export const generateAsyncMutation = (mutation, {
    mutateType,
    statePath,
    mergeBy = defaultMergeStrategy
}) => {
    if (_.isNil(mutation)) mutation = {};

    const penddingFn = _.isFunction(mutation.before) ? mutation.before : (state) => {
        const currentState = mergeBy({
            isFetching: true,
            isError: false,
            error: undefined,
            payload: null,
            isFulfill: false
        }, gainState(state, statePath));
        Vue.set(state, statePath, currentState);
    };

    const errorFn = _.isFunction(mutation.failure) ? mutation.failure : (state, {
        error
    }) => {
        const currentState = mergeBy({
            isFetching: false,
            isError: true,
            error,
            payload: null,
            isFulfill: false
        }, gainState(state, statePath));

        Vue.set(state, statePath, currentState);
    };

    let fulfillFn = null;
    if (_.isFunction(mutation)) {
        fulfillFn = mutation;
    } else {
        fulfillFn = _.isFunction(mutation.success) ? mutation.success : (state, {
            response
        }) => {
            const currentState = mergeBy({
                payload: response,
                isFetching: false,
                isError: false,
                error: undefined,
                isFulfill: true
            }, gainState(state, statePath));
            Vue.set(state, statePath, currentState);
        };
    }
    return {
        [`${mutateType}_${PENDDING_STATE}`]: penddingFn,
        [`${mutateType}_${REJECTED_STATE}`]: errorFn,
        [`${mutateType}_${FULFILL_STATE}`]: fulfillFn
    };
};