package xKing.branch.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.user.dao.UserRepository;
import xKing.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfig.class, MailConfig.class})
public class TestBranchRepository {

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Transactional
	public void testSave() {
		Branch branch = new Branch();
		User user = userRepository.findByUsername("ZhongHao");
		System.out.println(user);
		branch.setBranchName("LongMaoShe");
		branch.setEmail(user.getEmail());
		branch.setUser(user);
		Branch currentBranch = branchRepository.save(branch);
		Assert.assertNotNull(currentBranch);
		System.out.println(currentBranch);
	}
	
	@Test
	@Transactional
	public void testFindByBranchName() {
		Branch branch = branchRepository.findByBranchName("LongMaoShe");
		Assert.assertNotNull(branch);
		System.out.println(branch);
	}
	
	@Test
	@Transactional
	public void testDelete() {
		Branch branch = branchRepository.findByBranchName("LongMaoShe");
		// 删除方法
		branchRepository.delete(branch);
		// 测试有没有联级删除
		User user = userRepository.findByUsername("ZhongHao");
		// 测试有没有删除成功
		Branch branch1 = branchRepository.findByBranchName("LongMaoShe");
		Assert.assertNull(branch1);
		Assert.assertNotNull(user);
		System.out.println(user);
	}

}
