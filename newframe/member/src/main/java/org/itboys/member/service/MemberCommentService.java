package org.itboys.member.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.itboys.member.dto.MemberCommentDTO;
import org.itboys.member.entity.mongo.MemberComment;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;

@Service
public class MemberCommentService extends BaseService<MemberComment, Long> {
	@Autowired
	private MemberService memberService;
	@Resource
	private MongoDataSource memberCommentDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return memberCommentDataSource;
	}

	@Override
	protected Class<MemberComment> getEntityClass() {
		return MemberComment.class;
	}
	
	public void saveMemberComment(MemberComment memberComment){
		memberComment.setComTime(new Date());
		memberComment.setCt(System.currentTimeMillis());
		memberComment.setUt(System.currentTimeMillis());
		save(memberComment);
	}
	
	public void updateMemberComment(MemberComment memberComment){
		memberComment.setUt(System.currentTimeMillis());
		update(memberComment);
	}
	/**
	 * 查询某个会员的评论 
	 * @param memberId
	 * @return
	 */
	public List<MemberComment> getByMemberId(Long memberId){
		return findByField("memberId", memberId);
	}
	/**
	 * 查询某个对象的评论
	 * @param objectId
	 * @return
	 */
	public List<MemberComment> getByObjectId(Long objectId){
		return findByField("objectId", objectId);
	}
	
	/**
	 * 带分页的查询 comment包含会员信息
	 * @param param
	 * @param page
	 * @return
	 */
	public Page<MemberCommentDTO> pageDTOQuery(HttpServletRequest request){
		Page<MemberComment> page = pageQuery(request);
		page.getData();
		List<MemberCommentDTO> dtos = new ArrayList<MemberCommentDTO>();
		for(MemberComment comment : page.getData()){
			MemberCommentDTO dto = new MemberCommentDTO();
			dto.setMemberComment(comment);
			dto.setMember(memberService.getById(comment.getMemberId()));
			dtos.add(dto);
		}
		return new Page<>(page.getTotal(), dtos);
	}
	
}
