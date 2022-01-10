/*
 * @Author: your name
 * @Date: 2021-09-02 14:55:55
 * @LastEditTime: 2021-09-07 16:52:46
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vue\src\plugins\element.js
 */
import Vue from 'vue'
import { Button, Form, FormItem, Input, Select, Option, Message, Container, Header, Aside, Main, Switch, Link, Menu, MenuItem, Table } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'// element-ui的css

Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Select)
Vue.use(Option)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Switch)
Vue.use(Link)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Table)

Vue.prototype.$message = Message
