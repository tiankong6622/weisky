package org.javafans.mail;

import javax.annotation.Resource;

import org.javafans.BaseTest;
import org.junit.Test;


public class MailSenderTest extends BaseTest{

	@Resource
	private MailSender mailSender;

	@Test
	public void testSendMail(){
		/*//直接发内容
		String subject = "0903/17:33分";
		String context = "<body><p>Hello Html Email</p><img src='http://www.baidu.com/img/baidu_sylogo1.gif' /></body>";
		String[] toMail = new String[]{"shevxiaochenko@163.com","11@qq.com","liu1159125540@sina.com"};
		mailSender.sendRichTextMail(context, subject, toMail);
		
		//模板发内容
		VmContextModel model = new VmContextModel();
		User user = new User();
		user.setUsername("陈俊晖");
		model.addAttribute("content", "您好 您的密码是123456")
			.addAttribute("user", user);
		mailSender.sendRichTextMail("mail-findpassword.vm", model, subject, toMail);*/
		
		
		String subject = "测试标题erwe";
		String context = "测试内werw容";
		String[] toMail = new String[]{"1159125540@qq.com"};
		/*Map<String,InputStream> map = new HashMap<String,InputStream>();
		try {
			map.put("附件1",new FileInputStream(new File("d:\\09090909.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,InputStreamSource> map = new HashMap<String,InputStreamSource>();
		map.put("附件1",new FileSystemResource(new File("d:\\09090909.txt")));
		mailSender.sendMailWithAttachments(context, subject,map, toMail);*/
		
		
		
		//String[] toMail = new String[]{"2418568482@qq.com","1159125540@qq.com","1142256159@qq.com","361791989@qq.com","tangpeng@cncr-it.com"};
		mailSender.sendRichTextMail(context, subject, toMail);

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
