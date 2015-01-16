package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.oa.workflow.orm.WorkTask;
/**
 * 多人处理同一批工单
 * @author WeiSKY
 *
 */
public interface MoreActorNodeConditionMapper {
	
	public List<WorkTask> getWorkTask(Map<String,Object> param);
	
	public List<Long> getActor(Map<String,Object> param);
	
}
