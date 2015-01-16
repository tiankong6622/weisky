package com.hz.yisheng.apptemplate.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.apptemplate.orm.ApplyRole;

public interface ApplyRoleMapper {

	public List<ApplyRole> list(Map<String,Object> param);
	
	public void insert(ApplyRole applyRole);
	
	public void update(ApplyRole applyRole);
	
	public ApplyRole select(Long id);
	
	public void delete(Long id);
	
	public Integer getMaxSort();
}
