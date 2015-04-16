package com.hz.sunday.xccf;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.hz.sunday.xccf.dao.TicketMemberMapper;
import com.hz.sunday.xccf.orm.TicketMemberBean;

/**
 * 客户管理测试类
 * 
 * @author huanglei
 * @date 2015年4月1日
 * @version V1.0
 */

public class TicketMemberMapperTest extends BaseDAOTest {

	@Autowired
	private TicketMemberMapper ticketMemberMapper;

	private static Logger log = Logger.getLogger(TicketMemberMapperTest.class);

	/**
	 * 新增
	 */
	@Test
	public void testInsert() {
		try {
			// 设置报名人员信息
			TicketMemberBean ticketMemberBean = setTicketMemberData();
			// 执行新增
			ticketMemberMapper.insert(ticketMemberBean);
		} catch (Exception e) {
			log.error("TicketMemberMapperTest:testInsert，新增异常" + e);
		}
	}
	
	/**
	 * 获取总记录数
	 */
	@Test
	public void testFindCount() {
		try {
			Map<String, Object> params = Maps.newHashMap();
			Long count = ticketMemberMapper.findCount(params);
			System.out.println("报名人员总数：" + count);
		} catch (Exception e) {
			log.error("TicketMemberMapperTest:testFindCount，获取总记录数时异常" + e);
		}
	}
	
	/**
	 * 获取分页记录
	 */
	@Test
	public void testFindAll() {
		try {
			Map<String, Object> params = Maps.newHashMap();
			List<TicketMemberBean> list = ticketMemberMapper.findAll(params);
			if (null != list && list.size() > 0) {
				for (TicketMemberBean ticketMember : list) {
					System.out.println("姓名：" + ticketMember.getName() + ",公司：" + ticketMember.getCompany() + ",职务：" + ticketMember.getPost());
				}
			} else {
				log.info("还没有报名信息~");
			}
		} catch (Exception e) {
			log.error("TicketMemberMapperTest:testFindAll，获取分页记录时异常" + e);
		}
	}

	/**
	 * 设置报名人员信息
	 */
	private TicketMemberBean setTicketMemberData() {
		TicketMemberBean ticketMember = new TicketMemberBean();
		ticketMember.setName("桃红");
		ticketMember.setCompany("尚学堂");
		ticketMember.setPost("讲师");
		ticketMember.setPhone("13865286129");
		ticketMember.setGoal("其他");
		ticketMember.setNumber(11);
		ticketMember.setMake("我是好孩子");

		return ticketMember;
	}

}
