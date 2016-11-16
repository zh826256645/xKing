package xKing.mail.service;

import xKing.mail.domain.Mail;

public interface MailService {

	void sendActivationEmailToUser(final String to, final Mail mail);
	
	void sendActivationEmailToUserByVelocity(final String to, final Mail mail);
}
