package com.hz.yisheng.nosql.mongodb.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;

/**
 * 自增主键实体类
 * 
 * @author WeiSky
 *
 */
@Entity(value = "EntityId", noClassnameStored = true)
public class EntityIdDB extends BaseObjectIdEntity{

	private static final long serialVersionUID = 3319259434653908928L;

	@Indexed(unique=true,dropDups=true)
	private String name;
	
	private long currentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCurrentId() {
		return currentId;
	}

	public void setCurrentId(long currentId) {
		this.currentId = currentId;
	}
	
}
