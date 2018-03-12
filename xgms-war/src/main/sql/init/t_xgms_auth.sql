/*
Navicat MySQL Data Transfer

Source Server         : ali_cloud_mysql
Source Server Version : 50639
Source Host           : 47.100.38.50:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-03-12 19:54:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_xgms_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_xgms_auth`;
CREATE TABLE `t_xgms_auth` (
  `c_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '主键 UUID',
  `c_user_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `c_user_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `c_school_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '院校名称',
  `c_education` varchar(25) CHARACTER SET utf8 DEFAULT NULL COMMENT '学历',
  `c_student_no` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '学号',
  `c_entrance_year` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '入学年份',
  `c_operator_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作处理人id',
  `l_operate_time` bigint(20) unsigned DEFAULT NULL COMMENT '管理员处理时间',
  `l_create_time` bigint(20) unsigned DEFAULT NULL COMMENT '创建时间',
  `c_delete_flag` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除标识 0-未删除 1-已删除',
  `c_auth_status` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '认证状态 待处理-0 认证失败-2 认证成功-1',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_xgms_auth
-- ----------------------------
INSERT INTO `t_xgms_auth` VALUES ('01010', '01010', '小龙', '河南财经政法大学', '本科', '201434003066', '2014', '1231231564646', '1520839667850', '1520839667525', '0', '1');
INSERT INTO `t_xgms_auth` VALUES ('111', '123456', '王大龙', '河南财经政法大学', '本科', '201434003082', '2014', '12345646113213', '1520839667800', '1520839667525', '0', '2');
INSERT INTO `t_xgms_auth` VALUES ('123', '123456', '王诗琪', '河南财经政法大学', '本科', '201434003081', '2014', '1231231231332', '1520839667600', '1520839667524', '0', '1');
INSERT INTO `t_xgms_auth` VALUES ('123456', '123456', '王世强', '河南财经政法大学', '本科', '201434003085', '2014', '1231231231231323', '1520839667523', '1520839667523', '0', '0');
