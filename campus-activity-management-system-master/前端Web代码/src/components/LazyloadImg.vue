<template>
  <figure class="img-wrapper">
    <div v-show="isOk" class="img-content">
      <slot>
        <img class="img" :src="target" :alt="target">
      </slot>
    </div>
    <div class="status-box" v-show="!isOk">
      <div v-show="this.status === allStatus.LOADING" class="lg">
         <Spin size="large"></Spin>
      </div>
      <div v-show="this.status === allStatus.ERROR" class="lg">
        <p class="text-muted">
          <span class="text-danger"><i class="fa fa-times" aria-hidden="true"></i></span>
          <span>加载图片失败</span>
        </p>
      </div>
    </div>
  </figure>
</template>

<script type="text/ecmascript-6">
import stateParseMixin from '@/utils/stateParseMixin';

const LOAD_STATUS = { INIT: 'init', LOADING: 'loading', LOADED: 'loaded', NULL: 'null', ERROR: 'error' };

export default {
  name: "LazyloadImg",
  mixins: [stateParseMixin],
  data() {
    return {
      status: LOAD_STATUS.INIT,
      stamp: 0,
    }
  },
  props: {
    src: {
      type: String,
      required: false
    },
  },
  computed: {
    target() {
      if (this.isOk) {
        return this.src;
      }
      return undefined;
    },
    isOk() {
      return this.status === LOAD_STATUS.LOADED;
    },
    allStatus() {
      return LOAD_STATUS;
    }
  },
  methods: {
    load() {
      const { src } = this;
      const stamp = this.stamp = Date.now();
      this.status = LOAD_STATUS.LOADING;
      const img = document.createElement('img');
      img.onload = () => {
        if (stamp === this.stamp) this.status = LOAD_STATUS.LOADED;
      };
      img.onerror = () => {
        if (stamp === this.stamp) this.status = LOAD_STATUS.ERROR;
      };
      img.src = src;
    },
  },
  mounted() {
    this.load();
  },
  watch: {
    src(val, oldVal) {
      if (val !== oldVal) {
        this.status = LOAD_STATUS.INIT;
        this.src = val;
        this.load();
      }
    },
  }
}
</script>

<style type="text/less" lang="less">
@import "~@/css/vars.less";

.img {
  width: 100%;
  &-wrapper {
    font-size: 12px;
    display: inline-block;
    vertical-align: middle;
    margin: 0;
    &__large {
      width: 100%;
    }
  }
  &-content {
    font-size: 0;
  }
}

.status-box {
  position: absolute;
  top: 0;
  z-index: 3;
  left: 0;
  width: 100%;
  height: 100%;
  .flex-line;
  color: #ddd;
  text-align: center;
  .lg {
    font-size: 24px;
  }
}

p {
  margin: 0;
}
</style>
