/*
 大学生计算机设计大赛报名网站-数据库

 数据库类型：MariaDB
 数据库版本：10.6.5
 创建时间：2021-12-28
 修改时间：2022-03-29
 */

-- 创建数据库:  `contest_web`
-- ------------------------------
DROP DATABASE IF EXISTS `contest_web`;
CREATE DATABASE `contest_web` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `contest_web`;
-- 创建用户组表:  `groups`
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
	`group_id` INT NOT NULL AUTO_INCREMENT COMMENT '组id',
	`group_name` VARCHAR(10) NOT NULL UNIQUE KEY COMMENT '组名',
	`encoding` CHAR(5) UNIQUE KEY COMMENT '组编码',
	PRIMARY KEY (`group_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- 创建用户表:  `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`user_id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`name` VARCHAR(10) COMMENT '姓名',
	`sex` TINYINT(1) COMMENT '性别',
	`birthday` DATE COMMENT '出生日期',
	`school` VARCHAR(20) COMMENT '学校',
	`address` VARCHAR(50) COMMENT '地址',
	`phone` CHAR(11) NOT NULL UNIQUE KEY COMMENT '手机号',
	`email` CHAR(100) NOT NULL UNIQUE KEY COMMENT '邮箱',
	`group_id` INT COMMENT '用户组（用户组.组id）',
	`password` CHAR(32) NOT NULL COMMENT '密码',
	PRIMARY KEY (`user_id`),
	INDEX `users_1` (`school`),
	CONSTRAINT `users_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- 创建比赛表:  `contests`
-- ----------------------------
DROP TABLE IF EXISTS `contests`;
CREATE TABLE `contests` (
	`contest_id` INT NOT NULL AUTO_INCREMENT COMMENT '比赛id',
	`contest_title` varchar(50) NOT NULL UNIQUE KEY COMMENT '比赛标题',
	`contest_text` TEXT NOT NULL COMMENT '比赛内容',
	`url` varchar(255) NOT NULL COMMENT '比赛宣传图',
	`promulgator` INT NOT NULL COMMENT '发布人（组.组id）',
	`group_id` INT NOT NULL COMMENT '所属组（用户.用户id）',
	`reg_start_time` DATETIME NOT NULL COMMENT '报名开始时间',
	`reg_end_time` DATETIME NOT NULL COMMENT '报名结束时间',
	`start_time` DATETIME NOT NULL COMMENT '比赛开始时间',
	`end_time` DATETIME NOT NULL COMMENT '比赛截止时间',
	`status` TINYINT(1) DEFAULT(FALSE) COMMENT '比赛审核状态',
	`status_text` TEXT DEFAULT(NULL) COMMENT '比赛审核说明',
	PRIMARY KEY (`contest_id`),
	CONSTRAINT `contests_1` FOREIGN KEY (`promulgator`) REFERENCES `users` (`user_id`),
	CONSTRAINT `contests_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- 创建消息表:  `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
	`message_id` INT NOT NULL AUTO_INCREMENT COMMENT '消息id',
	`recipient` INT NOT NULL COMMENT '接收人（用户.用户id）',
	`title` varchar(20) NOT NULL COMMENT '消息标题',
	`text` TEXT NOT NULL COMMENT '消息内容',
	`sender` INT NOT NULL COMMENT '发布人（用户.用户id）',
	`time` DATETIME NOT NULL DEFAULT(now()) COMMENT '发布时间',
	`status` TINYINT(1) DEFAULT(FALSE) COMMENT '消息读取状态',
	PRIMARY KEY (`message_id`),
	INDEX `messages_1` (title),
	CONSTRAINT `messages_2` FOREIGN KEY (`recipient`) REFERENCES `users` (`user_id`),
	CONSTRAINT `messages_3` FOREIGN KEY (`sender`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- 创建报名评分表:  `scores `
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores` (
	`scores_id` INT NOT NULL AUTO_INCREMENT COMMENT '报名id',
	`contest_id` INT NOT NULL COMMENT '比赛id（比赛.比赛id）',
	`contestant` INT NOT NULL COMMENT '参赛者（用户.用户id）',
	`status` TINYINT(1) NOT NULL DEFAULT(FALSE) COMMENT '评审状态（0或1）',
	`judge` INT COMMENT '评审人（用户.用户id）',
	`text` TEXT COMMENT '评审内容',
	`result` TINYINT(3) COMMENT '成绩',
	PRIMARY KEY (`scores_id`),
	CONSTRAINT `enter_2` FOREIGN KEY (`contest_id`) REFERENCES `contests` (`contest_id`),
	CONSTRAINT `enter_3` FOREIGN KEY (`contestant`) REFERENCES `users` (`user_id`),
	CONSTRAINT `enter_4` FOREIGN KEY (`judge`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;



-- 组表:  `groups` 插入数据
INSERT INTO `groups`
VALUES
	( DEFAULT, '管理员', 'admin' ),
	( DEFAULT, '学生', 'stu' ),
	( DEFAULT, '计算机设计', 'YVN1U' ),
	( DEFAULT, '网页设计', 'S5GWA' ),
	( DEFAULT, '算法设计', 'OY5TT' );
-- 用户表:  `users` 插入数据
INSERT INTO `users`
VALUES
	( DEFAULT, '管理员', TRUE, NULL, NULL, NULL, 'admin', 'admin@zhangguapi.com', '1', '21232f297a57a5a743894a0e4a801fc3' ),
	( DEFAULT, '张瓜皮皮', TRUE, '1999-11-20', '西安交通大学', '天门', '13886961359', '13886961359@qq.com', '3', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '球球慧子', FALSE, '1998-06-15', '西南财经大学', '武汉', '15023476163', '15023476163@qq.com', '3', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '小江云子', FALSE, '1999-07-29', '华中科技大学', '黄石', '18453887612', '18453887612@qq.com', '3', 'e10adc3949ba59abbe56e057f20f883e' ),
	
	( DEFAULT, '德隆东墙', TRUE, '2000-04-05', '武汉设计工程学院', '️武设', '13866039800', '13866039800@qq.com', '4', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '五号六号', FALSE, '1999-12-12', '中国人民公安大学', '吉林', '13027048577', '13027048577@qq.com', '4', 'e10adc3949ba59abbe56e057f20f883e' ),
	
	( DEFAULT, '舒服阿寿', TRUE, '2000-12-24', '武汉首义学院', '南宁', '15949101110', '15949101110@qq.com', '5', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '公叔幼安', TRUE, '2000-12-24', '武汉首义学院', '南宁', '15885569223', '15885569223@qq.com', '5', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '左丘恬美', FALSE, '2000-12-24', '武汉首义学院', '南宁', '14721738231', '14721738231@qq.com', '5', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '申屠婉丽', FALSE, '2000-12-24', '武汉首义学院', '南宁', '17381012624', '17381012624@qq.com', '5', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '段干海瑶', FALSE, '2000-12-24', '武汉首义学院', '南宁', '18569173312', '18569173312@qq.com', '5', 'e10adc3949ba59abbe56e057f20f883e' ),
	
	( DEFAULT, '赵同学', FALSE, '1998-05-10', '武汉科技大学', '鄂州', '13704131948', '13704131948@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '钱同学', TRUE, '2002-01-16', '华中科技大学', '仙桃', '13742968739', '13742968739@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '孙同学', FALSE, '2002-12-30', '电子科技大学', '成都', '15319493760', '15319493760@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '李同学', TRUE, '2001-10-10', '北京大学', '青海', '18282876997', '18282876997@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '周同学', TRUE, '2000-02-15', '武汉城市学院', '辽阳', '14984342228', '14984342228@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '吴同学', TRUE, '1998-10-08', '华中师范大学', '西安', '14912959902', '14912959902@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '郑同学', FALSE, '2002-04-16', '复旦大学', '南通', '15089652824', '15089652824@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '王同学', FALSE, '2003-09-09', '哈尔滨工业大学', '惠州', '15939648850', '15939648850@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '冯同学', TRUE, '2000-02-15', '复旦大学', '辽阳', '17186946553', '17186946553@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '陈同学', TRUE, '1998-10-08', '复旦大学', '西安', '15524953409', '15524953409@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '褚同学', FALSE, '2002-04-16', '复旦大学', '南通', '13548431571', '13548431571@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '卫同学', FALSE, '2003-09-09', '复旦大学', '惠州', '13653853195', '13653853195@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '蒋同学', TRUE, '2000-02-15', '复旦大学', '辽阳', '17852847203', '17852847203@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '沈同学', TRUE, '1998-10-08', '复旦大学', '西安', '18674806323', '18674806323@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '韩同学', FALSE, '2002-04-16', '复旦大学', '南通', '15960541480', '15960541480@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '杨同学', FALSE, '2003-09-09', '复旦大学', '惠州', '15098963002', '15098963002@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '朱同学', TRUE, '2000-02-15', '复旦大学', '辽阳', '18576191111', '18576191111@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '秦同学', TRUE, '1998-10-08', '复旦大学', '西安', '17349901778', '17349901778@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '尤同学', FALSE, '2002-04-16', '复旦大学', '南通', '18528057614', '18528057614@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '许同学', FALSE, '2003-09-09', '复旦大学', '惠州', '14568709642', '14568709642@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '何同学', TRUE, '2000-02-15', '复旦大学', '辽阳', '13748489747', '13748489747@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '吕同学', TRUE, '1998-10-08', '复旦大学', '西安', '13899106349', '13899106349@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '施同学', FALSE, '2002-04-16', '复旦大学', '南通', '18884438382', '18884438382@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' ),
	( DEFAULT, '张同学', FALSE, '2003-09-09', '复旦大学', '惠州', '18011459018', '18011459018@qq.com', '2', 'e10adc3949ba59abbe56e057f20f883e' );
-- 比赛表:  `contests` 插入数据
INSERT INTO `contests`
VALUES
	( DEFAULT, '2021年度全国大学生算法设计编程挑战赛',
	'# 2021年度全国大学生算法设计编程挑战赛\n报名开始时间：2021-01-01 10:00:00\n报名结束时间：2021-02-01 23:59:59\n\n**请同学们尽快报名**\n\n![测试图片](https://pic.imgdb.cn/item/5fb1289a0d550d58f52f5b1b.png)',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d91903853.jpg',
	'2', '3', '2021-01-01 10:00:00', '2021-02-01 23:59:59', '2021-03-01 10:00:00', '2021-04-01 23:59:59', TRUE, DEFAULT ),
	( DEFAULT, '2021年度阿里巴巴人工智能对抗算法竞赛',
	'# 2021年度阿里巴巴人工智能对抗算法竞赛\n内容：\n1. 第一条\n2. 第二条\n3. 第三条',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg',
	'5', '4', '2021-08-01 10:00:00', '2022-10-01 23:59:59', '2021-11-01 10:00:00', '2022-11-11 23:59:59', TRUE, DEFAULT ),
	( DEFAULT, '2021年度中国国际艺术博览会设计视觉形象设计大赛',
	'# 2021年度中国国际艺术博览会设计视觉形象设计大赛\n内容：123',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d91903848.jpg',
	'7', '5', '2021-12-01 10:00:00', '2022-02-01 23:59:59', '2022-02-10 10:00:00', '2022-04-01 23:59:59', TRUE, DEFAULT ),
	( DEFAULT, '2022年度世界大学生超级计算机竞赛',
	'# 2022年度世界大学生超级计算机竞赛\n内容：456',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d91903844.gif',
	'6', '4', '2022-01-01 10:00:00', '2022-03-01 23:59:59', '2022-03-02 10:00:00', '2022-03-20 23:59:59', TRUE, DEFAULT ),
	( DEFAULT, 'ACM国际大学生程序设计竞赛',
	'# ACM国际大学生程序设计竞赛\n内容：\n> 段落引用',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg',
	'4', '3', '2022-03-01 10:00:00', '2022-03-20 23:59:59', '2022-04-04 10:00:00', '2022-05-01 23:59:59', TRUE, DEFAULT ),
	( DEFAULT, '2022年度中国高校计算机大赛-网络技术挑战赛',
	'# 2022年度中国高校计算机大赛-网络技术挑战赛\n内容：789',
	'https://pic.imgdb.cn/item/61e95d082ab3f51d918ed3d2.png',
	'3', '3', '2022-02-01 10:00:00', '2022-05-01 23:59:59', '2022-06-01 10:00:00', '2022-07-01 23:59:59', TRUE, DEFAULT ),
	( DEFAULT, '测试比赛1',
	'# 测试比赛1\n内容：\n> 段落引用',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg',
	'2', '3', '2022-03-01 10:00:00', '2022-03-20 23:59:59', '2022-04-04 10:00:00', '2022-05-01 23:59:59', FALSE, DEFAULT ),
	( DEFAULT, '测试比赛2',
	'# 测试比赛2\n内容：\n> 段落引用',
	'https://pic.imgdb.cn/item/61e95e482ab3f51d9190384d.jpg',
	'2', '3', '2022-03-01 10:00:00', '2022-03-20 23:59:59', '2022-04-04 10:00:00', '2022-05-01 23:59:59', FALSE, '这个比赛重复，请删除' );
-- 消息表:  `messages` 插入数据
INSERT INTO `messages`
VALUES
	( DEFAULT, '1', '测试公告01', '这是一条测试公告01', '1', '2021-10-11 10:10:10', NULL ),
	( DEFAULT, '1', '测试公告01', '这是一条测试公告01', '1', '2021-10-11 10:10:10', NULL ),
	( DEFAULT, '1', '测试公告02', '这是一条测试公告02', '1', '2021-10-12 10:10:11', NULL ),
	( DEFAULT, '1', '测试公告03', '这是一条测试公告03', '1', '2021-10-13 10:10:12', NULL ),
	( DEFAULT, '1', '测试公告04', '这是一条测试公告04', '1', '2021-10-14 10:10:13', NULL ),
	( DEFAULT, '1', '测试公告05', '这是一条测试公告05', '1', '2021-10-15 10:10:14', NULL ),
	( DEFAULT, '1', '测试公告06', '这是一条测试公告06', '1', '2021-10-15 10:10:15', NULL ),
	( DEFAULT, '1', '测试公告07', '这是一条测试公告07', '1', '2021-10-15 10:10:16', NULL ),
	( DEFAULT, '1', '测试公告08', '这是一条测试公告08', '1', '2021-10-15 10:10:17', NULL ),
	( DEFAULT, '1', '测试公告09', '这是一条测试公告09', '1', '2021-10-15 10:10:18', NULL ),
	( DEFAULT, '1', '测试公告10', '这是一条测试公告10', '1', '2021-10-15 10:10:19', NULL ),
	( DEFAULT, '1', '测试公告11', '这是一条测试公告11', '1', '2021-10-15 10:11:10', NULL ),
	( DEFAULT, '1', '测试公告12', '这是一条测试公告12', '1', '2021-10-15 10:12:10', NULL ),
	( DEFAULT, '1', '测试公告13', '这是一条测试公告13', '1', '2021-10-15 10:13:10', NULL ),
	( DEFAULT, '1', '测试公告14', '这是一条测试公告14', '1', '2021-10-15 10:14:10', NULL ),
	( DEFAULT, '1', '测试公告15', '这是一条测试公告15', '1', '2021-10-15 10:15:10', NULL ),
	( DEFAULT, '1', '测试公告16', '这是一条测试公告16', '1', '2021-10-15 10:16:10', NULL ),
	( DEFAULT, '1', '测试公告17', '这是一条测试公告17', '1', '2021-10-15 10:17:10', NULL ),
	( DEFAULT, '1', '测试公告18', '这是一条测试公告18', '1', '2021-10-15 10:18:10', NULL ),
	( DEFAULT, '1', '测试公告19', '这是一条测试公告19', '1', '2021-10-15 10:19:10', NULL ),
	( DEFAULT, '1', '测试公告20', '这是一条测试公告20', '1', '2021-10-15 10:20:10', NULL ),
	( DEFAULT, '1', '测试公告21', '这是一条测试公告21', '1', '2021-10-15 10:21:10', NULL ),
	( DEFAULT, '1', '测试公告22', '这是一条测试公告22', '1', '2021-10-15 10:22:10', NULL ),
	( DEFAULT, '1', '测试公告23', '这是一条测试公告23', '1', '2021-10-15 10:23:10', NULL ),
	( DEFAULT, '1', '测试公告24', '这是一条测试公告24', '1', '2021-10-15 10:24:10', NULL ),
	( DEFAULT, '1', '测试公告25', '这是一条测试公告25', '1', '2021-10-15 10:25:10', NULL ),
	( DEFAULT, '1', '测试公告26', '这是一条测试公告26', '1', '2021-10-15 10:26:10', NULL ),
	( DEFAULT, '1', '测试公告27', '这是一条测试公告27', '1', '2021-10-15 10:27:10', NULL ),
	( DEFAULT, '1', '测试公告28', '这是一条测试公告28', '1', '2021-10-15 10:28:10', NULL ),
	( DEFAULT, '1', '测试公告29', '这是一条测试公告29', '1', '2021-10-15 10:29:10', NULL ),
	( DEFAULT, '1', '测试公告30', '这是一条测试公告30', '1', '2021-10-15 10:30:10', NULL ),
	( DEFAULT, '1', '网站正式运营', '本网站正式开始运营啦！', '1', '2021-12-10 10:10:10', NULL ),
	( DEFAULT, '1', '发布比赛规范', '禁止发布谩骂、包含人身攻击的比赛！', '1', '2022-01-10 10:10:10', NULL ),
	( DEFAULT, '2', '管理员发送的测试消息1', '这是一条管理员发送的测试消息1', '1', '2021-10-10 10:10:01', '0' ),
	( DEFAULT, '2', '管理员发送的测试消息2', '这是一条管理员发送的测试消息2', '3', '2021-10-10 10:10:02', '0' ),
	( DEFAULT, '2', '管理员发送的测试消息3', '这是一条管理员发送的测试消息3', '4', '2021-10-10 10:10:03', '0' ),
	( DEFAULT, '2', '管理员发送的测试消息4', '这是一条管理员发送的测试消息4', '5', '2021-10-10 10:10:04', '0' ),
	( DEFAULT, '2', '管理员发送的测试消息5', '这是一条管理员发送的测试消息5', '6', '2021-10-10 10:10:05', '0' ),
	( DEFAULT, '2', '管理员发送的测试消息6', '这是一条管理员发送的测试消息6', '7', '2021-10-10 10:10:06', '0' ),
	( DEFAULT, '3', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:12', '0' ),
	( DEFAULT, '4', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:13', '0' ),
	( DEFAULT, '5', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:14', '0' ),
	( DEFAULT, '6', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:15', '0' ),
	( DEFAULT, '7', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:16', '0' ),
	( DEFAULT, '8', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:17', '0' ),
	( DEFAULT, '9', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:18', '0' ),
	( DEFAULT, '10', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:19', '0' ),
	( DEFAULT, '11', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:20', '0' ),
	( DEFAULT, '12', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:21', '0' ),
	( DEFAULT, '13', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:22', '0' ),
	( DEFAULT, '14', '管理员发送的测试消息', '这是一条管理员发送的测试消息', '1', '2021-10-10 10:10:23', '0' );
-- 评分表:  `scores ` 插入数据
INSERT INTO `scores`
VALUES
	( DEFAULT, '1', '12', '1', '3', '优秀', '85' ),
	( DEFAULT, '1', '13', '1', '2', '出彩', '91' ),
	( DEFAULT, '1', '14', '1', '3', '良好', '70' ),
	( DEFAULT, '1', '15', '1', '2', '优秀', '84' ),
	( DEFAULT, '1', '16', '1', '3', '不行', '53' ),
	( DEFAULT, '1', '17', '1', '2', '太差', '71' ),
	( DEFAULT, '1', '18', '1', '2', '优秀', '100' ),
	( DEFAULT, '1', '19', '1', '2', '优秀', '98' ),
	
	( DEFAULT, '2', '15', '1', '5', '没有评语', '48' ),
	( DEFAULT, '2', '16', '1', '6', '评价1', '16' ),
	( DEFAULT, '2', '18', '1', '4', '我得龙东墙觉得不错', '80' ),
	( DEFAULT, '2', '17', '1', '5', '该作品有待改进', '71' ),
	( DEFAULT, '2', '12', '1', '6', '我觉得冠军非你莫属', '98' ),
	
	
	( DEFAULT, '3', '12', '1', '4', '评价1', '52' ),
	( DEFAULT, '3', '13', '1', '5', '评价2', '78' ),
	( DEFAULT, '3', '14', '1', '6', '评价3', '89' ),
	( DEFAULT, '3', '15', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '16', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '17', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '18', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '19', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '20', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '21', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '22', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '23', '0', NULL, NULL, NULL ),
	( DEFAULT, '3', '24', '0', NULL, NULL, NULL ),
	
	( DEFAULT, '4', '12', '0', NULL, NULL, NULL ),
	( DEFAULT, '4', '13', '0', NULL, NULL, NULL ),
	( DEFAULT, '4', '14', '0', NULL, NULL, NULL ),
	( DEFAULT, '4', '15', '0', NULL, NULL, NULL ),
	
	( DEFAULT, '5', '12', '1', '4', '我得龙东墙觉得不错', '80' ),
	( DEFAULT, '5', '13', '0', NULL, NULL, NULL ),
	( DEFAULT, '5', '14', '1', '4', '我得龙东墙觉得不错', '80' ),
	( DEFAULT, '6', '15', '0', NULL, NULL, NULL ),
	( DEFAULT, '6', '16', '0', NULL, NULL, NULL ),
	( DEFAULT, '6', '17', '0', NULL, NULL, NULL ),
	( DEFAULT, '6', '18', '0', NULL, NULL, NULL ),
	( DEFAULT, '6', '19', '0', NULL, NULL, NULL );