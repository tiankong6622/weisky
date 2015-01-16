package com.hz.yisheng.oa.workflow.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.yisheng.oa.workflow.dao.MoreActorNodeConditionMapper;
import com.hz.yisheng.oa.workflow.orm.WorkTask;

/**
 * 多人处理同一批工单
 * @author WeiSKY
 *
 */
@Service
public class MoreActorNodeConditionBO {

	@Autowired
	private MoreActorNodeConditionMapper moreActorNodeConditionMapper;
	
	public List<WorkTask> getWorkTask(Map<String,Object> param){
		return moreActorNodeConditionMapper.getWorkTask(param);
	}
	
	public List<Long> getActor(Map<String,Object> param){
		return moreActorNodeConditionMapper.getActor(param);
	}
}
