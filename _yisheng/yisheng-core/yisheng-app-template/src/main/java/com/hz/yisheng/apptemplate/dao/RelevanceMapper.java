package com.hz.yisheng.apptemplate.dao;

import java.util.List;

import com.hz.yisheng.apptemplate.orm.Apply;
import com.hz.yisheng.apptemplate.orm.Template;

public interface RelevanceMapper {
	/**
	 * 获得所有应用
	 * @return
	 */
	public List<Apply> getApply();
	/**
	 * 获得所有模块
	 * @return
	 */
	public List<Template> getTemplate();

}
