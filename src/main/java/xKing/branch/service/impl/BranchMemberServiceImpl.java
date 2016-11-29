package xKing.branch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.dao.BranchMemberRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchMemberSerivce;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.user.domain.User;

/**
 * BranchMember Service
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
@Service
@Transactional
public class BranchMemberServiceImpl implements BranchMemberSerivce{

	@Autowired
	private BranchMemberRepository branchMemberRepository;
	
	
	// 通过 branch_id 和 user_id 查找 BranchMember
	@Override
	public BranchMember findByBranchidAndUserId(Branch branch, User user) {
		BranchMember branchMember = branchMemberRepository.findByBranch_idAndUser_id(branch.getId(), user.getId());
		if(branchMember == null) {
			throw new AbsentException("BranchMember 不存在！");
		}
		return branchMember;
	}

	// 添加 BranchMember
	@Override
	public BranchMember addBranchMember(String memberName, String email, Branch branch, BranchRole branchRole, User user) {
		if(branchMemberRepository.findByBranch_idAndUser_id(branch.getId(), user.getId()) != null) {
			throw new ExistedException("该成员已存在！");
		} 
		BranchMember branchMember = branchMemberRepository.save(
				new BranchMember(memberName, email, new Date(), branch, branchRole, user));
		return branchMember;
	}

	// 通过用户 Id 查找 BranchMember
	@Override
	public List<BranchMember> findByUserId(User user) {
		List<BranchMember> branchMembers = branchMemberRepository.findByUser_id(user.getId());
		return branchMembers;
	}

}
