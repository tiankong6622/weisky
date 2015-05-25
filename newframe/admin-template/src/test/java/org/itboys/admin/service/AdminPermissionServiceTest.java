package org.itboys.admin.service;

import static org.junit.Assert.fail;

import org.itboys.admin.entity.AdminPermission;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.UpdateOperations;

public class AdminPermissionServiceTest extends BaseServiceTest{
	
	@Autowired
	private AdminPermissionService adminPermissionService;

	@Ignore("忽略")
	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveLongIdEntity() {
		AdminPermission ap = new AdminPermission();
		ap.setCode("1");
		ap.setName("权限名称");
		adminPermissionService.save(ap);
		
		Assert.assertTrue(adminPermissionService.getById(ap.getId()).getName().equals(ap.getName()));
	}

	@Test
	public void testDelete() {
		AdminPermission ap = new AdminPermission();
		ap.setCode("1");
		ap.setName("权限名称3333");
		adminPermissionService.save(ap);
		
		adminPermissionService.delete(ap.getId());
		
		Assert.assertNull(adminPermissionService.getById(ap.getId()));
	}

	@Test
	public void testUpdate() {
		UpdateOperations<AdminPermission> uo = adminPermissionService.getMongoDataSource().createUpdateOperations(AdminPermission.class);
		uo.set("name", "权限名称222");
		adminPermissionService.update(1L, uo);
		
		Assert.assertTrue(adminPermissionService.getById(1L).getName().equals("权限名称222"));
	}

}
