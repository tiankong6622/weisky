-- ----------------------------
-- Table structure for `message_info` 信息相关资料表
-- ----------------------------
DROP TABLE IF EXISTS `message_info`;
CREATE TABLE `message_info` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(64) default NULL COMMENT '标题',
  `subhead` varchar(64) default NULL COMMENT '副标题',
  `summary` varchar(200) default NULL COMMENT '摘要',
  `comment` text COMMENT '内容',
  `address` varchar(32) default NULL COMMENT '地点位置',
  `m_type` int(4) default '0' COMMENT '信息类型  1：论坛介绍  2：新闻中心 3：顶尖领袖团 4：拟邀请嘉宾  5：组织机构  6:论坛日程  7:合作媒体  8:总策划人  9:联系方式',
  `reource` varchar(64) default NULL COMMENT '信息来源',
  `creator` bigint(20) default NULL COMMENT '创建人',
  `create_time` datetime default NULL,
  `updater` bigint(20) default NULL COMMENT '修改人',
  `update_time` datetime default NULL,
  `action_time` datetime default NULL COMMENT '活动时间',
  `action_time2` varchar(64) default NULL COMMENT '活动时间的另一种格式  比如：10:00--10:55',
  `action_addr` varchar(120) default NULL COMMENT '活动地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息相关资料表';

-- ----------------------------
-- Table structure for `ticket_member` 获取门票的人员信息
-- ----------------------------
DROP TABLE IF EXISTS `ticket_member`;
CREATE TABLE `ticket_member` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `company` varchar(64) NOT NULL COMMENT '公司',
  `post` varchar(16) NOT NULL COMMENT '职务',
  `phone` varchar(11) NOT NULL COMMENT '手机',
  `goal` varchar(64) NOT NULL COMMENT '意向',
  `number` int(20) NOT NULL COMMENT '人数',
  `make` varchar(648) default NULL COMMENT '预留字段',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='获取门票的人员信息';