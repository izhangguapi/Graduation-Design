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
    Result,
    PageHeader,
    Timeline,
    TimelineItem,
    Tooltip,
    Descriptions,
    DescriptionsItem,
    Table,
    TableColumn,
    Pagination,
    Drawer,
    Tag,
    Aside,
    Submenu,
    MenuItemGroup,
    Popconfirm,

    Notification,
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
        Vue.use(Result);
        Vue.use(PageHeader);
        Vue.use(Timeline);
        Vue.use(TimelineItem);
        Vue.use(Tooltip);
        Vue.use(Descriptions);
        Vue.use(DescriptionsItem);
        Vue.use(Table);
        Vue.use(TableColumn);
        Vue.use(Pagination);
        Vue.use(Drawer);
        Vue.use(Tag);
        Vue.use(Aside);
        Vue.use(Submenu);
        Vue.use(MenuItemGroup);
        Vue.use(Popconfirm);

        Vue.prototype.$message = Message;
        Vue.prototype.$msgbox = MessageBox;
        Vue.prototype.$alert = MessageBox.alert;
        Vue.prototype.$confirm = MessageBox.confirm;
        Vue.prototype.$prompt = MessageBox.prompt;
        Vue.prototype.$notify = Notification;
    }
}
export default element