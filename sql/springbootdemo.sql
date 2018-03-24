/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1 (MariaDB 10.1)
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : springbootdemo

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-04-01 16:40:03
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS springbootdemo DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE springbootdemo;

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` BIGINT(20) NOT NULL COMMENT '租户ID',
  `name` varchar(255) DEFAULT NULL,
  `age` INT(11) NULL DEFAULT NULL,
  `status` smallint UNSIGNED DEFAULT 128,
  `intro` text DEFAULT NULL,
  `deleted` boolean DEFAULT FALSE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1, 1, 'name1', 20, 128, '', FALSE);
INSERT INTO `demo_user` VALUES (2, 2, 'name2', 20, 256, '', FALSE);
INSERT INTO `demo_user` VALUES (3, 2, 'name3', 20, 384, '', FALSE);
INSERT INTO `demo_user` VALUES (4, 1, 'name4', 20, 512, '', FALSE);
INSERT INTO `demo_user` VALUES (5, 1, 'name5', 21, 640, '', FALSE);
INSERT INTO `demo_user` VALUES (6, 1, 'name6', 21, 768, '', FALSE);
INSERT INTO `demo_user` VALUES (7, 1, 'name6', 21, 128, '', TRUE);
