package org.itboys.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberScore;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MemberScoreService extends BaseService<MemberScore, Long> {
	
	@Resource(name="memberDS")
	private MongoDataSource memberScoreDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberScoreDataSource;
	}

	@Override
	protected Class<MemberScore> getEntityClass() {
		return MemberScore.class;
	}
	
	public void saveMemberScore(MemberScore memberScore){
		memberScore.setCt(System.currentTimeMillis());
		memberScore.setUt(System.currentTimeMillis());
		save(memberScore );
	}
	
	public void updateMemberScore(MemberScore memberScore){
		memberScore.setUt(System.currentTimeMillis());
		update(memberScore);
	}
	/**
	 * 查询某个会员的积分明细
	 * @param memberId
	 * @return
	 */
	public List<MemberScore> getByMemberId(Long memberId){
		return findByField("memberId", memberId);
	}
	
	
}
