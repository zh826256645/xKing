package xKing.user.service;

import xKing.user.domain.ChangePassword;
import xKing.user.domain.User;

public interface UserService {
	// 处理用户登录
	User Login(final User user);
	
	User register(final User user);
	
	User getUserByUsername(final String username);
	
	boolean activateUser(final String username, final String key);
	
	User updateProfile(final User userUpdateMessage);

	boolean changePassword(String username, ChangePassword changePassword);

	String updateIntroduction(String username, String newIntroduction);

	String saveUploda(final String originalFilename, final String username);
	
	boolean addFriend(final String username, User currentUser);
}
