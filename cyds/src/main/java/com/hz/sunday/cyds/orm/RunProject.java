package com.hz.sunday.cyds.orm;

import java.math.BigDecimal;
import java.util.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 参赛项目表
 * @author WeiSky
 *
 */
public class RunProject extends BaseAdminEntity{

	private static final long serialVersionUID = 4449875160017884536L;
	
	private String groupName;//组别名称
	private String trade;//所属行业
	private String goal;//参赛目的
	private String teamName;//团队名称
	private String teamType;//团队类型
	private String leader;//负责人
	private String area;//所属西安市行政区域
	private String recomOrgan;//推荐机构
	private String projectName;//参赛项目和名称
	private String leave;//项目阶段
	private String regisNum;//企业营业执照注册号
	private String organCode;//企业组织机构代码号
	private BigDecimal regisCapital;//企业注册资本
	private BigDecimal incomeCapital;//企业实收资本
	private Date enteBuildTime;//企业成立时间
	private BigDecimal income;//某一年的营业收入
	private String regisAddre;//注册地址
	private String workAddre;//办公地址
	private Date teamBuildTime;//团队成立时间
	private Date estiBuildComp;//拟公司成立时间
	private BigDecimal score;//分数
	private String fruit;//结果: 是否通过审核
	private Long oneCheckId;//初赛审核人id
	private Long twoCheckId;//复赛审核人id
	private Long threeCheckId;//决赛审核人id
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRecomOrgan() {
		return recomOrgan;
	}
	public void setRecomOrgan(String recomOrgan) {
		this.recomOrgan = recomOrgan;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public String getRegisNum() {
		return regisNum;
	}
	public void setRegisNum(String regisNum) {
		this.regisNum = regisNum;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public BigDecimal getRegisCapital() {
		return regisCapital;
	}
	public void setRegisCapital(BigDecimal regisCapital) {
		this.regisCapital = regisCapital;
	}
	public BigDecimal getIncomeCapital() {
		return incomeCapital;
	}
	public void setIncomeCapital(BigDecimal incomeCapital) {
		this.incomeCapital = incomeCapital;
	}
	public Date getEnteBuildTime() {
		return enteBuildTime;
	}
	public void setEnteBuildTime(Date enteBuildTime) {
		this.enteBuildTime = enteBuildTime;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public String getRegisAddre() {
		return regisAddre;
	}
	public void setRegisAddre(String regisAddre) {
		this.regisAddre = regisAddre;
	}
	public String getWorkAddre() {
		return workAddre;
	}
	public void setWorkAddre(String workAddre) {
		this.workAddre = workAddre;
	}
	public Date getTeamBuildTime() {
		return teamBuildTime;
	}
	public void setTeamBuildTime(Date teamBuildTime) {
		this.teamBuildTime = teamBuildTime;
	}
	public Date getEstiBuildComp() {
		return estiBuildComp;
	}
	public void setEstiBuildComp(Date estiBuildComp) {
		this.estiBuildComp = estiBuildComp;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getFruit() {
		return fruit;
	}
	public void setFruit(String fruit) {
		this.fruit = fruit;
	}
	public Long getOneCheckId() {
		return oneCheckId;
	}
	public void setOneCheckId(Long oneCheckId) {
		this.oneCheckId = oneCheckId;
	}
	public Long getTwoCheckId() {
		return twoCheckId;
	}
	public void setTwoCheckId(Long twoCheckId) {
		this.twoCheckId = twoCheckId;
	}
	public Long getThreeCheckId() {
		return threeCheckId;
	}
	public void setThreeCheckId(Long threeCheckId) {
		this.threeCheckId = threeCheckId;
	}
	
}
