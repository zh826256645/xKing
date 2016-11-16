package xKing.user.dao;

import xKing.user.domain.User;

public interface UserDao {
	// 通过 username 获取 User
	User getUserByUsername(final String username);
	
	User addUser(final User user);
	
	User upDateStateByUsername(final String username);
	
	User updateUserProfile(final User user);
}
