CREATE TABLE `customer_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `sex` tinyint(2) DEFAULT '1' COMMENT '性别 1:男， 2：女',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `in_hospital_time` datetime DEFAULT NULL COMMENT '住院时间',
  `out_hospital_time` datetime DEFAULT NULL COMMENT '出院时间',
  `born_time` datetime DEFAULT NULL COMMENT '出生时间',
  `weight` decimal(12,2) DEFAULT NULL COMMENT '重量',
  `number` varchar(80) DEFAULT NULL COMMENT '住院编号',
  `bed_number` varchar(80) DEFAULT NULL COMMENT '床位',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `father` varchar(20) DEFAULT NULL COMMENT '父亲',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '孩子父类ID（默认为0，表示父母）',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `leve` varchar(20) DEFAULT NULL COMMENT '等级',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  `blood_type` varchar(20) DEFAULT NULL COMMENT '血型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='客户基本信息表';

CREATE TABLE `customer_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL COMMENT '设备类型',
  `number` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `status` varchar(20) DEFAULT NULL COMMENT '启动状态',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户设备信息表';


CREATE TABLE `child_health` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL COMMENT '婴儿的ID',
  `icterus` decimal(12,4) DEFAULT NULL COMMENT '黄疸值',
  `blood_pre` decimal(12,4) DEFAULT NULL COMMENT '血压值',
  `heart_rate` decimal(12,4) DEFAULT NULL COMMENT '心率',
  `temperature` decimal(12,4) DEFAULT NULL COMMENT '体温',
  `memo1` varchar(225) DEFAULT NULL COMMENT '预留字段1',
  `memo2` varchar(225) DEFAULT NULL COMMENT '预留字段2',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  `gatherer` varchar(50) DEFAULT NULL COMMENT '采集人',
  `gather_date` datetime DEFAULT NULL COMMENT '采集时间',
  `blood_type` varchar(20) DEFAULT NULL COMMENT '血型',
  `red_blood_cell` decimal(12,4) DEFAULT NULL COMMENT '红细胞',
  `white_blood_cell` decimal(12,4) DEFAULT NULL COMMENT '白细胞',
  `blood_plat` decimal(12,4) DEFAULT NULL COMMENT '血小板',
  `glucose` decimal(12,4) DEFAULT NULL COMMENT '葡萄糖',
  `carba` decimal(12,4) DEFAULT NULL COMMENT '尿素',
  `trioxy` decimal(12,4) DEFAULT NULL COMMENT '尿酸',
  `creatinine` decimal(12,4) DEFAULT NULL COMMENT '肌酐',
  `pyruvic_acid` decimal(12,4) DEFAULT NULL COMMENT '丙酮酸',
  `blood_fat` decimal(12,4) DEFAULT NULL COMMENT '血脂',
  `cholest` decimal(12,4) DEFAULT NULL COMMENT '胆固醇',
  `phospho` decimal(12,4) DEFAULT NULL COMMENT '磷脂',
  `trigly` decimal(12,4) DEFAULT NULL COMMENT '甘油三酯',
  `vital_capa` decimal(12,4) DEFAULT NULL COMMENT '肺活量',
  `weight` decimal(12,2) DEFAULT NULL COMMENT '体重',
  `cord_blood` decimal(12,4) DEFAULT NULL COMMENT '脐带血',
  `img` blob COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='婴儿健康信息表';


CREATE TABLE `child_trajectory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL COMMENT '婴儿的母亲ID',
  `coord_x` decimal(12,4) DEFAULT NULL COMMENT 'x坐标',
  `coord_y` decimal(12,4) DEFAULT NULL COMMENT 'y坐标',
  `coord_z` decimal(12,4) DEFAULT NULL COMMENT 'z坐标',
  `memo1` varchar(225) DEFAULT NULL COMMENT '预留字段1',
  `memo2` varchar(225) DEFAULT NULL COMMENT '预留字段2',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='婴儿轨迹信息';

CREATE TABLE `handbook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(225) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(225) DEFAULT NULL COMMENT '副标题',
  `summary` varchar(225) DEFAULT NULL COMMENT '摘要',
  `content` varchar(4000) DEFAULT NULL COMMENT '内容',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医院常用指南';

CREATE TABLE `online_faq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT 0 COMMENT '最原始的问题ID',
  `title` varchar(225) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(225) DEFAULT NULL COMMENT '副标题',
  `summary` varchar(225) DEFAULT NULL COMMENT '摘要',
  `content` varchar(225) DEFAULT NULL COMMENT '内容',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线问答';

CREATE TABLE `specialist_faq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT 0 COMMENT '最原始的问题ID',
  `title` varchar(225) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(225) DEFAULT NULL COMMENT '副标题',
  `summary` varchar(225) DEFAULT NULL COMMENT '摘要',
  `content` varchar(225) DEFAULT NULL COMMENT '内容',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专家解答';

CREATE TABLE `knowledge_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(225) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(225) DEFAULT NULL COMMENT '副标题',
  `summary` varchar(225) DEFAULT NULL COMMENT '摘要',
  `content` varchar(4000) DEFAULT NULL COMMENT '内容',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='知识库';

CREATE TABLE `download_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appcode` varchar(20) NOT NULL COMMENT '应用编码(手机端各个项目标识)',
  `dowtype` varchar(10) NOT NULL COMMENT '下载类型(ios,android,pad)',
  `mobitype` varchar(50) NOT NULL COMMENT '手机型号',
  `mobiname` varchar(50) COMMENT '机器名称',
  `mobiversion` varchar(20) COMMENT '机器版本',
  `mobicode` varchar(64) NOT NULL COMMENT '机器编码(手机唯一识别码)',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下载数量表';


CREATE TABLE `start_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appcode` varchar(20) NOT NULL COMMENT '应用编码(手机端各个项目标识)',
  `dowtype` varchar(10) NOT NULL COMMENT '下载类型(ios,android,pad)',
  `mobicode` varchar(64) NOT NULL COMMENT '机器编码(手机唯一识别码)',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='启动数量表';


CREATE TABLE `login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appcode` varchar(20) NOT NULL COMMENT '应用编码(手机端各个项目标识)',
  `userid` varchar(20) NOT NULL COMMENT '登录账号',
  `mobicode` varchar(64) NOT NULL COMMENT '机器编码(手机唯一识别码)',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志表';

CREATE TABLE `loginout_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appcode` varchar(20) NOT NULL COMMENT '应用编码(手机端各个项目标识)',
  `userid` varchar(20) NOT NULL COMMENT '登录账号',
  `mobicode` varchar(64) NOT NULL COMMENT '机器编码(手机唯一识别码)',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退出登录日志表';

CREATE TABLE `medication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `medication_name` varchar(50) NOT NULL COMMENT '药品名称',
  `medication_num` varchar(50) DEFAULT NULL COMMENT '用量',
  `medication_date` datetime DEFAULT NULL COMMENT '日期',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用药记录表';

CREATE TABLE `lactation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lactation_num` varchar(50) NOT NULL COMMENT '奶量',
  `medication_time` datetime DEFAULT NULL COMMENT '时间',
  `children_id` bigint(20) NOT  NULL COMMENT '儿童编号',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='哺乳记录表';

CREATE TABLE `customer_child_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL COMMENT '家长ID',
  `children_id` bigint(20) DEFAULT NULL COMMENT '儿童ID',
  `customer_device_number` varchar(50) DEFAULT NULL COMMENT '客户设备编号',
  `children_device_number` varchar(50) DEFAULT NULL COMMENT '婴儿设备编号',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1:有效 0:删除',
  `if_add` tinyint(1) DEFAULT NULL COMMENT '1:新增 0:更换',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户婴儿设备关联表';

CREATE TABLE `sports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户id',
  `type` varchar(10) CHARACTER DEFAULT NULL COMMENT '类型',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `memo1` varchar(255) CHARACTER DEFAULT NULL COMMENT '备注',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1：保留 0：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运动记录表';

CREATE TABLE `hospital` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '医院编号',
  `address` varchar(50) DEFAULT NULL COMMENT '医院地址',
  `telephone` varchar(15) DEFAULT NULL COMMENT '医院电话',
  `city_id` int(10) DEFAULT NULL COMMENT '医院所在城市id',
  `hospital_name` varchar(15) DEFAULT NULL COMMENT '医院名称',
  `province_id` int(10) DEFAULT NULL COMMENT '医院所在省份id',
  `district_id` int(10) DEFAULT NULL COMMENT '医院所在地区id',
  `code` varchar(100) DEFAULT NULL COMMENT '医院编码',
  `hospital_url` varchar(1000) DEFAULT NULL COMMENT '医院网址',
  `hospital_des` text COMMENT '医院简介',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医院管理表';
