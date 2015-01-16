package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.commondata.orm.ParamConfigTemplate;

public interface ParamConfigTemplateMapper {

	public List<ParamConfigTemplate> list(Map<String, Object> map);
	
	public Long count(Map<String, Object> map);
	
	public void insert(ParamConfigTemplate pconfig);
	
	public List<ParamConfigTemplate> getCodesByType(Map<String, Object> map);
	
	public void update(ParamConfigTemplate pconfig);
	
	public int delete(Long id);
	
	public ParamConfigTemplate getById(Long id);
}
