package com.hz.yisheng.apptemplate.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.admin.pojo.AdminRoleMenu;
import com.hz.yisheng.apptemplate.dao.ApplyRoleTemplateMapper;
import com.hz.yisheng.apptemplate.orm.ApplyRoleTemplate;

@Service
@Transactional
public class ApplyRoleTemplateBO {

	@Autowired
	private ApplyRoleTemplateMapper applyRoleTemplateMapper;
	
	public List<ApplyRoleTemplate> getByRoleId(List<Long> roleId){
		return applyRoleTemplateMapper.getByRoleId(roleId);
	}
	
	public void deleteByRoleId(Long roleId){
		applyRoleTemplateMapper.deleteByRoleId(roleId);
	}
	public void insert(ApplyRoleTemplate applyRoleTemplate){
		applyRoleTemplateMapper.insert(applyRoleTemplate);
	}
	
}
