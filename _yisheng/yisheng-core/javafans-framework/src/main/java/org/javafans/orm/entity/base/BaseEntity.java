package org.javafans.orm.entity.base;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * orm 实体类的基础类
 * @author DingXQ
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4747935309941295435L;

	protected Long id; //主键
	
	protected Date createTime; //创建时间
	
	protected Date updateTime; //修改时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj.getClass().equals(this.getClass()) && (obj instanceof BaseEntity)) {
			BaseEntity entity = (BaseEntity) obj;
			Long eid = entity.getId();
			if (eid == null || id == null)
				return false;
			else
				return eid.longValue() == id.longValue();
		} else
			return false;
	}
	
	@Override 
    public int hashCode() { 
            return HashCodeBuilder.reflectionHashCode(this); 

    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
