package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

public class Trajectory implements Serializable{

	private static final long serialVersionUID = 193809554773615421L;
	
	private Long id;       
	private Long customerId;  //婴儿的ID
	private double coordX; //x坐标
	private double coordY;  //y坐标
	private double coordZ;  //z坐标
	
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	private int isDeleted; //删除标记
	
	private String name; //姓名
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public double getCoordX() {
		return coordX;
	}
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}
	public double getCoordY() {
		return coordY;
	}
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
	public double getCoordZ() {
		return coordZ;
	}
	public void setCoordZ(double coordZ) {
		this.coordZ = coordZ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
