package org.itboys.member.dto;

import org.itboys.member.entity.mongo.Member;
import org.itboys.member.entity.mongo.MemberComment;

public class MemberCommentDTO {
	private MemberComment memberComment;
	private Member member;
	public MemberComment getMemberComment() {
		return memberComment;
	}
	public void setMemberComment(MemberComment memberComment) {
		this.memberComment = memberComment;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
