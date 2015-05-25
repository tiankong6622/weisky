package org.itboys.member.service;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.MemberThirdAccount;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

@Service
public class MemberThirdAccountService extends BaseMemberService<MemberThirdAccount, Long> {
	@Resource(name="memberDS")
	private MongoDataSource memberThirdAccountDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberThirdAccountDataSource;
	}

	@Override
	protected Class<MemberThirdAccount> getEntityClass() {
		return MemberThirdAccount.class;
	}
	

}
