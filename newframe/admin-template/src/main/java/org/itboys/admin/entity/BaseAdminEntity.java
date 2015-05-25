package org.itboys.admin.entity;

import org.itboys.mongodb.entity.BaseLongIdEntity;

public abstract class BaseAdminEntity extends BaseLongIdEntity{

	private static final long serialVersionUID = -9112970541468501587L;

	/**
	 * 记录创建者的用户D
	 */
	private long cr;
	
	/**
	 * 记录修改者的用户ID
	 */
	private long ur;

	public long getCr() {
		return cr;
	}

	public void setCr(long cr) {
		this.cr = cr;
	}

	public long getUr() {
		return ur;
	}

	public void setUr(long ur) {
		this.ur = ur;
	}
}
