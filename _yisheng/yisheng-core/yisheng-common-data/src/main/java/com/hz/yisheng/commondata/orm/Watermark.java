package com.hz.yisheng.commondata.orm;

import java.math.BigDecimal;

import org.javafans.orm.entity.base.BaseEntity;

/**
 * 水印配置相关
 * @author ChenJunhui
 *
 */
public class Watermark extends BaseEntity {

	private static final long serialVersionUID = -9056272327176117664L;
	public static final int TYPE_IMG=1;//水印图片
	public static final int TYPE_WORD=0;//水印文字
	
	public static final int STATUS_YES=1;
	public static final int STATUS_NO=2;

	private String name;	//水印管理名称
	private Integer type;//1:加水印图片 0:加水印文字
	private String watermark;//if type = 1 then 水印图片上传文件的地址 else if type =0 then 存储水印文字
	private Integer x;//水印图片距离左边的距离 默认负数 如果x<0, 视flag定
	private Integer y;//水印图片距离顶部的距离 默认负数 如果y<0, 视flag定
	private Integer flag ;//1:x<0 或 y<0 左上角 2:x<0 或 y<0 右下角 3:x<0 或 y<0 中间 4:必须有
	private BigDecimal alpha;//水印透明度 decimal (3,2)0-1之间的小数
	private int fontStyle;//字体样式 下拉框选择
	private int fontSize;//字体大小 下拉框选择
	private String color;//三色像素 _ 拼接存储 比如 255_255_0
	private Long projectId;//大项目为0 否则为项目ID
	private Long objId;//特殊项目的时候 可能会关联到商家自定义水印信息 一般不需要的项目 不管这个字段 搞0就可以了
	private String fontName;//字体名称 
	private Integer status;//有效状态
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getWatermark() {
		return watermark;
	}
	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public BigDecimal getAlpha() {
		return alpha;
	}
	public void setAlpha(BigDecimal alpha) {
		this.alpha = alpha;
	}
	public int getFontStyle() {
		return fontStyle;
	}
	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
}