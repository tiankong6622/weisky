package org.itboys.admin.service;

import   static  org.mockito.Mockito.*; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itboys.admin.constant.AdminSessionConstant;
import org.itboys.commons.utils.servlet.ServletContextHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * service 层单元测试父类
 * @author ChenJunhui
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-mongodb-test.xml"}) 
public class BaseServiceTest {
	
	protected HttpServletRequest request = mock(HttpServletRequest.class);
	protected HttpSession session = mock(HttpSession.class);
	protected HttpServletResponse response = mock(HttpServletResponse.class);
	
	/**
	 * save方法需要填充创建人 修改人等从session 获取的信息 
	 * 这里把他mock掉
	 */
	@Before
	public void before(){
		ServletContextHolder.prepare(request, response);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(AdminSessionConstant.SESSION_USER_ID)).thenReturn(1L);
	}
	
	@After
	public void after(){
		ServletContextHolder.clear();
	}
}
