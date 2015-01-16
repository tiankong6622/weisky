package com.hz.yisheng.apptemplate.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.apptemplate.dao.AppUserRoleMapper;
import com.hz.yisheng.apptemplate.dao.ApplyRoleMapper;
import com.hz.yisheng.apptemplate.dao.ApplyRoleTemplateMapper;
import com.hz.yisheng.apptemplate.orm.ApplyRole;
import com.hz.yisheng.apptemplate.orm.ApplyRoleTemplate;

@Service
@Transactional
public class ApplyRoleBO {

	@Autowired
	private ApplyRoleMapper applyRoleMapper;
	@Autowired
	private ApplyRoleTemplateMapper applyRoleTemplateMapper;
	@Autowired
	private AppUserRoleMapper appUserRoleMapper;
	
	public List<ApplyRole> list(Map<String,Object> param){
		return applyRoleMapper.list(param);
	}
	
	public void insert(ApplyRole applyRole){
		applyRoleMapper.insert(applyRole);
	}
	
	public void delete(Long id){
		applyRoleMapper.delete(id);
		applyRoleTemplateMapper.deleteByRoleId(id);
		appUserRoleMapper.deleteByRoleId(id);
		
	}
	
	public void update(ApplyRole applyRole){
		applyRoleMapper.update(applyRole);
	}
	/***
	 * 根据id获取角色信息
	 * @param id
	 * @return
	 */
	public ApplyRole select(Long id){
		return applyRoleMapper.select(id);
	}
	public Integer getMaxSort(){
		return applyRoleMapper.getMaxSort();
	}

}
