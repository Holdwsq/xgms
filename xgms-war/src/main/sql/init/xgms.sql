/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3308
Source Database       : xgms

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-03-11 21:51:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_xgms_user
-- ----------------------------
DROP TABLE IF EXISTS `t_xgms_user`;
CREATE TABLE `t_xgms_user` (
  `c_id` varchar(32) collate utf8_unicode_ci NOT NULL COMMENT '用户id',
  `c_name` varchar(64) collate utf8_unicode_ci default '' COMMENT '用户姓名',
  `c_hobby` varchar(255) collate utf8_unicode_ci default NULL COMMENT '用户兴趣爱好 ',
  `c_preview_id` varchar(32) collate utf8_unicode_ci default NULL COMMENT '用户头像缩略图id',
  `c_file_id` varchar(32) collate utf8_unicode_ci default NULL COMMENT '用户头像原图id',
  `c_sex` varchar(2) collate utf8_unicode_ci default NULL COMMENT '用户性别 1-男 2-女',
  `d_birthday` date default NULL COMMENT '用户生日',
  `c_brief` varchar(255) collate utf8_unicode_ci default NULL COMMENT '用户简介',
  `c_school` varchar(255) collate utf8_unicode_ci default NULL COMMENT '用户所在学校名称',
  `c_delete_flag` varchar(1) collate utf8_unicode_ci default NULL COMMENT '删除标识 0-未删除 1-已删除',
  `n_create_time` bigint(20) default NULL COMMENT '创建时间 long类型',
  `n_update_time` bigint(20) default NULL COMMENT '更新时间 long类型',
  `n_age` int(10) unsigned zerofill default NULL COMMENT '年龄',
  `c_phone` varchar(11) character set utf8 default NULL COMMENT '手机号',
  `c_auth` varchar(1) collate utf8_unicode_ci default NULL COMMENT '是否认证',
  `c_account_name` varchar(64) collate utf8_unicode_ci NOT NULL COMMENT '应用昵称',
  `c_pwd` varchar(64) collate utf8_unicode_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY  (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_xgms_user
-- ----------------------------
INSERT INTO `t_xgms_user` VALUES ('210b346cf29a4577aa51fb077b04674b', '', null, null, null, null, null, null, null, '0', '1520149639661', '1520149639661', null, null, null, 'Tom', '210b346cf29a4577aa51fb077b04674b');
