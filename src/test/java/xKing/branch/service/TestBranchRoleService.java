package xKing.branch.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchRole;
import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, DataConfig.class, MailConfig.class})
public class TestBranchRoleService {

	@Autowired
	private BranchRoleSerivce branchRoleSerivce;
	
	@Autowired
	private BranchService branchService;
	
	@Test
	@Transactional
	public void testAddBranchRole() {
		Branch branch = branchService.findBranchByBranchName("LongMaoShe");
		BranchRole branchRole = branchRoleSerivce.addBranchRole(branch, "newKing", 1);
		Assert.assertNotNull(branchRole);
		System.out.println(branchRole);
	}
}
