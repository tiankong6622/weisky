package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * config_codes 配置表相关实体
 * @author ChenJunhui
 *
 */
public class Codes extends BaseAdminEntity {

	private static final long serialVersionUID = 2760671231650183361L;

	/**
	 *默认配置的projectID 一般管理项目的ID
	 * 比如某某子项目 等 如果没有子项目 或者最高级项目  默认为default 的值0
	 */
	public static final String DEFAULT_REL_OBJ_ID = "0";
	
	private String relObjectId = DEFAULT_REL_OBJ_ID;
	private String type;
	private String key;
	private String value;
	private String desc;

	public String getRelObjectId() {
		return relObjectId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setRelObjectId(String relObjectId) {
		this.relObjectId = relObjectId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	  
	  
}
