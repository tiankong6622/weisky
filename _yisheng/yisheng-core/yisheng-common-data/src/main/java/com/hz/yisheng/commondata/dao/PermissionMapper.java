package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.commondata.orm.Permission;

/**
 * 权限DAO
 * @author GuoRui
 */
public interface PermissionMapper {

	public List<Permission> list(Map<String, Object> param);
	
	public Long count(Map<String, Object> param);
	
	public void insert(Permission permission);
	
	public int update(Permission permission);
	
	/*
	 * 删除功能模块时删除该模块下的权限
	 */
	public int updateDltByModule(List<Permission> lists);
	
	public int delete(Long id);
	
	/*
	 * 根据角色ID 到关联关系表里 找出关联的权限
	 * @param roleIds
	 * @return
	 */
	
	public List<Permission> getPermissionsByGrade(Map<String, Object> map);
}
