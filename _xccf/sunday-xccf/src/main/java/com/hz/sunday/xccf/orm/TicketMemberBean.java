package com.hz.sunday.xccf.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 报名管理
 * 
 * @author huanglei
 * @date 2015年4月14日
 * @version V1.0
 */
public class TicketMemberBean extends BaseAdminEntity {

	private static final long serialVersionUID = 1L;

	/** 姓名 */
	private String name;
	/** 公司 */
	private String company;
	/** 职务 */
	private String post;
	/** 手机号 */
	private String phone;
	/** 意向 */
	private String goal;
	/** 人数*/
	private Integer number;
	/** 预留字段 */
	private String make;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

}
