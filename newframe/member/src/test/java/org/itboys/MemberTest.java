package org.itboys;

import static org.junit.Assert.*;

import org.itboys.member.entity.mongo.MemberActiveCode;
import org.itboys.member.service.MemberActiveCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberTest {
	@Autowired
	private MemberActiveCodeService memberActiveCodeService;
	@Test
	public void test() {
		MemberActiveCode memberActiveCode = new MemberActiveCode();
		memberActiveCode.setCode("1234");
		memberActiveCode.setType("0");
		memberActiveCode.setValue("111");
		memberActiveCodeService.memberActiveCodeSave(memberActiveCode);
		System.out.println(memberActiveCodeService.getRecentlyActiveCodeByValue("111", 161545));
	}

}
