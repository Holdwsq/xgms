/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : template

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-03-27 17:28:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_appclient
-- ----------------------------
DROP TABLE IF EXISTS `t_appclient`;
CREATE TABLE `t_appclient` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_ver_code` bigint(20) DEFAULT NULL,
  `c_ver_name` varchar(45) DEFAULT NULL,
  `n_date` bigint(20) DEFAULT NULL,
  `c_url` varchar(100) DEFAULT NULL,
  `c_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_appclient
-- ----------------------------

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(45) DEFAULT NULL COMMENT '名称',
  `c_code` varchar(45) DEFAULT NULL COMMENT '编码',
  `n_pid` int(11) DEFAULT NULL COMMENT '父部门ID',
  `n_sort` int(11) DEFAULT NULL COMMENT '排序',
  `n_utype` int(11) DEFAULT NULL COMMENT '更新类型：1表示非删除，2表示删除',
  `n_utime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '总经理办公室', '00', '0', '1', '1', '1471490412557');
INSERT INTO `t_dept` VALUES ('2', '后勤保障部', '0000', '1', '1', '1', '1471490496978');
INSERT INTO `t_dept` VALUES ('3', '、、。，，', null, '0', '-1', '2', '1501221947313');
INSERT INTO `t_dept` VALUES ('4', 'kfkd', '0001', '1', '1', '1', '1501222006381');
INSERT INTO `t_dept` VALUES ('5', 'fhfh', '0002', '1', '-1', '1', '1501222023440');
INSERT INTO `t_dept` VALUES ('6', 'fgh', '01', '0', '1', '1', '1501222155926');
INSERT INTO `t_dept` VALUES ('7', 'n', '0100', '6', '1', '1', '1501222167428');

-- ----------------------------
-- Table structure for t_emploee
-- ----------------------------
DROP TABLE IF EXISTS `t_emploee`;
CREATE TABLE `t_emploee` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `c_py` varchar(45) DEFAULT NULL COMMENT '拼音简拼',
  `c_emplid` varchar(45) DEFAULT NULL COMMENT '工号/警号',
  `c_tel` varchar(45) DEFAULT NULL COMMENT '固话',
  `c_mobile` varchar(45) DEFAULT NULL COMMENT '手机多个以#分割',
  `c_email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `n_deptid` int(11) DEFAULT NULL COMMENT '部门ID',
  `c_job` varchar(45) DEFAULT NULL COMMENT '职位',
  `n_sort` int(11) DEFAULT NULL COMMENT '排序',
  `n_utype` int(11) DEFAULT NULL COMMENT '更新类型：1表示非删除，2表示删除',
  `n_utime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `c_avater` varchar(80) DEFAULT NULL COMMENT '头像',
  `n_entry_time` bigint(20) DEFAULT NULL COMMENT '入职时间',
  `n_quit_time` bigint(20) DEFAULT NULL COMMENT '离职时间',
  `c_pwd` varchar(45) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of t_emploee
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_func
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_func`;
CREATE TABLE `t_sys_func` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(45) DEFAULT NULL,
  `c_icon` varchar(45) DEFAULT NULL,
  `n_pid` int(11) DEFAULT NULL,
  `n_order` int(11) DEFAULT NULL,
  `c_url` varchar(45) DEFAULT NULL,
  `c_permission` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_func
-- ----------------------------
INSERT INTO `t_sys_func` VALUES ('1', '系统管理', null, '0', '1', null, null);
INSERT INTO `t_sys_func` VALUES ('2', '角色管理', 'glyphicon-sunglasses', '1', '1', 'sysrole', 'sysrole');
INSERT INTO `t_sys_func` VALUES ('3', '用户管理', 'glyphicon-user', '1', '2', 'sysuser', 'sysuser');
INSERT INTO `t_sys_func` VALUES ('4', '组织机构管理', null, '0', '2', null, null);
INSERT INTO `t_sys_func` VALUES ('5', '部门管理', 'glyphicon-home', '4', '1', 'orgdept', 'orgdept');
INSERT INTO `t_sys_func` VALUES ('6', '警员管理', 'glyphicon-user', '4', '2', 'orgemp', 'orgemp');
INSERT INTO `t_sys_func` VALUES ('7', 'APP管理', null, '0', '3', 'appup', 'appup');
INSERT INTO `t_sys_func` VALUES ('8', '日志管理', 'glyphicon-list-alt', '1', '3', 'syslog', 'syslog');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '超级管理员');
INSERT INTO `t_sys_role` VALUES ('2', 'QQQQ');
INSERT INTO `t_sys_role` VALUES ('3', '最普通');
INSERT INTO `t_sys_role` VALUES ('4', '经理');

-- ----------------------------
-- Table structure for t_sys_role_func
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_func`;
CREATE TABLE `t_sys_role_func` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_role_id` int(11) DEFAULT NULL,
  `n_func_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role_func
-- ----------------------------
INSERT INTO `t_sys_role_func` VALUES ('1', '1', '1');
INSERT INTO `t_sys_role_func` VALUES ('2', '1', '2');
INSERT INTO `t_sys_role_func` VALUES ('3', '1', '3');
INSERT INTO `t_sys_role_func` VALUES ('4', '1', '4');
INSERT INTO `t_sys_role_func` VALUES ('5', '1', '5');
INSERT INTO `t_sys_role_func` VALUES ('6', '1', '6');
INSERT INTO `t_sys_role_func` VALUES ('7', '1', '7');
INSERT INTO `t_sys_role_func` VALUES ('8', '1', '8');
INSERT INTO `t_sys_role_func` VALUES ('9', '2', '1');
INSERT INTO `t_sys_role_func` VALUES ('10', '2', '2');
INSERT INTO `t_sys_role_func` VALUES ('11', '2', '3');
INSERT INTO `t_sys_role_func` VALUES ('12', '2', '8');
INSERT INTO `t_sys_role_func` VALUES ('13', '3', '7');
INSERT INTO `t_sys_role_func` VALUES ('16', '4', '3');
INSERT INTO `t_sys_role_func` VALUES ('17', '4', '8');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_account` varchar(45) DEFAULT NULL,
  `c_pwd` varchar(45) DEFAULT NULL,
  `c_name` varchar(45) DEFAULT NULL,
  `n_create_time` bigint(20) DEFAULT NULL,
  `n_status` int(11) DEFAULT NULL,
  `n_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '11A9F6C34874760D42FFEB37563BDD0B', '周小欠', '0', '1', '1');
INSERT INTO `t_sys_user` VALUES ('2', 'wang', '11A9F6C34874760D42FFEB37563BDD0B', '王世强', '1501491250371', '1', '2');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_user_id` int(11) DEFAULT NULL,
  `n_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_sys_user_role` VALUES ('4', '2', '4');
