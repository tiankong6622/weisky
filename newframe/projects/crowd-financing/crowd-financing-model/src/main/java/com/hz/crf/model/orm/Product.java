package com.hz.crf.model.orm;

import java.math.BigDecimal;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
import com.hz.crf.model.CrowdConstants;

/**
 * 后台编辑或者会员发起的众筹项目
 * 
 * @author weisky
 *
 * May 28, 2015
 */
@Entity(value = "Product", noClassnameStored = true)
public class Product extends BaseLongIdEntity{

	private static final long serialVersionUID = 366779832725221933L;
	
	private Long productTypeId;//项目分类的 Id   比如音乐、运动类等后台自定义的分类的ID
	private int type = CrowdConstants.Product.TYPE_ADMIN;//项目类型 默认后台编辑  1：后台编辑发布的  2：会员提交的项目 
	private String bigLogo;//大图
	private String smallLogo;//小图
	private String name;//项目发起人姓名
	private String mobi;//项目发起人联系电话
	private String goal;//筹款目标  可能有非数字字符  所以写成String型的
	private String productName;//项目名称
	private String comment;//简介或者描述
	private int support;//点赞的数量
	private int review;//评论数量
	private int sustain;//支持人数量
	private BigDecimal finish;//已筹集的金额
	private int day;//距离筹集结束  还剩下多少天
	private String city;//城市
	
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobi() {
		return mobi;
	}
	public void setMobi(String mobi) {
		this.mobi = mobi;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public int getSustain() {
		return sustain;
	}
	public void setSustain(int sustain) {
		this.sustain = sustain;
	}
	public BigDecimal getFinish() {
		return finish;
	}
	public void setFinish(BigDecimal finish) {
		this.finish = finish;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
}
