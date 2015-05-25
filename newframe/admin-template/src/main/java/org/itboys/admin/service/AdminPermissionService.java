package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.itboys.admin.entity.AdminPermission;
import org.itboys.admin.entity.AdminRolePermissionRel;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;
import org.itboys.commons.utils.common.CommonUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 权限service
 * @author WeiSky
 *
 */
@Service
public class AdminPermissionService extends BaseAdminService<AdminPermission, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6607305220050169017L;
	@Autowired
	private AdminRolePermissionRelService adminRolePermissionRelService;
	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminPermission> getEntityClass() {
		return AdminPermission.class;
	}
	
	public List<AdminPermission> list(final String ...types){
		List<AdminPermission> list = ds.createQuery(getEntityClass()).filter("isDeleted", 0).asList();
		return CommonCollectionUtils.filterCollection(list, new FetchCondition<AdminPermission>() {
			public boolean needFetch(AdminPermission t) {
				return types==null || types.length==0?true:CommonUtils.isIn(t.getType(), types);
			}
		});
	}
	
	/**
	 * 获取角色已经具备的权限ID集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getSelectedPermissionIdsByRoleId(Long roleId){
		return getSelectedPermissionIdsByRoleId(Lists.newArrayList(roleId));
	}
	
	/**
	 * 获取角色已经具备的权限ID集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getSelectedPermissionIdsByRoleId(List<Long> roleIds,String...type){
		List<AdminRolePermissionRel> list = adminRolePermissionRelService.getByTypeAndRole(roleIds, type);
		List<AdminPermission> permissions =  new ArrayList<AdminPermission>();
		for(AdminRolePermissionRel rel : list){
			List<AdminPermission> permissions2 = ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("id", rel.getRelObjectId()).asList();
			permissions.addAll(permissions2);
		}
		return Lists.transform(permissions, new Function<AdminPermission, Long>() {
			@Override
			public Long apply(AdminPermission permission) {
				return permission.getId();
			}
		});
	}
	
	/**
	 * 获得关联的权限
	 * @param roleIds
	 * @param type
	 * @return
	 */
	public List<AdminPermission> getSelectedPermissionByRoleIds(List<Long> roleIds,String...type){
		List<AdminRolePermissionRel> list = adminRolePermissionRelService.getByTypeAndRole(roleIds, type);
		List<AdminPermission> permissions =  new ArrayList<AdminPermission>();
		for(AdminRolePermissionRel rel : list){
			List<AdminPermission> permissions2 = ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("id", rel.getRelObjectId()).asList();
			permissions.addAll(permissions2);
		}
		return permissions;
	}
	
	public void insert(AdminPermission adminPermission){
		AdminSessionHolder.prepareAdminLoginData(adminPermission);
		save(adminPermission);
	}
	
	public void doUpdate(AdminPermission adminPermission){
		AdminSessionHolder.prepareAdminLoginData(adminPermission);
		update(adminPermission);
	}
	
	public void doDelete(Long id){
		update(id, "isDeleted", 1);
	}
}
