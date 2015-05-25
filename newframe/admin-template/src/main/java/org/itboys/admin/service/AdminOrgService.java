package org.itboys.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.entity.AdminOrg;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 组织的service层
 * @author WeiSky
 *
 */
@Service
public class AdminOrgService extends BaseAdminService<AdminOrg, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3835978431001056288L;
	public static final String PATH_SPLIT = "/";
	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminOrg> getEntityClass() {
		return AdminOrg.class;
	}
	
	public List<AdminOrg> findOrgByIds(List<Long> ids){
		return getByIds(ids);
	}
	
	public List<AdminOrg> getAllOrgs(){
		return ds.createQuery(getEntityClass()).filter("isDeleted", 0).asList();
	}
	
	/**
	 * 获取部门列表简略信息
	 * @return
	 */
	public List<AdminOrg> getAllOrgsSimple(){
		return ds.createQuery(getEntityClass()).filter("isDeleted", 0).asList();
	}

	public List<AdminOrg> findByParentId(Long parentId){
		List<AdminOrg> list = findByField("parentId", parentId);
		return list;
	}
	
	/**
	 * 根据ID加载菜单对象
	 * @param id
	 * @return
	 */
	public List<AdminOrg> getChilidsByParentid(Long parentId){
		List<AdminOrg> list = findByField("parentId", parentId);
		return list;
	}
	
	
	public void doSave(AdminOrg adminOrg){
		AdminSessionHolder.prepareAdminLoginData(adminOrg);
		if(adminOrg.getParentId() == null){
			adminOrg.setParentId(0L);
		}
		if(adminOrg.getParentId()==0L){
			adminOrg.setFullPath(StringUtils.EMPTY);
			adminOrg.setFullNamePath(PATH_SPLIT+adminOrg.getName()+PATH_SPLIT);
			adminOrg.setLevel(1);
			save(adminOrg);
			adminOrg.setFullPath(PATH_SPLIT+adminOrg.getId()+PATH_SPLIT);
			update(adminOrg);
		}else{
			AdminOrg parent = getById(adminOrg.getParentId());
			adminOrg.setFullPath(StringUtils.EMPTY);
			adminOrg.setFullNamePath(parent.getFullNamePath()+adminOrg.getName()+PATH_SPLIT);
			adminOrg.setLevel(parent.getLevel()+1);
			save(adminOrg);
			adminOrg.setFullPath(parent.getFullPath()+adminOrg.getId()+PATH_SPLIT);
			update(adminOrg);
		}
	}
	
	
	public void doUpdate(AdminOrg adminOrg){
		AdminSessionHolder.prepareAdminLoginData(adminOrg);
		AdminOrg exists = getById(adminOrg.getId());
		if(exists.getParentId().equals(0L)){
			adminOrg.setFullPath(PATH_SPLIT+adminOrg.getId()+PATH_SPLIT);
			adminOrg.setFullNamePath(PATH_SPLIT+adminOrg.getName()+PATH_SPLIT);
		}else{
			AdminOrg parent = getById(adminOrg.getParentId());
			adminOrg.setFullPath(parent.getFullPath()+adminOrg.getId()+PATH_SPLIT);
			adminOrg.setFullNamePath(parent.getFullNamePath()+adminOrg.getName()+PATH_SPLIT);
		}
		update(adminOrg);
	}
	/**
	 * 删除  isDeleted标为1
	 * @param id
	 */
	public void deleteOrg(Long id){
		update(id, "isDeleted",1);
	}
	
}
