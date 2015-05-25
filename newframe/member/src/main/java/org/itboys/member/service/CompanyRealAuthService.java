package org.itboys.member.service;

import javax.annotation.Resource;

import org.itboys.member.entity.mongo.CompanyRealAuth;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.itboys.member.entity.mongo.CompanyRealAuth;
/**
 * 企业实名认证相关
 * @author ChenJunhui
 *
 */
@Service
public class CompanyRealAuthService extends BaseMemberService<CompanyRealAuth, Long>{
	
	@Resource(name="memberDS")
	private MongoDataSource memberMongoDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberMongoDataSource;
	}
	@Override
	protected Class<CompanyRealAuth> getEntityClass()
	{
		// TODO Auto-generated method stub
		return CompanyRealAuth.class;
	}
	
	
	public CompanyRealAuth findCompanyAuth(Long memberId){
		CompanyRealAuth companyRealAuth=getByField("memberId", memberId);
		return companyRealAuth;
	}
	
	public void insert(CompanyRealAuth companyRealAuth){
		insert(companyRealAuth);
	}

	public void  update(CompanyRealAuth companyRealAuth){
		update(companyRealAuth);
	}
	
	

	
}
