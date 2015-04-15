package com.hz.sunday.xccf.dao;

import java.util.List;
import java.util.Map;

import com.hz.sunday.xccf.orm.TicketMemberBean;

/**
 * 报名管理
 * 
 * @author huanglei
 * @date 2015年4月13日
 * @version V1.0
 */
public interface TicketMemberMapper {

	/** 获取记录总数 */
	public long findCount(Map<String, Object> queryMap);

	/** 获取栏目信息 */
	public List<TicketMemberBean> findAll(Map<String, Object> queryMap);

	/** 新增 */
	public void insert(TicketMemberBean ticket);

	/** 更新 */
	public void update(TicketMemberBean ticket);

	/** 根据ID，删除选中信息 */
	public void deleteById(Long id);

	/** 根据ID，获取选中信息 */
	public TicketMemberBean findById(Long id);

}
