package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.constant.AdminConstants;
import org.itboys.admin.dto.AdminMenuDto;
import org.itboys.admin.entity.AdminMenu;
import org.itboys.admin.entity.AdminRolePermissionRel;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginHolder;
import org.itboys.admin.tools.LoginUser;
import org.itboys.admin.tools.WebConstants;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 菜单服务层
 * 
 * @author WeiSky
 * 
 */
@Service
public class AdminMenuService extends BaseAdminService<AdminMenu, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9102283084846424347L;
	@Autowired
	private AdminRolePermissionRelService adminRolePermissionRelService;
	public static final String PATH_SPLIT = "/";
	@Resource(name = "adminDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminMenu> getEntityClass() {
		return AdminMenu.class;
	}

	/**
	 * 获取所有的菜单
	 * 
	 * @return
	 */
	public List<AdminMenuDto> findAllMenu2() {
		List<AdminMenu> amList = ds.createQuery(getEntityClass())
				.filter("isDeleted", 0).asList();
		List<AdminMenuDto> ad = Lists.newArrayList();
		boolean flage = true;
		for (AdminMenu am : amList) {
			AdminMenuDto amd = new AdminMenuDto();
			amd.setId(am.getId());
			amd.setMenuName(am.getMenuName());
			amd.setPid(am.getParentId());
			amd.setUrl(am.getUrl());
			if (flage) {
				amd.setExpanded(true);
				flage = false;
			}
			ad.add(amd);
		}
		return ad;
	}

	/**
	 * 条件搜索 无分页
	 * 
	 * @param request
	 * @return
	 */
	public List<AdminMenu> list(HttpServletRequest request) {
		Map<String, Object> param = QueryParamUtils.builderQueryMap(request);
		Iterator<String> ite = param.keySet().iterator();
		Query<AdminMenu> query = ds.createQuery(getEntityClass()).filter(
				"isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		List<AdminMenu> amList = query.order("sort").asList();
		return amList;
	}
	public List<AdminMenu> list(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<AdminMenu> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		return query.order("sort").asList();
	}

	/**
	 * 获取所有的菜单，并分配层级关系
	 * 
	 * @return
	 */
	public List<AdminMenuDto> findAllMenu() {
		boolean flage = true;// 如果是第一个根菜单，就展开
		List<AdminMenuDto> ad = Lists.newArrayList();
		List<AdminMenu> rootList = findRootMenu();
		for (AdminMenu am : rootList) {
			List<AdminMenuDto> childDtoList = Lists.newArrayList();
			AdminMenuDto amd = new AdminMenuDto();
			amd.setMenuName(am.getMenuName());
			List<AdminMenu> childList = findByRootId(am.getId());
			for (AdminMenu child : childList) {
				AdminMenuDto childDto = new AdminMenuDto();
				childDto.setId(child.getId());
				childDto.setMenuName(child.getMenuName());
				childDto.setPid(child.getParentId());
				childDto.setUrl(child.getUrl());
				childDtoList.add(childDto);
				amd.setChildren(childDtoList);
			}
			if (flage) {
				amd.setExpanded(true);
				flage = false;
			}
			ad.add(amd);
		}
		return ad;
	}

	/**
	 * 更新菜单
	 * 
	 * @param am
	 */
	public void updateMenu(AdminMenu am) {
		UpdateOperations<AdminMenu> uo = ds
				.createUpdateOperations(AdminMenu.class);
		uo.set("menuName", am.getMenuName());
		uo.set("url", am.getUrl());
		super.update(am.getId(), uo);
	}

	/**
	 * 删除 将isDeleted标为1
	 * 
	 * @param id
	 */
	public void deleteMenu(Long id) {
		update(id, "isDeleted", 1);
	}

	/**
	 * 获取全部菜单ID
	 * 
	 * @return
	 */
	public List<Long> getAllMenuIds() {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(0);
		List<AdminMenu> alls = list(param);
		return Lists.transform(alls, AdminConstants.transform.getMenuId);
	}

	/**
	 * 获取所有一级类目菜单
	 * 
	 * @return
	 */
	public List<AdminMenu> getRootMenus() {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(0);
		List<AdminMenu> alls = list(param);
		return getRootsMenus(alls);
	}

	/**
	 * 根据ID加载菜单对象
	 * 
	 * @param id
	 * @return
	 */
	public AdminMenu getAdminMenu(Long id) {
		return getById(id);
	}

	/**
	 * 根据ID加载菜单对象
	 * 
	 * @param id
	 * @return
	 */
	public List<AdminMenu> getChilidsByParentid(Long parentId) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("parentId", parentId);
		List<AdminMenu> alls = list(param);
		return alls;
	}

	/**
	 * 获取一级菜单ID
	 * 
	 * @param menus
	 * @return
	 */
	public List<AdminMenu> getRootsMenus(List<AdminMenu> menus) {
		final LoginUser user = LoginHolder
				.getLoginUser(WebConstants.SessionKey.ADMIN_USER);
		if (menus == null || menus.isEmpty() || user == null) {
			return new ArrayList<AdminMenu>(0);
		}
		final List<Long> menuids = user.getMenuIds();
		return CommonCollectionUtils.filterCollection(menus,
				new FetchCondition<AdminMenu>() {
					@Override
					public boolean needFetch(AdminMenu t) {
						if (user.getType() == AdminConstants.TYPE_SUPER) {
							return t != null && t.getParentId() == 0L;
						}
						return menuids != null && menuids.contains(t.getId())
								&& t != null && t.getParentId() == 0L;
					}
				});
	}

	/**
	 * 获取所有一级菜单 忽略权限 一
	 * 
	 * @param menus
	 * @return
	 */
	public List<AdminMenu> getRootsMenusIgnorePermission(List<AdminMenu> menus) {
		return CommonCollectionUtils.filterCollection(menus,
				new FetchCondition<AdminMenu>() {
					@Override
					public boolean needFetch(AdminMenu t) {
						return t.getParentId() == 0L;
					}
				});
	}

	/**
	 * 获取一个角色关联的菜单ID
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getAdminMenuByRoleIds(Long roleId) {
		return getAdminMenuByRoleIds(Lists.newArrayList(roleId));
	}

	/**
	 * 获取角色关联的菜单ID
	 * 
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getAdminMenuByRoleIds(List<Long> roleIds) {
		if (CommonCollectionUtils.isEmpty(roleIds)) {
			return Collections.EMPTY_LIST;
		}
		List<AdminRolePermissionRel> list = adminRolePermissionRelService
				.getByTypeAndRole(roleIds,"menu");
		List<Long> relIds = new ArrayList<Long>();
		for(AdminRolePermissionRel rel : list){
			relIds.add(rel.getRelObjectId());
		}
		List<AdminMenu> roleMenus = ds.createQuery(getEntityClass())
				.filter("isDeleted", 0).filter("id in", relIds.isEmpty()?0:relIds).asList();
		List<Long> menuIds = new ArrayList<Long>();
		for(AdminMenu menu : roleMenus){
			menuIds.add(menu.getId());
		}
		return menuIds;
	}

	/*
	 * 添加菜单
	 */
	public void insert(AdminMenu adminMenu) {
		if (adminMenu.getParentId() == null) {
			adminMenu.setParentId(0L);
		}
		AdminSessionHolder.prepareAdminLoginData(adminMenu);
		if (adminMenu.getParentId() == 0L) {
			adminMenu.setFullPath(StringUtils.EMPTY);
			adminMenu.setFullNamePath(PATH_SPLIT + adminMenu.getMenuName()
					+ PATH_SPLIT);
			adminMenu.setLevel(1);
			save(adminMenu);
			adminMenu.setFullPath(PATH_SPLIT + adminMenu.getId() + PATH_SPLIT);
			update(adminMenu);
		} else {
			AdminMenu parent = getAdminMenu(adminMenu.getParentId());
			adminMenu.setFullPath(StringUtils.EMPTY);
			adminMenu.setFullNamePath(parent.getFullNamePath()
					+ adminMenu.getMenuName() + PATH_SPLIT);
			adminMenu.setLevel(parent.getLevel() + 1);
			insert(adminMenu);
			adminMenu.setFullPath(parent.getFullPath() + adminMenu.getId()
					+ PATH_SPLIT);
			update(adminMenu);
		}
	}

	/*
	 * 修改菜单
	 */
	public void doUpdate(AdminMenu adminMenu) {
		AdminSessionHolder.prepareAdminLoginData(adminMenu);
		AdminMenu exists = getAdminMenu(adminMenu.getId());
		if (exists.getParentId().equals(0L)) {
			adminMenu.setFullNamePath(PATH_SPLIT + adminMenu.getMenuName()
					+ PATH_SPLIT);
		} else {
			AdminMenu parent = getAdminMenu(adminMenu.getParentId());
			adminMenu.setFullNamePath(parent.getFullNamePath()
					+ adminMenu.getMenuName() + PATH_SPLIT);
		}
		update(adminMenu);
	}

	/**
	 * 获取最顶级的菜单
	 * 
	 * @return
	 */
	public List<AdminMenu> findRootMenu() {
		return findByField("parentId", 0);
	}

	/**
	 * 根据父级ID，获取下面所有的子菜单
	 * 
	 * @param parentId
	 * @return
	 */
	public List<AdminMenu> findByRootId(long parentId) {
		return findByField("parentId", parentId);
	}

}
