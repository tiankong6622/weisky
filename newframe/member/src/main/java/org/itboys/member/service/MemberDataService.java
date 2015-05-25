/*package org.itboys.member.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.itboys.member.dao.MemberDataMapper;
import org.itboys.member.entity.mysql.MemberData;
*//**
 * 企业会员的各种数据信息 如信用额 积分 。。
 * @author Liuguanjun
 *
 *//*
@Service
public class MemberDataService {
	
	@Autowired
	private MemberDataMapper memberDataMapper;
	
	public MemberData findByMemberId(Long memberId){
		return memberDataMapper.findByMemberId(memberId);
	}
	
	public void insert(MemberData entity){
		memberDataMapper.insert(entity);
	}
	
	public int update(MemberData entity){
		return memberDataMapper.update(entity);
	}

	public int delete(Long memberId){
		return memberDataMapper.delete(memberId);
	}
	
	public int updatePay(Long memberId,BigDecimal totalFee){
		return memberDataMapper.updatePay(memberId,totalFee);
	}
}
*/