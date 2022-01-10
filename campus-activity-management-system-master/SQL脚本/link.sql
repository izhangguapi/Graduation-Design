/*
 Navicat Premium Data Transfer

 Source Server         : stu
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : backend

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 08/09/2021 10:44:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `uid` int NOT NULL,
  `aid` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT COMMENT '报名表编号',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `aname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ulink`(`uid`, `uname`) USING BTREE,
  INDEX `alink`(`aid`, `aname`) USING BTREE,
  CONSTRAINT `ulink` FOREIGN KEY (`uid`, `uname`) REFERENCES `users` (`id`, `username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `alink` FOREIGN KEY (`aid`, `aname`) REFERENCES `activities` (`a_id`, `a_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
