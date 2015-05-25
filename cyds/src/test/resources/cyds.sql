CREATE TABLE `host_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '主办单位名称',
  `log` varchar(512) NOT NULL COMMENT '主办单位log',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主办单位';

CREATE TABLE `recom_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_type` int(4) NOT NULL COMMENT '推荐单位类型 1:创新型服务机构 2:孵化器和园区 3:投资机构 4:科技站点 5:重点高校 6:培训机构 7:媒体 8:其它',
  `name` varchar(128) NOT NULL COMMENT '推荐单位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='推荐单位';

INSERT INTO recom_unit(unit_type, `name`) value(1,'极客窝');
INSERT INTO recom_unit(unit_type, `name`) value(1,'泥巴创客空间');
INSERT INTO recom_unit(unit_type, `name`) value(1,'DO+咖啡');
INSERT INTO recom_unit(unit_type, `name`) value(1,'西部众筹');
INSERT INTO recom_unit(unit_type, `name`) value(1,'华友会');
INSERT INTO recom_unit(unit_type, `name`) value(1,'学子360');
INSERT INTO recom_unit(unit_type, `name`) value(1,'西安热');
INSERT INTO recom_unit(unit_type, `name`) value(1,'西北狼电商');
INSERT INTO recom_unit(unit_type, `name`) value(1,'陕西青年培训学校');
INSERT INTO recom_unit(unit_type, `name`) value(1,'非凡士3D打印');
INSERT INTO recom_unit(unit_type, `name`) value(1,'业梦响团');
INSERT INTO recom_unit(unit_type, `name`) value(1,'木立方成长俱乐部');
INSERT INTO recom_unit(unit_type, `name`) value(1,'西安众投企业管理咨询有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(1,'金融家咖啡');
INSERT INTO recom_unit(unit_type, `name`) value(1,'创途在XIAN');

INSERT INTO recom_unit(unit_type, `name`) value(2,'西安软件园发展中心');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安集成电路设计专业孵化器有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安光电子专业孵化器有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'陕西启迪科技园发展有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安三元数字媒体科技企业孵化器');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安电力电子企业孵化器');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安创联企业孵化器有限责任公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安联创生物医药孵化器有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安易创军民两用科技工业孵化器有限责任公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安电子科技大学国家电子科技园');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安经开区生产力促进中心');
INSERT INTO recom_unit(unit_type, `name`) value(2,'碑林环大学创新产业带管委会');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安航空基地科创中心');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安航天基地国际孵化器有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(2,'西安文化科技创业城股份有限公司（筹）');

INSERT INTO recom_unit(unit_type, `name`) value(3,'默克（中国）集团');
INSERT INTO recom_unit(unit_type, `name`) value(3,'陕西协和资产管理股份有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'拓金投资管理合伙企业');
INSERT INTO recom_unit(unit_type, `name`) value(3,'西安瑞鹏创业投资管理有限合伙企业');
INSERT INTO recom_unit(unit_type, `name`) value(3,'力合清源创业投资管理有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'力合清源创业投资管理有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'光大金控资产管理有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'西安同创博润创业投资管理中心');
INSERT INTO recom_unit(unit_type, `name`) value(3,'深圳市达晨创业投资有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'深圳市东方富海投资管理有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'陕西西科天使投资基金');
INSERT INTO recom_unit(unit_type, `name`) value(3,'陕西富源投资集团有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'西安高新技术产业风险投资有限责任公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'西安迈朴资本管理有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(3,'力邦财务投资集团');

INSERT INTO recom_unit(unit_type, `name`) value(4,'西格玛大厦');
INSERT INTO recom_unit(unit_type, `name`) value(4,'华晶广场');
INSERT INTO recom_unit(unit_type, `name`) value(4,'杰座广场');
INSERT INTO recom_unit(unit_type, `name`) value(4,'E阳国际');
INSERT INTO recom_unit(unit_type, `name`) value(4,'绿地SOHO');
INSERT INTO recom_unit(unit_type, `name`) value(4,'伟志科技苑');
INSERT INTO recom_unit(unit_type, `name`) value(4,'旺座现代');
INSERT INTO recom_unit(unit_type, `name`) value(4,'汇鑫IBC');
INSERT INTO recom_unit(unit_type, `name`) value(4,'信凯工业园');

INSERT INTO recom_unit(unit_type, `name`) value(5,'西安交通大学');
INSERT INTO recom_unit(unit_type, `name`) value(5,'西安电子科技大学');
INSERT INTO recom_unit(unit_type, `name`) value(5,'西安邮电大学');
INSERT INTO recom_unit(unit_type, `name`) value(5,'陕西科技大学');
INSERT INTO recom_unit(unit_type, `name`) value(5,'西安文理学院');
INSERT INTO recom_unit(unit_type, `name`) value(5,'西安欧亚学院');

INSERT INTO recom_unit(unit_type, `name`) value(6,'西安宏略企业管理咨询公司');
INSERT INTO recom_unit(unit_type, `name`) value(6,'西安洪杉企业管理咨询有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(6,'德恩企业管理咨询有限公司');
INSERT INTO recom_unit(unit_type, `name`) value(6,'科林利康医学研究有限公司');

INSERT INTO recom_unit(unit_type, `name`) value(7,'张持有道');














