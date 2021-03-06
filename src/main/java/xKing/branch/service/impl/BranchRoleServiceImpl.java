package xKing.branch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.dao.BranchRoleRepository;
import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;
import xKing.branch.service.BranchRoleSerivce;
import xKing.exception.FaultyOperationException;
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
		if(roleName != null && branchRoleRepository.findByRoleNameAndBranch_id(roleName, branch.getId()) != null) {
			throw new SameNameException("该身份名已经存在！");
		}
		if(level <= 0) {
			throw new FaultyOperationException("操作错误");
		}
		BranchRole currentBranchRole = branchRoleRepository.save(new BranchRole(roleName, level, branch));
		return currentBranchRole;
	}

	// 获取 branch 的 Branch Role
	@Override
	public List<BranchRole> findByBranchId(Branch branch) {
		return branchRoleRepository.findByBranchIdOrderByRoleLevelAsc(branch.getId());
	}

	// 添加 BranchRole
	@Override
	public BranchRole addBranchRole(Branch branch, BranchMember currentMember, String roleName, int rolelevel) {
		
		if(roleName != null && branchRoleRepository.findByRoleNameAndBranch_id(roleName, branch.getId()) != null) {
			throw new SameNameException("该身份名已经存在！");
		}
		if( rolelevel <= 0) {
			throw new FaultyOperationException("操作错误");
		}
		if(currentMember.getBranchRole().getRoleLevel() > rolelevel) {
			throw new FaultyOperationException("你不能添加比自己权限大的角色");
		}
		BranchRole currentBranchRole = branchRoleRepository.save(new BranchRole(roleName,  rolelevel, branch));
		return currentBranchRole;
	}

	// 修改 branch
	@Override
	public BranchRole ChangeBranchRole(Branch currentBranch, BranchRole oldBranchRole, String newRoleName, int newRoleLevel) {
		if(oldBranchRole == null || newRoleName == null || newRoleLevel <= 0) {
			throw new FaultyOperationException("错误操作");
		}
		
		if(newRoleLevel == oldBranchRole.getRoleLevel() && branchRoleRepository.findByRoleNameAndBranch_id(newRoleName, currentBranch.getId()) != null) {
			throw new SameNameException("该身份名已经存在！");
		}
		
		oldBranchRole.setRoleName(newRoleName);
		oldBranchRole.setRoleLevel(newRoleLevel);
		return branchRoleRepository.save(oldBranchRole);
	}
}
