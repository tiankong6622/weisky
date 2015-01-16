package com.hz.yisheng.nosql.mongodb.entity;

import com.google.code.morphia.annotations.Id;

/**
 * ID为Long类型的实体的父类
 * @author WeiSky
 *
 */
public class BaseLongIdEntity extends BaseEntity{

	private static final long serialVersionUID = 5542043764491843638L;
	
	@Id
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if(this.getId()==0L){
			return false;
		}
		if (obj.getClass().equals(this.getClass()) && (obj instanceof BaseLongIdEntity)) {
			BaseLongIdEntity o = (BaseLongIdEntity)obj;
			if(o.getId()==0L){
				return false;
			}
			return o.getId()==this.id;
		}
		return false;
	}
}
