package com.hz.yisheng.commondata.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * index page to html  indexHtml
 * @author lenovo
 */
public class StaticPage extends BaseAdminEntity {
	

	private static final long serialVersionUID = 7467215466514192696L;
	
	private Long projectId;//项目id  默认为0
	private String previewAddress;//预览地址
	private String address;//正式地址
	private String previewPath;//预览上传地址
	private String path;//正式上传地址	
	private String title;//page title 唯一
	private String descript;//描述
	private Integer status;//是否同步  0：未同步（默认）   1：同步	
	
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getPreviewAddress() {
		return previewAddress;
	}
	public void setPreviewAddress(String previewAddress) {
		this.previewAddress = previewAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPreviewPath() {
		return previewPath;
	}
	public void setPreviewPath(String previewPath) {
		this.previewPath = previewPath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
}