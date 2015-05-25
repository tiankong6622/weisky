package org.itboys.member.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.member.dto.MemberDTO;
import org.itboys.member.entity.mongo.Member;
import org.itboys.member.entity.mongo.MemberExt;
import org.itboys.member.entity.mongo.MemberRealAuth;
import org.itboys.member.entity.mysql.MemberData;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Lists;

@Service
public class MemberService extends BaseMemberService<Member, Long> {
	
	@Resource(name="memberDS")
	private MongoDataSource memberMongoDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberMongoDataSource;
	}

	@Override
	protected Class<Member> getEntityClass() {
		return Member.class;
	}
	

	
	
	public Member findByUserName(String userName) {
		return getByField("userName", userName.toLowerCase());
	}

	public List<Member> findByNameAndProject(String userName, Long projectId) {
		return memberMongoDataSource.createQuery(getEntityClass()).filter("userName", userName).filter("projectId", projectId).asList();
	}


	public Member findByEmail(String email) {
		return getByField("email", email);
	}

	public Member findByMobile(String mobile) {
		return getByField("mobile", mobile);
	}

	public Member findByNickname(String nickname) {
		return getByField("nickname",nickname);
	}
/////////////////////////////

	
	
	/**
	 * 创建用户
	 * 
	 * @param member
	 */
	//@Transactional
	public Long create(Member member) {
		member.setPassword(DigestUtils.md5Hex(member.getPassword()));// 密码md5加密
		member.setUserName(member.getUserName().toLowerCase());// 用户名转小写
		member.setRegTime(new Date());
		save(member);
		Long memberId = member.getId();
		return memberId;
	}
	
	

}
