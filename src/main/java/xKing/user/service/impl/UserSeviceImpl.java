package xKing.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xKing.user.dao.UserDao;
import xKing.user.domain.User;
import xKing.user.service.UserService;
import xKing.utils.MD5;

// Service 组件
@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
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

}
