package com.hz.yisheng.oa.workflow.orm;

import java.io.Serializable;
import java.util.List;

/**
 * 流程节点
 * @author WeiSky
 *
 */
public class FlowNode implements Serializable{

	private static final long serialVersionUID = 597255901519716097L;
	private Long id;//主键
	private Long flowId;//关联流程ID
	private String name;//节点名称
	private String type;//节点类型
	private String code;//给个英文code标识下
	private String beanName;//流程处理的spring bean的那么 bean为FlowNodeAction抽象类的子类
	private String dealUrl;//每个工单处理地址的url
	private String actorFilterCondition;
	private List<FlowTransition> transitions;//该流程节点 有几条下步流转线 处理人选择下步流转用
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public List<FlowTransition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<FlowTransition> transitions) {
		this.transitions = transitions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDealUrl() {
		return dealUrl;
	}
	public void setDealUrl(String dealUrl) {
		this.dealUrl = dealUrl;
	}
	public String getActorFilterCondition() {
		return actorFilterCondition;
	}
	public void setActorFilterCondition(String actorFilterCondition) {
		this.actorFilterCondition = actorFilterCondition;
	}
}
