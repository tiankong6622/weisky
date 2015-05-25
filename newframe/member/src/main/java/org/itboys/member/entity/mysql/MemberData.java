package org.itboys.member.entity.mysql;

import java.io.Serializable;
import java.math.BigDecimal;

import org.itboys.commons.CommonConstants;

/**
 * 企业会员的各种数据信息 如信用额 积分 。。
 * @author Liuguanjun
 *
 */
public class MemberData implements Serializable {

	private static final long serialVersionUID = -7421055245376216424L;
	
	public static final String MEMBER_ID = "memberId";
	
	/********************
	 * 大卖场会员 
	 * amount:信用余额
	 * amount1:已购买总金额
	 * amount2:已支付的总金额
	 * amount3:已支付的信用总金额
	 * amount4:已支付的现金总金额
	 */
	private Long memberId; //用户的ID
	private BigDecimal amount = CommonConstants.ZERO; 
	private BigDecimal amount1 = CommonConstants.ZERO; 
	private BigDecimal amount2 = CommonConstants.ZERO; 
	private BigDecimal amount3 = CommonConstants.ZERO; 
	private BigDecimal amount4 = CommonConstants.ZERO; 
	private Integer buyCount = 0;//成功购买次数
	private Integer count1 = 0; // 备用 如积分
	private Integer count2 = 0; // 备用
	private Integer count3 = 0; // 备用
	private Integer count4 = 0; // 备用
	
	
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
	public BigDecimal getAmount1() {
		return amount1;
	}
	public void setAmount1(BigDecimal amount1) {
		this.amount1 = amount1;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public BigDecimal getAmount2() {
		return amount2;
	}
	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount2;
	}
	public BigDecimal getAmount3() {
		return amount3;
	}
	public void setAmount3(BigDecimal amount3) {
		this.amount3 = amount3;
	}
	public BigDecimal getAmount4() {
		return amount4;
	}
	public void setAmount4(BigDecimal amount4) {
		this.amount4 = amount4;
	}
	public Integer getCount1() {
		return count1;
	}
	public void setCount1(Integer count1) {
		this.count1 = count1;
	}
	public Integer getCount2() {
		return count2;
	}
	public void setCount2(Integer count2) {
		this.count2 = count2;
	}
	public Integer getCount3() {
		return count3;
	}
	public void setCount3(Integer count3) {
		this.count3 = count3;
	}
	public Integer getCount4() {
		return count4;
	}
	public void setCount4(Integer count4) {
		this.count4 = count4;
	}
	
	
	

}
