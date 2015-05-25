package org.itboys.admin.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminContacts;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Maps;

@Service
public class AdminContactService extends BaseAdminService<AdminContacts, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1409874602667381844L;
	@Resource(name="adminDS")
	private MongoDataSource mds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return mds;
	}

	@Override
	protected Class<AdminContacts> getEntityClass() {
		return AdminContacts.class;
	}
	
	public List<AdminContacts> list(Map<String, Object> param) {
		Iterator<String> ite = param.keySet().iterator();
		Query<AdminContacts> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
		}
		return query.asList();
	}
	
	//删除某项目下的某部门下的通讯录信息
	public void deleteByOrgIdAndProjectId(Long orgId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("orgId", orgId);
		List<AdminContacts> list = list(param);
		for(AdminContacts contacts : list){
			update(contacts.getId(), "isDeleted", 1);
		}
	}
	
}
