package xKing.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xKing.user.domain.ChangePassword;
import xKing.user.domain.User;
import xKing.user.domain.UserFriend;

public interface UserService {
	// 处理用户登录
	User Login(final User user);

	User register(final User user, String scheme, String hostName);
	
	User getUserByUsername(final String username);
	
	boolean activateUser(final String username, final String key);
	
	User updateProfile(final User userUpdateMessage);

	boolean changePassword(String username, ChangePassword changePassword);

	String updateIntroduction(String username, String newIntroduction);

	String saveUploda(final String originalFilename, final String username);
	
	boolean addFriend(final String username, User currentUser);
	
	boolean setUserFriendState(final String username, final int state, User currentUser);
	
	Page<UserFriend> getFriends(User currentUser, Pageable pageable);
	
	Page<UserFriend> getFriendRequests(User currentUser, Pageable pageable);
	
	boolean handelMemberRequest(User currentUser, String branchName, int state);
	
	boolean requestJoin(User currentUser, String branchName, String message);
}
