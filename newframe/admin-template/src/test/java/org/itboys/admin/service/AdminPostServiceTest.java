package org.itboys.admin.service;

import static org.junit.Assert.fail;

import org.itboys.admin.entity.AdminPost;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.UpdateOperations;

public class AdminPostServiceTest extends BaseServiceTest{
	
	@Autowired
	private AdminPostService adminPostService;

	@Ignore("忽略")
	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		AdminPost ap = new AdminPost();
		ap.setName("总经理");
		ap.setLevel(1);
		adminPostService.save(ap);
		
		Assert.assertTrue(adminPostService.getById(ap.getId()).getName().equals(ap.getName()));
	}

	@Test
	public void testDelete() {
		AdminPost ap = new AdminPost();
		ap.setName("总经理222");
		ap.setLevel(1);
		adminPostService.save(ap);
		
		adminPostService.delete(ap.getId());
		
		Assert.assertNull(adminPostService.getById(2L));
	}

	@Test
	public void testUpdate() {
		UpdateOperations<AdminPost> uo = adminPostService.getMongoDataSource().createUpdateOperations(AdminPost.class);
		uo.set("name", "CEO");
		adminPostService.update(1L, uo);
		
		Assert.assertTrue(adminPostService.getById(1L).getName().equals("CEO"));
	}

}
