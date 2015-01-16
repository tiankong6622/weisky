package com.hz.yisheng.oa.workflow.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 流程定义
 * @author WeiSky
 *
 */
public class FlowDefine extends BaseAdminEntity {
	
	private static final long serialVersionUID = 9102947681301466434L;
	
	private String flowname;//流程名称
	private String name;//流程中文名称
	private Integer version;//流程版本
	private Integer status=1;//流程状态 1:启用 2:停用
	private String template;//流程模板xml
	private Long startNodeId;//冗余字段 起始节点ID
	private Long projectId;//项目ID
	private Long flowTypeId;//自定义的流程所属哪个模块  从work_task_biz_data_config表中获取
	
	public Long getFlowTypeId() {
		return flowTypeId;
	}
	public void setFlowTypeId(Long flowTypeId) {
		this.flowTypeId = flowTypeId;
	}
	public String getFlowname() {
		return flowname;
	}
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getStartNodeId() {
		return startNodeId;
	}
	public void setStartNodeId(Long startNodeId) {
		this.startNodeId = startNodeId;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
