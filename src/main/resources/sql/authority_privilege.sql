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

 Date: 30/04/2019 22:15:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority_privilege
-- ----------------------------
DROP TABLE IF EXISTS `authority_privilege`;
CREATE TABLE `authority_privilege`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `parent_privilege_id` int(11) NULL DEFAULT NULL,
  `enable` tinyint(1) NULL DEFAULT NULL,
  `privilege_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `privilege_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
