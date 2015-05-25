package org.itboys.mongodb.core;

import java.util.List;

import com.mongodb.DB;

/**
 * 要扫描的mongodb实体配到此包下
 * @author 俊哥
 *
 */
public class MorphiaEntities  extends BaseMorphiaMapper {

	private List<String> entityPackages;
	
	@Override
	public void mapEntities(DB db) {
		if(entityPackages==null || entityPackages.isEmpty()){
			return;
		}
		for(String packageName:entityPackages){
			mapPackage(packageName);
		}
	}

	public List<String> getEntityPackages() {
		return entityPackages;
	}

	public void setEntityPackages(List<String> entityPackages) {
		this.entityPackages = entityPackages;
	}

}
