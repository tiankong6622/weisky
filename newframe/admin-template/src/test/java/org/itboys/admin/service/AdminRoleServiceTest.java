package org.itboys.admin.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.itboys.admin.entity.AdminRole;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.UpdateOperations;

public class AdminRoleServiceTest extends BaseServiceTest{
	
	@Autowired
	private AdminRoleService adminRoleService;

	@Ignore("忽略")
	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		AdminRole ar = new AdminRole();
		ar.setName("角色");
		adminRoleService.save(ar);
		
		Assert.assertTrue(adminRoleService.getById(ar.getId()).getName().equals(ar.getName()));
	}

	@Ignore("忽略")
	@Test
	public void testGetQueryById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		AdminRole ar = new AdminRole();
		ar.setName("角色333");
		adminRoleService.save(ar);
		
		adminRoleService.delete(ar.getId());
		
		Assert.assertNull(adminRoleService.getById(ar.getId()));
	}

	@Test
	public void testUpdate() {
		UpdateOperations<AdminRole> uo = adminRoleService.getMongoDataSource().createUpdateOperations(AdminRole.class);
		uo.set("name", "角色2222");
		adminRoleService.update(1L, uo);
		
		Assert.assertTrue(adminRoleService.getById(1L).getName().equals("角色2222"));
	}
	
	@Test
	public void testGet(){
		List<Long> list = adminRoleService.getAdminRoleByUserIds(1L);
		System.out.println(list.size());
		Assert.assertNotNull(list);
	}

}
