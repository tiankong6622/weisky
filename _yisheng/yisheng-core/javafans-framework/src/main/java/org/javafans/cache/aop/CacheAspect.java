package org.javafans.cache.aop;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.javafans.cache.CacheProvider;
import org.javafans.cache.annotation.CacheParam;
import org.javafans.cache.annotation.FetchFromCache;
import org.javafans.cache.annotation.RemoveCache;
import org.javafans.cache.constants.CacheConstants;

/**
 * 缓存拦截器 子类继承 该类  重写pointCut 描述切入点
 * @author ChenJunhui
 */
public abstract class CacheAspect {

	public static final String FETCH_FROM_CACHE_POINT_CUT_NAME = "fetchCachePointCut()";
	public static final String REMOVE_CACHE_POINT_CUT_NAME = "removeCachePointCut()";
	
	/**
	 *  缓存取值切入点描述 子类实现该类 重写该方法 比如这样写
	 *  @Pointcut("execution(public * org.javafans.cache..*.*(..)) && @annotation(org.javafans.cache.annotation.FetchFromCache)")
		public void fetchCachePointCut() {
			
		}
	 */
	public abstract void fetchCachePointCut();
	/**
	 * 从缓存中删除数据切入点 子类实现该类 重写该方法 比如这样写
	 * @Pointcut("execution(public * org.javafans.cache..*.*(..)) && @annotation(org.javafans.cache.annotation.RemoveCache)")
		public void removeCachePointCut() {
			
		}
	 */
	public abstract void removeCachePointCut();
	
	@Resource(name="memcachedProvider")
	private CacheProvider memcachedProvider;
	@Resource(name="ehcacheProvider")
	private CacheProvider ehcacheProvider;
	/**
	 * 缓存取数据环绕通知
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	@Around(FETCH_FROM_CACHE_POINT_CUT_NAME)
	public Object fetchFromCachePoint(ProceedingJoinPoint invocation) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
		Method targetMethod = methodSignature.getMethod();
		if(targetMethod.isAnnotationPresent(FetchFromCache.class)){
			FetchFromCache annotation = targetMethod.getAnnotation(FetchFromCache.class);
			String cacheName = annotation.cacheName();
			//组装缓存key
			cacheName=cacheName+getCacheParamValue(invocation, targetMethod);
			String cacheType = annotation.cacheType();
			CacheProvider cacheProvider = StringUtils.equals(cacheType, CacheConstants.CACHE_TYPE_REMOTE)?memcachedProvider:ehcacheProvider;
			//根据缓存key去缓存中取数据
			Object cacheValue = cacheProvider.get(cacheName);
			if(cacheValue!=null){
			//	System.out.println("哈哈 从"+cacheType+"缓存中取得了数据:"+cacheValue);
				return cacheValue;
			}else{
				Object returnValue = invocation.proceed();
				//将返回值置于缓存中
			//	System.out.println("将取得的值"+returnValue+"放入"+cacheType+"缓存 缓存key为"+cacheName);
				cacheProvider.put(cacheName, annotation.cacheTime(), (Serializable)returnValue);
				return returnValue;
			}
		}
		//缓存中取不到直接返回结果
		return invocation.proceed();
	}
	
	/**
	 * 删除缓存中的数据环绕通知
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	@Around(REMOVE_CACHE_POINT_CUT_NAME)
	public Object removeFromCachePoint(ProceedingJoinPoint invocation) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
		Method targetMethod = methodSignature.getMethod();
		if(targetMethod.isAnnotationPresent(RemoveCache.class)){
			RemoveCache annotation = targetMethod.getAnnotation(RemoveCache.class);
			String[] cacheNameArr = annotation.catcheNames();
			String cacheType = annotation.cacheType();
			CacheProvider cacheProvider = StringUtils.equals(cacheType, CacheConstants.CACHE_TYPE_REMOTE)?memcachedProvider:ehcacheProvider;
			if(cacheNameArr!=null && cacheNameArr.length>0){
				String cacheParamValue = getCacheParamValue(invocation, targetMethod);
				for(String cacheName:cacheNameArr){
					cacheProvider.removeKey(cacheName);
					System.out.println("从"+cacheType+"删除缓存:"+cacheName);
					if(StringUtils.isNotBlank(cacheParamValue)){
						cacheProvider.removeKey(cacheName, cacheParamValue);
						System.out.println("从"+cacheType+"删除缓存:"+cacheName+cacheParamValue);
					}
				}
			}
		}
		//缓存中取不到直接返回结果
		return invocation.proceed();
	}

	/**
	 * 根据参数的注释获取缓存的值
	 * @author ChenJunhui
	 * @param invocation
	 * @param targetMethod
	 */
	private String getCacheParamValue(ProceedingJoinPoint invocation,
			Method targetMethod) throws Exception{
		String suffix = StringUtils.EMPTY;//组装缓存后缀
		//获取参数上的注解信息
		Annotation[][] paramAnnotationArr = targetMethod.getParameterAnnotations();
		if(paramAnnotationArr.length!=0){
			Object params[] = invocation.getArgs();
			for(int i=0,length=paramAnnotationArr.length;i<length;i++){
				Annotation[] paramAnnotationParams = paramAnnotationArr[i];
				for(int j=0;j<paramAnnotationParams.length;j++){
					Annotation paramAnnotation = paramAnnotationParams[j];
					if(paramAnnotation.annotationType().equals(CacheParam.class)){
						CacheParam cacheParam = (CacheParam) paramAnnotation;
						String field = cacheParam.field();
						Object value = null;
						if(StringUtils.isNotBlank(field)){
							value = PropertyUtils.getProperty(params[i], field); //Reflections.getFieldValue(params[i], field);
						}else{
							value = params[i];
						}
						if(value!=null){
							suffix=suffix+value.toString();
						}
					}
				}
			}
		}
		return suffix;
	}
}
