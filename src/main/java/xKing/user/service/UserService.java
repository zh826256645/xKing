package xKing.user.service;

import xKing.user.domain.User;

public interface UserService {
	// 处理用户登录
	User Login(final User user);
	
	User register(final User user);
	
	User getUserByUsername(final String username);
}
