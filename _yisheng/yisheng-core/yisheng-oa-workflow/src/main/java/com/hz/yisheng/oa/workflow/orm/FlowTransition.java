package com.hz.yisheng.oa.workflow.orm;

/**
 * 流程流转线
 * @author WeiSky
 *
 */
public class FlowTransition {

	private Long id;
	private String name;//流程流转线名称
	private String dealname;
	private Long fromNodeId;//从哪个节点留过来的
	private Long toNodeId;//流向哪个节点
	
	//顺时对象
	private String from;
	private String to;
	
	private String code;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFromNodeId() {
		return fromNodeId;
	}
	public void setFromNodeId(Long fromNodeId) {
		this.fromNodeId = fromNodeId;
	}
	public Long getToNodeId() {
		return toNodeId;
	}
	public void setToNodeId(Long toNodeId) {
		this.toNodeId = toNodeId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDealname() {
		return dealname;
	}
	public void setDealname(String dealname) {
		this.dealname = dealname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
