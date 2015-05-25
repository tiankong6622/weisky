package org.itboys.admin.entity;

import java.util.Date;
import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
@Entity(value = "AdminUser", noClassnameStored = true)
public class AdminUser extends BaseAdminEntity{

	private static final long serialVersionUID = -662046340520304499L;
	public static final Integer TYPE_SUPER = 1;// 超级用户
	public static final Integer TYPE_COMMON = 0;// 普通用户
	/**
	 * 用户拥有的角色 每次分配重新set
	 */
	private List<Long> roleIds;
	
	/**
	 * 用户的职务ID 没职务的话 该ID为0L
	 */
	private long postId;
	
	/**
	 * 用户的组织 部门ID 没部门的话 改ID为0L
	 */
	private long orgId;
	
	/**
	 * 用户名加唯一索引
	 */
	@Indexed(unique=true)
	private String username;
	
	private String password;
	
	private String name;//真是姓名
	
	private String nickName;//昵称
	
	private String mobi;//电话
	
	private String enamil;//邮箱
	
	private String loginIp;//最后登入IP
	
	private long loginTime;//最后登入时间
	
	private Integer status;//状态 1有效 0冻结
	
	private Integer sex;//性别:1.男 2.女
	
	private Date birthday;//生日
	
	private String area;//地区  省市县ID的拼装  格式： 省-市-县
	
	private String bigLogo;//大头像
	
	private String middleLogo;//小头像
	
	private String smallLogo;//最小头像
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除
	
	private Integer type=0;
	
	private Long provinceId;//省
	private Long districtId;//区县
	private Long cityId;//市
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBigLogo() {
		return bigLogo;
	}

	public void setBigLogo(String bigLogo) {
		this.bigLogo = bigLogo;
	}

	public String getMiddleLogo() {
		return middleLogo;
	}

	public void setMiddleLogo(String middleLogo) {
		this.middleLogo = middleLogo;
	}

	public String getSmallLogo() {
		return smallLogo;
	}

	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}

	public String getNickName() {
		return nickName;
	}

	public String getMobi() {
		return mobi;
	}

	public void setMobi(String mobi) {
		this.mobi = mobi;
	}

	public String getEnamil() {
		return enamil;
	}

	public void setEnamil(String enamil) {
		this.enamil = enamil;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
}
