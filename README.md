# 毕业设计

## 忠告：同学们，Web已死，在2022年就不行啦，不只是java语言。做完毕业设计后，请为自己未知的未来做好准备，校招尽量参加，能进则进，出社会后就只能转行啦

### 项目简介

本项目是因毕业所设计出的一个前后端分离的web应用程序，前端采用Vue框架，后端采用Spring Boot框架、数据库采用MariaDB（可自行更改为其他关系型数据库）。

起初课题名称：基于Web的大学生计算机设计大赛报名网站的设计与实现

而后课题名称：基于Web的大学生计算机设计大赛网站的设计与实现

最终课题名称：基于Web的大学生计算机设计大赛报名网站的开发

**该项目已添加Spring Security和Redis，结构大改，有能力者可拉取代码自行完善。如需急用，请前往[这里](https://github.com/izhangguapi/Graduation-Design/releases)使用打包好的前端和后端，或者下载源码进行修改**

请各位仔细甄别，本项目已开源，如果您是购买的，请退款并差评。

挂一个不要脸的盗版贩卖者

![csdn盗版](https://storage.zhangguapi.com/picture/GitHub/csdn.png)

[![AUR](https://img.shields.io/badge/license-GPL-blue.svg)](https://github.com/zhangguapipi/Graduation_Design/blob/main/LICENSE)

|  git仓库 |  源码  |
|---|---|
|  github |  https://github.com/izhangguapi/Graduation-Design  |
|  码云  | https://gitee.com/izhangguapi/Graduation-Design |

### 项目启动
使用idea打开BackEnd和FrontEnd，后端需要jdk1.8，前端需要Vue脚手架和node.js才可运行

### 开发环境
| 名称               | 早期开发使用的版本          | 后续更新使用的版本           |
| ------------------ | --------------------------- | ---------------------------- |
| 操作系统           | macOS Monterey 12.3         | Windows11_22H2_22621.963     |
| 前端框架           | Vue 2.6.14                  |                              |
| 前端脚手架         | @vue/cli 4.5.15             | @vue/cli 5.0.8               |
| JavaScript运行环境 | Node.js 16.14.0             | Node.js 18.12.1              |
| 包管理工具         | npm 8.5.3                   | npm 9.2.0                    |
| 后端框架           | Spring Boot 2.6.3           | Spring Boot 2.7.6            |
| Java SE 开发工具包 | jkd-8u321(1.8)              | corretto-jdk1.8.0_342        |
| 数据库             | MariaDB 10.6.4              |                              |
| 开发软件           | IntelliJ IDEA 2021.3.3      | IntelliJ IDEA 2022.3.2       |
| 数据库管理工具     | Navicat Premium 16.0.8      | Navicat Premium 16.1.6       |
| 浏览器             | Google Chrome 100.0.4896.60 | Google Chrome 109.0.5414.120 |

## 项目功能

* 登录、退出、注册
* 消息查看、消息删除
* 报名比赛
* 消息发布
* 评审比赛
* 用户信息修改
* 搜索比赛
* 查看评审结果、比赛排名

视频演示：[点击跳转](https://github.com/izhangguapi/Graduation-Design/blob/720c10bf940b0add57c83faa70d5ca8bfa8694e4/%E6%96%87%E6%A1%A3/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6%E4%B8%8E%E6%8A%80%E6%9C%AF%EF%BC%88%E4%B8%93%E5%8D%87%E6%9C%AC%EF%BC%892002-204304064-%E5%BC%A0%E6%81%A3%E8%B1%AA/10.%E7%B3%BB%E7%BB%9F%E6%BC%94%E7%A4%BA%E8%A7%86%E9%A2%91-204304064-%E5%BC%A0%E6%81%A3%E8%B1%AA.mp4)

## 项目所用技术

* Json文件读写
* 平均分配算法（自行设计，目前能用，比较简单，后续更新）
* JWT
* MyBatis-plus和MyBatis-plus-join链表查询插件
* alibaba druid连接池
* Spring Security登录鉴权
* element-ui
* axios
* vuex
* vue-router

## 已知bug

1. 点击消息列表后，前端消息已读数量偶尔出现不变化的情况。是应为后台运行查询比修改快，导致获取的消息列表跟上一次相同，目前本人所学技术找不到良好的解决方案。


## 在线预览

http://contest.zhangguapi.com

测试账号：

|       用户组       | 账号（密码：123456） |   姓名   |
| :----------------: | :------------------: | :------: |
|       管理员       | admin（密码：admin） |  管理员  |
| 计算机设计（老师） |     13886961359      | 张瓜皮皮 |
| 计算机设计（老师） |     15023476163      | 球球慧子 |
| 计算机设计（老师） |     18453887612      | 小江云子 |
|  网页设计（老师）  |     13866039800      | 德隆东墙 |
|  网页设计（老师）  |     13027048577      | 五号六号 |
|  算法设计（老师）  |     15949101110      | 舒服阿寿 |
|  算法设计（老师）  |     15885569223      | 公叔幼安 |
|  算法设计（老师）  |     14721738231      | 左丘恬美 |
|  算法设计（老师）  |     17381012624      | 申屠婉丽 |
|  算法设计（老师）  |     18569173312      | 段干海瑶 |
|        学生        |     13704131948      |  赵同学  |
|        学生        |     13742968739      |  钱同学  |
|        学生        |     15319493760      |  孙同学  |
|        学生        |     18282876997      |  李同学  |
|        学生        |     14984342228      |  周同学  |
|        学生        |     14912959902      |  吴同学  |
|        学生        |     15089652824      |  郑同学  |
|        学生        |     15939648850      |  王同学  |
|        学生        |     17186946553      |  冯同学  |
|        学生        |     15524953409      |  陈同学  |
|        学生        |     13548431571      |  褚同学  |
|        学生        |     13653853195      |  卫同学  |
|        学生        |     17852847203      |  蒋同学  |
|        学生        |     18674806323      |  沈同学  |
|        学生        |     15960541480      |  韩同学  |
|        学生        |     15098963002      |  杨同学  |
|        学生        |     18576191111      |  朱同学  |
|        学生        |     17349901778      |  秦同学  |
|        学生        |     18528057614      |  尤同学  |
|        学生        |     14568709642      |  许同学  |
|        学生        |     13748489747      |  何同学  |
|        学生        |     13899106349      |  吕同学  |
|        学生        |     18884438382      |  施同学  |
|        学生        |     18011459018      |  张同学  |
