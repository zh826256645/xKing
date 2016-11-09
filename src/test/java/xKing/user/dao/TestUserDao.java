package xKing.user.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xKing.config.MailConfig;
import xKing.config.RootConfig;
import xKing.user.domain.User;

/**
 * UserDao 测试类
 * @author 钟浩
 * @date 2016年10月26日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, MailConfig.class})
public class TestUserDao {

	@Autowired
	private UserDao userdao;
	
	// 测试 getUserByUsername()
	@Test
	public void testGetUserByUsername() {
		User user = userdao.getUserByUsername("Zhonghao");
		Assert.assertNotNull(user);
	}
	
	// 测试 addUser()
	@Test
	public void testAddUser() {
		User user = new User(2, "HuangLiNa", "123456");
		user.setEmail("826256645@qq.com");
		User newUser = userdao.addUser(user);
		Assert.assertNotNull(newUser);
	}
}
