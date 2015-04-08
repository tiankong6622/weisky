package com.hz.sunday.cyds.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hz.sunday.cyds.orm.MessageNotice;

public class MessageNoticeMapperTest extends BaseDAOTest{

	@Autowired
	private MessageNoticeMapper messageNoticeMapper;
	
	@Test
	@Rollback(false)
	public void testInsert(){
		MessageNotice mn = new MessageNotice();
		mn.setComment("werwer");
		mn.setTitle("bbbb");
		messageNoticeMapper.insert(mn);
		System.out.println(mn.getId());
	}
	
	@Test
	public void testGetList(){
		List<MessageNotice> list = messageNoticeMapper.getList(null);
		System.out.println(list.size());
	}
}
