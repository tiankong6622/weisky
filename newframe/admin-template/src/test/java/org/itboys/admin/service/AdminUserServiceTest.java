package org.itboys.admin.service;

import org.itboys.admin.entity.AdminUser;
import org.itboys.commons.utils.random.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUserServiceTest extends BaseServiceTest {

	@Autowired
	private AdminUserService adminUserService;
	
	@Test
	public void testAll(){
		AdminUser au = new AdminUser();
		au.setCt(System.currentTimeMillis());
		au.setUt(System.currentTimeMillis());
		au.setPassword("aaa");
		au.setUsername("大屁股大贵"+RandomUtils.buildRandom(7));
		
		adminUserService.save(au);
		
		Assert.assertNotNull(au.getId());
		
		AdminUser exist = adminUserService.getById(au.getId());
		
		Assert.assertNotNull(exist);
		
		adminUserService.updatePassword(au.getId(), "xpp");
		adminUserService.delete(au.getId());
	}
}
