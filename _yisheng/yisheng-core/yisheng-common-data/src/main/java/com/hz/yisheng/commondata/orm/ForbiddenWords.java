package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseEntity;

/**
 * 违禁词表
 * @author Liuguanjun
 * 2013-05-30
 */
public class ForbiddenWords extends BaseEntity {

	private static final long serialVersionUID = -3712533166237860098L;
	
	/**
	 * 默认的配置ForbiddenWords 的projectId 
	 * 为了不写多套 项目的话就项目ID 通用的话就默认为default 的值0
	 */
	public static final Long DEFAULT_PROJECTID= 0l;
	
	private Integer sort; //优先级
	private String word;//关键词
	private Long projectId;
	
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
