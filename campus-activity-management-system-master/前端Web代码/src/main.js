/*
 * @Author: your name
 * @Date: 2021-09-01 19:47:09
 * @LastEditTime: 2021-09-07 23:47:15
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \front-end\src\main.js
 */
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import iView from 'iview';
import VueMoment from 'vue-moment';
import moment from 'moment'
import {Table, TableColumn} from 'element-ui';
import Axios from 'axios';

import 'iview/dist/styles/iview.css';
import './css/index.less';
import 'font-awesome/css/font-awesome.css';
import 'element-ui/lib/theme-chalk/index.css';
import './plugins/element.js'

Vue.prototype.$http = Axios;
Vue.use(iView);
Vue.use(VueMoment, { moment });
Vue.component('ETable', Table);
Vue.component('EColumn', TableColumn);
Vue.config.productionTip = process.env.NODE_ENV === 'production';

Axios.defaults.baseURL = 'http://localhost:8181';

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
