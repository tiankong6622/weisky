package org.itboys.member.entity.mongo;

import java.math.BigDecimal;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;

/**
 * 会员实体类
 * 
 * @author huml
 * 
 */
@Entity(value = "member", noClassnameStored = true)
public class Member extends MemberBaseEntity {

	private static final long serialVersionUID = 1193737885381425680L;
	
	public final static String USER_NAME = "userName";
	public final static String NAME = "name";
	public final static String EMAIL = "email";
	public final static String NICKNAME = "nickname";
	public final static String MOBILE = "mobile";

	public final static Long DEFAULT_PID = 0L;// 默认porjectID

	public static final Integer RECOMMEND_YES = 2;// 推荐
	public static final Integer RECOMMEND_NO = 1;// 不推荐
	public static final Integer SEX_MALE=1;
	public static final Integer SEX_FEMALE=2;
	public static final Integer SEX_NONE=0;
	//private Long id;
	private Long projectId;
	private String userName;
	private String password;
	private String email;

	private String name;// 真实姓名
	private String nickname;// 昵称
	private Long recommendId;//推荐人id

	private String mobile;
	private String phone;
	private String fax;

	private String loginip;
	private Date loginTime;
	private Date regTime;

	private Integer type;// 类型
	private Integer status;// 状态
	private Long level;// 等级 系统自行定义
	private String levelName;//等级名称
	private Long score;// 积分
	private String logo;// 头像
	private Long provinceId;
	private Long districtId;
	private Long cityId;
	private String code;// 邮编
	private String address;// 地址
	private Integer sex;// 性别 
	private Date birthday;// 生日
	private String partNo;
	private Integer isRecommend;
	private BigDecimal amount;
	private String minTotalPrice;
	private String signature;
	private String openfireName;
	private String openfirePwd;
	private Integer isDeleted=0;
	
	private int isView;
	
	
	public int getIsView() {
		return isView;
	}

	public void setIsView(int isView) {
		this.isView = isView;
	}

	public String getOpenfireName() {
		return openfireName;
	}

	public void setOpenfireName(String openfireName) {
		this.openfireName = openfireName;
	}

	public String getOpenfirePwd() {
		return openfirePwd;
	}

	public void setOpenfirePwd(String openfirePwd) {
		this.openfirePwd = openfirePwd;
	}

	public String getMinTotalPrice() {
		return minTotalPrice;
	}

	public void setMinTotalPrice(String minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Long recommendId) {
		this.recommendId = recommendId;
	}

	public String getSignature()
	{
		return signature;
	}

	public void setSignature(String signature)
	{
		this.signature = signature;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
