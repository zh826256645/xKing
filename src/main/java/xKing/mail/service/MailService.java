package xKing.mail.service;

import xKing.mail.domain.Mail;
import xKing.user.domain.User;

public interface MailService {

	void sendActivationEmailToUser(final String to, final Mail mail);
	
	void sendActivationEmailToUserByVelocity(final String to, final Mail mail);
	
	void sendMessageEmailToUserByVelocity(final String to, final Mail mail);
	
	Mail initMessageMail(User user, String content);
}
