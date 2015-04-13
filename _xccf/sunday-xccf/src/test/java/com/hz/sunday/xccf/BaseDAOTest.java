package com.hz.sunday.xccf;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * DAO层测试基类
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application-dao-test.xml"}) 
public abstract class BaseDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

	
}
