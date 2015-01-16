package org.javafans.cache;

import java.util.List;
import java.util.Map;

import org.javafans.cache.annotation.CacheParam;
import org.javafans.cache.annotation.FetchFromCache;
import org.javafans.cache.annotation.RemoveCache;
import org.javafans.cache.constants.CacheConstants;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * 测试 从缓存中取数据
 * @author ChenJunhui
 *
 */
@Service
public class CacheTestManager {
	
	public static final String GET_CATEGORY_CACHE_KEY = "101-";
	public static final String GET_CATEGORY_GETBYPARENTID_CACHE_KEY = "102-";
	public static final String GET_ALL_CATEGORY_CACHE_KEY = "103-";
	
	/**
	 * 根据ID获取 TestCategory 从分布式缓存中取
	 * @param id
	 * @return
	 */
	@FetchFromCache(cacheName=GET_CATEGORY_CACHE_KEY) //memcached
	//@FetchFromCache(cacheName=GET_CATEGORY_CACHE_KEY,cacheType=CacheConstants.CACHE_TYPE_LOCAL) //ehcache
	public TestCategory getTestCategory(@CacheParam Long id){
		TestCategory category = prepareTestCategory(1L, null);
		return category;
	}
	
	/**
	 * 根据对象的ID属性 获取 TestCategory 从分布式缓存中取
	 * @param id
	 * @return
	 */
	@FetchFromCache(cacheName=GET_CATEGORY_CACHE_KEY)
	//@FetchFromCache(cacheName=GET_CATEGORY_CACHE_KEY,cacheType=CacheConstants.CACHE_TYPE_LOCAL)
	public TestCategory getTestCategory(@CacheParam(field="id") TestCategory testCategory){
		TestCategory category = prepareTestCategory(testCategory.getId(), null);
		return category;
	}
	
	/**
	 * 根据map总的key为id 的值 获取 TestCategory 从分布式缓存中取
	 * @param id
	 * @return
	 */
	@FetchFromCache(cacheName=GET_CATEGORY_CACHE_KEY)
	//@FetchFromCache(cacheName=GET_CATEGORY_CACHE_KEY,cacheType=CacheConstants.CACHE_TYPE_LOCAL)
	public TestCategory getTestCategory(@CacheParam(field="id") Map<String,Object> map){
		TestCategory category = prepareTestCategory((Long)map.get("id"), null);
		return category;
	}
	
	/**
	 * 根据parentIdID获取 TestCategory 从分布式缓存中取
	 * @param id
	 * @return
	 */
	@FetchFromCache(cacheName=GET_CATEGORY_GETBYPARENTID_CACHE_KEY)
	//@FetchFromCache(cacheName=GET_CATEGORY_GETBYPARENTID_CACHE_KEY,cacheType=CacheConstants.CACHE_TYPE_LOCAL)
	public List<TestCategory> getTestCategoryByParentId(@CacheParam Long parentId){
		TestCategory category1 = prepareTestCategory(2L, 1L);
		TestCategory category2 = prepareTestCategory(3L, 1L);
		return Lists.newArrayList(category1,category2);
	}
	
	/**
	 * 获取全部类目从缓存中取
	 * @return
	 */
	@FetchFromCache(cacheName=GET_ALL_CATEGORY_CACHE_KEY)
	//@FetchFromCache(cacheName=GET_ALL_CATEGORY_CACHE_KEY,cacheType=CacheConstants.CACHE_TYPE_LOCAL)
	public List<TestCategory> getAll(){
		TestCategory category = prepareTestCategory(1L, null);
		TestCategory category1 = prepareTestCategory(2L, 1L);
		TestCategory category2 = prepareTestCategory(3L, 1L);
		return Lists.newArrayList(category,category1,category2);
	}
	
	/**
	 * 更新操作的时候一个ID 的时候 这个ID对应的类目 可能被缓存在了 全部类目列表中 以及根据ID找出子类目的列表中 以及
	 * 根据ID获取对象的缓存中 所以要删除 所有key以及key+参数打头的缓存
	 * @param id
	 */
	@RemoveCache(catcheNames={GET_ALL_CATEGORY_CACHE_KEY,GET_CATEGORY_CACHE_KEY,GET_CATEGORY_GETBYPARENTID_CACHE_KEY})
	//@RemoveCache(catcheNames={GET_ALL_CATEGORY_CACHE_KEY,GET_CATEGORY_CACHE_KEY,GET_CATEGORY_GETBYPARENTID_CACHE_KEY},cacheType=CacheConstants.CACHE_TYPE_LOCAL)
	public void updateOrDeleteCategory(@CacheParam Long id){
		//TODO delete 或 更新操作 代码
		
	}
	

	@FetchFromCache(cacheName="XXXXX")
	public int getFromCache(@CacheParam int a,String b,@CacheParam int v){
		return a*10+v+b.hashCode();
	}
	
	@RemoveCache(catcheNames="XXXXX")
	public void deleteCache(@CacheParam int a,@CacheParam int v){
		
	}
	
	private TestCategory prepareTestCategory(Long id,Long parentid){
		TestCategory testCategory = new TestCategory();
		testCategory.setId(id);
		testCategory.setParentId(parentid);
		testCategory.setName("测试类目"+id);
		return testCategory;
	}
}
