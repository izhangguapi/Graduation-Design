import {
    Container,
    Header,
    Col,
    Dropdown,
    DropdownMenu,
    DropdownItem,
    Avatar,
    Badge,
    Menu,
    MenuItem,
    Divider,
    Main,
    Footer,
    Form,
    FormItem,
    Tabs,
    TabPane,
    Input,
    Row,
    Link,
    Checkbox,
    Button,
    Image,
    Message
} from 'element-ui';
import Vue from "vue";

const element = {
    install: function (Vue) {
        Vue.use(Container);
        Vue.use(Header);
        Vue.use(Col);
        Vue.use(Dropdown);
        Vue.use(DropdownMenu);
        Vue.use(DropdownItem);
        Vue.use(Avatar);
        Vue.use(Badge);
        Vue.use(Menu);
        Vue.use(MenuItem);
        Vue.use(Main);
        Vue.use(Footer);
        Vue.use(Form);
        Vue.use(FormItem);
        Vue.use(Divider);
        Vue.use(Tabs);
        Vue.use(TabPane);
        Vue.use(Input);
        Vue.use(Row);
        Vue.use(Link);
        Vue.use(Checkbox);
        Vue.use(Button);
        Vue.use(Image);
        Vue.prototype.$message = Message;

    }
}
export default element