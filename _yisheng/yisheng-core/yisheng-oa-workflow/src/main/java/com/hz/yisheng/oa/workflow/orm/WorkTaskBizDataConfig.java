package com.hz.yisheng.oa.workflow.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 工单业务数据 配置表
 * 
 * @author WeiSky
 * 
 */
public class WorkTaskBizDataConfig extends BaseAdminEntity {

	private static final long serialVersionUID = -4056661935244433395L;
	
	/** 实体名称 */
	private String dataObjType;
	/** y:关联单个对象 n:关联多个对象 是list的 */
	private String isSignle;
	/** 处理层BO */
	private String dealBean;
	/**
	 * 获取对象的方法 如果is_single是y 那么方法应该是 deal_bean的bean_method方法 该方法 只有一个参数 是long的
	 * 如果is_single是n的话 deal_bean的bean_method方法 该方法 只有一个参数 List<Long>
	 */
	private String beanMethod;
	/** java bean对应的流程模块名称  **/
	private String dataObjTypeName;

	public String getDataObjTypeName() {
		return dataObjTypeName;
	}

	public void setDataObjTypeName(String dataObjTypeName) {
		this.dataObjTypeName = dataObjTypeName;
	}

	public String getDataObjType() {
		return dataObjType;
	}

	public void setDataObjType(String dataObjType) {
		this.dataObjType = dataObjType;
	}

	public String getIsSignle() {
		return isSignle;
	}

	public void setIsSignle(String isSignle) {
		this.isSignle = isSignle;
	}

	public String getDealBean() {
		return dealBean;
	}

	public void setDealBean(String dealBean) {
		this.dealBean = dealBean;
	}

	public String getBeanMethod() {
		return beanMethod;
	}

	public void setBeanMethod(String beanMethod) {
		this.beanMethod = beanMethod;
	}

}
