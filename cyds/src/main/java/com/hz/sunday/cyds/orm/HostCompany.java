package com.hz.sunday.cyds.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;
/**
 * 主办单位基本信息
 * @author WeiSky
 *
 */
public class HostCompany extends BaseAdminEntity{

	private static final long serialVersionUID = -421321887284172277L;
	private String name;//主办单位名称
	private String log;//主办单位log
	private int htype;//合作机构类型  1：主办单位  2：承办单位
	
	public int getHtype() {
		return htype;
	}
	public void setHtype(int htype) {
		this.htype = htype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}
