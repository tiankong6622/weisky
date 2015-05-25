/*package org.itboys.member.service;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.member.dto.MemberDTO;
import org.itboys.member.entity.mongo.Member;
import org.itboys.member.entity.mongo.MemberExt;
import org.itboys.member.entity.mongo.MemberRealAuth;
import org.itboys.member.entity.mysql.MemberData;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Lists;


public class MemberDTOService{
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberExtService memberExtService;
	@Autowired
	private MemberDataService memberDataService;
	@Autowired
	private MemberRelAuthService memberRelAuthService;
	@Autowired
	private CompanyRealAuthService companyRealAuthService;
	@Autowired
	private MemberGradeService memberGradeService;
	@Autowired
	private MemberAccountsService memberAccountsService;
	@Autowired
	private MemberFavoriteService memberFavoriteService;
	@Autowired
	private MemberMallDataService memberMallDataService;
	@Autowired
	private MemberScoreService memberScoreService;
	
	public List<MemberDTO> list(HttpServletRequest request){
		Page<Member> page = memberService.pageQuery_framwork(request);
		List<Member> list = page.getResult();
		for(Member member : list){
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setMember(member);
		}
		return null;
	}
	private MemberDTO prepareMemberDTO(Member member, String... needObjectName) {
		MemberDTO memberDTO =new MemberDTO();
		memberDTO.setMember(member);
		if (ArrayUtils.contains(needObjectName, MemberDTO.EXT)) {
			memberDTO.setMemberExts(memberExtService.getByMemberId(memberDTO.getMember().getId()));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_DATA)) {
			memberDTO.setMemberData(memberDataService.findByMemberId(memberDTO.getMember().getId()));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_REAL_AUTH)) {
			memberDTO.setMemberRealAuth(memberRelAuthService.findRealAuth(memberDTO.getMember().getId()));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.COMPANY_REALAUTH)) {
			memberDTO.setCompanyRealAuth(companyRealAuthService.findCompanyAuth(memberDTO.getMember().getId()));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_GRADE)) {// 根据会员的level的到等级
			memberDTO.setMemberGrade(memberGradeService.getById(Long.valueOf(memberDTO.getMember().getLevel())));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_ACCOUNTS)) {// 根据会员的id获取交易记录
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("memberId", memberDTO.getMember().getId());
			memberDTO.setMemberAccounts(memberAccountsService.list(map));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_FAVORITES)) {// 根据会员的id获取收藏夹
			
			memberDTO.setMemberFavorites(memberFavoriteService.getByMemberId(memberDTO.getMember().getId()));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_MALLDATA)) {// 根据会员的id获取商城信息
			
			memberDTO.setMemberMallData(memberMallDataService.getByMemberId(memberDTO.getMember().getId()));
		}
		if (ArrayUtils.contains(needObjectName, MemberDTO.MEMBER_SCORES)) {// 根据会员的id获取会员的积分明细
			
			memberDTO.setMemberScores(memberScoreService.getByMemberId(memberDTO.getMember().getId()));
		}
		
		return memberDTO;
	}
	
	
	
	
	*//**
	 * 分页查询会员信息并组装DTO对象 如果需要获得 会员的其他数据对象 扩展信息 认证信息 三方账号信息 请自行选择 needObjectName
	 * 的值
	 * 
	 * @param page
	 * @param sqlMap
	 * @param needObjectName
	 * @return
	 *//*
	public Page<MemberDTO> pageQueryDTO(final Map<String, Object> sqlMap, final String... needObjectName) {
		Page<MemberDTO> page=PageQueryUtils.pageQuery(sqlMap, new PageQuery<MemberDTO>() {
		
			private Query<Member> getquery(){
				Iterator<String> ite = sqlMap.keySet().iterator();
				Query<Member> query = memberService.getMongoDataSource().createQuery(memberService.getEntityClass());
				while (ite.hasNext()) {
					String key = ite.next();
					Object value = sqlMap.get(key);
					query.filter(key, value);
				}
				return query;
			}
			final Query<Member> query=getquery();
			final long count = memberService.getMongoDataSource().getCount(query);
			public long count() {
				return count;
			}
			public List<MemberDTO> list() {
				List<MemberDTO> mlist = Lists.newArrayList();
				List<Member> list = query.asList();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						MemberDTO dto = prepareMemberDTO(list.get(i), needObjectName);
						mlist.add(dto);
					}
				}
				return mlist;
			}
		});
		return page;
	}
	*//**
	 * 创建用户
	 * 
	 * @param member
	 * @param memberExts
	 * @param memberData
	 **//*
	public void create(Member member, List<MemberExt> memberExts, MemberData memberData) {
		Long memberId=memberService.create(member);
		if (memberExts != null && !memberExts.isEmpty()) {
			for (MemberExt ext : memberExts) {
				ext.setMemberId(memberId);
			}
			memberExtService.insert(memberExts);
		}
		if (memberData != null) {
			memberData.setMemberId(memberId);
			memberDataService.insert(memberData);
		}

	}
	*//**
	 * 创建用户
	 * 
	 * @param member
	 * @param memberExts
	 * @param memberData
	 **//*
	public void create(Member member, List<MemberExt> memberExts, MemberData memberData, MemberRealAuth memberRealAuth) {
		create(member, memberExts, memberData);
		memberRealAuth.setMemberId(member.getId());
		memberRelAuthService.save(memberRealAuth);
	}
}*/