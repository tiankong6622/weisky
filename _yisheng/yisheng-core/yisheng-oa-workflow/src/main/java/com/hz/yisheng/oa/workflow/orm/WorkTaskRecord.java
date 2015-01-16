package com.hz.yisheng.oa.workflow.orm;

import java.util.Map;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 各个节点的工单处理记录
 * createTime为第一次处理时间 updateTime为修改时间
 * @author WeiSky
 *
 */
public class WorkTaskRecord extends BaseAdminEntity {
	
	
	private static final long serialVersionUID = -7508009704660804906L;
	
	private Long workTaskId;//工单ID
	private Long nodeId;//当前处理节点ID
	private Long toNodeId;//处理完后流向流程哪个节点
	private Long submitor;//工单提交人 冗余字段 方便查询用
	private Long submitOrg;//工单提交人所在组织 方便查询用
	private String submitFullOrgPath;//工单提交人所在组织 方便查询用
	private Long actor;//节点处理人 
	private Long orgId;//处理人所在组织
	private String fullOrgPath;//当前处理人所在组织全路径 冗余字段 方便查询用
	private Long fromTransition;//从那个流转线过来的 冗余
	private Long toTransition;//处理完后走哪根流转线
	private Long flowId;//关联流程ID 容易字段
	private String taskType;//工单类型 就是流程定义表的 flow_name
	private String dealContent;//每个节点的处理意见
	private String status;//当前这个节点的处理状态 冗余
	
	//以下是非持久字段
	private FlowNode node;
	private String actorName;
	private String submitterName;
	private String toTransitionName;
	private Map<String,Object> bizData;
	
	public Long getWorkTaskId() {
		return workTaskId;
	}
	public void setWorkTaskId(Long workTaskId) {
		this.workTaskId = workTaskId;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public Long getToNodeId() {
		return toNodeId;
	}
	public void setToNodeId(Long toNodeId) {
		this.toNodeId = toNodeId;
	}
	public Long getFromTransition() {
		return fromTransition;
	}
	public void setFromTransition(Long fromTransition) {
		this.fromTransition = fromTransition;
	}
	public Long getToTransition() {
		return toTransition;
	}
	public void setToTransition(Long toTransition) {
		this.toTransition = toTransition;
	}
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	public String getDealContent() {
		return dealContent;
	}
	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}
	public Long getActor() {
		return actor;
	}
	public void setActor(Long actor) {
		this.actor = actor;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmitFullOrgPath() {
		return submitFullOrgPath;
	}
	public void setSubmitFullOrgPath(String submitFullOrgPath) {
		this.submitFullOrgPath = submitFullOrgPath;
	}
	public String getFullOrgPath() {
		return fullOrgPath;
	}
	public void setFullOrgPath(String fullOrgPath) {
		this.fullOrgPath = fullOrgPath;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public FlowNode getNode() {
		return node;
	}
	public void setNode(FlowNode node) {
		this.node = node;
	}
	public String getSubmitterName() {
		return submitterName;
	}
	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}
	public Map<String, Object> getBizData() {
		return bizData;
	}
	public void setBizData(Map<String, Object> bizData) {
		this.bizData = bizData;
	}
	public String getToTransitionName() {
		return toTransitionName;
	}
	public void setToTransitionName(String toTransitionName) {
		this.toTransitionName = toTransitionName;
	}
	
}
