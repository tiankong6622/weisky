package com.hz.yisheng.nosql.mongodb.entity;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;

/**
 * ID为mongodb ObjectId类型的父类
 * @author WeiSky
 *
 */
public class BaseObjectIdEntity extends BaseEntity{

	private static final long serialVersionUID = -2849028937405158683L;
	
	@Id
	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
