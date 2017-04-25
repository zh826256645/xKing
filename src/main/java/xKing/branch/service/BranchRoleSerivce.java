package xKing.branch.service;

import java.util.List;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.branch.domain.BranchRole;

public interface BranchRoleSerivce {
	BranchRole addBranchRole(Branch branch, String roleName, int rolelevel);
	
	BranchRole addBranchRole(Branch branch, BranchMember currentMember, String roleName, int rolelevel);
	
	BranchRole findByRoleNameAndBranchId(String roleName, Branch branch);
	
	List<BranchRole> findByBranchId(Branch branch);
	
	BranchRole ChangeBranchRole(Branch currentBranch, BranchRole oldBranchRole, String newRoleName, int newRoleLevel);
}
