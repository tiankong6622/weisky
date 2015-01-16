package com.hz.yisheng.commondata.orm;

/**
 * 一些项目要开通部分城市 就用这个表
 * 所有属性都和city一样 就是ID是自己的ID 不是city的ID 而 city_id才是 city表的ID
 * @author 余颖芳
 * CREATE TABLE `open_city` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `city_id` int(11) NOT NULL,
	  `project_id` int(11) NOT NULL,
	  `parent_id` int(11) NOT NULL,
	  `name` varchar(64) NOT NULL,
	  `level` tinyint(1) NOT NULL,
	  `full_name` varchar(128) DEFAULT NULL,
	  `code` varchar(32) DEFAULT NULL COMMENT '省份代码',
	  `initial` varchar(10) DEFAULT NULL COMMENT '首字母',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='开通的城市表';
 */
public class OpenCity extends City {
	

	private static final long serialVersionUID = 5398585151872038456L;
	private Long projectId;//项目ID 独立项目为0 小项目为项目的ID
	private Long cityId;//城市的ID

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	

	
}
