package xKing.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import xKing.user.dao.UserDao;
import xKing.user.domain.User;

@Repository
public class UserDaoTestImpl implements UserDao {
	
	private static List<User> userList = new ArrayList<User>();
	
	static {
		User user1 = new User(1, "Zhonghao", "4QrcOUm6Wau+VuBX8g+IPg==");
		userList.add(user1);
	}

	// 用户认证
	@Override
	public User getUserByUsername(String username) {
		for (User user : userList) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
}
