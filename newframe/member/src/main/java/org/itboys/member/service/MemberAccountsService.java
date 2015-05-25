/*package org.itboys.member.service;

import java.util.List;
import java.util.Map;

import org.itboys.member.dao.MemberAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.itboys.member.entity.mysql.MemberAccount;
@Service
public class MemberAccountsService {

	@Autowired
	private MemberAccountMapper memberAccountMapper;
	
	public void insert(MemberAccount memberAccount){
		memberAccountMapper.insert(memberAccount); 
	}
	public void delete(Long id){
		memberAccountMapper.delete(id);
	}
	public void update(MemberAccount memberAccount){
		memberAccountMapper.update(memberAccount);
	}
	public List<MemberAccount> list(Map<String, Object> sqlMap){
		return memberAccountMapper.list(sqlMap);
	}
	public Long count(Map<String,Object> sqlMap){
		return memberAccountMapper.count(sqlMap);
	}
	
	
	
}

	
	
*/