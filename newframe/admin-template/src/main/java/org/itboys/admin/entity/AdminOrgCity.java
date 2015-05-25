package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;
/**
 * 城市关联对象表
 * @author huml
 *
 */
@Entity(value="AdminOrgCity", noClassnameStored = true)
public class AdminOrgCity extends BaseAdminEntity{
	private static final long serialVersionUID = -8569025903282005492L;
	public static final Integer TYPE_O=1;//关联组织
	public static final Integer TYPE_U=2;//关联用户
	
	private Long objId;
	private Integer type;
	private Long cityId;
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}
