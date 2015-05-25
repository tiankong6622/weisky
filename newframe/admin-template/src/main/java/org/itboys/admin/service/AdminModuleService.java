package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminModule;
import org.itboys.admin.entity.AdminPermission;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class AdminModuleService extends BaseAdminService<AdminModule, Long> {
	private static final long serialVersionUID = -3829919937898055811L;
	@Autowired
	private AdminPermissionService adminPermissionService;
	@Resource(name="adminDS")
	private MongoDataSource mds;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return mds;
	}

	@Override
	protected Class<AdminModule> getEntityClass() {
		return AdminModule.class;
	}
	
	/**
	 * 获得全部模块
	 * @return
	 */
	public List<AdminModule> getAll(){
		List<AdminModule> list = mds.createQuery(getEntityClass()).filter("isDeleted", 0).asList();
		return list;
	}
	
	public void insert(AdminModule adminModule){
		AdminSessionHolder.prepareAdminLoginData(adminModule);
		save(adminModule);
	}
	
	public void doUpdate(AdminModule adminModule){
		AdminSessionHolder.prepareAdminLoginData(adminModule);
		update(adminModule);
	}
	
	/**
	 * select distinct m.id,m.name from admin_module m,admin_permission p
		where  p.module_id in(
			select id from admin_module where is_deleted=1
		) and p.module_id=m.id and p.is_deleted=1
	 * @return
	 */
	public List<AdminModule> getModule() {
		List<AdminModule> modules = getAll();
		
		List<Long> ids = Lists.transform(modules, new Function<AdminModule, Long>() {
			@Override
			public Long apply(AdminModule permission) {
				return permission.getId();
			}
		});
		Map<String, Object> param = Maps.newHashMap();
		param.put("isDeleted", 0);
		param.put("moduleId in", ids);
		List<AdminPermission> permissions = adminPermissionService.list(param);
		
		param.clear();
		param.put("isDeleted", 0);
		List<AdminModule> list = new ArrayList<AdminModule>();
		for(AdminPermission permission : permissions){
			param.put("id", permission.getModuleId());
			list.addAll(list(param));
			param.remove("id");
		}
		
		return list;
	}
	
	public void doDeleted(Long id){
		update(id, "isDeleted", 1);
	}

}
