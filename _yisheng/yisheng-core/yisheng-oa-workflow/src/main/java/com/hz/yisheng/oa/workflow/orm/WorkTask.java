package com.hz.yisheng.oa.workflow.orm;

import java.util.List;
import java.util.Map;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 工单实体类  (主工单)
 * @author WeiSky
 *
 */
public class WorkTask extends BaseAdminEntity{
	
	private static final long serialVersionUID = -6571623661430005734L;
	
	
	public static final String STATUS_COMPLETE="complete";//最终的完成状态
	public static final String STATUS_HOLD="hold";
	
	private Long flowId;//流程ID
	private String taskType;//工单类型 就是流程定义表的 flow_name
	private Long submitor;//工单提交人 
	private Long submitOrg;//工单提交人所在组织 冗余字段 方便查询用
	private String submitFullOrgPath;//工单提交人所在组织全路径 冗余字段 方便查询用
	private Long actor;//当前处理人
	private Long orgId;//当前处理人所在组织 冗余字段 方便查询用
	private String fullOrgPath;//当前处理人所在组织全路径 冗余字段 方便查询用
	private Long submitNode;//提交的主业务节点
	private Long currentNode;//当前节点ID
	private Long fromTransition;//当前节点从哪个流转线流转过来的
	private String status;//各个工单的业务状态 业务系统自己定义业务状态 fromTransition可以 反正完成状态是complete即可

	private String reason;//出差事由
	private Integer extStatus;//扩展状态字段 自行定义
	private String flowType;//流程类型 1：非自定义  2：自定义
	
	//以下是非持久字段
	private FlowNode node;
	private String flowName;//这个是流程的中文名字
	private String fromTransitionName;//从哪个流转线过来的 就是哪个动作过来的
	private String actorName;//当前受理人名称
	private String submitterName;//提交人名称
	private String nodeName;//当前节点名称
	private List<WorkTaskRecord> records;
	private Map<String,Object>  bizData; 
	private Long projectId;
	
	private String taskTypeName;
	
	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}


	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCurrentNodeName(){
		return node==null?null:node.getName();
	}
	
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Long getActor() {
		return actor;
	}
	public void setActor(Long actor) {
		this.actor = actor;
	}
	public Long getSubmitNode() {
		return submitNode;
	}
	public void setSubmitNode(Long submitNode) {
		this.submitNode = submitNode;
	}
	public Long getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(Long currentNode) {
		this.currentNode = currentNode;
	}
	public Long getFromTransition() {
		return fromTransition;
	}
	public void setFromTransition(Long fromTransition) {
		this.fromTransition = fromTransition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getSubmitor() {
		return submitor;
	}
	public void setSubmitor(Long submitor) {
		this.submitor = submitor;
	}
	public Long getSubmitOrg() {
		return submitOrg;
	}
	public void setSubmitOrg(Long submitOrg) {
		this.submitOrg = submitOrg;
	}
	public String getSubmitFullOrgPath() {
		return submitFullOrgPath;
	}
	public void setSubmitFullOrgPath(String submitFullOrgPath) {
		this.submitFullOrgPath = submitFullOrgPath;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getFullOrgPath() {
		return fullOrgPath;
	}
	public void setFullOrgPath(String fullOrgPath) {
		this.fullOrgPath = fullOrgPath;
	}
	public String getFromTransitionName() {
		return fromTransitionName;
	}
	public void setFromTransitionName(String fromTransitionName) {
		this.fromTransitionName = fromTransitionName;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public String getSubmitterName() {
		return submitterName;
	}
	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}
	public List<WorkTaskRecord> getRecords() {
		return records;
	}
	public void setRecords(List<WorkTaskRecord> records) {
		this.records = records;
	}
	public Map<String, Object> getBizData() {
		return bizData;
	}
	public void setBizData(Map<String, Object> bizData) {
		this.bizData = bizData;
	}
	public FlowNode getNode() {
		return node;
	}
	public void setNode(FlowNode node) {
		this.node = node;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getExtStatus() {
		return extStatus;
	}

	public void setExtStatus(Integer extStatus) {
		this.extStatus = extStatus;
	}

	
	
	
}
