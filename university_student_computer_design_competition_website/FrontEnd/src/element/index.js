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
    //Image,
    Select,
    Option,
    Steps,
    Step,
    DatePicker,
    RadioGroup,
    Radio,
    Popover,
    Dialog,
    Carousel,
    CarouselItem,
    Card,

    Message,
    MessageBox
} from 'element-ui';



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
        //Vue.use(Image);
        Vue.use(Select);
        Vue.use(Option);
        Vue.use(Steps);
        Vue.use(Step);
        Vue.use(DatePicker);
        Vue.use(RadioGroup);
        Vue.use(Radio);
        Vue.use(Popover);
        Vue.use(Dialog);
        Vue.use(Carousel);
        Vue.use(CarouselItem);
        Vue.use(Card);

        Vue.prototype.$message = Message;
        Vue.prototype.$prompt = MessageBox.prompt;
    }
}
export default element
