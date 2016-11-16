package xKing.mail.service;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xKing.config.MailConfig;
import xKing.mail.domain.Mail;
import xKing.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MailConfig.class})
public class TestSendMail {
	
	@Autowired
	private MailService mailService;
	
	@Test
	public void testSendActivationEmailToUser() {
		Mail mail = new Mail();
		mail.setSubject("This is a Test Mail");
		mailService.sendActivationEmailToUser("826256645@qq.com", mail);
	}
	
	@Test
	public void testSendActivationEmailToUserByVelocity() throws MessagingException {
		Mail mail = new Mail();
		User user = new User();
		user.setUsername("ZhongHao");
		mail.setSubject("这是一封激活邮件");
		mail.setActiveCode("323243545");
		mail.setText("HHAHAHHAHAHAHAHA");
		mailService.sendActivationEmailToUserByVelocity("826256645@qq.com", mail);
	}
}
