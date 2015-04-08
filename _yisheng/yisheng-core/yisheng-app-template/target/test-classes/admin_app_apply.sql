/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.224
Source Server Version : 50621
Source Host           : 192.168.3.224:3306
Source Database       : yisheng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-01-05 17:22:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_app_apply`
-- ----------------------------
DROP TABLE IF EXISTS `admin_app_apply`;
CREATE TABLE `admin_app_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appname` varchar(20) DEFAULT NULL COMMENT '应用名称',
  `appimage` varchar(100) DEFAULT NULL COMMENT '应用图片',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `tdc_address` varchar(200) DEFAULT NULL COMMENT '二维码地址',
  `app_summy` varchar(1000) DEFAULT NULL COMMENT '应用介绍',
  `path` varchar(200) DEFAULT NULL COMMENT '文件的路径',
  `appcode` varchar(20) DEFAULT NULL COMMENT '应用标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_app_apply
-- ----------------------------
INSERT INTO `admin_app_apply` VALUES ('8', '易盛圈', 'F:\\项目管理\\易盛网络通讯\\易盛项目\\易盛oa\\切图\\登录界面切图.png', '沈云刚', '13656818210', '', '11', '/2014/12/f7ffa9c6-ddbd-4421-bf10-8322da02c9aa.png', 'YSQ');
