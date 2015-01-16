package com.hz.yisheng.oa.workflow.orm;

import java.io.Serializable;

/**
 * 工单各个节点处理记录关联的业务对象数据
 * @author WeiSky
 *
 */
public class WorkTaskBizData implements Serializable{

	private static final long serialVersionUID = 7120532456981568536L;
	private Long id;
	private Long workTaskId;//工单ID
	private String isMain;//是否主工单关联的数据 y:是 n:不是
	private Long workTaskRecordId;//工单处理记录的ID
	private String dataObjType;//关联的业务数据对象类型
	private Long dataObjId;//关联的对象ID 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkTaskId() {
		return workTaskId;
	}
	public void setWorkTaskId(Long workTaskId) {
		this.workTaskId = workTaskId;
	}
	public Long getWorkTaskRecordId() {
		return workTaskRecordId;
	}
	public void setWorkTaskRecordId(Long workTaskRecordId) {
		this.workTaskRecordId = workTaskRecordId;
	}
	public String getDataObjType() {
		return dataObjType;
	}
	public void setDataObjType(String dataObjType) {
		this.dataObjType = dataObjType;
	}
	public Long getDataObjId() {
		return dataObjId;
	}
	public void setDataObjId(Long dataObjId) {
		this.dataObjId = dataObjId;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}	
	
}
