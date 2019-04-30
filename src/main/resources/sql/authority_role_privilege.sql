/*
 Navicat Premium Data Transfer

 Source Server         : msi
 Source Server Type    : MySQL
 Source Server Version : 50132
 Source Host           : localhost:3306
 Source Schema         : msi

 Target Server Type    : MySQL
 Target Server Version : 50132
 File Encoding         : 65001

 Date: 30/04/2019 22:15:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `authority_role_privilege`;
CREATE TABLE `authority_role_privilege`  (
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`, `privilege_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
