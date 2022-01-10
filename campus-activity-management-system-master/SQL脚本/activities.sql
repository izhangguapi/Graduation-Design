/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : backend

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 06/09/2021 00:53:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities`  (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `a_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `a_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发起时间',
  `u_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起人用户名',
  `a_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动地点',
  `a_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动内容',
  `a_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动状态',
  `a_stime` datetime NOT NULL COMMENT '开始时间',
  `a_etime` datetime NOT NULL COMMENT '结束时间',
  `u_id` int(11) NOT NULL COMMENT '发起人账号',
  PRIMARY KEY (`a_id`) USING BTREE,
  INDEX `1`(`u_id`, `u_username`) USING BTREE,
  CONSTRAINT `1` FOREIGN KEY (`u_id`, `u_username`) REFERENCES `users` (`id`, `username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
