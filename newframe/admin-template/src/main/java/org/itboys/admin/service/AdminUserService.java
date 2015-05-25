package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.dto.AdminUserDTO;
import org.itboys.admin.dto.AdminUserData;
import org.itboys.admin.entity.AdminOrg;
import org.itboys.admin.entity.AdminPost;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.entity.AdminUserOrgPost;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginHolder;
import org.itboys.admin.tools.WebConstants;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.encryption.Digests;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.morphia.query.UpdateOperations;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class AdminUserService extends BaseAdminService<AdminUser, Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5282238462412183908L;
	@Autowired
	private AdminOrgService adminOrgService;
	@Autowired
	private AdminUserOrgPostService adminUserOrgPostService;
	@Autowired
	private AdminPostService adminPostService;
	@Autowired
	private AdminRoleService adminRoleService;

	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminUser> getEntityClass() {
		return AdminUser.class;
	}

	public int updatePassword(long userId,String password){
		UpdateOperations<AdminUser> ops = ds.createUpdateOperations(AdminUser.class);
		ops.set("password", password);
		return update(userId, ops);
	}
	
	/**
	 * 组装登录人相关的信息
	 * @return
	 */
	public AdminUserData installAdminUserData(Long userId){
		AdminUserData aud = new AdminUserData();
		
		AdminUser adminUser = getById(userId);
		aud.setLogined(true);
		aud.setUsername(adminUser.getUsername());
		aud.setName(adminUser.getName());
		aud.setOrgId(adminUser.getOrgId());
		
		/* 获取当前登录人的组织部门信息  */
		AdminOrg ao = adminOrgService.getById(adminUser.getOrgId());
		if(ao != null){
			aud.setOrgName(ao.getName());
		}
		/* 获取当前登陆人的职务信息  */
		AdminPost ap = adminPostService.getById(adminUser.getPostId());
		if(ap != null){
			aud.setPostName(ap.getName());
		}
		return aud;
	}

	/**
	 * 根据用户名查询用户
	 * 
	 * @param id
	 * @return
	 */
	public AdminUser getAdminUser(String userName) {
		// 用户名转小写再查
		List<AdminUser> list = ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("username", userName).asList();
		return list==null||list.isEmpty()?null:list.get(0);
	}

	/**
	 * 根据用户手机号查询用户
	 * 
	 * @param moblie
	 * @return
	 */
	public AdminUser getAdminUserByMoblie(String mobile) {
		List<AdminUser> list = ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("mobile", mobile).asList();
		return list==null?null:list.get(0);
	}

	public boolean isExists(String userName) {
		List<AdminUser> list = ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("username", userName).asList();
		return list.size() > 0;
	}

	/**
	 * 根据部门编号获取用户信息
	 * 
	 * @return
	 * select u.id,u.name,u.user_name from admin_user u
		left join admin_user_org_post o on u.id = o.user_id
		<if test=" orgId != null">
			where o.org_id = #{orgId}
		</if>
	 */
	public List<AdminUser> getUserByOrgIdSimple(Long orgId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("orgId", orgId);
		List<AdminUserOrgPost> list = adminUserOrgPostService.list(param);
		List<AdminUser> users = new ArrayList<AdminUser>();
		for(AdminUserOrgPost post : list){
			users.addAll(ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("id", post.getUserId()).asList());
		}
		return users;
	}

	/**
	 * 根据ID获取管理用户信息
	 * 
	 * @param id
	 * @return
	 */
	public AdminUserDTO getUserById(Long id) {
		AdminUser user = getById(id);
		AdminUserDTO dto = new AdminUserDTO();
		if(user!=null){
			//新的页面中暂时没有部门模块,这里先不考虑
//			List<AdminUserOrgPost> userOrgPost = null;//adminUserOrgPostService.findsByUserId(user.getId());
			List<AdminUserOrgPost> userOrgPost = adminUserOrgPostService.findsByUserId(user.getId());
			dto.setAdminUser(user);
			dto.setUserOrgPost(CommonCollectionUtils.isNotEmpty(userOrgPost) ? userOrgPost.get(0) : null);
		}
		return dto;
	}

	/**
	 * 分页查询
	 * 
	 * @param id
	 * @return
	 */
	public Page<AdminUserDTO> pageQueryAll(HttpServletRequest request) {
		Page<AdminUser> page = pageQuery(request);
		List<AdminUser> list = page.getData();
		List<AdminUserDTO> dtos = new ArrayList<AdminUserDTO>();
		if (!list.isEmpty()) {
			List<Long> userIds = Lists.newArrayList();
			for (AdminUser users : list) {
				userIds.add(users.getId());
			}
			Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
			param.put("userId in", userIds);
			List<AdminUserOrgPost> userOrgPosts = adminUserOrgPostService.list(param);
			for (AdminUser au : list) {
				for (AdminUserOrgPost auop : userOrgPosts) {
					if (au.getId()==auop.getUserId()) {
						AdminUserDTO dto = new AdminUserDTO();
						dto.setAdminUser(au);
						dto.setUserOrgPost(auop);
						dtos.add(dto);
						break;
					}
				}
			}
		}
		return new Page<AdminUserDTO>(dtos, page.getPageNo(), page.getPageSize(), page.getTotal());
	}

	/**
	 * 新增一个管理后台用户
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void insert(AdminUserDTO dto) {
		AdminUser user = dto.getAdminUser();
		user.setPassword(Digests.md5(user.getPassword()));// 密码md5加密
		user.setUsername(user.getUsername().toLowerCase());// 用户名转小写
		AdminSessionHolder.prepareAdminLoginData(user);
		save(user);
		if (dto.getUserOrgPost() != null) {
			AdminUserOrgPost adminUserOrgPost = dto.getUserOrgPost();
			adminUserOrgPost.setUserId(user.getId());
			adminUserOrgPost.setDeptName(adminOrgService.getById(adminUserOrgPost.getOrgId()).getFullNamePath());
			adminUserOrgPost.setPostName(adminPostService.getById(adminUserOrgPost.getPostId()).getName());
			AdminSessionHolder.prepareAdminLoginData(adminUserOrgPost);
			adminUserOrgPostService.save(dto.getUserOrgPost());
		}
	}

	/**
	 * 修改后台用户
	 * 
	 * @param id
	 * @return
	 */
	public void doUpdate(AdminUserDTO dto) {
		AdminUser user = dto.getAdminUser();
		user.setPassword(getById(user.getId()).getPassword());
		user.setUsername(user.getUsername().toLowerCase());// 用户名转小写
		if(StringUtils.isEmpty(user.getBigLogo())){
			user.setBigLogo(getById(user.getId()).getBigLogo());
		}
		AdminSessionHolder.prepareAdminLoginData(user);
		update(user);
		AdminUserOrgPost adminUserOrgPost = dto.getUserOrgPost();
		adminUserOrgPost.setUserId(user.getId());
		if (adminUserOrgPost != null && adminUserOrgPost.getId() != 0l) {
			if(adminUserOrgPost.getOrgId()!=null && adminUserOrgPost.getOrgId()>0){
				adminUserOrgPost.setDeptName(adminOrgService.getById(adminUserOrgPost.getOrgId()).getFullNamePath());
			}
			if(adminUserOrgPost.getPostId() != null && adminUserOrgPost.getPostId() >0){
				adminUserOrgPost.setPostName(adminPostService.getById(adminUserOrgPost.getPostId()).getName());
			}
			AdminSessionHolder.prepareAdminLoginData(adminUserOrgPost);
			adminUserOrgPostService.update(adminUserOrgPost);
		} else if (adminUserOrgPost != null && adminUserOrgPost.getId() == 0l) {
			adminUserOrgPost.setUserId(user.getId());
			if(adminUserOrgPost.getOrgId()!=null && adminUserOrgPost.getOrgId()>0){
				adminUserOrgPost.setDeptName(adminOrgService.getById(adminUserOrgPost.getOrgId()).getFullNamePath());
			}
			if(adminUserOrgPost.getPostId() != null && adminUserOrgPost.getPostId() >0){
				adminUserOrgPost.setPostName(adminPostService.getById(adminUserOrgPost.getPostId()).getName());
			}
			AdminSessionHolder.prepareAdminLoginData(adminUserOrgPost);
			adminUserOrgPostService.save(adminUserOrgPost);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @return
	 */
	public int updatePassword(String oldpass, String newpass, String upass) {
		AdminUser user = getById(LoginHolder.getLoginUser(WebConstants.SessionKey.ADMIN_USER).getUserid());
		if (user != null && StringUtils.equals(user.getPassword(), Digests.md5(oldpass))) {
			if (newpass.equals(upass)) {
				user.setPassword(Digests.md5(upass));
				AdminSessionHolder.prepareAdminLoginData(user);
				update(user);
				return 1;
			} else {
				return 2;
			}
		} else {
			return -1;
		}
	}

	/**
	 * 记下最后登入信息
	 * 
	 * @param id
	 * @return
	 */
	public void doLogin() {
		AdminUser au = new AdminUser();
		au.setId(AdminSessionHolder.getAdminUserId());
		au.setLoginIp(AdminSessionHolder.getLoginIp());
		au.setLoginTime(System.currentTimeMillis());
		update(au);
	}

	public int deleteBy(String userName,Long id) {
		List<AdminUser> list = ds.createQuery(getEntityClass()).filter("username", userName).filter("id", id).asList();
		AdminUser adminUser = new AdminUser();
		if(!list.isEmpty()){
			adminUser = list.get(0);
		}
		int result = update(adminUser.getId(), "isDeleted", 1);
		if (result > 0) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("userId", id);
			adminUserOrgPostService.list(param);
			if(!adminUserOrgPostService.list(param).isEmpty()){
				adminUserOrgPostService.update(adminUserOrgPostService.list(param).get(0).getId(), "isDeleted", 1);
			}
		}
		return result;
	}

	public void modifyPass(Long id, String newPassword) {
		AdminUser au = getById(id);
		au.setPassword(Digests.md5(newPassword));
		update(au);
	}
	
	/**
	 * 获取所有的用户列表(带分页)
	 * 
	 * @return
	 */
	public Page<AdminUser> findAllUserAndPage(HttpServletRequest request){
		return pageQuery(request);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
