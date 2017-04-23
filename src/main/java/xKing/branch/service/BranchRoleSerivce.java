package xKing.branch.service;

import java.util.List;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchRole;

public interface BranchRoleSerivce {
	BranchRole addBranchRole(Branch branch, String roleName, int rolelevel);
	
	BranchRole findByRoleNameAndBranchId(String roleName, Branch branch);
	
	List<BranchRole> findByBranchId(Branch branch);
}
