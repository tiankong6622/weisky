package org.itboys.admin.service;

import static org.junit.Assert.fail;

import org.itboys.admin.entity.AdminOrg;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.UpdateOperations;

public class AdminOrgServiceTest extends BaseServiceTest{
	
	@Autowired
	private AdminOrgService adminOrgService;

	@Ignore("忽略")
	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Ignore("忽略")
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveLongIdEntity() {
		AdminOrg ao = new AdminOrg();
		ao.setDesc("描述");
		ao.setName("组织");
		ao.setLevel(1);
		adminOrgService.save(ao);
		
		Assert.assertTrue(adminOrgService.getById(ao.getId()).getLevel().equals(ao.getLevel()));
	}

	@Test
	public void testDelete() {
		AdminOrg ao = new AdminOrg();
		ao.setDesc("描述2");
		ao.setName("组织2");
		ao.setLevel(1);
		adminOrgService.save(ao);
		
		adminOrgService.delete(ao.getId());
		
		Assert.assertNull(adminOrgService.getById(ao.getId()));
	}

	@Test
	public void testUpdate() {
		UpdateOperations<AdminOrg> uo = adminOrgService.getMongoDataSource().createUpdateOperations(AdminOrg.class);
		uo.set("level", 2);
		adminOrgService.update(1L, uo);
		
		Assert.assertTrue(adminOrgService.getById(1L).getLevel().equals(2));
	}

}
