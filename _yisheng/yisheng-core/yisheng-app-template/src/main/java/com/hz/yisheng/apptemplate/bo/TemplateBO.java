package com.hz.yisheng.apptemplate.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.apptemplate.dao.TemplateMapper;
import com.hz.yisheng.apptemplate.orm.Template;
/**
 * 模块处理
 * @author loard
 *
 */
@Service
@Transactional
public class TemplateBO {

	@Autowired
	private TemplateMapper templateMapper;
	
	/**
	 * 根据id获得模块对象
	 * @param id
	 * @return
	 */
	public Template select(Long id){
		return templateMapper.select(id);
	}
	/**
	 * 更新模块
	 * @param template
	 */
	public void update(Template template){
		templateMapper.update(template);
	}
	/**
	 * 根据id删除模块
	 * @param id
	 */
	public void delete(Long id){
		templateMapper.delete(id);
	}
	/**
	 * 新增模块
	 * @param template
	 */
	public void insert(Template template){
		templateMapper.insert(template);
	}
	/**
	 * 获得最大sort
	 * @param id
	 * @return
	 */
	public Integer getMaxsort(Long id){
		return templateMapper.getMaxsort(id);
	}
	/**
	 * 获取最新插入的id
	 * @return
	 */
	public Long getId(){
		return templateMapper.getId();
	}
}
