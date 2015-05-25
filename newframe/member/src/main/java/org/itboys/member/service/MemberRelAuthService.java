package org.itboys.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.commons.dto.ResultDO;
import org.itboys.member.entity.mongo.MemberRealAuth;
import org.itboys.mongodb.core.MongoDataSource;



public class MemberRelAuthService extends BaseMemberService<MemberRealAuth, Long> {
	@Resource(name="memberDS")
	private MongoDataSource memberrelAuthDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberrelAuthDataSource;
	}

	@Override
	protected Class<MemberRealAuth> getEntityClass() {
		return MemberRealAuth.class;
	}
	public MemberRealAuth findRealAuth(Long memberId){
		MemberRealAuth memberRealAuth=getByField("memberId", memberId);
		return memberRealAuth;
	}
	
	
	
	public void update(MemberRealAuth memberRealAuth){
		 update(memberRealAuth);
	}
	
	public boolean delete(Long id){
		
		return delete(id);
	}
}
