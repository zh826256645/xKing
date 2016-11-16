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
		User user1 = new User(1, "ZhongHao", "4QrcOUm6Wau+VuBX8g+IPg==");
		user1.setState(1);
		user1.setBlog("NO BLOG");
		user1.setEmail("826256645@qq.com");
		user1.setIntroduction("I'm very happy today!");
		user1.setName("钟浩");
		user1.setSex("man");
		User user2 = new User(1, "HuangLiNa", "4QrcOUm6Wau+VuBX8g+IPg==");
		user2.setState(0);
		userList.add(0,user1);
		userList.add(1,user2);
	}

	// 用户认证
	@Override
	public User getUserByUsername(final String username) {
		for (User user : userList) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	// 添加用户
	@Override
	public User addUser(User user) {
		userList.add(user);
		return user;
	}

	@Override
	public User upDateStateByUsername(String username) {
		User user = this.getUserByUsername(username);
		user.setState(1);
		return user;
	}

	@Override
	public User updateUserProfile(User user) {
		User currentUser = this.getUserByUsername(user.getUsername());
		currentUser.setBlog(user.getBlog());
		currentUser.setIntroduction(user.getIntroduction());
		currentUser.setName(user.getName());
		userList.add(0, currentUser);
		return currentUser;
	}
}
