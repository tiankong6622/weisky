package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminRole;
import org.itboys.admin.entity.AdminRolePermissionRel;
import org.itboys.admin.entity.AdminUserRole;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 角色service
 * @author WeiSky
 *
 */
@Service
public class AdminRoleService extends BaseAdminService<AdminRole, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6783172304583219793L;
	@Autowired
	private AdminPermissionService adminPermissionService;
	@Autowired
	private AdminUserRoleService adminUserRoleService;
	@Autowired
	private AdminRolePermissionRelService adminRolePermissionRelService;
	
	@Resource(name="adminDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminRole> getEntityClass() {
		return AdminRole.class;
	}
	
	public List<AdminRole> list(){
		return ds.createQuery(getEntityClass()).filter("isDeleted", 0).asList();
	}
	
	//用户角色关联
	public void insertUserRole(final Long userId,List<Long> roleIds){
		//取消用户原来的角色
		adminUserRoleService.updateDltByUserOrRole(userId);
		
		if(userId==null || CommonCollectionUtils.isEmpty(roleIds)){
			return ;
		}
		
		List<AdminUserRole> userRoles = Lists.transform(roleIds, new Function<Long, AdminUserRole>() {
			@Override
			public AdminUserRole apply(Long roleId) {
				AdminUserRole userRole = new AdminUserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(userId);
				userRole.setType(1);//关联角色
				AdminSessionHolder.prepareAdminLoginData(userRole);
				return userRole;
			}
		});
		for(AdminUserRole adminUserRole : userRoles){
			adminUserRoleService.save(adminUserRole);
		}
		
	}
	
	//角色 菜单 关联
	public void insertRoleMenu(final Long roleId,List<Long> menuIds,final String types){
		//取消角色具备的原来的菜单
		adminRolePermissionRelService.updateDltByRole(roleId);
		
		if(roleId==null || CommonCollectionUtils.isEmpty(menuIds)){
			return ;
		}
		List<AdminRolePermissionRel> roleRels = new ArrayList<AdminRolePermissionRel>();
		for(Long menuId : menuIds){
			AdminRolePermissionRel roleRel = new AdminRolePermissionRel();
			roleRel.setRoleId(roleId);
			roleRel.setRelObjectId(menuId);
			roleRel.setRelType(types);
			AdminSessionHolder.prepareAdminLoginData(roleRel);
			roleRels.add(roleRel);
		}
		if(!roleRels.isEmpty()){
			for(AdminRolePermissionRel roleRel : roleRels){
				adminRolePermissionRelService.save(roleRel);
			}
		}
	}
	
	
	//角色权限关联(type 权限类型 )
	public void insertRolePermission(final Long roleId,List<Long> permissionIds,final String type){
		//取消角色具备的某种权限
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(3);
//		sqlMap.put("roleId", roleId);
		sqlMap.put("relType", type);
		List<Long> permissionid = this.getPermissionIdByRoleAndType(roleId,type);
		if(!permissionid.isEmpty()){
			sqlMap.put("relObjectId", permissionid);
			List<AdminRolePermissionRel> lists = adminRolePermissionRelService.list(sqlMap);
			adminRolePermissionRelService.updateDltByRoleAndAccess(lists);
		}
		
		if(roleId==null || CommonCollectionUtils.isEmpty(permissionIds)){
			return ;
		}
		
		List<AdminRolePermissionRel> roleRels = Lists.transform(permissionIds, new Function<Long, AdminRolePermissionRel>() {
			@Override
			public AdminRolePermissionRel apply(Long permissionId){
				AdminRolePermissionRel roleRel = new AdminRolePermissionRel();
				roleRel.setRoleId(roleId);
				roleRel.setRelObjectId(permissionId);
				roleRel.setRelType(type);
				AdminSessionHolder.prepareAdminLoginData(roleRel);
				return roleRel;
			}
		});
		adminRolePermissionRelService.batchInsert(roleRels);
	}
	
	//某个角色具备的权限(访问权限，ui元素权限，虚拟权限)下的信息id
	private List<Long> getPermissionIdByRoleAndType(Long roleId,String type){
		return adminRolePermissionRelService.getPermissionIdByRoleAndType(roleId,type);
	}
	
	public void insert(AdminRole adminRole){
		AdminSessionHolder.prepareAdminLoginData(adminRole);
		save(adminRole);
	}
	
	public void doUpdate(AdminRole adminRole){
		AdminSessionHolder.prepareAdminLoginData(adminRole);
		update(adminRole);
	}
	
	public void doDelete(Long id){
		update(id, "isDeleted", 1);
	}
	
	/**
	 * 获取用户关联的角色ID
	 * @param roleId
	 * @return
	 * select ar.id from admin_role ar,admin_user_role aur 
		where ar.id=aur.role_id and aur.user_id=#{userId} and ar.is_deleted=1 and aur.is_deleted=1
	 */
	public List<Long> getAdminRoleByUserIds(Long userId){
		Map<String, Object> param = Maps.newHashMap();
		param.put("userId", userId);
		List<AdminUserRole> list = adminUserRoleService.list(param);
		List<AdminRole> userRoles =  new ArrayList<AdminRole>();
		for(AdminUserRole adminUserRole : list){
			userRoles.addAll(ds.createQuery(getEntityClass()).filter("isDeleted", 0).filter("id", adminUserRole.getRoleId()).asList());
		}
		List<Long> menuIds = Lists.transform(userRoles, new Function<AdminRole, Long>() {
			@Override
			public Long apply(AdminRole input) {
				return input.getId();
			}
		});
		return  menuIds;
	}

}
