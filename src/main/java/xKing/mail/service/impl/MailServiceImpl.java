package xKing.mail.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import xKing.mail.domain.Mail;
import xKing.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;

	// 发送简单的邮件
	@Override
	public void sendActivationEmailToUser(final String to, final Mail mail) {
		SimpleMailMessage message = this.setting(to, mail);
		mailSender.send(message);
	}

	// 使用 Velocity 模板发送
	@Override
	public void sendActivationEmailToUserByVelocity(final String to, final Mail mail){
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			Mail velocityMail = this.velocitySetting(mail, "emailTemplate.vm");
			helper = this.setting(helper, to, velocityMail);
		} catch (MessagingException e) {
			throw new RuntimeException();
		}
		mailSender.send(message);

	}
	
	// SimpleMailMessage 设置
	protected SimpleMailMessage setting(String to, Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("18813973744@163.com");
		message.setTo(to);
		message.setSubject(mail.getSubject());
		message.setText(mail.getText());
		return message;
	}
	
	// MimeMessageHelper 设置
	protected MimeMessageHelper setting(MimeMessageHelper helper, String to, Mail mail) throws MessagingException {
		helper.setFrom("18813973744@163.com");
		helper.setTo(to);
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getText(), true);
		return helper;
	}
	
	// Velocity 模板 Mail 设置
	protected Mail velocitySetting(Mail mail, String emailTemplate) {
		Mail mailVelocity = mail;
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("username", mail.getUsername());
		model.put("key", mail.getActiveCode());
		model.put("text", mail.getText());
		model.put("date", mail.getTime());
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplate,
				"UTF-8", model);
		mailVelocity.setText(text);
		return mailVelocity;
	}
}
