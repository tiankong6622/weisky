package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminOrgCity;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;

@Service
public class AdminOrgCityService extends BaseAdminService<AdminOrgCity,Long> {
	
	private static final long serialVersionUID = -3305865986121632121L;
	
	@Resource(name="adminDS")
	private MongoDataSource adminOrgCityDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return adminOrgCityDataSource;
	}

	@Override
	protected Class<AdminOrgCity> getEntityClass() {
		return AdminOrgCity.class;
	}
	
	public List<Long> getAdminOrgCitys(Long orgId){
		Query<AdminOrgCity> query = adminOrgCityDataSource.createQuery(getEntityClass())
				.filter("objId", orgId).filter("type", 1);
		List<Long> ids = new ArrayList<>();
		if(query != null){
			for(AdminOrgCity adminOrgCity : query.asList()){
				ids.add(adminOrgCity.getCityId());
			}
		}
		return ids;
	}
	
	public List<Long> getAdminUserCitys(Long userId){
		
		Query<AdminOrgCity> query = adminOrgCityDataSource.createQuery(getEntityClass())
				.filter("objId", userId).filter("type", 2);
		List<Long> ids = new ArrayList<>();
		if(query != null){
			for(AdminOrgCity adminOrgCity : query.asList()){
				ids.add(adminOrgCity.getId());
			}
		}
		return ids;
	}
	
	
	public void doRealOrg(Long orgId,List<Long> cityIds){
		List<AdminOrgCity> ocs = new ArrayList<AdminOrgCity>(cityIds.size());
		for(Long cityId:cityIds){
			AdminOrgCity aoc=new AdminOrgCity();
			aoc.setCityId(cityId);
			aoc.setObjId(orgId);
			aoc.setType(AdminOrgCity.TYPE_O);
			AdminSessionHolder.prepareAdminLoginData(aoc);
			ocs.add(aoc);
		}
		List<AdminOrgCity> list = findByField("objId", orgId);
		for(AdminOrgCity adminOrgCity : list){
			delete(adminOrgCity.getId());
		}
		batchSave(ocs);
	}
	

}
