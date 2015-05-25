package org.itboys.admin.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminUserRole;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;

@Service
public class AdminUserRoleService extends BaseAdminService<AdminUserRole, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3610702458307564790L;
	@Resource(name="adminDS")
	private MongoDataSource mds;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return mds;
	}

	@Override
	protected Class<AdminUserRole> getEntityClass() {
		return AdminUserRole.class;
	}
	
	public List<AdminUserRole> list(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<AdminUserRole> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		List<AdminUserRole> list = query.asList();
		return list;
	}

	/*
	 * 删除角色时 解除用户关联关系
	 * 解除用户之前具备角色的关联关系
	 */
	public void updateDltByUserOrRole(Long userId){
		List<AdminUserRole> list = mds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("userId", userId).asList();
		for(AdminUserRole adminUserRole : list){
			update(adminUserRole.getId(), "isDeleted", 1);
		}
	}

}
