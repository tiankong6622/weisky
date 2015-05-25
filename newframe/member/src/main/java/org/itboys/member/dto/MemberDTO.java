package org.itboys.member.dto;

import java.util.List;

import org.itboys.member.entity.mongo.CompanyRealAuth;
import org.itboys.member.entity.mongo.Member;
import org.itboys.member.entity.mongo.MemberExt;
import org.itboys.member.entity.mongo.MemberFavorite;
import org.itboys.member.entity.mongo.MemberGrade;
import org.itboys.member.entity.mongo.MemberMallData;
import org.itboys.member.entity.mongo.MemberRealAuth;
import org.itboys.member.entity.mongo.MemberScore;
import org.itboys.member.entity.mongo.MemberSnsOper;
import org.itboys.member.entity.mongo.MemberThirdAccount;
import org.itboys.member.entity.mysql.MemberAccount;
import org.itboys.member.entity.mysql.MemberData;
/**
 * 会员相关信息组装的实体类
 * @author huml
 *
 */
public class MemberDTO {
	public static final String MEMBER="member";
	public static final String EXT="ext";
	public static final String MEMBER_DATA="data";
	public static final String MEMBER_REAL_AUTH="memberRealAuth";
	public static final String COMPANY_REALAUTH="companyRealAuth";
	public static final String THIRD_ACCOUNT="thirdAccount";
	public static final String 	MEMBER_GRADE="memberGrade";
	
	public static final String MEMBER_ACCOUNTS="memberAccounts";
	public static final String MEMBER_FAVORITES="memberFavorites";
	public static final String MEMBER_MALLDATA="memberMallData";
	public static final String MEMBER_SCORES="memberScores";
	public static final String MEMBER_SNSOPERS="memberSnsOpers";
	
	private Member member;
	private List<MemberAccount> memberAccounts;
	private MemberData memberData;
	private List<MemberExt> memberExts;
	private List<MemberFavorite> memberFavorites;
	private MemberMallData memberMallData;
	private MemberRealAuth memberRealAuth;
	private List<MemberScore> memberScores;
	private List<MemberSnsOper> memberSnsOpers;
	private List<MemberThirdAccount> memberThirdAccounts;
	private CompanyRealAuth companyRealAuth;
	private MemberGrade memberGrade;
	
	public MemberGrade getMemberGrade()
	{
		return memberGrade;
	}
	public void setMemberGrade(MemberGrade memberGrade)
	{
		this.memberGrade = memberGrade;
	}
	public CompanyRealAuth getCompanyRealAuth()
	{
		return companyRealAuth;
	}
	public void setCompanyRealAuth(CompanyRealAuth companyRealAuth)
	{
		this.companyRealAuth = companyRealAuth;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<MemberAccount> getMemberAccounts() {
		return memberAccounts;
	}
	public void setMemberAccounts(List<MemberAccount> memberAccounts) {
		this.memberAccounts = memberAccounts;
	}
	public MemberData getMemberData() {
		return memberData;
	}
	public void setMemberData(MemberData memberData) {
		this.memberData = memberData;
	}
	public List<MemberExt> getMemberExts() {
		return memberExts;
	}
	public void setMemberExts(List<MemberExt> memberExts) {
		this.memberExts = memberExts;
	}
	public List<MemberFavorite> getMemberFavorites() {
		return memberFavorites;
	}
	public void setMemberFavorites(List<MemberFavorite> memberFavorites) {
		this.memberFavorites = memberFavorites;
	}
	public MemberMallData getMemberMallData() {
		return memberMallData;
	}
	public void setMemberMallData(MemberMallData memberMallData) {
		this.memberMallData = memberMallData;
	}
	public MemberRealAuth getMemberRealAuth() {
		return memberRealAuth;
	}
	public void setMemberRealAuth(MemberRealAuth memberRealAuth) {
		this.memberRealAuth = memberRealAuth;
	}
	public List<MemberScore> getMemberScores() {
		return memberScores;
	}
	public void setMemberScores(List<MemberScore> memberScores) {
		this.memberScores = memberScores;
	}
	public List<MemberSnsOper> getMemberSnsOpers() {
		return memberSnsOpers;
	}
	public void setMemberSnsOpers(List<MemberSnsOper> memberSnsOpers) {
		this.memberSnsOpers = memberSnsOpers;
	}
	public List<MemberThirdAccount> getMemberThirdAccounts() {
		return memberThirdAccounts;
	}
	public void setMemberThirdAccounts(List<MemberThirdAccount> memberThirdAccounts) {
		this.memberThirdAccounts = memberThirdAccounts;
	}
	
	
}
