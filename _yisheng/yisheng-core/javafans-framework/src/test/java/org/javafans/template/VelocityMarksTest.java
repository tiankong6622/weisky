package org.javafans.template;

import java.io.FileWriter;

import javax.annotation.Resource;

import org.javafans.BaseTest;
import org.junit.Test;

public class VelocityMarksTest extends BaseTest {

	@Resource
	private VelocityMarks velocityMarks;
	
	@Test
	public void test() throws Exception{
		VmContextModel model = new VmContextModel();
		User user = new User();
		user.setUsername("陈俊晖");
		model.addAttribute("content", "您好 您的密码是123456")
			.addAttribute("user", user);
		String text = velocityMarks.getMergedTemplateContent("mail-findpassword.vm", model);
		System.out.println(text);
		FileWriter fw = new FileWriter("D:\\aa.html");
		velocityMarks.mergeTemplate("mail-findpassword.vm", model, fw);
	}
	
	public class User{
		private  String username;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	}
}
