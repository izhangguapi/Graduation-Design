/*
 大学生计算机设计大赛报名网站-数据库

 数据库类型：MariaDB
 数据库版本：10.6.5
 创建时间：2021-12-28
 修改时间：2022-03-29
 */ -- 创建数据库:  `contest_web`
-- ------------------------------
DROP DATABASE
IF
	EXISTS `contest_web`;
CREATE DATABASE `contest_web` DEFAULT CHARACTER 
SET utf8 COLLATE utf8_general_ci;
USE `contest_web`;
/*
创建群组表:  `groups`
*/
DROP TABLE
IF
	EXISTS `groups`;
CREATE TABLE `groups` (
	`group_id` INT NOT NULL AUTO_INCREMENT COMMENT '群组id',
	`group_name` VARCHAR ( 10 ) NOT NULL UNIQUE KEY COMMENT '群组名',
	`encoding` CHAR ( 5 ) UNIQUE KEY COMMENT '群组编码',
	`create_time` DATETIME NOT NULL DEFAULT (
	now()) COMMENT '创建时间',
	`description` VARCHAR ( 200 ) COMMENT '描述',
	PRIMARY KEY ( `group_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '群组表';
/*
创建角色表:  `role`
*/
DROP TABLE
IF
	EXISTS `role`;
CREATE TABLE `role` (
	`role_id` INT NOT NULL AUTO_INCREMENT COMMENT '角色id',
	`role_name` VARCHAR ( 64 ) NOT NULL COMMENT '角色名称',
	`create_time` DATETIME NOT NULL DEFAULT (now()) COMMENT '创建时间',
	`description` VARCHAR ( 200 ) COMMENT '描述',
	PRIMARY KEY ( `role_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '角色表';
/*
创建学校表：`school`
*/
DROP TABLE
IF
	EXISTS `school`;
CREATE TABLE `school` (
	`id` INT ( 11 ) NOT NULL COMMENT 'id',
	`school_id` VARCHAR ( 20 ) COMMENT '学校编号',
	`school_name` VARCHAR ( 255 ) COMMENT '学校名称',
	`province_id` VARCHAR ( 20 ) COMMENT '省份编号',
	`province_name` VARCHAR ( 255 ) COMMENT '省份名称',
	`city_id` VARCHAR ( 20 ) COMMENT '城市编号',
	`city_name` VARCHAR ( 255 ) COMMENT '城市名称',
	`level` VARCHAR ( 255 ) COMMENT '学校等级',
	`department` VARCHAR ( 255 ) COMMENT '学校id',
	`other` VARCHAR ( 255 ) COMMENT '其他',
	PRIMARY KEY ( `id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '学校表';
/*
创建用户表:  `users`
*/
DROP TABLE
IF
	EXISTS `users`;
CREATE TABLE `users` (
	`user_id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`name` VARCHAR ( 10 ) COMMENT '姓名',
	`sex` TINYINT ( 1 ) COMMENT '性别',
	`birthday` DATE COMMENT '出生日期',
	`school_id` VARCHAR ( 20 ) COMMENT '学校id',
	`address` VARCHAR ( 50 ) COMMENT '地址',
	`phone` CHAR ( 11 ) NOT NULL UNIQUE KEY COMMENT '手机号',
	`email` CHAR ( 100 ) NOT NULL UNIQUE KEY COMMENT '邮箱',
	`password` CHAR ( 60 ) NOT NULL COMMENT '密码',
	PRIMARY KEY ( `user_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '用户表';
/*
创建用户角色表:  `User_Role`
*/
DROP TABLE
IF
	EXISTS `User_Role`;
CREATE TABLE `User_Role` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
	`user_id` INT NOT NULL COMMENT '用户id',
	`role_id` INT NOT NULL COMMENT '角色id',
	PRIMARY KEY ( `id` ),
	CONSTRAINT `User_Role_1` FOREIGN KEY ( `user_id` ) REFERENCES `users` ( `user_id` ),
	CONSTRAINT `User_Role_2` FOREIGN KEY ( `role_id` ) REFERENCES `role` ( `role_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '用户角色表';
/*
创建用户群组表:  `User_Group_Relation`
*/
DROP TABLE
IF
	EXISTS `User_Group`;
CREATE TABLE `User_Group` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
	`user_id` INT NOT NULL COMMENT '用户id',
	`group_id` INT NOT NULL COMMENT '群组id',
	PRIMARY KEY ( `id` ),
	CONSTRAINT `User_Group_1` FOREIGN KEY ( `user_id` ) REFERENCES `users` ( `user_id` ),
	CONSTRAINT `User_Group_2` FOREIGN KEY ( `group_id` ) REFERENCES `groups` ( `group_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '用户群组表';
/*
创建比赛表:  `contests`
*/
DROP TABLE
IF
	EXISTS `contests`;
CREATE TABLE `contests` (
	`contest_id` INT NOT NULL AUTO_INCREMENT COMMENT '比赛id',
	`contest_title` VARCHAR ( 50 ) NOT NULL UNIQUE KEY COMMENT '比赛标题',
	`contest_text` TEXT NOT NULL COMMENT '比赛内容',
	`url` VARCHAR ( 255 ) NOT NULL COMMENT '比赛宣传图',
	`promulgator` INT NOT NULL COMMENT '发布人（组.组id）',
	`group_id` INT NOT NULL COMMENT '所属组（用户.用户id）',
	`reg_start_time` DATETIME NOT NULL COMMENT '报名开始时间',
	`reg_end_time` DATETIME NOT NULL COMMENT '报名结束时间',
	`start_time` DATETIME NOT NULL COMMENT '比赛开始时间',
	`end_time` DATETIME NOT NULL COMMENT '比赛截止时间',
	`status` TINYINT ( 1 ) DEFAULT ( FALSE ) COMMENT '比赛审核状态',
	`status_text` TEXT DEFAULT ( NULL ) COMMENT '比赛审核说明',
	PRIMARY KEY ( `contest_id` ),
	CONSTRAINT `contests_1` FOREIGN KEY ( `promulgator` ) REFERENCES `users` ( `user_id` ),
	CONSTRAINT `contests_2` FOREIGN KEY ( `group_id` ) REFERENCES `groups` ( `group_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '比赛表';
/*
创建消息表:  `messages`
*/
DROP TABLE
IF
	EXISTS `messages`;
CREATE TABLE `messages` (
	`message_id` INT NOT NULL AUTO_INCREMENT COMMENT '消息id',
	`recipient` INT NOT NULL COMMENT '接收人（用户.用户id）',
	`title` VARCHAR ( 20 ) NOT NULL COMMENT '消息标题',
	`text` TEXT NOT NULL COMMENT '消息内容',
	`sender` INT NOT NULL COMMENT '发布人（用户.用户id）',
	`time` DATETIME NOT NULL DEFAULT (
	now()) COMMENT '发布时间',
	`status` TINYINT ( 1 ) DEFAULT ( FALSE ) COMMENT '消息读取状态',
	PRIMARY KEY ( `message_id` ),
	INDEX `messages_1` ( title ),
	CONSTRAINT `messages_2` FOREIGN KEY ( `recipient` ) REFERENCES `users` ( `user_id` ),
	CONSTRAINT `messages_3` FOREIGN KEY ( `sender` ) REFERENCES `users` ( `user_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '消息表';
/*
创建公告表:  `announcements`
*/
DROP TABLE
IF
	EXISTS `announcements`;
CREATE TABLE `announcements` (
	`announcement_id` INT NOT NULL AUTO_INCREMENT COMMENT '公告id',
	`title` VARCHAR ( 20 ) NOT NULL COMMENT '公告标题',
	`text` TEXT NOT NULL COMMENT '公告内容',
	`time` DATETIME NOT NULL DEFAULT (
	now()) COMMENT '发布时间',
	PRIMARY KEY ( `announcement_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '公告表';
/*
创建报名评分表:  `scores `
*/
DROP TABLE
IF
	EXISTS `scores`;
CREATE TABLE `scores` (
	`scores_id` INT NOT NULL AUTO_INCREMENT COMMENT '报名id',
	`contest_id` INT NOT NULL COMMENT '比赛id（比赛.比赛id）',
	`contestant` INT NOT NULL COMMENT '参赛者（用户.用户id）',
	`status` TINYINT ( 1 ) NOT NULL DEFAULT ( FALSE ) COMMENT '评审状态（0或1）',
	`judge` INT COMMENT '评审人（用户.用户id）',
	`text` TEXT COMMENT '评审内容',
	`result` TINYINT ( 3 ) COMMENT '成绩',
	PRIMARY KEY ( `scores_id` ),
	CONSTRAINT `scores_1` FOREIGN KEY ( `contest_id` ) REFERENCES `contests` ( `contest_id` ),
	CONSTRAINT `scores_2` FOREIGN KEY ( `contestant` ) REFERENCES `users` ( `user_id` ),
	CONSTRAINT `scores_3` FOREIGN KEY ( `judge` ) REFERENCES `users` ( `user_id` ) 
) ENGINE = INNODB AUTO_INCREMENT = 1 COMMENT '报名评分表';