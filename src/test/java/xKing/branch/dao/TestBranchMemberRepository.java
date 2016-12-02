package xKing.branch.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.BranchMember;
import xKing.config.DataConfig;
import xKing.config.MailConfig;
import xKing.config.RootConfig;

/**
 * 测试 BranchMemberRepository
 * 
 * @author 钟浩
 * @date 2016年11月29日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, DataConfig.class, MailConfig.class})
public class TestBranchMemberRepository {
	
	@Autowired
	private BranchMemberRepository branchMemberRepository;

	
	// 测试 BranchMember findByBranch_idAndUser_id(long branch_id, long user_id)
	@Test
	@Transactional
	public void testFindByBranch_idAndUser_id() {
		BranchMember branchMember = branchMemberRepository.findByBranch_idAndUser_id(1L, 1L);
		Assert.assertNotNull(branchMember);
		System.out.println(branchMember);
	}
	
	// 测试 List<BranchMember> findByUser_id(long user_id)
	@Test
	@Transactional
	public void testFindByUser_id() {
		List<BranchMember> list = branchMemberRepository.findByUser_id(1L);
		BranchMember branchMember = list.get(0);
		Assert.assertNotNull(branchMember);
		System.out.println(branchMember);
	}
	
	@Test
	@Transactional
	public void testPageFindByUser_id() {
		Pageable pageable = new PageRequest(0, 2);
		Page<BranchMember> page = branchMemberRepository.findByUser_id(1L, pageable);
		System.out.println(page.getSize());
		System.out.println(page.getTotalPages());
		System.out.println(page.getNumber());
		System.out.println(page.getContent().size());
		System.out.println(page.getTotalElements());
	}
	
	@Test
	@Transactional
	public void testFindByUser_idOrderByJointime() {
		Pageable pageable = new PageRequest(0, 2);
		Page<BranchMember> page = branchMemberRepository.findByUser_idOrderByJoinTimeDesc(1L, pageable);
		System.out.println(page.getContent().get(0).getBranch().getBranchName());
		System.out.println(page.getContent().get(1).getBranch().getBranchName());
	}
}
