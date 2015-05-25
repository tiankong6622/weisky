package org.itboys.member.entity.mysql;

import java.math.BigDecimal;
import java.util.Date;

import org.itboys.commons.CommonConstants;
import org.itboys.member.entity.BaseEntity;

/**
 * 资金进出记录信息
 * @author huml
 *
 */
public class MemberAccount extends BaseEntity{

	private static final long serialVersionUID = -7421055245376216424L;
	public static final String MEMBER_ID = "memberId";
	
	private Long memberId;
	private BigDecimal amount = CommonConstants.ZERO; // 金额
	private Integer inorout;//是进还是出帐 1 出 0 进帐
	private Date createTime;
	private Long objId; // 是由哪些对象消费导致的 比如订单 什么的 具体的有type定
	private Integer objType;//关联哪种对象 1订单 2转账 等等
	private String remark;//备注
	private BigDecimal remainAmount = CommonConstants.ZERO;//那一瞬间余额
	
	private Object obj;
	
	public static Integer OUT =1;
	public static Integer IN =0;
	public static Integer OBJTYPE_ORDER =1;//订单消费
	public static Integer OBJTYPE_CREDIT =2;//设置信用额 添加/减少

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Integer getInorout() {
		return inorout;
	}

	public void setInorout(Integer inorout) {
		this.inorout = inorout;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(BigDecimal remainAmount) {
		this.remainAmount = remainAmount;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	
	

}
