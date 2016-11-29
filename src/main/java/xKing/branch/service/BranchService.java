package xKing.branch.service;

import java.util.List;

import xKing.branch.domain.Branch;

public interface BranchService {

	Branch createBranch(Branch branch, String yourRoleName, String newComerRoleName ,String username);
	
	Branch findBranchByBranchName(String branchName);
	
	Branch findBranchByBranchId(long branchId);
	
	List<Branch> getBranchByUsername(String username);
	
}
