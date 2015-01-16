package com.hz.yisheng.commondata.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.javafans.common.utils.CommonUtils;
import org.javafans.common.utils.collection.CommonCollectionUtils;
import org.javafans.common.utils.collection.FetchCondition;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.dao.PermissionMapper;
import com.hz.yisheng.commondata.orm.Permission;

/**
 * 权限相关业务层
 * @author GuoRui
 */
@Service
public class PermissionBO {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	public Page<Permission> pageQuery(final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<Permission>() {
			@Override
			public List<Permission> list(){
				List<Permission> list = permissionMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return permissionMapper.count(sqlMap);
			}
		});
	}

	public List<Permission> list(final String ...types){
		Map<String, Object> sqlMap = Maps.newHashMap();
		List<Permission> list = permissionMapper.list(sqlMap);
		return CommonCollectionUtils.filterCollection(list, new FetchCondition<Permission>() {
			@Override
			public boolean needFetch(Permission t) {
				return types==null || types.length==0?true:CommonUtils.isIn(t.getType(), types);
			}
		});
	}
	
	public Permission findById(Long id){
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(1);
		sqlMap.put("id", id);
		List<Permission> lists = permissionMapper.list(sqlMap);
		return lists.isEmpty()?null:lists.get(0);
	}
	
	public void insert(Permission permission){
		SessionHolder.prepareMemberLoginData(permission);
		permissionMapper.insert(permission);
	}
	
	public int update(Permission permission){
		SessionHolder.prepareMemberLoginData(permission);
		int result = permissionMapper.update(permission);
		return result;
	}
	

	public int delete(Long id){
		return permissionMapper.delete(id);
	}
	
	
	/**
	 * 获取等级已经具备的权限ID集合
	 * @param roleId
	 * @return
	 */
	public List<Long> getSelectedPermissionIdsByGradeId(Long gradeId,String...type){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("gradeId", gradeId);
		if(ArrayUtils.isNotEmpty(type)){
			map.put("types", type);
		}
		List<Permission> permissions =  permissionMapper.getPermissionsByGrade(map);
		return Lists.transform(permissions, new Function<Permission, Long>() {
			@Override
			public Long apply(Permission permission) {
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
	public List<Permission> getSelectedPermissionByGradeId(Long gradeId,String...type){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("gradeId", gradeId);
		if(ArrayUtils.isNotEmpty(type)){
			map.put("types", type);
		}
		List<Permission> permissions =  permissionMapper.getPermissionsByGrade(map);
		return permissions;
	}
	
}
