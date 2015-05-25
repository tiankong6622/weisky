package org.itboys.mongodb.entity;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;

/**
 * ID为mongodb ObjectId类型的父类
 * @author 俊哥
 *
 */
public class BaseObjectIdEntity extends BaseEntity {

	private static final long serialVersionUID = -6571236456844690257L;
	
	@Id
	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if(this.getId()==null){
			return false;
		}
		if (obj.getClass().equals(this.getClass()) && (obj instanceof BaseLongIdEntity)) {
			BaseObjectIdEntity o = (BaseObjectIdEntity)obj;
			if(o.getId()==null){
				return false;
			}
			return o.getId().equals(this.id);
		}
		return false;
	}
}
