CREATE TABLE `host_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '主办单位名称',
  `log` varchar(512) NOT NULL COMMENT '主办单位log',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主办单位'';