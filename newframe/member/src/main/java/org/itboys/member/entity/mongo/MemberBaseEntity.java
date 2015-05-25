package org.itboys.member.entity.mongo;

import org.itboys.mongodb.entity.BaseEntity;
import org.itboys.mongodb.entity.BaseLongIdEntity;

public class MemberBaseEntity extends BaseLongIdEntity {

	private static final long serialVersionUID = 164165165414684L;
	
	private Long creator;
	private Long updater;
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
