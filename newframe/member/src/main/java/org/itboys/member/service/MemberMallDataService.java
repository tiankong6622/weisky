package org.itboys.member.service;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberMallData;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MemberMallDataService extends BaseService<MemberMallData, Long> {
	
	@Resource(name="memberDS")
	private MongoDataSource memberMallDataDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberMallDataDataSource;
	}

	@Override
	protected Class<MemberMallData> getEntityClass() {
		return MemberMallData.class;
	}
	
	public void saveMemberMallData(MemberMallData memberMallData){
		memberMallData.setCt(System.currentTimeMillis());
		memberMallData.setUt(System.currentTimeMillis());
		save(memberMallData);
	}
	
	public void updateMemberMallData(MemberMallData memberMallData){
		memberMallData.setUt(System.currentTimeMillis());
		update(memberMallData);
	}
	/**
	 * 查询某个会员的商城信息
	 * @param memberId
	 * @return
	 */
	public MemberMallData getByMemberId(Long memberId){
		return getByField("memberId", memberId);
	}
	
	
}
