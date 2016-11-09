package xKing.mail.service;

import xKing.mail.domain.Mail;

public interface MailService {

	void sendActivationEmailToUser(String to,Mail mail);
}
