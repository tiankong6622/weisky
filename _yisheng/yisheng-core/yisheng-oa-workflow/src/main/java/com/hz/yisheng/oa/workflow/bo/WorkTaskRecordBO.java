package com.hz.yisheng.oa.workflow.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.admin.bo.AdminUserBO;
import com.hz.yisheng.admin.pojo.AdminUser;
import com.hz.yisheng.oa.workflow.constants.FlowTransform;
import com.hz.yisheng.oa.workflow.dao.WorkTaskMapper;
import com.hz.yisheng.oa.workflow.dao.WorkTaskRecordMapper;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;

/**
 * 工单处理记录业务层
 * @author WeiSky
 *
 */
@Service
public class WorkTaskRecordBO {

	@Autowired
	private WorkTaskRecordMapper workTaskRecordMapper;
	@Autowired
	private FlowNodeBO flowNodeBO;
	@Autowired
	private AdminUserBO adminUserBO;
	@Autowired
	private WorkTaskBizDataBO workTaskBizDataBO;
	@Autowired
	private FlowTransitionBO flowTransitionBO;
	@Autowired
	private WorkTaskMapper workTaskMapper;
	
	/**
	 * 插入
	 * @param task
	 */
	public void insert(WorkTaskRecord record){
		workTaskRecordMapper.insert(record);
	}
	
	public List<WorkTaskRecord> getTaskRecords(Long taskId){
		Map<String, Object> sqlMap = new HashMap<String, Object>(1);
		sqlMap.put("workTaskId", taskId);
		WorkTask workTask = workTaskMapper.getById(taskId);
		Long projectId = workTask.getProjectId();
		boolean isProject = (projectId!=null && projectId>0L);
		List<WorkTaskRecord> result = workTaskRecordMapper.list(sqlMap);
		if(!result.isEmpty()){
			List<Long> nodeIds = Lists.transform(result, FlowTransform.getTaskRecordNodeIds);
			List<Long> actors = Lists.transform(result, FlowTransform.getTaskRecordActors);
			List<Long> submiters = Lists.transform(result, FlowTransform.getTaskRecordSubmiters);
			List<Long> transitionsIds =  Lists.transform(result, FlowTransform.getTaskRecordToTransitionIds);
			List<AdminUser> actorUsers = null;
			List<AdminUser> submitUsers = null;
			actorUsers = adminUserBO.getUserByIds(actors);
			submitUsers = adminUserBO.getUserByIds(submiters);
			List<FlowNode> flowNodes = flowNodeBO.getByIds(nodeIds);
			List<FlowTransition> transitions = flowTransitionBO.getByIds(transitionsIds);
			for(WorkTaskRecord record:result){
				for(FlowNode node:flowNodes){
					if(record.getNodeId().equals(node.getId())){
						record.setNode(node);
						break;
					}
				}
				
				for(FlowTransition ft:transitions){
					if(record.getToTransition().equals(ft.getId())){
						record.setToTransitionName(ft.getDealname());
						break;
					}
				}
				
				for(AdminUser au:actorUsers){
					if(record.getActor().equals(au.getId())){
						record.setActorName(au.getUserName());
						break;
					}
				}
				
				for(AdminUser au:submitUsers){
					if(record.getSubmitor().equals(au.getId())){
						record.setSubmitterName(au.getUserName());
						break;
					}
				}
				
				
			}
		}
		return result;
	}
	
	/**
	 * 查找某个节点最近的一次处理记录 回退的时候用
	 * @param nodeId
	 * @return
	 */
	public WorkTaskRecord getLastRecord(Long nodeId, Long workTaskId){
		Map<String,Object> param = Maps.newHashMapWithExpectedSize(2);
		param.put("nodeId", nodeId);
		param.put("workTaskId", workTaskId);
		return workTaskRecordMapper.getLastRecord(param);
	}
}
