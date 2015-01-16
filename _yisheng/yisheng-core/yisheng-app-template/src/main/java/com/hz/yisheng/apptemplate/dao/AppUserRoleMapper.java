package com.hz.yisheng.apptemplate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.apptemplate.orm.AppUserRole;

public interface AppUserRoleMapper {
	
	public List<AppUserRole> getByUserId(Long userId);
	
	public void insert(AppUserRole aur);
	
	public void deleteByRoleId(Long id);
	
	public void deleteByUserId(Long userId);
	
	public List<AppUserRole> selectAllUser();
	
	public List<AppUserRole> getByRoleId(Long id);
	
	public void adduser(@Param("uid") String uid,@Param("roleid") Long roleid);
	
	public void deleteuser(@Param("uid") String uid,@Param("roleid") Long roleid);

}
