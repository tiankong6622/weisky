package org.javafans.cache;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.javafans.cache.aop.CacheAspect;

@Aspect
public class CacheAspect1 extends CacheAspect {
/*
	@Pointcut("execution(public * org.javafans.cache..*.*(..)) && @annotation(org.javafans.cache.annotation.FetchFromCache)")
	public void pointCut() {
		
	}*/

	@Pointcut("execution(public * org.javafans.cache..*.*(..)) && @annotation(org.javafans.cache.annotation.FetchFromCache)")
	public void fetchCachePointCut() {
		// TODO Auto-generated method stub
		
	}

	@Pointcut("execution(public * org.javafans.cache..*.*(..)) && @annotation(org.javafans.cache.annotation.RemoveCache)")
	public void removeCachePointCut() {
		// TODO Auto-generated method stub
		
	}

}
