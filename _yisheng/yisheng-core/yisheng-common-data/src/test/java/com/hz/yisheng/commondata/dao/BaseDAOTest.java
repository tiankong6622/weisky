package com.hz.yisheng.commondata.dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * DAO层测试基类
 * @author ChenJunhui
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application-commondata.xml"}) 
public abstract class BaseDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * 单元测试执行前预先执行的sql语句文件 抽象方法 子类实现
	 * @return
	 */
	protected abstract String[] getSqlFiles();
	
	/**
	 * 在测试方法执行前 预先执行sql
	 */
	@Before
	public void preExecuteSqls(){
		String[] sqls = getSqlFiles();
		if(sqls==null || sqls.length==0){
			return;
		}
		for(String sql:sqls){
			executeSqlScript(sql, true);
		}
	}
}
