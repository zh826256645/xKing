package xKing.branch.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.config.RootConfig;
import xKing.user.domain.User;
import xKing.user.service.UserService;

/**
 * 测试 BranchMemberService
 * 
 * @author 钟浩
 * @date 2016年11月29日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, DataConfig.class, MailConfig.class})
public class TestBranchMemberService {

	@Autowired
	private BranchMemberSerivce branchMemberService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private BranchRoleSerivce branchRoleSerivce;
	
	
	@Test
	@Transactional
	public void testFindByBranchidAndUserId() {
		Branch branch = branchService.findBranchByBranchId(1L);
		User user = userService.getUserByUsername("ZhongHao");
		BranchMember branchMember = branchMemberService.findByBranchidAndUserId(branch, user);
		Assert.assertNotNull(branchMember);
		System.out.println(branchMember);	
	}
	
	@Test
	@Transactional
	public void testAddBranchMember() {
		Branch branch = branchService.findBranchByBranchId(1L);
		User user = userService.getUserByUsername("ZhongHao1");
		BranchRole branchRole = branchRoleSerivce.findByRoleNameAndBranchId("King", branch);
		BranchMember branchMember = branchMemberService.addBranchMember("ZhongHao", user.getEmail(), branch, branchRole, user);
		Assert.assertNotNull(branchMember);
		System.out.println(branchMember);
	}
	
	@Test
	@Transactional
	public void testfindByUserId() {
		List<BranchMember> branchMembers = branchMemberService.findByUserId(userService.getUserByUsername("ZhongHao"));
		Assert.assertNotNull(branchMembers.get(0));
	}
}
