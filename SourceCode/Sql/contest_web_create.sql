/*
 大学生计算机设计大赛报名网站-数据库
 
 数据库类型：MariaDB
 数据库版本：10.6.5
 创建时间：2021-12-28
 修改时间：2022-03-29
 */
-- ------------------------------
-- 创建数据库:  `contest_web`
-- ------------------------------
DROP DATABASE IF EXISTS `contest_web`;
CREATE DATABASE `contest_web` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `contest_web`;
-- ----------------------------
-- 创建用户组表:  `groups`
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
	`group_id` INT NOT NULL AUTO_INCREMENT COMMENT '组id',
	`group_name` VARCHAR(10) NOT NULL UNIQUE KEY COMMENT '组名',
	`encoding` CHAR(5) UNIQUE KEY COMMENT '组编码',
	PRIMARY KEY (`group_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- ----------------------------
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
-- ----------------------------
-- 创建比赛表:  `contests`
-- ----------------------------
DROP TABLE IF EXISTS `contests`;
CREATE TABLE `contests` (
	`contest_id` INT NOT NULL AUTO_INCREMENT COMMENT '比赛id',
	`contest_title` varchar(50) NOT NULL UNIQUE KEY COMMENT '比赛标题',
	`contest_text` longtext NOT NULL COMMENT '比赛内容',
	`url` varchar(255) NOT NULL COMMENT '比赛宣传图',
	`promulgator` INT NOT NULL COMMENT '发布人（组.组id）',
	`group_id` INT NOT NULL COMMENT '所属组（用户.用户id）',
	`reg_start_time` DATETIME NOT NULL COMMENT '报名开始时间',
	`reg_end_time` DATETIME NOT NULL COMMENT '报名结束时间',
	`start_time` DATETIME NOT NULL COMMENT '比赛开始时间',
	`end_time` DATETIME NOT NULL COMMENT '比赛截止时间',
	`status` TINYINT(1) DEFAULT(FALSE) COMMENT '比赛审核状态',
	`status_text` VARCHAR(255) DEFAULT(NULL) COMMENT '比赛审核结果',
	PRIMARY KEY (`contest_id`),
	CONSTRAINT `contests_1` FOREIGN KEY (`promulgator`) REFERENCES `users` (`user_id`),
	CONSTRAINT `contests_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- ----------------------------
-- 创建消息表:  `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
	`message_id` INT NOT NULL AUTO_INCREMENT COMMENT '消息id',
	`recipient` INT NOT NULL COMMENT '接收人（用户.用户id）',
	`title` varchar(20) NOT NULL COMMENT '消息标题',
	`text` text NOT NULL COMMENT '消息内容',
	`sender` INT NOT NULL COMMENT '发布人（用户.用户id）',
	`time` DATETIME NOT NULL DEFAULT(now()) COMMENT '发布时间',
	`status` TINYINT(1) DEFAULT(FALSE) COMMENT '消息读取状态',
	PRIMARY KEY (`message_id`),
	INDEX `messages_1` (title),
	CONSTRAINT `messages_2` FOREIGN KEY (`recipient`) REFERENCES `users` (`user_id`),
	CONSTRAINT `messages_3` FOREIGN KEY (`sender`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;
-- ----------------------------
-- 创建报名评分表:  `scores `
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores` (
	`scores_id` INT NOT NULL AUTO_INCREMENT COMMENT '报名id',
	`contest_id` INT NOT NULL COMMENT '比赛id（比赛.比赛id）',
	`contestant` INT NOT NULL COMMENT '参赛者（用户.用户id）',
	`status` TINYINT(1) NOT NULL DEFAULT(0) COMMENT '评审状态（0或1）',
	`judge` INT COMMENT '评委（用户.用户id）',
	`text` text COMMENT '评审内容',
	`result` TINYINT(3) COMMENT '成绩',
	PRIMARY KEY (`scores_id`),
	INDEX `enter_1` (`text`),
	CONSTRAINT `enter_2` FOREIGN KEY (`contest_id`) REFERENCES `contests` (`contest_id`),
	CONSTRAINT `enter_3` FOREIGN KEY (`contestant`) REFERENCES `users` (`user_id`),
	CONSTRAINT `enter_4` FOREIGN KEY (`judge`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1;