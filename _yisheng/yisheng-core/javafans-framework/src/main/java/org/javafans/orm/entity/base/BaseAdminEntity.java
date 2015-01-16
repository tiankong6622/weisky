package org.javafans.orm.entity.base;

/**
 * 后台管理相关的实体bean的通用父类
 * @author ChenJunhui
 */
public abstract class BaseAdminEntity extends BaseEntity {


	private static final long serialVersionUID = -3867258108044656130L;

	private Long creator; //创建者
	
	private Long updater;//最后修改者

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}
	
	
}
