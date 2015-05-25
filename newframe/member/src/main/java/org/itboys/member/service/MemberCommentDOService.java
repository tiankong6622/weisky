package org.itboys.member.service;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberCommentDO;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

@Service
public class MemberCommentDOService extends BaseMemberService<MemberCommentDO, Long>{
	@Resource
	private MongoDataSource memberCommentDODataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberCommentDODataSource;
	}

	@Override
	protected Class<MemberCommentDO> getEntityClass() {
		return MemberCommentDO.class;
	}

}
