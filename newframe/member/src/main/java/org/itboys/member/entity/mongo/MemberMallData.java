package org.itboys.member.entity.mongo;

import java.io.Serializable;
import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 商城会员用户信息
 * @author huml
 *
 */
@Entity(value = "memberMallData", noClassnameStored = true)
public class MemberMallData extends MemberBaseEntity {

	private static final long serialVersionUID = -1156595127888232091L;
	private Long memberId;
	private Date lastBuyTime;
	private float buyMoney;
	private Integer buyedCount;
	private float amount;
	private float amount1;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getLastBuyTime() {
		return lastBuyTime;
	}

	public void setLastBuyTime(Date lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}

	public float getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(float buyMoney) {
		this.buyMoney = buyMoney;
	}

	public Integer getBuyedCount() {
		return buyedCount;
	}

	public void setBuyedCount(Integer buyedCount) {
		this.buyedCount = buyedCount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getAmount1() {
		return amount1;
	}

	public void setAmount1(float amount1) {
		this.amount1 = amount1;
	}

}
