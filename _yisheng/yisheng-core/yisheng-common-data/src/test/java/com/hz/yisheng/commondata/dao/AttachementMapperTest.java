package com.hz.yisheng.commondata.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.hz.yisheng.commondata.dao.AttachementMapper;
import com.hz.yisheng.commondata.orm.Attachement;

/**
 * 附件表测试
 * @author ChenJunhui
 *
 */
public class AttachementMapperTest extends BaseDAOTest{

	@Autowired
	private AttachementMapper attachementMapper;
	
	@Override
	protected String[] getSqlFiles() {
		return null;
	}
	
	@Test
	public void testAll(){
		Attachement a1 = new Attachement();
		a1.setContentType("1");
		a1.setFileName("3");
		a1.setName("");
		a1.setPath("sdf");
		a1.setObjId("1"); 
		a1.setType("xiaoliuhaochang");
		a1.setSize(1L);
		
		Attachement a2 = new Attachement();
		a2.setContentType("1");
		a2.setFileName("3");
		a2.setName("");
		a2.setPath("sdf");
		a2.setObjId("1"); 
		a2.setType("xiaoliuhaochang");
		a2.setSize(1L);
		
		List<Attachement> list = Lists.newArrayList(a1,a2);
		attachementMapper.batchInsert(list);
		
		List<Attachement> list2 = attachementMapper.findBy("1", "xiaoliuhaochang");
		Assert.assertEquals(list2.size(), 2);
		
		Attachement a = list2.get(0);
		Assert.assertNotNull(attachementMapper.getById(a.getId()));
		
		Assert.assertEquals(attachementMapper.delete(a.getId()), 1);
		Assert.assertEquals(attachementMapper.deleteAll("1", "xiaoliuhaochang"), 2);
	}

}
