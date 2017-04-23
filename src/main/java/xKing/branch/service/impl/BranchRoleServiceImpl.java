package xKing.branch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.dao.BranchRoleRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchRoleSerivce;
import xKing.exception.SameNameException;

@Service
@Transactional
public class BranchRoleServiceImpl implements BranchRoleSerivce {

	@Autowired
	private BranchRoleRepository branchRoleRepository;
	
	// 通过 branch_id 和 roleName 查询
	@Override
	public BranchRole findByRoleNameAndBranchId(String roleName, Branch branch) {
		BranchRole branchRole = branchRoleRepository.findByRoleNameAndBranch_id(roleName, branch.getId());
		return branchRole;
	}

	// 添加 BranchRole
	@Override
	public BranchRole addBranchRole(Branch branch, String roleName, int level) {
		if(branchRoleRepository.findByRoleNameAndBranch_id(roleName, branch.getId()) != null) {
			throw new SameNameException("该身份名已经存在！");
		}
		BranchRole currentBranchRole = branchRoleRepository.save(new BranchRole(roleName, level, branch));
		return currentBranchRole;
	}

	// 获取 branch 的 Branch Role
	@Override
	public List<BranchRole> findByBranchId(Branch branch) {
		return branchRoleRepository.findByBranchIdOrderByRoleLevelAsc(branch.getId());
	}
}
