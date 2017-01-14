package xKing.user.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.user.domain.User;

/**
 * UserDao 测试类
 * @author 钟浩
 * @date 2016年10月26日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfig.class, MailConfig.class})
public class TestUserRepository {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void testFindByUsername() {
		User user = userRepository.findByUsername("zhonghao");
		Assert.assertNotNull(user);
		System.out.println(user);
	}
	
	@Test
	@Transactional
	public void testSave() {
		User user = new User();
		user.setUsername("HuangLiNa");
		user.setPassword("123456");
		user.setEmail("zh826256645@gmail.com");
		user.setSex("female");
		user.setKeyDate(new Date());
		User newUser = userRepository.save(user);
		Assert.assertNotNull(newUser);
		System.out.println(newUser);
	}
	
	
	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "value");
		map.put("2", "value");
		map.put("3", "value");
		map.put("4", "value");
		Set<String> set = map.keySet();
		set.remove("1");
		
	}
}
