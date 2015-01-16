package org.javafans.orm.entity.base;

/**
 * 后台管理相关的实体bean的通用父类 
 * 逻辑删除标记
 * @author DingXQ
 */
public abstract class BaseAdminLogicDeleteEntity extends BaseAdminEntity {


	private static final long serialVersionUID = -3867258108044656130L;
	private Integer is_deleted;			//是否删除
	
	public Integer getIs_deleted() {
		return is_deleted;
	}
	
	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}
	
}
