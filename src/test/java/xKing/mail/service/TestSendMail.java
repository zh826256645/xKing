package xKing.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xKing.config.MailConfig;
import xKing.mail.domain.Mail;

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
}
