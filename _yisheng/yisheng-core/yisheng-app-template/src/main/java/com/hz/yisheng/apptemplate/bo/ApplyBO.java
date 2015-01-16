package com.hz.yisheng.apptemplate.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.apptemplate.dao.ApplyMapper;
import com.hz.yisheng.apptemplate.dao.TemplateMapper;
import com.hz.yisheng.apptemplate.orm.Apply;
/**
 * 应用处理
 * @author loard
 *
 */
@Service
@Transactional
public class ApplyBO {

	@Autowired
	private ApplyMapper applyMapper;
	@Autowired
	private TemplateMapper templateMapper;
	/**
	 * 根据id查找应用对象
	 * @param id
	 * @return
	 */
	public Apply select(Long id){
		return applyMapper.select(id);
	}
	/**
	 * 添加应用对象
	 * @param apply
	 */
	public void insert(Apply apply){
		applyMapper.insert(apply);
	}
	/**
	 * 更新应用对象
	 * @param apply
	 */
	public void update(Apply apply){
		applyMapper.update(apply);
	}
	/**
	 * 删除应用对象
	 * @param id
	 */
	@Transactional
	public void delete(Long id){
		templateMapper.deleteByAppId(id);
		applyMapper.delete(id);
	}
	/**
	 * 获得最新插入的id
	 * @return
	 */
	public Long getId(){
		return applyMapper.getId();
	}
}
