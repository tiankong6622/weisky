package com.hz.yisheng.nio.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
/**
 * 单例模式自定义ClassPathXmlApplicationContext
 * 
 * @author WeiSky
 *
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

	private volatile static ClassPathXmlApplicationContext cpxa= null;
	
	
	private ClassPathXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
	}

	public static ClassPathXmlApplicationContext getClassPathXmlApplicationContext(String configLocation){
		//先检查实例是否存在，如果不存在则进入下面的同步块
		if(cpxa == null){
			//同步块,线程安全的创建实例
			synchronized (ClassPathXmlApplicationContext.class) {
				//再次检查实例是否存在，如果不存在才正真的创建实例
				if(cpxa == null){
					cpxa = new ClassPathXmlApplicationContext(configLocation);
				}
			}
		}
		return cpxa;
	}
	
	public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
			throws BeansException {
		super(parent);
		setConfigLocations(configLocations);
		if (refresh) {
			refresh();
		}
	}
}
