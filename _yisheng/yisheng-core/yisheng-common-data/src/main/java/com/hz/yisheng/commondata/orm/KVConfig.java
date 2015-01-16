package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * key-value格式的 配置项 
 * @author ChenJunhui
 *
 */
public class KVConfig extends BaseAdminEntity {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_APP_KEY="0";
	
	private String appKey;//不为项目的配置项则为0 否则为项目的code
	private String key;
	private String value;
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
