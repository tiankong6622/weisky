package org.itboys.mongodb.entity;

import com.google.code.morphia.annotations.Id;

/**
 * ID为Long类型的实体的父类
 * @author 俊哥
 *
 */
public class BaseLongIdEntity extends BaseEntity{

	
	private static final long serialVersionUID = -4576356298348745830L;
	
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
