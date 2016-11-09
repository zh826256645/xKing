package xKing.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import xKing.mail.domain.Mail;
import xKing.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private MailSender mailSender;

	@Override
	public void sendActivationEmailToUser(String to, Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("18813973744@163.com");
		message.setTo(to);
		message.setSubject(mail.getSubject());
		message.setText(mail.getText());
		mailSender.send(message);
	}

}
