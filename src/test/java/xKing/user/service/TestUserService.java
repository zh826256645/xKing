package xKing.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.config.RootConfig;
import xKing.user.domain.User;

/**
 * UserService 的测试类
 * @author 钟浩
 * @date 2016年10月26日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, DataConfig.class, MailConfig.class})
public class TestUserService {

	@Autowired
	private UserService userSerivce;
	
	// 测试 login()
	@Test
	public void testLogin() {
		User user = new User("Zhonghao", "123456");
		User _user = userSerivce.Login(user);
		Assert.assertNotNull(_user);
	}
	
	// 测试 register()
	@Test
	public void testRegister() {
		User user = new User("Zhonghao", "123456", "826256645@qq.com");
		User newUser = userSerivce.register(user);
		Assert.assertNotNull(newUser);
	}
}
