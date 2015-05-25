package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.entity.AdminUserOrgPost;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;

@Service
public class AdminUserOrgPostService extends BaseAdminService<AdminUserOrgPost, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6549096366663261951L;
	@Autowired
	private AdminOrgService adminOrgService;
	@Autowired
	private AdminPostService adminPostService;
	@Autowired
	private AdminUserService adminUserService;
	
	@Resource(name="adminDS")
	private MongoDataSource adminUserorgPostDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return adminUserorgPostDataSource;
	}

	@Override
	protected Class<AdminUserOrgPost> getEntityClass() {
		return AdminUserOrgPost.class;
	}
	
	public List<AdminUserOrgPost> list(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<AdminUserOrgPost> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		return query.asList();
	}
	
	public AdminUserOrgPost findByUserId(Long userId){
		List<AdminUserOrgPost> list = adminUserorgPostDataSource.createQuery(getEntityClass()).filter("userId", userId)
				.filter("isDeleted", 0).asList();
		for(AdminUserOrgPost adminUserOrgPost : list){
			String deptName = adminOrgService.getById(adminUserOrgPost.getOrgId()).getName();
			Integer isArea = adminOrgService.getById(adminUserOrgPost.getOrgId()).getIsArea();
			String postName = adminPostService.getById(adminUserOrgPost.getPostId()).getName();
			adminUserOrgPost.setDeptName(deptName);
			adminUserOrgPost.setIsArea(isArea);
			adminUserOrgPost.setPostName(postName);
		}
		return list.size()>=1?list.get(0):null;
	}
	public List<AdminUserOrgPost> findsByUserId(Long userId){
		List<AdminUserOrgPost> list = adminUserorgPostDataSource.createQuery(getEntityClass()).filter("userId", userId)
				.filter("isDeleted", 0).asList();
		for(AdminUserOrgPost adminUserOrgPost : list){
			String deptName = adminOrgService.getById(adminUserOrgPost.getOrgId()).getName();
			Integer isArea = adminOrgService.getById(adminUserOrgPost.getOrgId()).getIsArea();
			String postName = "";
			if(adminUserOrgPost.getPostId()!=0){
				adminPostService.getById(adminUserOrgPost.getPostId()).getName();
			}
			adminUserOrgPost.setDeptName(deptName);
			adminUserOrgPost.setIsArea(isArea);
			adminUserOrgPost.setPostName(postName);
		}
		return list.size()>=1?list:null;
	}
	
	//获取同一部门的员工
	public List<AdminUser> findUserByOrgId(Long orgId){
		List<AdminUserOrgPost> list = adminUserorgPostDataSource.createQuery(getEntityClass()).filter("orgId", orgId)
				.filter("isDeleted", 0).asList();
		List<AdminUser> users = new ArrayList<AdminUser>();
		for(AdminUserOrgPost adminUserOrgPost : list){
			AdminUser adminUser = adminUserService.getById(adminUserOrgPost.getUserId());
			users.add(adminUser);
		}
		return users;
	}

}
