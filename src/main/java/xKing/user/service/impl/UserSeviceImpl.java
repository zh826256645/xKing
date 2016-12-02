package xKing.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.mail.domain.Mail;
import xKing.mail.service.MailService;
import xKing.user.dao.UserRepository;
import xKing.user.domain.ChangePassword;
import xKing.user.domain.User;
import xKing.user.exception.SameUsernameException;
import xKing.user.exception.UserActivateErrorException;
import xKing.user.exception.UserNotExistException;
import xKing.user.service.UserService;
import xKing.utils.FontImageUtils;
import xKing.utils.MD5;
import xKing.utils.Utils;

// Service 组件
@Service
@Transactional
public class UserSeviceImpl implements UserService {

	@Autowired
	private MailService mailSerivce;
	
	@Autowired
	private UserRepository userRepository;

	
	// 用户认证
	@Override
	public User Login(User user) {
		String passwordMd5 = MD5.EncoderByMd5(user.getPassword());
		User userData = userRepository.findByUsername(user.getUsername());
		if(userData.getPassword().equals(passwordMd5)) {
			return userData;
		}
		return null;
	}

	// 用户注册
	@Override
	public User register(User user) {
		User getUser = userRepository.findByUsername(user.getUsername());
		if(getUser != null) {
			throw new SameUsernameException("用户名重复");
		}
		User newUser = userRepository.save(initNewUser(user));
		Mail mail = this.activationMail(newUser);
		mailSerivce.sendActivationEmailToUserByVelocity(user.getEmail(), mail);
		return newUser;
	}

	// 根据用户名获取用户
	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user != null) {
			return user;
		} else {
			throw new UserNotExistException("用户不存在！");
		}
	}
	
	// 用户激活
	@Override
	public boolean activateUser(String username, String key) {
		User user = this.getUserByUsername(username);
		if(user.getState() == 0) {
			if(user.getKeyCode().equals(key)) {
				long keyDate = user.getKeyDate().getTime();
				if(keyDate - (new Date()).getTime() < 1800000) {
					user.setState(1);
					userRepository.save(user);
					return true;
				} else {
					User userAddNewKey = this.addNewKey(user);
					Mail mail = this.activationMail(userAddNewKey);
					mailSerivce.sendActivationEmailToUserByVelocity(userAddNewKey.getEmail(), mail);
					throw new UserActivateErrorException("你的激活码已过期，新的激活码已发送至你的邮箱，请前去邮箱激活!");
				}
			} else {
				throw new UserActivateErrorException("激活码错误!");
			}
		} else {
			return false;
		}
	}
	
	// 更新用户信息
	@Override
	public User updateProfile(User userUpdateMessage) {
		User user = this.getUserByUsername(userUpdateMessage.getUsername());
		
		user.setBlog(userUpdateMessage.getBlog());
		user.setIntroduction(userUpdateMessage.getIntroduction());
		user.setName(userUpdateMessage.getName());
		
		User currentUser = userRepository.save(user);
		return currentUser;
	}
	
	// 更改密码
	@Override
	public boolean changePassword(String username, ChangePassword changePassword) {
		User currentUser = this.getUserByUsername(username);
		String oldPassword = MD5.EncoderByMd5(changePassword.getOldPassword());
		if(currentUser.getPassword().equals(oldPassword)) {
			currentUser.setPassword(MD5.EncoderByMd5(changePassword.getNewPassword()));
			userRepository.save(currentUser);
			return true;
		} else {
			return false;
		}
	}

	// 更新 introduction
	@Override
	public String updateIntroduction(String username, String newIntroduction) {
			User currentUser = userRepository.findByUsername(username);
			currentUser.setIntroduction(newIntroduction);
			userRepository.save(currentUser);
			return newIntroduction;
	}

	// 保存用户上传文件
	@Override
	public String saveUploda(String originalFilename, String username) {
		String extensionName = getExtensionName(originalFilename);
		User currentUser = this.getUserByUsername(username);
		if(extensionName != null) {
			long pid = currentUser.getId();
			String picuture = pid + extensionName;
			currentUser.setPicture(picuture);
			userRepository.save(currentUser);
			return picuture;
		}
		return null;
	}
	
	
	// 初始化新用
	protected User initNewUser(User user) {
		// 设置激活码
		User newUser = this.addNewKey(user);
		// MD5 加密密码
		String password = user.getPassword();
		String MD5Password = MD5.EncoderByMd5(password);
		newUser.setPassword(MD5Password);	
		FontImageUtils utils = new FontImageUtils();
		newUser.setPicture(utils.randomColor().getRGB() + "");
		return newUser;
	}
	
	// 设置用户状态，添加验证码
	protected User addNewKey(User user) {
		user.setState(0);
		user.setKeyCode(Utils.getUUID());
		user.setKeyDate(new Date());
		return user;
	}
	
	// 设置激活邮件
	protected Mail activationMail(User user) {
		Mail mail = new Mail();
		mail.setSubject("xKing 账户激活邮件");
		mail.setActiveCode(user.getKeyCode());
		mail.setUsername(user.getUsername());
		mail.setText("");
		mail.setTime(Utils.getCurrentDate());
		return mail;
	}
	
	// 截取文件名后缀
	protected String getExtensionName(String fileName) {
		int i = fileName.lastIndexOf(".");
		if((i > -1) && i < (fileName.length() - 1)) {
			return fileName.substring(i); 
		}
		return null;
	}
	
}
