package xKing.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.config.RootConfig;
import xKing.user.domain.ChangePassword;
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
	
	// 测试 register(User user)
	@Test 
	public void testRegister() {
		User user = new User("Zhonghao", "123456", "826256645@qq.com");
		User newUser = userSerivce.register(user);
		Assert.assertNotNull(newUser);
	}
	
	// 测试 getUserByUsername(String username)
	@Test
	public void testGetUserByUsername() {
		User currentUser = userSerivce.getUserByUsername("ZhongHao");
		Assert.assertNotNull(currentUser);
	}
	
	// 测试 updateProfile(User userUpdateMessage)
	@Test
	@Transactional
	public void testUpdateProfile() {
		User userChangeMessage = new User();
		userChangeMessage.setUsername("ZhongHao");
		userChangeMessage.setBlog("www.baidu.com");
		userChangeMessage.setIntroduction("I'm very happy today!!");
		userChangeMessage.setName("钟浩");
		User currentUser = userSerivce.updateProfile(userChangeMessage);
		Assert.assertNotNull(currentUser);
	}

	// 测试 changePassword(String username, ChangePassword changePassword)
	@Test
	@Transactional
	public void testChangePassword() {
		ChangePassword changePassword = new ChangePassword();
		changePassword.setNewPassword("123456789");
		changePassword.setOldPassword("123456");
		boolean bool = userSerivce.changePassword("ZhongHao", changePassword);
		Assert.assertFalse(!bool);
	}
	
	// 测试 updateIntroduction(String username, String newIntroduction)
	@Test
	@Transactional
	public void testUpdateIntroduction() {
		String inroduction = userSerivce.updateIntroduction("ZhongHao", "hahhahaha!");
		Assert.assertNotNull(inroduction);
	}
	
	// 测试 saveUploda(String originalFilename, String username)
	@Test
	@Transactional
	public void testSaveUpload() {
		userSerivce.saveUploda("xxx.jpg", "ZhongHao");
	}

}
