package com.hz.yisheng.apptemplate.dao;

import com.hz.yisheng.apptemplate.orm.Template;

public interface TemplateMapper {
	
	public Template select(Long id);
	
	public void update(Template template);
	
	public void insert(Template template);
	
	public void delete(Long id);
	
	public Integer getMaxsort(Long id);
	
	public void deleteByAppId(Long id);
	
	public Long getId();

}
