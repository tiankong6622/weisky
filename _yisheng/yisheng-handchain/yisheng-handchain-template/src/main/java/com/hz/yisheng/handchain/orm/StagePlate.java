package com.hz.yisheng.handchain.orm;
import java.util.List;


/**
 * 系统菜单
 * @author chengxingju
 *
 */
public class StagePlate {
	private Long id;
	private String name;//菜单名称
	private Long _parentId = 0L;//用于jQuery easyui中treegird的父子级别表示
	private Long parentId;
	private String plate;
	private Integer sort;
	private List<StagePlate> plateList;
	
	private String path; //二级栏目图片
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<StagePlate> getPlateList() {
		return plateList;
	}
	public void setPlateList(List<StagePlate> plateList) {
		this.plateList = plateList;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
