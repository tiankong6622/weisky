package com.hz.sunday.xccf.dao;

import java.util.List;
import java.util.Map;

import com.hz.sunday.xccf.orm.ColumnBean;

/**
 * 栏目管理
 * 
 * @author huanglei
 * @date 2015年4月13日
 * @version V1.0
 */
public interface ColumnMapper {

	/** 获取记录总数 */
	public long findCount(Map<String, Object> queryMap);

	/** 获取栏目信息 */
	public List<ColumnBean> findAll(Map<String, Object> queryMap);

	/** 新增 */
	public void insert(ColumnBean column);

	/** 更新 */
	public void update(ColumnBean column);

	/** 根据ID，删除选中信息 */
	public void deleteById(Long id);

	/** 根据ID，获取选中信息 */
	public ColumnBean findById(Long id);

}
