package org.javafans.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.javafans.BaseTest;
import org.junit.Test;

public class CacheTest extends BaseTest {

	@Resource
	CacheTestManager cacheTestManager;
	
	@Test
	public void testCache(){
		Long id = 1L;
		//测试从缓存中取数据 第一次缓存中没有 取出后 放进缓存
		TestCategory category = cacheTestManager.getTestCategory(id);
		System.out.println(category);
		//第二次从缓存中取
		category = cacheTestManager.getTestCategory(id);
		System.out.println(category);
		//根据对象的属性值或map的key从缓存中取数据
		TestCategory param  = new TestCategory();
		param.setId(1L);
		category = cacheTestManager.getTestCategory(param);
		System.out.println(category);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 1L);
		category = cacheTestManager.getTestCategory(map);
		System.out.println(category);
		//测试根据parentId加载子类目 第一次查数据  取出后 放进缓存里
		List<TestCategory> list = cacheTestManager.getTestCategoryByParentId(1L);
		System.out.println(list);
		//第二次从缓存中取
		list = cacheTestManager.getTestCategoryByParentId(1L);
		System.out.println(list);
		//测试加载所有类目 第一次查数据  取出后 放进缓存里
		List<TestCategory> listAll = cacheTestManager.getAll();
		System.out.println(listAll);
		//第二次从缓存中取
		listAll = cacheTestManager.getAll();
		System.out.println(listAll);
		//做更新或删除操作 清空之前的缓存
		cacheTestManager.updateOrDeleteCategory(1L);
		
		int k = cacheTestManager.getFromCache(1,"343",2);
		System.out.println(k);
		k = cacheTestManager.getFromCache(1,"32",2);
		System.out.println(k);
		k = cacheTestManager.getFromCache(1,"44442",2);
		System.out.println(k);
		cacheTestManager.deleteCache(1, 2);
	}
}
