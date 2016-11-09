package xKing.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xKing.mail.domain.Mail;
import xKing.mail.service.MailService;
import xKing.user.dao.UserDao;
import xKing.user.domain.User;
import xKing.user.exception.SameUsernameException;
import xKing.user.service.UserService;
import xKing.utils.MD5;

// Service 组件
@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MailService mailSerivce;
	
	// 用户认证
	@Override
	public User Login(User user) {
		String passwordMd5 = MD5.EncoderByMd5(user.getPassword());
		User userData = userDao.getUserByUsername(user.getUsername());
		if(userData.getPassword().equals(passwordMd5)) {
			return userData;
		}
		return null;
	}

	// 用户注册
	@Override
	public User register(User user) {
		User getUser = userDao.getUserByUsername(user.getUsername());
		if(getUser != null) {
			throw new SameUsernameException("用户名重复");
		}
		User newUser = userDao.addUser(user);
		Mail mail = new Mail();
		mail.setSubject("xKing 的激活信息");
		mail.setText("这是一封来自 xKing 的激活信息！不要删了呀");
		mailSerivce.sendActivationEmailToUser(user.getEmail(), mail);
		return newUser;
	}

}
