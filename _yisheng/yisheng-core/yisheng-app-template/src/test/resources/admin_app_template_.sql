/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.224
Source Server Version : 50621
Source Host           : 192.168.3.224:3306
Source Database       : yisheng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-01-05 17:20:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_app_template_`
-- ----------------------------
DROP TABLE IF EXISTS `admin_app_template_`;
CREATE TABLE `admin_app_template_` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块编号',
  `template_name` varchar(30) DEFAULT NULL COMMENT '模块名称',
  `android_image` varchar(200) DEFAULT NULL COMMENT '模块android图',
  `android_image_b` varchar(200) DEFAULT NULL COMMENT '模块android图',
  `ios_image` varchar(200) DEFAULT NULL COMMENT '模块ios图',
  `ios_image_b` varchar(200) DEFAULT NULL COMMENT '模块ios大图',
  `impl_address` varchar(100) DEFAULT NULL COMMENT '接口地址',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `app_id` int(11) DEFAULT NULL COMMENT '对应的appid',
  `apath` varchar(200) DEFAULT NULL COMMENT 'android图文件的路径',
  `abpath` varchar(200) DEFAULT NULL COMMENT 'android大图文件的路径',
  `ipath` varchar(200) DEFAULT NULL COMMENT 'ios图文件的路径',
  `ibpath` varchar(200) DEFAULT NULL COMMENT 'ios大图文件的路径',
  `team` varchar(20) DEFAULT NULL COMMENT '组名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_app_template_
-- ----------------------------
INSERT INTO `admin_app_template_` VALUES ('7', '待办事项', 'C:\\Users\\shenyg\\Desktop\\icon\\index-todo.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index-todo.png', null, '', '', '0', '8', '/2014/12/1f73e728-a6f8-42cd-ba8d-043c5cefe405.png', null, '/2014/12/af8eb12b-f153-45b4-88a0-ad2cea8a1397.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('8', '通知公告', 'C:\\Users\\shenyg\\Desktop\\icon\\2.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\2.png', null, '', '', '1', '8', '/2014/12/7ad12a02-e5e8-44fb-a8dd-a6ef476262e5.png', null, '/2014/12/94e00b01-47e4-499b-8f0a-ac011fc1f01f.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('9', '请假申请', 'C:\\Users\\shenyg\\Desktop\\icon\\index_vacation.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_vacation.png', null, '', '', '2', '8', '/2014/12/7009cafe-ffa1-43e2-86ab-87dccce7d411.png', null, '/2014/12/26093c56-d8c1-45f6-9faf-41bb748d2341.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('10', '加班申请', 'C:\\Users\\shenyg\\Desktop\\icon\\index_chufa.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_chufa.png', null, '', '', '3', '8', '/2014/12/661e6991-1383-4743-afff-a80ac37067c9.png', null, '/2014/12/c88047d4-23ec-40e7-b32a-8cbba6462493.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('11', '工作计划', 'C:\\Users\\shenyg\\Desktop\\icon\\index_duty.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_duty.png', null, '', '', '4', '8', '/2014/12/70c8c298-c923-4982-9bac-453a6af17ff7.png', null, '/2014/12/e23554fa-114d-49b5-9588-b24ceacd22b8.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('12', '文件资料', 'C:\\Users\\shenyg\\Desktop\\icon\\index_pword.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_pword.png', null, '', '', '5', '8', '/2014/12/92a96583-91e8-483c-9813-c269aa96ec83.png', null, '/2014/12/cd0eb1b6-552e-4df8-b839-eb8a0a54234a.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('13', '客户管理', 'C:\\Users\\shenyg\\Desktop\\icon\\index_meeting.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_meeting.png', null, '', '', '6', '8', '/2014/12/3dad4d3e-8d38-4140-9782-1ec79d2c90af.png', null, '/2014/12/294f781e-dfde-404b-989e-5851dd1f09fb.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('14', '加班管理', 'C:\\Users\\shenyg\\Desktop\\icon\\index_company.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_company.png', null, '', '', '7', '8', '/2014/12/b35371a8-1a35-4ebb-8437-30ab6894dd99.png', null, '/2014/12/29fe5ae5-06e9-4ea6-958d-588099a4457e.png', null, null);
INSERT INTO `admin_app_template_` VALUES ('15', '请假管理', 'C:\\Users\\shenyg\\Desktop\\icon\\index_jicha.png', null, 'C:\\Users\\shenyg\\Desktop\\icon\\index_jicha.png', null, '', '', '8', '8', '/2014/12/4a36ffb1-35a4-4161-86f0-1e6a859a7fbc.png', null, '/2014/12/0ecfdcfa-62dc-4118-b808-b4515b080139.png', null, null);
