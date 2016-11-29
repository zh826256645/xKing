package xKing.branch.service;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchRole;

public interface BranchRoleSerivce {
	BranchRole addBranchRole(Branch branch, String roleName, int rolelevel);
	
	BranchRole findByRoleNameAndBranchId(String roleName, Branch branch);
}
