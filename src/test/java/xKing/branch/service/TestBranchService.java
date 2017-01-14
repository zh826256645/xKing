package xKing.branch.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.service.BranchService;
import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.config.RootConfig;
import xKing.user.service.UserService;

/**
 * 测试 BranchService
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, DataConfig.class, MailConfig.class})
public class TestBranchService {

	@Autowired
	private BranchService branchService;
	
	@Autowired
	private UserService userService;
	
	
	// 测试 findBranchByBranchId(long branchId)
	@Test
	@Transactional
	public void testFindByBranchId() {
		Branch branch = branchService.findBranchByBranchId(1L);
		Assert.assertNotNull(branch);
		System.out.println(branch);
	}
	
	@Test
	@Transactional
	public void testCreateBranch() {
		Branch branch = new Branch();
		branch.setBranchName("xKing");
		branch.setEmail("826256645@qq.com");
		branch.setIntro("hahahah");
		Branch currentBranch = branchService.createBranch(branch, "king", "user", "ZhongHao");
		Assert.assertNotNull(currentBranch);
		System.out.println(currentBranch);
		
	}
	
	@Test
	@Transactional
	public void testGetBranchByUserId() {
		List<Branch> branches = branchService.getBranchByUserId(userService.getUserByUsername("ZhongHao"), new PageRequest(0,2));
	}
}
