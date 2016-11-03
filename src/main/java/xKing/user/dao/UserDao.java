package xKing.user.dao;

import xKing.user.domain.User;

public interface UserDao {
	// 通过 username 获取 User
	User getUserByUsername(String username);
}
