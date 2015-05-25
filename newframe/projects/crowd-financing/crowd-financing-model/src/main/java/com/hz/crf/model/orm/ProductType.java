package com.hz.crf.model.orm;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 众筹项目的分类
 * @author weisky
 *
 * May 28, 2015
 */
@Entity(value = "ProductType", noClassnameStored = true)
public class ProductType extends BaseLongIdEntity{
	
	private static final long serialVersionUID = -6458548722367172535L;
	
	private String name;//类型名称
	private String bigLogo;//大图
	private String smallLogo;//小图
	private String city;//城市
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBigLogo() {
		return bigLogo;
	}
	public void setBigLogo(String bigLogo) {
		this.bigLogo = bigLogo;
	}
	public String getSmallLogo() {
		return smallLogo;
	}
	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
