package com.hz.yisheng.oa.workflow.core;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.javafans.common.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.admin.bo.AdminDepartBO;
import com.hz.yisheng.admin.bo.AdminDepartUserBO;
import com.hz.yisheng.admin.pojo.AdminDepart;
import com.hz.yisheng.admin.pojo.AdminDepartUser;
import com.hz.yisheng.oa.workflow.bo.FlowDefineBO;
import com.hz.yisheng.oa.workflow.bo.FlowNodeBO;
import com.hz.yisheng.oa.workflow.bo.FlowTransitionBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskRecordBO;
import com.hz.yisheng.oa.workflow.constants.FlowNodeType;
import com.hz.yisheng.oa.workflow.dto.FlowParam;
import com.hz.yisheng.oa.workflow.exception.FlowException;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizData;
import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 流程引擎
 * @author WeiSky
 *
 */
@Service
public class FlowEngine {
	
	private static Logger logger = LoggerFactory.getLogger(FlowEngine.class);
	@Autowired
	private FlowDefineBO flowDefineBO;
	@Autowired
	private FlowNodeBO flowNodeBO;
	@Autowired
	private FlowTransitionBO flowTransitionBO;
	@Autowired
	private WorkTaskBO workTaskBO;
	@Autowired
	private AdminDepartUserBO adminDepartUserBO;
	@Autowired
	private AdminDepartBO adminDepartBO;
	
	@Autowired
	private WorkTaskBizDataBO workTaskBizDataBO;
	@Autowired
	private WorkTaskRecordBO workTaskRecordBO;
	
	/**
	 * 启动一个流程 用户基于admin的 project的到时候搞过
	 * @param submiter 流程提交人
	 * @param actor 流程处理人 提交流程给哪个人处理
	 * @param flowname 流程名称
	 *  @param toTranstaionId 工单创建的时候要提交给谁
	 * @param workTaskBizDatas 主工单关联的业务数据对象ID
	 * @param param 第一个节点处理bean用到的特殊处理参数 没有的话 可以传null
	 */
	@Transactional("workFlowTransactionManager")
	public  void startFlow(FlowParam flowParam,List<WorkTaskBizData> workTaskBizDatas){
		try{
			//找最新流程
			FlowDefine flow = flowDefineBO.getLast(flowParam.getFlowname());
			if(flow==null){
				throw new FlowException(FlowException.NO_FLOW,flowParam.getFlowname());
			}
			long startNodeId = flow.getStartNodeId();
			//找开始节点
			FlowNode startNode = flowNodeBO.getById(startNodeId);
			if(startNode==null){
				throw new FlowException(FlowException.CAN_NOT_FIND_START_NODE,flowParam.getFlowname());
			}
			//开始节点只有一条流转线
			FlowTransition startToTransition = flowTransitionBO.getByFromId(startNode.getId());
			if(startToTransition==null){
				throw new FlowException(FlowException.CAN_NOT_FIND_TRANSITION,flowParam.getFlowname());
			}
			//找到第一个流向节点
			long toId = startToTransition.getToNodeId();
			FlowNode node = flowNodeBO.getById(toId);
			//创建主工单
			if(flowParam.getSubmiter()==null){
				flowParam.setSubmiter(SessionHolder.getAdminUserId());
			}
			AdminDepartUser submitterUserOrgPost = adminDepartUserBO.getByUserId(flowParam.getSubmiter());
			AdminDepartUser actorUserOrgPost = adminDepartUserBO.getByUserId(flowParam.getActor());
			AdminDepart submitterOrg = adminDepartBO.getAdminDepart(submitterUserOrgPost.getDepartId());
			AdminDepart actorOrg = adminDepartBO.getAdminDepart(actorUserOrgPost.getDepartId());
			WorkTask task = new WorkTask();
			task.setFlowId(flow.getId());
			task.setActor(flowParam.getActor());
			task.setOrgId(Long.parseLong(String.valueOf(actorOrg.getId())));
			task.setFullOrgPath(actorOrg.getFullIdPath());
			task.setSubmitor(flowParam.getSubmiter());
			task.setSubmitOrg(Long.parseLong(String.valueOf(submitterOrg.getId())));
			task.setSubmitFullOrgPath(submitterOrg.getFullIdPath());
			task.setCreator(flowParam.getSubmiter());
			task.setSubmitNode(node.getId());
			task.setCurrentNode(node.getId());
			task.setFromTransition(startToTransition.getId());
			task.setStatus(WorkTask.STATUS_HOLD);
			task.setUpdater(flowParam.getSubmiter());
			task.setTaskType(flow.getFlowname());
			SessionHolder.prepareAdminLoginData(task);
			task.setProjectId(0L);
			workTaskBO.insert(task);
		
			
			WorkTaskRecord record = new WorkTaskRecord();
			record.setWorkTaskId(task.getId());
			record.setNodeId(node.getId());
			record.setSubmitor(task.getSubmitor());
			record.setSubmitOrg(task.getSubmitOrg());
			record.setSubmitFullOrgPath(task.getSubmitFullOrgPath());
			record.setFromTransition(task.getFromTransition());
			record.setFlowId(task.getFlowId());
			record.setTaskType(task.getTaskType());
			record.setDealContent(StringUtils.EMPTY);
			record.setActor(task.getSubmitor());
			record.setOrgId(task.getSubmitOrg());
			record.setFullOrgPath(task.getSubmitFullOrgPath());
			record.setToTransition(flowParam.getToTranstaionId());
			record.setStatus(flowParam.isFistTaskNodeComplete()?WorkTask.STATUS_COMPLETE:WorkTask.STATUS_HOLD);
			record.setToNodeId(node.getId());
			//插入第一个工单节点记录
			SessionHolder.prepareAdminLoginData(record);
			workTaskRecordBO.insert(record);
			//批量插入业务数据
			if(workTaskBizDatas!=null && !workTaskBizDatas.isEmpty()){
				for(WorkTaskBizData bizData:workTaskBizDatas){
					bizData.setIsMain(CommonConstants.YES);
					bizData.setWorkTaskId(task.getId());
					bizData.setWorkTaskRecordId(record.getId());
				}
				workTaskBizDataBO.batchInsert(workTaskBizDatas);
			}
			flowParam.setWorkTaskId(task.getId());
			//往下流
			if(flowParam.getToTranstaionId()!=null){
				FlowTransition nextTransition = flowTransitionBO.getById(flowParam.getToTranstaionId());
				if(!nextTransition.getFromNodeId().equals(node.getId())){
					throw new FlowException(FlowException.DIRTY_DATA,flowParam.getFlowname());
				}
				doFlowTask(workTaskBizDatas, flowParam);
			}else{
				throw new FlowException(FlowException.DIRTY_DATA,flowParam.getFlowname());
			}
		}catch(Exception e){
			logger.error("startFlow fail,param is "+ToStringBuilder.reflectionToString(flowParam),e);
			throw new FlowException(FlowException.START_FLOW_FAIL,flowParam.getFlowname());
		}
	}
	
	/**
	 * 执行流程任务
	 * @param param
	 * @param workTaskBizDatas
	 * @param actor
	 * @param workTaskId
	 * @param  transitionId 流转线ID 
	 */
	@Transactional("workFlowTransactionManager")
	public   void doFlowTask(List<WorkTaskBizData> workTaskBizDatas,FlowParam flowParam){
		Long workTaskId = flowParam.getWorkTaskId();
		FlowTransition nextTransition = flowTransitionBO.getById(flowParam.getToTranstaionId());
		WorkTask task = workTaskBO.getById(workTaskId);
		if(flowParam.isFinished()){
			//创建一条新的工单处理记录
			WorkTaskRecord record = new WorkTaskRecord();
			record.setWorkTaskId(task.getId());
			record.setNodeId(task.getCurrentNode());
			record.setSubmitor(task.getSubmitor());
			record.setSubmitOrg(task.getSubmitOrg());
			record.setSubmitFullOrgPath(task.getSubmitFullOrgPath());
			record.setFromTransition(task.getFromTransition());
			record.setToTransition(task.getCurrentNode());
			record.setFlowId(task.getFlowId());
			record.setTaskType(task.getTaskType());
			record.setDealContent(flowParam.getDealContent()==null?"":flowParam.getDealContent());
			//record.setActor(task.getActor());
			record.setActor(SessionHolder.getAdminUserId());
			record.setOrgId(task.getOrgId());
			record.setFullOrgPath(task.getFullOrgPath());
			record.setDealContent(flowParam.getDealContent());
			record.setToTransition(flowParam.getToTranstaionId());
			record.setStatus(WorkTask.STATUS_COMPLETE);
			record.setToNodeId(nextTransition.getFromNodeId());
			SessionHolder.prepareAdminLoginData(record);
			workTaskRecordBO.insert(record);
			if(workTaskBizDatas!=null && !workTaskBizDatas.isEmpty()){
				for(WorkTaskBizData bizData:workTaskBizDatas){
					bizData.setIsMain(CommonConstants.NO);
					bizData.setWorkTaskId(task.getId());
					bizData.setWorkTaskRecordId(record.getId());
				}
				if(FlowParam.BIZ_DATA_DEAL_TYPE_NEW.equals(flowParam.getNodeBizDataDealType())){
					workTaskBizDataBO.batchInsert(workTaskBizDatas);
				}else if(FlowParam.BIZ_DATA_DEAL_TYPE_RESET.equals(flowParam.getNodeBizDataDealType())){
					for(WorkTaskBizData bizData:workTaskBizDatas){
						bizData.setIsMain(CommonConstants.YES);
					}
					workTaskBizDataBO.deleteByTaskId(workTaskId);
					workTaskBizDataBO.batchInsert(workTaskBizDatas);
				}
			}
		}
		FlowNode nextNode = flowNodeBO.getById(nextTransition.getToNodeId());
		task.setFromTransition(nextTransition.getId());
		task.setUpdateTime(new Date());
		if(nextNode.getType().equals(FlowNodeType.end) || nextNode.getType().equals(FlowNodeType.end_round)){//到结束节点了
			task.setFromTransition(nextTransition.getId());
			task.setStatus(WorkTask.STATUS_COMPLETE);//工单完成了
		}else{
			Long toNodeId=nextTransition.getToNodeId();
			if(toNodeId<task.getCurrentNode()){
				//说明是回退节点 回退节点重新置上上次那个节点的处理人
				WorkTaskRecord backRecord = workTaskRecordBO.getLastRecord(toNodeId, workTaskId);
				task.setActor(backRecord.getActor());
				task.setOrgId(backRecord.getOrgId());
				task.setFullOrgPath(backRecord.getFullOrgPath());
			}else{
				//到了新的节点 修改工单数据
				AdminDepartUser actorUserOrgPost = adminDepartUserBO.getByUserId(flowParam.getActor());
				AdminDepart actorOrg = adminDepartBO.getAdminDepart(actorUserOrgPost.getDepartId());
				
				task.setActor(flowParam.getActor());
				task.setOrgId(Long.parseLong(String.valueOf(actorOrg.getId())));
				task.setFullOrgPath(actorOrg.getFullIdPath());
			}
		}
		task.setCurrentNode(nextTransition.getToNodeId());
		SessionHolder.prepareAdminLoginData(task);
		workTaskBO.update(task);
	}
}
