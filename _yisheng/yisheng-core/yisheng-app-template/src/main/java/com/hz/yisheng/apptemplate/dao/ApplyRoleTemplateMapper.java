package com.hz.yisheng.apptemplate.dao;

import java.util.List;

import com.hz.yisheng.apptemplate.orm.ApplyRoleTemplate;

public interface ApplyRoleTemplateMapper {

	public List<ApplyRoleTemplate> getByRoleId(List<Long> roleId);
	
	public void deleteByRoleId(Long id);
	
	public void insert(ApplyRoleTemplate applyRoleTemplate);
	
}
