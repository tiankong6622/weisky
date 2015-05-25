package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.itboys.admin.entity.AdminPermission;
import org.itboys.admin.entity.AdminRolePermissionRel;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class AdminRolePermissionRelService extends BaseAdminService<AdminRolePermissionRel, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4082778566539714233L;

	@Autowired
	private AdminPermissionService adminPermissionService;
	
	@Resource(name="adminDS")
	private MongoDataSource mds;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return mds;
	}

	@Override
	protected Class<AdminRolePermissionRel> getEntityClass() {
		return AdminRolePermissionRel.class;
	}
	
	public List<AdminRolePermissionRel> getByTypeAndRole(List<Long> roleIds,String...type){
		Query<AdminRolePermissionRel> query = mds.createQuery(getEntityClass()).filter("isDeleted", 0);
		if(ArrayUtils.isNotEmpty(type)){
			query = query.filter("relType in", type);
		}
		if(!roleIds.isEmpty()){
			query = query.filter("roleId in", roleIds);
		}
		
		return query.asList();
	}
	
	/**
	 * 给角色赋予菜单/权限
	 * @param roleRels
	 */
	public void batchInsert(List<AdminRolePermissionRel> roleRels){
		batchSave(roleRels);
	}

	/**
	 * 得到某个角色之前在某种类型权限下所具备的权限
	 * @param sqlMap
	 * @return
	 * select ap.id from admin_permission ap ,admin_role_permission_rel arp 
		where ap.id=arp.rel_object_id and ap.is_deleted=1 and arp.is_deleted=1 
		and arp.role_id=#{roleId}
	 */
	public List<Long> getPermissionIdByRoleAndType(Long roleId,String type){
		List<AdminRolePermissionRel> list = mds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("roleId", roleId).
				filter("relType", type).asList();
		List<AdminPermission> permissions = new ArrayList<AdminPermission>();
		for(AdminRolePermissionRel rel : list){
			permissions.add(adminPermissionService.getById(rel.getRelObjectId()));
		}
		return Lists.transform(permissions, new Function<AdminPermission, Long>() {
			@Override
			public Long apply(AdminPermission permission) {
				return permission.getId();
			}
		});
	}

	/**
	 *  删除角色  解除角色-菜单关联关系
	 * @param roleId
	 * @return
	 * update admin_role_permission_rel set is_deleted=0 
		where rel_type='menu' and role_id=#{roleId}
	 */
	public void updateDltByRole(Long roleId){
		List<AdminRolePermissionRel> list = mds.createQuery(getEntityClass()).filter("relType", "menu").filter("roleId", roleId).asList();
		for(AdminRolePermissionRel rel : list){
			update(rel.getId(), "isDeleted", 1);
		}
	}
	
	
	/**
	 *  * 删除菜单 解除菜单-角色关联关系
	 * @param roleRels
	 * @return
	 * update admin_role_permission_rel set is_deleted=0 
		where rel_type='menu' and rel_object_id=#{relObjectId}
	 */
	
	public void updateDltByMneu(Long menuId){
		List<AdminRolePermissionRel> list = mds.createQuery(getEntityClass()).filter("relType", "menu").filter("relObjectId", menuId).asList();
		for(AdminRolePermissionRel rel : list){
			update(rel.getId(), "isDeleted", 1);
		}
	}
	
	/**
	 * 删除角色 解除角色-权限关联关系
	 * 删除权限 解除权限-角色关联关系(根据权限id查出具备这个权限的角色关联信息)
	 * @param roleRels
	 * @return
	 * update admin_role_permission_rel set is_deleted=0
		where id in (
		<foreach collection="list" item="item" index="index" separator=",">
			#{item.id} 
		</foreach>)
	 */
	public void updateDltByRoleAndAccess(List<AdminRolePermissionRel> roleRels){
		List<Long> ids = Lists.transform(roleRels, new Function<AdminRolePermissionRel, Long>() {
			@Override
			public Long apply(AdminRolePermissionRel permission) {
				return permission.getId();
			}
		});
		for(Long id : ids){
			update(id, "isDeleted", 1);
		}
	}

}
