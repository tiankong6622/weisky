package com.hz.yisheng.oa.workflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.oa.workflow.orm.FlowTransition;

/**
 * 流程线DAO
 * @author WeiSky
 *
 */
public interface FlowTransitionMapper {

	public void batchInsert(List<FlowTransition> list);
	
	public FlowTransition getByFromId(long fromId);
	
	public FlowTransition getByFromIdCode(@Param("fromId") long fromId, @Param("nodeCode") String nodeCode);
	
	public FlowTransition getByToId(long toId);
	
	public FlowTransition getById(long id);
	
	public List<FlowTransition>  getTransitionsFromId(long fromId);
	
	public List<FlowTransition>  getByIds(List<Long> list);
}
