package xKing.branch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.dao.BranchMemberRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchMemberSerivce;
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
			return null;
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

	// 通过用户 Id 查找 BranchMember 分页
	@Override
	public Page<BranchMember> findByUserId(User user, Pageable pageable) {
		Page<BranchMember> page = branchMemberRepository.findByUser_id(user.getId(), pageable);
		if(page != null && page.getContent() != null && page.getContent().size() > 0) {
			for(BranchMember branchMember: page.getContent()) {
				this.getMemberNum(branchMember.getBranch());
			}
		}
		return page;
	}

	// 排序查找
	@Override
	public Page<BranchMember> findByUserIdOrderByJoinTimeDesc(User user, Pageable pageable) {
		return branchMemberRepository.findByUser_idOrderByJoinTimeDesc(user.getId(), pageable);
	}

	// 通过 branch id 查找 branch
	@Override
	public Page<BranchMember> findByBranch(Branch branch, Pageable pageable) {
		return branchMemberRepository.findByBranch_id(branch.getId(), pageable);
	}

	// 更新 branchMember
	@Override
	public BranchMember update(BranchMember branchMember) {
		return branchMemberRepository.save(branchMember);
	}

	// 设置组织的成员数量
	@Override
	public void getMemberNum(Branch currentBranch) {
			currentBranch.setMemberNum(branchMemberRepository.countByBranch_id(currentBranch.getId()));
	}

	// 查找成员
	@Override
	public BranchMember findByBranchAndMember(Branch branch, long member_id) {
		return branchMemberRepository.findByBranch_idAndId(branch.getId(), member_id);
	}

	// 获取所有用户成员
	@Override
	public List<BranchMember> getBranchMembers(Branch branch) {
		return branchMemberRepository.findByBranch_id(branch.getId());
	}
}
